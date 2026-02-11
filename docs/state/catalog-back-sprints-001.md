# CATALOG-BACK-001 — Catalogo canonico de sprints BACK

Fuente de verdad auditada: `docs/sprints/spr-master-back.md` (ACCEPTED, 2026-02-11).

## A) Lista de sprints BACK (orden del master)
| Orden | ID | Titulo | BRD-REQ objetivo (master: Cierra) | Dependencias (master) |
|---|---|---|---|---|
| 1 | SPR-B001 | Walking Skeleton (auth + scoping + seed minimo + 1 vertical slice) | BRD-REQ-001, BRD-REQ-002, BRD-REQ-020, BRD-REQ-021, BRD-REQ-022 | T1-T4 docs listos. |
| 2 | SPR-B002 | RBAC base (roles/permisos por accion) | BRD-REQ-003 | No listadas en master |
| 3 | SPR-B003 | 2FA TOTP (ADMIN/SUPERADMIN) | BRD-REQ-004 | No listadas en master |
| 4 | SPR-B004 | Auditoria framework (eventos + before/after en sensibles) | BRD-REQ-005 | No listadas en master |
| 5 | SPR-B005 | Config operativa (branches/rooms/vets profiles) | BRD-REQ-006 | No listadas en master |
| 6 | SPR-B006 | Agenda core (CRUD citas + no-solape sala+vet + override) | BRD-REQ-007 | No listadas en master |
| 7 | SPR-B007 | Workflow de cita (transiciones + check-in separado) | BRD-REQ-008 (y completa BRD-REQ-007) | No listadas en master |
| 8 | SPR-B008 | CRM clientes + consentimientos | BRD-REQ-009 | No listadas en master |
| 9 | SPR-B009 | Mascotas/pacientes | BRD-REQ-010 | No listadas en master |
| 10 | SPR-B010 | Clinica: atenciones SOAP + plantillas por servicio | BRD-REQ-011 (parcial: SOAP+templates) | No listadas en master |
| 11 | SPR-B011 | Adjuntos de historia clinica | BRD-REQ-011 (adjuntos) | No listadas en master |
| 12 | SPR-B012 | Cierre/reapertura gobernada (HC) | BRD-REQ-012 | No listadas en master |
| 13 | SPR-B013 | Catalogo servicios + prescripciones + BOM | BRD-REQ-013, BRD-REQ-014, BRD-REQ-016 (estructura BOM) | No listadas en master |
| 14 | SPR-B014 | Inventario base (productos/unidades/stock/movimientos/alertas) | BRD-REQ-015 | No listadas en master |
| 15 | SPR-B015 | Consumo automatico por BOM al cerrar atencion | BRD-REQ-016 | No listadas en master |
| 16 | SPR-B016 | Facturacion: factura desde atencion + IVA + descuentos + pagos | BRD-REQ-017 | No listadas en master |
| 17 | SPR-B017 | Factura: export + anulacion | BRD-REQ-018 | No listadas en master |
| 18 | SPR-B018 | Reportes minimos (backend) | BRD-REQ-023 | No listadas en master |
| 19 | SPR-B019 | Hardening (validaciones, constraints, limpieza, performance basica) | BRD-REQ-019 (parcial: smoke), BRD-REQ-022 (consistencia), NFR | No listadas en master |
| 20 | SPR-RC001 | Release Candidate local (backend) | BRD-REQ-019, BRD-REQ-021 | No listadas en master |

## B) Validacion de secuencia SPR-B### (sin huecos)
- Rango detectado en master: `SPR-B001` a `SPR-B019`.
- Huecos detectados: ninguno.
- INCONSISTENCIA: NO.

## C) Cruce rapido con status
| ID del master | Existe fila en `docs/status/status.md` | Nota |
|---|---|---|
| SPR-B001 | Si | Fila encontrada |
| SPR-B002 | No | No existe fila en status |
| SPR-B003 | No | No existe fila en status |
| SPR-B004 | No | No existe fila en status |
| SPR-B005 | No | No existe fila en status |
| SPR-B006 | No | No existe fila en status |
| SPR-B007 | No | No existe fila en status |
| SPR-B008 | No | No existe fila en status |
| SPR-B009 | No | No existe fila en status |
| SPR-B010 | No | No existe fila en status |
| SPR-B011 | No | No existe fila en status |
| SPR-B012 | No | No existe fila en status |
| SPR-B013 | No | No existe fila en status |
| SPR-B014 | No | No existe fila en status |
| SPR-B015 | No | No existe fila en status |
| SPR-B016 | No | No existe fila en status |
| SPR-B017 | No | No existe fila en status |
| SPR-B018 | No | No existe fila en status |
| SPR-B019 | No | No existe fila en status |
| SPR-RC001 | No | No existe fila en status |

## D) Recomendacion: siguiente sprint a redactar
- Primer sprint del master cuyo `.md` no existe en `docs/sprints/`: `SPR-B007`.
- Ruta esperada para redaccion: `docs/sprints/spr-b007.md` (o variante en mayusculas `docs/sprints/SPR-B007.md`).

<!-- EOF -->
