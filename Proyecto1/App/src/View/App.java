package View;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import Controller.Hotel;
import Controller.BookingHandler.BookingHandler;
import Controller.RegisterHandler.CompanionGuest;
import Controller.RegisterHandler.RegisterHandler;
import Controller.WorkersAuth.HotelWorkersAuth;
import Model.HotelObjects.Admin;
import Model.HotelObjects.Employee;
import Model.HotelObjects.Receptionist;
import Model.HotelObjects.RoomRelated.*;
import Model.HotelObjects.User;
import Model.HotelObjects.UserType;

public class App {
    User activeUser;
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private Hotel hotel = new Hotel();

    public static void main(String[] args) throws Exception {
        System.out.println(" ");
        System.out.println("Bienvenido al sistema del hotel!");
        App instanceApp = new App();
        instanceApp.authApp();
        //instanceApp.showTypeUser();

    }

    private void authApp() throws Exception {
        /*
         * Autentica a los usuarios del Hotel (Recepcionista, Administrador, Empleado)
         * <br>
         * <b>pre:</b> El archivo de usuarips ya esta inicializado
         * <b>post:</b> Un usuario inicia sesion o se registra en el hotel
         *
         * @throw Exception <br>
         * 1. Si se registra un usuario con las mismas credenciales
         * 2. Si se loggea con datos que no existen
         *
         *
         *
         */

        HotelWorkersAuth authHandler = new HotelWorkersAuth();
        hotel.getUserHandler().loadPersistentData();

        System.out.println("1. Registrarse");
        System.out.println("2. Iniciar sesion");
        System.out.print("Ingrese la opcion deseada: ");
        String opcionStr = br.readLine();
        int opcion = Integer.parseInt(opcionStr);
        if (opcion == 1) {
            System.out.println(" ");
            System.out.println("----- Registro  -----");
            System.out.println("Como desea registrarse?");
            System.out.println("1. Como administrador");
            System.out.println("2. Como empleado");
            System.out.println("3. Como recepcionista");
            System.out.print("Ingrese la opcion deseada: ");
            String registroStr = br.readLine();
            int registro = Integer.parseInt(registroStr);

            System.out.println("Nombre de usuario: ");
            String userName = br.readLine();
            System.out.println("Contraseña: ");
            String password = br.readLine();
            if (registro == 1) {
                try {
                    authHandler.register(userName, password, UserType.ADMIN, hotel.getUserHandler().getData());
                    hotel.getUserHandler().SavePersistentData();
                } catch (Exception e) {
                    System.out.println(e);
                    authApp();
                }
                showAdminScreen();
            } else if (registro == 2) {
                try {
                    authHandler.register(userName, password, UserType.EMPLOYEE, hotel.getUserHandler().getData());
                    hotel.getUserHandler().SavePersistentData();
                } catch (Exception e) {
                    System.out.println(e);
                    authApp();
                }

                showEmployeeScreen();

            } else if (registro == 3) {
                try {
                    authHandler.register(userName, password, UserType.RECEPTIONIST, hotel.getUserHandler().getData());
                    hotel.getUserHandler().SavePersistentData();
                } catch (Exception e) {
                    System.out.println(e);
                    authApp();
                }

                showRecepcionistScreen();
            }

        } else if (opcion == 2) {
            System.out.println(" ");
            System.out.println("----- Inicio de sesion  -----");
            System.out.print("Ingrese su usuario: ");
            String usuarioStr = br.readLine();

            System.out.print("Ingrese su contrasena: ");
            String contrasenaStr = br.readLine();

            try {
                User actualUser = authHandler.login(usuarioStr, contrasenaStr, hotel.getUserHandler().getData());
                switch (actualUser.getUserType()) {
                    case ADMIN:
                        showAdminScreen();
                        break;
                    case RECEPTIONIST:
                        showRecepcionistScreen();
                        break;
                    case EMPLOYEE:
                        showEmployeeScreen();
                        break;
                    default:
                        break;

                }
            } catch (Exception e) {
                System.out.println(e);
                authApp();
            }

        }

    }
    // ---------------------- Comprobacion tipo de usuario ----------------------

