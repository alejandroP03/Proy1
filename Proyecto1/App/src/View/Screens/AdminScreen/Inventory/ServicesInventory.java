package View.Screens.AdminScreen.Inventory;

import View.Components.Inputs.SelectorInput;
import View.Components.ObjectLists.SpanableObjectsList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class ServicesInventory extends VBox {

    public ServicesInventory() {
        Label title = new Label("Filtros");
        title.getStyleClass().add("filter-title");

        // Filtro habitacion
        SelectorInput availabilityMenu = new SelectorInput("Tipo", new String[] { "Bebida", "Comida" });
        // Filtro caracteristicas
        SelectorInput froGroupMenu = new SelectorInput("Disponibilidad",
                new String[] { "Opción 3", "Opción 5", "Opción 6" });
        // Filtro cama

        // Se agrega a la grilla
        GridPane filterGrid = new GridPane();
        filterGrid.setAlignment(Pos.TOP_CENTER);
        filterGrid.setHgap(50);
        filterGrid.setVgap(10);
        filterGrid.add(title, 0, 0);
        filterGrid.add(availabilityMenu, 0, 1);
        filterGrid.add(froGroupMenu, 1, 1);

        // Grilla de abajo
        SpanableObjectsList servicesList = new SpanableObjectsList(
                new String[] { "Nombre", "Horario", "Disponibilidad", "Para Grupo", "Precio" }, new HBox());
        servicesList.addElem(new Node[] { new Text("Suite 444"), new Button("Hola"), new Text("Balcon"),
                new Text("Piso 4"), new Text("Suite") });
        setAlignment(Pos.TOP_CENTER);
        getChildren().addAll(filterGrid, servicesList);
        setSpacing(20);

    }

}
