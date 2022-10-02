### Previous requirements

Install Docker [Docker](https://docs.docker.com )
### Como ejecutar este proyecto.


Teniedo en cuenta la siguiente estructura de las carpetas:

PROYECT_FOLDER


  -- API  
  -- APP  
  -- DB
  
  
1. Clona este repositorio

```
  git clone https://github.com/FernandoSC18/Album-Imagenes.git
```

2. Genera un sql con instrucciones insert, este repetira las imagenes hasta conseguir un total de mas de 10000 registros. para eso hubicate en el Folder PROYECT_FOLDER/DB "cd Album-Imagenes/DB"
    
```
  ./create_sql_populate.sh
```
Tambien si por alguna razón no se genera el sql aqui hay un enlace para su descarga. [decarga sql aqui](https://drive.google.com/file/d/1lxJ6RKHRxV4uy5TzHvL8RbdkxG9cu_QL/view?usp=sharing) Solo colocalo en el folder DB "PROYECT_FOLDER/DB"

Iniciar los contenedores, en este caso solo son 2, la base de datos(MySQL) y el servidor (Java con JDK8), los puertos de los servicios a exponer se pueden cambiar en el archivo .env hubicado en la raiz de este proyecto.
Si por alguna razon los puertos estan ocupados por algun otro servicio deberan ser cambiados para 
correcto funcionamiento y sera preferibe eliminar los volumenes e imagenes si es que se habia ejecutado el docker compose para que los nuevos puertos se vean reflejados.

3. En la raiz del proyecto se encuentra el archivo docker-compose.yml, navega hasta ahi y ejecutalo con:

```
  docker compose up --detach
```

4. Espera a que los dos conetenedores esten activos, despues navega hasta el folder DB "PROYECT_FOLDER/DB" y ejecuta connect_db_container.sh, para conectar con la bash del contenedor

```
  ./connect_db_container.sh
```
Nota: Los contenedores pueden demorar en levantarse, ejecuta el comando "docker container ls" y verifica el estatus UP.

5. Carga el sql que generaste al principio, hazlo ejecutando el siguiente script, espera hasta que termine el proceso.

```
  ./populate_data.sh
```
6. Abre localhost:8080 y deberias ver el proyecto angular.