    /*
     * Se comprueba que tipo de usuario se registro o inicio sesion dentro del hotel
     * <br>
     * <b>pre:</b> El usuario se registro o inicio sesion dentro de la aplicacion
     * <b>post:</b> Se verifica que tipo de pantalla se le va a mostrar al usuario
     * segun sea.
     *
     * @throw Exception <br>
     * 1. No se cargo el archivo en donde se encuentran los usuarios
     *
     *
     */
    private void showTypeUser() throws Exception {
        if (activeUser instanceof Admin) {
            showAdminScreen();

        } else if (activeUser instanceof Receptionist) {
            showRecepcionistScreen();

        } else if (activeUser instanceof Employee) {
            showEmployeeScreen();

        }
    }

    // ---------------------- Pantalla para el Administrador ----------------------
    /*
     * Se le muestra al administrador las funciones que puede realizar <br>
     * <b>pre:</b> El usuario se registro o inicio sesion dentro de la aplicacion y
     * es administrador el hotel
     * <b>post:</b> El administrasdor elegir cuales funciones puede hacer, como
     * cargar habitaciones, servicios...etc
     *
     * @throw Exception <br>
     * 1. El usuario no es un administrador
     *
     *
     */
    private void showAdminScreen() throws Exception {
        System.out.println("------  Inicio como administrador ------- ");
        System.out.println("1. Crear habitaciones (manual) ");
        System.out.println("2. Cargar archivo habitaciones");
        System.out.println("3. Cargar tarifas");
        System.out.println("4. Consultar inventario de habitaciones");
        System.out.println("5. Crear servicios (manual) ");
        System.out.println("6. Cargar archivo de servcios");
        System.out.println("7. Ingresar comidas para el restaurante (manual)");
        System.out.println("8. Cargar menu completo del restaurante");

        System.out.print("Ingrese una opcion: ");
        String opcionStr = br.readLine();
        int opcion = Integer.parseInt(opcionStr);
        if (opcion == 1) {
            createRoom();
        } else if (opcion == 2) {
            loadDataRooms();
        } else if (opcion == 3) {
            loadFares();

        } else if (opcion == 4) {
            System.out.println("Consultar inventario de habitaciones");
            // TODO hacer la consulta de las habitaciones en RoomsDataHolder con
            // RoomDataHolder
        } else if (opcion == 5) {
            createService();
        } else if (opcion == 6) {
            loadServices();
        } else if (opcion == 7) {

            createMenuRestaurant();
        } else if (opcion == 8) {
            loadMenuRestaurant();

        }
    }
    // ---------------------- Funciones para el adminsitrador ----------------------

