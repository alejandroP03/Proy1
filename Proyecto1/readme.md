## Table of Contents
1. [General Info](#general-info)
2. [Technologies](#technologies)
3. [Installation](#installation)
4. [Collaboration](#collaboration)
5. [FAQs](#faqs)
## Autenticación de los usuarios
***
Lo primero que la aplicacion imprime, es elegir la opcion para iniciar o registrarse en la aplicación.
En caso de que elija registrarse, pedirá un usuario y contraseña totalmente nuevo, y al ingresarlos inmediatamente
mostrará el menú del usuario sobre el cual se hizo el registro. Por otro lado, si se inicia sesion pide los mismos datos,
pero solamente podrá ingresar si el usuario ya está registrario, de lo contrario manda un error al usuario. Las pantallas de funciones
son diferentes para cada usuario, por lo que si se registra como adminsitrador, solo podrá ver las opciones de administrador.

<b> Notas: </b> 

1. Para elegir iniciar sesion o registrarse elija 1 o 2, el ingreso de otra entrada generará error en la aplicación.

2. Tenga en cuenta que al iniciar sesion no ingresa tal cual su usuario y contrasena, no va a poder ser autenticado en el sistema.
En otras palabras, importan las mayusculas y minusculas.


## Inicio de sesión como administrador
***
En este punto de la aplicacion, se muestran todas las funciones posibles que puede realizar un adminisrador. 
Para elegir una opcion, simplemente digite el indice sobre el cual se identifica cada opcion. Tenga en cuenta que 
si no digita un número del 1-7, entonces la aplicación lanzará un error.

### Mapeo de funciones disponibles
------  Inicio como administrador -------
1. Crear habitaciones (manual)
2. Cargar archivo habitaciones
3. Cargar tarifas
4. Consultar inventario de habitaciones
5. Crear servicios (manual)
6. Cargar archivo de servcios
7. Ingresar comidas para el restaurante (manual)
8. Cargar menu completo del restaurante


#### Funcion 1: Crear habitaciones (manual)

Esta funcion se encarga de que el administrador pueda crear nuevas habitaciones una por una.

Primero, se pide cuantas habitaciones se quieren cargar, por lo que es importante ingresar un numero N. Si ingresa otra cosa
diferente a un entero la aplicacion generara un error.

```
< Cuantas habitaciones desea crear? : 2
```
Luego se piden los datos para cada habitacion por separado.

#### Los datos que debe ingresar para cada habitacion son: 

* la ubicacion de la habitacion: (String de la ubicacion)
* Tipos de cama disponibles para agregar: (Numero entero del 1-7)
* Cuantas camas de este tipo desea agregar: (Cualquier numero entero)
* Caracteristicas posibles para la habitacion:: (numero entero del 1-3)
* Tipos posibles para la habitacion: (numero entero del 1-3)
* Desea hacer algo mas: (1 o 2, 1 carga de nuevo el menu del admin, 2 termina aplicacion)


Por otro lado, cuando tenga preguntas de tipo 1. Si 2. No, debe ingresar 1 o 2, que significan SI y NO respectivamente.
Si ingresa cualquier otra cosa diferente a 1 o 2, la aplicacion arrojara un error.

```
Desea agregar mas tipos de cama?
1.Si 
2.No 
Ingresar opcion: 2
# 1 = Si
# 2 = No
```

En general, la interaccion del usuario se hace solamente utilizando nuneros, con el fin de simplificar la interaccion 
con el usuario y asi mismo evitar errores de gramatica. Por lo que si se imprime una lista con numerales del 1 - n, 
procure ingresar opciones dentro de ese rango, de otra manera habra error en la aplicacion.

Ejemplo: 

```
Desea ingresar xxxxx cosa? 
1.Opcion 1
....
....
....
n.Opcion n  
Ingresar opcion: n
# n = opcion

```

#### Funcion 2: Cargar archivo habitaciones
Esta funcion simplemente de cargar el archivo en el cual se van a guardar los datos de las habitaciones, por lo que no
tiene ninguna entrada en especifico, solamente se encarga de cargar el archivo.

#### Funcion 3: Cargar tarifas
Esta funcion se encarga de cargar una tarifa a las habitaciones que NO tiene tarifa hasta ahora. Por ello, solamente
le aparecera en pantalla las habitaciones sin tarifa dentro de los 365 dias.

##### Debe ingresar los datos:

* Elija a que habitacion desea agreagarle una nueva tarifa: (numero entero de 1-n habitaciones)
* Fecha inicial: (Fecha mayor a la fecha actual en formato (YYYY-MM-DD))
* Fecha final:  (Fecha mayor a la fecha actual en formato (YYYY-MM-DD))
* Dia a la semana de las tarifas: (numero entero del 1-7 que representan cada uno un dia a la semana)
* Para los dias a la semana debe ingresarlos uno por uno.

#### Funcion 4: Consultar inventario de habitaciones
Simplemente le permite al administrador conocer el inventario de las habitaciones que se encuentra disponible en el hotel.
No espera ninguna entrada por parte del usuario.


#### Funcion 5: Crear servicios (manual) 
Se encarga de crear un servicio nuevo para el hotel

##### Debe ingresar los datos:

* id del servicio: (Cualquier numero entero)
* nombre del serivicio: (Cualquier String)
* precio del servicio: (Cualquier numero entero)
* servicio es para varias personas: (numeros 1 o 2)
* Dia a la semana disponibilidad del servicio: (numero entero del 1-7 que representan cada uno un dia a la semana).
Para los dias a la semana debe ingresarlos uno por uno.
* hora inicial disponible del servicio: (Numero entero del 1-24, ya que las horas estan en formato 24 horas)
* hora final disponible del servicio: (Numero entero del 1-24, ya que las horas estan en formato 24 horas)

Nota: Para crear otro servicio, necesita volver a ejectar esta opcion en el menu del administrador

### Funcion 6: Cargar archivo de servcios
Esta funcion simplemente de cargar el archivo en el cual se van a guardar los datos de los servicios, por lo que no
tiene ninguna entrada en especifico, solamente se encarga de cargar el archivo para los servicios.

### Funcion 7: Ingresar comidas para el restaurante (manual)
Esta funcion se encarga de ingresar nuevas comidas/bebidas al menu del restaurante.

##### Debe ingresar los datos:
* <b>el identificador de la comida/bebida:  </b> (cualquier numero entero)
* <b>el nombre de la comida/bebida:  </b>(cualquier string que identifique el nombre)
* <b>el precio de la comida/bebida: </b>(cualquier numero entero)
* <b> llevar a la habitacion: </b>(1 o 2)
* <b> Ingrese que tipo de comida es: </b>(numero entero del 1-3)

#### Funcion 8: Cargar menu completo del restaurante
Esta funcion simplemente de cargar el archivo en el cual se van a guardarel menu del restaurante, por lo que no
tiene ninguna entrada en especifico, solamente se encarga de cargar el archivo.

## Inicio de sesión como administrador
***
