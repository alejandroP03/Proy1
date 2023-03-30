package Controller.BillService;

import Controller.RegisterHandler.Guest;
import Model.HotelObjects.HotelService;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class ServicesBillGenerator {

    private String textBill;
    private Guest guest;

    public ServicesBillGenerator(String textBill, Guest guest){

        this.textBill = textBill;
        this.guest = guest;

    }



    public void showBill() throws IOException {

        String billName = this.guest.getName().replaceAll("\\s","") + "Service.txt";
        File file = new File("App/data/bills/"+billName);
        if(!file.exists()){
            file.createNewFile();
        }
        FileWriter fr = new FileWriter(file);
        fr.append(this.textBill);

        fr.close();

    }

}
