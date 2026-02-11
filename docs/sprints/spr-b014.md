# SPR-B014 - Inventario base (productos/unidades/stock/movimientos/alertas)

**Estado:** NOT_STARTED  
**Stage:** 14  
**Duracion objetivo:** 45-90 min (referencial)  
**Tipo:** Backend  

## 1) Objetivo

Cerrar exactamente el alcance de SPR-B014 segun el master BACK aceptado, sin ampliar scope y respetando contratos vigentes del proyecto.

- **Cierra (master):** BRD-REQ-015
- **Entrega (master):** productos CRUD, unidades catálogo, stock por branch, movimientos (IN/OUT/ADJUST), mínimos/alertas, override con reason+audit.
- **Excluye (master):** consumo automático.

**BRD-REQ objetivo (detalle):**
- BRD-REQ-015: Inventario: productos + unidades + stock + movimientos + mínimos/alertas + overrides auditados.

## 2) Cierre de alcance (sin inventar)

### Incluye
- productos CRUD.
- unidades catálogo.
- stock por branch.
- movimientos (IN/OUT/ADJUST).
- mínimos/alertas.
- override con reason+audit.
- Solo lo definido por master + BRD para este sprint.

### Excluye
- consumo automático.
- Cualquier funcionalidad no listada en Entrega del master.

## 3) Contexto tecnico alineado

**Scoping del sprint:**
- Branch-scoped (inventory).
- Si aplica branch-scoped: validar JWT branch_id + header X-Branch-Id segun contrato (400/403 en mismatch/falta).

**Entidades principales involucradas:**
- product
- unit
- stock_balance
- stock_movement

**Permisos minimos (docs/10-permisos.md):**
- INVENTORY_VIEW
- INVENTORY_PRODUCT_CREATE
- INVENTORY_PRODUCT_EDIT
- INVENTORY_MOVEMENT_IN
- INVENTORY_MOVEMENT_OUT
- INVENTORY_ADJUST
- INVENTORY_OVERRIDE_NEGATIVE

**Acciones sensibles (reason required):**
- INVENTORY_ADJUST (reason required)
- INVENTORY_OVERRIDE_NEGATIVE (reason required)

## 4) Pre-check obligatorio (DoR)

- Verificar identidad de repo con docs/project-lock.md (remote + branch).
- Verificar existencia de este sprint: docs/sprints/spr-b014.md
- Releer docs/state/state.md, DoR, DoD, BRD, arquitectura, seguridad, permisos y dominio antes de implementar.
- Si hay hueco de contrato, contradiccion o permiso faltante: abrir RFC/ADR y detener.

## 5) Entregables esperados

### Backend
- productos CRUD.
- unidades catálogo.
- stock por branch.
- movimientos (IN/OUT/ADJUST).
- mínimos/alertas.
- override con reason+audit.

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
- [ ] Implementado: productos CRUD.
- [ ] Implementado: unidades catálogo.
- [ ] Implementado: stock por branch.
- [ ] Implementado: movimientos (IN/OUT/ADJUST).
- [ ] Implementado: mínimos/alertas.
- [ ] Implementado: override con reason+audit.
- [ ] BRD-REQ objetivo cubierto: BRD-REQ-015
- [ ] Exclusion respetada: consumo automático.
- [ ] Scoping y permisos aplicados segun contrato.
- [ ] DoR validado antes de iniciar.
- [ ] DoD validado antes de READY_FOR_VALIDATION.

## 7) Smoke manual sugerido (usuario)

1) Levantar backend local
- ./mvnw test
- ./mvnw spring-boot:run

2) Validar flujo principal de SPR-B014
- Registrar movimientos IN/OUT/ADJUST y validar stock resultante.
- Validar alertas por min_stock.
- Validar override negativo solo con permiso+reason+audit.

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
- **Sprint previo de continuidad:** SPR-B013

## 10) Regla anti-desvio

- No inventar requisitos, endpoints o arquitectura fuera de master/BRD/docs canonicos.
- Si hay cambios de contrato: RFC/ADR + changelog antes de seguir.
- Si bloquea el sprint: registrar BLOCKED en status/log durante ejecucion.

<!-- EOF -->
