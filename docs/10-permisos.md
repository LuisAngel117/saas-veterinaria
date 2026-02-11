# Permisos (matriz estable)

## Roles
- `SUPERADMIN`: gobierno global, configuracion sensible, auditoria total.
- `ADMIN`: operacion administrativa por sucursal.
- `RECEPCION`: agenda, clientes, facturacion/pagos operativos.
- `VETERINARIO`: atencion clinica y agenda propia.

## Permisos (codigos) por modulo
| Modulo | Permisos |
|---|---|
| Auth | `auth.login`, `auth.refresh`, `auth.logout`, `auth.2fa.manage` |
| Branch | `branch.select` |
| Agenda | `agenda.view`, `agenda.manage`, `agenda.checkin`, `agenda.override_overlap` |
| Clientes/Mascotas | `client.view`, `client.manage`, `pet.view`, `pet.manage` |
| Atencion/HC | `encounter.view`, `encounter.manage`, `encounter.close`, `encounter.reopen`, `encounter.attach` |
| Servicios | `service.view`, `service.manage` |
| Inventario | `inventory.view`, `inventory.adjust`, `inventory.override_block` |
| Facturacion/Pagos | `billing.view`, `billing.manage`, `billing.void`, `payment.manage` |
| Reportes | `report.view`, `report.export` |
| Configuracion | `config.iva.update`, `config.security.update` |
| Auditoria | `audit.view` |

## Matriz rol -> permisos
| Permiso | SUPERADMIN | ADMIN | RECEPCION | VETERINARIO |
|---|---|---|---|---|
| auth.login / auth.refresh / auth.logout | SI | SI | SI | SI |
| auth.2fa.manage | SI | SI | NO | NO |
| branch.select | SI | SI | SI | SI |
| agenda.view | SI | SI | SI | SI |
| agenda.manage | SI | SI | SI | SI |
| agenda.checkin | SI | SI | SI | SI |
| agenda.override_overlap | SI | SI | NO | NO |
| client.view / pet.view | SI | SI | SI | SI |
| client.manage / pet.manage | SI | SI | SI | NO |
| encounter.view | SI | SI | SI | SI |
| encounter.manage / encounter.attach | SI | SI | NO | SI |
| encounter.close | SI | SI | NO | SI |
| encounter.reopen | SI | SI | NO | NO |
| service.view | SI | SI | SI | SI |
| service.manage | SI | SI | NO | NO |
| inventory.view | SI | SI | SI | SI |
| inventory.adjust | SI | SI | NO | NO |
| inventory.override_block | SI | SI | NO | NO |
| billing.view | SI | SI | SI | SI |
| billing.manage | SI | SI | SI | NO |
| billing.void | SI | SI | NO | NO |
| payment.manage | SI | SI | SI | NO |
| report.view / report.export | SI | SI | SI | SI |
| config.iva.update / config.security.update | SI | NO | NO | NO |
| audit.view | SI | SI | NO | NO |

## Acciones sensibles: reason required
- `agenda.override_overlap`
- `encounter.reopen`
- `billing.void`
- `inventory.override_block`
- `config.iva.update`
- Ajustes manuales de inventario y cambios de precio

## Auditoria obligatoria
Toda accion sensible y todo cambio de permisos/roles/configuracion debe registrar:
- actor
- fecha/hora
- sucursal contexto
- entidad/accion
- reason
- before/after cuando aplique

<!-- EOF -->
