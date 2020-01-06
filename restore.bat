@echo off
c:
cd /
IF NOT EXIST POSTGRESQL MKDIR POSTGRESQL
cd POSTGRESQL
IF NOT EXIST Restore MKDIR Restore
cd C:\Program Files\PostgreSQL\9.2\bin
psql -d colegio -h localhost -U postgres -f c:/POSTGRESQL/Restore/backup.sql
exit