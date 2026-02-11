# SPR-B004 - Auditoría framework (eventos + before/after en sensibles)

**Estado:** NOT_STARTED  
**Stage:** 4  
**Duracion objetivo:** 45-90 min (referencial)  
**Tipo:** Backend  

## 1) Objetivo

Cerrar exactamente el alcance de SPR-B004 segun el master BACK aceptado, sin ampliar scope y respetando contratos vigentes del proyecto.

- **Cierra (master):** BRD-REQ-005
- **Entrega (master):** audit_event persistente, helper para before/after, action_codes, reason-required enforcement, retención demo (job TBD si aplica).
- **Excluye (master):** reportes de auditoría avanzados.

**BRD-REQ objetivo (detalle):**
- BRD-REQ-005: Auditoría de acciones (incluye before/after en sensibles) + retención demo 90 días.

## 2) Cierre de alcance (sin inventar)

### Incluye
- audit_event persistente.
- helper para before/after.
- action_codes.
- reason-required enforcement.
- retención demo (job TBD si aplica).
- Solo lo definido por master + BRD para este sprint.

### Excluye
- reportes de auditoría avanzados.
- Cualquier funcionalidad no listada en Entrega del master.

## 3) Contexto tecnico alineado

**Scoping del sprint:**
- Transversal global con branch_id opcional en audit_event.
- Si aplica branch-scoped: validar JWT branch_id + header X-Branch-Id segun contrato (400/403 en mismatch/falta).

**Entidades principales involucradas:**
- audit_event

**Permisos minimos (docs/10-permisos.md):**
- AUDIT_VIEW

**Acciones sensibles (reason required):**
- ENCOUNTER_REOPEN
- ENCOUNTER_EDIT_CLOSED
- INVENTORY_ADJUST
- INVENTORY_OVERRIDE_NEGATIVE
- INVOICE_PRICE_OVERRIDE
- INVOICE_ANNUL
- SETTINGS_TAX_UPDATE
- FEATURE_FLAG_UPDATE
- AGENDA_OVERRIDE_OVERLAP

## 4) Pre-check obligatorio (DoR)

- Verificar identidad de repo con docs/project-lock.md (remote + branch).
- Verificar existencia de este sprint: docs/sprints/spr-b004.md
- Releer docs/state/state.md, DoR, DoD, BRD, arquitectura, seguridad, permisos y dominio antes de implementar.
- Si hay hueco de contrato, contradiccion o permiso faltante: abrir RFC/ADR y detener.

## 5) Entregables esperados

### Backend
- audit_event persistente.
- helper para before/after.
- action_codes.
- reason-required enforcement.
- retención demo (job TBD si aplica).

### Calidad/validacion
- Casos positivos y negativos por cada item de Entrega.
- Errores consistentes (Problem Details) cuando aplique.

### Trazabilidad/documentacion
- docs/log/log.md (append-only) con comandos y outputs.
- docs/status/status.md en READY_FOR_VALIDATION (nunca DONE por Codex).
- docs/traceability/rtm.md con evidencia de commit para BRD-REQ del sprint.
- docs/state/state.md con snapshot y siguiente sprint.

## 6) Criterios de aceptacion (AC)

- [ ] Scope del sprint coincide exactamente con Entrega del master.
- [ ] Implementado: audit_event persistente.
- [ ] Implementado: helper para before/after.
- [ ] Implementado: action_codes.
- [ ] Implementado: reason-required enforcement.
- [ ] Implementado: retención demo (job TBD si aplica).
- [ ] BRD-REQ objetivo cubierto: BRD-REQ-005
- [ ] Exclusion respetada: reportes de auditoría avanzados.
- [ ] Scoping y permisos aplicados segun contrato.
- [ ] DoR validado antes de iniciar.
- [ ] DoD validado antes de READY_FOR_VALIDATION.

## 7) Smoke manual sugerido (usuario)

1) Levantar backend local
- ./mvnw test
- ./mvnw spring-boot:run

2) Validar flujo principal de SPR-B004
- Ejecutar accion sensible y verificar audit_event con actor/branch/action_code.
- Verificar before_json y after_json en accion sensible.
- Verificar reason required cuando aplique.

3) Evidencia
- Pegar outputs en docs/log/log.md bajo la entrada del sprint.

## 8) Comandos verdad

Backend:
- ./mvnw test
- ./mvnw spring-boot:run

Documentacion:
- pwsh -ExecutionPolicy Bypass -File .\scripts\verify\verify-docs-eof.ps1
- pwsh -ExecutionPolicy Bypass -File .\scripts\verify\preflight.ps1

## 9) Dependencias y continuidad

- **Dependencias (master):** No listadas en master.
- **Sprint previo de continuidad:** SPR-B003

## 10) Regla anti-desvio

- No inventar requisitos, endpoints o arquitectura fuera de master/BRD/docs canonicos.
- Si hay cambios de contrato: RFC/ADR + changelog antes de seguir.
- Si bloquea el sprint: registrar BLOCKED en status/log durante ejecucion.

<!-- EOF -->
