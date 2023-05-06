package View.Screens.RecepcionistScreen;

import View.Components.PrincipalWindow.PrinicipalWindow;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;

public class DataSaved  extends  VBox{

    public DataSaved(PrinicipalWindow prinicipalWindow){
        getStylesheets().add("View/Styles/recepcionist/recepcionistScreens.css");
        setVgrow(prinicipalWindow, Priority.ALWAYS);
        Pane mainPane = new Pane();
        prinicipalWindow.setContent(mainPane);

        Label title = new Label("Resumen");
        title.setId("title-screen-data-guest");
        BorderPane borderPane = new BorderPane();
        Button button = new Button("Botón");
        button.setId("button-form");
        borderPane.setBottom(button);
        BorderPane.setAlignment(button, Pos.BOTTOM_RIGHT);
        BorderPane.setMargin(button, new Insets(20));
        borderPane.setTop(title);
        borderPane.setLeft(leftCard());
        borderPane.setRight(rightCard());
        borderPane.setPadding(new Insets(70));

        mainPane.getChildren().add(borderPane);
        getChildren().add(prinicipalWindow);

    }

    public VBox leftCard(){
        Label title = new Label("Datos del Huesped");
        title.setId("data-guest");
        HBox name = addInfo("Nombre","JhonDoe");
        HBox phone =addInfo("Telefono","300495473");
        HBox id =addInfo("Cedula","100239320");
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

    public Label titleMenu(String page){
        Label rooms = new Label("Habitaciones  > Fecha > Huesped > Resumen");
        return rooms;
    }




}
