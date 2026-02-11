# BRD — Business Requirements (contrato funcional)

## 1) Propósito
Este documento define el contrato funcional y no-funcional del sistema, con IDs estables `BRD-REQ-###` para trazabilidad (RTM).

## 2) Alcance v1 (vendible local-first / offline-first real)
Incluye:
- Agenda/turnos multi-sucursal (branch) con no-solape por **Sala + Veterinario** y flujos de estados.
- CRM: clientes (propietarios) + consentimientos.
- Pacientes/mascotas con código interno único por sucursal.
- Clínica: atenciones SOAP, plantillas por servicio, adjuntos.
- Catálogo: servicios (precio base, duración sugerida) + BOM para consumo.
- Inventario: productos/unidades/stock/movimientos/alertas + overrides auditados.
- Facturación interna: IVA configurable global, descuentos, pagos mixtos/parciales, exportación.
- Reportes mínimos + export CSV/PDF.
- Seguridad: login/refresh, lockout, 2FA admin, permisos por acción.
- Auditoría: eventos + before/after en acciones sensibles.
- Seeds/demo + runbook + scripts “verdad” + smoke scripts.

Excluye (v1):
- Multi-tenant real.
- Auto-reserva por cliente (online-only futuro).
- E-factura SRI real (solo placeholder con feature flag).
- Lotes/caducidad.
- Horarios por sucursal (v1 global).
- Integraciones externas reales (WhatsApp/Email/SMS): solo placeholders “pendiente de enviar”.

## 3) Módulos (tabla)
| Módulo | Descripción | Prioridad | Local/Online |
|---|---|---:|---|
| Auth/Security | login/refresh/logout, lockout, 2FA, session | P0 | Local |
| Scoping Branch | selector de sucursal + header X-Branch-Id validado vs claim | P0 | Local |
| Agenda | citas, calendario semana, no-solape, override | P0 | Local |
| CRM | clientes + consentimientos, mascotas | P0 | Local |
| Clínica | atenciones SOAP, plantillas, adjuntos | P0 | Local |
| Catálogo | servicios + BOM + prescripciones | P0 | Local |
| Inventario | productos, stock, movimientos, alertas | P0 | Local |
| Facturación | factura interna, IVA, descuentos, pagos, export | P0 | Local |
| Reportes | ventas, citas, top servicios, consumo, frecuentes | P1 | Local |
| Auditoría | eventos + before/after + retención demo | P0 | Local |
| Online-only placeholders | recordatorios / e-factura / integraciones | P2 | Online-only (flag) |

## 4) Historias / Casos de uso (core demo)
1) Recepción: login → seleccionar sucursal → crear cita (sala+veterinario, slot 30m) → confirmar → check-in.
2) Veterinario: ver agenda semanal → iniciar atención desde cita → completar SOAP → agregar servicios/procedimientos → adjuntar archivo → cerrar atención.
3) Sistema: al cerrar atención, consumir inventario por BOM (si aplica) y generar consistencia.
4) Recepción/Admin: generar factura desde atención cerrada → aplicar descuentos → registrar pago parcial/mixto → marcar pagado.
5) Admin/Superadmin: anular factura con reason → auditoría before/after.
6) Admin: reapertura de historia clínica cerrada (reason) → auditoría.
7) Inventario: ingreso/egreso/ajuste manual con reason cuando aplica; override por stock insuficiente.
8) Reportes: ver ventas por período + export CSV/PDF.
9) Auditoría: consultar quién cambió qué (acciones sensibles).
10) Seeds demo: flujos probables en 2–3 minutos sin configurar nada manual.

## 5) Reglas de negocio críticas (duras)
### Agenda / no-solape
- Recurso compuesto: **Sala + Veterinario**.
- Conflicto de solape → 409 (Problem Details) salvo override con permiso + reason + auditoría.
- Estados EXACTOS de cita: RESERVED / CONFIRMED / IN_SERVICE / CLOSED / CANCELLED.
- Check-in es evento separado: `check_in_at` (timestamp), no estado adicional.
- Slot base v1: 30 minutos. Buffer default: 10 minutos (configurable).

### Scoping (branch)
- `branch_id` en JWT es fuente de verdad.
- `X-Branch-Id` obligatorio en endpoints branch-scoped.
- Falta header (en endpoint branch-scoped) → 400.
- Mismatch header vs claim → 403.

### Clínica (historia)
- Atención puede existir sin cita.
- SOAP mínimo cerrado (S/O/A/P).
- Cierre bloquea edición.
- Reapertura: ADMIN/SUPERADMIN (reason+auditoría). VETERINARIO solo con permiso explícito; si no, registra solicitud para admin.

### Facturación
- Factura interna.
- IVA global configurable (default 15%): solo SUPERADMIN, reason+auditoría.
- Estados: PENDING / PAID / VOID.
- Anulación siempre requiere reason + auditoría before/after.

