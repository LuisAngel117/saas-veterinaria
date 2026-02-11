# State Snapshot

## 1) Resumen actual
Proyecto nuevo “SaaSVeterinaria” (repo: saas-veterinaria). La documentación contractual ya define: alcance v1, decisiones clave (ADRs), quality gates (DoR/DoD), BRD con IDs estables, RTM inicial, y masters BACK/FRONT en estado DRAFT para aceptación.

## 2) Decisiones cerradas (ADRs)
- Stack: `docs/decisions/adr-0001-stack.md`
- Arquitectura: `docs/decisions/adr-0002-arquitectura.md`
- Scoping: `docs/decisions/adr-0003-tenancy-scoping.md`
- Seguridad/Auth: `docs/decisions/adr-0004-seguridad-auth.md`
- Auditoría: `docs/decisions/adr-0005-auditoria.md`
- UX principios: `docs/decisions/adr-0006-ux-principios.md`
- Walking skeleton: `docs/decisions/adr-0007-walking-skeleton.md`

## 3) Requerimientos (BRD-REQ) — estado
- P0 definidos en `docs/02-brd.md` (BRD-REQ-001..022).
- P1 definidos (BRD-REQ-023..025).
- Online-only placeholders (BRD-REQ-026..027) definidos como fuera de alcance v1 real.

## 4) Estado de sprints/tandas
Referencia: `docs/status/status.md`
- T1/T2/T3: documentación base + gobernanza (READY_FOR_VALIDATION según evidencia).
- T4: BRD-REQ + RTM + masters (READY_FOR_VALIDATION después del commit T4).
- Masters:
  - `docs/sprints/spr-master-back.md` = DRAFT
  - `docs/sprints/spr-master-front.md` = DRAFT

## 5) Próximo paso recomendado (bloqueante)
1) Usuario revisa masters.
2) Usuario responde exactamente:
   - “Acepto el plan maestro BACK tal cual”
   - “Acepto el plan maestro FRONT tal cual”
3) Luego se genera el primer sprint detallado:
   - BACK: SPR-B001 (Walking Skeleton)

## 6) Riesgos/bloqueos actuales
- Si no se aceptan masters, no se deben generar sprints detallados.
- Cualquier cambio a scoping/agenda/seguridad exige RFC/ADR.

<!-- EOF -->
