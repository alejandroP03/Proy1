package Model.HotelObjects.RoomRelated;

import java.util.ArrayList;

import org.json.simple.JSONObject;

import Model.HotelObjects.HotelObject;

public class RoomFares implements HotelObject {
    private ArrayList<Fare> FaresForRoomType;

    public ArrayList<Fare> getFaresForRoomType() {
        return FaresForRoomType;
    }

    public JSONObject getJsonObject() {
        return null;
    }
}