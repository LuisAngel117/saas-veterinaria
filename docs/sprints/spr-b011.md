# SPR-B011 - Adjuntos de historia clínica

**Estado:** NOT_STARTED  
**Stage:** 11  
**Duracion objetivo:** 45-90 min (referencial)  
**Tipo:** Backend  

## 1) Objetivo

Cerrar exactamente el alcance de SPR-B011 segun el master BACK aceptado, sin ampliar scope y respetando contratos vigentes del proyecto.

- **Cierra (master):** BRD-REQ-011 (adjuntos)
- **Entrega (master):** storage local + metadata DB, upload/download protegido, límite 10MB, tipos permitidos.
- **Excluye (master):** export a PDF de historia (si no está en alcance v1).

**BRD-REQ objetivo (detalle):**
- BRD-REQ-011: Atenciones: SOAP estructurado + plantillas por servicio + adjuntos (PDF/JPG/PNG) máx 10MB.

## 2) Cierre de alcance (sin inventar)

### Incluye
- storage local + metadata DB.
- upload/download protegido.
- límite 10MB.
- tipos permitidos.
- Solo lo definido por master + BRD para este sprint.

### Excluye
- export a PDF de historia (si no está en alcance v1).
- Cualquier funcionalidad no listada en Entrega del master.

## 3) Contexto tecnico alineado

**Scoping del sprint:**
- Branch-scoped (attachment de historia clinica).
- Si aplica branch-scoped: validar JWT branch_id + header X-Branch-Id segun contrato (400/403 en mismatch/falta).

**Entidades principales involucradas:**
- attachment
- encounter

**Permisos minimos (docs/10-permisos.md):**
- ATTACHMENT_UPLOAD
- ATTACHMENT_VIEW

**Acciones sensibles (reason required):**
- Sin nuevas acciones sensibles en este sprint (mantener reglas globales vigentes).

## 4) Pre-check obligatorio (DoR)

- Verificar identidad de repo con docs/project-lock.md (remote + branch).
- Verificar existencia de este sprint: docs/sprints/spr-b011.md
- Releer docs/state/state.md, DoR, DoD, BRD, arquitectura, seguridad, permisos y dominio antes de implementar.
- Si hay hueco de contrato, contradiccion o permiso faltante: abrir RFC/ADR y detener.

## 5) Entregables esperados

### Backend
- storage local + metadata DB.
- upload/download protegido.
- límite 10MB.
- tipos permitidos.

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
- [ ] Implementado: storage local + metadata DB.
- [ ] Implementado: upload/download protegido.
- [ ] Implementado: límite 10MB.
- [ ] Implementado: tipos permitidos.
- [ ] BRD-REQ objetivo cubierto: BRD-REQ-011 (adjuntos)
- [ ] Exclusion respetada: export a PDF de historia (si no está en alcance v1).
- [ ] Scoping y permisos aplicados segun contrato.
- [ ] DoR validado antes de iniciar.
- [ ] DoD validado antes de READY_FOR_VALIDATION.

## 7) Smoke manual sugerido (usuario)

1) Levantar backend local
- ./mvnw test
- ./mvnw spring-boot:run

2) Validar flujo principal de SPR-B011
- Subir adjunto permitido y validar metadata DB.
- Intentar adjunto >10MB o tipo no permitido y validar rechazo.
- Descargar adjunto con auth+scoping correcto.

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
- **Sprint previo de continuidad:** SPR-B010

## 10) Regla anti-desvio

- No inventar requisitos, endpoints o arquitectura fuera de master/BRD/docs canonicos.
- Si hay cambios de contrato: RFC/ADR + changelog antes de seguir.
- Si bloquea el sprint: registrar BLOCKED en status/log durante ejecucion.

<!-- EOF -->
