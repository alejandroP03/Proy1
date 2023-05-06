package View;

import Controller.Controller;
import Controller.Router;
import View.Screens.AdminScreen.CreateServiceScreen;
import View.Screens.AdminScreen.Inventory.FoodInventory;
import View.Screens.AdminScreen.Inventory.InventoryScreen;
import View.Screens.AdminScreen.Inventory.RoomInventory;
import View.Screens.AdminScreen.Inventory.ServicesInventory;
import View.Screens.AdminScreen.RoomManaging;
import View.Screens.AuthScreen.Auth;
import View.Screens.RecepcionistScreen.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        InventoryScreen inventoryScreen = new InventoryScreen();
        Pane inventoryRoom = new RoomInventory(); // Probar inventario habitaciones
        Pane inventoryFood = new FoodInventory(); // Probar inventario comidas
        Pane invetoryService = new ServicesInventory(); // Probar inventario servicios
        Pane createService = new CreateServiceScreen();  // Probar creacion servicios\
        Pane bookingScreen = new BookingScreen();
        Pane datePickScreen = new BookingDate(); // Probar el calendario masivo
        Pane guesForm = new InfoGuest(); // Probar fomulario para un huesped
        Pane registerForm = new RegisterForm(); // Probar fomrulario de registro
        Pane companionInfo = new CompanionInfo();
        Pane savedInfo = new DataSaved();
        Pane checkout = new CheckOutScreen();

        Scene scene = new Scene(checkout);

        primaryStage.setScene(scene);

        primaryStage.setTitle("Panel con background color");
        primaryStage.setMinWidth(1125);
        primaryStage.setMinHeight(800);
        primaryStage.show();
//       new Router(primaryStage, new Controller());
    }

    public static void main(String[] args) {
        launch(args);
    }
}
