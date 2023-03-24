package Model.HotelObjects.RoomRelated;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.json.simple.JSONObject;

import Model.HotelObjects.HotelObject;

public class Room implements HotelObject {
    private String roomId;
    private String location;
    private int capacity = 0;
    private boolean isOccupied;
    private Map<LocalDate, LocalDate> bookedDates;
    private ArrayList<Bed> beds;
    private ArrayList<RoomFeatures> featuresList;
    private TypeRoom type;
    private ArrayList<Fare> roomFares;

    public Room(String roomId,
            String location,
            boolean isOccupied,
            ArrayList<Bed> beds,
            ArrayList<RoomFeatures> featuresList,
            TypeRoom type) {
        this.roomId = roomId;
        this.location = location;
        this.isOccupied = isOccupied;
        this.bookedDates = new HashMap<LocalDate, LocalDate>();
        this.beds = beds;
        this.featuresList = featuresList;
        this.type = type;
        this.roomFares = new ArrayList<Fare>();
    }

    public void setBookedDates(HashMap<LocalDate, LocalDate> bookedDates) {
        this.bookedDates = bookedDates;
    }



    public void setRoomFares(ArrayList<Fare> roomFares) {
        this.roomFares = roomFares;
    }

    public ArrayList<Bed> getBeds() {
        return beds;
    }

    public Map<LocalDate, LocalDate> getBookedDates() {
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

    public ArrayList<Fare> getRoomFares() {
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

    public Set<Object> createTypeRoomId(){
        Set<Object> roomId = new HashSet<Object>();


        roomId.add(this.type); 
        for (Bed bed : beds) {
            roomId.add(bed);
        }

        for (RoomFeatures features : featuresList) {
            roomId.add(features);
        }

        return roomId;
    }

    public JSONObject getJsonObject() {
        return null;
    }
}