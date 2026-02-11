$ErrorActionPreference = "Stop"

$requiredDirs = @(
  "docs",
  "docs/status",
  "docs/log",
  "docs/quality",
  "docs/traceability",
  "docs/state",
  "docs/handoff",
  "docs/decisions",
  "docs/rfcs",
  "docs/sprints",
  "scripts/verify",
  "scripts/smoke",
  "scripts/release"
)

$requiredFiles = @(
  "docs/status/status.md",
  "docs/log/log.md",
  "docs/state/project-lock.md",
  "scripts/verify/verify-docs-eof.ps1",
  "scripts/verify/preflight.ps1"
)

$missing = @()

foreach ($dir in $requiredDirs) {
  if (-not (Test-Path -Path $dir -PathType Container)) {
    $missing += "DIR:$dir"
  }
}

foreach ($file in $requiredFiles) {
  if (-not (Test-Path -Path $file -PathType Leaf)) {
    $missing += "FILE:$file"
  }
}

if ($missing.Count -gt 0) {
  Write-Host "Preflight FAILED. Missing items:"
  $missing | ForEach-Object { Write-Host $_ }
  exit 1
}

& "$PSScriptRoot/verify-docs-eof.ps1"
if ($LASTEXITCODE -ne 0) {
  exit $LASTEXITCODE
}

Write-Host "Preflight OK"
exit 0
