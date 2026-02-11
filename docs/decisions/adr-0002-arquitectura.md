# ADR-0002 — Arquitectura

## Contexto
Necesitamos evitar “piezas sueltas” y mantener trazabilidad.

## Decisión
- Monolito modular (backend) con capas claras (web/api, aplicación, dominio, infraestructura).
- Contratos de API consistentes + errores Problem Details.
- Gobernanza: sprints bloqueados; cambios por RFC/ADR/CHANGELOG.

## Consecuencias
- El diseño de sprints debe ser vertical slice cuando aplique.
- Se requiere RTM y state snapshot actualizados.

## Alternativas descartadas
TBD

## Fecha
2026-02-11

<!-- EOF -->
