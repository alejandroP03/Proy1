package Controller.BookingHandler;

import java.time.LocalDate;
import java.util.ArrayList;

import org.json.simple.JSONObject;

import Controller.Hotel;
import Model.HotelObjects.HotelObject;
import Model.HotelObjects.RoomRelated.Room;

public class Booking implements HotelObject {
    // Atributos
    private String reserviourName;
    private String reservoirDNI;

    private String reserviourPhone;
    private String reserviourEmail;
    private String reserviourSupportCardNumber;
    private int numberOfGuests;
    private LocalDate initialDate;
    private LocalDate finalDate;
    private Hotel roomsToReserve;
    private ArrayList<Room> roomsReserved;

    // constructor
    public Booking(String reserviourName, String reservoirDNI, String reserviourPhone, String reserviourEmail,
            String reserviourSupportCardNumber, int numberOfGuests, LocalDate initialDate, LocalDate finalDate) {
        this.reserviourName = reserviourName;
        this.reservoirDNI = reservoirDNI;
        this.reserviourPhone = reserviourPhone;
        this.reserviourEmail = reserviourEmail;
        this.reserviourSupportCardNumber = reserviourSupportCardNumber;
        this.numberOfGuests = numberOfGuests;
        this.finalDate = finalDate;
        this.initialDate = initialDate;
        this.roomsReserved = new ArrayList<>();

    }
    // metodos

    public void setRoomsReserved(Room rooms) {
        this.roomsReserved.add(rooms);

    }

    public String getReserviourName() {
        return this.reserviourName;
    }

    public LocalDate getInitialDate() {
        return this.initialDate;
    }

    public LocalDate getFinalDate() {
        return this.finalDate;
    }

    public ArrayList<Room> getRoomsReserved() {
        return this.roomsReserved;
    }

    @Override
    public JSONObject getJsonObject() {
        return null;
    }
}
