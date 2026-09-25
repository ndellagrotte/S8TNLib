#!/usr/bin/env python3
"""Provenance audit for S8TNLib.

Classifies every file in a scope against a baseline ref as:
  verbatim  - byte-identical (same git blob SHA) to the baseline
  adapted   - exists in both, content differs
  new       - only exists in the compared ref
  dropped   - only exists in the baseline

A scope is a list of source roots, each with optional include/exclude paths.
Files are keyed by their path relative to their source root, so the same
class matches across trees that put it under different prefixes (upstream's
src/main/java vs Actinium's and Demonica's GTNHLib/src/main/java). Two roots
of one scope yielding the same key is an error.

A layout says where each scope lives in a given tree:
  upstream  - upstream GTNHLib, the baseline
  actinium  - Actinium's multi-project build
  demonica  - Demonica's mod build from 3d0db995 on (before it: vendor/GTNHLib)
  s8tnlib   - this repository

The scopes gtnhlib, tests and upstream-tests restrict both sides to the same
paths, so --expect-identical ignores whatever else a tree carries. The scope
main covers every main source file, for the classification manifest.

The pseudo-ref INDEX reads the git index (`git ls-files -s`), so staged files
can be audited before they are committed.

--ledger checks the drop ledger in docs/PROVENANCE.md, as committed at
--b-ref, against the tree at --b-ref: every baseline file under src/ that the
tree lacks has exactly one row, and every row names a baseline file that the
tree lacks. It needs the upstream and s8tnlib layouts.

The manifest goes to stdout unless --expect-identical, --ledger or --write is
given.

Examples:
  # The kept files equal Actinium's at the checkpoint tag, then Demonica's
  # at the syncline's release tag:
  scripts/provenance_audit.py --scope gtnhlib \\
      --a-ref 4a19c95952cb9710211d29bec3440e752b6a2d03 --a-layout actinium \\
      --b-ref actinium-checkpoint/4a19c959 --expect-identical
  scripts/provenance_audit.py --scope gtnhlib \\
      --a-ref 61fa479dcfd00e39b92bdeb84f03c5ec693f6a6f --a-layout demonica \\
      --b-ref v0.1.1 --expect-identical

  # What S8TNLib changed since the syncline:
  scripts/provenance_audit.py --scope main --a-ref v0.1.1 --a-layout s8tnlib

  # What S8TNLib changed relative to the base, and whether the ledger
  # accounts for every dropped file, against staged files:
  scripts/provenance_audit.py --scope main --b-ref INDEX --ledger

  # What Actinium changed relative to the base:
  scripts/provenance_audit.py --scope main \\
      --b-ref 4a19c95952cb9710211d29bec3440e752b6a2d03 --b-layout actinium
"""
import argparse
import re
import subprocess
import sys
from dataclasses import dataclass

BASELINE = "gtnhlib-base/9644810c00"
INDEX = "INDEX"
SCOPES = ("gtnhlib", "tests", "upstream-tests", "main")

LEDGER = "docs/PROVENANCE.md"
LEDGER_HEADING = "## Drop ledger"
# Every baseline file that leaves this tree needs a ledger row.
LEDGER_TREE = "src/"
LEDGER_REASONS = (
    "1.7.10-only",
    "native on 1.12.2:",
    "unreachable from Demonica",
    "removed by actinium@",
    "moved to Demonica:",
)


@dataclass(frozen=True)
class Root:
    base: str
    include: tuple[str, ...] = ()  # paths relative to base; empty = everything
    exclude: tuple[str, ...] = ()

    def key(self, path: str) -> str | None:
        """Return path relative to this root if the root covers it, else None."""
        if not path.startswith(self.base + "/"):
            return None
        rel = path[len(self.base) + 1:]
        if self.include and not any(_under(rel, p) for p in self.include):
            return None
        if any(_under(rel, p) for p in self.exclude):
            return None
        return rel


