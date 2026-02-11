$ErrorActionPreference = "Stop"

Write-Host "==> Preflight: git status"
git status -sb

Write-Host "`n==> Preflight: git identity"
git config user.name
git config user.email

Write-Host "`n==> Preflight: git remote -v"
git remote -v

Write-Host "`n==> Preflight: branch"
git rev-parse --abbrev-ref HEAD

Write-Host "`n==> Preflight: verify docs EOF"
& "$PSScriptRoot\verify-docs-eof.ps1"

Write-Host "`nOK: Preflight completado."
