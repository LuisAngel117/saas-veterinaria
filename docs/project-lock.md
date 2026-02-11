# Project Lock (identidad del repo)

project_name: SaaSVeterinaria
project_domain: SaaS veterinaria local-first/offline-first para gestion de clinica single-tenant y multi-sucursal
repo_url: https://github.com/LuisAngel117/saas-veterinaria.git
local_path: C:\Users\luisa\Proyecto\saas-veterinaria
primary_branch: main
timezone: America/Guayaquil
ui_language: Espanol
code_language: Ingles
lock_version: T5

agenda_contract:
  appointment_statuses: RESERVED|CONFIRMED|IN_ATTENTION|CLOSED|CANCELLED
  check_in_rule: evento separado (no es estado)

billing_contract:
  invoice_statuses: PENDING|PAID|ANNULLED
  annul_action_name: ANNUL (anular)

governance_contract:
  dor_dod_required: true
  rtm_required: true
  status_log_required: true
  adr_rfc_required_on_contract_changes: true

created_at: 2026-02-11T00:00:00-05:00
updated_at: 2026-02-11T09:00:00-05:00

Regla: antes de cualquier cambio, validar `git remote -v` y `git rev-parse --abbrev-ref HEAD` contra este lock.
- Si el remote actual NO coincide con repo_url: DETENER (repo mismatch).
- Prohibido mezclar dominios/proyectos en docs, commits o codigo.

<!-- EOF -->
