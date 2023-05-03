package View;

import View.Screens.AdminScreen.CreateServiceScreen;
import View.Screens.AdminScreen.FoodInventory;
import View.Screens.AdminScreen.RoomInventory;
import View.Screens.AdminScreen.RoomManaging;
import View.Screens.AdminScreen.ServicesInventory;
import View.Screens.AuthScreen.Auth;
import View.Screens.RecepcionistScreen.BookingScreen;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {

        // EntryPoint
        Pane authScreen = new Auth();

        Pane prb = new RoomManaging(); // Probar pestana principal Admin
        Pane inventoryRoom = new RoomInventory(); // Probar inventario habitaciones
        Pane inventoryFood = new FoodInventory(); // Probar inventario comidas
        Pane invetoryService = new ServicesInventory(); // Probar inventario servicios

        Pane createService = new CreateServiceScreen();  // Probar creacion servicios\
        Pane bookingScreen = new BookingScreen();


        

        Scene scene = new Scene(bookingScreen);


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
