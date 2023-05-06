package Model.HotelObjects;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

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
    private List<String> reservedRoomsIds;

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
        this.reservedRoomsIds = new ArrayList<String>();
    }

    public void setReservedRooms(List<String> reservedRoomsIds) {
        this.reservedRoomsIds = reservedRoomsIds;
    }

    public List<String> getReservedRoomsIds() {
        return reservedRoomsIds;
    }

    public void addReservedRoom(String roomId) {
        this.reservedRoomsIds.add(roomId);
    }

    public String getReserviourName() {
        return this.reserviourName;
    }

    public String getReserviourDNI() {
        return reserviourDNI;
    }

    public String getReserviourEmail() {
        return reserviourEmail;
    }

    public String getReserviourPhone() {
        return reserviourPhone;
    }

    public int getNumberOfGuests() {
        return numberOfGuests;
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
        bookingData.put("initialDate", initialDate.toString());
        bookingData.put("finalDate", finalDate.toString());
        bookingData.put("reserviourName", reserviourName);

        JSONArray reservedRoomsIdsObj = new JSONArray();
        for (Object roomId : reservedRoomsIds) {

            reservedRoomsIdsObj.add(roomId.toString());
        }

        bookingData.put("reservedRoomsIds", reservedRoomsIdsObj);

        return new JSONObject(bookingData);
    }
}
