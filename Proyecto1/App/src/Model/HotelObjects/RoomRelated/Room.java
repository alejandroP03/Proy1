package Model.HotelObjects.RoomRelated;

import java.time.LocalDate;
import java.util.ArrayList;

import org.json.simple.JSONObject;

import Model.HotelObjects.HotelObject;

public class Room implements HotelObject {
    private String roomId;
    private String location;
    private int capacity;
    private boolean isOccupied;
    private ArrayList<LocalDate> bookedDates;
    private ArrayList<Bed> beds;
    private ArrayList<RoomFeatures> featuresList;
    private TypeRoom type;
    private ArrayList<RoomFare> roomFares;

    public Room(String roomId,
            String location,
            int capacity,
            boolean isOccupied,
            ArrayList<Bed> beds,
            ArrayList<RoomFeatures> featuresList,
            TypeRoom type) {
        this.roomId = roomId;
        this.location = location;
        this.capacity = capacity;
        this.isOccupied = isOccupied;
        this.bookedDates = new ArrayList<LocalDate>();
        this.beds = beds;
        this.featuresList = featuresList;
        this.type = type;
        this.roomFares = new ArrayList<RoomFare>();
    }

    public ArrayList<Bed> getBeds() {
        return beds;
    }

    public ArrayList<LocalDate> getBookedDates() {
        return bookedDates;
    }

    public int getCapacity() {
        return capacity;
    }

    public ArrayList<RoomFeatures> getFeaturesList() {
        return featuresList;
    }

    public String getLocation() {
        return location;
    }

    public ArrayList<RoomFare> getRoomFares() {
        return roomFares;
    }

    public String getRoomId() {
        return roomId;
    }

    public TypeRoom getType() {
        return type;
    }

    public boolean getIsOcupied() {
        return isOccupied;
    }

    public JSONObject getJsonObject() {
        return null;
    }
}