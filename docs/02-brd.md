# BRD - Business Requirements (contrato funcional)

## Modulos (tabla)
| Modulo | Objetivo |
|---|---|
| Auth y acceso | Identidad, sesion, roles y seguridad base |
| Tenancy/scoping | Aislar operacion por sucursal |
| Agenda | Gestion de citas y disponibilidad |
| Clientes/Mascotas | Registro operativo de pacientes |
| Atencion/HC | Registro clinico SOAP y adjuntos |
| Servicios/Inventario | Consumo y control de stock por sucursal |
| Facturacion/Pagos | Cobro interno con IVA configurable |
| Reportes | Salidas minimas para operacion y demo |
| Auditoria | Evidencia de cambios y acciones sensibles |

## Requisitos funcionales (con IDs BRD-REQ-###)
- BRD-REQ-001: El sistema debe permitir login con usuario/password, refresh token con rotacion y lockout por intentos fallidos.
- BRD-REQ-002: El sistema debe soportar 2FA TOTP para ADMIN y SUPERADMIN.
- BRD-REQ-003: Todo endpoint branch-scoped debe validar `branch_id` (JWT) y `X-Branch-Id` (header); mismatch 403, falta de header 400.
- BRD-REQ-004: El sistema debe aplicar roles base `SUPERADMIN`, `ADMIN`, `RECEPCION`, `VETERINARIO` con permisos por accion.
- BRD-REQ-005: Agenda debe impedir solape por Sala + Veterinario y gestionar slot 30m + buffer default 10m.
- BRD-REQ-006: Agenda debe soportar check-in separado del estado de cita.
- BRD-REQ-007: Debe existir CRUD de clientes y mascotas con scoping por sucursal.
- BRD-REQ-008: Debe permitirse atencion con o sin cita previa, usando estructura SOAP.
- BRD-REQ-009: HC debe permitir adjuntos PDF/imagen hasta 10MB, cierre con bloqueo y reapertura gobernada por permisos.
- BRD-REQ-010: Debe existir catalogo de servicios con BOM para consumo de inventario.
- BRD-REQ-011: Inventario debe operar por sucursal con costo promedio ponderado y movimientos auditables.
- BRD-REQ-012: Si no hay stock suficiente, se bloquea la operacion salvo override con permiso y reason.
- BRD-REQ-013: Facturacion interna debe soportar IVA global configurable (default 15%) solo por SUPERADMIN con auditoria.
- BRD-REQ-014: Debe soportar pagos mixtos/parciales y saldo pendiente por factura.
- BRD-REQ-015: Deben existir reportes minimos operativos con export CSV/PDF.
- BRD-REQ-016: Toda accion sensible (factura/anulacion/precio/HC cerrada/inventario/override) requiere reason y auditoria before/after.
- BRD-REQ-017: API debe estandarizar errores con Problem Details (RFC 7807).
- BRD-REQ-018: Funcionalidades online-only deben quedar tras feature flag y placeholder documentado.

## Reglas de negocio criticas
- V1 es single-tenant + multi-sucursal.
- `branch_id` de JWT es fuente de verdad.
- Flujo demo minimo: crear cita -> atender -> cerrar HC -> facturar -> registrar pago -> consultar reporte.
- Sucursales no comparten stock operativo en V1.
- Acciones sensibles no se ejecutan sin reason valido.

## Requisitos no funcionales (NFR)
- NFR-001: Operacion local sin servicios externos obligatorios.
- NFR-002: Seguridad base (JWT, lockout, 2FA para ADMIN/SUPERADMIN).
- NFR-003: Trazabilidad documental obligatoria (RTM/STATUS/LOG).
- NFR-004: Consistencia de errores API con RFC 7807.
- NFR-005: Evidencia de auditoria consultable para acciones sensibles.

## Definition of usable local
- Se valida `verify-docs-eof` y `preflight` en verde.
- BRD y RTM sin placeholders `TBD` para requerimientos nucleares.
- Estado y log actualizados con evidencia de tanda.

## Definition of vendible
- Flujo core demostrable sin configuracion manual extra.
- Roles y permisos reflejados en comportamiento esperado.
- Seguridad y auditoria aplicadas en puntos sensibles.
- Entrega con checklist y limites de alcance claros.

<!-- EOF -->
