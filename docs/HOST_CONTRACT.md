# Host contract

S8TNLib is a library, not a mod. It registers nothing and hooks nothing by
itself. A host mod on Minecraft 1.12.2 and Cleanroom merges it into its mod
jar, supplies the Minecraft-side hooks and the providers, and runs it on the
libraries that Minecraft and Cleanroom ship. Demonica is the only host. This
page lists what the host supplies as of the syncline `demonica@61fa479d`, and
how Demonica does it.

## The artifact

`./gradlew publishToMavenLocal` publishes `com.s8tnlib:s8tnlib` to
`mavenLocal`, and nowhere else.

- **The jar** holds the classes of the 60 kept files, all in
  `com.gtnewhorizon.gtnhlib`, and a manifest. It has no mod metadata, mixin
  configs, access transformer, nested jars, FML manifest attributes or
  notices at its root.
- **The sources jar** holds the 60 source files.
- **The POM and the Gradle module metadata** declare no dependencies.
- **Both manifests** say where the jar comes from:
  - `S8TNLib-Base`: the upstream GTNHLib commit, `gtnhlib@9644810c…`;
  - `S8TNLib-Synced-From`: the Demonica commit S8TNLib last synced from,
    `demonica@61fa479d…`. The kept files equal it up to 0.1.1;
  - `S8TNLib-Commit`: the S8TNLib commit the jar was built from, with
    `-dirty` if tracked files had uncommitted changes. A host can pin it.

`verifyPublishedJars` checks all of this, in `./gradlew build` and before
every publication.

The jar uses Minecraft's MCP names and is not remapped. The host remaps its
mod jar, S8TNLib's classes included, to SRG.

## Rules

- **Merge the jar into the mod jar.** S8TNLib must never sit on the app
  class path, in production or in a dev run. Classes there are loaded by the
  app class loader while the code that calls them runs in Cleanroom's
  `LaunchClassLoader`, which fails with a `LinkageError`, a loader
  constraint violation. Demonica's `docs/celeritas/SPIKE.md` ("Dev class
  loading") records that failure for GLSM, and says GTNHLib must travel the
  same way. Demonica's dev runs pass its merged classes to Cleanroom through
  `-Dcrl.dev.extrapath`.
- **Never relocate `com.gtnewhorizon.gtnhlib`.** Demonica matches the
  package name as a string: `GLSMRedirector` in its `UNIVERSAL_VAO` constant
  and its `com.gtnewhorizon.gtnhlib.asm` exclusion, `DisplayListManager` in
  `startsWith("com.gtnewhorizon.gtnhlib.")`, and `verifyDistributedJar` in a
  required entry. S8TNLib's blob audit also depends on the path
  ([`PROVENANCE.md`](PROVENANCE.md)).

## Runtime libraries

The jar declares no dependencies. Its classes reference these libraries,
which the host's runtime provides:

| Library | Used by | Notes |
|---|---|---|
| Minecraft 1.12.2, client | `TessellatorManager`: `Tessellator`, `BufferBuilder`, `VertexFormat`, `VertexFormatElement`. `PostProcessingBridge` and `DepthTextureProvider`: `EntityRenderer`, `DynamicTexture`, `Framebuffer`, `EntityLivingBase`. `ModelQuadFacing`: `EnumFacing` | MCP names, remapped with the mod jar |
| Cleanroom | `compat/Mods`: `com.cleanroommc.discovery.CleanroomModDiscoverer` | S8TNLib compiles against `0.6.12-alpha`, the minimum |
| LWJGL 3, core and OpenGL | `bytebuf`: `MemoryUtil`, `PointerBuffer`, `CustomBuffer`, `BufferUtils`, `Platform`, `Pointer`. `GLCaps`, `UniversalVAO`, `vao`, `vbo` and `vertex`: `GL11` to `GL44`, `EXTFramebufferObject` | Compiled against 3.4.1. `bytebuf/Pointer` avoids `Platform.Architecture.is64Bit()`, which LWJGL 3.3 lacks, because Cleanroom ships 3.3.x at runtime, as its comment says |
| lwjglx | `GLCaps`, `UniversalVAO`: `GLContext`, `ContextCapabilities` | Cleanroom's LWJGL 2 compatibility layer |
| JOML | `NormI8`, `VertexFormat` and the vertex writers: `Vector3f`, `Matrix4fc`, `Math` | 1.10.9, Cleanroom's copy |
| fastutil | `VBOManager`: `Int2ObjectMap`, `Int2ObjectOpenHashMap` | |
| log4j API | `UniversalVAO`: `LogManager`, `Logger` | |

