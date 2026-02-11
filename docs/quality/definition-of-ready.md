# Definition of Ready (DoR)

Un sprint NO puede iniciar si no cumple DoR. Si falla, debe quedar BLOCKED con nota “DoR FAIL”.

## Checklist DoR (verificable)
- [ ] `docs/project-lock.md` existe y repo_url coincide con `git remote -v`.
- [ ] `docs/state/state.md` actualizado y describe decisiones cerradas/pedientes.
- [ ] `docs/02-brd.md` existe con IDs `BRD-REQ-###` para el scope del sprint.
- [ ] El sprint declara explícitamente qué `BRD-REQ-###` toca/cierra (o justifica N/A si es infra).
- [ ] Alcance cerrado (incluye/excluye) sin ambigüedades.
- [ ] Criterios de aceptación (AC) verificables (no abstractos).
- [ ] Dependencias identificadas (backend/frontend/db/scripts).
- [ ] Decisiones de seguridad/scoping relevantes ya cerradas (o RFC/ADR creado).
- [ ] Plan de verificación: smoke test manual con comandos exactos.
- [ ] Comandos “verdad” definidos (o N/A con razón).
- [ ] Impacto en RTM/state definido (qué se actualizará al cierre).
- [ ] Linux-strict: paths canónicos, sin duplicados por casing.
- [ ] Riesgos/huecos: si existen, están documentados y bloquean con RFC/ADR.

<!-- EOF -->