def _under(rel: str, prefix: str) -> bool:
    return rel == prefix or rel.startswith(prefix + "/")


PKG = "com/gtnewhorizon/gtnhlib"
# The syncline's kept set, Demonica's live set at 61fa479d: the compile closure
# of the GTNHLib classes that Demonica's root, glsm and shader code named
# (docs/PROVENANCE.md). A file that leaves the tree after the syncline gets a
# ledger row, so the gtnhlib scope is audited at the syncline's tags.
KEPT = tuple(f"{PKG}/{c}.java" for c in (
    "asm/ClassConstantPoolParser",
    *(f"bytebuf/{c}" for c in (
        "APIUtil", "CheckIntrinsics", "Checks", "MemoryManage", "MemoryStack",
        "MemoryUtilities", "MultiReleaseMemCopy", "MultiReleaseTextDecoding",
        "Pointer", "StackWalkUtil", "package-info",
    )),
    *(f"client/opengl/{c}" for c in ("FBOFunctions", "GLCaps", "UniversalVAO")),
    *(f"client/renderer/{c}" for c in (
        "CallbackTessellator", "DirectDrawCallback", "DirectTessellator",
        "ITessellatorInstance", "RuntimeOptionsBridge", "TessellatorManager",
    )),
    *(f"client/renderer/cel/{c}" for c in (
        "api/util/ColorABGR", "api/util/ColorU8", "api/util/NormI8",
        "model/primitive/ModelPrimitiveView",
        "model/quad/ModelQuad", "model/quad/ModelQuadView",
        "model/quad/ModelQuadViewMutable",
        "model/quad/properties/ModelQuadFacing",
        "model/quad/properties/ModelQuadFlags",
        "util/MathUtil", "util/ModelQuadUtil",
    )),
    *(f"client/renderer/postprocessing/{c}" for c in (
        "DepthTextureProvider", "PostProcessingBridge",
    )),
    "client/renderer/stacks/IStateStack",
    *(f"client/renderer/vao/{c}" for c in (
        "BaseVAO", "IVertexArrayObject", "IndexBuffer", "IndexedVAO",
        "VAOManager", "VaoFunctions", "VertexArrayUnsupported",
        "VertexBufferFactory", "VertexBufferType",
    )),
    *(f"client/renderer/vbo/{c}" for c in ("IVertexBuffer", "VBOManager", "VertexBuffer")),
    *(f"client/renderer/vertex/{c}" for c in (
        "DefaultVertexFormat", "VertexFlags", "VertexFormat",
        "VertexFormatElement", "VertexOptimizer",
    )),
    *(f"client/renderer/vertex/writers/{c}" for c in (
        "ColorVertexAttributeWriter", "IVertexAttributeWriter",
        "LightVertexAttributeWriter", "NormalVertexAttributeWriter",
        "PositionVertexAttributeWriter", "TextureVertexAttributeWriter",
    )),
    "compat/Mods",
    "util/font/IFontParameters",
))
assert len(set(KEPT)) == 60, len(set(KEPT))
# MemoryUtilitiesTest was the other one, until 0.3.0 dropped bytebuf.
ACTINIUM_TESTS = tuple(f"{PKG}/{c}.java" for c in (
    "client/renderer/TessellatorManagerTest",
))
# Kept only if it passes unmodified against the ported renderer.
UPSTREAM_TESTS = (f"{PKG}/client/renderer/VertexFormatTest.java",)

