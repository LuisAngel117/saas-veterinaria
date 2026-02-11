# AGENTS - Reglas de conducta para agentes (Codex)

## Fuente de verdad
- Identidad del proyecto: `project-lock.md`.
- Orden de lectura: `docs/00-indice.md`.
- Estado actual: `docs/state/state.md`.
- Gobernanza: cambios de contrato por RFC/ADR + changelog.

## Orden minimo de lectura antes de actuar
1) `project-lock.md`
2) `docs/00-indice.md`
3) `docs/state/state.md`
4) Sprint actual (si aplica) en `docs/sprints/`
5) `docs/status/status.md` y `docs/log/log.md`
6) `docs/quality/definition-of-ready.md` y `docs/quality/definition-of-done.md`
7) `docs/traceability/rtm.md`

## Reglas duras
- NO inventar requisitos, endpoints, scripts, rutas, usuarios git ni datos faltantes.
- NO editar sprints bloqueados; si algo cambia, abrir RFC/ADR y actualizar changelog.
- NO tocar archivos fuera del scope del prompt.
- Nunca marcar DONE/APROBADO (lo marca el usuario tras validacion local).
- Mantener repo en estado verificable.
- Linux-strict: nombres en minusculas y case consistente.
- Agenda: check-in es evento separado; no inventar estados extra de cita.

## Evidencia
- `docs/log/log.md` es append-only (nunca reescribir entradas previas).
- `docs/status/status.md` es la tabla de control.
- Todo doc bajo `docs/**` termina con `<!-- EOF -->`.

<!-- EOF -->