Guava and Lombok are needed only to compile. Guava's `@Beta` has class
retention, and Lombok generates its code at compile time.

## What the host supplies

### Tessellator draws

Demonica's GLSM captures geometry while it compiles a display list: its
`DisplayListManager` starts a callback capture,
`TessellatorManager.startCapturingDirect(DirectDrawCallback)`, in `glNewList`
and stops it in `glEndList`. Vanilla code draws through a `BufferBuilder`
instead, so the host routes those draws into the capture.

- Demonica's `com.demonica.mixin.core.MixinTessellator` does it at the head
  of `Tessellator.draw()`, after `finishDrawing()`. Its
  `com.demonica.mixin.core.MixinWorldVertexBufferUploader` does it at the
  head of `WorldVertexBufferUploader.draw(BufferBuilder)`. Each calls
  `TessellatorManager.shouldInterceptBufferBuilderDraw()`, and when that
  returns true, passes the `BufferBuilder` to `interceptBufferBuilderDraw`
  and cancels the vanilla draw.
- `shouldInterceptBufferBuilderDraw()` is true while the calling thread has
  a capture and `RuntimeOptionsBridge` allows direct memory access.
  `interceptBufferBuilderDraw` copies the vertices into the capture's
  `DirectTessellator`, draws it and resets the `BufferBuilder`. It throws
  `IllegalStateException` when `shouldInterceptBufferBuilderDraw()` is
  false.
- Captures are per thread, so a capture never takes another thread's draws.
  A thread's first plain capture allocates its main native buffer, and its
  first callback capture a second one, `DEFAULT_BUFFER_SIZE` = 32 KiB each.
  S8TNLib frees them once the thread has ended and been collected. Up to
  0.1.1, both were allocated on the first capture and never freed.
- `MixinTessellator` also implements `ITessellatorInstance` on `Tessellator`.
  S8TNLib reads it only in `shouldInterceptDraw(Tessellator)`, which nothing
  calls. Neither S8TNLib nor Demonica calls `discard()` or sets the
  deprecated compiling flag.

### Fonts

Demonica's `com.demonica.mixin.fontrenderer.MixinFontRenderer` implements
`IFontParameters` on `FontRenderer`. Its six `demonica$` methods report the
glyph scale, spacing, whitespace scale, shadow offset and fine character
width of Demonica's batched font renderer. Nothing in S8TNLib calls them.

### Providers

`com.demonica.Demonica.onConstruct`, on FML's construction event, sets them
when Demonica's early mixins are active:

| Setter | Demonica passes | While unset |
|---|---|---|
| `RuntimeOptionsBridge.setAllowDirectMemoryAccess` | `DemonicaRuntimeOptions::allowDirectMemoryAccess` | `allowDirectMemoryAccess()` returns true |
| `PostProcessingBridge.setDepthTextureProvider` | `iris$getDepthTextureId()`, through Iris's `IRenderTargetExt` on `Framebuffer` | `getDepthTextureId` throws `UnsupportedOperationException` |
| `PostProcessingBridge.setLightmapColorAccessor` | `getLightmapColors()`, through `AccessorEntityRenderer` | `getLightmapColors` returns null |
| `PostProcessingBridge.setLightmapTextureAccessor` | `getLightmapTexture()`, through `AccessorEntityRenderer` | `getLightmapTexture` returns null |
| `PostProcessingBridge.setNightVisionBrightnessInvoker` | `invokeGetNightVisionBrightness`, through `AccessorEntityRenderer` | `getNightVisionBrightness` returns 0 |

`AccessorEntityRenderer` is Demonica's accessor mixin on `EntityRenderer`, in
`com.demonica.mixin.core.terrain`, and `IRenderTargetExt` is in Demonica's
Iris tree, in `net.coderbot.iris.rendertarget`. Nothing in S8TNLib reads the
`PostProcessingBridge` values: GTNHLib's post-processing pipeline did, and it
is dropped. Demonica's Iris tree reads `getLightmapTexture` and
`getNightVisionBrightness`.

### Mod presence

`compat/Mods` answers from `CleanroomModDiscoverer`, whose scan runs during
`CoreModManager#handleLaunch`, before any mod class loads. Its flags are
therefore safe to read from any mod code, mixins included. Its class javadoc
lists where the answer differs from `Loader#isModLoaded`. Demonica reads the
flags in 10 root and 2 `shader` files, and nothing in S8TNLib does.
