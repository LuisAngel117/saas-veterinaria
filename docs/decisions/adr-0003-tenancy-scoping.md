# ADR-0003 — Tenancy/Scoping (single-tenant + multi-sucursal)

## Contexto
V1 es single-tenant, pero debe soportar varias sucursales con separación estricta de datos.

## Decisión
- No hay tenant_id en v1.
- Sucursales:
  - `branch_id` en JWT es la fuente de verdad
  - Header `X-Branch-Id` obligatorio en endpoints branch-scoped
  - Validación:
    - falta header → 400
    - mismatch header vs claim → 403
- Branch-scoped por defecto para: agenda, clientes, mascotas, atenciones, inventario, facturación, reportes.

## Consecuencias
- Evita fuga de datos entre sucursales.
- Requiere disciplina: todo endpoint branch-scoped debe aplicar regla.

## Alternativas descartadas
- Header como fuente de verdad sin claim (riesgo de spoof).
- Multi-tenant v1 (fuera de alcance).

## Fecha
2026-02-11

<!-- EOF -->
