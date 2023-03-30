package Controller.BillService;

import Model.HotelObjects.Food;
import Model.HotelObjects.HotelService;
import Model.HotelObjects.Registration;
import Model.HotelObjects.Service;
import Model.HotelObjects.RoomRelated.RoomFares;

import java.io.*;
import java.util.ArrayList;
import java.util.Map;

public class StayBillGenerator {
    private Registration registration;
    private ArrayList<String> consumedServicesIds;
    private ArrayList<String> consumedFoodsIds;

    public StayBillGenerator(Registration registration){
        this.registration = registration;
        this.consumedFoodsIds = registration.getConsumedFoodsIds();
        this.consumedServicesIds = registration.getConsumedServicesIds();

    }

    // Todo lo que de consumio en el viaje y se cargo a las habitaciones

    public String calculateTotalCost(Map<Object,RoomFares> roomFaresList, Map<Object, Service> serviceList, Map<Object, Food> foodList) throws Exception {

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

        for(String serviceId : this.consumedServicesIds){
            Service service = serviceList.get(serviceId);
            textBill += service.getName() + ": \n" + "Precio: " + service.getPrice() + "\n" ;
            total += service.getPrice();

        }

        for(String foodId : this.consumedFoodsIds){
            Food food = foodList.get(foodId);
            textBill += food.getName() + ": \n" + "Precio: " +food.getPrice() + "\n" ;
            total += food.getPrice();

        }

        double iva = total*0.19;
        total += iva;
        textBill += "IVA: " + iva + "\n" + "Costo total: " + total;


        return textBill;
    }

    public void showBill(Map<Object,RoomFares> roomFaresList, Map<Object, Service> serviceList, Map<Object, Food> foodList) throws IOException, Exception {
        String billName = this.registration.getPrincipalGuest().getName().replaceAll("\\s","") + "StayBill.txt";
        File file = new File("App/data/bills/"+billName);

        String previousBills = "";

        BufferedReader br = new BufferedReader(new FileReader(file));

        String line = br.readLine();
        while(line != null){
            previousBills += line;
            line = br.readLine();
        }

        FileWriter fw = new FileWriter(file);
        fw.write(previousBills + calculateTotalCost(roomFaresList, serviceList, foodList));
        fw.close();
        br.close();

    }


}