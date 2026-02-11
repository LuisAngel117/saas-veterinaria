# AUDIT-FRONT-001 - Resumen tecnico y auditoria del plan maestro FRONT

## A) Identidad y lock (repo/branch/timezone/ui_language)

| Item | Valor observado | Fuente | Resultado |
|---|---|---|---|
| repo_url | https://github.com/LuisAngel117/saas-veterinaria.git | `git remote -v` + `project-lock.md` | OK |
| branch | main | `git rev-parse --abbrev-ref HEAD` + `project-lock.md` | OK |
| timezone | America/Guayaquil | `project-lock.md` | OK |
| ui_language | Espanol | `project-lock.md` | OK |
| lock path canonico | `docs/project-lock.md` referenciado pero inexistente; lock real en `project-lock.md` | `AGENTS.md` + `docs/00-indice.md` + lectura obligatoria | INCONSISTENCIA |

## B) Resumen ejecutivo (objetivo FRONT y conexion con BACK)

- El FRONT busca cerrar P0/P1 con flujos integrados contra backend, UX en espanol y demo vendible (`docs/sprints/spr-master-front.md`, seccion "Objetivo del plan").
- El master FRONT esta en DRAFT y no debe ejecutarse hasta aceptacion textual del usuario (`docs/sprints/spr-master-front.md`, seccion "Regla de aceptacion").
- La integracion FRONT depende de backend minimo SPR-B001 o handoff equivalente (`docs/sprints/spr-master-front.md`, SPR-F001).
- El contrato UX exige login, selector de sucursal, guards y manejo de errores claros (`docs/07-ux-ui-parte-a.md`, seccion "Navegacion"; `docs/07-ux-ui-parte-b.md`, seccion "Reglas visuales").
- Scoping y seguridad de integracion estan definidos por claim `branch_id` + header `X-Branch-Id` con 400/403 (`docs/03-arquitectura.md`, seccion "Tenancy & scoping"; `docs/02-brd.md`, seccion "Scoping").
- El flujo UX core esperado es cita -> check-in -> atencion SOAP -> factura -> pago -> reporte (`docs/07-ux-ui-parte-a.md`, seccion "Flujos criticos").
- Hay gaps bloqueantes de integracion real: handoff backend->frontend esta en `TBD` completo (`docs/handoff/handoff-back-to-front.md`).
- Existen inconsistencias contractuales que afectan frontend: estados de cita (`IN_SERVICE` vs `IN_ATTENTION`) y estados/terminologia de factura (`VOID` vs `ANNULED`/`ANNUL`).

## C) Decisiones cerradas (ADRs)

- [ADR-0001 Stack tecnico](../decisions/adr-0001-stack.md)
- [ADR-0002 Arquitectura monolito modular](../decisions/adr-0002-arquitectura.md)
- [ADR-0003 Tenancy/Scoping](../decisions/adr-0003-tenancy-scoping.md)
- [ADR-0004 Seguridad/Auth](../decisions/adr-0004-seguridad-auth.md)
- [ADR-0005 Auditoria before/after](../decisions/adr-0005-auditoria.md)
- [ADR-0006 UX principios](../decisions/adr-0006-ux-principios.md)
- [ADR-0007 Walking skeleton temprano](../decisions/adr-0007-walking-skeleton.md)

## D) Mapa de requerimientos FRONT (RTM + master FRONT)

| BRD-REQ | RTM (sprint FRONT) | Master FRONT |
|---|---|---|
| 001 | SPR-F001 | SPR-F001 |
| 002 | SPR-F001 | SPR-F001 |
| 003 | SPR-F002 | SPR-F002 |
| 004 | (sin sprint FRONT en RTM) | (sin cierre explicito en master FRONT) |
| 007 | SPR-F003 | SPR-F003 |
| 008 | SPR-F004 | SPR-F004 |
| 009 | SPR-F005 | SPR-F005 |
| 010 | SPR-F006 | SPR-F006 |
| 011 | SPR-F007,F008 | SPR-F007 + SPR-F008 |
| 012 | SPR-F009 | SPR-F009 |
| 013 | SPR-F010 | SPR-F010 |
| 014 | SPR-F010 | SPR-F010 |
| 015 | SPR-F011 | SPR-F011 |
| 016 | SPR-F010 | SPR-F010 |
| 017 | SPR-F012 | SPR-F012 |
| 018 | SPR-F013 | SPR-F013 |
| 019 | SPR-F001..RC002 | SPR-RC002 (y dependencias previas) |
| 022 | (sin sprint FRONT en RTM) | SPR-F001 |
| 023 | SPR-F014 | SPR-F014 |
| 024 | SPR-F014 | SPR-F014 |
| 025 | SPR-F001..F015 | SPR-F001/F002/F003/F007/F015 |

