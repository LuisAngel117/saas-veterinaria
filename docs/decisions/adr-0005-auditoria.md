# ADR-0005 — Auditoría (before/after)

## Contexto
Necesitamos trazabilidad “vendible”: quién cambió qué, cuándo y por qué, especialmente en acciones sensibles.

## Decisión
- Tabla `audit_event` con:
  - actor, rol, branch_id, action_code, entidad, timestamp
  - reason (cuando aplica)
  - before_json/after_json en acciones sensibles
- Auditoría obligatoria para auth, agenda, clínica, inventario, facturación y configuración sensible.
- Retención demo: 90 días (purga planificada).

## Consecuencias
- Overhead de implementación (captura before/after).
- Mejora de confianza y depuración.

## Alternativas descartadas
- Logs como auditoría (insuficiente y no consultable).
- Auditoría solo “after” (pierde trazabilidad).

## Fecha
2026-02-11

<!-- EOF -->
