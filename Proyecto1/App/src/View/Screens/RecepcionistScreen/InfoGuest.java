package View.Screens.RecepcionistScreen;

import Controller.Controller;
import View.Components.Inputs.InputText;
import View.Components.PrincipalWindow.PrinicipalWindow;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class InfoGuest extends VBox {

    public InfoGuest(Controller controller, PrinicipalWindow prinicipalWindow) {

        getStylesheets().add("View/Styles/recepcionist/recepcionistScreens.css");

        setVgrow(prinicipalWindow, Priority.ALWAYS);
        Pane mainPane = new VBox();
        prinicipalWindow.setContent(mainPane);
        VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);
        vbox.getChildren().add(titleMenu("asp"));
        vbox.getChildren().add(guestForm());
        vbox.setPadding(new Insets(30, 80, 40, 80));
        vbox.setSpacing(50);
        mainPane.getChildren().add(vbox);
        getChildren().add(prinicipalWindow);
    }

    public Label titleMenu(String page) {
        Label rooms = new Label("Habitaciones  > Fecha > Huesped > Resumen");
        return rooms;
    }

    public VBox guestForm() {
        Label titleForm = new Label("Huesped Principal");
        titleForm.setId("title-guest-form");
        InputText name = new InputText("Nombre", "Jhon Doe", "", "person");
        InputText phone = new InputText("Telefono", "321202833", "", "person");
        InputText id = new InputText("Cedula", "1039402331", "", "person");
        InputText card = new InputText("Tarjeta de credito", "1111 0000 3333", "", "person");
        InputText numGuests = new InputText("Numero de huespedes", "3", "", "person");
        Button sendForm = new Button("Ingresar -->");

        sendForm.setOnAction(e -> {

        });

        VBox vBox = new VBox();
        sendForm.setId("button-form");
        vBox.setPadding(new Insets(0, 450, 0, 450));
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(titleForm, name, phone, id, card, numGuests, sendForm);
        vBox.setSpacing(15);
        return vBox;
    }
}
