# Requirements Traceability Matrix (RTM)

Regla: todo `BRD-REQ-###` en `docs/02-brd.md` debe mapear a sprint(s) o quedar RFC/BLOCKED.

| BRD-REQ | Descripcion | Sprint(s) | Evidencia (commit) | Verificacion | Estado | Notas |
|---|---|---|---|---|---|---|
| BRD-REQ-001 | Login + refresh + lockout | SPR-B001, SPR-F001 | PENDING_T2_COMMIT | verify-docs-eof + preflight | READY_FOR_VALIDATION | Base documental cerrada en T2 |
| BRD-REQ-002 | 2FA TOTP para ADMIN/SUPERADMIN | SPR-B001, SPR-F001 | PENDING_T2_COMMIT | verify-docs-eof + preflight | READY_FOR_VALIDATION | Definido en seguridad |
| BRD-REQ-003 | Scoping branch JWT + header | SPR-B001, SPR-F001 | PENDING_T2_COMMIT | verify-docs-eof + preflight | READY_FOR_VALIDATION | Reglas 400/403 definidas |
| BRD-REQ-004 | Roles base y permisos por accion | SPR-B008, SPR-F008 | PENDING_T2_COMMIT | verify-docs-eof + preflight | READY_FOR_VALIDATION | Matriz inicial en docs/10 |
| BRD-REQ-005 | Agenda sin solape sala+vet | SPR-B002, SPR-F002 | PENDING_T2_COMMIT | verify-docs-eof + preflight | READY_FOR_VALIDATION | Slot 30m + buffer 10m |
| BRD-REQ-006 | Check-in separado de cita | SPR-B002, SPR-F002 | PENDING_T2_COMMIT | verify-docs-eof + preflight | READY_FOR_VALIDATION | Regla de flujo agenda |
| BRD-REQ-007 | CRUD clientes/mascotas | SPR-B003, SPR-F003 | PENDING_T2_COMMIT | verify-docs-eof + preflight | READY_FOR_VALIDATION | Scoping por sucursal |
| BRD-REQ-008 | Atencion con/sin cita + SOAP | SPR-B004, SPR-F004 | PENDING_T2_COMMIT | verify-docs-eof + preflight | READY_FOR_VALIDATION | Flujo clinico base |
| BRD-REQ-009 | Adjuntos HC + cierre/reapertura | SPR-B004, SPR-F004 | PENDING_T2_COMMIT | verify-docs-eof + preflight | READY_FOR_VALIDATION | Reapertura con permiso |
| BRD-REQ-010 | Servicios con BOM | SPR-B005, SPR-F006 | PENDING_T2_COMMIT | verify-docs-eof + preflight | READY_FOR_VALIDATION | Acopla a inventario |
| BRD-REQ-011 | Inventario por sucursal + costo promedio | SPR-B005, SPR-F006 | PENDING_T2_COMMIT | verify-docs-eof + preflight | READY_FOR_VALIDATION | Movimientos auditables |
| BRD-REQ-012 | Bloqueo por stock + override autorizado | SPR-B005, SPR-F006 | PENDING_T2_COMMIT | verify-docs-eof + preflight | READY_FOR_VALIDATION | Requiere reason |
| BRD-REQ-013 | Facturacion + IVA configurable | SPR-B006, SPR-F005 | PENDING_T2_COMMIT | verify-docs-eof + preflight | READY_FOR_VALIDATION | IVA default 15% |
| BRD-REQ-014 | Pagos mixtos/parciales | SPR-B006, SPR-F005 | PENDING_T2_COMMIT | verify-docs-eof + preflight | READY_FOR_VALIDATION | Manejo de saldo |
| BRD-REQ-015 | Reportes minimos + export | SPR-B007, SPR-F007 | PENDING_T2_COMMIT | verify-docs-eof + preflight | READY_FOR_VALIDATION | CSV/PDF |
| BRD-REQ-016 | Reason required + auditoria sensible | SPR-B008, SPR-F008 | PENDING_T2_COMMIT | verify-docs-eof + preflight | READY_FOR_VALIDATION | Before/after obligatorio |
| BRD-REQ-017 | Problem Details RFC 7807 | SPR-B008, SPR-F008 | PENDING_T2_COMMIT | verify-docs-eof + preflight | READY_FOR_VALIDATION | Contrato de errores |
| BRD-REQ-018 | Feature flags online-only | SPR-B008, SPR-F008 | PENDING_T2_COMMIT | verify-docs-eof + preflight | READY_FOR_VALIDATION | Placeholder controlado |

<!-- EOF -->