    /*
     * El administrador crea una o varias habitaciones <br>
     * <b>pre:</b> El archivo de habitaciones debe estar inicializado
     * <b>post:</b> El administrador carga las caracteristicas de las nuevas
     * habitaciones
     *
     * @throw Exception <br>
     * 1. No se inicializa el archivo donde se guardan los datos de las habitaciones
     *
     *
     */
    private void createRoom() throws Exception {
        loadDataRooms(); // Se carga primero el archivo asi este vacio
        System.out.println("------ Crear habitaciones------- ");
        System.out.print("Cuantas habitaciones desea crear? : ");
        String numRoomsStr = br.readLine();
        int numRooms = Integer.parseInt(numRoomsStr);
        Bed[] typeBeds = Bed.values();
        RoomFeatures[] typeFeeatures = RoomFeatures.values();
        TypeRoom[] typeRooms = TypeRoom.values();

        Map<Bed, Integer> mapBeds = new HashMap<Bed, Integer>();
        Set<RoomFeatures> featuresList = new HashSet<RoomFeatures>();

        for (int i = 1; i <= numRooms; i++) {
            System.out.println("----------- Datos para la " + (i) + " habitacion -----------");
            System.out.print("Ingrese la ubicacion de la habitacion: ");
            String location = br.readLine();
            boolean isMoreBeds = true;

            // Creacion del mapa para las camas

            while (isMoreBeds) {
                System.out.println("Tipos de cama disponibles para agregar");
                int x = 1;
                for (Bed typeRoom : typeBeds) {
                    System.out.println(x + "." + typeRoom);
                    x++;
                }

                System.out.print("Elija el tipo de cama que desea agregar a la habitacion: ");
                String chooseBedStr = br.readLine();
                int chooseBed = Integer.parseInt(chooseBedStr);
                Bed bedChoose = typeBeds[chooseBed - 1];
                System.out.print("Cuantas camas de este tipo desea agregar? : ");
                String numBedsStr = br.readLine();
                int numBeds = Integer.parseInt(numBedsStr);
                mapBeds.put(bedChoose, numBeds);

                System.out.println(numBeds + " camas de tipo " + bedChoose + " han sido agregadas a la habitacion");

                System.out.println("Desea agregar mas tipos de cama?");
                System.out.println("1.Si \n2.No ");
                System.out.print("Ingresar opcion: ");
                String isMoreBedsStr = br.readLine();
                int moreBeds = Integer.parseInt(isMoreBedsStr);
                if (moreBeds == 2) {
                    break;
                }
            }
            // Creacion del set para las caracteristicas

            while (isMoreBeds) {
                System.out.println("Caracteristicas posibles para la habitacion:");
                int x = 1;
                for (RoomFeatures typeFeature : typeFeeatures) {
                    System.out.println(x + "." + typeFeature);
                    x++;
                }
                System.out.print("Elija el tipo de caracteristica que tendra la habitacion: ");
                String chooseFeatureStr = br.readLine();
                int chooseFeature = Integer.parseInt(chooseFeatureStr);
                RoomFeatures featuresChoose = typeFeeatures[chooseFeature - 1];
                featuresList.add(featuresChoose);

                System.out.println("La caracteristica " + featuresChoose + " ha sido agregada");

                System.out.println("Desea agregar mas caracterisitcas a la habitacion?");
                System.out.println("1.Si \n2. No ");
                System.out.print("Ingresar opcion: ");
                String isMoreFeaturesStr = br.readLine();
                int moreFeatures = Integer.parseInt(isMoreFeaturesStr);
                if (moreFeatures == 2) {
                    break;
                }
            }
            // Creacion del tipo de room

            System.out.println("Tipos posibles para la habitacion: ");
            int x = 1;
            for (TypeRoom typeRoom : typeRooms) {
                System.out.println(x + "." + typeRoom);
                x++;
            }
            System.out.print("Elija el tipo de la habitacion: ");
            String chooseTypeStr = br.readLine();
            int chooseType = Integer.parseInt(chooseTypeStr);
            TypeRoom typeChoose = typeRooms[chooseType - 1];
            System.out.println("La habitacion nueva sera de tipo: " + typeChoose);

            hotel.getRoomsHandler().createNewRoom(location, false, mapBeds, featuresList, typeChoose);
            hotel.getRoomsHandler().SavePersistentData();
            System.out.println("Las habitaciones han sido creadas exitosamente!");
            System.out.println("Desea hacer algo mas? \n1.Si \n2.No");
            int masOpciones = Integer.parseInt(br.readLine());
            if (masOpciones == 1) showAdminScreen();

        }
        // try {
        // createRoom();
        // } catch (Exception e) {
        // System.out.println(e);
        // }

    }

