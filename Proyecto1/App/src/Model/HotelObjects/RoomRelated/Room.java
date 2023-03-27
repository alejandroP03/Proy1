package Model.HotelObjects.RoomRelated;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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



        Map<Bed, Integer> bedComposition = new HashMap<Bed, Integer>();
        for (Map.Entry<Bed, Integer> bedEntry : beds.entrySet()) {
            bedComposition.put(bedEntry.getKey(), bedEntry.getValue());
        }

        roomId.add(bedComposition);

        for (RoomFeatures features : featuresList) {
            roomId.add(features);
        }

        return roomId;
    }

}