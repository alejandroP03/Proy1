package View;

import View.Components.ScreensAdmin.FoodInventory;
import View.Components.ScreensAdmin.RoomInventory;
import View.Components.ScreensAdmin.ServicesInventory;
import View.Screens.AuthScreen.Auth;
import View.Components.ScreensAdmin.ScreenAdmin;
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

        Scene scene = new Scene(invetoryService);

        scene.getStylesheets().add("View/Styles/components.css");
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
