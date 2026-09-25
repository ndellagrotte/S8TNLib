# Host contract

S8TNLib is a mod that players install, but it registers nothing and hooks
nothing by itself. A host mod on Minecraft 1.12.2 and Cleanroom requires it,
supplies the Minecraft-side hooks and the providers, and runs it on the
libraries that Minecraft and Cleanroom ship. Demonica is the only host. This
page lists what the host supplies as of 0.3.0, and how Demonica does it.

## The artifact

A `v<version>` tag's CI run creates the GitHub release with three assets:
`s8tnlib-<version>.jar`, `s8tnlib-<version>-sources.jar` and `SHA256SUMS`.
`./gradlew publishToMavenLocal` publishes the same jars as
`com.s8tnlib:s8tnlib` to `mavenLocal`, which nothing depends on.

- **The jar** holds the classes of the 47 kept files, all in
  `com.gtnewhorizon.gtnhlib`; S8TNLib's own `com.s8tnlib.S8TNLib`, an
  `@Mod` with mod id `s8tnlib`, and `com.s8tnlib.S8TNLibLoadingPlugin`;
  `mcmod.info`; `LICENSE` and `THIRD_PARTY_NOTICES.md`; and a manifest. It
  has no mixin configs, access transformer or nested jars.
- **It is remapped to SRG,** the names Minecraft has in production. A dev
  environment remaps it back to MCP, as it does any mod jar (Demonica's
  build declares it `modCompileOnly` and `modRuntimeOnly`).
- **The sources jar** holds the source files, `mcmod.info` and the same
  license files.
- **The POM and the Gradle module metadata** declare no dependencies.
- **The jar's manifest** names `FMLCorePlugin: com.s8tnlib.S8TNLibLoadingPlugin`
  and `FMLCorePluginContainsFMLMod: true`. The plugin registers no
  transformer and excludes no package, so a host's transformers still see
  the library's classes.
- **Both manifests** say where the jar comes from:
  - `S8TNLib-Base`: the upstream GTNHLib commit, `gtnhlib@9644810c…`;
  - `S8TNLib-Synced-From`: the Demonica commit S8TNLib last synced from,
    `demonica@61fa479d…`. The kept files equal it up to 0.1.1;
  - `S8TNLib-Commit`: the S8TNLib commit the jar was built from, with
    `-dirty` if tracked files had uncommitted changes. A host can pin it.

`verifyPublishedJars` checks all of this, and that no `bytebuf` entry is
left, in `./gradlew build` and before every publication.

## Rules

- **The jar sits in `mods/`, as a coremod-flagged mod.** Cleanroom 0.6.12
  adds a mods-folder jar to its `LaunchClassLoader` while coremods load only
  if its manifest names an `FMLCorePlugin` (or a `TweakClass` or
  `MixinConfigs`). The host's coremod code needs S8TNLib's classes then:
  Demonica's `GLSMRedirector` holds a `ClassConstantPoolParser`, its
  `MixinTessellator` implements `ITessellatorInstance`, and its late
  tweaker builds the redirector. Cleanroom adds every coremod jar before any
  tweaker runs, so S8TNLib may sort before or after the host. The host's
  loading plugin must not touch S8TNLib's classes while it is constructed,
  and it declares the dependency (`required-after:s8tnlib`), so that a
  missing S8TNLib is FML's missing-mods screen, not a crash.
- **Never on the app class path.** Classes there are loaded by the app class
  loader while the code that calls them runs in Cleanroom's
  `LaunchClassLoader`, which fails with a `LinkageError`, a loader
  constraint violation. Demonica's `docs/celeritas/SPIKE.md` ("Dev class
  loading") records that failure for GLSM. Demonica's dev runs pass the jar
  to Cleanroom through `-Dcrl.dev.extrapath`, as they do Celeritas.
- **Never relocate `com.gtnewhorizon.gtnhlib`.** Demonica matches the
  package name as a string: `GLSMRedirector` in its `UNIVERSAL_VAO` constant
  and its `com.gtnewhorizon.gtnhlib.asm` exclusion, `DisplayListManager` in
  `startsWith("com.gtnewhorizon.gtnhlib.")`, its `Environment` in the
  resource it looks for to tell whether S8TNLib is installed, and
  `verifyDistributedJar`, which keeps the package out of Demonica's own jar.
  S8TNLib's blob audit also depends on the path
  ([`PROVENANCE.md`](PROVENANCE.md)).

## Runtime libraries

The jar declares no dependencies. Its classes reference these libraries,
which Minecraft and Cleanroom provide at runtime:

| Library | Used by | Notes |
|---|---|---|
| Minecraft 1.12.2, client | `TessellatorManager`: `Tessellator`, `BufferBuilder`, `VertexFormat`, `VertexFormatElement`. `PostProcessingBridge` and `DepthTextureProvider`: `EntityRenderer`, `DynamicTexture`, `Framebuffer`, `EntityLivingBase`. `ModelQuadFacing`: `EnumFacing` | SRG names in the jar |
| LWJGL 3, core and OpenGL | `DirectTessellator`, `TessellatorManager`, `IndexBuffer`, `VertexFormat` and the vertex writers: `MemoryUtil`. `GLCaps`, `UniversalVAO`, `vao`, `vbo` and `vertex`: `GL11` to `GL44`, `EXTFramebufferObject` | 3.4.1, which Cleanroom 0.6.12 ships |
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

### Moved to Demonica

Two types that only Demonica used lived in S8TNLib up to 0.1.1, and are
Demonica's own since 0.2.0, with only their package lines changed:

- `util/font/IFontParameters`, the interface that Demonica's
  `MixinFontRenderer` implements on `FontRenderer`, is
  `com.demonica.render.font.IFontParameters` (`demonica@b93f3130`).
- `compat/Mods`, the mod-presence flags answered from Cleanroom's
  `CleanroomModDiscoverer`, is `com.demonica.compat.Mods` in Demonica's
  `:shader` project (`demonica@55c51cdf`).

Nothing in S8TNLib referenced either, and no class of S8TNLib's uses
Cleanroom's own classes any more.
