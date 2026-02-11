# SPR-B019 - Hardening (validaciones, constraints, limpieza, performance básica)

**Estado:** NOT_STARTED  
**Stage:** 19  
**Duracion objetivo:** 45-90 min (referencial)  
**Tipo:** Backend  

## 1) Objetivo

Cerrar exactamente el alcance de SPR-B019 segun el master BACK aceptado, sin ampliar scope y respetando contratos vigentes del proyecto.

- **Cierra (master):** BRD-REQ-019 (parcial: smoke), BRD-REQ-022 (consistencia), NFR
- **Entrega (master):** constraints DB, validaciones, mensajes error consistentes, rate-limit/lockout refinado si aplica, smoke scripts ampliados.
- **Excluye (master):** No especificado en master (aplica regla de no ampliar scope).

**BRD-REQ objetivo (detalle):**
- BRD-REQ-019: Runbook local + scripts “verdad” + smoke scripts por flujo crítico.
- BRD-REQ-022: Estándar de errores API: Problem Details (RFC 7807).

## 2) Cierre de alcance (sin inventar)

### Incluye
- constraints DB.
- validaciones.
- mensajes error consistentes.
- rate-limit/lockout refinado si aplica.
- smoke scripts ampliados.
- Solo lo definido por master + BRD para este sprint.

### Excluye
- No hay exclusiones adicionales declaradas en master.
- Cualquier funcionalidad no listada en Entrega del master.

## 3) Contexto tecnico alineado

**Scoping del sprint:**
- Transversal (hardening, validaciones, constraints, smoke).
- Si aplica branch-scoped: validar JWT branch_id + header X-Branch-Id segun contrato (400/403 en mismatch/falta).

**Entidades principales involucradas:**
- constraints DB
- validadores API
- scripts smoke

**Permisos minimos (docs/10-permisos.md):**
- N/A especifico; depende de modulo tocado en hardening.

**Acciones sensibles (reason required):**
- Mantener reason required en acciones sensibles existentes.

## 4) Pre-check obligatorio (DoR)

- Verificar identidad de repo con docs/project-lock.md (remote + branch).
- Verificar existencia de este sprint: docs/sprints/spr-b019.md
- Releer docs/state/state.md, DoR, DoD, BRD, arquitectura, seguridad, permisos y dominio antes de implementar.
- Si hay hueco de contrato, contradiccion o permiso faltante: abrir RFC/ADR y detener.

## 5) Entregables esperados

### Backend
- constraints DB.
- validaciones.
- mensajes error consistentes.
- rate-limit/lockout refinado si aplica.
- smoke scripts ampliados.

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
- [ ] Implementado: constraints DB.
- [ ] Implementado: validaciones.
- [ ] Implementado: mensajes error consistentes.
- [ ] Implementado: rate-limit/lockout refinado si aplica.
- [ ] Implementado: smoke scripts ampliados.
- [ ] BRD-REQ objetivo cubierto: BRD-REQ-019 (parcial: smoke), BRD-REQ-022 (consistencia), NFR
- [ ] Exclusion respetada: no ampliar scope fuera de Entrega.
- [ ] Scoping y permisos aplicados segun contrato.
- [ ] DoR validado antes de iniciar.
- [ ] DoD validado antes de READY_FOR_VALIDATION.

## 7) Smoke manual sugerido (usuario)

1) Levantar backend local
- ./mvnw test
- ./mvnw spring-boot:run

2) Validar flujo principal de SPR-B019
- Ejecutar smoke ampliado end-to-end.
- Verificar mensajes Problem Details consistentes.
- Verificar lockout/rate-limit refinado si aplica.

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
- **Sprint previo de continuidad:** SPR-B018

## 10) Regla anti-desvio

- No inventar requisitos, endpoints o arquitectura fuera de master/BRD/docs canonicos.
- Si hay cambios de contrato: RFC/ADR + changelog antes de seguir.
- Si bloquea el sprint: registrar BLOCKED en status/log durante ejecucion.

<!-- EOF -->
