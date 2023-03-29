package Controller.RegisterHandler;

import java.util.ArrayList;

import org.json.simple.JSONObject;

import Model.HotelObjects.HotelObject;

public class Group implements HotelObject{
    // attributes
    private ArrayList<CompanionGuest> groupGuests;
    private CompanionGuest companionGuest;

    // Constructor
    public Group(ArrayList<CompanionGuest> groupGuests) {
        this.groupGuests = groupGuests;
    }

    // methods
    public void addCompanionGuest(String name, String dni) {
        companionGuest = new CompanionGuest(name, dni);
        this.groupGuests.add(companionGuest);

    }

    @Override
    public JSONObject getJsonObject() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getJsonObject'");
    }

}
