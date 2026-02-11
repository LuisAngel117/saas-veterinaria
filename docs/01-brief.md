# Brief (contexto de negocio)

## Visión
Un sistema local-first/offline-first para operar una clínica veterinaria multi-sucursal (single-tenant v1), con un flujo completo y demostrable en 2–3 minutos: agenda → atención clínica (SOAP) → facturación → pago → reporte, con seguridad (roles/permisos) y auditoría (before/after) que soporte una demo “vendible” para portafolio.

## Público objetivo
- Clínicas veterinarias pequeñas y medianas (1–5 sucursales) que requieren:
  - Agenda operativa por recepción.
  - Atención clínica por veterinarios con historia clínica consistente (SOAP).
  - Facturación interna y control básico de inventario.
- Usuario evaluador (reclutador/cliente freelance) que busca:
  - Producto funcional local.
  - Arquitectura coherente.
  - Calidad (trazabilidad + DoR/DoD + auditoría + seguridad).

## Alcance V1 vendible (local)
Incluye (core, offline-first real):
- Auth + refresh + lockout + 2FA TOTP para ADMIN/SUPERADMIN.
- Multi-sucursal (branch) con selección al login y scoping estricto.
- Agenda (semana) con estados y no-solape por Sala + Veterinario.
- Clientes/mascotas con alertas clínicas.
- Atenciones con SOAP, plantillas y adjuntos (PDF/imagen 10MB).
- Servicios con precio base y “duración sugerida”; BOM de consumo de inventario por servicio.
- Inventario por sucursal (stock, movimientos, mínimos/alertas, costeo promedio ponderado).
- Factura interna (IVA configurable auditable, descuentos, pagos parciales/mixtos, anulación con reason).
- Reportes mínimos + export CSV/PDF.
- Auditoría de acciones clave (incluye before/after en sensibles).
- Seeds demo + runbook + scripts “verdad”.

Online-only (solo placeholders con feature flags; NO rompe local):
- Recordatorios externos (email/SMS/WhatsApp): quedan “pendiente de enviar” local.
- E-factura SRI: fuera de alcance v1; solo estructura placeholder.

## No-objetivos (v1)
- Multi-tenant real (v1 es single-tenant).
- Portal de auto-reserva del cliente.
- Inventario avanzado (lotes/caducidad, trazabilidad por lote).
- Integraciones externas reales (pasarelas, mensajería, SRI).
- Horarios por sucursal (v1 global).

## Principios
- Local-first/offline-first real: el core opera sin servicios externos.
- Incrementos integrados (vertical slices): evitar “piezas sueltas”.
- Seguridad por defecto: scoping + permisos por acción + auditoría.
- UX “operativa”: rápido para recepción y veterinario, con errores claros.
- Trazabilidad: BRD-REQ → sprints → evidencia (RTM + LOG/STATUS).

## Glosario (mínimo)
- Sucursal (Branch): unidad operativa (inventario, agenda y datos separados).
- Cita (Appointment): reserva en agenda para un paciente con recurso Sala+Veterinario.
- Atención (Encounter): evento clínico (SOAP) que puede o no estar ligado a una cita.
- SOAP: Subjective, Objective, Assessment, Plan.
- BOM (Bill of Materials): receta de consumo de inventario asociada a un servicio.
- Acción sensible: operación que exige “reason required” y auditoría before/after.
- Local-first: se desarrolla y valida primero en local.
- Offline-first: el core funciona sin internet y sin servicios externos.

<!-- EOF -->
