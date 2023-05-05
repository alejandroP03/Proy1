package View.Screens.AdminScreen.Inventory;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import Model.HotelObjects.RoomRelated.Bed;
import Model.HotelObjects.RoomRelated.Room;
import Model.HotelObjects.RoomRelated.RoomFares;
import Model.HotelObjects.RoomRelated.RoomFeatures;
import Model.HotelObjects.RoomRelated.TypeRoom;
import View.Components.Badge.Badge;
import View.Components.Badge.Badge.BadgeColors;
import View.Components.Badge.BagdeSet;
import View.Components.Calendars.ViewCalendar;
import View.Components.Inputs.SelectorInput;
import View.Components.ObjectLists.ObjectsList;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class RoomInventory extends VBox {

        public RoomInventory(Map<Room, RoomFares> roomsInfo) {

                Text title = new Text("Filtros");
                title.getStyleClass().add("filter-title");

                SelectorInput menuType = new SelectorInput("Tipo de habitación", "", "", "",
                                new String[] { "Estandar", "Suite", "SuiteDoble" }, TypeRoom.values());

                SelectorInput menuFeatures = new SelectorInput("Caracteristica", "", "", "",
                                new String[] { "Balcón", "Vista al paisaje", "Cocina" }, RoomFeatures.values());

                SelectorInput menuBeds = new SelectorInput("Cama", "", "", "",
                                new String[] { "King plus", "King", "Queen", "Doble", "Simple", "Camarote", "Niños",
                                }, Bed.values());

                // Se agrega a la grilla
                GridPane filterGrid = new GridPane();
                filterGrid.setAlignment(Pos.TOP_CENTER);
                filterGrid.setHgap(50);
                filterGrid.setVgap(10);
                filterGrid.add(title, 0, 0);
                filterGrid.add(menuType, 0, 1);
                filterGrid.add(menuFeatures, 1, 1);
                filterGrid.add(menuBeds, 2, 1);

                ObjectsList obj = new ObjectsList(
                                new String[] { "ID Habitación", "Camas", "Caracteristica", "Lugar", "Tipo" });

                this.addElements(obj, roomsInfo);

                getChildren().addAll(filterGrid, obj);
                setSpacing(20);
                setAlignment(Pos.TOP_CENTER);
        }

        public VBox leftBorder() {
                Label title = new Label("Ocupacion");
                VBox container = new VBox();
                container.setPrefSize(200, 530);
                container.setId("ocuppied-container");
                container.getChildren().add(title);
                container.setAlignment(Pos.CENTER);
                return container;
        }

        private void addElements(ObjectsList obj, Map<Room, RoomFares> roomsInfo) {
                for (Map.Entry<Room, RoomFares> roomsInfoEntries : roomsInfo.entrySet()) {
                        createRoomNodes(roomsInfoEntries.getKey(), roomsInfoEntries.getValue(), obj);

                }

        }

        private void createRoomNodes(Room room, RoomFares roomFares, ObjectsList obj) {
                Node[] nodes = new RoomNodes(room, roomFares).getNodes();

                Pane span = getSpan(room, roomFares);

                obj.addElem(nodes, span);
        }

        private Pane getSpan(Room room, RoomFares roomFares) {

                Node[] nodes = new RoomNodes(room, roomFares).getNodes();

                /* name, beds, features, place, type, isFull, bookedDates, datesWithFare */
                FlowPane span = new FlowPane(Orientation.HORIZONTAL) {
                        {
                                setHgap(30);
                                setVgap(40);
                                setPrefWrapLength(250);
                                getStyleClass().add("span-pane__inventory-item");

                                VBox nameCont = new VBox(new Label("ID de habitación"), nodes[0]);
                                nameCont.getStyleClass().add("vbox");
                                VBox bedsCont = new VBox(new Label("Camas"), nodes[1]);
                                bedsCont.getStyleClass().add("vbox");
                                VBox featuresCont = new VBox(new Label("Características"), nodes[2]);
                                featuresCont.getStyleClass().add("vbox");
                                VBox locationCont = new VBox(new Label("ID de habitación"), nodes[3]);
                                locationCont.getStyleClass().add("vbox");
                                VBox typeCont = new VBox(new Label("Tipo de habitación"), nodes[4]);
                                typeCont.getStyleClass().add("vbox");
                                VBox fullCont = new VBox(new Label("Ocupada"), nodes[5]);
                                fullCont.getStyleClass().add("vbox");
                                VBox bookedDatesCont = new VBox(new Label("Fechas reservadas"), nodes[6]);
                                bookedDatesCont.getStyleClass().add("vbox");
                                VBox faredDatesCont = new VBox(new Label("Fechas con tarifa"), nodes[7]);
                                faredDatesCont.getStyleClass().add("vbox");

                                getChildren().addAll(nameCont, locationCont, bedsCont, featuresCont, fullCont,
                                                typeCont, bookedDatesCont, faredDatesCont);

                        }
                };
                return span;
        }

        private class RoomNodes {
                private Node[] nodes;

                public RoomNodes(Room room, RoomFares fare) {
                        String id = room.getRoomId();
                        Text name = new Text(id.substring(0, 1).toUpperCase() + id.substring(1).toLowerCase()) {
                                {
                                        getStyleClass().add("text-room");
                                }
                        };

                        BagdeSet beds = new BagdeSet();
                        for (Map.Entry<Bed, Integer> bedsEntries : room.getBeds().entrySet()) {
                                beds.addBadge(new Badge(bedsEntries.getKey().getBedName() + ": "
                                                + bedsEntries.getValue(), BadgeColors.GREEN));
                        }

                        BagdeSet features = new BagdeSet();

                        for (Object feature : room.getFeaturesList().toArray()) {
                                RoomFeatures feat = (RoomFeatures) feature;
                                features.addBadge(new Badge(feat.getFeatureName(), BadgeColors.YELLOW));
                        }

                        Text place = new Text(room.getLocation()) {
                                {
                                        getStyleClass().add("text-room");

                                }
                        };

                        CheckBox isFull = new CheckBox() {
                                {
                                        setSelected(room.getIsOcupied());
                                        setDisable(true);
                                }
                        };

                        BagdeSet type = new BagdeSet();

                        type.addBadge(new Badge(room.getType().getTypeName(), BadgeColors.RED));

                        List<LocalDate> dates = new ArrayList<LocalDate>();
                        for (Map.Entry<LocalDate, LocalDate> datesEntry : room.getBookedDates().entrySet()) {
                                for (LocalDate initDate = datesEntry.getKey(); initDate
                                                .isBefore(datesEntry.getValue()); initDate = initDate.plusDays(1)) {
                                        dates.add(initDate);
                                }

                        }
                        LocalDate[] datesArr = dates.toArray(new LocalDate[] {});

                        ViewCalendar bookedDates = new ViewCalendar(datesArr);

                        List<LocalDate> datesFares = new ArrayList<LocalDate>();

                        for (LocalDate initDate = LocalDate.now(); initDate
                                        .isBefore(LocalDate.now().plusYears(1)); initDate = initDate.plusDays(1)) {
                                if (fare != null && fare.hasFare(initDate, initDate.plusDays(1)))
                                        datesFares.add(initDate);
                        }

                        LocalDate[] datesFaresArr = datesFares.toArray(new LocalDate[] {});

                        ViewCalendar datesWithFare = new ViewCalendar(datesFaresArr);

                        this.nodes = new Node[] { name, beds, features, place, type, isFull, bookedDates,
                                        datesWithFare };
                }

                public Node[] getNodes() {
                        return nodes;
                }

        }

}
