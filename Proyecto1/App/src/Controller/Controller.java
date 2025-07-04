package Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import Controller.BillService.ServicesBillGenerator;
import Controller.BillService.StayBillGenerator;
import Controller.BookingHandler.BookingHandler;
import Controller.ConsumeHandler.ConsumeRecorder;
import Controller.RegisterHandler.CompanionGuest;
import Controller.RegisterHandler.Guest;
import Controller.RegisterHandler.RegisterHandler;
import Controller.WorkersAuth.HotelWorkersAuth;
import Model.HotelObjects.Booking;
import Model.HotelObjects.Food;
import Model.HotelObjects.Registration;
import Model.HotelObjects.Service;
import Model.HotelObjects.User;
import Model.HotelObjects.UserType;
import Model.HotelObjects.RoomRelated.Bed;
import Model.HotelObjects.RoomRelated.Room;
import Model.HotelObjects.RoomRelated.RoomFares;
import Model.HotelObjects.RoomRelated.RoomFeatures;
import Model.HotelObjects.RoomRelated.RoomModel;
import Model.HotelObjects.RoomRelated.TypeRoom;

public class Controller {
    User activeUser;
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public Hotel hotel = Hotel.getInstance();
    private HotelWorkersAuth authHandler = HotelWorkersAuth.getInstance();

    public Controller() throws Exception {
        hotel.startUp();
    }

    /*
     * AuthMethods
     * 
     * //TODO Volver estos metodos una clase (si queda tiempo)
     */
    public User signUp(String userName, String password, UserType userType) throws Exception {
        Map<Object, User> userList = hotel.getUserHandler().getData();
        User user = authHandler.register(userName, password, userType, userList);
        hotel.getUserHandler().SavePersistentData();
        return user;
    }

    public boolean userExist(String userName, String password) {
        Map<Object, User> userList = hotel.getUserHandler().getData();
        return authHandler.userExists(userName, password, userList);
    }

    public User signIn(String userName, String password) throws Exception {
        Map<Object, User> userList = hotel.getUserHandler().getData();
        User user = authHandler.login(userName, password, userList);
        hotel.getUserHandler().SavePersistentData();
        return user;
    }

    /*
     * Admin methods
     * 
     * //TODO Volver estos metodos una clase (si queda tiempo)
     */

    public Map<Room, RoomFares> getRoomStock() {

        List<Room> roomMap = new ArrayList<Room>(hotel.getRoomsHandler().getData().values());
        Map<Object, RoomFares> roomFares = hotel.getFaresHandler().getData();

        Map<Room, RoomFares> roomInfo = new HashMap<Room, RoomFares>();
        for (Room room : roomMap) {
            Set<Object> roomId = room.createTypeRoomId();
            RoomFares asociatedFares = roomFares.get(roomId);
            roomInfo.put(room, asociatedFares);
        }
        return roomInfo;
    }

    public void createRoom(String location, Map<Bed, Integer> mapBeds, Set<RoomFeatures> featuresList,
            TypeRoom typeChoose) throws Exception {
        hotel.getRoomsHandler().createNewRoom(location, false, mapBeds, featuresList, typeChoose);
        hotel.getRoomsHandler().SavePersistentData();
    }

    public List<Room> getNoFareRooms() {

        return getDifferentiatedRooms()[1];
    }

    public Integer[] getRoomsCount() {
        Integer[] roomCount = new Integer[2];
        roomCount[0] = getDifferentiatedRooms()[0].size();
        roomCount[1] = getRoomStock().size();

        return roomCount;
    }

    public void createFare(Set<Object> roomId, float fare, LocalDate startDate, LocalDate endDate,
            ArrayList<DayOfWeek> daysOfWeek) throws Exception {
        hotel.getFaresHandler().FareCreator(roomId, fare, startDate, endDate, daysOfWeek);
        ;
        hotel.getFaresHandler().SavePersistentData();
    }

    public void loadRoomsFromFile() throws Exception {
        if (hotel.getRoomsHandler().getData().size() == 0 && hotel.getFaresHandler().getData().size() == 0) {
            hotel.getRoomsHandler().loadPersistentData();
            hotel.getFaresHandler().loadPersistentData();
        }

    }

