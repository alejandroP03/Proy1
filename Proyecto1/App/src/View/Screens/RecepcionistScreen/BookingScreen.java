package View.Screens.RecepcionistScreen;

import Controller.Controller;
import Model.HotelObjects.RoomRelated.Room;
import Model.HotelObjects.RoomRelated.RoomFeatures;
import View.Components.Inputs.SelectorInput;
import View.Components.ObjectLists.ObjectsList;
import View.Components.PrincipalWindow.PrinicipalWindow;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class BookingScreen extends VBox {

    Controller controller;

    public BookingScreen(Controller controller, PrinicipalWindow prinicipalWindow) {
        getStylesheets().add("View/Styles/recepcionist/recepcionistScreens.css");
        this.controller = controller;
        Pane mainPane = new VBox();
        prinicipalWindow.setContent(mainPane);

        setVgrow(prinicipalWindow, Priority.ALWAYS);

        VBox vbox = new VBox();
        //vbox.getChildren().add(titleMenu("sapo"));
        vbox.getChildren().add(filtersForm());
        vbox.getChildren().add(gridInfo());
        vbox.setSpacing(50);
        vbox.setPadding(new Insets(30, 80, 40, 80));
        vbox.setAlignment(Pos.CENTER);

        mainPane.getChildren().add(vbox);
        getChildren().add(prinicipalWindow);

    }

    public Label titleMenu(String page) {
        Label rooms = new Label("Habitaciones  > Fecha > Huesped > Resumen");
        return rooms;
    }

    public GridPane filtersForm() {
        Label title = new Label("Filtros");
        title.setId("title-filter");
        SelectorInput typeRoom = new SelectorInput("Tipo habitacion", "", "", "Suite doble",
                new String[] { "opcion 1", "opcion 2", "opcion 3", "N/A" });
        SelectorInput feature = new SelectorInput("Caracteristica", "", "", "Balcon",
                new String[] { "opcion 1", "opcion 2", "opcion 3", "N/A" });
        SelectorInput bed = new SelectorInput("Cama", "", "", "King",
                new String[] { "opcion 1", "opcion 2", "opcion 3", "N/A" });
        typeRoom.setId("input-recepcionist-filters");
        feature.setId("input-recepcionist-filters");
        bed.setId("input-recepcionist-filters");

        GridPane gridPane = new GridPane();
        gridPane.add(title, 0, 0);
        gridPane.add(typeRoom, 0, 1);
        gridPane.add(feature, 1, 1);
        gridPane.add(bed, 2, 1);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(20);
        gridPane.setVgap(10);

        return gridPane;

    }
    public ScrollPane gridInfo() {
        ObjectsList roomList = new ObjectsList(
                new String[] { "Nombre", "Camas", "Caracteristicas", "Lugar", "Tipo", "" });
        addElements(roomList);
        return roomList;
    }

    public void addElements(ObjectsList roomList){
        ArrayList<Room> freeRooms =  controller.freeRooms();
        List<String> selectedRoomsIds = new ArrayList<String>();
        for (Room availableRoom : freeRooms) {
            String features = "";
            for (RoomFeatures feature : availableRoom.getFeaturesList()) {
                features += feature.getFeatureName();
                features += ", ";
            }
            roomList.addElem(new Node[] { new Label(availableRoom.getRoomId()), new Label("King 1"), new Label(features),
                    new Label(availableRoom.getLocation()), new Label(availableRoom.getType().getTypeName()), adderBtn(availableRoom.getRoomId(), selectedRoomsIds)
            });
        }
    }
    public Button adderBtn(String idRoom,  List<String> selectedRoomsIds){
        Button addRoom = new Button("+");
        addRoom.setOnMouseClicked(e -> {
            selectedRoomsIds.add(idRoom);
            //System.out.println(idRoom);
        });

        return addRoom;

    }

}
