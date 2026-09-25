# Third-Party Notices

S8TNLib is made from other projects' source. This page lists each one, where
it lives in this repository, its terms, and whether it ships in the jar.
Where a source file carries its own copyright or license header, that header
governs the file. This is an inventory, not legal advice.

## Source

| Component | Upstream | Paths in S8TNLib | Terms | In the jar |
|---|---|---|---|---|
| GTNHLib | https://github.com/GTNewHorizons/GTNHLib at `9644810c` (release `0.9.59`, tag `gtnhlib-base/9644810c00`) | `src/main/java`: the 44 kept files that upstream has, 23 of them verbatim. `src/test/java`: `VertexFormatTest`, whose import S8TNLib changed | LGPL-3.0 ([`LICENSE-LGPL-3.0.txt`](LICENSE-LGPL-3.0.txt)), plus the file-level notices below | yes, except the test |
| Actinium's port of GTNHLib | https://github.com/DHJComical/Actinium, its `GTNHLib/` up to `4a19c959` | its changes to the adapted files; the 3 files it added, `RuntimeOptionsBridge`, `PostProcessingBridge` and `DepthTextureProvider`; its test `TessellatorManagerTest` | LGPL-3.0 or GPL-3.0 (unstated). S8TNLib conveys it under GPL-3.0 (see [S8TNLib's license](#s8tnlibs-license)) | yes, except the tests |
| S8TNLib's own changes | this repository, since `v0.1.1` ([`docs/PROVENANCE.md`](docs/PROVENANCE.md#after-the-syncline)) | `client/renderer/TessellatorManager`; the `MemoryUtil` imports of 10 files and `VertexFormatTest`; `com/s8tnlib/`, `mcmod.info`; the test `TessellatorManagerBuffersTest` | GPL-3.0 ([`LICENSE`](LICENSE)) | yes, except the tests |
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
stays, as `LICENSE-LGPL-3.0.txt`, and the ASM file keeps its BSD notice
([`docs/PROVENANCE.md`](docs/PROVENANCE.md#license)).

## In the jar

The jar is distributed on its own, as a mod, so it carries the GPL-3.0 text
(`LICENSE`) and this file, which reproduces the ASM notice that its BSD
terms ask binary copies to carry ([Notices](#notices)). So does the sources
jar.

## Not in the jar

- **Compile-only libraries,** resolved at build time and never bundled:
  Minecraft 1.12.2 and Cleanroom `0.6.12-alpha`, with the libraries they
  bring; LWJGL 3.4.1 and lwjglx 1.0.0; JOML 1.10.9; fastutil 8.5.18; Lombok
  1.18.46; the JetBrains annotations 24.1.0. Each is governed by its own
  license. [`docs/HOST_CONTRACT.md`](docs/HOST_CONTRACT.md) lists what the
  jar needs at runtime.
- **Test libraries:** JUnit Jupiter 6.0.3, with LWJGL, its natives, JOML and
  fastutil.

## Notices

### ASM (BSD 3-Clause)

The header of `src/main/java/com/gtnewhorizon/gtnhlib/asm/ClassConstantPoolParser.java`,
which is derived from ASM's `ClassReader`:

```text
ASM: a very small and fast Java bytecode manipulation framework Copyright (c)
2000-2011 INRIA, France Telecom All rights reserved.

Redistribution and use in source and binary forms, with or without
modification, are permitted provided that the following conditions are met: 1.
Redistributions of source code must retain the above copyright notice, this
list of conditions and the following disclaimer. 2. Redistributions in binary
form must reproduce the above copyright notice, this list of conditions and
the following disclaimer in the documentation and/or other materials provided
with the distribution. 3. Neither the name of the copyright holders nor the
names of its contributors may be used to endorse or promote products derived
from this software without specific prior written permission.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
```