    public int[] getDayOcupation() {
        int[] ocupation = new int[365];
        // Initialize in zero
        for (int i = 0; i < ocupation.length; i++) {
            ocupation[i] = 0;
        }

        int firstInd = LocalDate.now().getDayOfYear();
        List<Booking> bookings = new ArrayList<Booking>(hotel.getBookingsHandler().getData().values());
        for (Booking booking : bookings) {
            LocalDate startDate = booking.getInitialDate();
            LocalDate endDate = booking.getFinalDate();
            for (LocalDate date = startDate; date.isBefore(endDate); date = date.plusDays(1)) {
                if (date.isAfter(LocalDate.now().plusDays(-1)) && date.isBefore(LocalDate.now().plusDays(366))) {
                    // Day is next year
                    if (date.getDayOfYear() - firstInd < 0) {
                        ocupation[date.getDayOfYear() - firstInd + 365] += 1;
                    } else {
                        ocupation[date.getDayOfYear() - firstInd] += 1;
                    }
                }
            }

        }

        List<Registration> registrations = new ArrayList<Registration>(
                hotel.getRegistrationHandler().getData().values());
        for (Registration registration : registrations) {
            LocalDate startDate = registration.getInitialDate();
            LocalDate endDate = registration.getFinalDate();
            for (LocalDate date = startDate; date.isBefore(endDate); date = date.plusDays(1)) {
                if (date.isAfter(LocalDate.now().plusDays(-1)) && date.isBefore(LocalDate.now().plusDays(366))) {
                    // Day is next year
                    if (date.getDayOfYear() - firstInd < 0)
                        ocupation[date.getDayOfYear() - firstInd + 365] += 1;
                    else
                        ocupation[date.getDayOfYear() - firstInd] += 1;
                }
            }
        }

        return ocupation;
    }

    public Map<Object, Food> getFoodStock() {
        return hotel.getRestaurantHandler().getData();
    }

    public Map<Object, Service> getServiceStock() {
        return hotel.getServices().getData();
    }

    // ---------------------- Funciones para el adminsitrador ----------------------

    // ---------------------- Pantalla para el Administrador ----------------------
    /*
     * Se le muestra al administrador las funciones que puede realizar <br>
     * <b>pre:</b> El usuario se registro o inicio sesion dentro de la aplicacion y
     * es administrador el hotel <br>
     * <b>post:</b> El administrasdor elegir cuales funciones puede hacer, como
     * cargar habitaciones, servicios...etc <br>
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
        System.out.println("5. Crear servicios (manual) ");
        System.out.println("6. Cargar archivo de servcios");
        System.out.println("7. Ingresar comidas para el restaurante (manual)");
        System.out.println("8. Cargar menu completo del restaurante");

        System.out.print("Ingrese una opcion: ");
        String opcionStr = br.readLine();
        System.out.println("\n \n");
        switch (opcionStr) {
            case "1":

                break;
            case "2":
                loadDataRooms();
                break;
            case "3":
                loadFares();
                break;
            case "5":
                createService();
                break;
            case "6":
                loadServices();
                break;
            case "7":
                createMenuRestaurant();
                break;
            case "8":
                loadMenuRestaurant();
                break;
            case "0":
                System.out.println("Cerrando aplicacion...");
                hotel.shutDown();
                break;
            default:
                System.out.print("Ingrese una opcion valida: ");
                opcionStr = br.readLine();
                System.out.println("\n \n");
                break;
        }

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
        if (hotel.getRoomsHandler().getData().isEmpty()) {
            hotel.getRoomsHandler().loadPersistentData();
        } else {
            System.out.println("Las habitaciones ya han sido cargadas");
        }
        System.out.println("Desea hacer algo mas? (1 o 2) \n1.Si \n2.No");
        int masOpciones = Integer.parseInt(br.readLine());
        if (masOpciones == 1)
            showAdminScreen();
        if (masOpciones == 2)
            hotel.shutDown();
    }

    /*
     * El administrador crea una nueva tarifa para una habitacion<br>
     * 
     * <b>pre:</b> Debe estar inicializado el mapa de habitaciones y de tarifas
     * <b>post:</b> Se guarda en el hotel la nueva tarifa creada
     *
     */
    private void loadFares() throws Exception {

        System.out.println("------ Crear tarifas para habitaciones ------- ");

        int moreFairs = 0;
        do {
            List<Room>[] differentiatedRooms = getDifferentiatedRooms();
            List<Room> roomsWithFares = differentiatedRooms[0];
            List<Room> roomsWithoutFares = differentiatedRooms[1];
            int pos = 1;
            if (!roomsWithoutFares.isEmpty()) {
                System.out.println(
                        "Los siguientes tipos de habitaciones NO tienen tarifas asignadas para alguno de los proximos 365 dias:");
            }

            for (Room availableRoom : roomsWithoutFares) {
                System.out.println(" ");
                System.out.println("******* Tipo de habitacion #" + pos + " *******");
                System.out.println("Tipo: " + availableRoom.getType().toString());
                System.out.println("Capacidad: " + availableRoom.getCapacity());
                System.out.println("Camas: " + availableRoom.getBeds());
                System.out.println("Características: " + availableRoom.getFeaturesList());
                pos++;
            }

            if (!roomsWithFares.isEmpty()) {
                System.out.println(
                        "Los siguientes tipos de habitaciones SI tienen tarifas asignadas para alguno de los proximos 365 dias:");
            }

            for (Room availableRoom : roomsWithFares) {
                System.out.println(" ");
                System.out.println("******* Tipo de habitacion #" + pos + " *******");
                System.out.println("Tipo: " + availableRoom.getType().toString());
                System.out.println("Capacidad: " + availableRoom.getCapacity());
                System.out.println("Camas: " + availableRoom.getBeds());
                System.out.println("Características: " + availableRoom.getFeaturesList());
                pos++;
            }

            System.out.print("Elija a que habitacion desea agreagarle una nueva tarifa: ");
            Room roomSelected;
            int addFair = Integer.parseInt(br.readLine());
            if (addFair - 1 < roomsWithoutFares.size())
                roomSelected = roomsWithoutFares.get(addFair - 1);
            else
                roomSelected = roomsWithFares.get(addFair - 1 - roomsWithoutFares.size());

            RoomModel roomModel = new RoomModel(roomSelected.getType(), roomSelected.getBeds(),
                    roomSelected.getFeaturesList());

            System.out.print("Ingrese el precio para la habitacion: ");
            int price = Integer.parseInt(br.readLine());

            ArrayList<LocalDate> localDatesList = getGoodDates(false);
            ArrayList<DayOfWeek> daysList = getDaysOfWeek();

            hotel.getFaresHandler().FareCreator(roomModel.createTypeRoomId(), price, localDatesList.get(0),
                    localDatesList.get(1), daysList);

            System.out.println("Desea cargar otra tarifa? (1 o 2) \n1.Si \n2.No");
            moreFairs = Integer.parseInt(br.readLine());

        } while (moreFairs == 1);

        hotel.getFaresHandler().SavePersistentData();
        System.out.println("Tarifa agregada exitosamente!");
        System.out.println(" ");
        System.out.println("Desea hacer algo mas? (1 o 2) \n1.Si \n2.No");
        int masOpciones = Integer.parseInt(br.readLine());
        if (masOpciones == 1)
            showAdminScreen();
        else
            hotel.shutDown();

    }

