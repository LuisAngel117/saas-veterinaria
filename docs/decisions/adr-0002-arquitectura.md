# ADR-0002 - Arquitectura

## Estado
ACCEPTED

## Contexto
El proyecto necesita entregas incrementales sin piezas sueltas y con trazabilidad entre requerimiento, codigo y evidencia.

## Decision
- Backend en monolito modular por capas (`web/api`, `application`, `domain`, `infrastructure`).
- Frontend desacoplado por modulos funcionales sobre contratos API estables.
- Errores API bajo Problem Details (RFC 7807).
- Gobernanza obligatoria: DoR/DoD + RTM + status/log + ADR/RFC/changelog.

## Consecuencias
- Cambios de contrato deben pasar por RFC/ADR antes de ejecucion.
- Cada sprint debe cerrar evidencia documental junto con el incremento tecnico.

## Alternativas descartadas
- Microservicios tempranos: descartados por complejidad innecesaria en fase actual.
- Documentacion no versionada: descartada por riesgo de divergencia.

## Fecha
2026-02-11

<!-- EOF -->
