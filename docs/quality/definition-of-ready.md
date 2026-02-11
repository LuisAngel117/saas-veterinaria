# Definition of Ready (DoR)

Un sprint NO puede iniciar si no cumple DoR. Si falla, debe quedar en `BLOCKED` con nota `DoR FAIL`.

## Checklist DoR (verificable)
- [ ] `project-lock.md` existe y `repo_url` coincide con `git remote -v`.
- [ ] `docs/state/state.md` esta actualizado (decisiones cerradas, pendientes, riesgos).
- [ ] `docs/02-brd.md` incluye los `BRD-REQ-###` que el sprint tocara.
- [ ] El sprint declara `incluye/excluye` y mapea `BRD-REQ-###` explicitamente.
- [ ] AC verificables y no ambiguos (con resultados observables).
- [ ] Dependencias detectadas (backend/frontend/db/scripts/docs).
- [ ] Seguridad/scoping relevantes ya definidos o existe RFC/ADR abierto.
- [ ] Plan de verificacion definido (smoke y comandos exactos).
- [ ] Impacto en RTM/status/log/state identificado antes de empezar.
- [ ] Coherencia agenda validada: check-in separado, sin estados extra inventados.
- [ ] Paths canonicos y linux-strict (case consistente, sin duplicados por casing).
- [ ] Riesgos/huecos documentados; si bloquean, quedan en RFC/ADR.

<!-- EOF -->
