# Sprint Master — BACK (DRAFT)

Estado: DRAFT (no aceptar aún). Se congela tras revisión final de mapeo BRD-REQ.

## Objetivo
Construir backend funcional local-first/offline-first, con walking skeleton temprano y luego vertical slices integradas.

## Sprints propuestos (orden recomendado)

### SPR-B001 — Walking skeleton: auth + scoping + health + seeds mínimos
- Cierra: BRD-REQ-001..003, 008..011, 013..014, 067, 070 (parcial)
- Incluye: auth básico + JWT + refresh + branch_id claim + header validation + `/actuator/health` + seeds demo base
- Excluye: agenda completa, clínica, facturación
- Riesgo: si se difiere scoping se rompe todo; se hace primero

### SPR-B002 — Seguridad hardening: lockout + password policy + 2FA TOTP + auditoría auth
- Cierra: BRD-REQ-004..006, 058
- Incluye: lockout, password rules, 2FA flows, audit auth
- Excluye: módulos core

### SPR-B003 — Agenda core: salas + veterinarios + citas + no-solape + estados + override
- Cierra: BRD-REQ-018..025, 020..024, 059
- Incluye: CRUD cita, validación no-solape, override (reason), auditoría agenda
- Excluye: UI, clínica

### SPR-B004 — CRM core: clientes + consentimientos + mascotas + código interno único
- Cierra: BRD-REQ-026..030, 027..029, 060
- Incluye: CRUD, constraints, auditoría create/update
- Excluye: clínica

### SPR-B005 — Clínica core: atención + SOAP + cierre/reapertura + adjuntos
- Cierra: BRD-REQ-031..035, 032..034, 061
- Incluye: encounter + soap + templates base + attachments local storage
- Excluye: facturación

### SPR-B006 — Catálogo: servicios + prescripción + impresión base (HTML) + BOM
- Cierra: BRD-REQ-036..038, 044
- Incluye: service CRUD + BOM + prescription model
- Excluye: consumo automático

### SPR-B007 — Inventario: productos/unidades + stock/movimientos + mínimos + costeo promedio
- Cierra: BRD-REQ-039..042, 063
- Incluye: movimientos IN/OUT/ADJUST, avg_cost
- Excluye: bloqueo facturar por stock (va con billing/consumo)

### SPR-B008 — Consumo automático + bloqueo por stock + override negativo
- Cierra: BRD-REQ-043, 045
- Incluye: consumo BOM al cerrar atención, validación stock con override
- Excluye: reportes

### SPR-B009 — Facturación: factura por atención + IVA configurable + descuentos + pagos + anulación + exports
- Cierra: BRD-REQ-046..051, 047..050, 062
- Incluye: invoice/items/payments, tax setting, audit before/after
- Excluye: SRI real

### SPR-B010 — Reportes + export CSV/PDF + dashboard endpoints
- Cierra: BRD-REQ-052..057
- Incluye: queries por período, agregaciones
- Excluye: BI avanzado

### SPR-B011 — Online-only placeholders (feature flags): recordatorios + SRI structure
- Cierra: BRD-REQ-065..066
- Incluye: tablas/config + flags; sin llamadas externas

### SPR-B012 — RC backend: smoke scripts completos + runbook real + hardening final
- Cierra: BRD-REQ-069..070 (completo) + NFR cobertura
- Incluye: scripts smoke, preflight ampliado (si aplica), docs final

<!-- EOF -->
