package View.Components;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class MenuCreateBooking  extends HBox {

    public MenuCreateBooking(){
        Label room = new Label("Habitacion");
        Label date = new Label("fecha");
        Label guest = new Label("Huesped");
        Label allData = new Label("Resumen");

        room.setOnMouseClicked(e -> {
            room.setStyle(" -fx-text-fill: red;");
            date.setStyle("-fx-text-fill: grey;");
            guest.setStyle("-fx-text-fill: grey;");
            allData.setStyle("-fx-text-fill: grey;");
        });
        date.setOnMouseClicked(e -> {
            room.setStyle(" -fx-text-fill: red;");
            date.setStyle("-fx-text-fill: red;");
            guest.setStyle("-fx-text-fill: grey;");
            allData.setStyle("-fx-text-fill: grey;");
        });
        guest.setOnMouseClicked(e -> {
            room.setStyle(" -fx-text-fill: red;");
            date.setStyle("-fx-text-fill: red;");
            guest.setStyle("-fx-text-fill: red;");
            allData.setStyle("-fx-text-fill: grey;");
        });
        allData.setOnMouseClicked(e -> {
            room.setStyle(" -fx-text-fill: red;");
            date.setStyle("-fx-text-fill: red;");
            guest.setStyle("-fx-text-fill: red;");
            allData.setStyle("-fx-text-fill: red;");
        });

        setSpacing(15);
        getChildren().addAll(room,date,guest,allData);


    }

    }





