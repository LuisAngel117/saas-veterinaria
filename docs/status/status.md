# Status (tabla de control)

Estados permitidos: NOT_STARTED | IN_PROGRESS | READY_FOR_VALIDATION | DONE | BLOCKED  
Regla: DONE solo tras validación local del usuario con evidencia en `docs/log/log.md`.

| Sprint/Tanda | Título | Estado | Evidencia (commit) | Notas |
|---|---|---|---|---|
| T1 | Bootstrap docs/scripts + project-lock | READY_FOR_VALIDATION | TBD | Ver LOG para evidencia y comandos |
| T2 | Docs base reales + contratos | READY_FOR_VALIDATION | TBD | BRD/arquitectura/seguridad/dominio/UX/runbook |
| T3 | Governance + ADR/RFC + AGENTS + coherencia agenda | READY_FOR_VALIDATION | TBD | DoR/DoD + ADRs + fix agenda |
| T4 | BRD-REQ + RTM + state + masters BACK/FRONT (DRAFT) | READY_FOR_VALIDATION | TBD | Pendiente aceptación textual de masters |
| AUDIT-BACK-001 | Resumen técnico + auditoría de huecos/inconsistencias plan maestro BACK | READY_FOR_VALIDATION | 2f8bd240eb75ba4d4a73baa796ee2f8bfafca4ee | Documento: docs/state/audit-back-master-001.md |
| AUDIT-FRONT-001 | Resumen técnico + auditoría de huecos/inconsistencias plan maestro FRONT | READY_FOR_VALIDATION | 601268f57c08d7e6bb2fad07780c31770903b1c7 | Documento: docs/state/audit-front-master-001.md |
| T5 | RFC normalizaciones (estados + project-lock) + aceptación master BACK | READY_FOR_VALIDATION | 8f266c314753043d603e7a4c7513358ee18cc4d7 | RFC-0002/RFC-0003 + lock canónico + master BACK ACCEPTED |
| SPR-B001 | Walking Skeleton Backend: Auth + Branch Scoping + Seeds + OpenAPI + Problem Details | NOT_STARTED |  | BRD-REQ-001,002,020,021,022 |
| SPR-B002 | RBAC base (roles/permisos por accion) | NOT_STARTED |  | BRD-REQ-003 |
| SPR-B003 | 2FA TOTP (ADMIN/SUPERADMIN) | NOT_STARTED |  | BRD-REQ-004 |
| SPR-B004 | Auditoria framework (eventos + before/after en sensibles) | NOT_STARTED |  | BRD-REQ-005 |
| SPR-B005 | Config operativa (branches/rooms/vets profiles) | NOT_STARTED |  | BRD-REQ-006 |
| SPR-B006 | Agenda core (CRUD citas + no-solape sala+vet + override) | NOT_STARTED |  | BRD-REQ-007 |
| SPR-B007 | Workflow de cita (transiciones + check-in separado) | NOT_STARTED |  | BRD-REQ-008 (completa BRD-REQ-007) |
| SPR-B008 | CRM clientes + consentimientos | NOT_STARTED |  | BRD-REQ-009 |
| SPR-B009 | Mascotas/pacientes | NOT_STARTED |  | BRD-REQ-010 |
| SPR-B010 | Clinica: atenciones SOAP + plantillas por servicio | NOT_STARTED |  | BRD-REQ-011 (parcial: SOAP+templates) |
| SPR-B011 | Adjuntos de historia clinica | NOT_STARTED |  | BRD-REQ-011 (adjuntos) |
| SPR-B012 | Cierre/reapertura gobernada (HC) | NOT_STARTED |  | BRD-REQ-012 |
| SPR-B013 | Catalogo servicios + prescripciones + BOM | NOT_STARTED |  | BRD-REQ-013,014,016 (estructura BOM) |
| SPR-B014 | Inventario base (productos/unidades/stock/movimientos/alertas) | NOT_STARTED |  | BRD-REQ-015 |
| SPR-B015 | Consumo automatico por BOM al cerrar atencion | NOT_STARTED |  | BRD-REQ-016 |
| SPR-B016 | Facturacion: factura desde atencion + IVA + descuentos + pagos | NOT_STARTED |  | BRD-REQ-017 |
| SPR-B017 | Factura: export + anulacion | NOT_STARTED |  | BRD-REQ-018 |
| SPR-B018 | Reportes minimos (backend) | NOT_STARTED |  | BRD-REQ-023 |
| SPR-B019 | Hardening (validaciones, constraints, limpieza, performance basica) | NOT_STARTED |  | BRD-REQ-019 (parcial), BRD-REQ-022, NFR |
| SPR-RC001 | Release Candidate local (backend) | NOT_STARTED |  | BRD-REQ-019, BRD-REQ-021 |
| CATALOG-BACK-001 | Catalogo canonico sprints BACK + validacion secuencia + cruce status | READY_FOR_VALIDATION | TBD | Documento: docs/state/catalog-back-sprints-001.md |
| DOCS-BACK-SPRINTS-001 | Creacion de sprints BACK faltantes (SPR-B007..SPR-B019 y SPR-RC001) | READY_FOR_VALIDATION | TBD | Documentos en docs/sprints + status/log/changelog actualizados |
| DOCS-BACK-SPRINTS-002 | Completa SPR-B002..SPR-B006 y corrige plantilla en SPR-B007..SPR-B019/SPR-RC001 | READY_FOR_VALIDATION | TBD | Documentos en docs/sprints + status/log/changelog actualizados |
| DOCS-BACK-SPRINTS-003 | Aumenta detalle de SPR-B002..SPR-B019 y SPR-RC001 con plantilla extendida alineada a master+BRD | READY_FOR_VALIDATION | TBD | Solo documentacion; sin implementacion de features |

<!-- EOF -->
