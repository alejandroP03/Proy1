package Model.HotelObjects.RoomRelated;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import Model.HotelObjects.HotelObject;

public class Fare implements HotelObject {
    private float price;
    private LocalDate initialDate;
    private LocalDate finalDate;
    private ArrayList<DayOfWeek> days;

    public Fare(float price,
            LocalDate initialDate,
            LocalDate finalDate,
            ArrayList<DayOfWeek> days) {
        this.price = price;
        this.initialDate = initialDate;
        this.finalDate = finalDate;
        this.days = days;
    }

    public float getPrice() {
        return price;
    }

    public LocalDate getInitialDate() {
        return initialDate;
    }

    public LocalDate getFinalDate() {
        return finalDate;
    }

    public ArrayList<DayOfWeek> getDays() {
        return days;
    }

    public JSONObject getJsonObject() {
        Map<Object, Object> fareData = new HashMap<Object, Object>();
        fareData.put("price", this.getPrice());
        fareData.put("initialDate", this.getInitialDate().toString());
        fareData.put("finalDate", this.getFinalDate().toString());

        JSONArray daysJsonArray = new JSONArray();
        for (DayOfWeek dayOfWeek : this.getDays()) {
            daysJsonArray.add(dayOfWeek.toString());
        }

        fareData.put("days", daysJsonArray);

        JSONObject fareObject = new JSONObject(fareData);
        return fareObject;
    }


}