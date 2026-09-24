# S8TNLib Provenance

S8TNLib ports [GTNHLib](https://github.com/GTNewHorizons/GTNHLib) to Minecraft
1.12.2 on Cleanroom as a series of reviewable commits. Its base is upstream
`9644810c0041584a9c6b80855d64c5766b6d1e1e`, release `0.9.59` (tag
`gtnhlib-base/9644810c00`).

## Charter

- **Demonica is the only consumer.**
  [Demonica](https://github.com/ndellagrotte/Demonica) merges the S8TNLib jar
  into its mod jar and remaps the result.
- **The code comes from Actinium.** The `GTNHLib/` project of
  [Actinium](https://github.com/DHJComical/Actinium) is the only 1.12.2 port
  of GTNHLib. Actinium imported it in one commit, together with unrelated work
  (`actinium@83ea1b7c`), and kept changing it afterwards. S8TNLib replays that
  work theme by theme, and each commit names the Actinium commits it ports.
- **What Demonica doesn't reach is dropped.** S8TNLib keeps the 60 main files
  that Demonica's code reaches, Actinium's 2 GTNHLib tests, and upstream's
  `VertexFormatTest` if it passes unmodified. Everything else is dropped, one
  labeled commit per subsystem, and recorded in the [drop ledger](#drop-ledger).
- **The syncline is exact.** At the syncline, every kept main file is
  byte-identical to Demonica's copy and every kept test to its source. A
  [blob-SHA audit](#scopes-and-audits) proves it.

## Base and syncline

| | Commit | Main-source tree |
|---|---|---|
| Base: upstream, tag `gtnhlib-base/9644810c00` | `9644810c0041584a9c6b80855d64c5766b6d1e1e` | `957d7b98e3c89b10d76ab6961054faf3364812d3` (390 files) |
| Checkpoint: `actinium@4a19c959` | `4a19c95952cb9710211d29bec3440e752b6a2d03` | `e8032ad587e60b027eaac63781318dd245344976` (90) |
| Syncline: `demonica@61fa479d`, Demonica tag `s8tnlib-source/61fa479d` | `61fa479dcfd00e39b92bdeb84f03c5ec693f6a6f` | `2dcfcfc8e387eae6435b2fbf9606d30879259388` (90) |

Demonica forked from Actinium at `4a19c959`. The syncline's GTNHLib equals
Actinium's at that commit, except for two Demonica edits:

- `compat/Mods.java`, blob `47f3c8babea36dda267bdcda00edee5e60c0dd63`
  (Actinium's is `6138185af69e84d581f55c0e6a64f4d6027cca04`), from
  `demonica@1511a6bd4c8960578f3ef575449de289159a6c97`. It adds the
  `LITTLETILES` flag, and its javadoc says "a Demonica compat class".
- `util/font/IFontParameters.java`, blob
  `caf04d57d4312654ad1ab79ce6aa0af2d01efeb5` (Actinium's is
  `970ed03e716e221a614a6ebb3a773963e987cdb3`), from
  `demonica@3d0db9950bd52411f05b859400bf1b576e83b851`. It renames the
  `actinium$` method prefixes to `demonica$`.

The port first reaches Actinium's content, tagged
`actinium-checkpoint/4a19c959`. The two Demonica edits then land last, in a
commit of their own.

## What is kept

Demonica's code outside `GTNHLib/` (its root, `glsm` and `shader` projects)
names 24 GTNHLib classes. Following their imports and same-package references
reaches 59 classes, and `bytebuf/package-info` makes 60 files. Actinium's code
at `4a19c959` reaches the same 60.

| Against upstream | Kept | Dropped as unreachable |
|---|---|---|
| verbatim | 28 | 12 |
| adapted | 29 | 18 |
| new in Actinium: `RuntimeOptionsBridge`, `PostProcessingBridge`, `DepthTextureProvider` | 3 | 0 |
| total | 60 | 30 |

- Four kept files, `ModelQuadFlags`, `ColorABGR`, `ColorU8` and
  `cel/util/MathUtil`, are needed only because `ModelQuadView` and
  `ModelQuadViewMutable` import `ModelQuadFlags` for a javadoc link.
- The 30 unreachable files are referenced only by each other:
  - the post-processing pipeline (9): `CustomFramebuffer`,
    `I3DGeometryRenderer`, `PostProcessingHelper`, `PostProcessingManager`,
    `SharedDepthFramebuffer`, `shaders/{BloomShader,BloomTonemapShader,
    PostProcessingRenderer,UniversiumShader}`;
  - `client/renderer/shader/**` (3) and `client/renderer/textures/**` (4);
  - 7 `cel` classes: `ColorARGB`, `ColorMixer`, `ModelLine`,
    `ModelQuadOrientation`, `ModelQuadWinding`, `ModelTriangle`,
    `polyfill/Maps`;
  - stubs and leftovers (7): `GTNHLib`, `ClientProxy`, `core/GTNHLibCore`,
    `blockpos/BlockPos`, `stacks/Vector3dStack`, `vbo/IModelCustomExt`,
    `util/ObjectPooler`.
- `PostProcessingBridge` stays although its pipeline goes. Demonica's Iris tree
  reads the lightmap and night-vision values through it, and
  `com.demonica.Demonica` sets its providers.
- Two kept types serve only Demonica, and nothing inside GTNHLib uses them:
  `compat/Mods` (used by 10 root and 2 `shader` files) and
  `util/font/IFontParameters` (implemented by `MixinFontRenderer`).

## Path map

| Tree | Main sources | Tests |
|---|---|---|
| upstream GTNHLib | `src/main/java` (390 files), `src/main17/java` (4) | `src/test/java` (14) |
| Actinium, project `:GTNHLib` | `GTNHLib/src/main/java` (90) | `GTNHLib/src/test/java` (2) |
| Demonica since `3d0db995`, project `:GTNHLib` | `GTNHLib/src/main/java` (90) | none |
| S8TNLib | `src/main/java` | `src/test/java` |

Files keep their package, `com.gtnewhorizon.gtnhlib`, so a path below these
roots names the same file in every tree. Upstream's 50 resources have no
counterpart, since none of them is kept. Before `3d0db995`, Demonica kept
GTNHLib in `vendor/GTNHLib`.

## Scopes and audits

`scripts/provenance_audit.py` classifies every file in a scope by git blob SHA
against a baseline:

| Class | Meaning |
|---|---|
| **verbatim** | byte-identical to the baseline |
| **adapted** | in both trees, content differs |
| **new** | only in the compared tree |
| **dropped** | only in the baseline |

It knows where each scope lives in four layouts: `upstream` (the default
baseline), `actinium`, `demonica` (from `3d0db995` on) and `s8tnlib` (the
default compared tree, at `HEAD`). A file is keyed by its path below the
scope's root, so it matches across layouts.

| Scope | Files | Layouts |
|---|---|---|
| `gtnhlib` | the 60 kept main files, `KEPT` in the script | all four |
| `tests` | Actinium's `MemoryUtilitiesTest` and `TessellatorManagerTest` | `upstream`, `actinium`, `s8tnlib` |
| `upstream-tests` | upstream's `VertexFormatTest`, while it is kept | `upstream`, `s8tnlib` |
| `main` | every main source file, for the classification manifest | all four |

- `gtnhlib`, `tests` and `upstream-tests` restrict both sides to the same
  paths, so `--expect-identical` ignores whatever else a tree carries.
- Demonica never vendored GTNHLib's tests, so `tests` is audited against
  Actinium only.
- `--ledger` checks the [drop ledger](#drop-ledger), as committed at
  `--b-ref`, against that tree. Every upstream file under `src/` that the tree
  lacks must have exactly one row, and every row must name a base file that
  the tree lacks. So the `main` scope's dropped list equals the ledger. Every
  commit keeps this check passing.
- `--b-ref INDEX` audits staged files before they are committed.

```sh
ACT=4a19c95952cb9710211d29bec3440e752b6a2d03
DEM=61fa479dcfd00e39b92bdeb84f03c5ec693f6a6f

# Gate 1, at the Actinium checkpoint: the kept files and tests equal Actinium's.
scripts/provenance_audit.py --scope gtnhlib --a-ref $ACT --a-layout actinium --expect-identical
scripts/provenance_audit.py --scope tests --a-ref $ACT --a-layout actinium --expect-identical

# Gate 2, at the syncline: the kept files equal Demonica's.
scripts/provenance_audit.py --scope gtnhlib --a-ref $DEM --a-layout demonica --expect-identical

# VertexFormatTest is still upstream's.
scripts/provenance_audit.py --scope upstream-tests --expect-identical

# What S8TNLib changed relative to the base, and the ledger check, on staged
# files before a commit and on HEAD after it:
scripts/provenance_audit.py --scope main --b-ref INDEX --ledger
scripts/provenance_audit.py --scope main --ledger

# What Actinium changed relative to the base:
scripts/provenance_audit.py --scope main --b-ref $ACT --b-layout actinium
```

## Branches

| Branch | Role |
|---|---|
| `master` | mirror of upstream `master`; nothing is committed to it |
| `dev` | buildable states only; tracks `origin/dev` |
| `main` | blessed states: it only fast-forwards to a tagged release |
| `port/1.12.2-cleanroom` | the port series, merged to `dev` at the syncline |
| `sync/demonica-<sha>` | a later Demonica change to GTNHLib, until it merges |

## Remotes

| Remote | Repository | Use |
|---|---|---|
| `origin` | https://github.com/ndellagrotte/S8TNLib | S8TNLib itself |
| `upstream` | https://github.com/GTNewHorizons/GTNHLib | fetch only |
| `actinium` | https://github.com/DHJComical/Actinium, or a local clone | fetch only, no tags |
| `demonica` | https://github.com/ndellagrotte/Demonica, or a local clone | fetch only; of its tags, only `s8tnlib-source/*`, as `demonica/tags/s8tnlib-source/*` |

Audits and diffs read the other trees' objects locally, so the three read-only
remotes only need fetching, and their push URL is `DISABLED`. Actinium's and
Demonica's tags stay out of S8TNLib's tag namespace, because Demonica carries
Angelica's version tags, and those collide with upstream's (`0.0.1` is in
both).

```sh
git remote add upstream https://github.com/GTNewHorizons/GTNHLib.git
git remote add actinium https://github.com/DHJComical/Actinium.git
git remote add demonica https://github.com/ndellagrotte/Demonica.git
for r in upstream actinium demonica; do git remote set-url --push "$r" DISABLED; done
git config remote.actinium.tagOpt --no-tags
git config remote.demonica.tagOpt --no-tags
git config --add remote.demonica.fetch \
    'refs/tags/s8tnlib-source/*:refs/remotes/demonica/tags/s8tnlib-source/*'
git fetch --multiple upstream actinium demonica
```

## Tags

| Tag | Repository | Marks |
|---|---|---|
| `gtnhlib-base/9644810c00` | S8TNLib | the base |
| `s8tnlib-source/61fa479d` | Demonica | the syncline. It keeps `61fa479d`, `1511a6bd` and `3d0db995` reachable if Demonica's history is rewritten, and is pushed only with the maintainer's go-ahead |
| `actinium-checkpoint/4a19c959` | S8TNLib | the commit whose kept files and tests equal Actinium's |
| `demonica-syncline/61fa479d` | S8TNLib | the merge to `dev` whose kept files equal Demonica's |
| `v<version>` | S8TNLib | a release, such as `v0.1.0` |

Release tags start with `v` because upstream's version tags, `0.1.0` among
them, are already in this repository. The Maven coordinates are
`com.s8tnlib:s8tnlib`, at `0.1.0-SNAPSHOT` during the port and `0.1.0` at the
syncline.

## Commits

- Subjects start with `build:`, `port(<area>):`, `drop(<subsystem>):`,
  `docs:`, `tools:` or `ci:`. Bodies are bulleted.
- A mechanical commit calls out every hunk in it that is not mechanical.
- A commit that changes files the build does not compile yet says which
  commit first compiles them.
- Trailers record where the content came from:
  - `Ported-From: actinium@<full sha>` or `Ported-From: demonica@<full sha>`,
    one per source commit;
  - `Baseline: gtnhlib-base/9644810c00` where the change is measured against
    the base.

`git log --grep=Ported-From` lists the ported commits.

## Rules

- **Never rename `com.gtnewhorizon.gtnhlib`.** Blob identity with Actinium's
  and Demonica's copies depends on it. Demonica also matches the package name
  as a string: `GLSMRedirector` in its `UNIVERSAL_VAO` constant and its
  `com.gtnewhorizon.gtnhlib.asm` exclusion, `DisplayListManager` in
  `startsWith("com.gtnewhorizon.gtnhlib.")`, and `verifyDistributedJar` in its
  required entries.
- **Write kept files from git objects,** as in
  `git show <sha>:GTNHLib/src/main/java/<file> > src/main/java/<file>`, and
  never save one through an editor. `.editorconfig` trims trailing whitespace,
  and `ColorU8` has two lines of it.
- **No formatter in the build.** Formatting would break blob identity.
- **Read Demonica through refs, never through its working tree.** Other work
  happens in Demonica's checkout, which may be on any branch.
- **Port only what is kept.** A dropped file goes at its upstream content and
  is never ported, and code that Actinium later deleted never lands.

## Open: license

GTNHLib is LGPL-3.0 (`LICENSE.txt`). Actinium's `THIRD_PARTY_NOTICES.md` lists
its `GTNHLib/` as LGPL-3.0, and Demonica's notices label the same code
LGPL-3.0. But Actinium's repository `LICENSE` is GPL-3.0, and Demonica treats
its mod jar, which combines this code with Actinium's root project, as
GPL-3.0. Nothing states which of the two licenses covers Actinium's changes to
GTNHLib, and the port carries those changes into S8TNLib's adapted and new
files.

Pushing S8TNLib counts as distributing it. Before it is pushed, or published
beyond `mavenLocal`, S8TNLib must take Demonica's position or state the
conflict in its notices.

## Port frontier

Until the port is complete, `port-frontier.txt` lists every upstream file
under `src/main/java`, `src/main/resources` and `src/test/java` that is not
ported yet, one path per line. `build.gradle` leaves each out of its source
set, so the build compiles, tests and packages only what is ported, and
`git log -p port-frontier.txt` shows the progress.

- **Exit rule.** A file leaves the frontier only in a commit where it
  compiles, and a test only in one where it passes.
- **Only kept files leave.** A file that will be dropped stays on the frontier
  until its `drop(...)` commit, even if it compiles. A kept file that compiles
  may also wait, when a later commit would make it depend on a file still on
  the frontier.
- **Deletions.** A commit that deletes a file also removes its line: the build
  fails on a line that names a missing file. A deleted file may stay
  referenced only by files still on the frontier.
- **`src/main17`** is not a source set, so it has no lines. The `bytebuf` port
  folds its 4 files into `src/main/java` and removes it.
- **End.** `build: retire the port frontier` removes the list and its
  mechanism before gate 1.

## Drop ledger

Every upstream file that S8TNLib drops gets a row here, added by the commit
that removes it. The ledger is the last section of this file, so that commit
appends a heading with its subject, such as `### drop(mixins)`, and a table
with two columns:

- **File:** the path at the base, in backticks, such as
  `src/main/java/com/gtnewhorizon/gtnhlib/…`. A path ending in `/` stands for
  a directory dropped as a whole.
- **Reason:** one of the reasons below, which also says where to restore the
  file from. A short explanation may follow it.
  - **Not in Actinium's set:** `1.7.10-only`, or `native on 1.12.2: <what
    replaces it>`. Restore it from `gtnhlib-base/9644810c00`. This includes
    `api/CapturingTesselator`, which Actinium never imported.
  - **In Actinium's set, unreachable from Demonica:** `unreachable from
    Demonica`. Restore the 1.12.2 version from `actinium@4a19c959`, under
    `GTNHLib/`.
  - **Removed by Actinium:** `removed by actinium@<sha>`. These are the 9 files
    that Actinium's dead-code commits deleted (`b6c98b8b`, `1a65c496`,
    `303b9789`). Restore the last 1.12.2 version from that commit's parent.

`scripts/provenance_audit.py --ledger` checks the rows against the tree
([Scopes and audits](#scopes-and-audits)).

### drop(mixins)

| File | Reason |
|---|---|
| `src/main/java/com/gtnewhorizon/gtnhlib/mixins/` | 1.7.10-only: mixins into 1.7.10 vanilla, Forge and FML classes |
| `src/main/java/com/gtnewhorizon/gtnhlib/mixin/` | 1.7.10-only: the GTNHMixins builder DSL, deprecated upstream |
| `src/main/resources/mixins.gtnhlib.early.json` | 1.7.10-only: a mixin config for `mixins/` |
| `src/main/resources/mixins.gtnhlib.json` | 1.7.10-only: a mixin config for `mixins/` |
| `src/main/resources/mixins.gtnhlib.late.json` | 1.7.10-only: a mixin config for `mixins/` |

### drop(core)

| File | Reason |
|---|---|
| `src/main/java/com/gtnewhorizon/gtnhlib/core/GTNHLibCore.java` | unreachable from Demonica: the FML coremod upstream. Actinium cut it down to isObf() |
| `src/main/java/com/gtnewhorizon/gtnhlib/core/GTNHLibCoreModContainer.java` | 1.7.10-only: the coremod's FML mod container |
| `src/main/java/com/gtnewhorizon/gtnhlib/core/GTNHLibLateMixinLoader.java` | 1.7.10-only: the GTNHMixins loader of the late mixins |
| `src/main/java/com/gtnewhorizon/gtnhlib/core/fml/` | 1.7.10-only: FML class transformers, and a tweaker that registers them late |
| `src/main/java/com/gtnewhorizon/gtnhlib/core/rfb/` | 1.7.10-only: the RetroFuturaBootstrap plugin and its transformers |
| `src/main/java/com/gtnewhorizon/gtnhlib/core/shared/` | 1.7.10-only: the Tessellator transformers and the class dumper that the FML and RFB paths share |
| `src/main/java/com/gtnewhorizon/gtnhlib/asm/ASMUtil.java` | 1.7.10-only: an ASM helper of the class dumper in `core/shared/` |
| `src/main/java/com/gtnewhorizon/gtnhlib/asm/ByteCodeUtil.java` | 1.7.10-only: an ASM helper of the transformers in `core/fml/` |
| `src/main/java/com/gtnewhorizon/gtnhlib/asm/SafeClassWriter.java` | 1.7.10-only: an ASM helper of the transformers in `core/fml/` |
| `src/main/resources/META-INF/gtnhlib_at.cfg` | 1.7.10-only: an access transformer in 1.7.10 names |
| `src/main/resources/META-INF/rfb-plugin/gtnhlib.properties` | 1.7.10-only: registers the RetroFuturaBootstrap plugin in `core/rfb/` |

### drop(blockstate)

| File | Reason |
|---|---|
| `src/main/java/com/gtnewhorizon/gtnhlib/blockstate/` | native on 1.12.2: IBlockState and IProperty |
| `src/main/java/com/gtnewhorizon/gtnhlib/blocks/util/BFSLeafDecay.java` | 1.7.10-only: a leaf-decay search that nothing in GTNHLib uses |
| `src/main/java/com/gtnewhorizon/gtnhlib/test/` | 1.7.10-only: test blocks and a test item, registered only when GTNHLibConfig enables them |
| `src/main/resources/assets/gtnhlib/blockstates/` | 1.7.10-only: block states of the test blocks in `test/` |
| `src/main/resources/assets/gtnhlib/models/` | 1.7.10-only: models of the test blocks in `test/` |
| `src/main/resources/assets/gtnhlib/textures/blocks/test.png` | 1.7.10-only: the texture of the test blocks in `test/` |
| `src/main/resources/assets/minecraft/` | native on 1.12.2: the vanilla client jar ships all four of these block models, and these copies would shadow them |
| `models.md` | native on 1.12.2: vanilla JSON models. It documents GTNHLib's backport of them |

### drop(models)

| File | Reason |
|---|---|
| `src/main/java/com/gtnewhorizon/gtnhlib/client/model/BakeData.java` | native on 1.12.2: JSON block and item models |
| `src/main/java/com/gtnewhorizon/gtnhlib/client/model/BakedModelBuilder.java` | native on 1.12.2: JSON block and item models |
| `src/main/java/com/gtnewhorizon/gtnhlib/client/model/BakedModelQuadContext.java` | native on 1.12.2: JSON block and item models |
| `src/main/java/com/gtnewhorizon/gtnhlib/client/model/ItemContext.java` | native on 1.12.2: JSON block and item models |
| `src/main/java/com/gtnewhorizon/gtnhlib/client/model/JSONVariant.java` | native on 1.12.2: JSON block and item models |
| `src/main/java/com/gtnewhorizon/gtnhlib/client/model/ModelISBRH.java` | native on 1.12.2: JSON block and item models |
| `src/main/java/com/gtnewhorizon/gtnhlib/client/model/Weighted.java` | native on 1.12.2: JSON block and item models |
| `src/main/java/com/gtnewhorizon/gtnhlib/client/model/WorldContext.java` | native on 1.12.2: JSON block and item models |
| `src/main/java/com/gtnewhorizon/gtnhlib/client/model/baked/` | native on 1.12.2: JSON block and item models |
| `src/main/java/com/gtnewhorizon/gtnhlib/client/model/color/` | native on 1.12.2: IBlockColor and BlockColors |
| `src/main/java/com/gtnewhorizon/gtnhlib/client/model/loading/` | native on 1.12.2: JSON block and item models |
| `src/main/java/com/gtnewhorizon/gtnhlib/client/model/state/` | native on 1.12.2: JSON block and item models |
| `src/main/java/com/gtnewhorizon/gtnhlib/client/model/unbaked/` | native on 1.12.2: JSON block and item models |
| `src/main/java/com/gtnewhorizon/gtnhlib/itemrendering/` | native on 1.12.2: layered JSON item models. It is built on Forge's IItemRenderer, which 1.12.2 doesn't have |
| `src/main/java/com/gtnewhorizon/gtnhlib/util/ItemRenderUtil.java` | native on 1.12.2: layered JSON item models. It is built on Forge's IItemRenderer, which 1.12.2 doesn't have |
| `src/main/java/com/gtnewhorizon/gtnhlib/client/renderer/vbo/IModelCustomExt.java` | unreachable from Demonica: the VBO interface that an upstream mixin adds to Forge's WavefrontObject |

### drop(config)

| File | Reason |
|---|---|
| `src/main/java/com/gtnewhorizon/gtnhlib/config/` | native on 1.12.2: Forge's @Config and ConfigManager |
| `src/main/java/com/gtnewhorizon/gtnhlib/ExampleConfig.java` | native on 1.12.2: Forge's @Config and ConfigManager. An example of `config/` |
| `src/main/java/com/gtnewhorizon/gtnhlib/eventhandlers/ConfigEventHandler.java` | native on 1.12.2: Forge's @Config and ConfigManager. It reloads settings on ConfigChangedEvent |
| `src/main/java/com/gtnewhorizon/gtnhlib/GTNHLibConfig.java` | 1.7.10-only: settings of features that the port drops |
| `src/main/java/com/gtnewhorizon/gtnhlib/util/numberformatting/` | 1.7.10-only: number formatting, set through GTNHLib's @Config. Nothing kept uses it |
| `src/main/resources/assets/gtnhlib/lang/` | 1.7.10-only: translations for dropped features. Of en_US's 123 keys, 83 are villager names, 28 chat-component strings, 5 /title strings, 4 config GUI strings and 3 test-block names |
| `src/test/java/com/gtnewhorizon/gtnhlib/config/ConfigurationManagerTest.java` | native on 1.12.2: Forge's @Config and ConfigManager. It tests `config/` |
| `src/test/java/com/gtnewhorizon/gtnhlib/test/util/ExponentialFormatTest.java` | 1.7.10-only: tests `util/numberformatting/` |
| `src/test/java/com/gtnewhorizon/gtnhlib/test/util/NumberFormatUtilTest.java` | 1.7.10-only: tests `util/numberformatting/` |

### drop(game-api)

| File | Reason |
|---|---|
| `src/main/java/com/gtnewhorizon/gtnhlib/eventbus/` | native on 1.12.2: Forge's @Mod.EventBusSubscriber |
| `src/main/java/com/gtnewhorizon/gtnhlib/event/PickBlockEvent.java` | 1.7.10-only: posted by a dropped mixin. 1.12.2 has no counterpart |
| `src/main/java/com/gtnewhorizon/gtnhlib/client/event/LivingEquipmentChangeEvent.java` | native on 1.12.2: Forge's LivingEquipmentChangeEvent |
| `src/main/java/com/gtnewhorizon/gtnhlib/client/event/RenderTooltipEvent.java` | native on 1.12.2: Forge's RenderTooltipEvent |
| `src/main/java/com/gtnewhorizon/gtnhlib/client/event/WorldDeletionEvent.java` | 1.7.10-only: posted by a dropped mixin. 1.12.2 has no counterpart |
| `src/main/java/com/gtnewhorizon/gtnhlib/network/` | 1.7.10-only: GTNHLib's network channel and its packets |
| `src/main/java/com/gtnewhorizon/gtnhlib/commands/CommandResourcePack.java` | 1.7.10-only: the client command of the resource-pack updater |
| `src/main/java/com/gtnewhorizon/gtnhlib/commands/GTNHClientCommand.java` | 1.7.10-only: the base class of GTNHLib's client commands |
| `src/main/java/com/gtnewhorizon/gtnhlib/commands/ItemInHandCommand.java` | 1.7.10-only: the /iteminhand client command |
| `src/main/java/com/gtnewhorizon/gtnhlib/commands/TitleCommand.java` | native on 1.12.2: vanilla /title |
| `src/main/java/com/gtnewhorizon/gtnhlib/brigadier/BrigadierApi.java` | 1.7.10-only: 1.12.2 has no Brigadier |
| `src/main/java/com/gtnewhorizon/gtnhlib/chat/` | 1.7.10-only: custom chat components |
| `src/main/java/com/gtnewhorizon/gtnhlib/keybind/` | 1.7.10-only: server-synced keybindings |
| `src/main/java/com/gtnewhorizon/gtnhlib/gamerules/` | 1.7.10-only: a game-rule registry |
| `src/main/java/com/gtnewhorizon/gtnhlib/client/title/TitleAPI.java` | native on 1.12.2: vanilla titles, through GuiIngame.displayTitle and SPacketTitle |
| `src/main/java/com/gtnewhorizon/gtnhlib/client/VillagerNames.java` | 1.7.10-only: villager profession names, moved from WAWLA |

### drop(items)

| File | Reason |
|---|---|
| `src/main/java/com/gtnewhorizon/gtnhlib/item/` | native on 1.12.2: Forge capabilities and IItemHandler |
| `src/main/java/com/gtnewhorizon/gtnhlib/capability/` | native on 1.12.2: Forge capabilities and IItemHandler |
| `src/main/java/com/gtnewhorizon/gtnhlib/util/data/BlockMeta.java` | 1.7.10-only: block and item value types built on 1.7.10 metadata |
| `src/main/java/com/gtnewhorizon/gtnhlib/util/data/BlockSupplier.java` | 1.7.10-only: block and item value types built on 1.7.10 metadata |
| `src/main/java/com/gtnewhorizon/gtnhlib/util/data/ImmutableBlockMeta.java` | 1.7.10-only: block and item value types built on 1.7.10 metadata |
| `src/main/java/com/gtnewhorizon/gtnhlib/util/data/ImmutableItemMeta.java` | 1.7.10-only: block and item value types built on 1.7.10 metadata |
| `src/main/java/com/gtnewhorizon/gtnhlib/util/data/ItemId.java` | 1.7.10-only: block and item value types built on 1.7.10 metadata |
| `src/main/java/com/gtnewhorizon/gtnhlib/util/data/ItemMeta.java` | 1.7.10-only: block and item value types built on 1.7.10 metadata |
| `src/main/java/com/gtnewhorizon/gtnhlib/util/data/ItemStackSupplier.java` | 1.7.10-only: block and item value types built on 1.7.10 metadata |
| `src/main/java/com/gtnewhorizon/gtnhlib/util/data/ItemSupplier.java` | 1.7.10-only: block and item value types built on 1.7.10 metadata |
| `src/main/java/com/gtnewhorizon/gtnhlib/util/data/LazyBlock.java` | 1.7.10-only: block and item value types built on 1.7.10 metadata |
| `src/main/java/com/gtnewhorizon/gtnhlib/util/data/LazyItem.java` | 1.7.10-only: block and item value types built on 1.7.10 metadata |
| `src/main/java/com/gtnewhorizon/gtnhlib/util/data/IMod.java` | 1.7.10-only: an interface for mod enums, used by LazyBlock and LazyItem |
| `src/main/java/com/gtnewhorizon/gtnhlib/util/map/ItemStackMap.java` | 1.7.10-only: an ItemStack-keyed map, adapted from NotEnoughItems |
| `src/main/java/com/gtnewhorizon/gtnhlib/util/ItemUtil.java` | 1.7.10-only: item-transfer helpers for `item/` |
