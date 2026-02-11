# AUDIT-BACK-001 - Resumen tecnico y auditoria del plan maestro BACK

## A) Identidad y lock (verificacion repo/branch/timezone/ui_language/code_language)

| Item | Valor observado | Fuente | Resultado |
|---|---|---|---|
| repo_url | https://github.com/LuisAngel117/saas-veterinaria.git | `git remote -v` + `project-lock.md` | OK |
| branch | main | `git rev-parse --abbrev-ref HEAD` + `project-lock.md` | OK |
| timezone | America/Guayaquil | `project-lock.md` | OK |
| ui_language | Espanol | `project-lock.md` | OK |
| code_language | Ingles | `project-lock.md` | OK |
| lock path canonico | `docs/project-lock.md` (referenciado) vs `project-lock.md` (existente) | `AGENTS.md`, `docs/00-indice.md`, lectura obligatoria | INCONSISTENCIA |

Observacion de lock: la lectura obligatoria y el indice exigen `docs/project-lock.md`, pero ese archivo no existe; el lock real vive en `project-lock.md`.

## B) Resumen ejecutivo (BACK)

- El contrato funcional esta definido con BRD-REQ estables `BRD-REQ-001..027` en `docs/02-brd.md`.
- El plan maestro BACK existe y permanece en DRAFT hasta aceptacion explicita del usuario (`docs/sprints/spr-master-back.md`).
- RTM tiene mapeo inicial de casi todo P0/P1 hacia sprints, pero evidencia/estado tecnico esta en `TBD` o `NOT_STARTED`.
- Scoping esta bien cerrado a nivel de reglas (JWT `branch_id` + `X-Branch-Id`, 400/403) y referenciado por ADR.
- Seguridad base esta cerrada documentalmente (lockout, refresh rotado, 2FA solo ADMIN/SUPERADMIN, reason required).
- Hay incoherencia real en estados de cita entre documentos de contrato (IN_SERVICE vs IN_ATTENTION).
- Hay incoherencia real en estados de factura (VOID vs ANNULED/ANNUL).
- El master BACK cubre P0 backend, pero P1 `BRD-REQ-024` y `BRD-REQ-025` no tienen ruta BACK explicita (solo FRONT).
- Para iniciar SPR-B001, DoR global no pasa por gobernanza (masters aun no aceptados) y por falta de AC/smoke con comandos exactos a nivel sprint.

## C) Decisiones cerradas (ADRs)

- [ADR-0001 Stack tecnico](../decisions/adr-0001-stack.md)
- [ADR-0002 Arquitectura monolito modular](../decisions/adr-0002-arquitectura.md)
- [ADR-0003 Tenancy/Scoping](../decisions/adr-0003-tenancy-scoping.md)
- [ADR-0004 Seguridad/Auth](../decisions/adr-0004-seguridad-auth.md)
- [ADR-0005 Auditoria before/after](../decisions/adr-0005-auditoria.md)
- [ADR-0006 Principios UX](../decisions/adr-0006-ux-principios.md)
- [ADR-0007 Walking skeleton temprano](../decisions/adr-0007-walking-skeleton.md)

## D) Mapa de requerimientos (P0/P1) y caida en master BACK

Fuente cruzada: `docs/02-brd.md` + `docs/traceability/rtm.md` + `docs/sprints/spr-master-back.md`.

| BRD-REQ | Prioridad | RTM (sprint) | Sprint BACK master |
|---|---|---|---|
| 001 | P0 | SPR-B001 | SPR-B001 |
| 002 | P0 | SPR-B001 | SPR-B001 |
| 003 | P0 | SPR-B002 | SPR-B002 |
| 004 | P0 | SPR-B003 | SPR-B003 |
| 005 | P0 | SPR-B004 | SPR-B004 |
| 006 | P0 | SPR-B005 | SPR-B005 |
| 007 | P0 | SPR-B006 | SPR-B006 (+ completitud en B007) |
| 008 | P0 | SPR-B007 | SPR-B007 |
| 009 | P0 | SPR-B008 | SPR-B008 |
| 010 | P0 | SPR-B009 | SPR-B009 |
| 011 | P0 | SPR-B010,B011 | SPR-B010 + SPR-B011 |
| 012 | P0 | SPR-B012 | SPR-B012 |
| 013 | P0 | SPR-B013 | SPR-B013 |
| 014 | P0 | SPR-B013 | SPR-B013 |
| 015 | P0 | SPR-B014 | SPR-B014 |
| 016 | P0 | SPR-B013,B015 | SPR-B013 + SPR-B015 |
| 017 | P0 | SPR-B016 | SPR-B016 |
| 018 | P0 | SPR-B017 | SPR-B017 |
| 019 | P0 | SPR-B001..RC001 | SPR-B019 + SPR-RC001 (con apoyo desde B001) |
| 020 | P0 | SPR-B001 | SPR-B001 |
| 021 | P0 | SPR-B001 + mantenimiento | SPR-B001 + SPR-RC001 |
| 022 | P0 | SPR-B001 + estandar | SPR-B001 + SPR-B019 |
| 023 | P1 | SPR-B018 | SPR-B018 |
| 024 | P1 | SPR-F014 | Sin ruta BACK explicita |
| 025 | P1 | SPR-F001..F015 | Sin ruta BACK explicita |

