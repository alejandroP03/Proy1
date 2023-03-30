package Controller.BillService;

import Model.HotelObjects.Food;
import Model.HotelObjects.HotelService;
import Model.HotelObjects.Registration;
import Model.HotelObjects.Service;
import Model.HotelObjects.RoomRelated.RoomFares;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class StayBillGenerator {
    private Registration registration;
    private ArrayList<Service> consumedServices;
    private ArrayList<Food> consumedFoods;

    public StayBillGenerator(Registration registration){
        this.registration = registration;
        this.consumedFoods = registration.getConsumedFoods();
        this.consumedServices = registration.getConsumedServices();

    }

    // Todo lo que de consumio en el viaje y se cargo a las habitaciones

    public String calculateTotalCost(Map<Object,RoomFares> roomFaresList) throws Exception {

        double roomValues = 0;
        double total = 0;
        String textBill = "Factura total: \n" +
                "Huesped principal: " +
                this.registration.getPrincipalGuest().getName() +
                "DNI: " + this.registration.getPrincipalGuest().getDni();



        for(Map.Entry<Object,RoomFares> entry : roomFaresList.entrySet()){
            RoomFares roomFare = entry.getValue();

            roomValues += roomFare.getFare(this.registration.getInitialDate(),this.registration.getFinalDate());

        }

        textBill += "Valor habitaciones: " + roomValues;

        for(HotelService service : this.consumedServices){
            textBill += service.getName() + ": \n" + "Precio: " + service.getPrice() + "\n" ;
            total += service.getPrice();

        }

        for(HotelService food : this.consumedFoods){
            textBill += food.getName() + ": \n" + "Precio: " +food.getPrice() + "\n" ;
            total += food.getPrice();

        }

        double iva = total*0.19;
        total += iva;
        textBill += "IVA: " + iva + "\n" + "Costo total: " + total;


        return textBill;
    }

    public void showBill(Map<Object,RoomFares> roomFaresList) throws IOException, Exception {
        String billName = this.registration.getPrincipalGuest().getName().replaceAll("\\s","") + "StayBill.txt";
        File file = new File("App/data/bills/"+billName);
        FileWriter fr = new FileWriter(file);
        fr.write(calculateTotalCost(roomFaresList));
        fr.close();

    }


}