package Model.HotelObjects;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import Controller.RegisterHandler.Group;
import Controller.RegisterHandler.PrincipalGuest;
import Model.HotelObjects.RoomRelated.Room;

public class Registration implements HotelObject {
    private PrincipalGuest principalGuest;
    private Group groupOfGuests;
    private Map<String, Room> registerRooms;
    private Map<String, Service> consumedServices;
    private Map<String, Food> consumedFoods;

    public Registration(PrincipalGuest principalGuest, Group groupOfGuests, Map<String, Room> registerRooms) {
        this.groupOfGuests = groupOfGuests;
        this.principalGuest = principalGuest;
        this.registerRooms = registerRooms;
        this.consumedServices = new HashMap<String, Service>();
        this.consumedFoods = new HashMap<String, Food>();

    }

    public void addConsumedService(Service newService) {
        consumedServices.put(newService.getId(), newService);
    }

    public void addConsumedFood(Food newFood) {
        consumedFoods.put(newFood.getId(), newFood);
    }

    public JSONObject getJsonObject() {
        Map<String, Object> objMap = new HashMap<String, Object>();
        objMap.put("principalGuest", this.principalGuest.getJsonObject());
        objMap.put("groupOfGuests", this.groupOfGuests.getJsonObject());

        @SuppressWarnings("unchecked")
        ArrayList<String> roomsIds = new JSONArray();
        for (Room room : this.registerRooms.values()) {
            roomsIds.add(room.getRoomId());
        }

        objMap.put("registerRooms", roomsIds);

        @SuppressWarnings("unchecked")
        ArrayList<String> foodIds = new JSONArray();
        for (Food food : this.consumedFoods.values()) {
            foodIds.add(food.getId());
        }

        objMap.put("consumedFoods", foodIds);

        @SuppressWarnings("unchecked")
        ArrayList<String> serviceIds = new JSONArray();
        for (Service service : this.consumedServices.values()) {
            serviceIds.add(service.getId());
        }

        objMap.put("consumedServices", serviceIds);


        return new JSONObject(objMap);
    }
}