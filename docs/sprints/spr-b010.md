# SPR-B010 - Clínica: atenciones SOAP + plantillas por servicio

**Estado:** NOT_STARTED  
**Stage:** 10  
**Duracion objetivo:** 45-90 min (referencial)  
**Tipo:** Backend  

## 1) Objetivo

Cerrar exactamente el alcance de SPR-B010 segun el master BACK aceptado, sin ampliar scope y respetando contratos vigentes del proyecto.

- **Cierra (master):** BRD-REQ-011 (parcial: SOAP+templates)
- **Entrega (master):** CRUD atenciones, SOAP campos cerrados, plantillas por servicio, vínculo opcional a cita.
- **Excluye (master):** adjuntos (siguiente sprint).

**BRD-REQ objetivo (detalle):**
- BRD-REQ-011: Atenciones: SOAP estructurado + plantillas por servicio + adjuntos (PDF/JPG/PNG) máx 10MB.

## 2) Cierre de alcance (sin inventar)

### Incluye
- CRUD atenciones.
- SOAP campos cerrados.
- plantillas por servicio.
- vínculo opcional a cita.
- Solo lo definido por master + BRD para este sprint.

### Excluye
- adjuntos (siguiente sprint).
- Cualquier funcionalidad no listada en Entrega del master.

## 3) Contexto tecnico alineado

**Scoping del sprint:**
- Branch-scoped (encounter/soap/template por servicio).
- Si aplica branch-scoped: validar JWT branch_id + header X-Branch-Id segun contrato (400/403 en mismatch/falta).

**Entidades principales involucradas:**
- encounter
- soap_note
- soap_template
- service

**Permisos minimos (docs/10-permisos.md):**
- ENCOUNTER_VIEW
- ENCOUNTER_CREATE
- ENCOUNTER_EDIT
- SERVICE_VIEW

**Acciones sensibles (reason required):**
- Sin nuevas acciones sensibles en este sprint (mantener reglas globales vigentes).

## 4) Pre-check obligatorio (DoR)

- Verificar identidad de repo con docs/project-lock.md (remote + branch).
- Verificar existencia de este sprint: docs/sprints/spr-b010.md
- Releer docs/state/state.md, DoR, DoD, BRD, arquitectura, seguridad, permisos y dominio antes de implementar.
- Si hay hueco de contrato, contradiccion o permiso faltante: abrir RFC/ADR y detener.

## 5) Entregables esperados

### Backend
- CRUD atenciones.
- SOAP campos cerrados.
- plantillas por servicio.
- vínculo opcional a cita.

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
- [ ] Implementado: CRUD atenciones.
- [ ] Implementado: SOAP campos cerrados.
- [ ] Implementado: plantillas por servicio.
- [ ] Implementado: vínculo opcional a cita.
- [ ] BRD-REQ objetivo cubierto: BRD-REQ-011 (parcial: SOAP+templates)
- [ ] Exclusion respetada: adjuntos (siguiente sprint).
- [ ] Scoping y permisos aplicados segun contrato.
- [ ] DoR validado antes de iniciar.
- [ ] DoD validado antes de READY_FOR_VALIDATION.

## 7) Smoke manual sugerido (usuario)

1) Levantar backend local
- ./mvnw test
- ./mvnw spring-boot:run

2) Validar flujo principal de SPR-B010
- Crear atencion con SOAP estructurado.
- Aplicar plantilla por servicio en atencion.
- Vincular atencion a cita de forma opcional.

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
- **Sprint previo de continuidad:** SPR-B009

## 10) Regla anti-desvio

- No inventar requisitos, endpoints o arquitectura fuera de master/BRD/docs canonicos.
- Si hay cambios de contrato: RFC/ADR + changelog antes de seguir.
- Si bloquea el sprint: registrar BLOCKED en status/log durante ejecucion.

<!-- EOF -->
