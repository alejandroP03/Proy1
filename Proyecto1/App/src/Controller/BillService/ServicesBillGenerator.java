package Controller.BillService;

import Controller.RegisterHandler.Guest;
import Model.HotelObjects.HotelService;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class ServicesBillGenerator implements BillGenerator {

    private String textBill;
    private Guest guest;

    public ServicesBillGenerator(String textBill, Guest guest){

        this.textBill = textBill;
        this.guest = guest;

    }




    @Override
    public void showBill() throws IOException {

        String billName = this.guest.getName().replaceAll("\\s","") + "Service.txt";
        File file = new File("App/data/bills/"+billName);
        FileWriter fr = new FileWriter(file);
        fr.write(this.textBill);
        fr.close();


    }

}
