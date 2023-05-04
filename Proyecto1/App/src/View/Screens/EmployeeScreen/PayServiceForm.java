package View.Screens.EmployeeScreen;

import Controller.BillService.ServicesBillGenerator;
import Controller.ConsumeHandler.ConsumeRecorder;
import Controller.Hotel;
import Controller.RegisterHandler.CompanionGuest;
import Controller.RegisterHandler.Guest;
import Model.HotelObjects.*;
import View.Components.Inputs.InputText;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PayServiceForm extends VBox{

    Hotel hotel = Hotel.getInstance();
    Map<Object, Registration> mapRegisters = hotel.getRegistrationHandler().getData();
    Map<Object, Food> mapFoods = hotel.getRestaurantHandler().getData();
    ArrayList<String> addedServices;
    boolean isService;
    ConsumeRecorder<Service> newConsumes = new ConsumeRecorder<Service>();
    ConsumeRecorder<Food> newConsumesFood = new ConsumeRecorder<Food>();


    private HBox bigBox = new HBox(){
        {
            setAlignment(Pos.CENTER);
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
        inputsContainer.getChildren().clear();



        InputText dniInput = new InputText("DNI", "Ingrese el DNI del huésped principal", "", "person");
        inputsContainer.getChildren().add(dniInput);
        InputText dniHPInput = new InputText("DNI del huésped principal", "Ingrese el DNI del huésped principal", "", "person");

        Text textBill = new Text();
        final String[] tempBill = { "" };

        if(isPrincipal){
            inputsContainer.getChildren().add(dniHPInput);
        }

        Button payBtn = new Button("Enviar -->");
        payBtn.setId("button-form");

        // TODO Terminar setOnAction para que los servicios se guarden en billContainer
        payBtn.setOnAction(e->{
            String dni = dniInput.getValue();
            String dniHP = dniHPInput.getValue();
            Alert errorAlert = new Alert(Alert.AlertType.INFORMATION);
            errorAlert.setHeaderText("Error");
            errorAlert.setTitle("Error");

            Guest guest;
            if(isService){
                if(isPrincipal){
                    if(dni.isBlank()){
                        errorAlert.setContentText("El DNI ingresado no es valido");
                        errorAlert.showAndWait();
                    } else {
                        guest = searchConsumer(dni);
                        if(guest == null){
                            errorAlert.setContentText("El DNI no ha sido encontrado");
                            errorAlert.showAndWait();
                        } else{
                            String newBill = newConsumes.handleInstantPay(this.addedServices,guest,hotel.getServices().getData());
                            ServicesBillGenerator newBillPay = new ServicesBillGenerator(newBill, guest);
                            tempBill[0] = newBill;
                            try {
                                newBillPay.showBill();
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }

                        }


                    }
                } else {
                    if(dni.isBlank()||dniHP.isBlank()){
                        errorAlert.setContentText("Alguno de los DNI ingresados no es valido");
                        errorAlert.showAndWait();

                    } else{
                        guest = searchConsumer(dni,dniHP);
                        if(guest == null){
                            errorAlert.setContentText("El DNI no ha sido encontrado");
                            errorAlert.showAndWait();
                        } else{
                            String newBill = newConsumes.handleInstantPay(this.addedServices, guest, hotel.getServices().getData());
                            ServicesBillGenerator newBillPay = new ServicesBillGenerator(newBill, guest);
                            tempBill[0] = newBill;
                            try {
                                newBillPay.showBill();
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }

                        }
                    }
                }

            } else {
                if(isPrincipal){
                    if(dni.isBlank()){
                        errorAlert.setContentText("El DNI ingresado no es valido");
                        errorAlert.showAndWait();
                    } else {
                        guest = searchConsumer(dni);
                        if(guest == null){
                            errorAlert.setContentText("El DNI no ha sido encontrado");
                            errorAlert.showAndWait();
                        } else{
                            String newBill = newConsumesFood.handleInstantPay(this.addedServices,guest,hotel.getRestaurantHandler().getData());
                            ServicesBillGenerator newBillPay = new ServicesBillGenerator(newBill, guest);
                            tempBill[0] = newBill;
                            try {
                                newBillPay.showBill();
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }

                        }


                    }
                } else {
                    if(dni.isBlank()||dniHP.isBlank()){
                        errorAlert.setContentText("Alguno de los DNI ingresados no es valido");
                        errorAlert.showAndWait();

                    } else{
                        guest = searchConsumer(dni,dniHP);
                        if(guest == null){
                            errorAlert.setContentText("El DNI no ha sido encontrado");
                            errorAlert.showAndWait();
                        } else{
                            String newBill = newConsumesFood.handleInstantPay(this.addedServices,guest,hotel.getRestaurantHandler().getData());
                            ServicesBillGenerator newBillPay = new ServicesBillGenerator(newBill, guest);
                            tempBill[0] = newBill;
                            try {
                                newBillPay.showBill();
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }

                        }
                    }
                }

            }


        });

        inputsContainer.getChildren().add(payBtn);
        bigBox.getChildren().add(inputsContainer);




        getChildren().add(bigBox);


    }
    public Guest searchConsumer(String dniHP){
        if(mapRegisters.get(dniHP).getPrincipalGuest().getDni().equals(dniHP)){
            return mapRegisters.get(dniHP).getPrincipalGuest();
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
