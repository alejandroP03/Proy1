package View;

import View.Screens.AdminScreen.FoodInventory;
import View.Screens.AdminScreen.RoomInventory;
import View.Screens.AdminScreen.ScreenAdmin;
import View.Screens.AdminScreen.ServicesInventory;
import View.Screens.AuthScreen.Auth;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {

        // EntryPoint
        Pane authScreen = new Auth();

        Pane prb = new ScreenAdmin(); // Probar pestana principal Admin
        Pane inventoryRoom = new RoomInventory(); // Pronbar inventario habitaciones
        Pane inventoryFood = new FoodInventory();  // Pronbar inventario comidas
        Pane invetoryService = new ServicesInventory(); // Porbar inventario servicios

        Scene scene = new Scene(prb);

        primaryStage.setTitle("Panel con background color");


        scene.getStylesheets().add("View/Styles/font.css");

        primaryStage.setMinWidth(1125);
        primaryStage.setMinHeight(800);
        primaryStage.setX(300);
        primaryStage.setY(100);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

}
