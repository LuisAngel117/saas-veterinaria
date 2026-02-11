# BRD — Business Requirements (contrato funcional)

Este documento es el contrato funcional. Todo requisito relevante debe tener ID estable `BRD-REQ-###` y mapearse en RTM.

## Módulos (alcance por módulo)
| Módulo | Descripción | Prioridad | Local/Online |
|---|---|---:|---|
| Autenticación y sesión | Login + refresh + logout + lockout + 2FA (admin) | P0 | Local |
| Usuarios/roles/permisos | CRUD usuarios, asignación de roles, permisos por acción | P0 | Local |
| Multi-sucursal (scoping) | Selección sucursal y scoping seguro en API y UI | P0 | Local |
| Agenda/turnos | Calendario semanal, estados, no-solape sala+veterinario, override | P0 | Local |
| Clientes | Ficha cliente, contactos, tags, consentimientos | P0 | Local |
| Pacientes/mascotas | Ficha mascota, alertas/alergias, código interno por sucursal | P0 | Local |
| Atenciones + historia clínica | SOAP, plantillas, adjuntos, cierre/reapertura | P0 | Local |
| Servicios + prescripciones | Catálogo servicios, prescripción estructurada, impresión | P1 | Local |
| Inventario | Productos, unidades, stock por sucursal, movimientos, mínimos | P0 | Local |
| Facturación | Factura interna, IVA configurable, descuentos, pagos, anulación | P0 | Local |
| Reportes | 5 reportes mínimos + export CSV/PDF | P1 | Local |
| Auditoría | Eventos + before/after en sensibles + retención 90 días | P0 | Local |
| Feature flags online-only | Placeholders: recordatorios externos, e-factura SRI | P2 | Online-only (placeholder local) |
| Runbook + scripts verdad | Levantar, test, smoke, verificación | P0 | Local |

## Requisitos funcionales (BRD-REQ-###)

### Autenticación, sesión y seguridad (P0)
- BRD-REQ-001 — Login con usuario+password.
- BRD-REQ-002 — JWT access (1h) + refresh (7d) con rotación de refresh token.
- BRD-REQ-003 — Logout (invalida refresh token activo).
- BRD-REQ-004 — Lockout: 4 intentos fallidos → 15 min.
- BRD-REQ-005 — Política password: min 10 chars + mayúscula + minúscula + número + símbolo.
- BRD-REQ-006 — 2FA TOTP para ADMIN/SUPERADMIN (RFC 6238): enrolar, validar, reset (auditable).
- BRD-REQ-007 — CORS local: permitir localhost (frontend) → backend.

### Multi-sucursal (branch) y scoping (P0)
- BRD-REQ-008 — Selección de sucursal al iniciar sesión.
- BRD-REQ-009 — `branch_id` en JWT como fuente de verdad.
- BRD-REQ-010 — `X-Branch-Id` obligatorio en endpoints branch-scoped.
- BRD-REQ-011 — Validación: mismatch header vs claim → 403; falta header → 400.
- BRD-REQ-012 — Separación de datos por sucursal (branch): agenda, inventario, códigos internos.

### Usuarios, roles, permisos (P0)
- BRD-REQ-013 — Roles v1: SUPERADMIN, ADMIN, RECEPCION, VETERINARIO.
- BRD-REQ-014 — Permisos granulares por acción (códigos estables).
- BRD-REQ-015 — UI debe reflejar permisos (ocultar/deshabilitar con explicación).
- BRD-REQ-016 — Acciones sensibles “reason required” (mínimo): anular factura, cambiar precio, anular atención, editar HC cerrada, ajustes inventario manuales, override solape.
- BRD-REQ-017 — Auditoría de cambios de usuarios/roles/permisos.

### Agenda/turnos (P0)
- BRD-REQ-018 — Calendario semanal por sucursal.
- BRD-REQ-019 — Estados de cita: reservado/confirmado/en_atención/cerrado/cancelado.
- BRD-REQ-020 — Cita usa recursos Sala + Veterinario (no-solape de ambos).
- BRD-REQ-021 — Slot base 30 min; buffer configurable (default 10 min).
- BRD-REQ-022 — Check-in separado de “en atención”.
- BRD-REQ-023 — CRUD cita (recepción): crear/editar/confirmar/cancelar + motivo.
- BRD-REQ-024 — Override solape (permiso + reason + auditoría) para emergencias.
- BRD-REQ-025 — No-show simple: marcar como cancelado con motivo NO_SHOW (auditable).

### Clientes y pacientes/mascotas (P0)
- BRD-REQ-026 — CRUD cliente con campos: nombre (req), teléfono (req), id opcional, email/dirección/notas/tags opcionales.
- BRD-REQ-027 — Consentimientos trazables: tratamiento_datos + contacto_recordatorios + versión_texto + usuario/fecha.
- BRD-REQ-028 — CRUD mascota/paciente con alertas clínicas (alergias/alertas/antecedentes).
- BRD-REQ-029 — Código interno de mascota único por sucursal.
- BRD-REQ-030 — Una mascota tiene un solo propietario (v1).

### Atenciones / Historia clínica (P0)
- BRD-REQ-031 — Una atención puede existir sin cita.
- BRD-REQ-032 — SOAP mínimo por atención:
  - S: motivo_consulta, anamnesis
  - O: peso, temperatura, hallazgos, constantes_adicionales
  - A: diagnostico_texto, diagnosticos_secundarios
  - P: plan_tratamiento, indicaciones, recontrol_fecha (opcional), notas
