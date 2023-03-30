package Controller.ConsumeHandler;

import Model.HotelObjects.HotelService;
import Controller.RegisterHandler.Guest;

import java.util.ArrayList;

public class ConsumeRecorder {
    public void registerConsumption(ArrayList<HotelService> servicesConsumed, HotelService service){
        servicesConsumed.add(service);
    }
    public String handleInstantPay(ArrayList<HotelService> services, Guest guest){



        /*double iva = service.getPrice()*0.19;
        String textBill = "Hotel\n" +
                            "Cliente: " + guest.getName() +"\n"
                            + "DNI: " + guest.getDni() + "\n"
                            + "\n" + "Servicio: " + service.getName() + "\n"
                            + "Precio: " + service.getPrice() + "\n"
                            + "IVA: " + iva + "\n"
                            + "Total: " + service.getPrice() + iva;
*/
        return null;



    }

}
