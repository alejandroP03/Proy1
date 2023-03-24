package Model.HotelObjects;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Service implements HotelObject {

    private String name;
    private double price;
    private boolean isForGroup;
    private ArrayList<DayOfWeek> daysAvailable;


    public Service(String name, double price, boolean isForGroup, ArrayList<DayOfWeek> daysAvailable) {
        this.name = name;
        this.price = price;
        this.isForGroup = isForGroup;
        this.daysAvailable = daysAvailable;
    }

    public JSONObject getJsonObject() {
        Map<Object, Object> mapa = new HashMap<Object, Object>();
        mapa.put("name",this.name);
        mapa.put("price",this.price);
        mapa.put("isForGroup",this.isForGroup);

        ArrayList<String> days = new ArrayList<String>();

        for (DayOfWeek day: this.daysAvailable){
            days.add(day.toString());
        }
        mapa.put("daysAvailable",days);


        return new JSONObject(mapa);
    }




    public String getName() {
        return this.name;
    }

    public double getPrice() {
        return this.price;
    }

    public boolean getIsForGroup(){
        return this.isForGroup;
    }

    public ArrayList<DayOfWeek> getDaysAvailable() {
        return this.daysAvailable;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setPrice(double price){
        this.price = price;
    }

    public void setIsForGroup(boolean group){
        this.isForGroup = isForGroup;
    }

    public void setDaysAvailable(ArrayList<DayOfWeek> daysAvailable) {
        this.daysAvailable = daysAvailable;
    }




}