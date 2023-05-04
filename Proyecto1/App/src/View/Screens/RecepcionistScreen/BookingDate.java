package View.Screens.RecepcionistScreen;

import View.Components.PrinicipalWindow;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class BookingDate extends VBox {
    public PrinicipalWindow pw;

    public BookingDate() {
        getStylesheets().add("View/Styles/recepcionist/recepcionistScreens.css");

        pw = new PrinicipalWindow<VBox>("recepcionist", new VBox());
        setVgrow(pw, Priority.ALWAYS);
        Pane mainPane = pw.getMainPane();
        VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);
        vbox.getChildren().add(titleMenu("asp"));
        vbox.getChildren().add(datePick());
        vbox.getChildren().add(infPrice());

        vbox.setPadding(new Insets(30, 80, 40, 80));
        vbox.setSpacing(50);
        mainPane.getChildren().add(vbox);
        getChildren().add(pw);
    }

    public Label titleMenu(String page) {
        Label rooms = new Label("Habitaciones  > Fecha > Huesped > Resumen");
        return rooms;
    }

    public GridPane datePick() {
        Label title = new Label("Seleccionar fecha");
        DatePicker datePicker = new DatePicker();
        title.setId("title-date-picker");
        GridPane gridPane = new GridPane();
        gridPane.setVgap(40);
        gridPane.setHgap(50);
        gridPane.add(title, 0, 0);
        gridPane.add(datePicker, 0, 1);

        return gridPane;

    }

    public GridPane infPrice() {
        Label priceTitle = new Label("Precio Total");
        priceTitle.setId("title-date-price");
        Label days = new Label("7 noches");
        days.setId("info-date-price");
        Label price = new Label(" 40000 $");
        price.setId("info-date-price");
        // Grid del precio
        GridPane priceGrid = new GridPane();
        priceGrid.add(price, 0, 0);
        priceGrid.setVgap(15);
        HBox infoPrice = new HBox();
        infoPrice.setSpacing(100);
        infoPrice.getChildren().addAll(days, price);
        priceGrid.add(priceTitle, 0, 0);
        priceGrid.add(infoPrice, 0, 1);
        priceGrid.setAlignment(Pos.BOTTOM_RIGHT);
        return priceGrid;
    }
}
