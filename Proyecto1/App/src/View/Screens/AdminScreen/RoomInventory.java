package View.Screens.AdminScreen;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import View.Components.PrinicipalWindow;
import View.Components.ObjectLists.ObjectsList;
import View.Components.ObjectLists.SpanableObjectsList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class RoomInventory extends VBox {

    public RoomInventory() {
        getStylesheets().add("View/Styles/admin/adminScreens.css");
        PrinicipalWindow<VBox> pw = new PrinicipalWindow<VBox>("admin", new VBox());
        setVgrow(pw, Priority.ALWAYS);
        VBox mainPane = pw.getMainPane();

        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(30, 0, 40, 30));

        // Agregar titulo y boton habitaciones
        StackPane hBox = topBorder();
        BorderPane.setMargin(hBox, new Insets(0, 40, 50, 330));
        borderPane.setTop(hBox);

        // Agregar informacion del centro
        VBox centerInfo = topCenterBorder();
        borderPane.setCenter(centerInfo);

        // Agrear cosa de la izquierda
        VBox leftInfo = leftBorder();
        BorderPane.setMargin(leftInfo, new Insets(0, 40, 0, 0));
        borderPane.setLeft(leftInfo);
        mainPane.getChildren().add(borderPane);
        VBox.setVgrow(borderPane, Priority.ALWAYS);

        getChildren().add(pw);

    }

    public StackPane topBorder() {
        Label title = new Label("Inventario");
        title.setId("inventory-title");
        MenuButton menuButton = crearMenuButton("Habitaciones", Arrays.asList("Opción 7", "Opción 8", "Opción 9"));
        menuButton.setId("type-inventory");
        HBox hBox = new HBox();
        hBox.setSpacing(100);
        hBox.getChildren().addAll(title, menuButton);
        hBox.setAlignment(Pos.CENTER);
        StackPane stackPane = new StackPane();
        stackPane.getChildren().add(hBox);
        stackPane.setAlignment(Pos.CENTER);
        return stackPane;
    }

    public VBox leftBorder() {
        Label title = new Label("Ocupacion");
        VBox container = new VBox();
        container.setPrefSize(202, 536);
        container.setId("ocuppied-container");
        container.getChildren().add(title);
        container.setAlignment(Pos.CENTER);
        return container;
    }

    public VBox topCenterBorder() {
        Label title = new Label("Filtros");

        // Filtro habitacion
        Label typeRoom = new Label("Tipo de habitacion");
        MenuButton menuType = crearMenuButton("SuiteDoble", Arrays.asList("Opción 1", "Opción 2", "Opción 3"));
        // Filtro caracteristicas
        Label features = new Label("Caracteristica");
        MenuButton menuFeatures = crearMenuButton("Balcon", Arrays.asList("Opción 3", "Opción 5", "Opción 6"));
        // Filtro cama
        Label bed = new Label("Cama");
        MenuButton menuBeds = crearMenuButton("King", Arrays.asList("Opción 7", "Opción 8", "Opción 9"));

        // Se agrega a la grilla
        GridPane filterGrid = new GridPane();
        filterGrid.setHgap(50);
        filterGrid.setVgap(15);

        filterGrid.add(typeRoom, 0, 0);
        filterGrid.add(menuType, 0, 1);
        filterGrid.add(features, 1, 0);
        filterGrid.add(menuFeatures, 1, 1);
        filterGrid.add(bed, 2, 0);
        filterGrid.add(menuBeds, 2, 1);

        ObjectsList obj = new SpanableObjectsList(new String[] { "Nombre", "Camas", "Caracteristica", "Lugar", "Tipo" },
                new HBox());

        obj.addElem(new Node[] { new Text("Suite 444"), new Button("Hola"), new Text("Balcon"), new Text("Piso 4"),
                new Text("Suite") });
        obj.addElem(new Node[] { new Text("Suite 444"), new Text("King1"), new Text("Balcon"), new Text("Piso 4"),
                new Text("Suite") });
        obj.addElem(new Node[] { new Text("Suite 444"), new Text("King1"), new Text("Balcon"), new Text("Piso 4"),
                new Text("Suite") });
        obj.addElem(new Node[] { new Text("Suite 444"), new Text("King1"), new Text("Balcon"), new Text("Piso 4"),
                new Text("Suite") });
        obj.addElem(new Node[] { new Text("Suite 444"), new Text("King1"), new Text("Balcon"), new Text("Piso 4"),
                new Text("Suite") });
        obj.addElem(new Node[] { new Text("Suite 444"), new Text("King1"), new Text("Balcon"), new Text("Piso 4"),
                new Text("Suite") });
        obj.addElem(new Node[] { new Text("Suite 444"), new Text("King1"), new Text("Balcon"), new Text("Piso 4"),
                new Text("Suite") });
        obj.addElem(new Node[] { new Text("Suite 444"), new Text("King1"), new Text("Balcon"), new Text("Piso 4"),
                new Text("Suite") });
        obj.addElem(new Node[] { new Text("Suite 444"), new Text("King1"), new Text("Balcon"), new Text("Piso 4"),
                new Text("Suite") });
        obj.addElem(new Node[] { new Text("Suite 444"), new Text("King1"), new Text("Balcon"), new Text("Piso 4"),
                new Text("Suite") });
        obj.addElem(new Node[] { new Text("Suite 444"), new Text("King1"), new Text("Balcon"), new Text("Piso 4"),
                new Text("Suite") });
        obj.addElem(new Node[] { new Text("Suite 444"), new Text("King1"), new Text("Balcon"), new Text("Piso 4"),
                new Text("Suite") });

        VBox topCenter = new VBox();

        topCenter.getChildren().addAll(title, filterGrid, obj);
        topCenter.setSpacing(20);

        return topCenter;

    }

    public MenuButton crearMenuButton(String texto, List<String> opciones) {
        MenuButton menuButton = new MenuButton(texto);
        // Crear una lista de elementos de menú a partir de la lista de opciones dada
        List<MenuItem> menuItems = opciones.stream()
                .map(MenuItem::new)
                .collect(Collectors.toList());
        // Agregar la lista de elementos de menú al botón de menú
        menuButton.getItems().addAll(menuItems);
        menuButton.setId("menu-btn");
        return menuButton;
    }

}
