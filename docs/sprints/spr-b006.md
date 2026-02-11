# SPR-B006 - Agenda core (CRUD citas + no-solape sala+vet + override)

**Estado:** NOT_STARTED  
**Stage:** 6  
**Duracion objetivo:** 45-90 min (referencial)  
**Tipo:** Backend  

## 1) Objetivo

Cerrar exactamente el alcance de SPR-B006 segun el master BACK aceptado, sin ampliar scope y respetando contratos vigentes del proyecto.

- **Cierra (master):** BRD-REQ-007
- **Entrega (master):** appointment CRUD, verificación de solape por sala+vet, respuesta 409, override con permiso+reason+audit.
- **Excluye (master):** acciones de estado (confirm/checkin/start/close) si se separan.

**BRD-REQ objetivo (detalle):**
- BRD-REQ-007: Agenda: CRUD de citas + vista semana + no-solape por Sala+Veterinario + override (permiso+reason+audit).

## 2) Cierre de alcance (sin inventar)

### Incluye
- appointment CRUD.
- verificación de solape por sala+vet.
- respuesta 409.
- override con permiso+reason+audit.
- Solo lo definido por master + BRD para este sprint.

### Excluye
- acciones de estado (confirm/checkin/start/close) si se separan.
- Cualquier funcionalidad no listada en Entrega del master.

## 3) Contexto tecnico alineado

**Scoping del sprint:**
- Branch-scoped (appointment, room, vet, client, pet).
- Si aplica branch-scoped: validar JWT branch_id + header X-Branch-Id segun contrato (400/403 en mismatch/falta).

**Entidades principales involucradas:**
- appointment
- room
- user(vet)
- client
- pet

**Permisos minimos (docs/10-permisos.md):**
- AGENDA_VIEW
- AGENDA_CREATE
- AGENDA_EDIT
- AGENDA_OVERRIDE_OVERLAP

**Acciones sensibles (reason required):**
- AGENDA_OVERRIDE_OVERLAP (reason required)

## 4) Pre-check obligatorio (DoR)

- Verificar identidad de repo con docs/project-lock.md (remote + branch).
- Verificar existencia de este sprint: docs/sprints/spr-b006.md
- Releer docs/state/state.md, DoR, DoD, BRD, arquitectura, seguridad, permisos y dominio antes de implementar.
- Si hay hueco de contrato, contradiccion o permiso faltante: abrir RFC/ADR y detener.

## 5) Entregables esperados

### Backend
- appointment CRUD.
- verificación de solape por sala+vet.
- respuesta 409.
- override con permiso+reason+audit.

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
- [ ] Implementado: appointment CRUD.
- [ ] Implementado: verificación de solape por sala+vet.
- [ ] Implementado: respuesta 409.
- [ ] Implementado: override con permiso+reason+audit.
- [ ] BRD-REQ objetivo cubierto: BRD-REQ-007
- [ ] Exclusion respetada: acciones de estado (confirm/checkin/start/close) si se separan.
- [ ] Scoping y permisos aplicados segun contrato.
- [ ] DoR validado antes de iniciar.
- [ ] DoD validado antes de READY_FOR_VALIDATION.

## 7) Smoke manual sugerido (usuario)

1) Levantar backend local
- ./mvnw test
- ./mvnw spring-boot:run

2) Validar flujo principal de SPR-B006
- Crear cita sin solape (200/201).
- Intentar solape sala/vet y verificar 409.
- Aplicar override con permiso+reason y verificar auditoria.

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
- **Sprint previo de continuidad:** SPR-B005

## 10) Regla anti-desvio

- No inventar requisitos, endpoints o arquitectura fuera de master/BRD/docs canonicos.
- Si hay cambios de contrato: RFC/ADR + changelog antes de seguir.
- Si bloquea el sprint: registrar BLOCKED en status/log durante ejecucion.

<!-- EOF -->
