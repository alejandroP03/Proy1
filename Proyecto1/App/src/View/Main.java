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


    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Hotel PMS");
        primaryStage.setMinWidth(1125);
        primaryStage.setMinHeight(800);
        new Router(primaryStage, new Controller());
        primaryStage.show();
//       new Router(primaryStage, new Controller());
    }

    public static void main(String[] args) {
        launch(args);
    }
}


