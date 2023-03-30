package Controller.ConsumeHandler;

import Model.HotelObjects.HotelService;
import Controller.RegisterHandler.Guest;

import java.util.ArrayList;

public class ConsumeRecorder {
    public void registerConsumption(ArrayList<HotelService> servicesConsumed, HotelService service){
        servicesConsumed.add(service);
    }
    public String handleInstantPay(ArrayList<HotelService> consumedServices, Guest guest){

        double total = 0;

        String textBill = "Hotel\n" +
                "Huésped: " + guest.getName() +"\n"
                + "DNI: " + guest.getDni() + "\n";
        for(HotelService service : consumedServices){
            textBill += service.getName() + ": " + service.getPrice() + "\n" ;
            total += service.getPrice();

        }

        double iva = total*0.19;
        textBill += "Precio neto: " + total + "\n" + "IVA: " + iva + "\n" +"Precio total: " + total+iva;


        return textBill;



    }

}
