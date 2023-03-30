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
    private ArrayList<Service> consumedServices;
    private ArrayList<Food> consumedFoods;


    public Registration(PrincipalGuest principalGuest, ArrayList<CompanionGuest> groupOfGuests,
            List<String> registerRoomsIds) {

        this.groupOfGuests = groupOfGuests;
        this.principalGuest = principalGuest;
        this.registerRoomsIds = registerRoomsIds;
        this.consumedServices = new ArrayList<>();
        this.consumedFoods = new ArrayList<>();

    }

    public void addConsumedService(Service newService) {
        consumedServices.add(newService);
    }

    public void addConsumedFood(Food newFood) {
        consumedFoods.add(newFood);
    }

    public PrincipalGuest getPrincipalGuest() {
        return this.principalGuest;
    }

    public ArrayList<Service> getConsumedServices(){
        return this.consumedServices;
    }

    public ArrayList<Food> getConsumedFoods(){
        return this.consumedFoods;
    }

    public List<String> getregisterRoomsIds

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
        for (Food food : this.consumedFoods) {
            foodIds.add(food.getId());
        }

        objMap.put("consumedFoods", foodIds);

        @SuppressWarnings("unchecked")
        ArrayList<String> serviceIds = new JSONArray();
        for (Service service : this.consumedServices) {
            serviceIds.add(service.getId());
        }

        objMap.put("consumedServices", serviceIds);

        return new JSONObject(objMap);
    }
}