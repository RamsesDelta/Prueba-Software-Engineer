#Prueba Software Engineer


###Carpeta 1
Contiene el ejerció siguiente:
Dado un arreglo bidimensional de N x M, desarrolle una función que genere un
arreglo unidimensional de 1 x NM en el cual los elementos correspondan a los
elementos del arreglo original obtenidos siguiendo una trayectoria de caracol en el
sentido de las manecillas del reloj
Solo es ejecutar y ingresar el numero de columnas y numero de filas, seguido de los numero que queremos colocarle.


###Carpeta 2
Contiene el ejercicio que consiste en diagramar un sistema de tienda en línea para una empresa de venta de artesanías.

###Carpeta 3

Contiene el ejercicio de un sistema para gestionar el préstamo de libros de una biblioteca.
Para poder ejecutar probar el ejercicio es necesario seguir los pasos
Para la parte back es necesario lo siguiente:

```
1.	Instalare la librería servicio-libreria-commons que se ubica dentro de la carpeta back, se tiene que instalar el jar con mvnw install -DskipTests a nivel de la capeta ejecutar ese comando, ya que es donde se encuentran algunas clases que comparten todos los ms
2.	Se tiene que abrir los proyecto con cualquier IDE de desarrollo, una vez abiertos se deberían ejecutar los proyectos que se encuentran en la carpeta back cuyos nombres son servicio-administrador, servicio-bibliotecario y servicio-lector. Es esencial que siempre este ejecutado el proyecto de servicio-administrador ya que lo consume el otro ms de servicio-lector
```

La base de datos que se usa el proyecto es MYSQL con los valores por defectos que se encuentran en el properties de cada proyecto que serían (usuario = root) y (password = root)
Tambien se cuenta con un archivo import.sql para insertar algunos datos de ejemplo en la base. Y además las tablas se crean al ejecutar cualquier proyecto de
Para la parte Front fue echo en react, todo el proyecto se encuentra dentro de la carpeta front
Para poder probar algunas funcionales del proyecto es necesario ingresar las siguientes urls


```
1.	/usuarios :  Para la gestión de usuarios
2.	/libros : Para la gestión de libros
3.	/solicitar-libro/:id : para que un Lector pueda solicitar un libro
4.	/historial-prestado/:id  : donde el Lector pueda consultar todos los libros que a pedido prestado 
5.	/registro-lector: Para que un lector se pueda registrar y a su vez pueda solicitar préstamos de libros 
6.	/login : para validar que el usuario este registrado 
7.	/solicitudes : donde el Bibliotecario será el encargado de menguar los préstamos de los libros
```

Para la urla que contiene el (:id) se tendrá que remplazar por un numero que corresponde al id del Lector 
Y al principio de cada url se debería colocar el localhost que levanta react
