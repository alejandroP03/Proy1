package Controller;

import java.util.Map;

import Model.HotelObjects.HotelObject;

public class Hotel {
    private Map<String, HotelObject> roomFares;

    public Map<String, HotelObject> getRoomFares() {
        return roomFares;
    }
}
