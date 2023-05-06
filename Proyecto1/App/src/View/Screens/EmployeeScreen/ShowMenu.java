package View.Screens.EmployeeScreen;

import Controller.Controller;
import View.Components.Inputs.InputText;
import View.Components.Inputs.SelectorInput;
import View.Components.ObjectLists.ObjectsList;
import View.Components.PrincipalWindow.PrinicipalWindow;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;

public class ShowMenu  extends VBox {

    public ShowMenu(Controller controller, PrinicipalWindow prinicipalWindow) {
        getStylesheets().add("View/Styles/recepcionist/recepcionistScreens.css");

        Pane mainPane = new VBox();
        prinicipalWindow.setContent(mainPane);
        setVgrow(prinicipalWindow, Priority.ALWAYS);
        VBox vbox = new VBox();
        Label title = new Label("Menú");
        title.setId("title-menu");
        vbox.getChildren().add(title);
        vbox.getChildren().add(filtersForm());
        vbox.getChildren().add(gridInfo());
        vbox.setSpacing(50);
        vbox.setPadding(new Insets(30, 80, 40, 80));
        vbox.setAlignment(Pos.CENTER);

        mainPane.getChildren().add(vbox);
        getChildren().add(prinicipalWindow);


    }
    public GridPane filtersForm() {
        Label title = new Label("Filtros");
        title.setId("title-filter");
        SelectorInput typeFood = new SelectorInput("Tipo bebida", "", "", "Bebida",
                new String[]{"opcion 1", "opcion 2", "opcion 3", "N/A"});
        SelectorInput avaiability = new SelectorInput("Disponibilidad", "", "", "Balcon",
                new String[]{"opcion 1", "opcion 2", "opcion 3", "N/A"});

        InputText search = new InputText("ID Servicio", "0123", "", "person");

        typeFood.setId("input-employee-filters");
        avaiability.setId("input-employee-filters");
        search.setId("input-employee-filters");

        GridPane gridPane = new GridPane();
        gridPane.add(title, 0, 0);
        gridPane.add(typeFood, 0, 1);
        gridPane.add(avaiability, 1, 1);
        gridPane.add(search, 2, 1);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(20);
        gridPane.setVgap(10);

        return gridPane;
    }

    public ScrollPane gridInfo() {
        ObjectsList roomList = new ObjectsList(
                new String[] { "Nombre", "Tipo", "Disponibilidad", "Room service", "precio", "Agregar" });
        roomList.addElem(new Node[] { new Label("Sopa"), new Label("Comida"), new Label("Almuerzo"),
                new Label("Si"), new Label("15000"), new Button("+") });
        return roomList;
    }



}
