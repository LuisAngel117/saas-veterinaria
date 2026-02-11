# Brief (contexto de negocio)

## Vision
SaaS veterinario local-first para clinicas con multiples sucursales, orientado a una demo vendible en 2-3 minutos y operable sin dependencias externas obligatorias.

## Publico objetivo
- Duenos/administradores de clinicas veterinarias pequenas y medianas.
- Recepcion y veterinarios que operan agenda, historia clinica y cobro diario.

## Alcance V1 vendible
- Auth con roles y seguridad base.
- Contexto de sucursal obligatorio (scoping).
- Flujo core: cita -> atencion -> cierre HC -> factura -> pago -> reporte.
- Auditoria de acciones sensibles con reason obligatorio.
- Operacion local con trazabilidad documental (RTM + STATUS + LOG).

## No-objetivos
- Integraciones online obligatorias en V1.
- Facturacion electronica SRI activa (queda en placeholder con feature flag).
- Multi-tenant completo por organizacion (V1 es single-tenant).

## Principios
- Local-first/offline-first real.
- Demo en 2-3 minutos.
- Trazabilidad fuerte (RTM + LOG/STATUS).
- Contratos explicitos y verificables.

## Glosario
- Branch/Sucursal: unidad operativa de la clinica.
- Scoping: restriccion de acceso a datos por `branch_id`.
- Reason required: motivo obligatorio en acciones sensibles.
- HC: historia clinica.
- BOM: receta de consumo de inventario por servicio.

<!-- EOF -->