## E) Auditoria de coherencia (contradicciones)

### 1) Scoping: claim `branch_id` vs `X-Branch-Id` (400/403)
- Estado: OK
- Evidencia:
  - `docs/02-brd.md` seccion "Scoping (branch)"
  - `docs/03-arquitectura.md` seccion "Tenancy & scoping"
  - `docs/decisions/adr-0003-tenancy-scoping.md`

### 2) Agenda: no-solape Sala+Vet, estados exactos, check-in separado, slot 30m, buffer 10m
- Estado: INCONSISTENCIA
- Hallazgo:
  - `docs/02-brd.md` y `docs/06-dominio-parte-a.md` usan `IN_SERVICE`.
  - `project-lock.md` y `docs/06-dominio-parte-b.md` usan `IN_ATTENTION`.
  - Check-in separado si es consistente, pero el nombre del estado de "atencion" no.
- Evidencia:
  - `docs/02-brd.md` seccion "Agenda / no-solape"
  - `docs/06-dominio-parte-a.md` seccion "appointment (cita)"
  - `docs/06-dominio-parte-b.md` seccion "appointment.status"
  - `project-lock.md` bloque `agenda_contract`
  - `docs/03-arquitectura.md` seccion "Reglas clave de agenda"

### 3) Roles/permisos + acciones sensibles con reason required
- Estado: OK
- Evidencia:
  - `docs/02-brd.md` (RBAC y acciones sensibles)
  - `docs/05-seguridad.md` seccion "Acciones sensibles"
  - `docs/10-permisos.md` secciones "Permisos" y "Acciones sensibles"
  - `docs/decisions/adr-0004-seguridad-auth.md`

### 4) 2FA TOTP solo ADMIN/SUPERADMIN
- Estado: OK
- Evidencia:
  - `docs/02-brd.md` BRD-REQ-004
  - `docs/03-arquitectura.md` seccion "2FA TOTP"
  - `docs/05-seguridad.md` seccion "2FA TOTP (ADMIN/SUPERADMIN)"
  - `docs/decisions/adr-0004-seguridad-auth.md`

### 5) Auditoria before/after + retencion 90 dias
- Estado: OK
- Evidencia:
  - `docs/02-brd.md` BRD-REQ-005
  - `docs/05-seguridad.md` seccion "Auditoria"
  - `docs/decisions/adr-0005-auditoria.md`
  - `docs/sprints/spr-master-back.md` SPR-B004

### 6) Facturacion: IVA global solo SUPERADMIN + pagos parciales/mixtos + void
- Estado: INCONSISTENCIA
- Hallazgo:
  - BRD define estado de factura `VOID`.
  - Dominio define `ANNULED` y campo `annulled_reason`.
  - Permisos/acciones usan verbo `ANNUL` (`INVOICE_ANNUL`).
  - Master BACK mezcla termino `void` para BRD-REQ-018.
- Evidencia:
  - `docs/02-brd.md` seccion "Facturacion"
  - `docs/06-dominio-parte-b.md` secciones "invoice.status" e "invoice"
  - `docs/10-permisos.md` seccion "Facturacion"
  - `docs/sprints/spr-master-back.md` SPR-B017

### 7) Inventario: BOM + consumo automatico + override por stock con reason+audit
- Estado: OK
- Evidencia:
  - `docs/02-brd.md` seccion "Inventario" + BRD-REQ-016
  - `docs/03-arquitectura.md` seccion "Inventario y consumo por BOM"
  - `docs/06-dominio-parte-b.md` secciones `service_bom_item`, `stock_movement`, invariantes
  - `docs/10-permisos.md` permisos `INVENTORY_ADJUST` / `INVENTORY_OVERRIDE_NEGATIVE`

## F) Huecos (gaps) detectados