- BRD-REQ-033 — Plantillas SOAP por tipo de servicio.
- BRD-REQ-034 — Adjuntos en atención: PDF/imagen (10MB) almacenados localmente.
- BRD-REQ-035 — Cerrar atención bloquea edición; reapertura:
  - ADMIN/SUPERADMIN siempre con reason
  - VETERINARIO solo con permiso explícito (si no, solicita reapertura).

### Servicios / Prescripciones / Impresión (P1)
- BRD-REQ-036 — Catálogo de servicios: nombre, precio base, duración sugerida.
- BRD-REQ-037 — Prescripción estructurada (dosis, frecuencia, duración, vía) + texto libre opcional.
- BRD-REQ-038 — Export/imprimir indicaciones (PDF/HTML).

### Inventario (P0)
- BRD-REQ-039 — Productos (medicamento/insumo) y unidades (catálogo).
- BRD-REQ-040 — Stock por sucursal (branch) con movimientos: ingreso/egreso/ajuste/consumo.
- BRD-REQ-041 — Mínimos y alertas por producto/sucursal.
- BRD-REQ-042 — Costeo: promedio ponderado.
- BRD-REQ-043 — Bloquear facturar si no hay stock, con override por permiso + reason + auditoría.

### Consumo automático (BOM) (P1)
- BRD-REQ-044 — Servicio puede definir BOM de consumo (producto + cantidad).
- BRD-REQ-045 — Al cerrar atención/facturar (según regla definida en arquitectura), se registra consumo automático desde BOM.

### Facturación (P0)
- BRD-REQ-046 — Factura interna por atención con items (servicios/productos), totales e impuestos.
- BRD-REQ-047 — IVA global configurable (default 15%), solo SUPERADMIN, auditable con reason + before/after.
- BRD-REQ-048 — Descuentos por ítem y global.
- BRD-REQ-049 — Pagos mixtos y parciales; estados factura: pendiente/pagado/anulado.
- BRD-REQ-050 — Anulación de factura: requiere reason + auditoría before/after.
- BRD-REQ-051 — Exportación de facturas: CSV/PDF.

### Reportes (P1)
- BRD-REQ-052 — Reporte: citas por período (filtros: sucursal, vet, estado) + export.
- BRD-REQ-053 — Reporte: ventas por período + export.
- BRD-REQ-054 — Reporte: top servicios + export.
- BRD-REQ-055 — Reporte: consumo inventario + export.
- BRD-REQ-056 — Reporte: clientes/pacientes frecuentes + export.
- BRD-REQ-057 — Dashboard home por rol (KPIs básicos según rol).

### Auditoría (P0)
- BRD-REQ-058 — Auditoría de auth: login/logout/refresh.
- BRD-REQ-059 — Auditoría de agenda: create/update/cancel/confirm/check-in/start/close + override solape.
- BRD-REQ-060 — Auditoría de cliente/mascota: create/update.
- BRD-REQ-061 — Auditoría de historia clínica: create/update/close/reopen/editar post-cierre (si aplica).
- BRD-REQ-062 — Auditoría de facturación: create/update, descuentos, pagos, anulación, cambios de precio.
- BRD-REQ-063 — Auditoría de inventario: ingresos/egresos/ajustes/consumos + overrides.
- BRD-REQ-064 — Retención auditoría demo: 90 días (con job/purge planificado).

### Online-only placeholders (P2)
- BRD-REQ-065 — Recordatorios externos “pendiente de enviar” local + feature flag para integración futura.
- BRD-REQ-066 — E-factura SRI placeholder: estructura de configuración + feature flag, sin envío real.

### Runbook / Seeds / Demo (P0)
- BRD-REQ-067 — Seeds demo: 4 usuarios demo (roles) + 2 sucursales + servicios + productos + clientes + mascotas.
- BRD-REQ-068 — Flujo demo 2–3 min operable end-to-end.
- BRD-REQ-069 — Runbook local completo (Windows + Linux) con comandos “verdad”.
- BRD-REQ-070 — Smoke scripts por flujo crítico (mínimo: auth + crear cita + atención + factura + pago).

## Reglas de negocio críticas (rompen sistemas)
- No-solape sala+veterinario: no se permite doble reserva salvo override con permiso+reason.
- Scoping: `branch_id` del JWT manda; header obligatorio y debe coincidir.
- Acciones sensibles requieren reason y auditoría before/after.
- Inventario: no permitir stock negativo sin override (permiso+reason+audit).
- HC cerrada no editable salvo reapertura gobernada.

## Requisitos no funcionales (NFR)
- NFR-001 Seguridad: OWASP básico (validación input, auth robusta, no exponer secretos).
- NFR-002 Errores: Problem Details (RFC 7807) consistente.
- NFR-003 Observabilidad local: logs estructurados y auditoría consultable.
- NFR-004 Performance demo: operaciones comunes < 300ms en ambiente local (objetivo).
- NFR-005 Portabilidad: runbook para Windows + Linux; Postgres 17.
- NFR-006 Calidad: DoR/DoD + RTM + LOG/STATUS obligatorios.

## Definition of usable local
- Backend + frontend levantan local sin servicios externos.
- Seeds demo cargados y el flujo core se puede ejecutar de punta a punta.
- Errores claros (no pantallas en blanco), y scoping funciona.

## Definition of vendible (portafolio)
- Demo reproducible en 2–3 minutos siguiendo `docs/11-entrega.md`.
- Trazabilidad: RTM tiene cobertura de BRD-REQ P0 y la evidencia en commits/LOG.
- Sprints no dejan piezas sueltas: cada incremento integrado.

<!-- EOF -->
