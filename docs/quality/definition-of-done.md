# Definition of Done (DoD)

Propósito: evitar “terminados” sin incremento usable e integrado.

## Checklist DoD (obligatorio)
### Calidad e integración
- [ ] Incremento funcional e integrado (no pieza suelta).
- [ ] Repo compila/build (según aplique).
- [ ] Comandos “verdad” ejecutados o N/A con razón.

### Criterios y pruebas
- [ ] AC completos (verificables).
- [ ] Smoke/manual ejecutado y registrado en LOG (output pegado o placeholder).

### Trazabilidad y documentación
- [ ] `docs/log/log.md` actualizado (append-only) con comandos y resultado.
- [ ] `docs/status/status.md` actualizado con READY_FOR_VALIDATION + hash commit.
- [ ] Si toca/cierra BRD-REQ: `docs/traceability/rtm.md` actualizado.
- [ ] `docs/state/state.md` actualizado con snapshot y “next sprint”.

### Seguridad/auditoría (si aplica)
- [ ] Acciones sensibles exigen reason.
- [ ] Auditoría before/after implementada donde corresponde.

## “Vendible local” mínimo
- [ ] El flujo demo se acerca más a lo descrito en `docs/11-entrega.md`.
- [ ] No introduce dependencias externas en core (offline-first se mantiene).

## Regla de estado
- Codex solo puede dejar **READY_FOR_VALIDATION**.
- **DONE** solo lo marca el usuario tras validar localmente.

<!-- EOF -->
