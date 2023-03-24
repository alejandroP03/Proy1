package Controller;

import java.util.*;

import Model.HotelDataHolder.BookingsDataHandler;
import Model.HotelDataHolder.FaresDataHandler;
import Model.HotelDataHolder.RoomsDataHandler;
import Model.HotelDataHolder.ServicesDataHandler;
import Model.HotelObjects.HotelObject;
import Model.HotelObjects.RoomRelated.Room;
import Model.HotelObjects.Service;

public class Hotel {
    //atributos

    private HashMap<String, Room> roomList;
    private HashMap<String, HotelObject> roomFares;

    private HashMap<String, Service> services;

    private RoomsDataHandler roomsHandler = new RoomsDataHandler();
    private FaresDataHandler faresHandler = new FaresDataHandler();
    private ServicesDataHandler servicesHandler = new ServicesDataHandler();
    private RestaurantDataHandler restaurantHandler = new RestaurantDataHandler();
    private BookingsDataHandler bookingsHandler = new BookingsDataHandler();
    private RegistrationDataHandler registrationHandler = new RegistrationDataHandler();


    //meotodos
    public Map<String, HotelObject> getRoomFares() {
        return roomFares;
    }

    public HashMap<String, Room> getRoomList() {
        return this.roomList;
    }

    public HashMap<String, Service> getServices() {
        return this.services;
    }

    public RoomsDataHandler getRoomsHandler() {
        return this.roomsHandler;
    }

    public FaresDataHandler getFaresHandler() {
        return this.faresHandler;
    }

    public ServicesDataHandler getServicesHandler() {
        return this.servicesHandler;
    }

    public RestaurantDataHandler getRestaurantHandler() {
        return this.restaurantHandler;
    }

    public BookingsDataHandler getBookingsHandler() {
        return this.bookingsHandler;
    }

    public RegistrationDataHandler getRegistrationHandler() {
        return this.registrationHandler;
    }
}
