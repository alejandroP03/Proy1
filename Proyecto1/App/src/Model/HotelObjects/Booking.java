package Model.HotelObjects;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import Model.HotelObjects.RoomRelated.Room;

public class Booking implements HotelObject {
    // Atributos
    private String reserviourName;
    private String reserviourDNI;
    private String reserviourPhone;
    private String reserviourEmail;
    private String reserviourSupportCardNumber;
    private int numberOfGuests;
    private LocalDate initialDate;
    private LocalDate finalDate;
    private ArrayList<Room> reservedRooms;

    // constructor
    public Booking(String reserviourName, String reserviourDNI, String reserviourPhone, String reserviourEmail,
            String reserviourSupportCardNumber, int numberOfGuests, LocalDate initialDate, LocalDate finalDate) {
        this.reserviourName = reserviourName;
        this.reserviourDNI = reserviourDNI;
        this.reserviourPhone = reserviourPhone;
        this.reserviourEmail = reserviourEmail;
        this.reserviourSupportCardNumber = reserviourSupportCardNumber;
        this.numberOfGuests = numberOfGuests;
        this.finalDate = finalDate;
        this.initialDate = initialDate;
        this.reservedRooms = new ArrayList<Room>();
    }

    public void setReservedRooms(ArrayList<Room> reservedRooms) {
        this.reservedRooms = reservedRooms;
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

    @Override
    public JSONObject getJsonObject() {
        Map<String, Object> bookingData = new HashMap<String, Object>();

        bookingData.put("reserviourDNI", reserviourDNI);
        bookingData.put("reserviourPhone", reserviourPhone);
        bookingData.put("reserviourEmail", reserviourEmail);
        bookingData.put("reserviourSupportCardNumber", reserviourSupportCardNumber);
        bookingData.put("numberOfGuests", numberOfGuests);
        bookingData.put("initialDate", initialDate);
        bookingData.put("finalDate", finalDate);
        bookingData.put("reserviourName", reserviourName);

        List<String> reservedRoomsIds = new ArrayList<String>();
        for (Room room : reservedRooms) {
            reservedRoomsIds.add(room.getRoomId());
        }
        bookingData.put("reservedRoomsIds", (JSONArray) reservedRoomsIds);

        return new JSONObject(bookingData);
    }
}
