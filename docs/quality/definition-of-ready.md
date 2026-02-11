# Definition of Ready (DoR)

Propósito: bloquear sprints que arrancan con huecos o decisiones faltantes.

## Checklist DoR (obligatorio)
### Alcance y decisiones
- [ ] Sprint ID y título existen en `spr-master-*.md`.
- [ ] Sprint declara BRD-REQ objetivo (o N/A con justificación).
- [ ] No hay contradicciones con `docs/02-brd.md` y `docs/03-arquitectura.md`.
- [ ] Si hay cambios a decisiones (scoping/seguridad/arquitectura): existe RFC/ADR.

### Datos y scoping
- [ ] Está definido si el endpoint/entidad es branch-scoped o global.
- [ ] Reglas de `X-Branch-Id` vs `branch_id` del JWT claras para el sprint.

### Seguridad y permisos
- [ ] Permisos necesarios están definidos en `docs/10-permisos.md`.
- [ ] Si hay acción sensible: “reason required” definido + auditoría requerida.

### UX/contratos (si aplica)
- [ ] Para FRONT: existe `docs/handoff/handoff-back-to-front.md` actualizado (si integra).
- [ ] Errores esperados (Problem Details) definidos para el flujo del sprint.

### Pruebas y evidencia
- [ ] AC verificables (checklist).
- [ ] Smoke/manual definido (comandos exactos) o marcado N/A con razón.
- [ ] Se define qué evidencia se dejará en LOG/STATUS/RTM.

## Qué bloquea el sprint
- Si falta cualquier ítem crítico (alcance, decisiones, scoping, permisos, AC): **BLOCKED**.
- Si requiere RFC/ADR y no existe: **BLOCKED**.

## Excepciones
- Solo por RFC aprobado, documentando:
  - qué se omite
  - riesgo
  - plan de cierre posterior

<!-- EOF -->
