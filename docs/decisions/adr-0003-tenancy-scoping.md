# ADR-0003 — Tenancy y Scoping

## Contexto
V1 es single-tenant pero multi-sucursal.

## Decisión
- Single-tenant v1.
- Multi-sucursal (branch) con selección al iniciar sesión.
- `branch_id` en JWT es fuente de verdad.
- `X-Branch-Id` obligatorio en endpoints branch-scoped.
- Validación: header debe coincidir con claim; mismatch → 403; falta header → 400.

## Consecuencias
- Todos los endpoints branch-scoped deben exigir y validar contexto.
- Seeds demo deben incluir múltiples sucursales.

## Alternativas descartadas
TBD

## Fecha
2026-02-11

<!-- EOF -->
