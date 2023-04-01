## Table of Contents
1. [General Info](#general-info)
2. [Technologies](#technologies)
3. [Installation](#installation)
4. [Collaboration](#collaboration)
5. [FAQs](#faqs)

## importacion del proyecto
***

Asegurese de importar como carpeta raiz a <b>Proyecto1</b>  y no a la carpeta <b>Grupo-2</b>, ya que 
al importar la carpeta <b>Grupo-2</b> los paths de los archivos JSON cambian y no es posible cargar, crear
o modificar cosas en los mismos.

Igualmente para evitar errores se recomienda hacer el clone de la sigueinte manera:
```
git clone https://github.com/DISC-ISIS1226-RM/Grupo-2/blob/e6611e9ed846946cb898acfaf84905a8f0fb7880/Proyecto1
```

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

Es importante que se cumplan los datos descritos, de otra manera el codigo tendra error.

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

Es importante que se cumplan los datos descritos, de otra manera el codigo tendra error.

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

Es importante que se cumplan los datos descritos, de otra manera el codigo tendra error.

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

Es importante que se cumplan los datos descritos, de otra manera el codigo tendra error.

#### Funcion 8: Cargar menu completo del restaurante
Esta funcion simplemente de cargar el archivo en el cual se van a guardarel menu del restaurante, por lo que no
tiene ninguna entrada en especifico, solamente se encarga de cargar el archivo.

## Inicio de sesión como recepcionista
***
En este punto de la aplicacion, se muestran todas las funciones posibles que puede realizar un recepcionista.
Para elegir una opcion, simplemente digite el indice sobre el cual se identifica cada opcion. 
Tenga en cuenta que si no digita un número del 1-4, entonces la aplicación lanzará un error.

#### Funcion 1: Hacer una nueva reserva.
Funcion que crea una nueva reserva para el hotel.

##### Debe ingresar los datos:
* <b>Nombre del responsable:  </b> (String que identifica el nombre)
* <b> Múmero de documento del responsable:  </b>(String que solo contiene numeros)
* <b>Teléfono del responsable: </b> (String que solo contiene numeros)
* <b> Correo del responsable: </b>(String que describe el correo del responsable)
* <b> Número de tarjeta de credito del responsable: </b> (String que solo contiene numeros)
* <b> Número de acompañantes: </b> (Cualquier numero entero)
* <b> Número habitacion que va a ocupar</b> (Número entre el rango 1-n habitaciones disponibles)


Es importante que se cumplan los datos descritos, de otra manera el codigo tendra error.

Nota: hasta este punto solo se hizo una reserva, es decir solo se guardo la informacion de la persona responsable y
las habuitaciones que va a ocupar, pero hasta ese momento no es un huesped a quien se le puede asignar consumos.

#### Funcion 2: Hacer una nueva registro.
Funcion que crea una nueva reserva para el hotel.

##### Debe ingresar los datos (Si ya existe una reserva)
Esta opcion solo se ejecuta cuando ya habia una reserva asociada al nuevo huesped que se va a registrar.

* <b> el numero de identificacion del nuevo huesped responsable:  </b> (String de numeros)
* <b> acompanantes vienen con el huesped principal  </b> (Numero entero)
* <b> Datos generales de los acompanantes (DNi y su nombre)</b>

Los datos anteriores deben coincidir con los que se hicieron en reservas pasadas, de otro modo, no se hara correctamente
el registro.

#### Funcion 3: Hacer checkout.

Funcion que hace check-out, es decir cobra cuentas pendientes y le da salida a los huespedes.

##### Debe ingresar los datos:
* <b> el numero de identificacion del  huesped responsable:  </b> (String de numeros)

El DNI del responsable debe coincidir con los datos que se hicieron dentro del registro, de lo contrario habra error
en la aplicacion

#### Funcion 4: Cancelar una reserva.
Funcion que se encarga de cancelar reservas ya hechas en el hotel

##### Debe ingresar los datos:
* <b> el numero de identificacion de la persona que resevo  </b> (String de numeros)

El DNI de quien reservo debe coincidir con el DNI de alguna reserva hecha anteriormente, de lo contrario habra error
en la aplicacion.

## Inicio de sesión como Empleado
***
Hay dos funciones importantes, registrar consumos para el restaurante o consumos de otros servicios.
Debe ingresar 1 o 2 respectivamente para poder ingresar a alguna de estas dos funciones

##### Debe ingresar los datos (Para consumir en el restaurante) :
* <b> el numero del item del menu a agregar </b> (entero del 1-n items del menu)

##### Debe ingresar los datos (Para consumir como servicio) :
* <b> el numero del servicio a agregar </b> (entero del 1-n servicios registrado)

##### Debe ingresar los datos (Para pagar ya) :
* <b> tipo de huesped que es:  </b> (1 o 2) 1 =  corresponde al huesped principal; 2= huesped invitado
Si es prinipal solamente ingresa su numero de identificacion.

Si es invitado, debe ingresar la identificacion de la persona que lo invito(huesped prinicpal) y ademas
el DNI con el que se identifico en el registro

Todos los datos tomados deben estar cargados antes en el registro, de otro modo generara error en la aplicacion.

##### Debe ingresar los datos (Para cargar a la habitacion ) :

* <b> Identificacion del huesped principal:  </b> (String de numeros)

Este dato corresponde sobre la persona que se hizo el registro y se responsabilizo por las habitaciones
ocupadas.

## Comentarios generales
***
En general, la interaccion del usuario se hace solamente utilizando numeros, con el fin de simplificar la interaccion
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
// n = opcion

```

Por otro lado, cuando tenga preguntas de tipo 1. Si 2. No, debe ingresar 1 o 2, que significan SI y NO respectivamente.
Si ingresa cualquier otra cosa diferente a 1 o 2, la aplicacion arrojara un error.

```
Desea agregar mas tipos de cama?
1.Si 
2.No 
Ingresar opcion: 2
// 1 = Si
// 2 = No
```


