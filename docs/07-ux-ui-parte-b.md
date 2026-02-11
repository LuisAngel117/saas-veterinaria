# UX/UI - Parte B

## Pantallas por rol
- Comun: login, seleccion de sucursal, dashboard.
- RECEPCION: agenda, clientes/mascotas, facturacion/pagos basicos.
- VETERINARIO: agenda, atencion/HC, adjuntos.
- ADMIN: configuracion operativa, reportes, auditoria (lectura).
- SUPERADMIN: administracion global, IVA, permisos avanzados.

## Tabla pantalla -> acciones -> permisos -> validaciones
| Pantalla | Accion clave | Permiso sugerido | Validaciones |
|---|---|---|---|
| Login | Iniciar sesion | auth.login | lockout + 2FA segun rol |
| Selector sucursal | Cambiar contexto | branch.select | sucursal habilitada para usuario |
| Agenda semana | Crear/editar cita | agenda.manage | no-solape sala+vet, slot/buffer |
| Agenda semana | Check-in | agenda.checkin | cita existente y estado valido |
| Clientes | Crear/editar cliente | client.manage | datos minimos requeridos |
| Mascotas | Crear/editar mascota | pet.manage | cliente asociado obligatorio |
| Atencion/HC | Abrir/cerrar atencion | encounter.manage | estructura SOAP minima |
| Atencion/HC | Reabrir HC cerrada | encounter.reopen | permiso + reason obligatorio |
| Facturacion | Emitir/anular factura | billing.manage | items validos, auditoria |
| Pagos | Registrar pago | payment.manage | monto > 0 y no exceder saldo |
| Inventario | Ajustar stock | inventory.adjust | permiso + reason en ajustes sensibles |
| Reportes | Exportar CSV/PDF | report.export | rango de fechas y branch scope |

## Reglas visuales
- UI en Espanol y orientada a demo rapida.
- Mostrar contexto activo de sucursal en header.
- Acciones sin permiso deben mostrarse deshabilitadas con mensaje explicativo.
- Errores de API se renderizan con mensaje comprensible sin ocultar codigo/causa.
- Formularios sensibles exigen confirmacion explicita y captura de reason.

<!-- EOF -->
