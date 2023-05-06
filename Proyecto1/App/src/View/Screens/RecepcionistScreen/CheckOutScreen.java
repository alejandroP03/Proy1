package View.Screens.RecepcionistScreen;

import Controller.Controller;
import View.Components.Inputs.InputText;
import View.Components.PrincipalWindow.PrinicipalWindow;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

public class CheckOutScreen extends VBox {


    public CheckOutScreen(Controller controller, PrinicipalWindow prinicipalWindow){
        getStylesheets().add("View/Styles/recepcionist/recepcionistScreens.css");

        setVgrow(prinicipalWindow, Priority.ALWAYS);
        BorderPane borderPane = new BorderPane();
        prinicipalWindow.setContent(borderPane);
        borderPane.setLeft(formLeft());
        borderPane.setRight(boxCardsRight());
        //borderPane.setBottom(errorMessage ());  TODO mostrar el mensaje de erro en pantalla de checkout

        borderPane.setPadding(new Insets(70));
//        mainPane.getChildren().add(borderPane);
        getChildren().add(prinicipalWindow);

    }

    public VBox formLeft(){
        Label title = new Label("Check-Out");
        title.setId("title-checkOut");
        InputText name = new InputText("Cedula", "10292202029", "", "person");
        VBox vBox = new VBox();
        vBox.getChildren().addAll(title,name);
        vBox.setSpacing(20);
        return vBox;
    }
    public VBox boxCardsRight(){
        VBox vBox = new VBox();
        // Creacion HBox de botones
        HBox hBoxButtons = new HBox();
        Button btnCheckOut = new Button("ChekOut -->");
        Button btnGenBill = new Button("Generar factura -->");
        hBoxButtons.getChildren().addAll(btnGenBill, btnCheckOut);
        hBoxButtons.setSpacing(20);
        vBox.setSpacing(30);
        vBox.getChildren().addAll(leftCard(),rightCard(), hBoxButtons);
        return vBox;
    }

    public VBox leftCard(){
        Label title = new Label("Datos del Huesped");
        title.setId("data-guest");
        HBox name = addInfo("Nombre","JhonDoe");
        HBox phone =addInfo("Telefono","300495473");
        HBox id = addInfo("Cedula","100239320");
        HBox card = addInfo("Tarjeta de credito","11000 3000 03444 0000");
        HBox nCompanions = addInfo("Numero Huespedes","5");
        VBox vBox = new VBox();
        vBox.setSpacing(15);
        vBox.setPadding(new Insets(30));
        vBox.setId("card-data-guest");
        vBox.getChildren().addAll(title,name,phone,id,card,nCompanions);

        return vBox;
    }
    public VBox rightCard(){
        Label title = new Label("Datos de la estadia");
        title.setId("data-guest");
        HBox time = addInfo("Tiempo de estadia","7 noches");
        HBox price =addInfo("Precio","750.000");
        HBox rooms =addInfo("Habitaciones","100239320");
        VBox vBox = new VBox();
        vBox.setSpacing(15);
        vBox.setPadding(new Insets(30));
        vBox.setId("card-data-guest");
        vBox.getChildren().addAll(title,time,price,rooms);
        return vBox;
    }

    public HBox addInfo(String type, String data){
        HBox hBox = new HBox();
        Label typeInfo = new Label(type+": ");
        typeInfo.setId("contenedor-guest-info");
        Label associatedInfo = new Label(data);
        associatedInfo.setId("data-guest-info");
        hBox.getChildren().addAll(typeInfo, associatedInfo);
        return hBox;
    }

    public VBox errorMessage (){
        HBox hBox = new HBox();
        hBox.setSpacing(15);
        Image imagen = new Image("View/assets/images/Vector.png");
        ImageView imageView = new ImageView(imagen);
        Label titleError = new Label("No se ha realizado el \ncheck-out");
        titleError.setId("title-error");
        hBox.getChildren().addAll(imageView, titleError);
        Label ms = new Label("Debe generar factura por \nprimera vez para poder realizar\nel check-out");
        ms.setId("ms-error");
        VBox vBox = new VBox();
        vBox.setSpacing(15);
        vBox.setId("box-error");
        vBox.setPadding(new Insets(30));
        vBox.getChildren().addAll(hBox,ms);
        return vBox;


    }

}