### Inventario
- Stock por sucursal.
- Consumo automático por BOM al cerrar atención (si aplica).
- Si no hay stock suficiente: bloquear (por defecto) con override por permiso + reason + auditoría.
- Costeo: promedio ponderado (costo promedio).

## 6) NFR (no funcionales) — mínimos vendibles
- Offline-first real: core funciona sin servicios externos (sin “dependencias internet”).
- Seguridad: OWASP base; 2FA admin; lockout; permisos por acción.
- Auditoría: rastreabilidad real consultable.
- Calidad: DoR/DoD obligatorios + RTM actualizado.
- UX: UI en español, errores claros, flujos completos.
- Operación: runbook Windows/Linux + scripts “verdad” y smoke.

## 7) Definition of usable local
Se considera usable local si:
- Backend y frontend arrancan en local según runbook.
- Seeds demo permiten ejecutar el flujo core sin configuración manual.
- No hay llamadas externas obligatorias en core.
- Smoke script(s) mínimo(s) pasan (auth + scoping + agenda no-solape).

## 8) Definition of vendible (demo portafolio)
Se considera vendible (para demo) si:
- El flujo 2–3 minutos se puede completar sin errores.
- UI es consistente (español), con validaciones y mensajes claros.
- Seguridad y auditoría se pueden “demostrar” (reason required + before/after).
- RTM muestra cobertura P0 y evidencia por sprint.

## 9) Requisitos con IDs estables (BRD-REQ-###)

### P0 — Obligatorio v1
- BRD-REQ-001: Auth (login + refresh + logout) con JWT access/refresh + rotación refresh.
- BRD-REQ-002: Selector de sucursal (branch) al login + scoping seguro (claim + X-Branch-Id validado).
- BRD-REQ-003: RBAC: roles v1 (SUPERADMIN/ADMIN/RECEPCION/VETERINARIO) + permisos por acción.
- BRD-REQ-004: 2FA TOTP para ADMIN/SUPERADMIN.
- BRD-REQ-005: Auditoría de acciones (incluye before/after en sensibles) + retención demo 90 días.
- BRD-REQ-006: Configuración operativa: sucursales, salas, veterinarios (perfil) + seeds.
- BRD-REQ-007: Agenda: CRUD de citas + vista semana + no-solape por Sala+Veterinario + override (permiso+reason+audit).
- BRD-REQ-008: Acciones de cita: confirmar/cancelar/check-in/iniciar atención/cerrar (transiciones válidas).
- BRD-REQ-009: CRM clientes (propietarios) + consentimientos trazables.
- BRD-REQ-010: Mascotas: CRUD + código interno único por sucursal + alertas/antecedentes.
- BRD-REQ-011: Atenciones: SOAP estructurado + plantillas por servicio + adjuntos (PDF/JPG/PNG) máx 10MB.
- BRD-REQ-012: Cierre y reapertura de historia clínica con permisos + reason + auditoría.
- BRD-REQ-013: Catálogo de servicios: precio base + duración sugerida + activo/inactivo.
- BRD-REQ-014: Prescripciones/indicaciones: estructura (dosis/frecuencia/duración/vía) + texto opcional + export.
- BRD-REQ-015: Inventario: productos + unidades + stock + movimientos + mínimos/alertas + overrides auditados.
- BRD-REQ-016: BOM por servicio + consumo automático de inventario al cerrar atención (si aplica).
- BRD-REQ-017: Facturación: factura desde atención, IVA global configurable (default 15%), descuentos ítem/global, pagos mixtos/parciales.
- BRD-REQ-018: Factura: anulación (reason+before/after) + export CSV/PDF.
- BRD-REQ-019: Runbook local + scripts “verdad” + smoke scripts por flujo crítico.
- BRD-REQ-020: Seeds/demo local (usuarios/roles, sucursales, salas, servicios, clientes, mascotas) para prueba 2–3 min.
- BRD-REQ-021: OpenAPI/Swagger actualizado para endpoints implementados.
- BRD-REQ-022: Estándar de errores API: Problem Details (RFC 7807).

### P1 — Recomendado v1 (si no rompe ritmo)
- BRD-REQ-023: Reportes mínimos (citas, ventas, top servicios, consumo inventario, frecuentes) + export CSV/PDF.
- BRD-REQ-024: Dashboard home por rol (resumen + accesos rápidos).
- BRD-REQ-025: UX vendible: permisos visibles, empty states, validación de formularios, mensajes claros.

### P2 — Online-only (solo placeholders con feature flag)
- BRD-REQ-026: Recordatorios/confirmaciones (pendiente de enviar en local; envío real online).
- BRD-REQ-027: E-factura SRI (solo estructura placeholder en v1).

<!-- EOF -->
