package View.Screens.AdminScreen;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import Controller.Controller;
import Model.HotelObjects.RoomRelated.Bed;
import Model.HotelObjects.RoomRelated.RoomFeatures;
import Model.HotelObjects.RoomRelated.TypeRoom;
import View.Components.Inputs.InputNumber;
import View.Components.Inputs.InputText;
import View.Components.Inputs.SelectorInput;
import View.Components.PrincipalWindow.PrinicipalWindow;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class RoomManaging extends VBox {

	public RoomManaging(Controller controller, PrinicipalWindow prinicipalWindow) {

		getStylesheets().add("View/Styles/admin/adminScreens.css");
		setVgrow(prinicipalWindow, Priority.ALWAYS);
		BorderPane mainScreen = new BorderPane();
		prinicipalWindow.setContent(mainScreen);
		getChildren().add(prinicipalWindow);

		VBox centerElem = new VBox(loadRooms(), noFaresContainer());
		BorderPane.setMargin(centerElem, new Insets(20));

		centerElem.setSpacing(20);

		mainScreen.setCenter(centerElem);
		mainScreen.setRight(createRoom(controller));

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

	public VBox createRoom(Controller controller) {
		VBox form = new VBox();
		form.setMaxHeight(670);
		form.setAlignment(Pos.TOP_LEFT);
		form.setPadding(new Insets(30));
		form.setId("create-room-tarjet");

		Text title = new Text("Crear una habitacion");
		title.setId("title");

		InputText locationInput = new InputText("Ubicacion", "Piso 3", "Ubicación física", "map");
		//
		SelectorInput bedSelector = new SelectorInput("Tipo de cama", "Elegir tipo de cama", "tag", "",
				new String[] { "King Plus", "King", "Queen", "Doble", "Simple", "Camarote", "Niños" },
				Bed.values());
		bedSelector.setId("bed-selector");

		InputNumber numOfBeds = new InputNumber("", "Cantidad", 1, 10, 1);

		Button addBed = new Button();
		Map<Bed, Integer> mapBeds = new HashMap<Bed, Integer>();
		addBed.setOnAction(e -> {
			Bed bed = (Bed) bedSelector.getValue();
			mapBeds.put(bed, Integer.parseInt(numOfBeds.getValue()));
		});

		HBox bedContainerInputs = new HBox(bedSelector, numOfBeds);
		bedContainerInputs.setSpacing(10);
		VBox bedContainer = new VBox(bedContainerInputs, addBed);

		SelectorInput featuresSelector = new SelectorInput("Características", "Puede seleccionar más de una", "", "",
				new String[] { "Balcón", "Vista al paisaje", "Cocina" }, RoomFeatures.values());

		Button addFeature = new Button();

		Set<RoomFeatures> featuresList = new HashSet<RoomFeatures>();
		addFeature.setOnAction(e -> {
			RoomFeatures feature = (RoomFeatures) featuresSelector.getValue();
			featuresList.add(feature);
			System.out.println(feature);
			System.out.println(featuresList.toArray().length);

		});

		VBox featuresContainer = new VBox(featuresSelector, addFeature);

		SelectorInput typeRoomSelector = new SelectorInput("Tipo de habitación", "Elegir tipo", "", "",
				new String[] { "Estandar", "Suite", "Suite doble" }, TypeRoom.values());

		Button addRoom = new Button("Agregar");

		addRoom.setOnAction(e -> {

			TypeRoom typeRoom = (TypeRoom) typeRoomSelector.getValue();
			String location = locationInput.getValue();

			try {
				controller.createRoom(location, mapBeds, featuresList, typeRoom);
			} catch (Exception ex) {
				System.out.println(ex.getMessage());
			}

		});

		addBed.setId("button-form");
		addFeature.setId("button-form");
		addRoom.setId("button-form-add-room");

		form.getChildren().addAll(title, locationInput, bedContainer, featuresContainer, typeRoomSelector, addRoom);

		form.setSpacing(30);

		return form;

	}

	public VBox noFaresContainer() {
		VBox container = new VBox();
		container.setSpacing(10);

		Label titleNoFare = new Label("Habitaciones sin tarifa");
		titleNoFare.setId("title-no-fare");

		FlowPane container_cards = new FlowPane();
		container_cards.setPrefWrapLength(800);
		container_cards.setHgap(10);
		container_cards.setVgap(10);
		container_cards.getChildren().addAll(noFareRoomBox(),
				noFareRoomBox(),
				noFareRoomBox(),
				noFareRoomBox(),
				noFareRoomBox(),
				noFareRoomBox(),
				noFareRoomBox(),
				noFareRoomBox(),
				noFareRoomBox(),
				noFareRoomBox(),
				noFareRoomBox()

		);

		container.getChildren().addAll(titleNoFare, container_cards);

		return container;
	}

	public BorderPane noFareRoomBox() {
		BorderPane noFareCard = new BorderPane();
		noFareCard.setId("no-fare-card");
		noFareCard.setMaxSize(500, 80);
		noFareCard.setMinSize(500, 80);
		noFareCard.setPadding(new Insets(10));

		Label roomName = new Label("Habitaciones #1");
		roomName.setId("room-name");

		Label roomInfo = new Label("id s7, suite, Balcony, capacidad 10");
		roomInfo.setId("room-info");
		VBox.setVgrow(roomInfo, Priority.ALWAYS);

		VBox info = new VBox(roomName, roomInfo);
		info.setAlignment(Pos.CENTER_LEFT);

		Button addFairBtn = new Button();
		addFairBtn.setOnAction(e -> System.out.println("MAMABICHO"));

		BorderPane.setAlignment(info, Pos.CENTER_LEFT);
		BorderPane.setAlignment(addFairBtn, Pos.CENTER_RIGHT);

		noFareCard.setLeft(info);
		noFareCard.setRight(addFairBtn);

		return noFareCard;
	}

}
