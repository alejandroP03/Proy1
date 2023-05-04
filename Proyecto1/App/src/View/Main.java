package View;



import Controller.Hotel;
import Model.HotelObjects.Service;
import View.Screens.AdminScreen.CreateServiceScreen;
import View.Screens.AdminScreen.FoodInventory;
import View.Screens.AdminScreen.RoomInventory;
import View.Screens.AdminScreen.RoomManaging;
import View.Screens.AdminScreen.ServicesInventory;
import View.Screens.AuthScreen.Auth;
import View.Screens.EmployeeScreen.PayService;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        // EntryPoint
        List<String> listservice = new ArrayList<String>();
        Hotel hotel = Hotel.getInstance();
        hotel.getServices().loadPersistentData();
        listservice.add(hotel.getServices().getData().get("11").getId());
        Pane authScreen = new Auth();

        Pane prb = new RoomManaging(); // Probar pestana principal Admin
        Pane inventoryRoom = new RoomInventory(); // Probar inventario habitaciones
        Pane inventoryFood = new FoodInventory();  // Probar inventario comidas
        Pane invetoryService = new ServicesInventory(); // Probar inventario servicios
        Pane createService = new CreateServiceScreen();  // Probar creacion servicios
        Pane payService = new PayService(listservice, true); // Probar pagar servicios


        Scene scene = new Scene(payService);


        primaryStage.setTitle("Panel con background color");


        scene.getStylesheets().add("View/Styles/font.css");

        primaryStage.setMinWidth(1125);
        primaryStage.setMinHeight(700);
        primaryStage.setX(300);
        primaryStage.setY(100);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

}
