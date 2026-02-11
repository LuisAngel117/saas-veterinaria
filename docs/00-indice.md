# Índice de documentación (fuente de verdad)

## Orden de lectura obligatorio (para Codex y para retomar contexto)
1) `docs/project-lock.md`
2) `docs/00-indice.md`
3) `docs/state/state.md`
4) `docs/quality/definition-of-ready.md`
5) `docs/quality/definition-of-done.md`
6) `docs/01-brief.md`
7) `docs/02-brd.md`
8) `docs/03-arquitectura.md`
9) `docs/04-convenciones.md`
10) `docs/05-seguridad.md`
11) `docs/06-dominio-parte-a.md` + `docs/06-dominio-parte-b.md`
12) `docs/07-ux-ui-parte-a.md` + `docs/07-ux-ui-parte-b.md`
13) `docs/08-runbook.md`
14) `docs/09-stage-release.md`
15) `docs/10-permisos.md`
16) `docs/11-entrega.md`
17) `docs/changelog.md`
18) `docs/traceability/rtm.md`
19) `docs/status/status.md`
20) `docs/log/log.md`
21) `docs/sprints/spr-master-back.md` / `docs/sprints/spr-master-front.md`
22) Sprint actual `docs/sprints/spr-*.md`
23) `docs/handoff/handoff-back-to-front.md` (cuando se trabaje FRONT)

## Mapa de docs (canónico, linux-friendly)
| Archivo | Propósito | Leer antes de codificar |
|---|---|---|
| docs/project-lock.md | Identidad del proyecto (anti-mezcla) | Sí |
| docs/state/state.md | Snapshot (entrada de contexto) | Sí |
| docs/02-brd.md | Contrato de requerimientos + BRD-REQ-### | Sí |
| docs/03-arquitectura.md | Arquitectura + scoping + errores | Sí |
| docs/05-seguridad.md | Auth/2FA/lockout/auditoría | Sí |
| docs/06-dominio-parte-*.md | Modelo de datos + reglas | Sí |
| docs/07-ux-ui-parte-*.md | UX/UI vendible por rol | Sí |
| docs/08-runbook.md | Operación local (comandos verdad) | Sí |
| docs/quality/definition-of-*.md | Quality gates DoR/DoD | Sí |
| docs/traceability/rtm.md | RTM req→sprint→evidencia | Sí |
| docs/status/status.md | Control de estados por sprint/tanda | Sí |
| docs/log/log.md | Evidencia append-only | Sí |

## Gobernanza (inmutable)
- Los sprints `docs/sprints/spr-*.md` son **BLOQUEADOS**: no se editan. Cambios solo por RFC/ADR/CHANGELOG.
- DONE/APROBADO solo tras validación local del usuario (evidencia en LOG).

## Trazabilidad (RTM)
- Todo requisito en BRD debe tener ID `BRD-REQ-###`.
- RTM mapea: requisito → sprint(s) → commit → verificación → estado.

## State snapshot (multi-conversación)
- `docs/state/state.md` es la entrada única para retomar.
- Se actualiza al cerrar cada sprint en READY_FOR_VALIDATION.

## Regla EOF
- Todo `.md` bajo `docs/**` termina EXACTO con `<!-- EOF -->`.
- Verificador: `scripts/verify/verify-docs-eof.ps1`.

<!-- EOF -->
