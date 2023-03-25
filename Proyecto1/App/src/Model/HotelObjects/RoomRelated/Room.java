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
    private Map<Bed, Integer> beds;
    private Set<RoomFeatures> featuresList;
    private TypeRoom type;
    private ArrayList<Fare> roomFares;

    public Room(String roomId,
            String location,
            boolean isOccupied,
            Map<Bed, Integer> beds,
            Set<RoomFeatures> featuresList,
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

    public Map<Bed, Integer> getBeds() {
        return beds;
    }

    public Map<LocalDate, LocalDate> getBookedDates() {
        return bookedDates;
    }

    public int getCapacity() {
        for (Map.Entry<Bed, Integer> bedEntry : beds.entrySet()) {
            capacity += bedEntry.getValue() * bedEntry.getKey().getBedSize();
        }

        return capacity;
    }

    public Set<RoomFeatures> getFeaturesList() {
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

    public Set<Object> createTypeRoomId() {
        Set<Object> roomId = new HashSet<Object>();
        roomId.add(this.type);

        for (Map.Entry<Bed, Integer> bedEntry : beds.entrySet()) {
            roomId.add(new HashMap<Bed, Integer>(Map.of(bedEntry.getKey(), bedEntry.getValue())));
        }

        for (RoomFeatures features : featuresList) {
            roomId.add(features);
        }

        return roomId;
    }

    public JSONObject getJsonObject() {
        Map<Object, Object> roomData = new HashMap<Object, Object>();
        roomData.put("roomId", this.getRoomId());
        roomData.put("location", this.getLocation());
        roomData.put("capacity", this.getCapacity());
        roomData.put("isOccupied", this.getIsOcupied());
        roomData.put("bookedDates", this.getBookedDates());
        roomData.put("beds", this.getBeds());

        ArrayList<String> featuresListArray = new ArrayList<String>();
        for (RoomFeatures featrue : this.getFeaturesList()) {
            featuresListArray.add(featrue.toString());
        }

        roomData.put("featuresList", featuresListArray);

        JSONObject roomObject = new JSONObject(roomData);
        return roomObject;
    }
}