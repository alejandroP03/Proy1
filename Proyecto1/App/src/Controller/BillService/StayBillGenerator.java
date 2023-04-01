package Controller.BillService;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import Model.HotelObjects.Food;
import Model.HotelObjects.Registration;
import Model.HotelObjects.Service;
import Model.HotelObjects.RoomRelated.Room;
import Model.HotelObjects.RoomRelated.RoomFares;

public class StayBillGenerator {
    private Registration registration;
    private ArrayList<String> consumedServicesIds;
    private ArrayList<String> consumedFoodsIds;
    private ArrayList<String> roomsIds;
    public StayBillGenerator(Registration registration){
        this.registration = registration;
        this.consumedFoodsIds = registration.getConsumedFoodsIds();
        this.consumedServicesIds = registration.getConsumedServicesIds();
        this.roomsIds = registration.getRegisterRoomsIds();

    }

    // Todo lo que de consumio en el viaje y se cargo a las habitaciones

    public String calculateTotalCost(Map<Object,RoomFares> roomFaresList, Map<Object, Service> serviceList, Map<Object, Food> foodList, Map<Object, Room> roomsList) throws Exception {

        double roomValues = 0;
        double total = 0;
        String textBill = "Factura total: \n" +
                "Huesped principal: " +
                this.registration.getPrincipalGuest().getName() + "\n" +
                "DNI: " + this.registration.getPrincipalGuest().getDni();
        
        for(String roomId : this.roomsIds){
            Room room = roomsList.get(roomId);
            RoomFares roomFares = roomFaresList.get(room.createTypeRoomId());
            if(roomFares == null)
                throw new Exception("No se encontraron tarifas para la habitacion " + room.getRoomId());
            roomValues += roomFares.getFare(registration.getInitialDate(), registration.getFinalDate());
        }
        

        

        textBill += "Valor habitaciones: " + roomValues;
        if(this.consumedServicesIds.size() > 0){
            for(String serviceId : this.consumedServicesIds){
                Service service = serviceList.get(serviceId);
                textBill += service.getName() + ": \n" + "Precio: " + service.getPrice() + "\n" ;
                total += service.getPrice();

            }

        }

        if(this.consumedFoodsIds.size() > 0){
            
            for(String foodId : this.consumedFoodsIds){
                Food food = foodList.get(foodId);
                textBill += food.getName() + ": \n" + "Precio: " +food.getPrice() + "\n" ;
                total += food.getPrice();

            }
        }


        double iva = total*0.19;
        total += iva;
        textBill += "IVA: " + iva + "\n" + "Costo total: " + total;


        return textBill;
    }

    public void showBill(Map<Object,RoomFares> roomFaresList, Map<Object, Service> serviceList, Map<Object, Food> foodList, Map<Object, Room> roomList) throws IOException, Exception {
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
        fw.write(previousBills + calculateTotalCost(roomFaresList, serviceList, foodList, roomList));
        fw.close();
        br.close();

    }


}