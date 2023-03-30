package Model.HotelObjects;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;

public class Service implements HotelObject {
    private String id;
    private String name;
    private double price;
    private boolean isForGroup;
    private ArrayList<DayOfWeek> daysAvailable;
    private LocalDate initialTime;
    private LocalDate finalTime;

    public Service(String id, String name, double price, boolean isForGroup, ArrayList<DayOfWeek> daysAvailable,
            LocalDate initialTime, LocalDate finalTime) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.isForGroup = isForGroup;
        this.daysAvailable = daysAvailable;
        this.initialTime = initialTime;
        this.finalTime = finalTime;
    }

    public JSONObject getJsonObject() {
        Map<Object, Object> mapa = new HashMap<Object, Object>();
        mapa.put("id", this.id);
        mapa.put("name", this.name);
        mapa.put("price", this.price);
        mapa.put("isForGroup", this.isForGroup);
        mapa.put("initialTime", this.initialTime.toString());
        mapa.put("finalTime", this.finalTime.toString());
        ArrayList<String> days = new ArrayList<String>();

        for (DayOfWeek day : this.daysAvailable) {
            days.add(day.toString());
        }

        mapa.put("daysAvailable", days);

        return new JSONObject(mapa);
    }

    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public double getPrice() {
        return this.price;
    }

    public boolean getIsForGroup() {
        return this.isForGroup;
    }

    public ArrayList<DayOfWeek> getDaysAvailable() {
        return this.daysAvailable;
    }

    public LocalDate getInitialTime() {
        return this.initialTime;
    }

    public LocalDate getFinalTime() {
        return this.finalTime;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setIsForGroup(boolean isForGroup) {
        this.isForGroup = isForGroup;
    }

    public void setDaysAvailable(ArrayList<DayOfWeek> daysAvailable) {
        this.daysAvailable = daysAvailable;
    }

    public void setInitialTime(LocalDate initialTime) {
        this.initialTime = initialTime;
    }

    public void setFinalTime(LocalDate finalTime) {
        this.finalTime = finalTime;
    }
}