package View.Components.PrincipalWindow;

import Model.HotelObjects.UserType;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;

public class ReceptionistPrincipalWindow extends PrinicipalWindow {

    public ReceptionistPrincipalWindow() {
        super(UserType.RECEPTIONIST);
    }

    @Override
    public VBox lateralMenu() {
        VBox vbox = new VBox();
        vbox.getChildren().addAll(
                verticalIconText("Crear reserva", "View/assets/images/Subtract.png", true),
                verticalIconText("Crear registro", "View/assets/images/building-storefront.png", false),
                verticalIconText("Check-out", "View/assets/images/shopping-bag.png", false),
                verticalIconText("Cancelar reserva", "View/assets/images/Union.png", false));

        VBox pane = new VBox();
        pane.getChildren().add(vbox);
        vbox.setSpacing(35);
        pane.setAlignment(Pos.CENTER);

        return pane;
    }

}