    /*
     * El administrador tiene la oportunidad de cargar un archivo con datos de
     * varias habitaciones<br>
     * <b>pre:</b> El archivo con los datos de las habitaciones
     * <b>post:</b> Se guarda en el hotel las nuevas habitaciones caragadas
     *
     * @throw Exception <br>
     * 1. no se inicializa el archivo que contiene los datos de las nuevas
     * habitaciones
     *
     *
     */
    private void loadDataRooms() throws Exception {
        try {
            hotel.getRoomsHandler().loadPersistentData();
            hotel.getRoomsHandler().SavePersistentData();
            System.out.println("Archivo cargado exitosamente");
            System.out.println("Desea hacer algo mas? \n1.Si \n2.No");
            int masOpciones = Integer.parseInt(br.readLine());
            if (masOpciones == 1) showAdminScreen();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // Metodo que carga las tarifas ???
    private void loadFares() throws Exception {

        System.out.println("------ Crear tarifas para habitaciones ------- ");
        hotel.getRoomsHandler().loadPersistentData();
        hotel.getFaresHandler().loadPersistentData();
        Map<Object, Room> mapRooms = new HashMap<>(hotel.getRoomsHandler().getData() );
        Map<Set<Object>, Room> roomsList = new HashMap<Set<Object>, Room>();

        System.out.println("Las siguientes habitaciones no tienen una tarifa asignada dentro de los siguientes 365 dias: ");

        for (Room availableRoom : mapRooms.values()) {
            roomsList.put(availableRoom.createTypeRoomId(), availableRoom);
        }
        ArrayList<Room> typesRooms = new ArrayList<Room>(roomsList.values());

        int moreFairs = 0;
        do {
            int pos = 1;
            for (Room availableRoom : typesRooms) {
                System.out.println(" ");
                System.out.print("******* Tipo de habitacion " + pos + "*******");
                System.out.println("Tipo: " + availableRoom.getType().toString());
                System.out.println("Capacidad: " + availableRoom.getCapacity());
                System.out.println("Camas: " + availableRoom.getBeds());
                System.out.println("Características: " + availableRoom.getFeaturesList());
                pos++;
            }
            System.out.print("Elija a que habitacion desea agreagarle una nueva tarifa: ");
            int addFair = Integer.parseInt(br.readLine());
            Room roomSelected = typesRooms.get(addFair);
            TypeRoom type = roomSelected.getType();
            Map<Bed, Integer> beds = roomSelected.getBeds();
            Set<RoomFeatures> featuresList = roomSelected.getFeaturesList();
            RoomModel roomModel = new RoomModel(type, beds, featuresList);

            System.out.print("Ingrese le precio para la habitacion: ");
            int price = Integer.parseInt(br.readLine());

            System.out.print("ingrese la fecha inicial para la tarifa (YYYY-MM-DD) : ");
            LocalDate initialDate = LocalDate.parse(br.readLine());

            System.out.print("ingrese la fecha final para la tarifa (YYYY-MM-DD) : ");
            LocalDate finalDate = LocalDate.parse(br.readLine());
            ArrayList<DayOfWeek> daysList = new ArrayList<>();
            DayOfWeek[] daysWeek = DayOfWeek.values();
            int moreDays = 0;

            do {
                System.out.println("Dias a la semana que va a ocupar la tarifa ");
                int x = 1;
                for (DayOfWeek dayWeek : daysWeek) {
                    System.out.println(x + "." + dayWeek);
                    x++;
                }
                System.out.print("Ingrese el dia a la semana que desea agregar: ");
                int chooseDay = Integer.parseInt(br.readLine());
                DayOfWeek dayChoose = daysWeek[chooseDay];
                daysList.add(dayChoose);
                System.out.println("Desea agreagar mas dias? \n1.Si\n2.No");
                moreDays = Integer.parseInt(br.readLine());

            } while (moreDays == 1);
            hotel.getFaresHandler().FareCreator(roomModel.createTypeRoomId(), price, initialDate, finalDate, daysList);
            typesRooms.remove(addFair);

            System.out.println("Desea cargar otra tarifa? \n1.Si \n2.No");
            moreFairs = Integer.parseInt(br.readLine());

        }while(moreFairs == 1);

        hotel.getFaresHandler().SavePersistentData();
        System.out.println("Tarifa agregada exitosamente!");
        System.out.println(" ");
        System.out.println("Desea hacer algo mas? \n1.Si \n2.No");
        int masOpciones = Integer.parseInt(br.readLine());
        if (masOpciones == 1) showAdminScreen();

    }

    /*
     * El administrador tiene la oportunidad de cargar un nuevos servicios <br>
     * <b>pre:</b> Se debe inicializar el archivo donde se van a guardar los nuevos
     * servicios
     * <b>post:</b> Se guarda en el hotel los nuevos servicios agregagados
     *
     * @throw Exception <br>
     * 1. no se inicializa el archivo que guarda los neuvos datos de los servicios
     *
     *
     */
    private void createService() throws Exception {
        loadServices();
        System.out.println("------ Crear servicio ------- ");
        System.out.print("Ingrese el id del servicio: ");
        String id = br.readLine();
        System.out.print("Ingrese el nombre del serivicio: ");
        String name = br.readLine();
        System.out.print("Ingrese el precio del servicio: ");
        String precioStr = br.readLine();
        double precio = Double.parseDouble(precioStr);

        System.out.println("El servicio es para varias personas? ");
        System.out.println("1.Si \n2. No ");
        String isGroupStr = br.readLine();
        int isGroup = Integer.parseInt(isGroupStr);
        boolean isForGroup;
        if (isGroup == 1) {
            isForGroup = true;
        } else
            isForGroup = false;

        System.out.println("Que dias a la semana va a estar disponible el servicio?");
        DayOfWeek[] daysWeek = DayOfWeek.values();
        Set<DayOfWeek> daySet = new HashSet<DayOfWeek>();
        while (true) {
            int x = 1;
            for (DayOfWeek dayWeek : daysWeek) {
                System.out.println(x + "." + dayWeek);
                x++;
            }
            System.out.print("Ingrese el dia a la semana que desea agregar: ");
            String dayWeekStr = br.readLine();
            int dayWeek = Integer.parseInt(dayWeekStr);
            DayOfWeek dayChoose = daysWeek[dayWeek];
            daySet.add(dayChoose);

            System.out.println("Desea ingresar otro dia?");
            System.out.println("1.Si \n2. No ");
            String otherDayStr = br.readLine();
            int otherDay = Integer.parseInt(otherDayStr);
            if (otherDay == 2) {
                break;
            }

        }
        System.out.println("Ingrese la hora inicial disponible del servicio (en formato HH de 24 horas) ");
        String initialDateStr = br.readLine();
        int initialDateOption = Integer.parseInt(initialDateStr);

        System.out.println("Ingrese la hora final disponible del servicio (en formato HH de 24 horas)");
        String finalDateStr = br.readLine();
        int finalDateOption = Integer.parseInt(finalDateStr);

        LocalTime initialDate = LocalTime.of(initialDateOption, 0);
        LocalTime finalDate = LocalTime.of(finalDateOption, 0);

        ArrayList<DayOfWeek> dayList = new ArrayList<DayOfWeek>(daySet);
        hotel.getServices().createNewService(id, name, precio, isForGroup, dayList, initialDate, finalDate);
        hotel.getServices().SavePersistentData();
        System.out.println("Servicio agregado exitosamente!");
        System.out.println(" ");
        System.out.println("Desea hacer algo mas? \n1.Si \n2.No");
        int masOpciones = Integer.parseInt(br.readLine());
        if (masOpciones == 1) showAdminScreen();

    }

    /*
     * El administrador tiene la oportunidad de cargar un archivo con datos de
     * varios servicios<br>
     * <b>pre:</b> El archivo con los datos de los servicios
     * <b>post:</b> Se guarda en el hotel las nuevos servicios caragadoss
     *
     * @throw Exception <br>
     * 1. no se inicializa el archivo que contiene los datos de las nuevos servicios
     * 2. El archivo cargado no es un JSON
     *
     *
     */
    private void loadServices() throws Exception {
        try {
            hotel.getServices().loadPersistentData();
            hotel.getServices().SavePersistentData();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /*
     * El administrador tiene la oportunidad de ingresar nuevas comidas para el
     * restaurante<br>
     * <b>pre:</b> Se inicializa el archivo donde se va a guardar los nuevas comidas
     * del restaurante
     * <b>post:</b> Se guarda en el restaurante las nuevas comidas disponibles
     *
     * @throw Exception <br>
     * 1. no se inicializa el archivo donde se guardan las nuevas comidas agregadas
     *
     *
     */
    private void createMenuRestaurant() throws Exception {
        loadMenuRestaurant();
        System.out.println("------ Crear servicio ------- ");
        System.out.println("Ingrese el identificador de la comida/bebida: ");
        String idFood = br.readLine();

        System.out.print("Ingrese el nombre de la comida/bebida: ");
        String nameFood = br.readLine();

        System.out.print("Ingrese el precio de la comida/bebida ");
        String priceFoodStr = br.readLine();
        double priceFood = Double.parseDouble(priceFoodStr);

        System.out.println("La comida/bebida se puede llevar a la habitacion?");
        System.out.println("1.Si \n2. No ");
        String roomServiceStr = br.readLine();
        double roomService = Integer.parseInt(roomServiceStr);
        boolean isRoomService = (roomService == 1) ? true : false;

        System.out.println("Ingrese que tipo de comida es");
        System.out.println("1.Desayuno");
        System.out.println("2. Almuerzo");
        System.out.println("3. Cena");
        System.out.print("ingrese una opcion: ");
        String typeFoodStr = br.readLine();
        double optionFood = Integer.parseInt(typeFoodStr);
        String typeFood = null;
        if (optionFood == 1) {
            typeFood = "Desayuno";
        } else if (optionFood == 2) {
            typeFood = "Almuerzo";
        } else if (optionFood == 3) {
            typeFood = "Cena";
        }
        hotel.getRestaurantHandler().createNewFood(idFood, nameFood, priceFood, isRoomService, typeFood);
        hotel.getRestaurantHandler().SavePersistentData();

        System.out.println("Comida agregada exitosamente! ");
        System.out.println(" ");
        System.out.println("Desea hacer algo mas? \n1.Si \n2.No");
        System.out.print("ingrese una opcion: ");
        int masOpciones = Integer.parseInt(br.readLine());
        if (masOpciones == 1) showAdminScreen();

    }

    /*
     * El administrador carga el menu del restaurante por medio de un archivo<br>
     * <b>pre:</b> El archivo con los datos del menu
     * <b>post:</b> Se guarda en el hotel el menu guardado
     *
     * @throw Exception <br>
     * 1. no se inicializa el archivo que contiene los datos de las nuevas
     * habitaciones
     *
     *
     *
     */
    private void loadMenuRestaurant() throws Exception {

        try {
            hotel.getRestaurantHandler().loadPersistentData();
            hotel.getRestaurantHandler().SavePersistentData();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /*
     * Se le muestra al recepcionista el menu de funciones que puede realizar<br>
     * <b>pre:</b> El inventario del hotel ya debe estar cargado para ser consultado
     * <b>post:</b> El recepcionista puede ver que opciones puede ejecurtar dentro
     * del hotel
     *
     * @throw Exception <br>
     * 1. ????
     *
     */
    private void showRecepcionistScreen() throws Exception {
        System.out.println("------  Inicio como recepcionista ------- ");
        System.out.println("1. Hacer una nueva reserva. ");
        System.out.println("2. Hacer un nuevo registro. ");
        System.out.println("3. Hacer Check-out del huesped.");
        System.out.println("3. Cancelar una reserva.");

        System.out.print("Ingrese una opcion: ");
        String opcionStr = br.readLine();
        int opcion = Integer.parseInt(opcionStr);
        if (opcion == 1) {
            newBooking();
        } else if (opcion == 2) {
            newRegister();
        } else if (opcion == 3) {

        } else if (opcion == 4) {

        }

    }

    private void newRegister() throws Exception {
        System.out.println(" ------ Hacer una nuevo registro -------");

        RegisterHandler registerHandler = new RegisterHandler();
        System.out.println(" ****** Ingreso de datos para el huesped principal: ****** ");
        System.out.print("Ingrese el nombre del nuevo huesped: ");
        String name = br.readLine();
        System.out.print("Ingrese el nombre el numero de identificacion del nuevo huesped:  ");
        String dni = br.readLine();
        System.out.print("Ingrese el nombre el email del nuevo huesped:  ");
        String email = br.readLine();
        System.out.print("Ingrese el nombre el numero de celular del nuevo huesped:  ");
        String phoneNumber = br.readLine();
        System.out.print("Cuantos acompanantes vienen con el huesped principal? : ");
        String numCompanionStr = br.readLine();
        int numCompanion = Integer.parseInt(numCompanionStr);

        ArrayList<CompanionGuest> groupGuests = new ArrayList<>();
        for (int i = 1; i <= numCompanion; i++) {

            System.out.println(" ****** Ingreso de datos para el acompanante " + i + " ****** ");
            System.out.print("Ingrese el nombre del acompanante: ");
            String nameCompanion = br.readLine();
            System.out.print("Ingrese el nunero identificacion del acompanante: ");
            String dniCompanion = br.readLine();
            groupGuests.add(new CompanionGuest(nameCompanion, dniCompanion));
        }

        System.out.println(" ");
        System.out.println("Todos los datos de los huespedes han sido registrados!");
        System.out.println("Estas son las habitaciones disponibles para asignarle a los huespedes:");
        System.out.println(" ");

        registerHandler.createRegister(name, dni, email, phoneNumber, groupGuests, selectRooms(true, null, null));

        hotel.getRegistrationHandler().getData().put(dni, registerHandler.getOpenRegister());
        //TODO Se debe verificar si ya habia antes una reserva con los datos del guest prinicpal


        hotel.getRegistrationHandler().SavePersistentData();

    }

    private void newBooking() throws Exception {
        BookingHandler bookingHdlr = new BookingHandler();
        System.out.print("Nombre del resonsable: ");
        String reserviourName = br.readLine();

        System.out.print("Número de documento del resonsable: ");
        String reserviourDNI = br.readLine();

        System.out.print("Teléfono del resonsable: ");
        String reserviourPhone = br.readLine();

        System.out.print("Correo del resonsable: ");
        String reserviourMail = br.readLine();

        System.out.print("Número de tarjeta de credito del resonsable: ");
        String reserviourSupportCardNumber = br.readLine();

        System.out.print("Número de acompañantes: ");
        int numberOfGuests = Integer.parseInt(br.readLine());

        System.out.print("Fecha del incio de la estadía (YYYY-MM-DD): ");
        LocalDate initialDate = LocalDate.parse(br.readLine());

        System.out.print("Número de dias de la estadía: ");
        LocalDate finalDate = initialDate.plusDays(Integer.parseInt(br.readLine()));

        bookingHdlr.createBooking(reserviourName, reserviourDNI, reserviourPhone, reserviourMail,
                reserviourSupportCardNumber, numberOfGuests, initialDate, finalDate);

        bookingHdlr.reserveRooms(selectRooms(false, initialDate, finalDate), hotel.getRoomsHandler().getData());

        hotel.getBookingsHandler().getData().put(bookingHdlr.getOpenBooking().getReserviourDNI(),
                bookingHdlr.getOpenBooking());

        hotel.getBookingsHandler().SavePersistentData();
    }

    private List<String> selectRooms(boolean isForNow, LocalDate initialDate, LocalDate finalDate) throws Exception {
        hotel.getRoomsHandler().loadPersistentData();
        List<String> selectedRoomsIds = new ArrayList<String>();
        ArrayList<Room> freeRooms;
        String moreRooms;

        do {
            if (isForNow)
                freeRooms = new ArrayList<Room>(hotel.getFreeRooms().values());

            else
                freeRooms = new ArrayList<Room>(hotel.getNotBookedRooms(initialDate, finalDate).values());

            int pos = 1;
            for (Room availableRoom : freeRooms) {
                System.out.println (pos + ". Hab" + availableRoom.getRoomId());
                System.out.println("\t Tipo: " + availableRoom.getType().toString());
                System.out.println("Capacidad: " + availableRoom.getCapacity());
                System.out.println("Camas: " + availableRoom.getBeds());
                System.out.println("Características: " + availableRoom.getFeaturesList());
                pos++;
            }
            System.out.print("Ingrese el número la habitacion que va a ocupar (1-"+ pos +"): ");
            int chooseRoom = Integer.parseInt(br.readLine());
            selectedRoomsIds.add(freeRooms.get(chooseRoom).getRoomId());

            System.out.println("Desea agregar mas habitaciones \n 1. Si \n 2. No");
            moreRooms = br.readLine();
        } while (moreRooms == "1");

        hotel.setOccupied(selectedRoomsIds);
        return selectedRoomsIds;
    }

    /*
     * Se le muestra al empleado el menu de funciones que puede realizar<br>
     * <b>pre:</b> El inventario de los servicios ya debe estar cargado para ser
     * consultado
     * <b>post:</b> El recepcionista puede ver que opciones puede ejecurtar dentro
     * del hotel
     *
     * @throw Exception <br>
     * 1. ????
     */
    private static void showEmployeeScreen() {
        System.out.println("Pantalla funciones de employee");


    }

}
