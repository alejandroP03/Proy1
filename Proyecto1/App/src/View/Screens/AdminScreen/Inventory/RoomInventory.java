package View.Screens.AdminScreen.Inventory;

import View.Components.Inputs.SelectorInput;
import View.Components.ObjectLists.ObjectsList;
import View.Components.ObjectLists.SpanableObjectsList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class RoomInventory extends VBox {

        public RoomInventory() {
                Text title = new Text("Filtros");
                title.getStyleClass().add("filter-title");

                SelectorInput menuType = new SelectorInput("Tipo de habitación", "", "", "",
                                new String[] { "Bebida", "Comida" });

                SelectorInput menuFeatures = new SelectorInput("Caracteristica", "", "", "",
                                new String[] { "Bebida", "Comida" });

                SelectorInput menuBeds = new SelectorInput("Cama", "", "", "", new String[] { "Bebida", "Comida" });

                // Se agrega a la grilla
                GridPane filterGrid = new GridPane();
                filterGrid.setAlignment(Pos.TOP_CENTER);
                filterGrid.setHgap(50);
                filterGrid.setVgap(10);
                filterGrid.add(title, 0, 0);
                filterGrid.add(menuType, 0, 1);
                filterGrid.add(menuFeatures, 1, 1);
                filterGrid.add(menuBeds, 2, 1);

                ObjectsList obj = new SpanableObjectsList(
                                new String[] { "Nombre", "Camas", "Caracteristica", "Lugar", "Tipo" },
                                new HBox());
                // TODO Falta crear el span

                obj.addElem(new Node[] { new Text("Suite 444"), new Button("Hola"), new Text("Balcon"),
                                new Text("Piso 4"),
                                new Text("Suite") });
                setAlignment(Pos.TOP_CENTER);

                getChildren().addAll(filterGrid, obj);
                setSpacing(20);

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

}
