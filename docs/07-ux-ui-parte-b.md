# UX/UI — Parte B

## 1) Pantallas por rol (resumen)
- RECEPCION:
  - Agenda, Clientes/Mascotas (CRUD), Check-in, Facturación (crear/pagos), Reportes básicos
- VETERINARIO:
  - Agenda (ver), Atenciones (crear/editar/cerrar), SOAP, Adjuntos
- ADMIN:
  - Todo lo anterior + catálogos + usuarios + reaperturas + anulaciones + overrides
- SUPERADMIN:
  - Todo + IVA default + feature flags + políticas seguridad + auditoría completa

## 2) Tabla: pantalla → acciones → permisos → validaciones clave
| Pantalla | Acción | Permiso | Validaciones |
|---|---|---|---|
| Agenda | Crear cita | AGENDA_CREATE | no-solape sala+vet; X-Branch-Id; start<end |
| Agenda | Forzar solape | AGENDA_OVERRIDE_OVERLAP | reason requerido; auditoría |
| Agenda | Check-in | AGENDA_CHECKIN | estado permitido; auditar |
| Atención | Crear sin cita | ENCOUNTER_CREATE | requiere client+pet+vet; scoping |
| Atención | Cerrar | ENCOUNTER_CLOSE | SOAP mínimo completo; consumir BOM; auditar |
| Atención | Reabrir | ENCOUNTER_REOPEN | reason; permiso; auditar before/after |
| Factura | Crear | INVOICE_CREATE | encounter cerrada; calcular IVA/desc |
| Factura | Pago parcial | PAYMENT_CREATE | monto >0; no exceder saldo |
| Factura | Anular | INVOICE_ANNUL | reason; auditar before/after |
| Inventario | Ajuste manual | INVENTORY_ADJUST | reason; auditar before/after |
| Inventario | Override stock | INVENTORY_OVERRIDE_NEGATIVE | reason; auditar |
| Admin | Cambiar IVA | SETTINGS_TAX_UPDATE | SUPERADMIN; reason; auditar |

## 3) Reglas visuales
- UI en español.
- Componentes consistentes (shadcn):
  - tablas con filtros
  - formularios con validación
  - modales para “reason required”
- Mensajes:
  - usar “No autorizado” (403), “Falta contexto de sucursal” (400), “Conflicto de agenda” (409).
- Estados:
  - siempre mostrar estado actual y “siguiente paso sugerido”.

<!-- EOF -->
