# ADR-0001 — Stack

## Contexto
Proyecto SaaSVeterinaria demo local-first/offline-first.

## Decisión
- Backend: Java 21 + Spring Boot
- DB: Postgres 17
- Migraciones: Flyway (compatible con Postgres 17)
- API docs: OpenAPI/Swagger
- Frontend: Next.js (TypeScript) + Tailwind + shadcn/ui
- Monorepo: backend + frontend
- Scripts “verdad”: PowerShell (.ps1)

## Consecuencias
- Tooling y runbook deben soportar Windows + Linux.
- Versiones deben mantenerse compatibles (especialmente Flyway/Postgres).

## Alternativas descartadas
TBD

## Fecha
2026-02-11

<!-- EOF -->
