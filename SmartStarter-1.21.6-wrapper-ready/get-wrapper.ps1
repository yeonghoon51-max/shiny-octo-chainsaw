param(
  [string]$SearchRoot = "$env:USERPROFILE"
)
Write-Host "Searching for existing gradle-wrapper.jar under: $SearchRoot"
$matches = Get-ChildItem -Path $SearchRoot -Filter "gradle-wrapper.jar" -Recurse -ErrorAction SilentlyContinue | Select-Object -First 10
if (-not $matches) {
  Write-Host "No gradle-wrapper.jar found automatically."
  $manual = Read-Host "Enter a full path to an existing gradle-wrapper.jar (or Ctrl+C to cancel)"
  if (-not (Test-Path $manual)) { Write-Error "File not found: $manual"; exit 1 }
  Copy-Item -LiteralPath $manual -Destination ".\gradle\wrapper\gradle-wrapper.jar" -Force
  Write-Host "Copied wrapper JAR to .\gradle\wrapper\ ."
  exit 0
}
Write-Host "Found the following:"
$idx = 0
$matches | ForEach-Object { Write-Host "[$idx] $($_.FullName)"; $idx++ }
$sel = Read-Host "Select index to copy"
if ($sel -notmatch '^\d+$') { Write-Error "Invalid selection"; exit 1 }
$sel = [int]$sel
if ($sel -lt 0 -or $sel -ge $matches.Count) { Write-Error "Out of range"; exit 1 }
$chosen = $matches[$sel].FullName
Copy-Item -LiteralPath $chosen -Destination ".\gradle\wrapper\gradle-wrapper.jar" -Force
Write-Host "Copied: $chosen -> .\gradle\wrapper\gradle-wrapper.jar"
