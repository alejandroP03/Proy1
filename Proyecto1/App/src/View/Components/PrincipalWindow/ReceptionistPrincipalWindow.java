package View.Components.PrincipalWindow;

import Controller.Router;
import Model.HotelObjects.UserType;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;

public class ReceptionistPrincipalWindow extends PrinicipalWindow {
    Router router;

    public ReceptionistPrincipalWindow(Router router) {
        super(UserType.RECEPTIONIST, router);
        this.router = router;
    }

    @Override
    public VBox lateralMenu() {
        VBox vbox = new VBox();
        // TODO Agregar los botones (revisar que esten los iconos en el css principalWindow)
        vbox.getChildren().addAll();

        VBox pane = new VBox();
        pane.getChildren().add(vbox);
        vbox.setSpacing(35);
        pane.setAlignment(Pos.CENTER);

        return pane;
    }

}
