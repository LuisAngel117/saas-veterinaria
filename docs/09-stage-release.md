# Stage/Release (futuro online)

## 1) Qué cambia al ir online
- CORS restringido por dominios.
- Configuración por environment (local/stage/prod).
- Persistencia de adjuntos:
  - local: filesystem
  - online: TBD (S3 u otro) → RFC/ADR
- Seguridad:
  - secrets en vault/env manager
  - rotación de claves JWT
- Observabilidad:
  - logs centralizados, métricas.

## 2) Feature flags online-only (contrato)
- `FEATURE_REMINDERS_EXTERNAL`:
  - local: “pendiente de enviar” (cola local)
  - online: integración futura (email/SMS/WhatsApp)
- `FEATURE_SRI_EINVOICE`:
  - local: placeholder (config y estados)
  - online: integración futura con SRI

## 3) Checklist stage (cuando aplique)
- [ ] Runbook stage actualizado
- [ ] Variables env configuradas
- [ ] Migraciones Flyway corren en stage
- [ ] CORS correcto
- [ ] Feature flags configuradas
- [ ] Smoke scripts pasan contra stage
- [ ] Auditoría y permisos verificados

<!-- EOF -->
