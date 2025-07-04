package View.Screens.AdminScreen;

import Controller.Controller;
import View.Components.Inputs.InputText;
import View.Components.PrincipalWindow.PrinicipalWindow;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class CreateServiceScreen extends VBox {

    public CreateServiceScreen(Controller controller, PrinicipalWindow prinicipalWindow) {
        getStylesheets().add("View/Styles/admin/adminScreens.css");

        setVgrow(prinicipalWindow, Priority.ALWAYS);
        Pane mainPane = new VBox();
        prinicipalWindow.setContent(mainPane);
        VBox vBox = new VBox();
        vBox.setPadding(new Insets(50));
        vBox.setSpacing(40);

        // Crear primer card formulario
        vBox.getChildren().add(createServiceCard());

        // Crear segundo card de abajo
        vBox.getChildren().add(loadServices());

        mainPane.getChildren().add(vBox);
        getChildren().add(prinicipalWindow);

    }

    public GridPane createServiceCard() {
        Label title = new Label("Crear servicio");
        title.setStyle("-fx-font-weight: 800; -fx-font-size: 36px; -fx-line-height: 44px; -fx-text-fill: #FFFFFF;");
        // title.setId("title-create-service");
        InputText idService = new InputText("ID Servicio", "0123", "", "person");
        idService.setId("input-service-card");
        InputText nameService = new InputText("Nombre del servicio", "Tender cama", "", "person");
        nameService.setId("input-service-card");
        InputText priceService = new InputText("Precio del servicio", "100000", "", "person");
        priceService.setId("input-service-card");

        GridPane gridPane = new GridPane();
        gridPane.setId("card-create-service");
        gridPane.setHgap(180);
        gridPane.setVgap(5);
        GridPane.setHgrow(gridPane, Priority.ALWAYS);
        gridPane.setPadding(new Insets(40));

        // Agregar cosas a la izquierda
        gridPane.add(title, 0, 0);
        gridPane.add(idService, 0, 1);
        gridPane.add(nameService, 0, 2);
        gridPane.add(priceService, 0, 3);

        // Agregar cosas a la derecha
        gridPane.add(morePeeopleForm(), 1, 1);
        gridPane.add(daysAvaiabylity(), 1, 2);
        gridPane.add(hoursForm(), 1, 3);

        return gridPane;
    }

    public BorderPane loadServices() {
        Label title = new Label("Cargar servicios");
        title.setStyle("-fx-font-weight: 800; -fx-font-size: 36px; -fx-line-height: 44px; -fx-text-fill: #FFFFFF;");
        Label description = new Label("Carga un archivo con los datos de los servicios");
        description.setStyle("-fx-font-weight: 300; -fx-font-size: 15px;  -fx-text-fill: #FFFFFF; ");
        Image imagen = new Image("View/assets/images/Group 79.png");
        ImageView imageView1 = new ImageView(imagen);
        // Crear el boton
        Button button = new Button("Cargar");
        // HBox hbox = new HBox();
        // Label label = new Label("Cargar");
        // ImageView imageView = new ImageView(new
        // Image("View/assets/images/arrow-down-tray.png"));

        // hbox.getChildren().addAll(label, imageView);
        // hbox.setSpacing(10);
        // button.setGraphic(hbox);
        // hbox.setStyle("-fx-background-color: #03555C; " + "-fx-border-radius: 20px;"
        // );

        GridPane gridPane = new GridPane();
        gridPane.add(title, 0, 0);
        gridPane.add(description, 0, 1, 1, 2);
        GridPane.setValignment(button, VPos.BOTTOM);
        gridPane.add(button, 0, 3, 1, 4);

        BorderPane borderPane = new BorderPane();
        borderPane.setId("load-services-card");
        borderPane.setLeft(gridPane);
        borderPane.setRight(imageView1);
        borderPane.setPadding(new Insets(30, -10, -30, 25));

        return borderPane;
    }

    public GridPane morePeeopleForm() {
        Label title = new Label("¿El servicio es para varias personas?");
        // Crear RadioButons
        RadioButton radioButton1 = new RadioButton("Si");
        RadioButton radioButton2 = new RadioButton("No");
        ToggleGroup toggleGroup = new ToggleGroup();
        radioButton1.setToggleGroup(toggleGroup);
        radioButton2.setToggleGroup(toggleGroup);
        HBox hbox = new HBox(radioButton1, radioButton2);
        hbox.setSpacing(50);
        hbox.setAlignment(Pos.CENTER_LEFT);
        hbox.setPadding(new Insets(10));
        GridPane girdPane = new GridPane();
        girdPane.add(title, 0, 0);
        girdPane.add(hbox, 0, 1);
        return girdPane;

    }

    public VBox daysAvaiabylity() {
        Label title = new Label("Dias disponible del servicio:");
        String[] dias = { "L", "M", "X", "J", "V", "S", "D" };
        HBox daysVbox = new HBox();

        for (String dia : dias) {
            Button dayBtn = crearBoton(dia);
            daysVbox.getChildren().add(dayBtn);
        }

        daysVbox.setSpacing(15);
        VBox generalVbox = new VBox();
        generalVbox.setSpacing(35);
        generalVbox.getChildren().addAll(title, daysVbox);
        return generalVbox;
    }

    public GridPane hoursForm() {

        InputText initialDateForm = new InputText("Hora inicial", "12:00", "", "person");
        initialDateForm.setId("initial-date-form");

        InputText finalDateForm = new InputText("Hora Final", "15:00", "", "person");
        finalDateForm.setId("initial-date-form");

        GridPane gridPane = new GridPane();
        gridPane.add(initialDateForm, 0, 0);
        gridPane.add(finalDateForm, 1, 0);
        gridPane.setHgap(35);

        return gridPane;
    }

    public Button crearBoton(String texto) {
        Button botonDay = new Button(texto);
        // Puedes agregar más propiedades al botón aquí si lo deseas
        return botonDay;
    }

}
