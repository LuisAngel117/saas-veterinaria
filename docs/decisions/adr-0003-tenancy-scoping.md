# ADR-0003 - Tenancy y Scoping

## Estado
ACCEPTED

## Contexto
V1 requiere aislamiento operativo por sucursal sin implementar multi-tenant completo.

## Decision
- V1 es single-tenant y multi-sucursal.
- `branch_id` en JWT es fuente de verdad.
- `X-Branch-Id` es obligatorio en endpoints branch-scoped.
- Si falta header: 400. Si no coincide con claim: 403.

## Consecuencias
- Todo modulo branch-scoped debe validar contexto de sucursal.
- Seeds y smoke deben cubrir al menos dos sucursales.

## Alternativas descartadas
- Scoping solo por header: descartado por inseguro.
- Sin header explicito: descartado por baja trazabilidad operacional.

## Fecha
2026-02-11

<!-- EOF -->
