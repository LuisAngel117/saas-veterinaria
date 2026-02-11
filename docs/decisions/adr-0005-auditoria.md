# ADR-0005 — Auditoría

## Contexto
Se requiere trazabilidad “quién cambió qué y cuándo”, con before/after en acciones sensibles.

## Decisión
- Auditoría obligatoria en auth, usuarios/roles, agenda, clientes/mascotas, HC, facturación, inventario, config sensible.
- Acciones sensibles requieren reason y registran before/after.
- Retención demo: 90 días (purga configurable a futuro).

## Consecuencias
- Los sprints deben incluir evidencia de auditoría en LOG y criterios DoD.
- El modelo de auditoría debe ser consistente y consultable.

## Alternativas descartadas
TBD

## Fecha
2026-02-11

<!-- EOF -->
