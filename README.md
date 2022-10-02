Como ejecutar proyecto.

Teniedo en cuenta la siguiente estructura de las carpetas:
Proyecto
  -- API
  -- APP
  -- DB
  
1. Genera un sql con instrucciones insert, este repetira las imagenes hasta conseguir un total de mas de 10000 registros.
    Tambien si por alguna razon no se genera el sql aqui hay un enlace para su descarga. https://drive.google.com/file/d/1lxJ6RKHRxV4uy5TzHvL8RbdkxG9cu_QL/view?usp=sharing

Ahora iniciar los contenedores en este solo son 2, la base de datos(MySQL) y el servidor (Java con JDK8), los puertos de los servicios a exponer se pueden cambiar en el archivo .env hubicado en la raiz de este proyecto.
Si por alguna razon los puertos estan ocupados por algun otro servicio deberan ser cambiados para 
correcto funcionamiento y sera preferibe eliminar los volumenes e imagenes si es que se habia ejecutado el docker compose para que los nuevos puertos se vean reflejados.

2. Clona el repositorio

3. Accede al proyecto y Ejecuta el docker compose

4. Descpues de que los contenedores esten activos, navega hasta el folder DB "PROYECT_FOLDER/DB" y ejecuta ./connect_db_container.sh, para conectar con la bash del contenedor

5. Carga el sql que generaste al principio ejecutando el siguiente script, espera hasta que termine el proceso.

./populate_data.sh

6. Abre localhost:8080 y deberias ver el proyecto angular.




