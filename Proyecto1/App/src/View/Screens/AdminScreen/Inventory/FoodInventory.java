package View.Screens.AdminScreen.Inventory;

import View.Components.Inputs.SelectorInput;
import View.Components.ObjectLists.ObjectsList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class FoodInventory extends VBox {

        public FoodInventory() {

                Label title = new Label("Filtros");
                title.getStyleClass().add("filter-title");
                SelectorInput menuType = new SelectorInput("Tipo", "", "", "", new String[] { "Bebida", "Comida" });
                SelectorInput menuFeatures = new SelectorInput("Tipo", "", "", "",
                                new String[] { "Desayuno", "Almuerzo", "Cena", "N/A" });

                // Se agrega a la grilla
                GridPane filterGrid = new GridPane();
                filterGrid.setAlignment(Pos.TOP_CENTER);
                filterGrid.setHgap(50);
                filterGrid.setVgap(10);
                filterGrid.add(title, 0, 0);
                filterGrid.add(menuType, 0, 1);
                filterGrid.add(menuFeatures, 1, 1);

                ObjectsList foodList = new ObjectsList(
                                new String[] { "Nombre", "Tipo", "Disponibilidad", "Room Service", "Precio" });
                foodList.addElem(new Node[] { new Text("Suite 444"), new Button("Hola"), new Text("Balcon"),
                                new Text("Piso 4"), new Text("Suite"), new HBox() });

                setAlignment(Pos.TOP_CENTER);
                getChildren().addAll(filterGrid, foodList);
                setSpacing(20);

        }

}
