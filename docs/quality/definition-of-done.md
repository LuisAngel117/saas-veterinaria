# Definition of Done (DoD)

Un sprint/tanda NO puede quedar `READY_FOR_VALIDATION` si no cumple DoD.

## Checklist DoD (verificable)
- [ ] Todos los AC comprometidos estan cumplidos.
- [ ] Incremento integrado (sin piezas sueltas).
- [ ] `docs/status/status.md` actualizado a `READY_FOR_VALIDATION` (nunca DONE).
- [ ] `docs/log/log.md` actualizado en modo append-only con comandos y resultado.
- [ ] Si hubo cambios en `BRD-REQ-###`, se actualizo `docs/traceability/rtm.md`.
- [ ] Si hubo cambios de alcance/contrato/arquitectura, se actualizo ADR/RFC/changelog.
- [ ] `docs/state/state.md` actualizado cuando aplique snapshot de continuidad.
- [ ] Coherencia agenda preservada (check-in separado, sin nuevos estados no aprobados).
- [ ] `scripts/verify/verify-docs-eof.ps1` pasa.
- [ ] `scripts/verify/preflight.ps1` pasa.
- [ ] `git status --porcelain` limpio al cerrar.

<!-- EOF -->
