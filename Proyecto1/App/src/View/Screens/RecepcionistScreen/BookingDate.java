package View.Screens.RecepcionistScreen;

import Controller.Controller;
import View.Components.Calendars.Calendar;
import View.Components.MenuCreateBooking;
import View.Components.PrincipalWindow.PrinicipalWindow;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.*;

import java.time.LocalDate;

public class BookingDate extends VBox {


    public BookingDate(Controller controller, PrinicipalWindow prinicipalWindow) {
        getStylesheets().add("View/Styles/recepcionist/recepcionistScreens.css");

        setVgrow(prinicipalWindow, Priority.ALWAYS);
        Pane mainPane = new VBox();
        prinicipalWindow.setContent(mainPane);
        VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);
        vbox.getChildren().add(titleMenu("asp"));
        vbox.getChildren().add(datePick());
        vbox.getChildren().add(infPrice());

        vbox.setPadding(new Insets(30, 80, 40, 80));
        vbox.setSpacing(50);

        mainPane.getChildren().add(vbox);
        getChildren().add(prinicipalWindow);
    }

    public HBox titleMenu(String page) {
        MenuCreateBooking topMenuBooking = new MenuCreateBooking();

        return  topMenuBooking;
    }

    public GridPane datePick() {
        Label title = new Label("Seleccionar fecha");
        Calendar calendario = new Calendar();
        LocalDate[] dates = calendario.getDateRange();


        title.setId("title-date-picker");
        GridPane gridPane = new GridPane();
        gridPane.setVgap(40);
        gridPane.setHgap(50);
        gridPane.add(title, 0, 0);
        gridPane.add(calendario, 0, 1);

        return gridPane;

    }

    public GridPane infPrice() {
        Label priceTitle = new Label("Fechas \nseleccionadas");
        priceTitle.setId("title-date-price");
        Label startDate = new Label("Fecha inicial");
        startDate.setId("info-date-price");
        Label startDateinfo = new Label(" 10/10/2023");
        startDateinfo.setId("info-date-price");

        Label finalDate = new Label("Fecha final");
        finalDate.setId("info-date-price");
        Label finalDateInfo = new Label(" 10/10/2023");
        finalDateInfo.setId("info-date-price");

        // Grid del precio
        GridPane priceGrid = new GridPane();
        priceGrid.setVgap(15);
        HBox infoStart = new HBox();
        infoStart.setSpacing(100);
        infoStart.getChildren().addAll(startDate, startDateinfo);

        HBox infoFinal = new HBox();
        infoFinal.setSpacing(100);
        infoFinal.getChildren().addAll(finalDate, finalDateInfo);

        priceGrid.add(priceTitle, 0, 0);
        priceGrid.add(infoStart, 0, 1);
        priceGrid.add(infoFinal, 0, 2);


        priceGrid.setAlignment(Pos.CENTER_RIGHT);
        return priceGrid;
    }
}