LAYOUTS: dict[str, dict[str, tuple[Root, ...]]] = {
    "upstream": {
        "gtnhlib": (Root("src/main/java", KEPT),),
        "tests": (Root("src/test/java", ACTINIUM_TESTS),),
        "upstream-tests": (Root("src/test/java", UPSTREAM_TESTS),),
        "main": (Root("src/main/java"),),
    },
    "actinium": {
        "gtnhlib": (Root("GTNHLib/src/main/java", KEPT),),
        "tests": (Root("GTNHLib/src/test/java", ACTINIUM_TESTS),),
        "main": (Root("GTNHLib/src/main/java"),),
    },
    # Demonica never vendored GTNHLib's tests, so it has no tests scope.
    "demonica": {
        "gtnhlib": (Root("GTNHLib/src/main/java", KEPT),),
        "main": (Root("GTNHLib/src/main/java"),),
    },
    "s8tnlib": {
        "gtnhlib": (Root("src/main/java", KEPT),),
        "tests": (Root("src/test/java", ACTINIUM_TESTS),),
        "upstream-tests": (Root("src/test/java", UPSTREAM_TESTS),),
        "main": (Root("src/main/java"),),
    },
}

TOP = "."


def git(*args: str) -> str:
    try:
        return subprocess.run(["git", *args], cwd=TOP, capture_output=True,
                              encoding="utf-8", check=True).stdout
    except subprocess.CalledProcessError as e:
        raise SystemExit(f"error: git {' '.join(args)}: {e.stderr.strip()}")


def list_files(ref: str, bases: set[str]) -> dict[str, str]:
    """Return {path: blob_sha} for every file under the given bases at ref.

    No bases means the whole tree.
    """
    if ref == INDEX:
        out = git("ls-files", "-s", "-z", "--", *sorted(bases))
    else:
        out = git("ls-tree", "-r", "-z", ref, "--", *sorted(bases))
    files = {}
    for entry in filter(None, out.split("\0")):
        meta, path = entry.split("\t", 1)
        fields = meta.split()
        if ref == INDEX:
            mode, sha, stage = fields
            if stage != "0":
                raise SystemExit(f"error: {path} is unmerged in the index")
        else:
            mode, _type, sha = fields
        files[path] = sha
    return files


def collect(ref: str, roots: tuple[Root, ...], files: dict[str, str]) -> dict[str, str]:
    """Map every file covered by roots to {key: blob_sha}; keys must be unique."""
    keyed: dict[str, str] = {}
    origin: dict[str, str] = {}
    for path, sha in files.items():
        for root in roots:
            key = root.key(path)
            if key is None:
                continue
            if key in keyed:
                raise SystemExit(f"error: key collision at {ref}: {origin[key]} and {path} "
                                 f"both map to {key}")
            keyed[key] = sha
            origin[key] = path
    return keyed


@dataclass
class Result:
    name: str
    a_roots: tuple[Root, ...]
    b_roots: tuple[Root, ...]
    verbatim: list[str]
    adapted: list[str]
    new: list[str]
    dropped: list[str]

    @property
    def identical(self) -> bool:
        return not (self.adapted or self.new or self.dropped)

    def counts(self) -> str:
        return (f"verbatim: {len(self.verbatim)}  adapted: {len(self.adapted)}  "
                f"new: {len(self.new)}  dropped: {len(self.dropped)}")


def compare(name, a_ref, a_roots, b_ref, b_roots, cache) -> Result:
    def tree(ref, roots):
        bases = {r.base for r in roots}
        files = cache.setdefault(ref, {})
        missing = bases - files.keys()
        if missing:
            listing = list_files(ref, missing)
            for base in missing:
                files[base] = {p: s for p, s in listing.items() if p.startswith(base + "/")}
        merged = {}
        for base in bases:
            merged.update(files[base])
        return collect(ref, roots, merged)

    base = tree(a_ref, a_roots)
    head = tree(b_ref, b_roots)
    both = base.keys() & head.keys()
    return Result(
        name, a_roots, b_roots,
        verbatim=sorted(p for p in both if base[p] == head[p]),
        adapted=sorted(p for p in both if base[p] != head[p]),
        new=sorted(head.keys() - base.keys()),
        dropped=sorted(base.keys() - head.keys()),
    )


