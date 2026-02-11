# SPR-B013 - Catálogo servicios + prescripciones + BOM

**Estado:** NOT_STARTED  
**Stage:** 13  
**Duracion objetivo:** 45-90 min (referencial)  
**Tipo:** Backend  

## 1) Objetivo

Cerrar exactamente el alcance de SPR-B013 segun el master BACK aceptado, sin ampliar scope y respetando contratos vigentes del proyecto.

- **Cierra (master):** BRD-REQ-013, BRD-REQ-014, BRD-REQ-016 (estructura BOM)
- **Entrega (master):** servicios CRUD (precio/duración sugerida), estructura prescripción, BOM por servicio.
- **Excluye (master):** consumo automático en cierre (sprint dedicado).

**BRD-REQ objetivo (detalle):**
- BRD-REQ-013: Catálogo de servicios: precio base + duración sugerida + activo/inactivo.
- BRD-REQ-014: Prescripciones/indicaciones: estructura (dosis/frecuencia/duración/vía) + texto opcional + export.
- BRD-REQ-016: BOM por servicio + consumo automático de inventario al cerrar atención (si aplica).

## 2) Cierre de alcance (sin inventar)

### Incluye
- servicios CRUD (precio/duración sugerida).
- estructura prescripción.
- BOM por servicio.
- Solo lo definido por master + BRD para este sprint.

### Excluye
- consumo automático en cierre (sprint dedicado).
- Cualquier funcionalidad no listada en Entrega del master.

## 3) Contexto tecnico alineado

**Scoping del sprint:**
- Branch-scoped (service, service_bom_item, estructura de prescripcion).
- Si aplica branch-scoped: validar JWT branch_id + header X-Branch-Id segun contrato (400/403 en mismatch/falta).

**Entidades principales involucradas:**
- service
- service_bom_item
- estructura prescripcion

**Permisos minimos (docs/10-permisos.md):**
- SERVICE_VIEW
- SERVICE_CREATE
- SERVICE_EDIT
- SERVICE_BOM_EDIT

**Acciones sensibles (reason required):**
- Sin nuevas acciones sensibles en este sprint (mantener reglas globales vigentes).

## 4) Pre-check obligatorio (DoR)

- Verificar identidad de repo con docs/project-lock.md (remote + branch).
- Verificar existencia de este sprint: docs/sprints/spr-b013.md
- Releer docs/state/state.md, DoR, DoD, BRD, arquitectura, seguridad, permisos y dominio antes de implementar.
- Si hay hueco de contrato, contradiccion o permiso faltante: abrir RFC/ADR y detener.

## 5) Entregables esperados

### Backend
- servicios CRUD (precio/duración sugerida).
- estructura prescripción.
- BOM por servicio.

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
- [ ] Implementado: servicios CRUD (precio/duración sugerida).
- [ ] Implementado: estructura prescripción.
- [ ] Implementado: BOM por servicio.
- [ ] BRD-REQ objetivo cubierto: BRD-REQ-013, BRD-REQ-014, BRD-REQ-016 (estructura BOM)
- [ ] Exclusion respetada: consumo automático en cierre (sprint dedicado).
- [ ] Scoping y permisos aplicados segun contrato.
- [ ] DoR validado antes de iniciar.
- [ ] DoD validado antes de READY_FOR_VALIDATION.

## 7) Smoke manual sugerido (usuario)

1) Levantar backend local
- ./mvnw test
- ./mvnw spring-boot:run

2) Validar flujo principal de SPR-B013
- Crear/editar servicio con precio base y duracion sugerida.
- Crear/editar BOM por servicio.
- Validar estructura de prescripcion segun contrato.

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
- **Sprint previo de continuidad:** SPR-B012

## 10) Regla anti-desvio

- No inventar requisitos, endpoints o arquitectura fuera de master/BRD/docs canonicos.
- Si hay cambios de contrato: RFC/ADR + changelog antes de seguir.
- Si bloquea el sprint: registrar BLOCKED en status/log durante ejecucion.

<!-- EOF -->
