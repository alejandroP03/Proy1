package View;

import View.Components.ObjectsList;
import View.Screens.AdminScreen.CreateServiceScreen;
import View.Screens.AdminScreen.FoodInventory;
import View.Screens.AdminScreen.RoomInventory;
import View.Screens.AdminScreen.RoomManaging;
import View.Screens.AdminScreen.ServicesInventory;
import View.Screens.AuthScreen.Auth;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
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
        Pane createService = new CreateServiceScreen(); // Probar creacion servicios

        ObjectsList obj = new ObjectsList(new String[] { "Nombre", "Camas", "Caracteristica", "Lugar", "Tipo" });
        obj.addElem(new Node[]{new Text("Pene"), new Label("Hello"), new Button("Jar")});

        Scene scene = new Scene(obj);

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
