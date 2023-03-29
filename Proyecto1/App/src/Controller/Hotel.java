package Controller;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import Controller.RegisterHandler.RegisterHandler;
import Model.HotelDataHolder.*;
import Model.HotelObjects.*;
import Model.HotelObjects.RoomRelated.Room;
import Model.HotelObjects.RoomRelated.RoomFares;

public class Hotel {
    // atributos

    private HashMap<String, Room> roomList;
    private HashMap<String, HotelObject> roomFares;

    private HashMap<String, Service> services;

    private RoomsDataHandler roomsHandler = new RoomsDataHandler(new File("App/data/rooms.json"));
    private FaresDataHandler faresHandler = new FaresDataHandler(new File("App/data/room_fares.json"));
    private ServicesDataHandler servicesHandler = new ServicesDataHandler(new File("App/data/services.json"));
    private FoodDataHandler restaurantHandler = new FoodDataHandler(new File("App/data/foodInfo.json"));
    //private BookingsDataHandler bookingsHandler = new BookingsDataHandler(new File("App/data/bookings.json"));
    private RegistrationDataHandler registrationHandler = new RegistrationDataHandler(new File("App/data/registrations.json"));

    private RegisterHandler registerHandler = new RegisterHandler();

    private Map<Object,Room> roomsList;
    private Map<Object,RoomFares> roomFaresList;
    private Map<Object,Service> servicesList;
    private Map<Object,Food> foodList;
    private Map<Object, Booking> bookingList;
    private Map<Object, Registration> registrationList;


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




//
//    public FaresDataHandler getFaresHandler() {
//        return this.faresHandler;
//    }
//
//    public ServicesDataHandler getServicesHandler() {
//        return this.servicesHandler;
//    }

//
//    public BookingsDataHandler getBookingsHandler() {
//        return this.bookingsHandler;
//    }
//
//    public RegistrationDataHandler getRegistrationHandler() {
//        return this.registrationHandler;
//    }
}
