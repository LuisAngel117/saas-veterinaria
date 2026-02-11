# SPR-B009 - Mascotas/pacientes

**Estado:** NOT_STARTED  
**Stage:** 9  
**Duracion objetivo:** 45-90 min (referencial)  
**Tipo:** Backend  

## 1) Objetivo

Cerrar exactamente el alcance de SPR-B009 segun el master BACK aceptado, sin ampliar scope y respetando contratos vigentes del proyecto.

- **Cierra (master):** BRD-REQ-010
- **Entrega (master):** CRUD mascotas, internal_code único por branch, alertas/antecedentes.
- **Excluye (master):** clínica SOAP.

**BRD-REQ objetivo (detalle):**
- BRD-REQ-010: Mascotas: CRUD + código interno único por sucursal + alertas/antecedentes.

## 2) Cierre de alcance (sin inventar)

### Incluye
- CRUD mascotas.
- internal_code único por branch.
- alertas/antecedentes.
- Solo lo definido por master + BRD para este sprint.

### Excluye
- clínica SOAP.
- Cualquier funcionalidad no listada en Entrega del master.

## 3) Contexto tecnico alineado

**Scoping del sprint:**
- Branch-scoped (pet).
- Si aplica branch-scoped: validar JWT branch_id + header X-Branch-Id segun contrato (400/403 en mismatch/falta).

**Entidades principales involucradas:**
- pet
- client

**Permisos minimos (docs/10-permisos.md):**
- PET_VIEW
- PET_CREATE
- PET_EDIT

**Acciones sensibles (reason required):**
- Sin nuevas acciones sensibles en este sprint (mantener reglas globales vigentes).

## 4) Pre-check obligatorio (DoR)

- Verificar identidad de repo con docs/project-lock.md (remote + branch).
- Verificar existencia de este sprint: docs/sprints/spr-b009.md
- Releer docs/state/state.md, DoR, DoD, BRD, arquitectura, seguridad, permisos y dominio antes de implementar.
- Si hay hueco de contrato, contradiccion o permiso faltante: abrir RFC/ADR y detener.

## 5) Entregables esperados

### Backend
- CRUD mascotas.
- internal_code único por branch.
- alertas/antecedentes.

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
- [ ] Implementado: CRUD mascotas.
- [ ] Implementado: internal_code único por branch.
- [ ] Implementado: alertas/antecedentes.
- [ ] BRD-REQ objetivo cubierto: BRD-REQ-010
- [ ] Exclusion respetada: clínica SOAP.
- [ ] Scoping y permisos aplicados segun contrato.
- [ ] DoR validado antes de iniciar.
- [ ] DoD validado antes de READY_FOR_VALIDATION.

## 7) Smoke manual sugerido (usuario)

1) Levantar backend local
- ./mvnw test
- ./mvnw spring-boot:run

2) Validar flujo principal de SPR-B009
- Crear mascota con internal_code por branch.
- Validar unique(branch_id,internal_code).
- Validar alertas/antecedentes persisten y se consultan.

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
- **Sprint previo de continuidad:** SPR-B008

## 10) Regla anti-desvio

- No inventar requisitos, endpoints o arquitectura fuera de master/BRD/docs canonicos.
- Si hay cambios de contrato: RFC/ADR + changelog antes de seguir.
- Si bloquea el sprint: registrar BLOCKED en status/log durante ejecucion.

<!-- EOF -->
