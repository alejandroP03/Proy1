package Controller.RegisterHandler;

import org.json.simple.JSONObject;

import Model.HotelObjects.HotelObject;

public interface Guest extends HotelObject{

    public String getName();

    public String getDni();

    public JSONObject getJsonObject();
}
