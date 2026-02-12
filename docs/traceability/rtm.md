# RTM — Requirements Traceability Matrix

Regla: todo `BRD-REQ-###` debe mapear a sprint(s) o quedar RFC/BLOCKED.

| BRD-REQ | Descripción | Sprint(s) | Evidencia (commit) | Verificación | Estado | Notas |
|---|---|---|---|---|---|---|
| BRD-REQ-001 | Auth login/refresh/logout | SPR-B001, SPR-F001 | e13a559bb19290859047da4ed647fd6b00f29b68 | smoke + manual | READY_FOR_VALIDATION | Implementado en SPR-B001 |
| BRD-REQ-002 | Branch selection + scoping claim/header | SPR-B001, SPR-F001 | e13a559bb19290859047da4ed647fd6b00f29b68 | smoke | READY_FOR_VALIDATION | Implementado en SPR-B001 |
| BRD-REQ-003 | Roles/permisos por acción | SPR-B002, SPR-F002 | ddb0194be186b8c827a6510bc1475944aeb790d7 | tests + manual | READY_FOR_VALIDATION | Implementado en SPR-B002 |
| BRD-REQ-004 | 2FA TOTP admin | SPR-B003 | TBD | manual | NOT_STARTED |  |
| BRD-REQ-005 | Auditoría before/after | SPR-B004 | TBD | manual | NOT_STARTED |  |
| BRD-REQ-006 | Branch/rooms/vets config | SPR-B005 | TBD | manual | NOT_STARTED |  |
| BRD-REQ-007 | Agenda CRUD + semana + no-solape sala+vet | SPR-B006, SPR-F003 | TBD | smoke + manual | NOT_STARTED |  |
| BRD-REQ-008 | Acciones cita (confirm/cancel/checkin/start/close) | SPR-B007, SPR-F004 | TBD | manual | NOT_STARTED |  |
| BRD-REQ-009 | CRM clientes + consentimientos | SPR-B008, SPR-F005 | TBD | manual | NOT_STARTED |  |
| BRD-REQ-010 | Mascotas + internal_code único | SPR-B009, SPR-F006 | TBD | manual | NOT_STARTED |  |
| BRD-REQ-011 | Atenciones SOAP + plantillas + adjuntos | SPR-B010,B011; SPR-F007,F008 | TBD | manual | NOT_STARTED |  |
| BRD-REQ-012 | Cierre/reapertura HC con reason | SPR-B012, SPR-F009 | TBD | manual | NOT_STARTED |  |
| BRD-REQ-013 | Catálogo servicios | SPR-B013, SPR-F010 | TBD | manual | NOT_STARTED |  |
| BRD-REQ-014 | Prescripción estructurada + export | SPR-B013, SPR-F010 | TBD | manual | NOT_STARTED |  |
| BRD-REQ-015 | Inventario base + movimientos + alertas | SPR-B014, SPR-F011 | TBD | manual | NOT_STARTED |  |
| BRD-REQ-016 | BOM + consumo automático | SPR-B013,B015; SPR-F010 | TBD | smoke + manual | NOT_STARTED |  |
| BRD-REQ-017 | Facturación + IVA + descuentos + pagos | SPR-B016, SPR-F012 | TBD | manual | NOT_STARTED |  |
| BRD-REQ-018 | Anulación + export factura | SPR-B017, SPR-F013 | TBD | manual | NOT_STARTED |  |
| BRD-REQ-019 | Runbook + scripts verdad + smoke | SPR-B001..RC001; SPR-F001..RC002 | TBD | smoke | NOT_STARTED |  |
| BRD-REQ-020 | Seeds/demo 2–3 min | SPR-B001 (+ seeds en varios) | e13a559bb19290859047da4ed647fd6b00f29b68 | manual | READY_FOR_VALIDATION | Implementado en SPR-B001 |
| BRD-REQ-021 | OpenAPI/Swagger | SPR-B001 + mantenimiento | e13a559bb19290859047da4ed647fd6b00f29b68 | manual | READY_FOR_VALIDATION | Implementado en SPR-B001 |
| BRD-REQ-022 | Errores RFC7807 Problem Details | SPR-B001 + estándar | e13a559bb19290859047da4ed647fd6b00f29b68 | tests | READY_FOR_VALIDATION | Implementado en SPR-B001 |
| BRD-REQ-023 | Reportes mínimos + export | SPR-B018, SPR-F014 | TBD | manual | NOT_STARTED |  |
| BRD-REQ-024 | Dashboard por rol | SPR-F014 | TBD | manual | NOT_STARTED |  |
| BRD-REQ-025 | UX vendible (roles, validaciones, mensajes) | SPR-F001..F015 | TBD | manual | NOT_STARTED |  |
| BRD-REQ-026 | Recordatorios online-only placeholder | TBD | TBD | N/A | NOT_STARTED | feature flag |
| BRD-REQ-027 | E-factura SRI placeholder | TBD | TBD | N/A | NOT_STARTED | feature flag |

<!-- EOF -->
