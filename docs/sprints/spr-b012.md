# SPR-B012 - Cierre/reapertura gobernada (HC)

**Estado:** NOT_STARTED  
**Stage:** 12  
**Duracion objetivo:** 45-90 min (referencial)  
**Tipo:** Backend  

## 1) Objetivo

Cerrar exactamente el alcance de SPR-B012 segun el master BACK aceptado, sin ampliar scope y respetando contratos vigentes del proyecto.

- **Cierra (master):** BRD-REQ-012
- **Entrega (master):** close HC, reopen HC (admin/superadmin), permiso opcional vet, reason+audit, bloqueo edición post-cierre.
- **Excluye (master):** flujos UI.

**BRD-REQ objetivo (detalle):**
- BRD-REQ-012: Cierre y reapertura de historia clínica con permisos + reason + auditoría.

## 2) Cierre de alcance (sin inventar)

### Incluye
- close HC.
- reopen HC (admin/superadmin).
- permiso opcional vet.
- reason+audit.
- bloqueo edición post-cierre.
- Solo lo definido por master + BRD para este sprint.

### Excluye
- flujos UI.
- Cualquier funcionalidad no listada en Entrega del master.

## 3) Contexto tecnico alineado

**Scoping del sprint:**
- Branch-scoped (encounter close/reopen).
- Si aplica branch-scoped: validar JWT branch_id + header X-Branch-Id segun contrato (400/403 en mismatch/falta).

**Entidades principales involucradas:**
- encounter
- soap_note

**Permisos minimos (docs/10-permisos.md):**
- ENCOUNTER_CLOSE
- ENCOUNTER_REOPEN
- ENCOUNTER_EDIT_CLOSED

**Acciones sensibles (reason required):**
- ENCOUNTER_REOPEN (reason required)
- ENCOUNTER_EDIT_CLOSED (reason required)

## 4) Pre-check obligatorio (DoR)

- Verificar identidad de repo con docs/project-lock.md (remote + branch).
- Verificar existencia de este sprint: docs/sprints/spr-b012.md
- Releer docs/state/state.md, DoR, DoD, BRD, arquitectura, seguridad, permisos y dominio antes de implementar.
- Si hay hueco de contrato, contradiccion o permiso faltante: abrir RFC/ADR y detener.

## 5) Entregables esperados

### Backend
- close HC.
- reopen HC (admin/superadmin).
- permiso opcional vet.
- reason+audit.
- bloqueo edición post-cierre.

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
- [ ] Implementado: close HC.
- [ ] Implementado: reopen HC (admin/superadmin).
- [ ] Implementado: permiso opcional vet.
- [ ] Implementado: reason+audit.
- [ ] Implementado: bloqueo edición post-cierre.
- [ ] BRD-REQ objetivo cubierto: BRD-REQ-012
- [ ] Exclusion respetada: flujos UI.
- [ ] Scoping y permisos aplicados segun contrato.
- [ ] DoR validado antes de iniciar.
- [ ] DoD validado antes de READY_FOR_VALIDATION.

## 7) Smoke manual sugerido (usuario)

1) Levantar backend local
- ./mvnw test
- ./mvnw spring-boot:run

2) Validar flujo principal de SPR-B012
- Cerrar HC y validar bloqueo de edicion posterior.
- Reabrir HC con rol autorizado + reason.
- Intentar reapertura sin permiso y validar 403.

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
- **Sprint previo de continuidad:** SPR-B011

## 10) Regla anti-desvio

- No inventar requisitos, endpoints o arquitectura fuera de master/BRD/docs canonicos.
- Si hay cambios de contrato: RFC/ADR + changelog antes de seguir.
- Si bloquea el sprint: registrar BLOCKED en status/log durante ejecucion.

<!-- EOF -->
