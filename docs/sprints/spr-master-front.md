# Sprint Master — FRONT (DRAFT)

Estado: DRAFT (no aceptar aún). Se congela tras revisión final de mapeo BRD-REQ y handoff backend.

## Objetivo
Construir frontend vendible con integración real contra backend, priorizando el flujo demo end-to-end.

## Dependencia obligatoria
- Antes de integración real, el FRONT debe basarse en:
  - `docs/handoff/handoff-back-to-front.md` (endpoints reales)

## Sprints propuestos (orden recomendado)

### SPR-F001 — Shell: login + 2FA + selector sucursal + layout + guards + API client
- Cierra: BRD-REQ-001..003, 006..011, 015
- Incluye: manejo sesión, refresh, guardas, base UI en español

### SPR-F002 — Agenda UI (semana): listar + crear/editar + estados + override con reason
- Cierra: BRD-REQ-018..025
- Incluye: calendario semana, formularios, errores 409/403 claros

### SPR-F003 — Clientes + consentimientos UI
- Cierra: BRD-REQ-026..027
- Incluye: lista+detalle+form; consentimientos editables

### SPR-F004 — Mascotas UI + alertas visibles + código interno
- Cierra: BRD-REQ-028..030
- Incluye: alertas en header de mascota/atención

### SPR-F005 — Atenciones + SOAP UI + adjuntos + cierre/reapertura
- Cierra: BRD-REQ-031..035
- Incluye: editor SOAP, bloqueo al cerrar, solicitud reapertura

### SPR-F006 — Servicios + prescripciones + impresión (HTML/PDF)
- Cierra: BRD-REQ-036..038

### SPR-F007 — Facturación UI: factura por atención + descuentos + pagos parciales/mixtos + anulación
- Cierra: BRD-REQ-046..051

### SPR-F008 — Inventario UI: productos + movimientos + mínimos + overrides
- Cierra: BRD-REQ-039..043

### SPR-F009 — Reportes UI + export + dashboard por rol
- Cierra: BRD-REQ-052..057

### SPR-F010 — Pulido vendible: permisos UI, mensajes, accesibilidad base, UX final
- Cierra: NFR/DoD + demo estable

<!-- EOF -->
