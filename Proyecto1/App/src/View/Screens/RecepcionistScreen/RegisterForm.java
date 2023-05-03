package View.Screens.RecepcionistScreen;

import View.Components.Inputs.InputText;
import View.Components.PrinicipalWindow;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.*;


public class RegisterForm extends VBox {
    public PrinicipalWindow pw;
    public RegisterForm(){
        getStylesheets().add("View/Styles/recepcionist/recepcionistScreens.css");
        pw = new PrinicipalWindow<VBox>("recepcionist", new VBox());
        setVgrow(pw, Priority.ALWAYS);
        Pane mainPane = pw.getMainPane();
        setAlignment(Pos.CENTER);
        BorderPane borderPane = new BorderPane();
        borderPane.setLeft(isReserve());
        borderPane.setRight(guestForm());
        borderPane.setPadding(new Insets(70));

//        GridPane contenPane = new GridPane();
//        contenPane.setAlignment(Pos.CENTER);
//        contenPane.add(isReserve(),0,0);
//        contenPane.add(guestForm(),1,0);
//        contenPane.setGridLinesVisible(true);
//        contenPane.setHgap(200);
//        contenPane.setPadding(new Insets(50));


        mainPane.getChildren().add(borderPane);
        getChildren().add(pw);
    }

    public GridPane isReserve(){
        Label titleReserve = new Label("Tiene reserva?");
        titleReserve.setId("title-reserve");
        InputText id = new InputText("Cedula", "103938234842", "", "person");
        CheckBox checkBox = new CheckBox("");
        Button searchBtn = new Button("Buscar -->");

        GridPane gridPane = new GridPane();

        gridPane.add(titleReserve, 0,0);
        gridPane.add(checkBox, 1,0);
        gridPane.add(id, 0,1);

        gridPane.add(searchBtn, 1,2);
        gridPane.setHgap(-70);

        return  gridPane;
    }

    public VBox guestForm(){
        Label titleForm = new Label("Huesped Principal");
        titleForm.setId("title-guest-form");
        InputText name = new InputText("Nombre", "Jhon Doe", "", "person");
        InputText phone = new InputText("Telefono", "321202833", "", "person");
        InputText id = new InputText("Cedula", "1039402331", "", "person");
        InputText card = new InputText("Tarjeta de credito", "1111 0000 3333", "", "person");
        InputText numGuests = new InputText("Numero de huespedes", "3", "", "person");
        Button sendForm = new Button("Ingresar -->");
        VBox vBox = new VBox();
        sendForm.setId("button-form");
        vBox.setPadding(new Insets(0,0,0,0));
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(titleForm, name, phone, id , card, numGuests,sendForm);
        vBox.setSpacing(15);
        return  vBox;
    }


}
