package Model.HotelObjects;

import java.time.LocalDate;
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
    private ArrayList<String> consumedServicesIds;
    private ArrayList<String> consumedFoodsIds;
    private LocalDate initialDate;
    private LocalDate finalDate;



    public Registration(PrincipalGuest principalGuest, ArrayList<CompanionGuest> groupOfGuests,
            List<String> registerRoomsIds, LocalDate initialDate, LocalDate finalDate) {

        this.groupOfGuests = groupOfGuests;
        this.principalGuest = principalGuest;
        this.registerRoomsIds = registerRoomsIds;
        this.consumedServicesIds = new ArrayList<String>();
        this.consumedFoodsIds = new ArrayList<String>();
        this.initialDate = initialDate;
        this.finalDate = finalDate;
    }

    public void addConsumedServiceId(String newServiceId) {
        consumedServicesIds.add(newServiceId);
    }
    public void setConsumedServices(ArrayList<String> listServicesIds){this.consumedServicesIds = listServicesIds;}
    public void setConsumedFoods(ArrayList<String> listFoodsIds){this.consumedFoodsIds = listFoodsIds;}

    public void addConsumedFood(String newFoodId) {
        consumedFoodsIds.add(newFoodId);
    }

    public PrincipalGuest getPrincipalGuest() {
        return this.principalGuest;
    }
    public ArrayList<CompanionGuest> getGroupGuest(){
        return this.groupOfGuests;
    }

    public ArrayList<String> getConsumedServicesIds(){
        return this.consumedServicesIds;
    }

    public ArrayList<String> getConsumedFoodsIds(){
        return this.consumedFoodsIds;
    }

    public List<String> getRegisterRoomsIds(){
        return this.registerRoomsIds;
    }

    public  LocalDate getInitialDate(){return  this.initialDate;}
    public  LocalDate getFinalDate(){return  this.finalDate;}

    public JSONObject getJsonObject() {
        Map<String, Object> objMap = new HashMap<String, Object>();
        objMap.put("principalGuest", this.principalGuest.getJsonObject());

        objMap.put("initialDate", this.initialDate.toString());
        objMap.put("finalDate", this.finalDate.toString());

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

        objMap.put("registerRoomsIds", roomsIds);

        @SuppressWarnings("unchecked")
        ArrayList<String> foodIds = new JSONArray();
        for (String foodId : this.consumedFoodsIds) {
            foodIds.add(foodId);
        }

        objMap.put("consumedFoodsIds", foodIds);

        @SuppressWarnings("unchecked")
        ArrayList<String> serviceIds = new JSONArray();
        for (String serviceId : this.consumedServicesIds) {
            serviceIds.add(serviceId);
        }

        objMap.put("consumedServicesIds", serviceIds);

        return new JSONObject(objMap);
    }
}