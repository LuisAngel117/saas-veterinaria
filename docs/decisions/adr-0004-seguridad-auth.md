# ADR-0004 — Seguridad y Auth

## Contexto
Necesitamos auth robusta para demo vendible, sin depender de terceros.

## Decisión
- Login con usuario/password.
- JWT access (1h) + refresh (7d) con rotación.
- Lockout: 4 intentos fallidos → 15 min.
- Password policy: mínimo 10 chars + mayúscula + minúscula + número + símbolo.
- 2FA TOTP (RFC 6238) para ADMIN/SUPERADMIN.
- CORS local: permitir localhost front→back.
- Errores API: Problem Details (RFC 7807).

## Consecuencias
- Auditoría de auth (login/logout/refresh).
- Flujos de 2FA deben documentarse y probarse en local.

## Alternativas descartadas
TBD

## Fecha
2026-02-11

<!-- EOF -->
