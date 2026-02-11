# ADR-0007 — Walking Skeleton

## Contexto
Evitar construir por capas sin integración.

## Decisión
Incluir temprano un “walking skeleton” end-to-end:
- Auth + selección de sucursal
- 1 caso core (crear cita) con persistencia
- Runbook + smoke scripts
- Evidencia en LOG/STATUS

## Consecuencias
- Los masters BACK/FRONT deben priorizar este bloque.
- Sprints deben ser vertical slice cuando aplique.

## Alternativas descartadas
TBD

## Fecha
2026-02-11

<!-- EOF -->
