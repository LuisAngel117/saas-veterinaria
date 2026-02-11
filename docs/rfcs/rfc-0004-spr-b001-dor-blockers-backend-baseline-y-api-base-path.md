# RFC-0004 — Bloqueos DoR de SPR-B001 (baseline backend y API base path)

## 1) Contexto
Durante la validacion DoR de `docs/sprints/spr-b001.md` se detectaron bloqueos que impiden ejecutar el sprint sin suposiciones.

## 2) Bloqueos detectados

### B1) Baseline backend inexistente en el repo
`SPR-B001` asume backend Spring Boot/Flyway ya presente, pero no existen artefactos base en el repositorio:
- `pom.xml` (ausente)
- `mvnw` / `mvnw.cmd` (ausentes)
- directorio backend (`backend/`, `apps/backend/` o `services/backend/`, ausentes)

Impacto:
- No es posible implementar ni probar auth/scoping/OpenAPI del sprint sin crear primero baseline tecnico.

### B2) Contradiccion de contrato en API base path
- `docs/03-arquitectura.md` y `docs/08-runbook.md` definen versionado en `/api/v1/...`.
- `docs/sprints/spr-b001.md` definia endpoints en `/api/...` (sin version).

Impacto:
- Riesgo de implementar rutas incompatibles con arquitectura/runbook.

## 3) Decision
`SPR-B001` queda **BLOCKED** hasta resolver ambos bloqueos.

## 4) Plan de desbloqueo propuesto
1. Definir y aprobar baseline backend minimo (estructura, build tool, wrapper, arranque local).
2. Unificar contrato de rutas API para `SPR-B001` y documentos canonicos (arquitectura/runbook/sprint).
3. Revalidar DoR de `SPR-B001`.
4. Solo despues, ejecutar implementacion.

## 5) Resolucion aplicada
- Se agrego baseline backend con `pom.xml`, Maven Wrapper y estructura Spring Boot.
- Se unifico el contrato de rutas a `/api/v1` en `docs/sprints/spr-b001.md`.
- `SPR-B001` pasa a revalidacion DoR (ver status/log/rtm/state).

## 6) Cambios de trazabilidad aplicados
- `docs/status/status.md`: `SPR-B001` -> `READY_FOR_VALIDATION` tras ejecucion.
- `docs/traceability/rtm.md`: BRD-REQ-001/002/020/021/022 -> `READY_FOR_VALIDATION` con evidencia.
- `docs/log/log.md`: entrada append-only de ejecucion `SPR-B001`.
- `docs/state/state.md`: snapshot actualizado con resultado.

<!-- EOF -->