    /*
     * Retorna un arreglo con dos listas, una con las habitaciones que tienen
     * tarifas y otra con las que no tienen tarifas para los proximos 365 dias
     * respectivamente<br>
     * 
     * @return List<Room>[] <br>
     */
    private List<Room>[] getDifferentiatedRooms() {
        @SuppressWarnings("unchecked")
        ArrayList<Room>[] allRooms = new ArrayList[2];
        Map<Object, RoomFares> mapFares = hotel.getFaresHandler().getData();
        Map<Object, Room> mapRooms = hotel.getRoomsHandler().getData();
        Map<Object, Room> roomWithoutFares = new HashMap<Object, Room>();
        Map<Object, Room> roomWithFares = new HashMap<Object, Room>();

        for (Room room : mapRooms.values()) {
            Set<Object> key = room.createTypeRoomId();
            if (!roomWithoutFares.containsKey(key) && !roomWithFares.containsKey(key)) {
                if (!mapFares.containsKey(key)) {
                    roomWithoutFares.put(key, room);
                } else {
                    if (!mapFares.get(key).hasFare(LocalDate.now(), LocalDate.now().plusDays(364)))
                        roomWithoutFares.put(key, room);
                    else
                        roomWithFares.put(key, room);
                }
            }

        }
        allRooms[0] = new ArrayList<Room>(roomWithFares.values());
        allRooms[1] = new ArrayList<Room>(roomWithoutFares.values());

        return allRooms;
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

        ArrayList<DayOfWeek> daysList = getDaysOfWeek();
        System.out.println("Ingrese la hora inicial disponible del servicio (en formato HH de 24 horas) ");
        String initialDateStr = br.readLine();
        int initialDateOption = Integer.parseInt(initialDateStr);

        System.out.println("Ingrese la hora final disponible del servicio (en formato HH de 24 horas)");
        String finalDateStr = br.readLine();
        int finalDateOption = Integer.parseInt(finalDateStr);

        LocalTime initialDate = LocalTime.of(initialDateOption, 0);
        LocalTime finalDate = LocalTime.of(finalDateOption, 0);

        hotel.getServices().createNewService(id, name, precio, isForGroup, daysList, initialDate, finalDate);
        hotel.getServices().SavePersistentData();
        System.out.println("Servicio agregado exitosamente!");
        System.out.println(" ");
        System.out.println("Desea hacer algo mas? \n1.Si \n2.No");
        int masOpciones = Integer.parseInt(br.readLine());
        if (masOpciones == 1)
            showAdminScreen();

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
        if (hotel.getServices().getData().isEmpty()) {
            hotel.getServices().loadPersistentData();
        }
        System.out.println("Archivo cargado exitosamente!");
        System.out.println("Desea hacer algo mas? \n1.Si \n2.No");
        String masOpciones = br.readLine();
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

        System.out.println("Ingrese que tipo de comida/bebida es");
        System.out.println("1.Desayuno");
        System.out.println("2. Almuerzo");
        System.out.println("3. Cena");
        System.out.println("4. Bebida");
        System.out.println("5. Licor");
        System.out.print("ingrese una opcion (1,2,3,4,5): ");
        String typeFoodStr = br.readLine();
        double optionFood = Integer.parseInt(typeFoodStr);
        String typeFood = null;
        if (optionFood == 1) {
            typeFood = "Desayuno";
        } else if (optionFood == 2) {
            typeFood = "Almuerzo";
        } else if (optionFood == 3) {
            typeFood = "Cena";
        } else if (optionFood == 4) {
            typeFood = "Bebida";
        } else if (optionFood == 5) {
            typeFood = "Licor";
        }
        hotel.getRestaurantHandler().createNewFood(idFood, nameFood, priceFood, isRoomService, typeFood);
        hotel.getRestaurantHandler().SavePersistentData();

        System.out.println("Comida/bebida agregada exitosamente! ");
        System.out.println(" ");
        System.out.println("Desea hacer algo mas? \n1.Si \n2.No");
        System.out.print("ingrese una opcion (1 o 2): ");
        int masOpciones = Integer.parseInt(br.readLine());
        if (masOpciones == 1)
            showAdminScreen();

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
        if (hotel.getRestaurantHandler().getData().isEmpty()) {
            hotel.getRestaurantHandler().loadPersistentData();
        }
        System.out.println("Archivo cargado exitosamente!");
        System.out.println("Desea hacer algo mas? \n1.Si \n2.No");
        String masOpciones = br.readLine();
    }

