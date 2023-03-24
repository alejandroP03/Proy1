package Model.HotelObjects;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Service implements HotelObject {

    private String name;
    private double price;
    private boolean isForGroup;
    private ArrayList<DayOfWeek> daysAvailable;
    private LocalTime initialTime;
    private LocalTime finalTime;




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
        mapa.put("initialTime",this.initialTime.toString());
        mapa.put("finalTime",this.finalTime.toString());

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

    public LocalTime getInitialTime() {
        return this.initialTime;
    }

    public LocalTime getFinalTime() {
        return this.finalTime;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setPrice(double price){
        this.price = price;
    }

    public void setIsForGroup(boolean isForGroup){
        this.isForGroup = isForGroup;
    }

    public void setDaysAvailable(ArrayList<DayOfWeek> daysAvailable) {
        this.daysAvailable = daysAvailable;
    }

    public void setInitialTime(LocalTime initialTime) {
        this.initialTime = initialTime;
    }

    public void setFinalTime(LocalTime finalTime) {
        this.finalTime = finalTime;
    }
}