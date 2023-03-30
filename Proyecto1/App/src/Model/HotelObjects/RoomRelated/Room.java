package Model.HotelObjects.RoomRelated;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.json.simple.JSONObject;

import Model.HotelObjects.HotelObject;

public class Room extends RoomModel implements HotelObject {
    private String roomId;
    private String location;
    private int capacity = 0;
    private boolean isOccupied;
    private Map<LocalDate, LocalDate> bookedDates;

    public Room(String roomId,
            String location,
            boolean isOccupied,
            Map<Bed, Integer> beds,
            Set<RoomFeatures> featuresList,
            TypeRoom type) {
        super(type, beds, featuresList);
        this.roomId = roomId;
        this.location = location;
        this.isOccupied = isOccupied;
        this.bookedDates = new HashMap<LocalDate, LocalDate>();
    }

    public void setBookedDates(Map<LocalDate, LocalDate> bookedDates) {
        this.bookedDates = bookedDates;
    }

    public void addBookedDate(LocalDate initialDate, LocalDate finalDate) {
        this.bookedDates.put(initialDate, finalDate);
    }

    public Map<Bed, Integer> getBeds() {
        return super.beds;
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
        return super.featuresList;
    }

    public String getLocation() {
        return location;
    }

    public String getRoomId() {
        return roomId;
    }

    public TypeRoom getType() {
        return super.type;
    }

    public boolean getIsOcupied() {
        return isOccupied;
    }
    public void setOccupied(boolean isOccupied){
        this.isOccupied = isOccupied;
    }

    public JSONObject getJsonObject() {
        Map<Object, Object> roomData = new HashMap<Object, Object>();
        roomData.put("roomId", this.getRoomId());
        roomData.put("location", this.getLocation());
        roomData.put("capacity", this.getCapacity());
        roomData.put("isOccupied", this.getIsOcupied());
        roomData.put("bookedDates", this.getBookedDates());
        roomData.put("beds", this.getBeds());
        roomData.put("type", this.getType().toString());

        ArrayList<String> featuresListArray = new ArrayList<String>();
        for (RoomFeatures feature : this.getFeaturesList()) {
            featuresListArray.add(feature.toString());
        }

        roomData.put("featuresList", featuresListArray);

        JSONObject roomObject = new JSONObject(roomData);
        return roomObject;
    }

}



