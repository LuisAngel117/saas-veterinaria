# SPR-B017 - Factura: export + anulación

**Estado:** NOT_STARTED  
**Stage:** 17  
**Duracion objetivo:** 45-90 min (referencial)  
**Tipo:** Backend  

## 1) Objetivo

Cerrar exactamente el alcance de SPR-B017 segun el master BACK aceptado, sin ampliar scope y respetando contratos vigentes del proyecto.

- **Cierra (master):** BRD-REQ-018
- **Entrega (master):** export CSV/PDF, annul (status ANNULLED) con reason + before/after, auditoría.
- **Excluye (master):** e-factura real.

**BRD-REQ objetivo (detalle):**
- BRD-REQ-018: Factura: anulación (reason+before/after) + export CSV/PDF.

## 2) Cierre de alcance (sin inventar)

### Incluye
- export CSV/PDF.
- annul (status ANNULLED) con reason + before/after.
- auditoría.
- Solo lo definido por master + BRD para este sprint.

### Excluye
- e-factura real.
- Cualquier funcionalidad no listada en Entrega del master.

## 3) Contexto tecnico alineado

**Scoping del sprint:**
- Branch-scoped (invoice export + annul).
- Si aplica branch-scoped: validar JWT branch_id + header X-Branch-Id segun contrato (400/403 en mismatch/falta).

**Entidades principales involucradas:**
- invoice
- invoice_item
- audit_event

**Permisos minimos (docs/10-permisos.md):**
- INVOICE_VIEW
- INVOICE_ANNUL
- REPORT_EXPORT
- Nota de cierre: si se decide separar un permiso dedicado para export de factura (distinto de `REPORT_EXPORT`), debe formalizarse por RFC.

**Acciones sensibles (reason required):**
- INVOICE_ANNUL (reason required + before/after)

## 4) Pre-check obligatorio (DoR)

- Verificar identidad de repo con docs/project-lock.md (remote + branch).
- Verificar existencia de este sprint: docs/sprints/spr-b017.md
- Releer docs/state/state.md, DoR, DoD, BRD, arquitectura, seguridad, permisos y dominio antes de implementar.
- Si hay hueco de contrato, contradiccion o permiso faltante: abrir RFC/ADR y detener.

## 5) Entregables esperados

### Backend
- export CSV/PDF.
- annul (status ANNULLED) con reason + before/after.
- auditoría.

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
- [ ] Implementado: export CSV/PDF.
- [ ] Implementado: annul (status ANNULLED) con reason + before/after.
- [ ] Implementado: auditoría.
- [ ] BRD-REQ objetivo cubierto: BRD-REQ-018
- [ ] Exclusion respetada: e-factura real.
- [ ] Scoping y permisos aplicados segun contrato.
- [ ] DoR validado antes de iniciar.
- [ ] DoD validado antes de READY_FOR_VALIDATION.

## 7) Smoke manual sugerido (usuario)

1) Levantar backend local
- ./mvnw test
- ./mvnw spring-boot:run

2) Validar flujo principal de SPR-B017
- Exportar factura CSV/PDF.
- Anular factura con reason y validar estado ANNULLED.
- Verificar auditoria before/after de anulacion.

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
- **Sprint previo de continuidad:** SPR-B016

## 10) Regla anti-desvio

- No inventar requisitos, endpoints o arquitectura fuera de master/BRD/docs canonicos.
- Si hay cambios de contrato: RFC/ADR + changelog antes de seguir.
- Si bloquea el sprint: registrar BLOCKED en status/log durante ejecucion.

<!-- EOF -->
