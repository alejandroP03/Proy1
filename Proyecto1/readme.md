## Tabla de contenidos
1. [Importacion del proyecto](#Importación-del-proyecto)
2. [Autenticación de los usuarios](#Autenticación-de-los-usuarios)
3. [Inicio de sesión como administrador](#Inicio-de-sesión-como-administrador)
4. [Inicio de sesión como recepcionista](#Inicio-de-sesión-como-recepcionista)
5. [Inicio de sesión como Empleado](#Inicio-de-sesión-como-Empleado)
6. [Estructura de la persistencia de datos](#Estructura-de-la-persistencia-de-datos)
7. [Comentarios generales](#Comentarios-generales)

## Importación del proyecto
***

Asegúrese de importar como directorio raíz a <b>/Proyecto1</b> y no a <b>/Grupo-2</b>, ya que al importar <b>/Grupo-2</b> los paths de los archivos JSON cambian y no es posible cargar, crear o modificar cosas en los mismos.

Igualmente, para evitar errores se recomienda hacer el clone de la siguiente manera:

- Con https:

```bash
git clone https://github.com/DISC-ISIS1226-RM/Grupo-2.git Proyecto1
```

- Con ssh:

```bash
git clone git@github.com:DISC-ISIS1226-RM/Grupo-2.git Proyecto1
```




## Autenticación de los usuarios
***
Lo primero que la aplicación imprime, es elegir la opción para iniciar o registrarse en la aplicación.
En caso de que elija registrarse, pedirá un usuario y contraseña totalmente nueva, y al ingresarlos inmediatamente
mostrará el menú del usuario sobre el cual se hizo el registro. Por otro lado, si se inicia sesión pide los mismos datos,
pero solamente podrá ingresar si el usuario ya está registrado, de lo contrario manda un error al usuario. Las pantallas de funciones
son diferentes para cada usuario, por lo que si se registra como administrador, solo podrá ver las opciones de administrador.

<b> Notas: </b> 

1. Para elegir iniciar sesión o registrarse elija 1 o 2, el ingreso de otra entrada generará error en la aplicación.

2. Tenga en cuenta que al iniciar sesión no ingresa tal cual su usuario y contraseña, no va a poder ser autenticado en el sistema.
En otras palabras, importan las mayúsculas y minúsculas.


## Inicio de sesión como administrador
***
En este punto de la aplicación, se muestran todas las funciones posibles que puede realizar un administrador. 
Para elegir una opción, simplemente digite el índice sobre el cual se identifica cada opción. Tenga en cuenta que 
si no digita un número del 1-7, entonces la aplicación lanzará un error.

### Mapeo de funciones disponibles
------  Inicio como administrador -------
1. Crear habitaciones (manual)
2. Cargar archivo habitaciones
3. Cargar tarifas
4. Consultar inventario de habitaciones
5. Crear servicios (manual)
6. Cargar archivo de servicios
7. Ingresar comidas para el restaurante (manual)
8. Cargar menu completo del restaurante


#### Función 1: Crear habitaciones (manual)

Esta función se encarga de que el administrador pueda crear nuevas habitaciones una por una.

Primero, se pide cuantas habitaciones se quieren cargar, por lo que es importante ingresar un número N. Si ingresa otra cosa
diferente a un entero la aplicación generará un error.

```
< Cuantas habitaciones desea crear? : 2
```
Luego se piden los datos para cada habitación por separado.

#### Los datos que debe ingresar para cada habitación son: 

* la ubicación de la habitación: (String de la ubicación)
* Tipos de cama disponibles para agregar: (Número entero del 1-7)
* Cuantas camas de este tipo desea agregar: (Cualquier numero entero)
* Caracteristicas posibles para la habitación:: (numero entero del 1-3)
* Tipos posibles para la habitación: (número entero del 1-3)
* Desea hacer algo más: (1 o 2, 1 carga de nuevo el menu del admin, 2 termina aplicación)

Es importante que se cumplan los datos descritos, de otra manera el código tendrá error.

#### Función 2: Cargar archivo habitaciones
Esta función simplemente de cargar el archivo en el cual se van a guardar los datos de las habitaciones, por lo que no
tiene ninguna entrada en específico, solamente se encarga de cargar el archivo.

#### Función 3: Cargar tarifas
Esta función se encarga de cargar una tarifa a las habitaciones que NO tiene tarifa hasta ahora. Por ello, solamente
le aparecerá en pantalla las habitaciones sin tarifa dentro de los 365 días.

##### Debe ingresar los datos:

* Elija a que habitación desea agregarle una nueva tarifa: (numero entero de 1-n habitaciones)
* Fecha inicial: (Fecha mayor a la fecha actual en formato (YYYY-MM-DD))
* Fecha final: (Fecha mayor a la fecha actual en formato (YYYY-MM-DD))
* Día a la semana de las tarifas: (número entero del 1-7 que representan cada uno un día a la semana)
* Para los días a la semana debe ingresarlos uno por uno.

Es importante que se cumplan los datos descritos, de otra manera el código tendrá error.

#### Función 4: Consultar inventario de habitaciones
Simplemente, le permite al administrador conocer el inventario de las habitaciones que se encuentra disponible en el hotel.
No espera ninguna entrada por parte del usuario.


#### Función 5: Crear servicios (manual) 
Se encarga de crear un servicio nuevo para el hotel

##### Debe ingresar los datos:

* id del servicio: (Cualquier numero entero)
* nombre del servicio: (Cualquier String)
* precio del servicio: (Cualquier numero entero)
* servicio es para varias personas: (números 1 o 2)
* Día a la semana disponibilidad del servicio: (número entero del 1-7 que representan cada uno un día a la semana).
Para los días a la semana debe ingresarlos uno por uno.
* hora inicial disponible del servicio: (Número entero del 1-24, ya que las horas están en formato 24 horas)
* hora final disponible del servicio: (Número entero del 1-24, ya que las horas están en formato 24 horas)

Nota: Para crear otro servicio, necesita volver a ejecutar esta opción en el menu del administrador

Es importante que se cumplan los datos descritos, de otra manera el código tendrá error.

#### Función 6: Cargar archivo de servicios
Esta función simplemente de cargar el archivo en el cual se van a guardar los datos de los servicios, por lo que no
tiene ninguna entrada en específico, solamente se encarga de cargar el archivo para los servicios.

#### Función 7: Ingresar comidas para el restaurante (manual)
Esta función se encarga de ingresar nuevas comidas/bebidas al menu del restaurante.

##### Debe ingresar los datos:
* <b>el identificador de la comida/bebida:  </b> (cualquier numero entero)
* <b>el nombre de la comida/bebida:  </b>(cualquier string que identifique el nombre)
* <b>el precio de la comida/bebida: </b>(cualquier numero entero)
* <b> llevar a la habitación: </b>(1 o 2)
* <b> Ingrese que tipo de comida es: </b>(numero entero del 1-3)

Es importante que se cumplan los datos descritos, de otra manera el código tendrá error.

#### Función 8: Cargar menu completo del restaurante
Esta función simplemente de cargar el archivo en el cual se van a guardar el menu del restaurante, por lo que no
tiene ninguna entrada en específico, solamente se encarga de cargar el archivo.

## Inicio de sesión como recepcionista
***
En este punto de la aplicación, se muestran todas las funciones posibles que puede realizar un recepcionista.
Para elegir una opción, simplemente digite el índice sobre el cual se identifica cada opción. 
Tenga en cuenta que si no digita un número del 1-4, entonces la aplicación lanzará un error.

#### Función 1: Hacer una nueva reserva.
Función que crea una nueva reserva para el hotel.

##### Debe ingresar los datos:
* <b>Nombre del responsable:  </b> (String que identifica el nombre)
* <b> Número de documento del responsable:  </b>(String que solo contiene números)
* <b>Teléfono del responsable: </b> (String que solo contiene números)
* <b> Correo del responsable: </b>(String que describe el correo del responsable)
* <b> Número de tarjeta de crédito del responsable: </b> (String que solo contiene números)
* <b> Número de acompañantes: </b> (Cualquier numero entero)
* <b> Número habitación que va a ocupar</b> (Número entre el rango 1-n habitaciones disponibles)


Es importante que se cumplan los datos descritos, de otra manera el código tendrá error.

Nota: hasta este punto solo se hizo una reserva, es decir solo se guardó la información de la persona responsable y
las habitaciones que va a ocupar, pero hasta ese momento no es un huésped a quien se le puede asignar consumos.

#### Función 2: Hacer una nueva registro.
Función que crea una nueva reserva para el hotel.

##### Debe ingresar los datos (Si ya existe una reserva)
Esta opción solo se ejecuta cuando ya había una reserva asociada al nuevo huésped que se va a registrar.

* <b> el numero de identificación del nuevo huésped responsable:  </b> (String de números)
* <b> acompañantes vienen con el huésped principal  </b> (Numero entero)
* <b> Datos generales de los acompañantes (DNi y su nombre)</b>

Los datos anteriores deben coincidir con los que se hicieron en reservas pasadas, de otro modo, no se hará correctamente
el registro.

#### Función 3: Hacer checkout.

Función que hace check-out, es decir cobra cuentas pendientes y le da salida a los huéspedes.

##### Debe ingresar los datos:
* <b> el numero de identificación del huésped responsable:  </b> (String de números)

El DNI del responsable debe coincidir con los datos que se hicieron dentro del registro, de lo contrario habrá error
en la aplicación

#### Función 4: Cancelar una reserva.
Función que se encarga de cancelar reservas ya hechas en el hotel

##### Debe ingresar los datos:
* <b> el numero de identificación de la persona que reservó  </b> (String de números)

El DNI de quien reservo debe coincidir con el DNI de alguna reserva hecha anteriormente, de lo contrario habrá error
en la aplicación.

## Inicio de sesión como Empleado
***
Hay dos funciones importantes, registrar consumos para el restaurante o consumos de otros servicios.
Debe ingresar 1 o 2 respectivamente para poder ingresar a alguna de estas dos funciones

##### Debe ingresar los datos (Para consumir en el restaurante):
* <b> El numero del item del menu a agregar </b> (entero del 1-n items del menu)

##### Debe ingresar los datos (Para consumir como servicio) :
* <b> El numero del servicio a agregar </b> (entero del 1-n servicios registrado)

##### Debe ingresar los datos (Para pagar ya) :
* <b> Tipo de huésped que es:  </b> (1 o 2) 1 =  corresponde al huésped principal; 2= huésped invitado
Si es principal solamente ingresa su número de identificación.

Si es invitado, debe ingresar la identificación de la persona que lo invito(huésped principal) y además
el DNI con el que se identificó en el registro

Todos los datos tomados deben estar cargados antes en el registro, de otro modo generará error en la aplicación.

##### Debe ingresar los datos (Para cargar a la habitación ) :

* <b> Identificación del huésped principal:  </b> (String de números)

Este dato corresponde sobre la persona que se hizo el registro y se responsabilizó por las habitaciones
ocupadas.

## Estructura de la persistencia de datos
***
### Importante
Los archivos pueden no contener datos, sin embargo deben contener mínimamente la estructura de un JSON vacío `` {} `` de lo contrario la aplicación lanzará la excepción `` El archivo JSON no tiene la estructura correcta ``

La persistencia de datos se hace sobre archivos JSON, los cuales se encuentran en la carpeta data. Para mayor facilidad, se divide en 7 archivos JSON, los cuales son:
##### rooms.json
``` json
    //Ejemplo de una habitacion
    "SUITE_2": {
		"isOccupied": false,
		"featuresList": ["KITCHEN", "BALCONY", "LANDSCAPE_VIEW"],
		"bookedDates": {
            2023-05-15: 2023-05-20, 
            2023-05-25: 2023-05-30},
		"location": "Piso 11 1101",
		"beds": { "KING_PLUS": 1 , "QUEEN": 1},
		"type": "SUITE",
		"roomId": "SUITE_2",
		"capacity": 4
	},

```

#### bookings.json  
```json
    //Ejemplo de una reserva
    	"1051065225": {
		"finalDate": "2024-01-03",
		"reserviourDNI": "1051065225",
		"reserviourPhone": "3003273088",
		"reserviourSupportCardNumber": "1200044405495955",
		"numberOfGuests": 3,
		"reserviourName": "Alejandro Pulido", 
		"reserviourEmail": "diegopulido384@gmail.com",
		"initialDate": "2023-12-20",
		"reservedRoomsIds": ["DOUBLE_SUITE_12"]
	}

```
#### foodInfo.json  
``` json
	"23": {
		"name": "Hamburguesa en combo",
		"id": "23",
		"availability": "Cena",
		"isRoomService": true,
		"price": 35000.0
	}
```

#### registrations.json  
``` json
	"1051065225": {
		"finalDate": "2024-01-03",
		"registerRoomsIds": ["DOUBLE_SUITE_12"],
		"consumedServicesIds": ["23", "25"],
		"consumedFoodsIds": ["14", "54"],
		"initialDate": "2023-12-20",
		"principalGuest": {
			"name": "Alejandro Pulido",
			"phoneNumber": "3003273088",
			"dni": "1051065225",
			"email": "diegopulido384@gmail.com"
		},
		"group": [{ "name": "Valeria Amaya", "dni": "1014977419" }]
	}
```
#### room_fares.json  
``` json
"[KITCHEN, BALCONY, LANDSCAPE_VIEW, {KING_PLUS=1}, SUITE]": {
		"fares": [
			{
				"finalDate": "2024-03-30",
				"days": [
					"TUESDAY",
					"SUNDAY",
					"SATURDAY",
					"THURSDAY",
					"FRIDAY",
					"WEDNESDAY",
					"MONDAY"
				],
				"price": 301.0,
				"initialDate": "2023-10-10"
			},
			{
				"finalDate": "2024-10-10",
				"days": [
					"SUNDAY",
					"SATURDAY",
					"FRIDAY",
				],
				"price": 305.0,
				"initialDate": "2024-03-31"
			},
        ]
    }
```
#### services.json  
``` json
	"1": {
		"initialTime": "10:00",
		"price": 23000.0,
		"name": "Peluquería",
		"isForGroup": false,
		"daysAvailable": [
            "MONDAY",
            "TUESDAY",
            "WEDNESDAY",
            "THURSDAY",
            "FRIDAY"
        ],
		"id": "1",
		"finalTime": "20:00"
	}

```
#### users.json
``` json
    "alejandroP03": {
		"password": "passwordSuperSeguro",
		"userType": "ADMIN",
		"userName": "alejandroP03"
	}
```

## Comentarios generales
***
En general, la interacción del usuario se hace solamente utilizando números, con el fin de simplificar la interacción
con el usuario y asi mismo evitar errores de gramática. Por lo que si se imprime una lista con numerales del 1 - n,
procure ingresar opciones dentro de ese rango, de otra manera habrá error en la aplicación.

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
Si ingresa cualquier otra cosa diferente a 1 o 2, la aplicación arrojará un error.

```
Desea agregar mas tipos de cama?
1.Si 
2.No 
Ingresar opcion: 2
// 1 = Si
// 2 = No
```

Para el ingreso de fechas, la aplicacion se encarga de verificar que la fecha ingresada sea correcta y manejará todos los errores con respecto a estas, sin embargo, debe tener en cuenta que el formato de fecha es el siguiente: AAAA-MM-DD

```
Ingrese fecha de inicio en YYYY-MM-DD: 2020-01-01

```


