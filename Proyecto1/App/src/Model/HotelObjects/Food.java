package Model.HotelObjects;

import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;

public class Food implements HotelService {
    private String id;
    private String name;
    private double price;
    private boolean isRoomService;
    private String availability;

    public Food(String id, String name, double price, boolean isRoomService, String availability) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.isRoomService = isRoomService;
        this.availability = availability;
    }

    public JSONObject getJsonObject() {
        Map<Object, Object> mapa = new HashMap<Object, Object>();
        mapa.put("id", this.id);
        mapa.put("name", this.name);
        mapa.put("price", this.price);
        mapa.put("isRoomService", this.isRoomService);
        mapa.put("availability", this.availability);

        return new JSONObject(mapa);
    }

    public String getId(){
        return this.id;
    }
    @Override
    public String getName() {
        return this.name;
    }
    @Override
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