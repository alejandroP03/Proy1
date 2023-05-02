package View.Screens.AdminScreen;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import Controller.Hotel;
import Model.HotelObjects.RoomRelated.Bed;
import Model.HotelObjects.RoomRelated.RoomFeatures;
import Model.HotelObjects.RoomRelated.TypeRoom;
import View.Components.PrinicipalWindow;
import View.Components.Inputs.InputNumber;
import View.Components.Inputs.InputText;
import View.Components.Inputs.SelectorInput;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class RoomManaging extends VBox {

        public Hotel hotel = Hotel.getInstance();

        public RoomManaging() {

                getStylesheets().add("View/Styles/admin/adminScreens.css");
                PrinicipalWindow pw = new PrinicipalWindow<Pane>("admin", new Pane());
                setVgrow(pw, Priority.ALWAYS);
                Pane mainPane = pw.getMainPane();
                GridPane gridPane = new GridPane();

                // agregar tarjeta de carga
                VBox leftGrid = new VBox();
                leftGrid.setPadding(new Insets(0, 30, 40, 50));
                BorderPane cargaGrid = loadRooms();
                leftGrid.getChildren().add(cargaGrid);

                // Agregar habitaciones sin tarifas
                VBox vboxSinTarifa = new VBox();
                vboxSinTarifa.setPadding(new Insets(0, 0, 40, 50));
                Label titleSintarifa = new Label("Habitaciones sin tarifa");
                GridPane roomNoFare = noRoomsFare();
                vboxSinTarifa.setSpacing(12);
                vboxSinTarifa.getChildren().addAll(titleSintarifa, roomNoFare);

                // Agregar Crear una habitacion
                VBox form = createRoom();
                gridPane.setHgap(20);
                gridPane.setVgap(30);
                gridPane.setPadding(new Insets(20, 15, 20, 25));
                gridPane.add(form, 1, 0, 1, 3);
                gridPane.add(leftGrid, 0, 0);
                gridPane.add(vboxSinTarifa, 0, 1);

                mainPane.getChildren().add(gridPane);

                getChildren().add(pw);

        }

        public BorderPane loadRooms() {
                Label titulo = new Label("Carga un archivo de habitaciones!");
                // Imagen personaje a la derecha
                Image imagen = new Image("View/assets/images/Group 87.png");
                ImageView imageView = new ImageView(imagen);

                // agreagar cajas abajo izquierda
                VBox vboxTextos = new VBox();
                vboxTextos.getChildren().addAll(smallInfoRooms("Agregadas"), smallInfoRooms("Sin tarifas"));
                vboxTextos.setAlignment(Pos.CENTER_LEFT);
                vboxTextos.setSpacing(10);

                // Crear el BorderPane y agregar los elementos
                BorderPane borderPane = new BorderPane();
                borderPane.setId("principal-panel");
                borderPane.setPadding(new Insets(30, 0, 0, 25));
                borderPane.setTop(titulo);
                borderPane.setLeft(vboxTextos);
                borderPane.setRight(imageView);

                return borderPane;
        }

        public GridPane smallInfoRooms(String textGrid) {
                // crear las cajas de abajo
                Image prbImg = new Image("View/assets/images/Group 47.png");
                ImageView prbImgView = new ImageView(prbImg);
                Label texto = new Label(textGrid);
                Label num = new Label("0");

                // Crear el GridPane y ajustar las columnas
                GridPane gridPane = new GridPane();
                gridPane.setPadding(new Insets(2));
                gridPane.setHgap(10);
                gridPane.setVgap(-10);
                gridPane.getColumnConstraints().add(new ColumnConstraints());
                gridPane.getColumnConstraints().add(new ColumnConstraints(140));
                // Agregar la imagen y el texto al GridPane
                gridPane.add(prbImgView, 0, 0);
                gridPane.add(texto, 1, 0);
                gridPane.add(num, 1, 1);
                return gridPane;
        }

        public VBox createRoom() {
                Text title = new Text("Crear una habitacion");

                VBox form = new VBox();
                form.setId("create-room-tarjet");
                form.setSpacing(10);
                form.setPadding(new Insets(30));// agregar espacio entre los nodos
                InputText locationInput = new InputText("Ubicacion", "E-527", "", "person");
                //
                SelectorInput bedSelector = new SelectorInput("Tipo de cama", "Elegir tipo de cama", "person", "",
                                new String[] { "King Plus", "King", "Queen", "Doble", "Simple", "Camarote", "Niños" },
                                Bed.values());
                bedSelector.setId("bed-selector");

                Button addBed = new Button("+");

                InputNumber numOfBeds = new InputNumber("", "Cantidad", 1, 10, 1);

                
                final Map<Bed, Integer> mapBeds = new HashMap<Bed, Integer>();

                final int[] countBed = { 0 };
                addBed.setOnAction(e -> {

                        Bed bed = (Bed) bedSelector.getValue();
                        countBed[0] = Integer.parseInt(numOfBeds.getValue());
                        mapBeds.put(bed, countBed[0]);
                        System.out.println(mapBeds.get(bed));
                        System.out.println(bed.toString());

                });

                Button addFeature = new Button("+");

                SelectorInput featuresSelector = new SelectorInput("Características", "", "", "",
                                new String[] { "Balcón", "Vista al paisaje", "Cocina" }, RoomFeatures.values());

                final Set<RoomFeatures> featuresList = new HashSet<RoomFeatures>();
                addFeature.setOnAction(e -> {
                        RoomFeatures feature = (RoomFeatures) featuresSelector.getValue();
                        featuresList.add(feature);
                        System.out.println(feature);
                        System.out.println(featuresList.toArray().length);

                });

                SelectorInput typeRoomSelector = new SelectorInput("Tipo de habitación", "", "", "",
                                new String[] { "Estandar", "Suite", "Suite doble" }, TypeRoom.values());

                Button addRoom = new Button("Agregar -->");

                final TypeRoom[] typeRoom = { null };
                final String[] location = { "" };

                addRoom.setOnAction(e -> {
                        typeRoom[0] = (TypeRoom) typeRoomSelector.getValue();
                        location[0] = locationInput.getValue();
                        try {
                                hotel.getRoomsHandler().loadPersistentData();
                        } catch (Exception ex) {
                                throw new RuntimeException(ex);
                        }
                        try {
                                hotel.getRoomsHandler().createNewRoom(location[0], false, mapBeds, featuresList,
                                                typeRoom[0]);
                        } catch (Exception ex) {
                                throw new RuntimeException(ex);
                        }
                        try {
                                hotel.getRoomsHandler().SavePersistentData();
                        } catch (IOException ex) {
                                throw new RuntimeException(ex);
                        }

                });

                addBed.setId("button-form");
                addRoom.setId("button-form");
                form.getChildren().addAll(title, locationInput, bedSelector, numOfBeds, addBed, featuresSelector,
                                addFeature, typeRoomSelector, addRoom);

                return form;

        }

        public GridPane noRoomsFare() {
                Label roomName = new Label("Habitacion #00");
                Label roomInfo = new Label("sdklajdkasjdk jkdhsjkadhjkashdjk ajkdh asjk");
                Button addFairBtn = new Button("+");
                addFairBtn.setId("btn-Add-Fair");
                addFairBtn.setOnAction(e -> System.out.println("MAMABICHO"));

                GridPane gridPane = new GridPane();
                gridPane.setId("grid-rooms");
                gridPane.setPadding(new Insets(20));
                gridPane.setHgap(200);
                gridPane.setVgap(-5);
                gridPane.getColumnConstraints().add(new ColumnConstraints());
                gridPane.getColumnConstraints().add(new ColumnConstraints());
                // Agregar la imagen y el texto al GridPane
                gridPane.add(addFairBtn, 1, 0);
                gridPane.add(roomName, 0, 0);
                gridPane.add(roomInfo, 0, 1);

                return gridPane;
        }

}
