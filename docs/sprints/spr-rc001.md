# SPR-RC001 - Release Candidate local (backend)

**Estado:** NOT_STARTED  
**Stage:** RC001  
**Duracion objetivo:** 45-90 min (referencial)  
**Tipo:** Backend  

## 1) Objetivo

Cerrar el alcance de SPR-RC001 segun docs/sprints/spr-master-back.md, sin ampliar scope y respetando contratos del proyecto.

- **Cierra (master):** BRD-REQ-019, BRD-REQ-021
- **Entrega (master):** scripts smoke/release-candidate, runbook validado, checklist entrega, evidencia completa en LOG/STATUS/RTM/state.
- **Excluye (master):** No especificado en master.

**BRD-REQ objetivo (detalle):**
- BRD-REQ-019: Runbook local + scripts “verdad” + smoke scripts por flujo crítico.
- BRD-REQ-021: OpenAPI/Swagger actualizado para endpoints implementados.

## 2) Alcance

### Incluye
- scripts smoke/release-candidate.
- runbook validado.
- checklist entrega.
- evidencia completa en LOG/STATUS/RTM/state.
- Mantener compatibilidad con docs/project-lock.md y arquitectura vigente.
- Mantener trazabilidad completa en LOG/STATUS/RTM/state cuando se ejecute implementacion.

### Excluye
- No especificado en master.
- Cualquier funcionalidad no listada en Entrega del master.

## 3) Pre-check (obligatorio para Codex)

- Verificar git remote -v y branch contra docs/project-lock.md.
- Verificar que existe este sprint: docs/sprints/spr-rc001.md
- Leer docs/state/state.md, DoR, DoD, docs/02-brd.md, docs/03-arquitectura.md, docs/05-seguridad.md, docs/10-permisos.md.
- Si DoR falla o hay contradiccion contractual: crear RFC/ADR y detener.

## 4) Entregables

### Codigo (backend)
- scripts smoke/release-candidate.
- runbook validado.
- checklist entrega.
- evidencia completa en LOG/STATUS/RTM/state.

### Pruebas y smoke
- Definir pruebas de casos positivos y negativos del alcance del sprint.
- Si existe script de smoke para el sprint, ejecutarlo y registrar output; si no existe, registrar N/A con razon.

### Documentacion y trazabilidad
- Actualizar docs/log/log.md (append-only) con comandos y output.
- Actualizar docs/status/status.md (READY_FOR_VALIDATION cuando corresponda; nunca DONE por Codex).
- Actualizar docs/traceability/rtm.md para los BRD-REQ del sprint con evidencia de commit.
- Actualizar docs/state/state.md con snapshot y siguiente sprint recomendado.

## 5) Instrucciones de implementacion (cerradas)

> No ampliar scope. Cualquier cambio de contrato requiere RFC/ADR/CHANGELOG.

### 5.1 Contratos transversales
- Respetar scoping por branch, permisos por accion y auditoria segun documentos canonicos.
- Mantener consistencia de errores API (Problem Details) cuando aplique al flujo.

### 5.2 Implementacion especifica del sprint
- Implementar: scripts smoke/release-candidate.
- Implementar: runbook validado.
- Implementar: checklist entrega.
- Implementar: evidencia completa en LOG/STATUS/RTM/state.

### 5.3 Exclusiones obligatorias
- No implementar: No especificado en master.
- Si algo de la exclusion se vuelve necesario, abrir RFC antes de continuar.

## 6) Criterios de aceptacion (AC)

- [ ] Scope del sprint coincide exactamente con Entrega del master.
- [ ] Implementar: scripts smoke/release-candidate.
- [ ] Implementar: runbook validado.
- [ ] Implementar: checklist entrega.
- [ ] Implementar: evidencia completa en LOG/STATUS/RTM/state.
- [ ] BRD-REQ objetivo cubierto: BRD-REQ-019, BRD-REQ-021
- [ ] Exclusion respetada: No especificado en master.
- [ ] DoR validado antes de iniciar implementacion.
- [ ] DoD validado antes de marcar READY_FOR_VALIDATION.

## 7) Smoke test manual (usuario)

1) Levantar backend local.
- ./mvnw test
- ./mvnw spring-boot:run

2) Ejecutar flujo del sprint SPR-RC001
- Cubrir caso positivo principal de cada item de Entrega.
- Cubrir al menos un caso negativo/validacion por item de Entrega, cuando aplique.

3) Evidencia
- Pegar outputs en docs/log/log.md bajo la entrada del sprint.

## 8) Comandos verdad

Backend:
- ./mvnw test
- ./mvnw spring-boot:run

Documentacion:
- pwsh -ExecutionPolicy Bypass -File .\scripts\verify\verify-docs-eof.ps1
- pwsh -ExecutionPolicy Bypass -File .\scripts\verify\preflight.ps1

## 9) DoD

- [ ] AC completos y verificables.
- [ ] LOG actualizado (append-only) con comandos y output.
- [ ] STATUS en READY_FOR_VALIDATION (nunca DONE por Codex).
- [ ] RTM actualizado para BRD-REQ del sprint con evidencia.
- [ ] state snapshot actualizado con next sprint.
- [ ] Cumple docs/quality/definition-of-done.md.

## 10) Dependencias y continuidad

- **Dependencias (master):** No listadas en master.
- **Sprint previo de continuidad:** SPR-B019

## 11) Si hay huecos/contradicciones

- No modificar este sprint para corregir contrato.
- Crear RFC en docs/rfcs/ y ADR en docs/decisions/ cuando aplique.
- Actualizar docs/changelog.md y registrar bloqueo en status/log si corresponde.

<!-- EOF -->
