# Indice de documentacion (fuente de verdad)

## Orden de lectura obligatorio (para Codex y para retomar contexto)
1) `project-lock.md`
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
17) `docs/traceability/rtm.md`
18) `docs/changelog.md`
19) `docs/status/status.md`
20) `docs/log/log.md`
21) `docs/sprints/spr-master-back.md` / `docs/sprints/spr-master-front.md`
22) Sprint actual `docs/sprints/spr-*.md`
23) `docs/handoff/handoff-back-to-front.md` (cuando aplique)

## Mapa canónico (anti-desviacion)
| Archivo | Proposito | Obligatorio |
|---|---|---|
| project-lock.md | Identidad del repo y contrato base | Si |
| docs/state/state.md | Snapshot de contexto para retomadas | Si |
| docs/02-brd.md | Contrato funcional (`BRD-REQ-###`) | Si |
| docs/03-arquitectura.md | Arquitectura, scoping, errores, reglas core | Si |
| docs/05-seguridad.md | Auth, 2FA, lockout, auditoria | Si |
| docs/06-dominio-parte-*.md | Modelo de datos e invariantes | Si |
| docs/07-ux-ui-parte-*.md | Flujos UX por rol y validaciones | Si |
| docs/08-runbook.md | Comandos operativos locales | Si |
| docs/quality/definition-of-*.md | Gates de calidad (DoR/DoD) | Si |
| docs/traceability/rtm.md | Req -> sprint -> commit -> verificacion | Si |
| docs/status/status.md | Control de estado por tanda/sprint | Si |
| docs/log/log.md | Evidencia append-only | Si |

## Gobernanza (inmutable)
- Los sprints `docs/sprints/spr-*.md` son BLOQUEADOS; cambios solo por RFC/ADR + changelog.
- Los masters BACK/FRONT se mantienen en DRAFT hasta validacion del usuario.
- DONE/APROBADO solo lo marca el usuario tras validacion local con evidencia en log.
- Si cambia alcance/contrato/arquitectura, es obligatorio actualizar ADR/RFC/changelog.

## Coherencia de agenda (contrato)
- Estados canonicos de cita: `RESERVED`, `CONFIRMED`, `IN_ATTENTION`, `CLOSED`, `CANCELLED`.
- Check-in es un evento separado; NO agrega estados nuevos.
- Si se propone estado adicional, debe pasar por RFC + ADR antes de documentarlo.

## Regla EOF
- Todo `.md` bajo `docs/**` debe terminar exacto con `<!-- EOF -->`.
- Verificador: `scripts/verify/verify-docs-eof.ps1`.

<!-- EOF -->
