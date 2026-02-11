# SPR-MASTER — FRONTEND (DRAFT)

**Estado:** DRAFT (no ejecutar sprints hasta aceptación explícita del usuario)  
**Regla:** una vez aceptado (“Acepto el plan maestro FRONT tal cual”), queda congelado; cambios solo por RFC/ADR/CHANGELOG.

## 1) Objetivo del plan
Cerrar P0 del BRD con flujos integrados reales contra backend (sin pantallas sueltas), UX en español y lista para demo.

## 2) Sprints frontend (propuestos)

### SPR-F001 — Shell vendible (auth + selector branch + guards + API client)
- Cierra: BRD-REQ-001, BRD-REQ-002, BRD-REQ-022, BRD-REQ-025
- Entrega: login, manejo refresh, selector sucursal post-login, layout, navegación base, manejo errores Problem Details.
- Dependencias: backend al menos SPR-B001 o handoff equivalente.

### SPR-F002 — RBAC UI (roles/permisos visibles)
- Cierra: BRD-REQ-003, BRD-REQ-025
- Entrega: ocultar/deshabilitar acciones según permisos, mensajes claros (“no autorizado”), protección rutas.

### SPR-F003 — Agenda semana + crear/editar cita (sin solape)
- Cierra: BRD-REQ-007, BRD-REQ-025
- Entrega: vista semanal, formulario crear/editar, validaciones, manejo 409 (conflicto) con UX clara.

### SPR-F004 — Acciones de cita (confirm/cancel/check-in/start/close)
- Cierra: BRD-REQ-008
- Entrega: botones por estado, confirmaciones, captura de motivos (cancel), auditoría reason cuando aplique (override).

### SPR-F005 — Clientes (CRM)
- Cierra: BRD-REQ-009
- Entrega: listado + ficha + edición, consentimientos, búsqueda/paginación básica.

### SPR-F006 — Mascotas
- Cierra: BRD-REQ-010
- Entrega: listado por cliente, ficha mascota, validaciones, internal_code.

### SPR-F007 — Atención SOAP + plantillas
- Cierra: BRD-REQ-011, BRD-REQ-025
- Entrega: UI SOAP, aplicar plantilla por servicio, crear atención desde cita o independiente.

### SPR-F008 — Adjuntos historia clínica
- Cierra: BRD-REQ-011
- Entrega: subir/ver/descargar adjuntos, validación tamaño/tipo, feedback UX.

### SPR-F009 — Cierre/reapertura de HC (reason)
- Cierra: BRD-REQ-012
- Entrega: cerrar atención/HC, reapertura (según permisos), captura reason, mensajes.

### SPR-F010 — Catálogo servicios + BOM + prescripción UI
- Cierra: BRD-REQ-013, BRD-REQ-014, BRD-REQ-016
- Entrega: UI gestión servicios, edición BOM, captura prescripciones estructuradas, export (HTML/PDF si backend lo expone).

### SPR-F011 — Inventario (stock, movimientos, alertas, overrides)
- Cierra: BRD-REQ-015
- Entrega: listado productos, stock por branch, movimientos, alertas mínimos, override con reason.

### SPR-F012 — Facturación (desde atención) + pagos
- Cierra: BRD-REQ-017
- Entrega: UI factura desde atención, descuentos, pagos parciales/mixtos, estados.

### SPR-F013 — Factura: export + anulación
- Cierra: BRD-REQ-018
- Entrega: export, anulación con reason, feedback UX, protección permisos.

### SPR-F014 — Reportes + dashboard por rol
- Cierra: BRD-REQ-023, BRD-REQ-024
- Entrega: reportes mínimos + export, home dashboard.

### SPR-F015 — Pulido vendible (a11y/perf/empty states) + “demo path”
- Cierra: BRD-REQ-025, BRD-REQ-020 (demo usabilidad)
- Entrega: estados vacíos, loaders, consistencia visual, recorrido demo 2–3 min (guía en UI o runbook).

### SPR-RC002 — Release Candidate local (frontend)
- Cierra: BRD-REQ-019
- Entrega: build, verificación env, smoke manual de UI, runbook actualizado, evidencia completa.

## 3) Regla de aceptación
Para congelar este master, el usuario debe responder exactamente:
- “Acepto el plan maestro FRONT tal cual”

<!-- EOF -->
