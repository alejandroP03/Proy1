package Model.HotelObjects;

import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;

public class Food implements HotelObject {
    private String name;
    private double price;
    private boolean isRoomService;
    private String availability;

    public Food(String name, double price, boolean isRoomService, String availability) {
        this.name = name;
        this.price = price;
        this.isRoomService = isRoomService;
        this.availability = availability;
    }

    public JSONObject getJsonObject() {
        Map<Object, Object> mapa = new HashMap<Object, Object>();
        mapa.put("name", this.name);
        mapa.put("price", this.price);
        mapa.put("isForGroup", this.isRoomService);
        mapa.put("availability", this.availability);

        return new JSONObject(mapa);
    }

    public String getName() {
        return this.name;
    }

    public double getPrice() {
        return this.price;
    }

    public boolean getIsRoomService() {
        return this.isRoomService;
    }

    public String getAvailability() {
        return this.availability;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setRoomService(boolean isRoomService) {
        this.isRoomService = isRoomService;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

}