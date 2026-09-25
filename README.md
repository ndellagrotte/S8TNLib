# S8TNLib

S8TNLib is [GTNHLib](https://github.com/GTNewHorizons/GTNHLib) ported to
Minecraft 1.12.2 on Cleanroom, for
[Demonica](https://github.com/ndellagrotte/Demonica). It is a mod that
players install: Demonica requires it.

- **The code comes from Actinium.** The `GTNHLib/` project of
  [Actinium](https://github.com/DHJComical/Actinium) is the only 1.12.2 port
  of GTNHLib. S8TNLib replays that work on upstream's history as a series of
  reviewable commits, and its port commits name the Actinium commits they
  take.
- **It keeps what Demonica uses.** That is 47 source files: the renderer's
  direct-capture tessellator, vertex formats, VAO and VBO helpers and quad
  model types. Every other upstream file is dropped, with its reason
  recorded. Two types that only Demonica used moved to Demonica in 0.2.0, and
  0.3.0 dropped the `bytebuf` memory utilities for LWJGL's own
  `org.lwjgl.system`.
- **It equaled Demonica's copy up to 0.1.1.** In `v0.1.0` and `v0.1.1`, every
  kept file is byte-identical to Demonica's `GTNHLib/` at
  `demonica@61fa479d`, and a blob-SHA audit proves it. Demonica has had no
  `GTNHLib/` since, so from 0.2.0 on S8TNLib changes on its own.

[`docs/PROVENANCE.md`](docs/PROVENANCE.md) records where each file comes
from, what was dropped and why, and how the audit works.

**Requires:**

- Minecraft 1.12.2 with Cleanroom 0.6.12, on Java 21 or later.
- The client only. A server needs no S8TNLib, and accepts clients with any
  version of it.
- Demonica 0.3.0 or later, which needs S8TNLib 0.3.0 or later. S8TNLib does
  nothing without it.

## Installing

Download `s8tnlib-<version>.jar` from the
[GitHub releases](https://github.com/ndellagrotte/S8TNLib/releases) and put
it in `mods/`, next to Celeritas and Demonica.

- Demonica names the versions it accepts: Demonica 0.3.0 declares
  `required-after:s8tnlib@[0.3.0,)`. Without S8TNLib, FML shows its
  missing-mods screen.
- Install 0.3.0 or later. Earlier releases are libraries that Demonica
  merged into its own jar, not mods.
- Each release's `SHA256SUMS` checks the download:
  `sha256sum -c --ignore-missing SHA256SUMS`.

## The artifact

Each GitHub release has three assets, which CI builds from the release tag:
`s8tnlib-<version>.jar`, its sources jar and `SHA256SUMS`. Demonica's build
downloads the jar from there and pins its SHA-256.

- The jar is a mod, remapped to SRG: the `com.gtnewhorizon.gtnhlib` classes,
  a mod (`s8tnlib`) and a loading plugin that do nothing, `mcmod.info`, and
  the license files. Its manifest makes it a coremod that also contains a
  mod, so Cleanroom loads it early enough for Demonica's coremod code.
- It never goes on the app class path, and its package is never relocated.

[`docs/HOST_CONTRACT.md`](docs/HOST_CONTRACT.md) lists what the host
supplies: the Minecraft-side hooks, the providers and the runtime libraries.

## Building

```sh
./gradlew build                  # compiles, runs the tests, and builds, remaps and verifies the jars
./gradlew publishToMavenLocal    # publishes com.s8tnlib:s8tnlib to mavenLocal, for local experiments
```

`build/libs/s8tnlib-<version>.jar` is the mod jar, already remapped to SRG:
it installs as a release's jar does.

To build Demonica against a local build, pass it
`-Ps8tnlibDir=<S8TNLib>/build/libs`. Demonica looks for the version in its
own `s8tnlib_version`, so a build of `dev` also needs
`-Ps8tnlib_version=<version>` (such as `0.4.0-SNAPSHOT`). With a local jar,
Demonica's build reports its SHA-256 instead of checking it against the pin.

The build runs on JDK 25, which Gradle provisions if it is missing, and
compiles for Java 21. It needs:

- git: each jar's manifest records the commit it was built from;
- the network on the first build: Unimined downloads Minecraft 1.12.2, the
  MCP mappings and Cleanroom to compile against and to remap the jar;
- Windows, Linux or macOS: the tests load LWJGL's natives, and the build
  stops on a platform that has none.

Pushing an annotated `v<version>` tag makes CI build the jars and draft the
GitHub release, with the three assets and the tag's message as its notes.
Publishing the draft is the maintainer's step.

## License

S8TNLib is distributed under GPL-3.0 ([`LICENSE`](LICENSE)). GTNHLib's code
is LGPL-3.0 ([`LICENSE-LGPL-3.0.txt`](LICENSE-LGPL-3.0.txt)), which may be
conveyed under GPL-3.0. GPL-3.0 also covers Actinium's changes, whichever of the two
licenses they carry. The ASM-derived file keeps its BSD notice.
[`THIRD_PARTY_NOTICES.md`](THIRD_PARTY_NOTICES.md) has the details.
