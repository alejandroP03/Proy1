package View.Screens.EmployeeScreen;

import Controller.BillService.ServicesBillGenerator;
import Controller.ConsumeHandler.ConsumeRecorder;
import Controller.Hotel;
import Controller.RegisterHandler.CompanionGuest;
import Controller.RegisterHandler.Guest;
import Model.HotelObjects.*;
import View.Components.Inputs.InputText;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PayServiceForm extends VBox{

    Hotel hotel = Hotel.getInstance();
    Map<Object, Registration> mapRegisters = hotel.getRegistrationHandler().getData();
    ArrayList<String> addedServices;
    boolean isService;
    ConsumeRecorder<Service> newConsumes = new ConsumeRecorder<Service>();
    ConsumeRecorder<Food> newConsumesFood = new ConsumeRecorder<Food>();


    private BorderPane bigBox = new BorderPane(){
        {

            setId("big-box");

        }
    };

    private VBox inputsContainer = new VBox() {
        {
            setAlignment(Pos.TOP_LEFT);
            setId("input-container");

        }
    };

    private VBox billContainer = new VBox(){
        {
            setAlignment(Pos.TOP_RIGHT);
            setId("bill-info");
        }
    };

    public PayServiceForm(boolean isPrincipal, List<String> addedServices, boolean isService) {
        this.addedServices = (ArrayList<String>) addedServices;
        this.isService = isService;
        getStylesheets().add("View/Styles/employee/payService.css");
        this.switchForm(isPrincipal);
        setAlignment(Pos.CENTER_LEFT);
        setId("pay-service-form");
    }

    protected void switchForm(boolean isPrincipal) {
        getChildren().clear();
        bigBox.getChildren().clear();
        inputsContainer.getChildren().clear();
        billContainer.getChildren().clear();



        InputText dniHPInput = new InputText("DNI  del huésped principal", "Ingrese el DNI del huésped  principal", "", "person");
        inputsContainer.getChildren().add(dniHPInput);
        InputText dniInput = new InputText("DNI", "Ingrese el DNI del huésped acompañante", "", "person");

        Text textBill = new Text();

        final String[] tempBill = { "" };

        if(!isPrincipal){
            inputsContainer.getChildren().add(dniInput);
        }

        Button payBtn = new Button("Enviar -->");
        payBtn.setId("button-form");


        payBtn.setOnMouseClicked(e->{
            String dni = dniInput.getValue();
            String dniHP = dniHPInput.getValue();
            Alert errorAlert = new Alert(Alert.AlertType.INFORMATION);
            errorAlert.setHeaderText("Error");
            errorAlert.setTitle("Error");

            Guest guest;
            Guest prGuest;
            if(isService){
                if(isPrincipal){
                    if(dniHP.isBlank()){
                        errorAlert.setContentText("El DNI ingresado no es valido linea 98");
                        errorAlert.showAndWait();
                    } else {
                        prGuest = searchConsumer(dniHP);
                        if(prGuest == null){
                            errorAlert.setContentText("El DNI no ha sido encontrado linea 105");
                            errorAlert.showAndWait();
                        } else{
                            String newBill = newConsumes.handleInstantPay(this.addedServices,prGuest,hotel.getServices().getData());
                            ServicesBillGenerator newBillPay = new ServicesBillGenerator(newBill, prGuest);
                            tempBill[0] = newBill;
                            textBill.setText(tempBill[0]);
                            textBill.setFont(Font.font("Inter", FontWeight.BLACK, 24));
                            try {
                                newBillPay.showBill();
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }

                        }


                    }
                } else {
                    if(dni.isBlank() || dniHP.isBlank()){
                        errorAlert.setContentText("Alguno de los DNI ingresados no es valido linea 121");
                        errorAlert.showAndWait();

                    } else{
                        prGuest = searchConsumer(dniHP);
                        if(prGuest == null){
                            errorAlert.setContentText("El DNI del huésped principal no ha sido encontrado linea 130");
                            errorAlert.showAndWait();

                        } else {
                            guest = searchConsumer(dni, dniHP);
                            if(guest == null){
                                errorAlert.setContentText("El DNI no ha sido encontrado linea 136");
                                errorAlert.showAndWait();
                            } else{
                                String newBill = newConsumesFood.handleInstantPay(this.addedServices,guest,hotel.getRestaurantHandler().getData());
                                ServicesBillGenerator newBillPay = new ServicesBillGenerator(newBill, guest);
                                tempBill[0] = newBill;
                                textBill.setText(tempBill[0]);
                                textBill.setFont(Font.font("Inter", FontWeight.BLACK, 24));
                                try {
                                    newBillPay.showBill();
                                } catch (IOException ex) {
                                    throw new RuntimeException(ex);
                                }

                            }

                        }


                    }
                }

            } else {
                if(isPrincipal){
                    if(dniHP.isBlank()){
                        errorAlert.setContentText("El DNI ingresado no es valido  linea 160");
                        errorAlert.showAndWait();
                    } else {
                        prGuest = searchConsumer(dniHP);
                        if(prGuest == null){
                            errorAlert.setContentText("El DNI del huésped principal no ha sido encontrado linea 165");
                            errorAlert.showAndWait();
                        } else{
                            String newBill = newConsumesFood.handleInstantPay(this.addedServices,prGuest,hotel.getRestaurantHandler().getData());
                            ServicesBillGenerator newBillPay = new ServicesBillGenerator(newBill, prGuest);
                            tempBill[0] = newBill;
                            textBill.setText(tempBill[0]);
                            textBill.setFont(Font.font("Inter", FontWeight.BLACK, 24));
                            try {
                                newBillPay.showBill();
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }

                        }


                    }
                } else {
                    if(dni.isBlank() || dniHP.isBlank()){
                        errorAlert.setContentText("Alguno de los DNI ingresados no es válido linea 184");
                        errorAlert.showAndWait();

                    } else{
                        prGuest = searchConsumer(dniHP);
                        if(prGuest == null){
                            errorAlert.setContentText("El DNI del huésped principal no ha sido encontrado linea 190");
                            errorAlert.showAndWait();

                        } else {
                            guest = searchConsumer(dni, dniHP);
                            if(guest == null){
                                errorAlert.setContentText("El DNI no ha sido encontrado linea 196");
                                errorAlert.showAndWait();
                            } else{
                                String newBill = newConsumesFood.handleInstantPay(this.addedServices,guest,hotel.getRestaurantHandler().getData());
                                ServicesBillGenerator newBillPay = new ServicesBillGenerator(newBill, guest);
                                tempBill[0] = newBill;
                                textBill.setText(tempBill[0]);
                                textBill.setFont(Font.font("Inter", FontWeight.BLACK, 24));
                                try {
                                    newBillPay.showBill();
                                } catch (IOException ex) {
                                    throw new RuntimeException(ex);
                                }

                            }

                        }


                    }
                }

            }


        });



        billContainer.getChildren().add(textBill);
        inputsContainer.getChildren().add(payBtn);


        bigBox.setLeft(inputsContainer);
        bigBox.setRight(billContainer);





        getChildren().add(bigBox);


    }
    public Guest searchConsumer(String dniHP){
        try{
            if(mapRegisters.get(dniHP).getPrincipalGuest().getDni().equals(dniHP)){
                return mapRegisters.get(dniHP).getPrincipalGuest();
            }

        } catch (NullPointerException e){
            System.out.println("ALGO FALLO");
        }

        return null;
    }
    public Guest searchConsumer(String invitedDNI, String dniHP){
        for (CompanionGuest eachCompanion: mapRegisters.get(dniHP).getGroupGuest()){
            if (eachCompanion.getDni().equals(invitedDNI)){
                return eachCompanion;
            }
        }
        return null;
    }


}
