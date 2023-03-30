package Controller.BillService;

import Model.HotelObjects.Food;
import Model.HotelObjects.Registration;
import Model.HotelObjects.Service;

import java.util.Map;

public class StayBillGenerator implements BillGenerator {
    private Registration registration;
    private Map<String, Service> consumedServices;
    private Map<String, Food> consumedFoods;

    public StayBillGenerator(Registration registration){
        this.registration = registration;
        this.consumedFoods = registration.getConsumedFoods();
        this.consumedServices = registration.getConsumedServices();

    }

    // Todo lo que de consumio en el viaje y se cargo a las habitaciones

    public float calculateTotalCost() {
        return 0;
    }

    @Override
    public void showBill() {

    }


}