Observaciones del mapeo:
- Gap de trazabilidad: `BRD-REQ-004` (2FA) y `BRD-REQ-022` (Problem Details) no tienen sprint FRONT explicito en RTM aunque el master FRONT si incorpora manejo de auth/errores en F001.

## E) Auditoria de coherencia

### 1) UI en espanol, UX vendible, validaciones
- Estado: OK
- Referencias:
  - `docs/07-ux-ui-parte-a.md`, secciones "Principios UX" y "Flujos criticos".
  - `docs/07-ux-ui-parte-b.md`, secciones "Reglas visuales" y "Tabla pantalla->acciones".
  - `docs/decisions/adr-0006-ux-principios.md`.

### 2) Auth/refresh + guards + selector de sucursal
- Estado: OK
- Referencias:
  - `docs/sprints/spr-master-front.md`, SPR-F001.
  - `docs/03-arquitectura.md`, seccion "Resumen" y "Diagrama logico".
  - `docs/05-seguridad.md`, secciones "Auth flows" y "2FA TOTP".

### 3) Scoping estricto (`X-Branch-Id`) + errores 400/403 y manejo UX
- Estado: OK
- Referencias:
  - `docs/02-brd.md`, seccion "Scoping (branch)".
  - `docs/03-arquitectura.md`, seccion "Tenancy & scoping".
  - `docs/07-ux-ui-parte-b.md`, seccion "Reglas visuales" (mensajes 400/403/409).
  - `docs/decisions/adr-0003-tenancy-scoping.md`.

### 4) Problem Details RFC7807 en UI (mensajes claros)
- Estado: OK
- Referencias:
  - `docs/03-arquitectura.md`, seccion "Errores: Problem Details (RFC 7807)".
  - `docs/sprints/spr-master-front.md`, SPR-F001.
  - `docs/07-ux-ui-parte-b.md`, seccion "Reglas visuales".

### 5) RBAC UI (ocultar/deshabilitar + no autorizado)
- Estado: OK
- Referencias:
  - `docs/sprints/spr-master-front.md`, SPR-F002.
  - `docs/10-permisos.md`, secciones "Permisos" y "Matriz rol->permisos".
  - `docs/07-ux-ui-parte-a.md`, principio "Permisos transparentes".

### 6) Flujos integrados agenda semana + crear cita + 409 solape + workflow estados
- Estado: INCONSISTENCIA
- Hallazgo:
  - BRD/dominio A usan estado `IN_SERVICE` para cita.
  - Project lock y dominio B usan `IN_ATTENTION`.
  - FRONT define acciones start/close y botones por estado, pero la nomenclatura de estado no esta unificada.
- Referencias:
  - `docs/02-brd.md`, seccion "Agenda / no-solape".
  - `docs/06-dominio-parte-a.md`, seccion "appointment (cita)".
  - `docs/06-dominio-parte-b.md`, seccion "appointment.status".
  - `project-lock.md`, bloque `agenda_contract`.
  - `docs/sprints/spr-master-front.md`, SPR-F003 y SPR-F004.

### 7) Flujos clinica SOAP + adjuntos + cierre/reapertura (reason)
- Estado: OK
- Referencias:
  - `docs/sprints/spr-master-front.md`, SPR-F007/F008/F009.
  - `docs/07-ux-ui-parte-b.md`, tabla (Atencion Cerrar/Reabrir).
  - `docs/02-brd.md`, BRD-REQ-011 y BRD-REQ-012.

### 8) Inventario + overrides (reason)
- Estado: OK
- Referencias:
  - `docs/sprints/spr-master-front.md`, SPR-F011.
  - `docs/07-ux-ui-parte-b.md`, tabla (Inventario Ajuste/Override).
  - `docs/10-permisos.md`, permisos `INVENTORY_ADJUST` / `INVENTORY_OVERRIDE_NEGATIVE`.

### 9) Facturacion + pagos parciales/mixtos + void con reason
- Estado: INCONSISTENCIA
- Hallazgo:
  - BRD usa estado `VOID`.
  - Dominio usa `ANNULED` + `annulled_reason`.
  - Permisos/UI usan accion `INVOICE_ANNUL` y termino "anular".
- Referencias:
  - `docs/02-brd.md`, seccion "Facturacion".
  - `docs/06-dominio-parte-b.md`, secciones "invoice.status" e "invoice".
  - `docs/10-permisos.md`, seccion "Facturacion".
  - `docs/sprints/spr-master-front.md`, SPR-F012/F013.

## F) Huecos (gaps) detectados

