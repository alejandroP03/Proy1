package View.Screens.AdminScreen.Inventory;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import Controller.Controller;
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
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class RoomInventory extends VBox {
        Controller controller;

        public RoomInventory(Map<Room, RoomFares> roomsInfo, Controller controller) {
                this.controller = controller;
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
                Label title = new Label("Ocupación");
                title.getStyleClass().add("ocupation-title");
                VBox container = new VBox();
                container.setSpacing(20);
                container.setPadding(new Insets(20));
                container.setPrefSize(200, 530);
                container.setId("ocuppied-container");
                container.getChildren().addAll(title, gridDays());
                container.setAlignment(Pos.TOP_CENTER);
                return container;
        }

        private GridPane gridDays() {
                GridPane grid = new GridPane();
                grid.setHgap(7);
                grid.setVgap(7);
                grid.setAlignment(Pos.CENTER);
                grid.setId("grid-days");

                int[] ocupation = controller.getDayOcupation();
                grid.add(new Label("L") {
                        {
                                setId("day-label");
                        }
                }, 1, 0);
                grid.add(new Label("I") {
                        {
                                setId("day-label");
                        }
                }, 3, 0);
                grid.add(new Label("V") {
                        {
                                setId("day-label");
                        }
                }, 5, 0);
                grid.add(new Label("D") {
                        {
                                setId("day-label");
                        }
                }, 7, 0);

                int i = 1;
                Month month = LocalDate.now().getMonth();
                for (LocalDate date = LocalDate.now(); date.isBefore(LocalDate.now().plusDays(365)); date = date
                                .plusDays(1)) {
                        int dayOcupation = ocupation[i - 1];
                        Button day = new Button(dayOcupation + "");
                        Tooltip tooltip = new Tooltip(date.toString() + "\n" + dayOcupation + " habitaciones ocupadas");
                        Tooltip.install(day, tooltip);
                        day.getStyleClass().add("day-button");
                        int totalRooms = controller.getRoomsCount()[1];
                        // Get percentage of ocupation nearest to (10 * (2n + 1))%
                        int percentage = (int) Math.round((dayOcupation * 100.0) / totalRooms);

                        if (1 < percentage && percentage <= 10)
                                day.getStyleClass().add("ocup" + 10 + "pct");
                        else if (10 < percentage && percentage <= 30)
                                day.getStyleClass().add("ocup" + 30 + "pct");
                        else if (30 < percentage && percentage <= 50)
                                day.getStyleClass().add("ocup" + 50 + "pct");
                        else if (50 < percentage && percentage <= 70)
                                day.getStyleClass().add("ocup" + 70 + "pct");
                        else if (70 < percentage && percentage <= 90)
                                day.getStyleClass().add("ocup" + 90 + "pct");
                        else if (percentage > 90)
                                day.getStyleClass().add("ocup" + 100 + "pct");

                        grid.add(day, (i % 7) + 1, (i++ / 7) + 1);
                        if (date.getMonth() == month) {
                                Label monthLabel = new Label(date.getMonth().toString().substring(0, 3));
                                monthLabel.setId("day-label");
                                monthLabel.setMinWidth(40);

                                grid.add(monthLabel, 0, (i / 7) + 1);
                                month = date.getMonth().plus(1);

                        }

                }

                return grid;
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

                                getChildren().addAll(
                                                new SpanElem("ID de habitación", nodes[0]),
                                                new SpanElem("Lugar", nodes[3]),
                                                new SpanElem("Camas", nodes[1]),
                                                new SpanElem("Características", nodes[2]),
                                                new SpanElem("Ocupada", nodes[5]),
                                                new SpanElem("Tipo de habitación", nodes[4]),
                                                new SpanElem("Fechas reservadas", nodes[6]),
                                                new SpanElem("Fechas con tarifa", nodes[7]));

                        }
                };
                return span;
        }

        private class SpanElem extends VBox {
                public SpanElem(String label, Node node) {
                        getChildren().addAll(new Label(label), node);
                        getStyleClass().add("vbox");
                }
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
