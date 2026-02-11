# ADR-0004 — Seguridad/Auth

## Contexto
La demo debe ser vendible y segura: sesión robusta, lockout, 2FA para admins, y permisos por acción.

## Decisión
- JWT access (1h) + refresh (7d) con rotación
- Logout invalida refresh
- Lockout: 4 intentos → 15 min
- 2FA TOTP para ADMIN/SUPERADMIN
- Permisos por acción + reason required en sensibles
- Errores estándar: Problem Details

## Consecuencias
- Debe persistirse estado de refresh tokens (server-side) y lockout.
- UX de login contempla challenge 2FA.

## Alternativas descartadas
- Access token largo sin refresh (peor UX/seguridad).
- 2FA para todos (fricción innecesaria en v1).

## Fecha
2026-02-11

<!-- EOF -->
