package Model.HotelObjects;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import Controller.RegisterHandler.CompanionGuest;
import Controller.RegisterHandler.PrincipalGuest;

public class Registration implements HotelObject {
    private PrincipalGuest principalGuest;
    private ArrayList<CompanionGuest> groupOfGuests;
    private List<String> registerRoomsIds;
    private Map<String, Service> consumedServices;
    private Map<String, Food> consumedFoods;

    public Registration(PrincipalGuest principalGuest, ArrayList<CompanionGuest> groupOfGuests,
            List<String> registerRoomsIds) {
        this.groupOfGuests = groupOfGuests;
        this.principalGuest = principalGuest;
        this.registerRoomsIds = registerRoomsIds;
        this.consumedServices = new HashMap<String, Service>();
        this.consumedFoods = new HashMap<String, Food>();
    }

    public void addConsumedService(Service newService) {
        consumedServices.put(newService.getId(), newService);
    }

    public void addConsumedFood(Food newFood) {
        consumedFoods.put(newFood.getId(), newFood);
    }

    public PrincipalGuest getPrincipalGuest() {
        return this.principalGuest;
    }

    public JSONObject getJsonObject() {
        Map<String, Object> objMap = new HashMap<String, Object>();
        objMap.put("principalGuest", this.principalGuest.getJsonObject());

        @SuppressWarnings("unchecked")
        ArrayList<JSONObject> groupOfGuests = new JSONArray();
        for (CompanionGuest companionGuest : this.groupOfGuests) {
            groupOfGuests.add(companionGuest.getJsonObject());
        }
        objMap.put("group", groupOfGuests);

        @SuppressWarnings("unchecked")
        ArrayList<String> roomsIds = new JSONArray();
        for (String roomId : this.registerRoomsIds) {
            roomsIds.add(roomId);
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