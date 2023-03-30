package Controller.RegisterHandler;

import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;

public class CompanionGuest implements Guest {
    // attributes
    private String name;
    private String dni;

    // Constructor
    public CompanionGuest(String name, String dni) {
        this.name = name;
        this.dni = dni;

    }

    // methods

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getDni() {
        return this.dni;
    }

    @Override
    public JSONObject getJsonObject() {
        Map<String, Object> companionGuest = new HashMap<String, Object>();
        companionGuest.put("name", this.name);
        companionGuest.put("dni", this.dni);
        JSONObject companionGuestJson = new JSONObject(companionGuest);
        return companionGuestJson;
    }

}