def ledger_rows(text: str) -> list[tuple[int, str, str]]:
    """Return (line, path, reason) for each table row of the drop ledger.

    A row's first cell is a path in backticks; header, separator and other
    rows are skipped, and so are fenced code blocks.
    """
    rows = []
    inside = fenced = seen = False
    for n, line in enumerate(text.splitlines(), 1):
        if line.startswith("```"):
            fenced = not fenced
            continue
        if fenced:
            continue
        if line.startswith("## "):
            inside = line.rstrip() == LEDGER_HEADING
            seen = seen or inside
            continue
        if not inside or not line.startswith("|"):
            continue
        cells = [c.strip() for c in line.strip().strip("|").split("|")]
        m = re.fullmatch(r"`([^`]+)`", cells[0])
        if m:
            rows.append((n, m.group(1), cells[1] if len(cells) > 1 else ""))
    if not seen:
        raise SystemExit(f"error: {LEDGER} has no '{LEDGER_HEADING}' section")
    return rows


def check_ledger(a_ref: str, b_ref: str) -> tuple[list[str], int, int]:
    """Return (problems, rows, files covered) for the drop ledger at b_ref."""
    base = list_files(a_ref, set())
    head = list_files(b_ref, set())
    if LEDGER not in head:
        raise SystemExit(f"error: {LEDGER} is not in {b_ref}")
    text = git("show", f":{LEDGER}" if b_ref == INDEX else f"{b_ref}:{LEDGER}")
    rows = ledger_rows(text)
    problems = []
    covered: dict[str, int] = {}
    for n, path, reason in rows:
        where = f"{LEDGER}:{n}: {path}"
        if not reason.replace("`", "").startswith(LEDGER_REASONS):
            problems.append(f"{where}: the reason must start with one of: "
                            + ", ".join(f"'{r}'" for r in LEDGER_REASONS))
        if path.endswith("/"):
            files = sorted(p for p in base if p.startswith(path))
        else:
            files = [path] if path in base else []
        if not files:
            problems.append(f"{where}: not in {a_ref}")
        for f in files:
            which = "" if f == path else f" {f}"
            if f in head:
                problems.append(f"{where}:{which} is still in {b_ref}")
            if f in covered:
                problems.append(f"{where}:{which} already has a row at line {covered[f]}")
            covered.setdefault(f, n)
    for f in sorted(base.keys() - head.keys()):
        if f.startswith(LEDGER_TREE) and f not in covered:
            problems.append(f"{f}: not in {b_ref}, and the ledger has no row for it")
    return problems, len(rows), len(covered)


def describe(roots: tuple[Root, ...]) -> str:
    parts = []
    for r in roots:
        s = f"`{r.base}`"
        if r.include:
            s += " (" + ", ".join(f"`{p}`" for p in r.include) + ")"
        if r.exclude:
            s += " minus " + ", ".join(f"`{p}`" for p in r.exclude)
        parts.append(s)
    return "; ".join(parts)


def manifest(results: list[Result], a_ref, a_layout, b_ref, b_layout) -> str:
    lines = [
        "# Provenance manifest",
        "",
        f"- baseline: `{a_ref}` ({a_layout} layout)",
        f"- compared: `{b_ref}` ({b_layout} layout)",
        "",
        "| scope | verbatim | adapted | new | dropped |",
        "|---|---|---|---|---|",
    ]
    for r in results:
        lines.append(f"| {r.name} | {len(r.verbatim)} | {len(r.adapted)} | "
                     f"{len(r.new)} | {len(r.dropped)} |")
    lines.append("")
    for r in results:
        lines += [
            f"## Scope `{r.name}`",
            "",
            f"- baseline roots: {describe(r.a_roots)}",
            f"- compared roots: {describe(r.b_roots)}",
            "",
        ]
        for title, items in (("Adapted", r.adapted), ("New", r.new), ("Dropped", r.dropped)):
            lines += [f"### {title} ({len(items)})", ""]
            lines += [f"- `{p}`" for p in items]
            lines += [""]
    return "\n".join(lines)


