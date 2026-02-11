# ADR-0001 - Stack

## Estado
ACCEPTED

## Contexto
Se requiere stack local-first, reproducible y mantenible para una demo vendible sin dependencias externas obligatorias.

## Decision
- Backend: Java 21 + Spring Boot.
- DB: Postgres 17.
- Migraciones: Flyway.
- API docs: OpenAPI/Swagger.
- Frontend: Next.js (TypeScript) + Tailwind + shadcn/ui.
- Scripts operativos/verificacion: PowerShell (`.ps1`).

## Consecuencias
- El runbook debe cubrir Windows y Linux.
- Versiones de tooling deben mantenerse compatibles en cada tanda.

## Alternativas descartadas
- Node.js backend completo: descartado por preferencia de stack Java en dominio empresarial.
- MySQL: descartado por estandarizacion actual en Postgres.

## Fecha
2026-02-11

<!-- EOF -->
