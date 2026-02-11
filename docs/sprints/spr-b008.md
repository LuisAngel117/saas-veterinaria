# SPR-B008 - CRM clientes + consentimientos

**Estado:** NOT_STARTED  
**Stage:** 8  
**Duracion objetivo:** 45-90 min (referencial)  
**Tipo:** Backend  

## 1) Objetivo

Cerrar exactamente el alcance de SPR-B008 segun el master BACK aceptado, sin ampliar scope y respetando contratos vigentes del proyecto.

- **Cierra (master):** BRD-REQ-009
- **Entrega (master):** CRUD clientes, consentimiento trazable (campos cerrados), búsqueda/paginación básica.
- **Excluye (master):** mascotas.

**BRD-REQ objetivo (detalle):**
- BRD-REQ-009: CRM clientes (propietarios) + consentimientos trazables.

## 2) Cierre de alcance (sin inventar)

### Incluye
- CRUD clientes.
- consentimiento trazable (campos cerrados).
- búsqueda/paginación básica.
- Solo lo definido por master + BRD para este sprint.

### Excluye
- mascotas.
- Cualquier funcionalidad no listada en Entrega del master.

## 3) Contexto tecnico alineado

**Scoping del sprint:**
- Branch-scoped (client y client_consent).
- Si aplica branch-scoped: validar JWT branch_id + header X-Branch-Id segun contrato (400/403 en mismatch/falta).

**Entidades principales involucradas:**
- client
- client_consent

**Permisos minimos (docs/10-permisos.md):**
- CLIENT_VIEW
- CLIENT_CREATE
- CLIENT_EDIT

**Acciones sensibles (reason required):**
- Sin nuevas acciones sensibles en este sprint (mantener reglas globales vigentes).

## 4) Pre-check obligatorio (DoR)

- Verificar identidad de repo con docs/project-lock.md (remote + branch).
- Verificar existencia de este sprint: docs/sprints/spr-b008.md
- Releer docs/state/state.md, DoR, DoD, BRD, arquitectura, seguridad, permisos y dominio antes de implementar.
- Si hay hueco de contrato, contradiccion o permiso faltante: abrir RFC/ADR y detener.

## 5) Entregables esperados

### Backend
- CRUD clientes.
- consentimiento trazable (campos cerrados).
- búsqueda/paginación básica.

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
- [ ] Implementado: CRUD clientes.
- [ ] Implementado: consentimiento trazable (campos cerrados).
- [ ] Implementado: búsqueda/paginación básica.
- [ ] BRD-REQ objetivo cubierto: BRD-REQ-009
- [ ] Exclusion respetada: mascotas.
- [ ] Scoping y permisos aplicados segun contrato.
- [ ] DoR validado antes de iniciar.
- [ ] DoD validado antes de READY_FOR_VALIDATION.

## 7) Smoke manual sugerido (usuario)

1) Levantar backend local
- ./mvnw test
- ./mvnw spring-boot:run

2) Validar flujo principal de SPR-B008
- Crear/editar cliente con consentimiento trazable.
- Validar busqueda y paginacion basica.
- Validar aislamiento de clientes por branch.

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
- **Sprint previo de continuidad:** SPR-B007

## 10) Regla anti-desvio

- No inventar requisitos, endpoints o arquitectura fuera de master/BRD/docs canonicos.
- Si hay cambios de contrato: RFC/ADR + changelog antes de seguir.
- Si bloquea el sprint: registrar BLOCKED en status/log durante ejecucion.

<!-- EOF -->
