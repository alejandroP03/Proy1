package View.Components.PrincipalWindow;

import Model.HotelObjects.UserType;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;

public class AdminPrincipalWindow extends PrinicipalWindow {

    public AdminPrincipalWindow() {
        super(UserType.ADMIN);
    }

    @Override
    public VBox lateralMenu() {
        VBox vbox = new VBox();
        vbox.getChildren().addAll(
                verticalIconText("Habitaciones", "View/assets/images/Subtract.png", true),
                verticalIconText("Restaurante", "View/assets/images/building-storefront.png", false),
                verticalIconText("Servicios", "View/assets/images/shopping-bag.png", false),
                verticalIconText("Inventario", "View/assets/images/Union.png", false));

        VBox pane = new VBox();
        pane.getChildren().add(vbox);
        vbox.setSpacing(35);
        pane.setAlignment(Pos.CENTER);

        return pane;
    }

}
