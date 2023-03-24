package Controller.RegisterHandler;

import java.util.ArrayList;

import org.json.simple.JSONObject;

import Controller.BillService.ConsumeRecorder;
import Model.HotelObjects.HotelObject;

public class Register implements HotelObject {
    // attributes
    private int id;

    private RegisterHandler assignedRooms;
    private ArrayList<ConsumeRecorder> consumedServicesList;
    private ArrayList<ConsumeRecorder> consumedFoodsList;

    // Constructor

    // methods
    @Override
    public JSONObject getJsonObject() {
        return null;
    }

}
