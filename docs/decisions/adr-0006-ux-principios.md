# ADR-0006 — UX principios (UI vendible)

## Contexto
El proyecto se usa para portafolio: debe “sentirse producto”, con UX clara por rol.

## Decisión
- UI en español.
- Flujos core con mínimos clics: cita → check-in → atención SOAP → cierre → factura → pago → reporte.
- Errores claros (403/400/409) con mensajes accionables.
- Permisos visibles: ocultar/deshabilitar con explicación.
- Offline-first: online-only como placeholder “pendiente de enviar” con feature flag.

## Consecuencias
- Requiere consistencia visual y mensajes estandarizados.
- FRONT depende del handoff backend para no inventar contratos.

## Alternativas descartadas
- UI genérica sin roles (confuso).
- Pantallas aisladas sin integración (piezas sueltas).

## Fecha
2026-02-11

<!-- EOF -->
