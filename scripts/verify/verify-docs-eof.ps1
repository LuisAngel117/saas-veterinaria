$ErrorActionPreference = "Stop"

if (-not (Test-Path -Path "docs" -PathType Container)) {
  Write-Host "docs directory not found."
  exit 1
}

$docsFiles = Get-ChildItem -Path "docs" -Recurse -File
$missing = @()

foreach ($file in $docsFiles) {
  $raw = Get-Content -Path $file.FullName -Raw
  $trimmed = $raw.TrimEnd("`r", "`n")
  if (-not $trimmed.EndsWith("<!-- EOF -->")) {
    $relative = Resolve-Path -Path $file.FullName -Relative
    $missing += $relative
  }
}

if ($missing.Count -gt 0) {
  Write-Host "EOF verification FAILED. Missing marker in:"
  $missing | ForEach-Object { Write-Host $_ }
  exit 1
}

Write-Host "EOF verification OK"
exit 0
