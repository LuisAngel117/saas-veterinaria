# State snapshot (entrada de contexto)

## Resumen actual
T2 deja cerrada la base documental real: brief, BRD con IDs estables, arquitectura, seguridad, dominio, UX, runbook, permisos, entrega, RTM inicial y masters BACK/FRONT en DRAFT.

## Decisiones cerradas (contrato)
- V1: single-tenant + multi-sucursal (branches).
- Scoping: `branch_id` en JWT + `X-Branch-Id` obligatorio en endpoints branch-scoped; mismatch -> 403; falta header -> 400.
- Roles: SUPERADMIN, ADMIN, RECEPCION, VETERINARIO con permisos por accion.
- Reason required en acciones sensibles.
- Agenda: no-solape Sala + Veterinario, slot 30m, buffer 10m, check-in separado.
- HC SOAP con adjuntos (max 10MB), cierre con bloqueo y reapertura gobernada por permisos.
- Facturacion interna con IVA configurable (default 15%) auditable por SUPERADMIN.
- Inventario por sucursal, BOM por servicio y bloqueo por stock con override autorizado.
- API errors en Problem Details (RFC 7807).
- 2FA TOTP para ADMIN/SUPERADMIN.
- Stack objetivo: Java 21 + Spring Boot + Postgres 17 + Flyway + Next.js + Tailwind + shadcn.

## Requerimientos (BRD-REQ-###)
Definidos en `docs/02-brd.md`:
- BRD-REQ-001..018 (auth, scoping, agenda, clientes/mascotas, HC, inventario, facturacion, reportes, auditoria, errores, feature flags).

## Estado de sprints/tandas
Ver `docs/status/status.md`.
- Masters BACK/FRONT siguen en DRAFT.
- No hay sprints ejecutables aprobados aun.

## Proximo paso recomendado
T3: convertir masters DRAFT en sprints ejecutables (vertical slice), iniciar walking skeleton y generar handoff tecnico backend -> frontend con contratos reales.

## Riesgos/bloqueos actuales
- Aun no existe codigo backend/frontend implementado.
- Runbook tecnico de arranque de apps queda pendiente hasta tener proyectos base.
- Integraciones online-only permanecen fuera de alcance en V1 local.

<!-- EOF -->
