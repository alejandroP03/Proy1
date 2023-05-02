package View.Screens.AdminScreen;

import View.Components.InputText;
import View.Components.PrinicipalWindow;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class CreateServiceScreen extends VBox {
    public CreateServiceScreen(){
        getStylesheets().add("View/Styles/AdminScreen.css");
        setId("background-window");
        setPrefSize(800,800);
        setAlignment(Pos.CENTER);
        PrinicipalWindow pw = new PrinicipalWindow("admin");
        setVgrow(pw, Priority.ALWAYS);
        setPadding(new Insets(50));
        Pane mainPane = pw.getMainPane();
        VBox vBox = new VBox();


    }

    public GridPane createServiceCard(){
        Label title = new Label("Crear servicio");
        InputText idService = new InputText("ID Servicio", "0123", "", "person");
        InputText nameService = new InputText("Nombre del servicio", "Tender cama", "", "person");
        InputText priceService = new InputText("Precio del servicio", "100000", "", "person");

        GridPane gridPane = new GridPane();
        gridPane.setId("card-create-service");


        return gridPane;
    }
}
