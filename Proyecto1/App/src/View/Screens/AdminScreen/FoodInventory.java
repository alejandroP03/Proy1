package View.Screens.AdminScreen;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import View.Components.PrinicipalWindow;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class FoodInventory extends VBox  {

    public FoodInventory(){

        getStylesheets().add("View/Styles/admin/adminScreens.css");
        
        PrinicipalWindow pw = new PrinicipalWindow("admin");
        setVgrow(pw, Priority.ALWAYS);
        
        Pane mainPane = pw.getMainPane();
        BorderPane borderPane = new BorderPane();


        //Agregar titulo y boton habitaciones
        StackPane hBox = topBorder();
        BorderPane.setMargin(hBox, new Insets(0, 40, 50, 330));
        borderPane.setTop(hBox);

        // Agregar informacion del centro
        VBox centerInfo = topCenterBorder();
        borderPane.setCenter(centerInfo);


        mainPane.getChildren().add(borderPane);
        getChildren().add(pw);


    }

    public StackPane topBorder(){
        javafx.scene.control.Label title = new Label("Inventario");
        title.setId("inventory-title");
        MenuButton menuButton = crearMenuButton("Comidas y bebidas", Arrays.asList("Opción 7", "Opción 8", "Opción 9"));
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

    public VBox topCenterBorder(){
        Label title = new Label("Filtros");

        // Filtro habitacion
        Label typeFood = new Label("Tipo");
        MenuButton menuType = crearMenuButton("Bebida", Arrays.asList("Bebida", "Comida"));
        // Filtro caracteristicas
        Label availability = new Label("Disponibilidad");
        MenuButton menuFeatures= crearMenuButton("Balcon", Arrays.asList("Opción 3", "Opción 5", "Opción 6"));
        // Filtro cama

        //Se agrega  a la grilla
        GridPane filterGrid = new GridPane();
        filterGrid.setHgap(50);
        filterGrid.setVgap(15);
        filterGrid.setPadding(new Insets(20, 15, 20, 300));
        filterGrid.add(title,0,0);
        filterGrid.add(typeFood,0,1);
        filterGrid.add(menuType,0,2);
        filterGrid.add(availability,1,1);
        filterGrid.add(menuFeatures,1,2);

        //Grilla de abajo
        GridPane infoGrid = new GridPane();
        Label name = new Label("Nombre");
        Label type = new Label("Tipo");
        Label availabilityInfo = new Label("Disponibilidad");
        Label isService = new Label("Room Service");
        Label price = new Label("Precio");
        infoGrid.setHgap(50);
        infoGrid.setVgap(15);
        infoGrid.setPadding(new Insets(20, 15, 20, 200));
        infoGrid.add(name,0,0);
        infoGrid.add(type,1,0);
        infoGrid.add(availabilityInfo,2,0);
        infoGrid.add(isService,3,0);
        infoGrid.add(price,4,0);
        addInfo(infoGrid,"Hamburguesa","comida","almuerzo", "Si", "Almuerzo");

        VBox topCenter = new VBox();
        topCenter.getChildren().addAll(filterGrid,infoGrid);
        topCenter.setSpacing(20);

        return topCenter;

    }
    public void addInfo(GridPane infoGrid, String name, String type, String availability, String isService, String price){
        // TODO cuando se anada rows debe incrementar en 1 para que se anada otra finla
        int rows = 1;
        Label nameInfo = new Label(name);
        Label typeInfo = new Label(type);
        Label availabilityInfo = new Label(availability);
        Label isServiceInfo = new Label(isService);
        Label priceInfo = new Label(price);
        infoGrid.add(nameInfo, 0,rows);
        infoGrid.add(typeInfo,1,rows);
        infoGrid.add(availabilityInfo,2,rows);
        infoGrid.add(isServiceInfo,3,rows);
        infoGrid.add(priceInfo,4,rows);
        rows++;
    }


    public MenuButton crearMenuButton(String texto, java.util.List<String> opciones) {
        MenuButton menuButton = new MenuButton(texto);
        // Crear una lista de elementos de menú a partir de la lista de opciones dada
        List<javafx.scene.control.MenuItem> menuItems = opciones.stream()
                .map(MenuItem::new)
                .collect(Collectors.toList());
        // Agregar la lista de elementos de menú al botón de menú
        menuButton.getItems().addAll(menuItems);
        menuButton.setId("menu-btn");
        return menuButton;
    }
}
