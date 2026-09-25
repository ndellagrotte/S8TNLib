# Third-Party Notices

S8TNLib is made from other projects' source. This page lists each one, where
it lives in this repository, its terms, and whether it ships in the jar.
Where a source file carries its own copyright or license header, that header
governs the file. This is an inventory, not legal advice.

## Source

| Component | Upstream | Paths in S8TNLib | Terms | In the jar |
|---|---|---|---|---|
| GTNHLib | https://github.com/GTNewHorizons/GTNHLib at `9644810c` (release `0.9.59`, tag `gtnhlib-base/9644810c00`) | `src/main/java`: the 57 kept files that upstream has, 27 of them verbatim. `src/test/java`: `VertexFormatTest` | LGPL-3.0 ([`LICENSE-LGPL-3.0.txt`](LICENSE-LGPL-3.0.txt)), plus the file-level notices below | yes, except the test |
| Actinium's port of GTNHLib | https://github.com/DHJComical/Actinium, its `GTNHLib/` up to `4a19c959` | its changes to the 29 adapted files; the 3 files it added, `RuntimeOptionsBridge`, `PostProcessingBridge` and `DepthTextureProvider`; its tests `MemoryUtilitiesTest` and `TessellatorManagerTest` | LGPL-3.0 or GPL-3.0 (unstated). S8TNLib conveys it under GPL-3.0 (see [S8TNLib's license](#s8tnlibs-license)) | yes, except the tests |
| Demonica's edits | https://github.com/ndellagrotte/Demonica, its `GTNHLib/` at `61fa479d` | its changes to `compat/Mods` and `util/font/IFontParameters` | LGPL-3.0, as Demonica's `THIRD_PARTY_NOTICES.md` labels its `GTNHLib/` | yes |
| LWJGL | https://github.com/LWJGL/lwjgl3 | 9 of the 11 files in `bytebuf/`: `APIUtil`, `CheckIntrinsics`, `Checks`, `MemoryManage`, `MemoryStack`, `MemoryUtilities`, `MultiReleaseMemCopy`, `Pointer` and `StackWalkUtil` | LWJGL's BSD-style license, which their headers link: https://www.lwjgl.org/license | yes |
| ASM | https://asm.ow2.io | `asm/ClassConstantPoolParser`, derived from ASM's `ClassReader` | the three-clause BSD license in its header, Copyright (c) 2000-2011 INRIA, France Telecom | yes |

The Gradle build and `scripts/provenance_audit.py` are adapted from
Demonica's. They are not in the jar.

## S8TNLib's license

GTNHLib is LGPL-3.0. Actinium's `THIRD_PARTY_NOTICES.md` lists its
`GTNHLib/` as LGPL-3.0, and Demonica's notices label the same code LGPL-3.0.
But Actinium's repository `LICENSE` is GPL-3.0, and Demonica treats its mod
jar, which combines this code with Actinium's root project, as a GPL-3.0
combined work. Nothing states which of the two licenses covers Actinium's
changes to GTNHLib, and S8TNLib's adapted and new files carry those changes.

On 2026-09-24 the maintainer chose GPL-3.0 for S8TNLib as a whole
([`LICENSE`](LICENSE)). It holds under either reading: LGPL-3.0 is GPL-3.0
plus additional permissions, which section 7 of GPL-3.0 lets a redistributor
remove. File headers still govern their files: GTNHLib's LGPL-3.0 text
stays, as `LICENSE-LGPL-3.0.txt`, and the LWJGL and ASM files keep their BSD
notices
([`docs/PROVENANCE.md`](docs/PROVENANCE.md#license)).

## Not in the jar

- **License texts.** The jar carries no license or notice file: the host
  merges it into its mod jar, where root files would collide with the host's
  own. A distribution that contains S8TNLib's classes must carry the GPL-3.0
  text ([`LICENSE`](LICENSE)) and the LWJGL and ASM notices, whose BSD terms
  ask binary copies to reproduce them.
- **Compile-only libraries,** resolved at build time and never bundled:
  Minecraft 1.12.2 and Cleanroom `0.6.12-alpha`, with the libraries they
  bring; LWJGL 3.4.1 and lwjglx 1.0.0; JOML 1.10.9; fastutil 8.5.18; Lombok
  1.18.46; the JetBrains annotations 24.1.0. Each is governed by its own
  license. [`docs/HOST_CONTRACT.md`](docs/HOST_CONTRACT.md) lists what the
  jar needs at runtime.
- **Test libraries:** JUnit Jupiter 6.0.3, with LWJGL, its natives, JOML and
  fastutil.
