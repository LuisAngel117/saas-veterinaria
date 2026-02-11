$ErrorActionPreference = "Stop"

$baseUrl = $env:API_BASE_URL
if ([string]::IsNullOrWhiteSpace($baseUrl)) {
  $baseUrl = "http://localhost:8080"
}

Write-Host "==> Health"
try {
  $health = Invoke-RestMethod -Method Get -Uri "$baseUrl/actuator/health" -TimeoutSec 10
  Write-Host "OK health" ($health.status)
} catch {
  Write-Host "FAIL health" $_.Exception.Message
  exit 1
}

Write-Host "==> Login"
$loginBody = @{ email = "recepcion@demo.local"; password = "Demo1234!" } | ConvertTo-Json
$login = Invoke-RestMethod -Method Post -Uri "$baseUrl/api/v1/auth/login" -ContentType "application/json" -Body $loginBody
$access = $login.accessToken
$branchId = $login.branchId

Write-Host "==> /me without X-Branch-Id (expect 400)"
try {
  Invoke-RestMethod -Method Get -Uri "$baseUrl/api/v1/me" -Headers @{ Authorization = "Bearer $access" }
  Write-Host "FAIL expected 400"
  exit 1
} catch {
  Write-Host "OK expected 400"
}

Write-Host "==> /me with correct X-Branch-Id (expect 200)"
$me = Invoke-RestMethod -Method Get -Uri "$baseUrl/api/v1/me" -Headers @{ Authorization = "Bearer $access"; "X-Branch-Id" = $branchId }
Write-Host "OK me" $me.user.email

Write-Host "==> Refresh token"
$refreshBody = @{ refreshToken = $login.refreshToken } | ConvertTo-Json
$refresh = Invoke-RestMethod -Method Post -Uri "$baseUrl/api/v1/auth/refresh" -ContentType "application/json" -Body $refreshBody

Write-Host "==> Logout"
$logoutBody = @{ refreshToken = $refresh.refreshToken } | ConvertTo-Json
Invoke-RestMethod -Method Post -Uri "$baseUrl/api/v1/auth/logout" -ContentType "application/json" -Body $logoutBody | Out-Null
Write-Host "OK logout"

Write-Host "==> Refresh revoked token (expect 401)"
try {
  $revokedBody = @{ refreshToken = $refresh.refreshToken } | ConvertTo-Json
  Invoke-RestMethod -Method Post -Uri "$baseUrl/api/v1/auth/refresh" -ContentType "application/json" -Body $revokedBody
  Write-Host "FAIL expected 401"
  exit 1
} catch {
  Write-Host "OK expected 401"
}

Write-Host "DONE"
