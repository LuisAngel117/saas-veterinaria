# UX/UI — Parte A

## 1) Principios UX (vendible en demo)
- Rapidez operativa: menos clics en recepción.
- Estado visible: en agenda y atención, mostrar claramente “qué sigue”.
- Errores útiles: mensajes en español, no genéricos.
- Permisos transparentes: si una acción está deshabilitada, explicar por qué.
- Offline-first: no depender de internet; si algo es online-only, mostrar “Pendiente de enviar” (feature flag).

## 2) Navegación (mapa de pantallas)
- Login
- (Si aplica) 2FA
- Selector de sucursal
- Home (dashboard por rol)
- Agenda (calendario semana)
- Clientes
  - Lista
  - Detalle cliente
  - Crear/editar cliente
- Mascotas
  - Lista (por cliente o global por sucursal)
  - Detalle mascota (alertas visibles)
  - Crear/editar mascota
- Atenciones
  - Iniciar atención (desde cita o sin cita)
  - Editor SOAP
  - Adjuntos
  - Cerrar/Reabrir (según permiso)
- Facturación
  - Generar factura desde atención
  - Registrar pagos (mixto/parcial)
  - Anular (reason required)
- Inventario
  - Productos
  - Movimientos
  - Alertas mínimos
- Reportes
  - Citas por período
  - Ventas por período
  - Top servicios
  - Consumo inventario
  - Frecuentes
- Admin
  - Usuarios/roles
  - Catálogos (servicios, productos, unidades)
  - Configuración (IVA, feature flags) — SUPERADMIN

## 3) Flujos críticos (3–10)
1) Recepción: crear cita
2) Recepción: check-in
3) Veterinario: iniciar atención + completar SOAP + cerrar
4) Recepción/Admin: generar factura + pago + cerrar estado
5) Admin/Superadmin: anulación factura (reason)
6) Inventario: consumo automático por BOM (al cerrar atención) + ajuste manual con reason
7) Reporte: ventas por período + export

<!-- EOF -->
