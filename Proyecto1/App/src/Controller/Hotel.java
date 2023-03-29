package Controller;

import java.io.File;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

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

    public Map<String, Room> getFreeRooms() {
        Map<String, Room> freeRooms = new HashMap<String, Room>();

        for (Room room : this.roomsHandler.getData().values()) {
            if (!room.getIsOcupied())
                freeRooms.put(room.getRoomId(), room);
        }

        return freeRooms;
    }

    public Map<String, Room> getNotBookedRooms(LocalDate initialDate, LocalDate finalDate) {
        Map<String, Room> freeRooms = new HashMap<String, Room>();
        for (Room room : this.roomsHandler.getData().values()) {
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
        }

        return freeRooms;
    }
}
