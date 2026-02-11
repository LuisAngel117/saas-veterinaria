$ErrorActionPreference = "Stop"

$root = Split-Path -Parent $PSScriptRoot
$docsPath = Join-Path $root "..\docs"

if (-not (Test-Path $docsPath)) {
  Write-Error "No existe la carpeta 'docs' en la raíz del repo (esperado en: $docsPath)."
}

$files = Get-ChildItem -Path $docsPath -Recurse -File -Filter *.md

$bad = @()

foreach ($f in $files) {
  $raw = Get-Content -LiteralPath $f.FullName -Raw

  # Debe terminar con <!-- EOF --> + opcional newline final
  if ($raw -notmatch "(?s)<!-- EOF -->\r?\n?$") {
    $bad += $f.FullName
  }
}

if ($bad.Count -gt 0) {
  Write-Host "ERROR: Estos archivos NO terminan con '<!-- EOF -->':"
  $bad | ForEach-Object { Write-Host " - $_" }
  exit 1
}

Write-Host "OK: Todos los docs .md bajo 'docs' terminan con '<!-- EOF -->'."
exit 0
