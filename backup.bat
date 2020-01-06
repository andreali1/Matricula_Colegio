@echo off
c:
cd /
IF NOT EXIST POSTGRESQL MKDIR POSTGRESQL
cd POSTGRESQL
IF NOT EXIST POSTGRESQL MKDIR Backup
cd C:\Program Files\PostgreSQL\9.2\bin
pg_dump -U postgres  colegio > c:/POSTGRESQL/Backup/backup.sql
explorer.exe C:\POSTGRESQL\Backup
exit