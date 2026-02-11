# Índice (mapa del sistema)

## 1) Propósito
Este archivo define el mapa canónico de documentación y el orden de lectura obligatorio. Es la fuente de verdad para evitar duplicados y “piezas sueltas”.

## 2) Tabla canónica (archivo → propósito → leer antes de codificar)
| Archivo | Propósito | ¿Leer antes de codificar? |
|---|---|:---:|
| docs/project-lock.md | Identidad del proyecto (anti-mezcla) | ✅ |
| AGENTS.md | Conducta del agente (Codex) | ✅ |
| docs/state/state.md | Snapshot de estado (entrada de contexto) | ✅ |
| docs/quality/definition-of-ready.md | Gate DoR (bloquea sprints) | ✅ |
| docs/quality/definition-of-done.md | Gate DoD (bloquea “terminados”) | ✅ |
| docs/01-brief.md | Visión/alcance/no-objetivos | ✅ |
| docs/02-brd.md | Requisitos con BRD-REQ-### | ✅ |
| docs/traceability/rtm.md | Matriz req → sprint → evidencia | ✅ |
| docs/03-arquitectura.md | Arquitectura, scoping, errores, data | ✅ |
| docs/04-convenciones.md | Normas duras (naming/commits/logs) | ✅ |
| docs/05-seguridad.md | Modelo seguridad (auth/2FA/auditoría) | ✅ |
| docs/06-dominio-parte-a.md | Modelo datos (identidad/CRM/agenda) | ✅ |
| docs/06-dominio-parte-b.md | Modelo datos (clínica/billing/inventario/audit) | ✅ |
| docs/07-ux-ui-parte-a.md | UX/UI: principios, navegación, flujos | ✅ |
| docs/07-ux-ui-parte-b.md | UX/UI: permisos por pantalla, reglas visuales | ✅ |
| docs/08-runbook.md | Cómo correr en local (Windows/Linux) | ✅ |
| docs/09-stage-release.md | Qué cambia al ir online/stage | ⚠️ |
| docs/10-permisos.md | Roles/permisos estables | ✅ |
| docs/11-entrega.md | Checklist vendible local | ✅ |
| docs/changelog.md | Cambios de docs/decisiones | ⚠️ |
| docs/status/status.md | Control de sprints/tandas (estado/evidencia) | ✅ |
| docs/log/log.md | Bitácora append-only (comandos/output) | ✅ |
| docs/decisions/adr-*.md | Decisiones congeladas | ✅ |
| docs/rfcs/*.md | Cambios propuestos (si hay huecos) | ✅ |
| docs/sprints/spr-master-back.md | Plan maestro BACK (DRAFT→ACEPTADO) | ✅ |
| docs/sprints/spr-master-front.md | Plan maestro FRONT (DRAFT→ACEPTADO) | ✅ |
| docs/handoff/handoff-back-to-front.md | Contrato real backend→frontend | ✅ (para FRONT) |

## 3) Orden de lectura obligatorio para Codex (fijo)
1) docs/project-lock.md  
2) AGENTS.md  
3) docs/00-indice.md  
4) docs/state/state.md  
5) docs/quality/definition-of-ready.md  
6) docs/quality/definition-of-done.md  
7) docs/01-brief.md  
8) docs/02-brd.md  
9) docs/traceability/rtm.md  
10) docs/03-arquitectura.md  
11) docs/04-convenciones.md  
12) docs/05-seguridad.md  
13) docs/06-dominio-parte-a.md + parte-b  
14) docs/07-ux-ui-parte-a.md + parte-b  
15) docs/08-runbook.md  
16) docs/10-permisos.md  
17) docs/11-entrega.md  
18) docs/sprints/spr-master-*.md + sprint actual  
19) docs/status/status.md + docs/log/log.md  
20) docs/handoff/handoff-back-to-front.md (si FRONT)

## 4) Cómo trabajar por tandas y sprints
- **Tandas (T1, T2, T3...)**: se aplican para documentación/plantillas/estructura.
- **Sprints (SPR-B### / SPR-F### / SPR-RC###)**: entregan incremento integrado y trazable.
- Cada sprint:
  - declara BRD-REQ objetivo (o N/A si infra estricta)
  - actualiza LOG/STATUS
  - actualiza RTM y state snapshot cuando toca/cierra reqs
  - nunca se marca DONE por Codex (solo usuario tras validar)

## 5) Estados (control)
- Estados permitidos: NOT_STARTED, IN_PROGRESS, READY_FOR_VALIDATION, DONE, BLOCKED.
- Regla: DONE solo tras validación local real del usuario con evidencia en LOG.

## 6) Cambios controlados (anti-inventos)
- Cambios a arquitectura/seguridad/scoping/requisitos:
  - RFC (docs/rfcs/...) + (si aplica) ADR + changelog.
- No se edita un sprint bloqueado sin RFC/ADR.
- Project lock canónico: `docs/project-lock.md` (sin duplicado en raíz).

## 7) Nombres linux-friendly y regla de EOF
- Todos los `.md` bajo `docs/**` terminan EXACTO con `<!-- EOF -->`.
- Script verdad: `scripts/verify/verify-docs-eof.ps1`.

## 8) Flujo multi-conversación BACK/FRONT
- Backend y Frontend se trabajan en conversaciones separadas.
- Ambos deben leer los mismos `.md` (fuente de verdad).
- `docs/state/state.md` es la entrada para retomar sin perder contexto.

## 9) Plan maestro por pista
- Canónicos:
  - `docs/sprints/spr-master-back.md`
  - `docs/sprints/spr-master-front.md`
- `docs/sprints/spr-master.md` puede existir como legacy (no borrar), pero el uso actual se define aquí.

## 10) Quality Gates
- DoR bloquea inicio de sprint si faltan decisiones/inputs.
- DoD bloquea “cierre” si no hay incremento integrado + evidencia.

<!-- EOF -->
