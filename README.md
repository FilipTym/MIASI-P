# MIASI-P

Baseline monorepo layout for the basketball boxscore DSL project.

## Folders

- `backend/` — Java + ANTLR core (future API layer)
- `frontend/` — React UI app

## Current status

- Legacy root folders (`src/`, `gen/`, `out/`) were removed.
- ANTLR generated Java is no longer committed as source files.
- Grammar source of truth is in `backend/src/main/antlr/*.g4`.
- Generated parser/lexer code is produced during Maven build in `backend/target/generated-sources/antlr4/`.

## Migration status

Project now uses build-time ANTLR generation from grammar files.
