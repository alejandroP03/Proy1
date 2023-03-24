package Model.HotelObjects.RoomRelated;

import java.time.LocalDate;
import java.util.ArrayList;

import org.json.simple.JSONObject;

import Model.HotelObjects.HotelObject;

public class Room implements HotelObject {
    private String roomId;
    private String location;
    private int capacity = 0;
    private boolean isOccupied;
    private ArrayList<LocalDate> bookedDates;
    private ArrayList<Bed> beds;
    private ArrayList<RoomFeatures> featuresList;
    private TypeRoom type;
    private ArrayList<RoomFare> roomFares;

    public Room(String roomId,
            String location,
            boolean isOccupied,
            ArrayList<Bed> beds,
            ArrayList<RoomFeatures> featuresList,
            TypeRoom type) {
        this.roomId = roomId;
        this.location = location;
        this.isOccupied = isOccupied;
        this.bookedDates = new ArrayList<LocalDate>();
        this.beds = beds;
        this.featuresList = featuresList;
        this.type = type;
        this.roomFares = new ArrayList<RoomFare>();
    }

    public void setBookedDates(ArrayList<LocalDate> bookedDates) {
        this.bookedDates = bookedDates;
    }

    public void setRoomFares(ArrayList<RoomFare> roomFares) {
        this.roomFares = roomFares;
    }

    public ArrayList<Bed> getBeds() {
        return beds;
    }

    public ArrayList<LocalDate> getBookedDates() {
        return bookedDates;
    }

    public int getCapacity() {
        for (Bed bed : beds) {
            capacity += bed.getBedSize();
        }

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

    public String createTypeRoomId(){
        String typeRoomId = type.toString();
        for (Bed bed : beds) {
            typeRoomId += '_' + bed.toString();
        }

        for (RoomFeatures features : featuresList) {
            typeRoomId += '_' + features.toString();
        }

        return typeRoomId;
    }

    public JSONObject getJsonObject() {
        return null;
    }
}