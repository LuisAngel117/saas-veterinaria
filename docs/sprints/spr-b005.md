# SPR-B005 - Config operativa (branches/rooms/vets profiles)

**Estado:** NOT_STARTED  
**Stage:** 5  
**Duracion objetivo:** 45-90 min (referencial)  
**Tipo:** Backend  

## 1) Objetivo

Cerrar exactamente el alcance de SPR-B005 segun el master BACK aceptado, sin ampliar scope y respetando contratos vigentes del proyecto.

- **Cierra (master):** BRD-REQ-006
- **Entrega (master):** endpoints/servicios para salas y perfil veterinario (branch-scoped), validaciones, seeds completos.
- **Excluye (master):** agenda.

**BRD-REQ objetivo (detalle):**
- BRD-REQ-006: Configuración operativa: sucursales, salas, veterinarios (perfil) + seeds.

## 2) Cierre de alcance (sin inventar)

### Incluye
- endpoints/servicios para salas y perfil veterinario (branch-scoped).
- validaciones.
- seeds completos.
- Solo lo definido por master + BRD para este sprint.

### Excluye
- agenda.
- Cualquier funcionalidad no listada en Entrega del master.

## 3) Contexto tecnico alineado

**Scoping del sprint:**
- Branch-scoped para configuracion operativa (rooms y veterinarian_profile).
- Si aplica branch-scoped: validar JWT branch_id + header X-Branch-Id segun contrato (400/403 en mismatch/falta).

**Entidades principales involucradas:**
- branch
- room
- veterinarian_profile
- user

**Permisos minimos (docs/10-permisos.md):**
- BRANCH_VIEW
- USER_VIEW
- USER_EDIT
- Nota de cierre: `docs/10-permisos.md` no define codigos explicitos `ROOM_*`/`VET_*`; si se requieren codigos nuevos, abrir RFC antes de implementar.

**Acciones sensibles (reason required):**
- Sin nuevas acciones sensibles en este sprint (mantener reglas globales vigentes).

## 4) Pre-check obligatorio (DoR)

- Verificar identidad de repo con docs/project-lock.md (remote + branch).
- Verificar existencia de este sprint: docs/sprints/spr-b005.md
- Releer docs/state/state.md, DoR, DoD, BRD, arquitectura, seguridad, permisos y dominio antes de implementar.
- Si hay hueco de contrato, contradiccion o permiso faltante: abrir RFC/ADR y detener.

## 5) Entregables esperados

### Backend
- endpoints/servicios para salas y perfil veterinario (branch-scoped).
- validaciones.
- seeds completos.

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
- [ ] Implementado: endpoints/servicios para salas y perfil veterinario (branch-scoped).
- [ ] Implementado: validaciones.
- [ ] Implementado: seeds completos.
- [ ] BRD-REQ objetivo cubierto: BRD-REQ-006
- [ ] Exclusion respetada: agenda.
- [ ] Scoping y permisos aplicados segun contrato.
- [ ] DoR validado antes de iniciar.
- [ ] DoD validado antes de READY_FOR_VALIDATION.

## 7) Smoke manual sugerido (usuario)

1) Levantar backend local
- ./mvnw test
- ./mvnw spring-boot:run

2) Validar flujo principal de SPR-B005
- Crear/editar sala por branch y validar unique(branch_id,name).
- Crear/editar perfil veterinario por branch y validar unique(branch_id,user_id).
- Validar aislamiento por branch (403 en mismatch).

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
- **Sprint previo de continuidad:** SPR-B004

## 10) Regla anti-desvio

- No inventar requisitos, endpoints o arquitectura fuera de master/BRD/docs canonicos.
- Si hay cambios de contrato: RFC/ADR + changelog antes de seguir.
- Si bloquea el sprint: registrar BLOCKED en status/log durante ejecucion.

<!-- EOF -->