def main() -> int:
    global TOP
    ap = argparse.ArgumentParser(description=__doc__,
                                 formatter_class=argparse.RawDescriptionHelpFormatter)
    ap.add_argument("--scope", choices=(*SCOPES, "all"),
                    help="audit a named scope, or all scopes both layouts define")
    ap.add_argument("--a-ref", default=BASELINE)
    ap.add_argument("--a-layout", choices=tuple(LAYOUTS), default="upstream")
    ap.add_argument("--b-ref", default="HEAD", help=f"a ref, or {INDEX} for staged files")
    ap.add_argument("--b-layout", choices=tuple(LAYOUTS), default="s8tnlib")
    ap.add_argument("--expect-identical", action="store_true",
                    help="exit 1 if any scope has an adapted, new or dropped file")
    ap.add_argument("--ledger", action="store_true",
                    help=f"check the drop ledger in {LEDGER} at --b-ref against that tree")
    ap.add_argument("--write", help="write the markdown manifest to this file")
    args = ap.parse_args()
    if args.scope is None and not args.ledger:
        ap.error("give --scope, --ledger or both")
    if args.ledger and (args.a_layout, args.b_layout) != ("upstream", "s8tnlib"):
        ap.error("--ledger needs --a-layout upstream and --b-layout s8tnlib")
    if args.scope is None and (args.expect_identical or args.write):
        ap.error("--expect-identical and --write need --scope")
    TOP = git("rev-parse", "--show-toplevel").strip()
    # Keep stdout and stderr in order when both go to one pipe.
    sys.stdout.reconfigure(line_buffering=True)

    status = 0
    if args.scope is not None:
        a_scopes, b_scopes = LAYOUTS[args.a_layout], LAYOUTS[args.b_layout]
        if args.scope == "all":
            names = [s for s in SCOPES if s in a_scopes and s in b_scopes]
            skipped = [s for s in SCOPES if s not in names]
            if skipped:
                print(f"note: scopes not defined in both layouts: {', '.join(skipped)}")
        else:
            names = [args.scope]
            for layout in (args.a_layout, args.b_layout):
                if args.scope not in LAYOUTS[layout]:
                    ap.error(f"scope {args.scope} is not defined for layout {layout}")
        cache: dict[str, dict[str, dict[str, str]]] = {}
        results = [compare(name, args.a_ref, a_scopes[name], args.b_ref, b_scopes[name], cache)
                   for name in names]

        for r in results:
            print(f"{r.name + ':':18} {r.counts()}  ({args.a_ref} vs {args.b_ref})")

        text = manifest(results, args.a_ref, args.a_layout, args.b_ref, args.b_layout)
        if args.write:
            with open(args.write, "w", encoding="utf-8") as f:
                f.write(text)
            print(f"wrote {args.write}")
        elif not args.expect_identical and not args.ledger:
            sys.stdout.write("\n" + text)

        if args.expect_identical:
            bad = [r for r in results if not r.identical]
            for r in bad:
                for title, items in (("adapted", r.adapted), ("new", r.new),
                                     ("dropped", r.dropped)):
                    for p in items:
                        print(f"  {r.name}: {title}: {p}", file=sys.stderr)
            if bad:
                print(f"FAIL: not identical: {', '.join(r.name for r in bad)}", file=sys.stderr)
                status = 1
            else:
                print("OK: all audited scopes are identical")

    if args.ledger:
        problems, rows, covered = check_ledger(args.a_ref, args.b_ref)
        print(f"{'ledger:':18} rows: {rows}  files: {covered}  ({LEDGER} at {args.b_ref})")
        for p in problems:
            print(f"  ledger: {p}", file=sys.stderr)
        if problems:
            print(f"FAIL: the drop ledger does not match {args.b_ref}", file=sys.stderr)
            status = 1
        else:
            print(f"OK: every file dropped from {LEDGER_TREE} has one ledger row")
    return status


if __name__ == "__main__":
    raise SystemExit(main())
