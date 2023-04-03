package Controller.BillService;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import Controller.RegisterHandler.Guest;

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
