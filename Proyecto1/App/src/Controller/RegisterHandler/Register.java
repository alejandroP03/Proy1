package Controller.RegisterHandler;

import Model.BillService.ConsumeRecorder;
import Model.HotelObjects.HotelObject;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Register implements HotelObject {
    //attributes
    private int id;

    private Group group;
    private PrincipalGuest responsibleGuest;
    private RegisterHandler assignedRooms;
    private ArrayList<ConsumeRecorder> consumedServicesList;
    private ArrayList<ConsumeRecorder> consumedFoodsList;

    //Constructor

    //methods
    @Override
    public JSONObject getJsonObject() {
        return null;
    }



}
