# ADR-0005 - Auditoria

## Estado
ACCEPTED

## Contexto
Se necesita trazabilidad de acciones sensibles con contexto suficiente para revisiones funcionales y tecnicas.

## Decision
- Auditoria obligatoria en auth, agenda, clientes/mascotas, HC, inventario, facturacion, configuracion y seguridad.
- Acciones sensibles exigen `reason` y, cuando aplique, `before/after`.
- Retencion demo: 90 dias.

## Consecuencias
- Las tandas deben registrar evidencia en log/status.
- El modelo de auditoria debe ser consultable y consistente entre modulos.

## Alternativas descartadas
- Auditoria parcial solo de auth/facturacion: descartada por cobertura insuficiente.
- Logs sin estructura de evento: descartados por baja trazabilidad.

## Fecha
2026-02-11

<!-- EOF -->
