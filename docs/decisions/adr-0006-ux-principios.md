# ADR-0006 - Principios UX/UI

## Estado
ACCEPTED

## Contexto
La demo debe ser clara, rapida y operable por rol, sin esconder restricciones de permisos o validaciones de negocio.

## Decision
- UI en espanol con flujos por rol.
- Mostrar siempre estado actual y siguiente paso sugerido.
- Acciones bloqueadas por permiso deben explicarse en UI.
- Flujo core demostrable: agenda -> atencion -> facturacion -> pago -> reporte.
- Coherencia de agenda: check-in separado del estado de cita.

## Consecuencias
- La UX prioriza operacion y legibilidad sobre complejidad visual.
- Cambios de flujo core requieren actualizacion de BRD y ADR/RFC.

## Alternativas descartadas
- UI opaca sin explicacion de permisos: descartada por friccion operativa.
- Check-in como estado extra: descartado por incoherencia con contrato BRD.

## Fecha
2026-02-11

<!-- EOF -->
