# State snapshot (entrada de contexto)

## Resumen actual
Repo nuevo. Se está bootstrappeando la estructura de documentación y scripts (T1). Aún no hay backend/frontend implementados.

## Decisiones cerradas (contrato)
- V1: single-tenant + multi-sucursal (branches).
- Scoping: `branch_id` en JWT (fuente de verdad) + `X-Branch-Id` obligatorio en endpoints branch-scoped; mismatch → 403; falta header → 400.
- Roles: SUPERADMIN, ADMIN, RECEPCION, VETERINARIO. Permisos por acción.
- Reason required en acciones sensibles (factura/anulación/precio/HC cerrada/inventario/override solape).
- Agenda: no-solape por **Sala + Veterinario** (ambos), slot 30m, buffer default 10m, vista semana, check-in separado, override con permiso+reason+auditoría.
- HC SOAP: atención puede existir sin cita; plantillas por servicio; adjuntos PDF/imagen hasta 10MB; cierre con bloqueo; reapertura gobernada por permisos.
- Facturación interna: IVA global configurable (default 15%) solo SUPERADMIN auditable; pagos mixtos/parciales; export CSV/PDF; SRI e-factura fuera de alcance (placeholder con flag).
- Inventario: stock por sucursal; BOM por servicio; costeo promedio ponderado; bloqueo por stock con override (permiso+reason+auditoría).
- Errores API: Problem Details (RFC 7807).
- 2FA: TOTP para ADMIN/SUPERADMIN (RFC 6238).
- Stack objetivo: Java 21 + Spring Boot + Postgres 17 + Flyway + Next.js (TS) + Tailwind/shadcn + OpenAPI.
- Local-first/offline-first real: core sin dependencias externas; online-only con feature flags + placeholders.

## Requerimientos (BRD-REQ-###)
Aún no generados (pendiente T2).

## Estado de sprints/tandas
Ver `docs/status/status.md`.

## Próximo paso recomendado
T2: completar docs de negocio/BRD/arquitectura/seguridad/dominio/UX/runbook/permisos y dejar BRD-REQ-### + RTM inicial.

## Riesgos/bloqueos actuales
- Sin BRD-REQ aún → no se deben crear sprints ejecutables.
- Falta de runbook/commands “verdad” → bloquear ejecución de sprints hasta definirlos.

<!-- EOF -->
