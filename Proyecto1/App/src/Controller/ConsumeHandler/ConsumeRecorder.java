package Controller.ConsumeHandler;

import Model.HotelObjects.HotelService;
import Controller.RegisterHandler.Guest;

import java.util.ArrayList;
import java.util.Map;

public class ConsumeRecorder <T extends HotelService>{

    /*
     * Añade a la lista de servicios consumidos de un registro el registro que se va a pagar cuando se realice el check out
     * <b>pre: El registro debe existir. </b> <br>
     * <b>pos: </b> Se añade un servicio a la lista de servicios consumidos de un registro.
     *
     */
    public void registerConsumption(ArrayList<String> servicesConsumed, T service){
        servicesConsumed.add(service.getId());
    }

    public String handleInstantPay(ArrayList<String> consumedServicesIds, Guest guest, Map<Object, T> listServices){

        double total = 0;

        String textBill = "Hotel\n" +
                "Huésped: " + guest.getName() +"\n"
                + "DNI: " + guest.getDni() + "\n";
        for(String serviceId : consumedServicesIds){
            textBill += listServices.get(serviceId).getName() + ": " + listServices.get(serviceId).getPrice() + "\n" ;
            total += listServices.get(serviceId).getPrice();

        }

        double iva = total*0.19;
        textBill += "Precio neto: " + total + "\n" + "IVA: " + iva + "\n" +"Precio total: " + (total+iva);


        return textBill;



    }

}
