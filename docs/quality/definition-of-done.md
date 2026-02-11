# Definition of Done (DoD)

Un sprint NO puede quedar READY_FOR_VALIDATION si no cumple DoD.

## Checklist DoD (verificable)
- [ ] Todos los AC del sprint están cumplidos.
- [ ] Incremento funcional e integrado (evitar “piezas sueltas”).
- [ ] El repo compila/arranca según comandos “verdad” del sprint (o N/A justificado).
- [ ] Smoke test manual definido y ejecutable; evidencia registrada en `docs/log/log.md` (o placeholder explícito para pegar output).
- [ ] `docs/status/status.md` actualizado a READY_FOR_VALIDATION (nunca DONE).
- [ ] `docs/log/log.md` actualizado (append-only) con comandos + resultado.
- [ ] Si el sprint tocó `BRD-REQ-###`: `docs/traceability/rtm.md` actualizado (req→sprint→commit→verificación→estado).
- [ ] `docs/state/state.md` actualizado con snapshot y “next sprint”.
- [ ] Si hubo cambios de alcance/arquitectura/contratos: RFC/ADR + CHANGELOG actualizados.
- [ ] `scripts/verify/verify-docs-eof.ps1` pasa.
- [ ] `git status` limpio y commit con prefijo correcto.

<!-- EOF -->
