# SPR-MASTER — BACKEND (DRAFT)

**Estado:** DRAFT (no ejecutar sprints hasta aceptación explícita del usuario)  
**Regla:** una vez aceptado (“Acepto el plan maestro BACK tal cual”), queda congelado; cambios solo por RFC/ADR/CHANGELOG.

## 1) Objetivo del plan
Cerrar P0 del BRD con incrementos integrados y trazables (RTM + LOG/STATUS), manteniendo offline-first real.

## 2) Sprints backend (propuestos)

### SPR-B001 — Walking Skeleton (auth + scoping + seed mínimo + 1 vertical slice)
- Cierra: BRD-REQ-001, BRD-REQ-002, BRD-REQ-020, BRD-REQ-021, BRD-REQ-022
- Entrega: health, Flyway baseline, login/refresh/me, validación X-Branch-Id vs claim, seeds demo users+branches+sala, Problem Details base, smoke auth+scoping.
- Excluye: dominio completo, UI.
- Dependencias: T1–T4 docs listos.

### SPR-B002 — RBAC base (roles/permisos por acción)
- Cierra: BRD-REQ-003
- Entrega: modelo permisos, guards por endpoint, códigos de permisos estables, seeds roles/perms.
- Excluye: 2FA, auditoría before/after completa.

### SPR-B003 — 2FA TOTP (ADMIN/SUPERADMIN)
- Cierra: BRD-REQ-004
- Entrega: enrolamiento/validación TOTP, flujo login con challenge, recovery codes (opcional v1, si se define).
- Excluye: UI; solo API/contrato.

### SPR-B004 — Auditoría framework (eventos + before/after en sensibles)
- Cierra: BRD-REQ-005
- Entrega: audit_event persistente, helper para before/after, action_codes, reason-required enforcement, retención demo (job TBD si aplica).
- Excluye: reportes de auditoría avanzados.

### SPR-B005 — Config operativa (branches/rooms/vets profiles)
- Cierra: BRD-REQ-006
- Entrega: endpoints/servicios para salas y perfil veterinario (branch-scoped), validaciones, seeds completos.
- Excluye: agenda.

### SPR-B006 — Agenda core (CRUD citas + no-solape sala+vet + override)
- Cierra: BRD-REQ-007
- Entrega: appointment CRUD, verificación de solape por sala+vet, respuesta 409, override con permiso+reason+audit.
- Excluye: acciones de estado (confirm/checkin/start/close) si se separan.

### SPR-B007 — Workflow de cita (transiciones + check-in separado)
- Cierra: BRD-REQ-008 (y completa BRD-REQ-007)
- Entrega: endpoints de transición (confirm/cancel/check-in/start/close) con validación de estado, auditoría, mensajes claros.
- Excluye: clínica SOAP.

### SPR-B008 — CRM clientes + consentimientos
- Cierra: BRD-REQ-009
- Entrega: CRUD clientes, consentimiento trazable (campos cerrados), búsqueda/paginación básica.
- Excluye: mascotas.

### SPR-B009 — Mascotas/pacientes
- Cierra: BRD-REQ-010
- Entrega: CRUD mascotas, internal_code único por branch, alertas/antecedentes.
- Excluye: clínica SOAP.

### SPR-B010 — Clínica: atenciones SOAP + plantillas por servicio
- Cierra: BRD-REQ-011 (parcial: SOAP+templates)
- Entrega: CRUD atenciones, SOAP campos cerrados, plantillas por servicio, vínculo opcional a cita.
- Excluye: adjuntos (siguiente sprint).

### SPR-B011 — Adjuntos de historia clínica
- Cierra: BRD-REQ-011 (adjuntos)
- Entrega: storage local + metadata DB, upload/download protegido, límite 10MB, tipos permitidos.
- Excluye: export a PDF de historia (si no está en alcance v1).

### SPR-B012 — Cierre/reapertura gobernada (HC)
- Cierra: BRD-REQ-012
- Entrega: close HC, reopen HC (admin/superadmin), permiso opcional vet, reason+audit, bloqueo edición post-cierre.
- Excluye: flujos UI.

### SPR-B013 — Catálogo servicios + prescripciones + BOM
- Cierra: BRD-REQ-013, BRD-REQ-014, BRD-REQ-016 (estructura BOM)
- Entrega: servicios CRUD (precio/duración sugerida), estructura prescripción, BOM por servicio.
- Excluye: consumo automático en cierre (sprint dedicado).

### SPR-B014 — Inventario base (productos/unidades/stock/movimientos/alertas)
- Cierra: BRD-REQ-015
- Entrega: productos CRUD, unidades catálogo, stock por branch, movimientos (IN/OUT/ADJUST), mínimos/alertas, override con reason+audit.
- Excluye: consumo automático.

### SPR-B015 — Consumo automático por BOM al cerrar atención
- Cierra: BRD-REQ-016
- Entrega: al cerrar atención, consumir stock según BOM; manejo stock insuficiente (block/override), auditoría.
- Excluye: facturación.

### SPR-B016 — Facturación: factura desde atención + IVA + descuentos + pagos
- Cierra: BRD-REQ-017
- Entrega: invoice desde atención cerrada, IVA global configurable (solo superadmin, reason+audit), descuentos, pagos parciales/mixtos.
- Excluye: export y anulación (sprint siguiente).

### SPR-B017 — Factura: export + anulación
- Cierra: BRD-REQ-018
- Entrega: export CSV/PDF, void con reason + before/after, auditoría.
- Excluye: e-factura real.

### SPR-B018 — Reportes mínimos (backend)
- Cierra: BRD-REQ-023
- Entrega: endpoints reportes (citas, ventas, top servicios, consumo inventario, frecuentes) + export CSV/PDF.
- Excluye: dashboards complejos.

### SPR-B019 — Hardening (validaciones, constraints, limpieza, performance básica)
- Cierra: BRD-REQ-019 (parcial: smoke), BRD-REQ-022 (consistencia), NFR
- Entrega: constraints DB, validaciones, mensajes error consistentes, rate-limit/lockout refinado si aplica, smoke scripts ampliados.

### SPR-RC001 — Release Candidate local (backend)
- Cierra: BRD-REQ-019, BRD-REQ-021
- Entrega: scripts smoke/release-candidate, runbook validado, checklist entrega, evidencia completa en LOG/STATUS/RTM/state.

## 3) Regla de aceptación
Para congelar este master, el usuario debe responder exactamente:
- “Acepto el plan maestro BACK tal cual”

<!-- EOF -->