    /*
     * Se le muestra al recepcionista el menu de funciones que puede realizar<br>
     * <b>pre:</b> El inventario del hotel ya debe estar cargado para ser consultado
     * <b>post:</b> El recepcionista puede ver que opciones puede ejecurtar dentro
     * del hotel
     *
     * @throw Exception <br>
     *
     *
     */
    private void showRecepcionistScreen() throws Exception {
        System.out.println("------  Inicio como recepcionista ------- ");
        System.out.println("1. Hacer una nueva reserva. ");
        System.out.println("2. Hacer un nuevo registro. ");
        System.out.println("3. Hacer Check-out del huesped.");
        System.out.println("4. Cancelar una reserva.");

        System.out.print("Ingrese una opcion: ");
        String opcionStr = br.readLine();
        int opcion = Integer.parseInt(opcionStr);
        if (opcion == 1) {
            newBooking();
        } else if (opcion == 2) {
            newRegister();
        } else if (opcion == 3) {
            checkOut();
        } else if (opcion == 4) {

        }

    }

    /*
     * Pide los datos al usuario para realizar un nuevo registro.
     *
     * <b>pre: </b> La información de RegistrationHandler ya debe estar cargada.
     * <br>
     * <b>pos: </b> Se crea un nuevo registro con los datos obtenidos
     *
     * @throws Exception <br>
     */
    private void newRegister() throws Exception {
        System.out.println(" ------ Hacer una nuevo registro -------");
        RegisterHandler registerHandler = new RegisterHandler();

        System.out.println("Tiene reserva \n 1. Si 2. No: ");
        int optionReserve = Integer.parseInt(br.readLine());
        if (optionReserve == 1) {
            System.out.print("Ingrese el nombre el numero de identificacion del nuevo huesped:  ");
            String dni = br.readLine();
            System.out.print("Cuantos acompanantes vienen con el huesped principal? : ");
            String numCompanionStr = br.readLine();
            int numCompanion = Integer.parseInt(numCompanionStr);
            ArrayList<CompanionGuest> groupGuests = new ArrayList<>();
            for (int i = 1; i <= numCompanion; i++) {

                System.out.println(" ****** Ingreso de datos para el acompanante " + i + " ****** ");
                System.out.print("Ingrese el nombre del acompanante: ");
                String nameCompanion = br.readLine();
                System.out.print("Ingrese el número de identificacion del acompanante: ");
                String dniCompanion = br.readLine();
                groupGuests.add(new CompanionGuest(nameCompanion, dniCompanion));
            }

            registerHandler.getAsociatedBooking(dni, hotel.getBookingsHandler().getData(), groupGuests);
            hotel.getRegistrationHandler().getData().put(dni, registerHandler.getOpenRegister());
            hotel.setOccupied(registerHandler.getOpenRegister().getRegisterRoomsIds(), true);
        } else {

            System.out.println(" ****** Ingreso de datos para el huesped principal: ****** ");
            System.out.print("Ingrese el nombre del nuevo huesped: ");
            String name = br.readLine();
            System.out.print("Ingrese el numero de identificacion del nuevo huesped:  ");
            String dni = br.readLine();
            System.out.print("Ingrese el nombre el email del nuevo huesped:  ");
            String email = br.readLine();
            System.out.print("Ingrese el numero de celular del nuevo huesped:  ");
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
            // System.out.print("Fecha del incio de la estadía (YYYY-MM-DD): ");
            // LocalDate initialDate = LocalDate.parse(br.readLine());
            //
            // System.out.print("Número de dias de la estadía: ");
            // LocalDate finalDate = initialDate.plusDays(Integer.parseInt(br.readLine()));
            ArrayList<LocalDate> localDates = getGoodDates(true);
            System.out.println(" ");
            System.out.println("Todos los datos de los huespedes han sido registrados!");
            System.out.println("Estas son las habitaciones disponibles para asignarle a los huespedes:");
            System.out.println(" ");

            registerHandler.createRegister(name, dni, email, phoneNumber, groupGuests, selectRooms(true, null, null),
                    localDates.get(0), localDates.get(1));

            hotel.getRegistrationHandler().getData().put(dni, registerHandler.getOpenRegister());
        }

        hotel.getRegistrationHandler().SavePersistentData();

        System.out.println("Desea hacer algo mas? \n1.Si \n2.No");
        String masOpciones = br.readLine();
        switch (masOpciones) {
            case "1":
                showRecepcionistScreen();
                break;
            case "2":
                hotel.shutDown();
                System.out.println("Cerrando aplicacion...");
                break;
            default:
                hotel.shutDown();
                break;
        }

    }

