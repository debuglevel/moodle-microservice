Write-Output "== docker-compose down --volumes"
docker-compose down --volumes

Write-Output "== docker-compose up --build"
docker-compose up --build

Write-Output "== pause"
pause