### 1) Lista de TBD encontrados
- `docs/05-seguridad.md`: "Rate limit per endpoint: TBD".
- `docs/sprints/spr-master-back.md`: SPR-B004 indica "job TBD" para retencion/purga.
- `docs/traceability/rtm.md`: evidencia de commit en `TBD` y estados `NOT_STARTED` para todos los BRD-REQ.
- `docs/status/status.md`: evidencias de T1..T4 en `TBD`.
- `docs/log/log.md`: bloques de output en "PEGAR OUTPUT AQUI".
- `docs/rfcs/rfc-0001-template.md`: placeholders `TBD` (esperable por ser plantilla).

### 2) BRD-REQ sin ruta clara
- `BRD-REQ-024` y `BRD-REQ-025`: en RTM caen solo en FRONT; no hay sprint BACK explicito.
- `BRD-REQ-026` y `BRD-REQ-027`: RTM en `TBD` (sin ruta definida; placeholders online-only).

### 3) Endpoints/contratos insuficientes para implementar sin suposiciones
- No existe catalogo completo endpoint/metodo/request/response por sprint (solo ejemplos de recursos en `docs/03-arquitectura.md` y objetivos en `docs/08-runbook.md`).
- En SPR-B001 no hay AC detallados en checklist ni comandos smoke exactos por endpoint.
- Contrato de estados agenda/factura no esta normalizado en un unico vocabulario estable (ver inconsistencias en seccion E).

### 4) Decisiones faltantes que bloquearian SPR-B001
- Bloqueo de gobernanza: el master BACK esta en DRAFT y requiere aceptacion textual del usuario (`docs/state/state.md`, `docs/sprints/spr-master-back.md`).
- Inconsistencia de lock path: documentos mandan leer `docs/project-lock.md` pero ese archivo no existe; lock real en `project-lock.md`.
- Falta de AC y smoke con comandos exactos para SPR-B001 (DoR no completo).

## G) Readiness de SPR-B001 (DoR)

Resultado global DoR SPR-B001: FAIL

| Item DoR | Resultado | Evidencia |
|---|---|---|
| Sprint ID/titulo existe | PASS | `docs/sprints/spr-master-back.md` (SPR-B001) |
| Declara BRD-REQ objetivo | PASS | `docs/sprints/spr-master-back.md` (Cierra BRD-REQ-001/002/020/021/022) |
| Sin contradicciones con BRD/arquitectura | FAIL | Inconsistencias de contrato en estados agenda/factura (seccion E) |
| Branch-scoped/global claro | PASS | `docs/02-brd.md`, `docs/03-arquitectura.md`, ADR-0003 |
| Reglas claim/header claras | PASS | `docs/02-brd.md`, `docs/03-arquitectura.md` |
| Permisos necesarios definidos | PASS | `docs/10-permisos.md` (AUTH_*, BRANCH_*) |
| Problem Details definidos | PASS | `docs/03-arquitectura.md` seccion errores RFC 7807 |
| AC verificables (checklist) | FAIL | `docs/sprints/spr-master-back.md` no trae checklist AC detallado |
| Smoke/manual con comandos exactos | FAIL | master indica "smoke auth+scoping" sin comandos exactos |
| Evidencia a dejar en LOG/STATUS/RTM | PASS | gobernanza definida en DoD/RTM/status/log |
| Aceptacion de master BACK | FAIL | `docs/state/state.md` exige aceptacion textual antes de ejecutar |

Si FAIL (faltantes exactos):
- Resolver inconsistencia de estados (agenda/factura) por RFC/ADR.
- Aceptar formalmente el master BACK (texto exacto en state/master).
- Definir AC checklist y smoke de SPR-B001 con comandos exactos (sin suposiciones).

## H) RFC/ADR recomendados

1) RFC: unificacion de estados de cita (`IN_SERVICE` vs `IN_ATTENTION`) y diccionario canonico agenda.
2) RFC: unificacion de estados/terminologia de factura (`VOID` vs `ANNULED`/`ANNUL`) y permisos asociados.
3) RFC: contrato operativo de SPR-B001 (AC verificables + comandos smoke exactos + evidencia esperada).
4) RFC corto de gobernanza docs: normalizar path de lock (`docs/project-lock.md` vs `project-lock.md`).

## I) Proximos pasos (maximo 5)

1. Normalizar vocabulario de estados de agenda y factura via RFC y aplicar ajuste documental coherente.
2. Resolver el path canonico del lock y alinear AGENTS/indice/lectura obligatoria.
3. Publicar SPR-B001 detallado (AC + comandos smoke exactos + criterios de evidencia).
4. Obtener aceptacion textual del plan maestro BACK.
5. Solo despues, iniciar implementacion de SPR-B001 con DoR en PASS.

<!-- EOF -->
