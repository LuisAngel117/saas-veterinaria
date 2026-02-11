# ADR-0004 - Seguridad y Auth

## Estado
ACCEPTED

## Contexto
Se requiere seguridad robusta para demo local vendible y auditable.

## Decision
- Login usuario/password.
- JWT access (1h) + refresh (7d) con rotacion.
- Lockout de 4 intentos fallidos por 15 minutos.
- Password policy minima: 10 chars, mayuscula, minuscula, numero y simbolo.
- 2FA TOTP (RFC 6238) para ADMIN/SUPERADMIN.
- CORS local controlado para frontend autorizado.
- Errores estandarizados con RFC 7807.

## Consecuencias
- Eventos auth deben quedar auditados.
- Flujos de 2FA y recuperacion requieren evidencia de pruebas.

## Alternativas descartadas
- Auth sin 2FA para perfiles administrativos: descartado por riesgo.
- Tokens sin rotacion refresh: descartado por seguridad.

## Fecha
2026-02-11

<!-- EOF -->
