# ADR-0001 — Stack técnico

## Contexto
Necesitamos un stack empleable, estable y compatible con local-first/offline-first real, con demo reproducible y tooling estándar.

## Decisión
- Backend: Java 21 + Spring Boot + Spring Security + Spring Data JPA
- DB: Postgres 17
- Migraciones: Flyway (mínimo versión compatible con Postgres 17; objetivo Flyway 10.20.0+)
- API docs: OpenAPI/Swagger
- Frontend: Next.js (TypeScript) + Tailwind + shadcn/ui + TanStack Query + React Hook Form + Zod
- Monorepo (backend + frontend)
- Scripts “verdad” principales en PowerShell (.ps1)
- Errores API: Problem Details (RFC 7807)

## Consecuencias
- Requiere disciplina de módulos y convenciones.
- Flyway debe fijarse en versión compatible (evitar “unsupported database”).

## Alternativas descartadas
- Multi-tenant v1 (descartado por alcance).
- Docker obligatorio desde el inicio (pospuesto).

## Fecha
2026-02-11

<!-- EOF -->
