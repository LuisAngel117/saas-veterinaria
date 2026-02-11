# SPR-B016 - Facturación: factura desde atención + IVA + descuentos + pagos

**Estado:** NOT_STARTED  
**Stage:** 16  
**Duracion objetivo:** 45-90 min (referencial)  
**Tipo:** Backend  

## 1) Objetivo

Cerrar exactamente el alcance de SPR-B016 segun el master BACK aceptado, sin ampliar scope y respetando contratos vigentes del proyecto.

- **Cierra (master):** BRD-REQ-017
- **Entrega (master):** invoice desde atención cerrada, IVA global configurable (solo superadmin, reason+audit), descuentos, pagos parciales/mixtos.
- **Excluye (master):** export y anulación (sprint siguiente).

**BRD-REQ objetivo (detalle):**
- BRD-REQ-017: Facturación: factura desde atención, IVA global configurable (default 15%), descuentos ítem/global, pagos mixtos/parciales.

## 2) Cierre de alcance (sin inventar)

### Incluye
- invoice desde atención cerrada.
- IVA global configurable (solo superadmin.
- reason+audit).
- descuentos.
- pagos parciales/mixtos.
- Solo lo definido por master + BRD para este sprint.

### Excluye
- export y anulación (sprint siguiente).
- Cualquier funcionalidad no listada en Entrega del master.

## 3) Contexto tecnico alineado

**Scoping del sprint:**
- Branch-scoped (invoice/payment) + global (settings_global tax).
- Si aplica branch-scoped: validar JWT branch_id + header X-Branch-Id segun contrato (400/403 en mismatch/falta).

**Entidades principales involucradas:**
- invoice
- invoice_item
- payment
- settings_global

**Permisos minimos (docs/10-permisos.md):**
- INVOICE_VIEW
- INVOICE_CREATE
- INVOICE_EDIT
- PAYMENT_CREATE
- INVOICE_PRICE_OVERRIDE
- SETTINGS_TAX_UPDATE

**Acciones sensibles (reason required):**
- INVOICE_PRICE_OVERRIDE (reason required)
- SETTINGS_TAX_UPDATE (reason required, solo SUPERADMIN)

## 4) Pre-check obligatorio (DoR)

- Verificar identidad de repo con docs/project-lock.md (remote + branch).
- Verificar existencia de este sprint: docs/sprints/spr-b016.md
- Releer docs/state/state.md, DoR, DoD, BRD, arquitectura, seguridad, permisos y dominio antes de implementar.
- Si hay hueco de contrato, contradiccion o permiso faltante: abrir RFC/ADR y detener.

## 5) Entregables esperados

### Backend
- invoice desde atención cerrada.
- IVA global configurable (solo superadmin.
- reason+audit).
- descuentos.
- pagos parciales/mixtos.

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
- [ ] Implementado: invoice desde atención cerrada.
- [ ] Implementado: IVA global configurable (solo superadmin.
- [ ] Implementado: reason+audit).
- [ ] Implementado: descuentos.
- [ ] Implementado: pagos parciales/mixtos.
- [ ] BRD-REQ objetivo cubierto: BRD-REQ-017
- [ ] Exclusion respetada: export y anulación (sprint siguiente).
- [ ] Scoping y permisos aplicados segun contrato.
- [ ] DoR validado antes de iniciar.
- [ ] DoD validado antes de READY_FOR_VALIDATION.

## 7) Smoke manual sugerido (usuario)

1) Levantar backend local
- ./mvnw test
- ./mvnw spring-boot:run

2) Validar flujo principal de SPR-B016
- Generar invoice desde atencion cerrada.
- Registrar pagos parciales/mixtos y transicion de estado.
- Cambiar IVA global con superadmin + reason + audit.

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
- **Sprint previo de continuidad:** SPR-B015

## 10) Regla anti-desvio

- No inventar requisitos, endpoints o arquitectura fuera de master/BRD/docs canonicos.
- Si hay cambios de contrato: RFC/ADR + changelog antes de seguir.
- Si bloquea el sprint: registrar BLOCKED en status/log durante ejecucion.

<!-- EOF -->