    private void checkOut() throws Exception {
        System.out.println("Ingrese el DNI del responsable: ");
        String dniRes = br.readLine();
        Registration closeRegistration = hotel.getRegistrationHandler().getData().get(dniRes);
        List<String> roomsIds = closeRegistration.getRegisterRoomsIds();
        StayBillGenerator billGenerator = new StayBillGenerator(closeRegistration);
        System.out.println("Su factura es: ");

        System.out.println(billGenerator.calculateTotalCost(hotel.getFaresHandler().getData(),
                hotel.getServices().getData(), hotel.getRestaurantHandler().getData(),
                hotel.getRoomsHandler().getData()));
        billGenerator.showBill(hotel.getFaresHandler().getData(), hotel.getServices().getData(),
                hotel.getRestaurantHandler().getData(), hotel.getRoomsHandler().getData());
        hotel.setOccupied(roomsIds, false);
        System.out.println("Se realizó efectivamente el check out");

    }

    private void newBooking() throws Exception {
        BookingHandler bookingHdlr = new BookingHandler();
        System.out.print("Nombre del responsable: ");
        String reserviourName = br.readLine();

        System.out.print("Número de documento del responsable: ");
        String reserviourDNI = br.readLine();

        System.out.print("Teléfono del responsable: ");
        String reserviourPhone = br.readLine();

        System.out.print("Correo del responsable: ");
        String reserviourMail = br.readLine();

        System.out.print("Número de tarjeta de credito del responsable: ");
        String reserviourSupportCardNumber = br.readLine();

        System.out.print("Número de acompañantes: ");
        int numberOfGuests = Integer.parseInt(br.readLine());

        ArrayList<LocalDate> localDates = getGoodDates(false);

        bookingHdlr.createBooking(reserviourName, reserviourDNI, reserviourPhone, reserviourMail,
                reserviourSupportCardNumber, numberOfGuests, localDates.get(0), localDates.get(1));

        bookingHdlr.reserveRooms(selectRooms(false, localDates.get(0), localDates.get(1)),
                hotel.getRoomsHandler().getData());

        hotel.getBookingsHandler().getData().put(bookingHdlr.getOpenBooking().getReserviourDNI(),
                bookingHdlr.getOpenBooking());

        hotel.shutDown();
        System.out.println("Desea hacer algo mas? \n1.Si \n2.No");
        String masOpciones = br.readLine();
        switch (masOpciones) {
            case "1":
                showRecepcionistScreen();
                break;
            case "2":
                hotel.shutDown();
                System.out.println("Cerrando aplicacion...");
                break;
            default:
                hotel.shutDown();
                break;
        }

    }

    public ArrayList<Room> freeRooms() {
        ArrayList<Room> freeRoomsList;
        return freeRoomsList = new ArrayList<Room>(hotel.getFreeRooms().values());
    }

