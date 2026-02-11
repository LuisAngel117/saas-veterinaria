# Dominio - Parte A (modelo de datos)

## Entidades (tabla)
| Entidad | Descripcion |
|---|---|
| branch | Sucursal operativa |
| user | Usuario del sistema |
| role | Rol de negocio |
| permission | Permiso por accion |
| user_role / role_permission | Asignaciones de seguridad |
| client | Cliente/tutor |
| pet | Mascota/paciente |
| room | Sala de atencion |
| appointment | Cita/agenda |
| encounter | Atencion clinica |
| soap_note | Registro SOAP |
| attachment | Adjunto de HC |
| service | Catalogo de servicios |
| service_bom | Receta de consumo por servicio |
| inventory_item | Item de inventario |
| inventory_movement | Movimientos de stock |
| invoice | Factura interna |
| payment | Pago de factura |
| audit_event | Evento de auditoria |
| system_config | Configuracion sensible (ej. IVA) |

## Relaciones
- `branch` 1..n `appointment`, `encounter`, `inventory_item`, `invoice`.
- `client` 1..n `pet`.
- `pet` 1..n `appointment` y 1..n `encounter`.
- `appointment` 0..1 `encounter`.
- `encounter` 1..n `soap_note` y 0..n `attachment`.
- `service` 0..n `service_bom` -> `inventory_item`.
- `invoice` 1..n `payment`.
- `audit_event` referencia actor + entidad objetivo.

## Reglas criticas
- Scoping obligatorio por `branch_id` en datos operativos.
- Agenda sin solape por combinacion Sala + Veterinario.
- `encounter` puede existir sin `appointment`.
- Cierre de HC bloquea edicion salvo permiso explicito de reapertura.
- Inventario bloquea consumo sin stock, salvo override autorizado con reason.
- Cambio de IVA y acciones sensibles siempre auditadas.

## Seeds demo (minimo)
- 2 sucursales.
- 4 roles base con usuarios de prueba.
- 1 veterinario por sucursal y al menos 1 sala por sucursal.
- 3 clientes con mascotas.
- Catalogo minimo de servicios y items de inventario relacionados.

<!-- EOF -->
