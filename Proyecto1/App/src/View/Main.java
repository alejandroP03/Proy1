package View;

import View.Components.Calendars.Calendar;
import View.Screens.AdminScreen.Inventory.InventoryScreen;
import View.Screens.AuthScreen.Auth;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {

        // EntryPoint
        Auth authScreen = new Auth();
        InventoryScreen inventoryScreen = new InventoryScreen();

        Scene scene = new Scene(new Calendar());


        primaryStage.setTitle("Panel con background color");

        scene.getStylesheets().add("View/Styles/font.css");

        primaryStage.setMinWidth(1125);
        primaryStage.setMinHeight(600);
        primaryStage.setX(300);
        primaryStage.setY(100);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

}