    private List<String> selectRooms(boolean isForNow, LocalDate initialDate, LocalDate finalDate) throws Exception {
        List<String> selectedRoomsIds = new ArrayList<String>();
        ArrayList<Room> freeRooms;
        String moreRooms;

        do {
            if (isForNow) {
                freeRooms = new ArrayList<Room>(hotel.getFreeRooms().values());

            } else
                freeRooms = new ArrayList<Room>(hotel.getNotBookedRooms(initialDate, finalDate).values());

            int pos = 1;
            for (Room availableRoom : freeRooms) {
                System.out.println(" ");
                System.out.println("--------- " + pos + ". Habitacion: " + " --------- ");
                System.out.println("Id de la habitacion: " + availableRoom.getRoomId());
                System.out.println("Tipo: " + availableRoom.getType().toString());
                System.out.println("Capacidad: " + availableRoom.getCapacity());
                System.out.println("Camas: " + availableRoom.getBeds());
                System.out.println("Características: " + availableRoom.getFeaturesList());
                pos++;
            }
            System.out.print("Ingrese el número de la habitacion que va a ocupar (1-" + (pos - 1) + "): ");
            int chooseRoom = Integer.parseInt(br.readLine());
            selectedRoomsIds.add(freeRooms.get(chooseRoom - 1).getRoomId());

            System.out.println("Desea agregar mas habitaciones \n 1. Si \n 2. No");
            moreRooms = br.readLine();
            System.out.println(moreRooms.equals("1"));
        } while (moreRooms.equals("1"));

        System.out.println(selectedRoomsIds);
        hotel.getRoomsHandler().SavePersistentData();
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
     *
     */
    private void showEmployeeScreen() throws Exception {
        System.out.println("------ Pantalla funciones de employee ------ ");
        System.out.println("Servicios disponibles para el huesped: ");
        int moreServices = 0;
        do {
            System.out.println("1. Restaurante \n2.Otros servicios");
            int kindService = Integer.parseInt(br.readLine());
            if (kindService == 1) {
                showRestaurantOptions();
            } else {
                showOtherServices();
            }
            System.out.println("Desea consumir mas servicios? \n1.Si\n2.No");
            System.out.println("Ingrese una opcion: ");
            moreServices = Integer.parseInt(br.readLine());
        } while (moreServices == 1);

    }

    /*
     * Muestra las opciones disponibles que posee el restaurante.
     * <b>pre: </b>El mapa de comidas debe estar inicializado y con información <br>
     * <b>pos: </b>Muestra los servicios disponibles
     *
     * @throws Exception
     */
    public void showRestaurantOptions() throws Exception {
        // hotel.getRestaurantHandler().loadPersistentData();

        ConsumeRecorder<Food> newConsumes = new ConsumeRecorder<Food>();
        Map<Object, Food> mapFoods = hotel.getRestaurantHandler().getData();
        ArrayList<Food> foodsList = new ArrayList<>();
        for (Food eachFood : mapFoods.values()) {
            foodsList.add(eachFood);
        }
        ArrayList<Food> addedFoods = new ArrayList<>();

        int moreFoods = 0;
        do {
            System.out.println("----- 1. Restaurante -----  ");
            System.out.println("Menu del restaurante: ");
            int posf = 1;
            for (Food availableFood : foodsList) {
                System.out.println(" ");
                System.out.println(" ****** Item # " + posf + " *******");
                System.out.println("Producto: " + availableFood.getName());
                System.out.println("Se puede subir a la habitacion: " + availableFood.getIsRoomService());
                System.out.println("Tipo de comida: " + availableFood.getAvailability());
                System.out.println("Precio de la comida: " + availableFood.getPrice());
                posf++;
            }
            System.out.print("Item a consumir: ");
            int chooseElementMenu = Integer.parseInt(br.readLine());
            Food foodChoosen = foodsList.get(chooseElementMenu - 1);
            addedFoods.add(foodChoosen);
            System.out.println(foodChoosen.getName() + " Ha sido agregado!");
            System.out.println("Desea agregar mas elementos del menu? \n1.Si\n2.No");
            System.out.print("Ingrese una opcion: ");
            moreFoods = Integer.parseInt(br.readLine());

        } while (moreFoods == 1);

        System.out.println("Como desea pagar por lo elementos consumidos del menu?");
        System.out.println("1. Pagar ya\n2. Que se cargue a la habitacion para pagar al hacer check-out: ");
        System.out.print("Ingrese una opcion: ");
        int methodPaid = Integer.parseInt(br.readLine());
        if (methodPaid == 1) {
            ArrayList<String> listFoodsId = new ArrayList<>();
            for (Food foodAdded : addedFoods) {
                listFoodsId.add(foodAdded.getId());
            }
            System.out.println("Factura generada exitosamente!");
            Guest guesSearch = searchConsumer();
            String newBill = newConsumes.handleInstantPay(listFoodsId, guesSearch, mapFoods);
            ServicesBillGenerator newBillPay = new ServicesBillGenerator(newBill, guesSearch);
            System.out.println("------ Informacion de la factura --------");
            System.out.println(newBill);
            newBillPay.showBill();

        } else {
            System.out.print("Numero identificacion del huesped principal (sobre el que se hizo el registro) :  ");
            String dniConsumer = br.readLine();
            ArrayList<String> listFoods = new ArrayList<>();
            for (Food eachFood : addedFoods) {
                newConsumes.registerConsumption(listFoods, eachFood);
            }
            System.out.println(listFoods);
            searchGuestRegistration(dniConsumer).setConsumedFoods(listFoods);
            System.out.println("Se ha registrado el consumo exitosamente!");

        }
        hotel.getRegistrationHandler().SavePersistentData();
        System.out.println("Desea hacer algo mas? \n1.Si \n2.No");
        String masOpciones = br.readLine();
        switch (masOpciones) {
            case "1":
                showEmployeeScreen();
                break;
            case "2":
                hotel.shutDown();
                System.out.println("Cerrando aplicacion...");
                break;
            default:
                hotel.shutDown();
                break;
        }

    }

    /*
     * Muestra los servicios disponibles que posee el hotel.
     * <b>pre: </b>El mapa de servicos debe estar inicializado y con información
     * <br>
     * <b>pos: </b>Muestra los servicios disponibles
     *
     * @throws Exception
     */
    public void showOtherServices() throws Exception {

        ConsumeRecorder<Service> newConsumes = new ConsumeRecorder<Service>();

        Map<Object, Service> mapServices = hotel.getServices().getData();
        ArrayList<Service> servicesList = new ArrayList<>();
        for (Service eachService : mapServices.values()) {
            servicesList.add(eachService);
        }
        ArrayList<Service> addedServices = new ArrayList<Service>();

        int moreServices = 0;
        do {
            int pos = 1;
            for (Service availableService : servicesList) {
                System.out.println(" ");
                System.out.println("------ Servicio#" + pos + ": " + availableService.getName() + " ------");
                System.out.println("Dias disponibles: " + availableService.getDaysAvailable());
                System.out.println("Hora que abre el servicio: " + availableService.getInitialTime());
                System.out.println("Hora que cierra el servicio: " + availableService.getFinalTime());
                System.out.println("Precio del servicio: " + availableService.getPrice());
                pos++;
            }
            System.out.print("Servicio que desea consumir: ");
            int chooseService = Integer.parseInt(br.readLine());
            Service serviceChoosen = servicesList.get(chooseService - 1);
            System.out.println();
            addedServices.add(serviceChoosen);

            System.out.println("El servicio: " + serviceChoosen.getName() + " Ha sido agregado!");
            System.out.println(" ");

            System.out.println("Desea consumir mas servicios? \n1.Si\n2.No");
            System.out.print("ingrese una opcion: ");
            moreServices = Integer.parseInt(br.readLine());

        } while (moreServices == 1);
        System.out.println("Como desea pagar por los servicios consumidos?");
        System.out.println("1. Pagar ya\n2. Que se cargue a la habitacion para pagar al hacer check-in ");
        System.out.print("ingrese una opcion: ");

        int methodPaid = Integer.parseInt(br.readLine());
        if (methodPaid == 1) {

            ArrayList<String> listServicesId = new ArrayList<>();
            for (Service eachIdService : addedServices) {
                listServicesId.add(eachIdService.getId());
            }
            System.out.println("Factura generada exitosamente!");
            Guest guesSearch = searchConsumer();
            String newBill = newConsumes.handleInstantPay(listServicesId, guesSearch, hotel.getServices().getData());
            ServicesBillGenerator newBillPay = new ServicesBillGenerator(newBill, guesSearch);
            System.out.println("------ Informacion de la factura --------");
            System.out.println(newBill);
            newBillPay.showBill();

        } else {
            System.out.print("Numero identificacion del huesped principal (sobre el que se hizo el registro) :  ");
            String dniConsumer = br.readLine();
            ArrayList<String> listServices = new ArrayList<>();

            for (Service eachIdService : addedServices) {
                newConsumes.registerConsumption(listServices, eachIdService);
            }
            searchGuestRegistration(dniConsumer).setConsumedServices(listServices);
            System.out.println("Se ha registrado el consumo exitosamente!");

        }
        hotel.getRegistrationHandler().SavePersistentData();
        System.out.println("Desea hacer algo mas? \n1.Si \n2.No");
        String masOpciones = br.readLine();
        switch (masOpciones) {
            case "1":
                showRecepcionistScreen();
                break;
            case "2":
                hotel.shutDown();
                System.out.println("Cerrando aplicacion...");
                break;
            default:
                hotel.shutDown();
                break;
        }
    }

    /*
     * Busca un registro dentro del mapa de registros para devolverlo
     *
     * <b>pre: </b> El mapa de registros debe estar cargado y con información. <br>
     * <b>pos: </b> Retorna el registro asociado al DNI dado. <br>
     * 
     * @param dni: DNI del huésped del que se desea encontrar su registro. <br>
     * 
     * @throws IOException
     */
    public Registration searchGuestRegistration(String dni) throws IOException {
        Map<Object, Registration> mapRegisters = hotel.getRegistrationHandler().getData();
        return mapRegisters.get(dni);
    }

    /*
     * Busca un huésped con ayuda del mapa de registros para saber si es principal o
     * invitado
     * con el fin de generar la factura inmediata a su nombre.
     * <b>pre: </b> El mapa de registros debe estar cargado y con información. <br>
     * <b>pos: </b> Retorna el huésped. <br>
     *
     * @throws IOException
     */
    public Guest searchConsumer() throws IOException {
        Map<Object, Registration> mapRegisters = hotel.getRegistrationHandler().getData();
        System.out.println("Elija el tipo de huesped que es:");
        System.out.println("1. Huesped principal (Sobre el que se hizo la reserva)  ");
        System.out.println("2. Huesped invitado");
        int isInvited = Integer.parseInt(br.readLine());
        if (isInvited == 1) {
            System.out.print("Ingrese su numero de identificacion: ");
            String dni = br.readLine();
            System.out.println("----------Informacion de la factura --------");
            return mapRegisters.get(dni).getPrincipalGuest();

        } else {
            System.out.print("Ingrese el dni de la persona responsable (El huésped principal) ");
            String principalDni = br.readLine();
            System.out.print("Ingrese su dni: ");
            String invitedDni = br.readLine();
            for (CompanionGuest eachCompanion : mapRegisters.get(principalDni).getGroupGuest()) {
                System.out.println(eachCompanion.getDni());
                if (eachCompanion.getDni().equals(invitedDni)) {
                    System.out.println("Usuario encontrado!");
                    return eachCompanion;
                }
                ;
            }

        }
        System.out.println("No se ha encontrado ningun usuario con esta identificacion.");
        return null;
    }

    private ArrayList<LocalDate> getGoodDates(boolean withNumOfDays) throws Exception {
        /*
         * Valida que las fechas que se pasan por paramtero sean correctas (sin errores)
         * de digitacion, que la inicial no este despues de la final y que sean actuales
         * (de hoy, o dias futuros)
         *
         * @param withNumOfDays: true si se quiere preguntar cuantos dias despues de la
         * fecha
         * inicial y false si se quiere preguntar la fecha exacta
         *
         */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        LocalDate now = LocalDate.now();
        ArrayList<LocalDate> dates = new ArrayList<LocalDate>();
        LocalDate initialDate = null;
        LocalDate finalDate = null;

        while (initialDate == null) {
            System.out.println("Fecha inicial (YYYY-MM-DD): ");
            String initialDateStr = br.readLine();
            try {
                initialDate = LocalDate.parse(initialDateStr);
            } catch (Exception e) {
                System.out.println("Error! \n la fecha debe estar en el formato indicado");
            }
            if (initialDate != null) {
                if (initialDate.isBefore(now)) {
                    System.out.println("Error! \n la fecha no puede ser del pasado");
                    initialDate = null;
                }
            }

        }
        while (finalDate == null) {
            if (withNumOfDays) {
                System.out.println("Duracion (numero de dias): ");
                String plusDays = br.readLine();
                try {
                    finalDate = initialDate.plusDays(Integer.parseInt(plusDays));
                } catch (Exception e) {
                    System.out.println("Error! \n debe ser un numero positivo");
                }
            } else {
                System.out.println("Fecha final (YYYY-MM-DD): ");
                String finalDateStr = br.readLine();
                try {
                    finalDate = LocalDate.parse(finalDateStr);
                } catch (Exception e) {
                    System.out.println("Error! \n la fecha debe estar en el formato indicado");
                }
                if (finalDate != null) {
                    if (finalDate.isBefore(now)) {
                        System.out.println("Error! \n la fecha no puede ser del pasado");
                        finalDate = null;
                    } else if (initialDate.compareTo(finalDate) >= 0) {
                        System.out.println("Error! \n la fecha inicial debe ser menor a la fecha final");
                        finalDate = null;
                    }
                }
            }

        }
        dates.add(initialDate);
        dates.add(finalDate);
        return dates;
    }

    private ArrayList<DayOfWeek> getDaysOfWeek() throws Exception {
        /*
         * Pide a traves de la consola los dias de la semana que se quieren
         *
         */
        String moreDays = "0";
        DayOfWeek[] days = DayOfWeek.values();
        Set<DayOfWeek> selectedDays = new HashSet<DayOfWeek>();
        do {
            for (int i = 0; i < days.length; i++) {
                System.out.println((i + 1) + ". " + days[i]);
            }
            System.out.println("8. Dias entre semana");
            System.out.println("9. Fines de semana");
            System.out.println("10. Todos los dias");

            int selection = Integer.parseInt(br.readLine());
            if (selection >= 1 && selection <= 7) {
                selectedDays.add(days[selection - 1]);
            } else if (selection == 8) {
                selectedDays.add(DayOfWeek.MONDAY);
                selectedDays.add(DayOfWeek.TUESDAY);
                selectedDays.add(DayOfWeek.WEDNESDAY);
                selectedDays.add(DayOfWeek.THURSDAY);
                selectedDays.add(DayOfWeek.FRIDAY);
            } else if (selection == 9) {
                selectedDays.add(DayOfWeek.SATURDAY);
                selectedDays.add(DayOfWeek.SUNDAY);
            } else if (selection == 10) {
                selectedDays.addAll(Arrays.asList(days));
            }

            if (selectedDays.size() < 7) {
                System.out.println("Desea agregar mas dias? (1. Si, 2. No)");
                moreDays = br.readLine();
            } else {
                moreDays = "0";
            }
        } while (moreDays.equals("1"));

        return new ArrayList<DayOfWeek>(selectedDays);
    }

    private String getFeaturesName(Set<RoomFeatures> feature) {
        String features = "";
        for (RoomFeatures eachFeature : feature) {
            switch (eachFeature) {
                case BALCONY:
                    features += "Balcon, ";
                    break;
                case LANDSCAPE_VIEW:
                    features += "Vista al paisaje, ";
                    break;
                case KITCHEN:
                    features += "Cocina, ";
                    break;
            }
        }
        return features.substring(0, features.length() - 2);
    }

    private String getBedsString(Map<Bed, Integer> beds) {
        String bedsString = "";
        for (Map.Entry<Bed, Integer> entry : beds.entrySet()) {
            bedsString += entry.getKey().toString().toLowerCase().replace("_", " ") + ": " + entry.getValue() + ", ";

        }
        return bedsString.substring(0, bedsString.length() - 2);
    }
}
