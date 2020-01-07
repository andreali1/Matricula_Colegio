## Sistema de Matricula
Es un sistema de escritorio, hecho en el lenguaje de java, que tiene las siguientes caracteristicas:
- [x] Utiliza una base de datos postgres 9.2, que tiene las siguientes tablas:
  - Alumnos.
  - Area Academica.
  - Asignaturas.
  - Calificaciones.
  - Grado.
  - Matricula.
  - Profesores.
- [x] Valida los campos de una forma ligera, para no tener errores al guardar.
- [x] No permite registrar dni ya grabado en la base de datos.
- [x] Tiene una interfaz simple.
- [x] Tiene una interfaz direferente para la secretaria y administrador.
- [x] Tiene una funcionalidad para restaurar y crear copia de seguridad.
## Como funciona
Para dar una descripción mas precisa, seguiremos los siguientes pasos:
1. Restaure la base de datos.
2. En el archivo de conexión, modificamos los datos de conexión segun la configuracion.
3. El logueo del usuario y contraseña, se encuentra en la tabla area academica.
4. Para la copia de seguridad, esta se creara en la particion C, en una carpeta **POSTGRESQL**, esta tendra dos carpetas:
  - Restore: Aqui se pega la restauración generado del backup. ***Importante: En caso de error no se restaurará la base de datos***
  - Backup: Aqui se situa el archivo generado del backup.
