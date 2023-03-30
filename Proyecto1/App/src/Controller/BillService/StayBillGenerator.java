package Controller.BillService;

import Model.HotelObjects.Food;
import Model.HotelObjects.HotelService;
import Model.HotelObjects.Registration;
import Model.HotelObjects.Service;
import Model.HotelObjects.RoomRelated.RoomFares;

import java.util.ArrayList;
import java.util.Map;

public class StayBillGenerator implements BillGenerator {
    private Registration registration;
    private ArrayList<Service> consumedServices;
    private ArrayList<Food> consumedFoods;

    public StayBillGenerator(Registration registration){
        this.registration = registration;
        this.consumedFoods = registration.getConsumedFoods();
        this.consumedServices = registration.getConsumedServices();

    }

    // Todo lo que de consumio en el viaje y se cargo a las habitaciones

    public float calculateTotalCost(Map<Object,RoomFares> roomFaresList) {
        double roomValues = 0;

        for(Map.Entry<Object,RoomFares> entry : roomFaresList.entrySet()){
            RoomFares roomFare = entry.getValue();

            /*\\roomValues += roomFare.getFare(this.registration.getInitialDate(),this.registration.getFinalDate)
            */



        }

        for(HotelService service : this.consumedServices){
            //textBill += service.getName() + ": " + service.getPrice() + "\n" ;
            //total += service.getPrice();

        }

        return 0;
    }

    @Override
    public void showBill() {

    }


}