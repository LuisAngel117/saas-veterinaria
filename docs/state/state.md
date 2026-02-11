# State snapshot (entrada de contexto)

## Resumen actual
El repo está en fase de documentación base. T1 define estructura y scripts de verificación; T2 define el contrato funcional (BRD-REQ-###), arquitectura, seguridad, dominio y UX. Aún no hay implementación backend/frontend.

## Decisiones cerradas (contrato)
- V1: single-tenant + multi-sucursal (branches).
- Scoping: `branch_id` en JWT (fuente de verdad) + `X-Branch-Id` obligatorio en endpoints branch-scoped; mismatch → 403; falta header → 400.
- Roles: SUPERADMIN, ADMIN, RECEPCION, VETERINARIO. Permisos por acción + reason required en sensibles.
- Agenda: no-solape por **Sala + Veterinario**, slot 30m, buffer default 10m, vista semana, check-in separado, override con permiso+reason+auditoría.
- HC SOAP: atención puede existir sin cita; plantillas por servicio; adjuntos PDF/imagen hasta 10MB; cierre con bloqueo; reapertura gobernada por permisos.
- Facturación interna: IVA global configurable (default 15%) solo SUPERADMIN auditable; pagos mixtos/parciales; export CSV/PDF; SRI e-factura fuera de alcance (placeholder con flag).
- Inventario: stock por sucursal; BOM por servicio; costeo promedio ponderado; bloqueo por stock con override (permiso+reason+auditoría).
- Errores API: Problem Details (RFC 7807).
- 2FA: TOTP para ADMIN/SUPERADMIN (RFC 6238).
- Stack objetivo: Java 21 + Spring Boot + Postgres 17 + Flyway + Next.js (TS) + Tailwind/shadcn + OpenAPI.
- Offline-first real: core sin dependencias externas; online-only con feature flags + placeholders.

## Requerimientos (BRD-REQ)
- Definidos en `docs/02-brd.md`.
- RTM inicial en `docs/traceability/rtm.md` (a completar con asignación a sprints).

## Estado de tandas/sprints
Ver `docs/status/status.md`.

## Próximo paso recomendado (FASE D)
Congelar planes maestros por pista (BACK y FRONT) con IDs SPR-B### / SPR-F### mapeados a BRD-REQ-### y pedir aceptación textual:
- “Acepto el plan maestro BACK tal cual”
- “Acepto el plan maestro FRONT tal cual”

## Riesgos/bloqueos actuales
- Masters aún en DRAFT hasta mapear 100% BRD-REQ P0/P1.
- Runbook contiene comandos objetivo que deben implementarse en sprints (no asumir existentes).

<!-- EOF -->
