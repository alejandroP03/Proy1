package Controller.BillService;

import Controller.RegisterHandler.Guest;
import Model.HotelObjects.HotelService;


import java.lang.reflect.Array;
import java.util.ArrayList;

public class ServicesBillGenerator implements BillGenerator {
    ArrayList<HotelService> services;
    Guest consumer;
    String textBill;

    public ServicesBillGenerator(ArrayList<HotelService> services, Guest consumer, String textBill){
        this.services = services;
        this.consumer = consumer;
        this.textBill = textBill;

    }

    @Override
    public float calculeTotalCost() {
        return 0;
    }

    @Override
    public String showBill() {
        return null;
    }

}
