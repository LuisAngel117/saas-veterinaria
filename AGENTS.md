# AGENTS — Reglas de conducta para agentes (Codex)

## 1) Identidad (obligatorio)
Antes de cualquier acción:
1) Leer `docs/project-lock.md`
   - Ruta canónica de lock: `docs/project-lock.md` (sin duplicado en raíz).
2) Verificar `git remote -v` coincide con `repo_url`
3) Verificar branch y que el proyecto coincide

Si mismatch: DETENER.

## 2) Orden mínimo de lectura (obligatorio)
- docs/project-lock.md
- AGENTS.md
- docs/00-indice.md
- docs/state/state.md
- docs/quality/definition-of-ready.md
- docs/quality/definition-of-done.md
- sprint actual (si aplica)
- docs/status/status.md + docs/log/log.md

## 3) Reglas duras
- No inventar requisitos ni arquitectura.
- No editar sprints bloqueados (solo por RFC/ADR/CHANGELOG).
- No tocar archivos fuera del scope de la tanda/sprint.
- Mantener repo compilable.
- Nunca marcar DONE (solo READY_FOR_VALIDATION).
- Siempre actualizar LOG (append-only) y STATUS (tabla).
- Si falta info crítica: crear RFC o detener.

## 4) Quality Gates
- Validar DoR antes de implementar.
- Validar DoD antes de marcar READY_FOR_VALIDATION.

## 5) Scripts “verdad”
- `scripts/verify/verify-docs-eof.ps1`
- `scripts/verify/preflight.ps1`
- Smoke scripts (cuando existan): `scripts/smoke/*`

<!-- EOF -->
