# S8TNLib

S8TNLib is [GTNHLib](https://github.com/GTNewHorizons/GTNHLib) ported to
Minecraft 1.12.2 on Cleanroom, for
[Demonica](https://github.com/ndellagrotte/Demonica).

- **The code comes from Actinium.** The `GTNHLib/` project of
  [Actinium](https://github.com/DHJComical/Actinium) is the only 1.12.2 port
  of GTNHLib. S8TNLib replays that work on upstream's history as a series of
  reviewable commits, and its port commits name the Actinium commits they
  take.
- **It keeps what Demonica uses.** That is 60 source files: the renderer's
  direct-capture tessellator, vertex formats, VAO and VBO helpers and quad
  model types, the `bytebuf` memory utilities, and two types that only
  Demonica uses. Every other upstream file is dropped, with its reason
  recorded.
- **It equals Demonica's copy.** In 0.1.0, every kept file is byte-identical
  to Demonica's `GTNHLib/` at `demonica@61fa479d`, and a blob-SHA audit
  proves it.

[`docs/PROVENANCE.md`](docs/PROVENANCE.md) records where each file comes
from, what was dropped and why, and how the audit works.

## The artifact

`com.s8tnlib:s8tnlib:0.1.0`: the jar and its sources jar, published to
`mavenLocal` only.

- It is an MCP-named library, not a mod: `com.gtnewhorizon.gtnhlib` classes
  only, with no mod metadata, mixins, access transformer or dependencies.
- The host merges it into its mod jar and remaps that. It never goes on the
  app class path, and its package is never relocated.

[`docs/HOST_CONTRACT.md`](docs/HOST_CONTRACT.md) lists what the host
supplies: the Minecraft-side hooks, the providers and the runtime libraries.

## Building

```sh
./gradlew build                  # compiles, runs the tests, and builds and verifies the jars
./gradlew publishToMavenLocal    # publishes com.s8tnlib:s8tnlib to mavenLocal
```

The build runs on JDK 25, which Gradle provisions if it is missing, and
compiles for Java 21. It needs git: each jar's manifest records the commit it
was built from.

## License

GTNHLib is LGPL-3.0 ([`LICENSE.txt`](LICENSE.txt)). Whether LGPL-3.0 or
GPL-3.0 covers Actinium's changes is an open question, which
[`THIRD_PARTY_NOTICES.md`](THIRD_PARTY_NOTICES.md) explains. Until it is
settled, S8TNLib is published to `mavenLocal` only.
