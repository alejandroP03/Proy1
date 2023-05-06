package View.Screens.AdminScreen.Inventory;

import Controller.Controller;
import View.Components.Inputs.SelectorInput;
import View.Components.PrincipalWindow.PrinicipalWindow;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class InventoryScreen extends VBox {

    public enum Inventory {
        ROOMS, FOODS, SERVICE;
    }

    Controller controller;
    Inventory requestedInventory = Inventory.ROOMS;
    BorderPane mainPane = new BorderPane();

    public InventoryScreen(Controller controller, PrinicipalWindow prinicipalWindow) throws Exception {
        getStylesheets().add("View/Styles/admin/adminScreens.css");
        getStylesheets().add("View/Styles/admin/inventory.css");
        this.controller = controller;
        setVgrow(prinicipalWindow, Priority.ALWAYS);
        mainPane.setPadding(new Insets(30));
        // Agregar titulo y boton habitaciones
        BorderPane titlePane = topBorder();
        BorderPane.setMargin(titlePane, new Insets(0, 0, 0, 330));
        mainPane.setTop(titlePane);
        VBox.setVgrow(mainPane, Priority.ALWAYS);
        // Agregar informacion del centro
        topCenterBorder(Inventory.ROOMS);
        prinicipalWindow.setContent(mainPane);

        getChildren().add(prinicipalWindow);

    }

    public BorderPane topBorder() {
        Text title = new Text("Inventario");
        title.setId("inventory-title");

        SelectorInput menuButton = new SelectorInput("", "", "", "",
                new String[] { "Habitaciones", "Comidas y bebidas", "Servicios" }, Inventory.values());
        menuButton.addListener(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
                try {
                    topCenterBorder((Inventory) menuButton.getValue());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        });

        menuButton.setId("type-inventory");

        BorderPane titlePane = new BorderPane();
        titlePane.setCenter(title);
        titlePane.setRight(menuButton);

        return titlePane;
    }

    public VBox topCenterBorder(Inventory requestedInventory) throws Exception {
        VBox inventoryBox;
        mainPane.setLeft(new HBox());
        switch (requestedInventory) {
            case FOODS:
                inventoryBox = new FoodInventory(controller.getFoodStock());
                break;
            case ROOMS:
                inventoryBox = new RoomInventory(controller.getRoomStock());
                VBox leftInfo = ((RoomInventory) inventoryBox).leftBorder();
                BorderPane.setMargin(leftInfo, new Insets(0, 40, 0, 0));
                mainPane.setLeft(leftInfo);
                break;
            case SERVICE:
                inventoryBox = new ServicesInventory();
                break;
            default:
                inventoryBox = new RoomInventory(this.controller.getRoomStock());
        }

        mainPane.setCenter(inventoryBox);

        return inventoryBox;
    }

}
