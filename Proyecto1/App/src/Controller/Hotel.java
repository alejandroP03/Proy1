package Controller;

import java.io.File;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Controller.RegisterHandler.RegisterHandler;
import Model.HotelDataHolder.BookingsDataHandler;
import Model.HotelDataHolder.FaresDataHandler;
import Model.HotelDataHolder.FoodDataHandler;
import Model.HotelDataHolder.RegistrationDataHandler;
import Model.HotelDataHolder.RoomsDataHandler;
import Model.HotelDataHolder.ServicesDataHandler;
import Model.HotelDataHolder.UsersDataHandler;
import Model.HotelObjects.RoomRelated.Room;

public class Hotel {
    // atributos

    private RoomsDataHandler roomsHandler = new RoomsDataHandler(new File("App/data/rooms.json"));
    private FaresDataHandler faresHandler = new FaresDataHandler(new File("App/data/room_fares.json"));
    private ServicesDataHandler servicesHandler = new ServicesDataHandler(new File("App/data/services.json"));
    private FoodDataHandler restaurantHandler = new FoodDataHandler(new File("App/data/foodInfo.json"));
    private UsersDataHandler userHandler = new UsersDataHandler(new File("App/data/users.json"));
    private BookingsDataHandler bookingsHandler = new BookingsDataHandler(new File("App/data/bookings.json"));
    private RegistrationDataHandler registrationHandler = new RegistrationDataHandler(
            new File("App/data/registrations.json"));

    private RegisterHandler registerHandler = new RegisterHandler();

    private static Hotel instancia;


    // private RegistrationDataHandler registrationHandler = new
    // RegistrationDataHandler(new File("App/data/registrations.json"));

    // metodos

    public RoomsDataHandler getRoomsHandler() {
        return this.roomsHandler;
    }

    public ServicesDataHandler getServices() {
        return this.servicesHandler;
    }

    public FoodDataHandler getRestaurantHandler() {
        return this.restaurantHandler;
    }

    public FaresDataHandler getFaresHandler() {
        return faresHandler;
    }

    public RegistrationDataHandler getRegistrationHandler() {
        return registrationHandler;
    }

    public UsersDataHandler getUserHandler() {
        return userHandler;
    }

    public BookingsDataHandler getBookingsHandler() {
        return bookingsHandler;
    }

    public RegisterHandler getRegisterHandler(){
        return this.registerHandler;
    }

    private Hotel(){};

    public static Hotel getInstance(){
        if(instancia == null){
            instancia = new Hotel();
        }
        return instancia;
    }

    public void startUp()throws Exception{
        getUserHandler().loadPersistentData();
        getFaresHandler().loadPersistentData();
        getRoomsHandler().loadPersistentData();
        getBookingsHandler().loadPersistentData();
        getRegistrationHandler().loadPersistentData();
        getRestaurantHandler().loadPersistentData();
        getServices().loadPersistentData();
    }

    public void shutDown()throws Exception{
        getUserHandler().SavePersistentData();
        getFaresHandler().SavePersistentData();
        getRoomsHandler().SavePersistentData();
        getBookingsHandler().SavePersistentData();
        getRegistrationHandler().SavePersistentData();
        getRestaurantHandler().SavePersistentData();
        getServices().SavePersistentData();
    }

    /*
     * Obtiene todas las habitaciones que estén libres
     * <b>pre: </b> RoomsDataHandler debe estar inicializado y con las estructura de datos cargada. <br>
     * <b>pos: </b> Se retorna un mapa que contiene todas las habitaciones libres.
     */
    public Map<String, Room> getFreeRooms() {
        Map<String, Room> freeRooms = new HashMap<String, Room>();

        for (Room room : this.roomsHandler.getData().values()) {
            if (!room.getIsOcupied())
                freeRooms.put(room.getRoomId(), room);
        }

        return freeRooms;
    }
    /*
     * Obtiene las habitaciones que no están reservadas dentro de un rango de fecha
     *
     * 
     * <b>pre: </b> RoomsDataHandler debe estar inicializado y con la estructura de datos cargada. <br>
     * <b>pos: </b> Se retorna un mapa que contiene las habitaciones que no fueron reservadas durante un rango de fechas
     *
     * @param initialDate: Fecha inicial desde la cuál se desea obtener las habitaciones que no están reservadas.
     * @param: finalDate: Fecha final desde la cuál se desea obtener las habitaciones que no están reservadas.
     */
    public Map<String, Room> getNotBookedRooms(LocalDate initialDate, LocalDate finalDate) {

        Map<String, Room> freeRooms = new HashMap<String, Room>();
        for (Room room : this.roomsHandler.getData().values()) {
            if(! room.getBookedDates().isEmpty()){
                for (Map.Entry<LocalDate, LocalDate> roomBookedDates : room.getBookedDates().entrySet()) {
                    LocalDate bookedInitialDate = roomBookedDates.getKey();
                    LocalDate bookedFinalDate = roomBookedDates.getValue();
                    // initialDate empieza antes de que bookedFinalDate termine o finaldate termina
                    // despues de que bookedInitialDate empiece
                    if (initialDate.compareTo(bookedFinalDate) <= 0)
                        break;
                    if (finalDate.compareTo(bookedInitialDate) >= 0)
                        break;
                    else
                        freeRooms.put(room.getRoomId(), room);
                }
            }else{
                freeRooms.put(room.getRoomId(), room);
            }

        }

        return freeRooms;
    }

    /*
     * A las habitaciones recibidas las coloca como ocupadas o libres
     *
     * <b>pre: </b> idSelectedRooms debe existir <br>
     * <b>pos: </b> A las habitaciones pertenecientes a idSelectedRooms se les sitúa como ocupadas o libres <br>
     *
     * @param idSelectedRooms: Lista que contiene los ids de las habitaciones seleccionadas.
     * @param occupied: Booleano que indica si las habitaciones van a colocarse como ocupadas o si van a estar libres.
     */
    public void setOccupied( List<String> idSelectedRooms, boolean occupied){
        Map<Object, Room> roomsList = this.roomsHandler.getData();
        for(String idRoom: idSelectedRooms ){
            roomsList.get(idRoom).setOccupied(occupied);

        }



    }
}
