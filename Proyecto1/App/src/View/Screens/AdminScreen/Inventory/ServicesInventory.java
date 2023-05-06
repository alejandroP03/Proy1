package View.Screens.AdminScreen.Inventory;

import Model.HotelObjects.Service;
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

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Map;

public class ServicesInventory extends VBox {

        public ServicesInventory(Map<Object, Service> servicesInfo) {
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
                ObjectsList servicesList = new ObjectsList(
                                new String[] { "Nombre", "Horario", "Disponibilidad", "Para Grupo", "Precio" });
                

                this.addElements(servicesList, servicesInfo);
                setAlignment(Pos.TOP_CENTER);
                getChildren().addAll(filterGrid, servicesList);
                setSpacing(20);

        }

        private void addElements(ObjectsList obj, Map<Object, Service> servicesInfo){
                for(Map.Entry<Object, Service> serviceInfoEntries : servicesInfo.entrySet()){
                        createServiceNodes(serviceInfoEntries.getValue(), obj);
                }
        }

        private void createServiceNodes(Service service, ObjectsList obj){
                Node[] nodes = serviceNodes(service);

                Pane span = getSpan(service);

                obj.addElem(nodes, span);
        }

        private Pane getSpan(Service service){
                Node[] nodes = serviceNodes(service);

                FlowPane span = new FlowPane(Orientation.HORIZONTAL){
                        {
                                setHgap(30);
                                setVgap(40);
                                setPrefWrapLength(250);
                                getStyleClass().add("span-pane__inventory-item");

                                getChildren().addAll(
                                        spanElem("Nombre", nodes[0]),
                                        spanElem("Horario", nodes[1]),
                                        spanElem("Disponibilidad", nodes[2]),
                                        spanElem("Para grupo", nodes[3]),
                                        spanElem("Precio", nodes[4])

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


        private Node[] serviceNodes(Service service){

                String serviceName = service.getName();
                Text name = new Text(serviceName.substring(0,1).toUpperCase() + serviceName.substring(1).toLowerCase()){
                        {
                                getStyleClass().add("text-service");
                        }
                };

                Text schedule = new Text(service.getInitialTime().toString() + '-' + service.getFinalTime().toString()){
                        {
                                getStyleClass().add("schedule-service");
                        }
                };

                BagdeSet days = new BagdeSet();
                for(DayOfWeek daysWeek : service.getDaysAvailable()){
                        days.addBadge(new Badge(daysWeek.toString(), Badge.BadgeColors.GREEN));

                }

                BagdeSet forGroup = new BagdeSet();
                forGroup.addBadge(new Badge(String.valueOf(service.getIsForGroup()), Badge.BadgeColors.RED));

                BagdeSet price = new BagdeSet();
                price.addBadge(new Badge(String.valueOf(service.getPrice()), Badge.BadgeColors.YELLOW));

                Node[] nodes = new Node[] {name, schedule, days, forGroup, price};

                return nodes;

        }



}
