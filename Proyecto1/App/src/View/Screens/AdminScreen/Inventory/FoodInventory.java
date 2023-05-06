package View.Screens.AdminScreen.Inventory;

import Model.HotelObjects.Food;
import View.Components.Badge.Badge;
import View.Components.Badge.BagdeSet;
import View.Components.Inputs.SelectorInput;
import View.Components.ObjectLists.ObjectsList;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.text.Text;

import java.util.Map;

public class FoodInventory extends VBox {

        public FoodInventory(Map<Object, Food> foodsInfo) {

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
                                new String[] { "Nombre", "Disponibilidad", "Room Service", "Precio" });



                this.addElements(foodList, foodsInfo);
                setAlignment(Pos.TOP_CENTER);
                getChildren().addAll(filterGrid, foodList);
                setSpacing(20);

        }

        private void addElements(ObjectsList obj, Map<Object, Food> foodsInfo){
                for(Map.Entry<Object, Food> foodInfoEntries : foodsInfo.entrySet()){
                        createFoodNodes(foodInfoEntries.getValue(), obj);
                }


        }

        private void createFoodNodes(Food food, ObjectsList obj){
                Node[] nodes = new FoodNodes(food).getNodes();

                Pane span = getSpan(food);

                obj.addElem(nodes, span);

        }

        private Pane getSpan(Food food){
                Node[] nodes = new FoodNodes(food).getNodes();

                FlowPane span = new FlowPane(Orientation.HORIZONTAL){
                        {
                                setHgap(30);
                                setVgap(40);
                                setPrefWrapLength(250);
                                getStyleClass().add("span-pane__inventory-item");

                                getChildren().addAll(
                                        spanElem("Nombre", nodes[0]),
                                        spanElem("Disponibilidad", nodes[1]),
                                        spanElem("Room service", nodes[2]),
                                        spanElem("Precio", nodes[3])

                                );
                        }
                };

                return span;
        }

        private VBox spanElem(String label, Node node) {
                VBox sp = new VBox();
                sp.getChildren().addAll(new Label(label), node);
                sp.getStyleClass().add("vbox");

                return sp;

        }

        private class FoodNodes {
                private Node[] nodes;

                public FoodNodes(Food food){
                        String id = food.getName();

                        Text name = new Text(id.substring(0,1).toUpperCase() + id.substring(1).toLowerCase()){
                                {
                                        getStyleClass().add("text-food");
                                }
                        };

                        BagdeSet disponibility = new BagdeSet();
                        disponibility.addBadge(new Badge(food.getAvailability(), Badge.BadgeColors.GREEN));

                        BagdeSet roomService = new BagdeSet();
                        roomService.addBadge(new Badge((String.valueOf(food.getIsRoomService())),Badge.BadgeColors.YELLOW));

                        BagdeSet price = new BagdeSet();
                        price.addBadge(new Badge((String.valueOf(food.getPrice())), Badge.BadgeColors.RED));

                        this.nodes = new Node[] {name, disponibility, roomService, price};

                }

                public Node[] getNodes() {
                        return nodes;
                }
        }




}
