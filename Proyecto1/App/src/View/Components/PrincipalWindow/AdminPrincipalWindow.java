package View.Components.PrincipalWindow;

import Controller.Controller;
import Controller.Router;
import Model.HotelObjects.UserType;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;

public class AdminPrincipalWindow extends PrinicipalWindow {
    Controller controller;
    VerticalIconText roomsBtn = new VerticalIconText("Habitaciones", "git-button", true);
    VerticalIconText restaurantBtn = new VerticalIconText("Restaurante", "git-button", false);
    VerticalIconText serviceBtn = new VerticalIconText("Servicios", "git-button", false);
    VerticalIconText inventoryBtn = new VerticalIconText("Inventario", "git-button", false);

    VerticalIconText selectedScreenBtn = roomsBtn;

    public AdminPrincipalWindow(Router router, Controller controller) {
        super(UserType.ADMIN, router);
        this.controller = controller;

    }

    @Override
    public VBox lateralMenu() {
        VBox vbox = new VBox();

        roomsBtn.setOnMouseClicked(e -> {
            if (!roomsBtn.isSelectedStatus(selectedScreenBtn)) {
                router.goToRoomManaging();
                this.selectedScreenBtn = roomsBtn;
            }
        });

        restaurantBtn.setOnMouseClicked(e -> {
            if (!restaurantBtn.isSelectedStatus(selectedScreenBtn)) {
                System.out.println("No implementado");
                this.selectedScreenBtn = restaurantBtn;
            }
        });

        serviceBtn.setOnMouseClicked(e -> {
            if (!serviceBtn.isSelectedStatus(selectedScreenBtn)) {
                System.out.println("No implementado");
                this.selectedScreenBtn = serviceBtn;
            }
        });

        inventoryBtn.setOnMouseClicked(e -> {
            if (!inventoryBtn.isSelectedStatus(selectedScreenBtn)) {
                try {
                    router.goToInventoryScreen();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
                this.selectedScreenBtn = inventoryBtn;
            }
        });

        vbox.getChildren().addAll(
                roomsBtn,
                restaurantBtn,
                serviceBtn,
                inventoryBtn);

        VBox pane = new VBox();
        pane.getChildren().add(vbox);
        vbox.setSpacing(35);
        pane.setAlignment(Pos.CENTER);

        return pane;
    }

}
