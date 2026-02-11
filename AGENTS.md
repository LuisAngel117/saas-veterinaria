# AGENTS — Reglas de conducta para agentes (Codex)

## Fuente de verdad
- Identidad del proyecto: `docs/project-lock.md`
- Orden de lectura: `docs/00-indice.md`
- Estado actual: `docs/state/state.md`
- Gobernanza: sprints bloqueados; cambios solo por RFC/ADR/CHANGELOG.

## Orden mínimo de lectura antes de actuar
1) `docs/project-lock.md`
2) `docs/00-indice.md`
3) `docs/state/state.md`
4) Sprint actual (si aplica) en `docs/sprints/`
5) `docs/status/status.md` y `docs/log/log.md`
6) `docs/quality/definition-of-ready.md` y `docs/quality/definition-of-done.md`
7) `docs/traceability/rtm.md`

## Reglas duras
- NO inventar requisitos, endpoints, scripts, rutas, usuarios git, ni datos faltantes.
- NO editar sprints bloqueados. Si algo debe cambiar: RFC/ADR + CHANGELOG.
- NO tocar archivos fuera del scope del prompt.
- Nunca marcar DONE/APROBADO (eso lo hace el usuario tras validación local con evidencia en LOG).
- Siempre mantener el repo compilable/ejecutable.
- Linux-strict: nombres minúsculos, sin espacios, case consistente.

## Evidencia
- `docs/log/log.md` es append-only (nunca reescribir).
- `docs/status/status.md` es la tabla de control.
- Todo doc bajo `docs/**` debe terminar con `<!-- EOF -->` (verificador en scripts/verify).

<!-- EOF -->
