# SPR-B003 - 2FA TOTP (ADMIN/SUPERADMIN)

**Estado:** NOT_STARTED  
**Stage:** 3  
**Duracion objetivo:** 45-90 min (referencial)  
**Tipo:** Backend  

## 1) Objetivo

Cerrar exactamente el alcance de SPR-B003 segun el master BACK aceptado, sin ampliar scope y respetando contratos vigentes del proyecto.

- **Cierra (master):** BRD-REQ-004
- **Entrega (master):** enrolamiento/validación TOTP, flujo login con challenge, recovery codes (opcional v1, si se define).
- **Excluye (master):** UI; solo API/contrato.

**BRD-REQ objetivo (detalle):**
- BRD-REQ-004: 2FA TOTP para ADMIN/SUPERADMIN.

## 2) Cierre de alcance (sin inventar)

### Incluye
- enrolamiento/validación TOTP.
- flujo login con challenge.
- recovery codes (opcional v1.
- si se define).
- Solo lo definido por master + BRD para este sprint.

### Excluye
- UI; solo API/contrato.
- Cualquier funcionalidad no listada en Entrega del master.

## 3) Contexto tecnico alineado

**Scoping del sprint:**
- Global de autenticacion por usuario; sin branch data propia.
- Si aplica branch-scoped: validar JWT branch_id + header X-Branch-Id segun contrato (400/403 en mismatch/falta).

**Entidades principales involucradas:**
- user (requires_2fa)
- sesion/auth tokens

**Permisos minimos (docs/10-permisos.md):**
- AUTH_2FA_ENROLL
- AUTH_2FA_RESET

**Acciones sensibles (reason required):**
- AUTH_2FA_RESET (reason required)

## 4) Pre-check obligatorio (DoR)

- Verificar identidad de repo con docs/project-lock.md (remote + branch).
- Verificar existencia de este sprint: docs/sprints/spr-b003.md
- Releer docs/state/state.md, DoR, DoD, BRD, arquitectura, seguridad, permisos y dominio antes de implementar.
- Si hay hueco de contrato, contradiccion o permiso faltante: abrir RFC/ADR y detener.

## 5) Entregables esperados

### Backend
- enrolamiento/validación TOTP.
- flujo login con challenge.
- recovery codes (opcional v1.
- si se define).

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
- [ ] Implementado: enrolamiento/validación TOTP.
- [ ] Implementado: flujo login con challenge.
- [ ] Implementado: recovery codes (opcional v1.
- [ ] Implementado: si se define).
- [ ] BRD-REQ objetivo cubierto: BRD-REQ-004
- [ ] Exclusion respetada: UI; solo API/contrato.
- [ ] Scoping y permisos aplicados segun contrato.
- [ ] DoR validado antes de iniciar.
- [ ] DoD validado antes de READY_FOR_VALIDATION.

## 7) Smoke manual sugerido (usuario)

1) Levantar backend local
- ./mvnw test
- ./mvnw spring-boot:run

2) Validar flujo principal de SPR-B003
- Enrolar TOTP para usuario admin/superadmin.
- Validar login con challenge TOTP.
- Validar reset 2FA con reason y auditoria.

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
- **Sprint previo de continuidad:** SPR-B002

## 10) Regla anti-desvio

- No inventar requisitos, endpoints o arquitectura fuera de master/BRD/docs canonicos.
- Si hay cambios de contrato: RFC/ADR + changelog antes de seguir.
- Si bloquea el sprint: registrar BLOCKED en status/log durante ejecucion.

<!-- EOF -->
