package Model.HotelObjects.RoomRelated;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;

import org.json.simple.JSONObject;

import Model.HotelObjects.HotelObject;

public class Fare implements HotelObject {
    private double price;
    private LocalDate initialDate;
    private LocalDate finalDate;
    private ArrayList<DayOfWeek> days;

    Fare(double price,
            LocalDate initialDate,
            LocalDate finalDate,
            ArrayList<DayOfWeek> days) {
        this.price = price;
        this.initialDate = initialDate;
        this.finalDate = finalDate;
        this.days = days;
    }

    public double getPrice() {
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
        return null;
    }
}