### 1) TBD encontrados
- `docs/handoff/handoff-back-to-front.md`: todas las secciones clave en `TBD` (resumen, endpoints reales, errores, seeds, env, runbook, smoke, estado por sprint).
- `docs/rfcs/rfc-0001-template.md`: `TBD` en campos del template (esperable por plantilla).
- `docs/log/log.md`: salidas con "PEGAR OUTPUT AQUÍ" en entradas historicas.
- `docs/status/status.md`: evidencias `TBD` en T1..T4.

### 2) Contratos API insuficientes para integrar sin suposiciones
- No existe contrato endpoint por endpoint con request/response/error codes por modulo frontend.
- `docs/03-arquitectura.md` da convenciones y ejemplos, pero no especifica payloads finales por flujo.
- `docs/08-runbook.md` lista comandos/objetivos y endpoints esperados, no contratos de integracion completos.

### 3) Handoff BACK->FRONT
- Estado: GAP BLOQUEANTE
- Justificacion:
  - DoR para FRONT exige handoff actualizado si integra (`docs/quality/definition-of-ready.md`, seccion UX/contratos).
  - `docs/handoff/handoff-back-to-front.md` esta totalmente en `TBD`.

### 4) Decisiones faltantes que bloquearian SPR-F001
- Aceptacion formal del master FRONT aun pendiente (`docs/sprints/spr-master-front.md`, seccion "Regla de aceptacion"; `docs/state/state.md`, seccion "Proximo paso recomendado").
- Handoff real inexistente (bloquea integracion backend real).
- Inconsistencia de lock path (`docs/project-lock.md` referenciado, inexistente).
- Gap de trazabilidad RTM en `BRD-REQ-004` y `BRD-REQ-022` para FRONT.

## G) Readiness de SPR-F001 (DoR)

Resultado global DoR SPR-F001: FAIL

| Item DoR | Resultado | Evidencia |
|---|---|---|
| Sprint ID/titulo existe | PASS | `docs/sprints/spr-master-front.md`, SPR-F001 |
| Declara BRD-REQ objetivo | PASS | `docs/sprints/spr-master-front.md`, SPR-F001 |
| Sin contradicciones con BRD/arquitectura | FAIL | Inconsistencias de estados agenda/factura (seccion E) |
| Scoping branch/global claro | PASS | `docs/02-brd.md`, `docs/03-arquitectura.md`, ADR-0003 |
| Regla `X-Branch-Id` clara | PASS | `docs/02-brd.md` + `docs/03-arquitectura.md` |
| Permisos necesarios definidos | PASS | `docs/10-permisos.md` |
| Handoff backend->frontend actualizado (si integra) | FAIL | `docs/handoff/handoff-back-to-front.md` en `TBD` |
| Errores esperados definidos | PASS | `docs/03-arquitectura.md` + `docs/07-ux-ui-parte-b.md` |
| AC verificables (checklist) | FAIL | SPR-F001 no incluye checklist AC detallado |
| Smoke/manual con comandos exactos | FAIL | No hay smoke FRONT detallado por sprint |
| Evidencia esperada en LOG/STATUS/RTM | PASS | DoD/RTM/status/log definidos |
| Master FRONT aceptado por usuario | FAIL | `docs/state/state.md` y `spr-master-front.md` indican pendiente |

Si FAIL (faltantes exactos):
- Exigir handoff backend->frontend real.
- Aceptar formalmente el master FRONT.
- Normalizar estados agenda/factura por RFC/ADR.
- Completar AC/checklist y smoke exacto para SPR-F001.

## H) RFC/ADR recomendados

1) RFC: unificacion de estado de cita (`IN_SERVICE` vs `IN_ATTENTION`) para contratos BACK/FRONT.
2) RFC: normalizacion de estado/terminologia de factura (`VOID` vs `ANNULED`/`ANNUL`).
3) RFC: handoff backend->frontend v1 (endpoints reales, payloads, errores, seeds, runbook, smoke).
4) RFC corto de gobernanza: normalizar lock path (`docs/project-lock.md` vs `project-lock.md`).
5) Ajuste RTM por RFC: alinear trazabilidad FRONT para `BRD-REQ-004` y `BRD-REQ-022`.

## I) Proximos pasos (maximo 5)

1. Exigir handoff BACK->FRONT real antes de arrancar integracion.
2. Normalizar estados de agenda y factura en un vocabulario unico.
3. Alinear RTM para cubrir explicitamente 2FA/Problem Details en ruta FRONT.
4. Publicar SPR-F001 detallado (AC + smoke + evidencia).
5. Obtener aceptacion textual del plan maestro FRONT y recien iniciar ejecucion.

<!-- EOF -->
