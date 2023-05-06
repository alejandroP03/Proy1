package View.Components.PrincipalWindow;

import Controller.Controller;
import Controller.Router;
import Model.HotelObjects.UserType;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;

public class EmployeePrincipalWindow extends PrinicipalWindow{
    Controller controller;
    VerticalIconText restaurantBtn = new VerticalIconText("Restaurante","git-button",true);
    VerticalIconText serviceBtn = new VerticalIconText("Servicios","git-button",false);

    VerticalIconText selectedScreenBtn = restaurantBtn;

    public EmployeePrincipalWindow(Router router, Controller controller) {
        super(UserType.EMPLOYEE, router);
        this.controller = controller;
    }


    @Override
    public VBox lateralMenu() {
        VBox vbox = new VBox();

        restaurantBtn.setOnMouseClicked(e -> {
            if (!restaurantBtn.isSelectedStatus(selectedScreenBtn)) {
                try {
                    router.goToInventoryScreen();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
                this.selectedScreenBtn = restaurantBtn;
            }
        });

        serviceBtn.setOnMouseClicked(e -> {
            if (!serviceBtn.isSelectedStatus(selectedScreenBtn)) {
                try {
                    router.goToInventoryScreen();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }

                this.selectedScreenBtn = serviceBtn;
            }
        });

        vbox.getChildren().addAll(restaurantBtn,serviceBtn);

        VBox pane = new VBox();
        pane.getChildren().add(vbox);
        vbox.setSpacing(35);
        pane.setAlignment(Pos.CENTER);



        return pane;
    }
}
