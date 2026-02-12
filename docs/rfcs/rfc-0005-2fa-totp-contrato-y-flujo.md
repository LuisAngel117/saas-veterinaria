# RFC-0005 — Contrato 2FA TOTP (login challenge + endpoints + datos)

**Estado:** ACCEPTED  
**Fecha:** 2026-02-11  
**Autor:** Codex

## 1) Contexto
- BRD-REQ-004 exige 2FA TOTP para ADMIN/SUPERADMIN.
- Seguridad/Arquitectura describen: enrolar, validar en login, reset con auditoria.
- No existe contrato detallado de endpoints, payloads, errores, ni modelo de datos para 2FA.
- Sprint SPR-B003 requiere implementar 2FA sin inventar contrato.

## 2) Problema
No hay definicion canonica de:
- Endpoints y payloads de enrolamiento/confirmacion/reset.
- Respuesta y codigo de error para el login con challenge.
- Modelo de datos (campos de usuario / tablas) para secreto TOTP y estado.
- Como cumplir "reason required + auditoria" en AUTH_2FA_RESET antes de SPR-B004.

Sin estas definiciones, el DoR de SPR-B003 falla y no se puede implementar.

## 3) Opciones
- Opcion A (separar enrolamiento/confirmacion + challenge por login):
  - POST /api/v1/auth/2fa/enroll (auth requerida, permisos AUTH_2FA_ENROLL)
  - POST /api/v1/auth/2fa/confirm (code TOTP para activar)
  - POST /api/v1/auth/2fa/verify (challengeId + code) para completar login
  - POST /api/v1/auth/2fa/reset (AUTH_2FA_RESET + reason)
  - Login con credenciales validas y 2FA activo -> 409 Problem Details (code=AUTH_2FA_REQUIRED) con challengeId

- Opcion B (login con flag 2FA requerido, sin challengeId):
  - Login retorna 401/403 con flag 2fa_required
  - Endpoint verify recibe email+password+code en un solo paso
  - Menos estados, pero expone mas superficie y no alinea con "challenge"

## 4) Decision propuesta
Adoptar Opcion A para mantener el flujo de challenge y separar enrolamiento.
- Login devuelve 409 Problem Details con code=AUTH_2FA_REQUIRED y challengeId.
- /auth/2fa/verify intercambia challengeId + code por access/refresh.
- Recovery codes: fuera de alcance v1 (no implementar en SPR-B003).
- AUTH_2FA_RESET requiere reason y auditoria (ver punto 5).
- Challenge TTL: 5 minutos, un solo uso.
- En login, la seleccion de branch se resuelve antes del challenge y se guarda en el challenge (branch_id).
- TOTP: SHA1, 6 digitos, step 30s, ventana +/-1 step.
- Secreto TOTP: base32 almacenado en DB (sin cifrado v1); hardening posterior en SPR-B019.
- Auditoria: se crea audit_event minimo en SPR-B003 solo para AUTH_2FA_RESET (framework completo sigue en SPR-B004).

## 5) Impacto
- Impacto tecnico:
  - Agregar campos a user_account: totp_enabled, totp_secret (base32), totp_verified_at.
  - Agregar tabla auth_2fa_challenge (id, user_id, branch_id, expires_at, used_at, created_at).
  - Agregar tabla audit_event (uso minimo para AUTH_2FA_RESET).
  - Endpoints nuevos bajo /api/v1/auth/2fa/*.
- Impacto UX:
  - UI debe manejar challengeId y pedir codigo TOTP.
- Impacto seguridad:
  - Secreto TOTP almacenado base32 en v1; hardening posterior.
  - Challenge debe expirar y ser de un solo uso.
- Impacto datos/migraciones:
  - Nueva migracion para campos 2FA y tabla de challenge.
  - AUTH_2FA_RESET requiere auditoria (ver decision abajo).

## 6) Plan de implementacion
1) Aplicar contrato Opcion A con challenge TTL 5m y branch_id en challenge.
2) Implementar audit_event minimo para AUTH_2FA_RESET.
3) Mantener recovery codes fuera de alcance v1.
4) Implementar en codigo + tests + smoke.

## 7) Riesgos
- Implementar reset sin auditoria viola contrato de seguridad.
- Almacenar secreto TOTP sin cifrado es riesgo.
- Sin challenge, el flujo puede derivar en endpoints no alineados con docs.

## 8) Archivos a cambiar (si se aprueba)
- docs/sprints/SPR-B003.md
- docs/05-seguridad.md (si se ajustan detalles de flujo)
- docs/03-arquitectura.md (detalle de endpoints)
- src/main/java/** (auth + security)
- src/main/resources/db/migration/* (2FA fields + challenge)
- docs/status/status.md, docs/log/log.md, docs/traceability/rtm.md, docs/state/state.md

<!-- EOF -->
