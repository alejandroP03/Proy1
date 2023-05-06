package View.Screens.AdminScreen;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import Controller.Controller;
import Controller.Router;
import Model.HotelObjects.RoomRelated.Bed;
import Model.HotelObjects.RoomRelated.Room;
import Model.HotelObjects.RoomRelated.RoomFeatures;
import Model.HotelObjects.RoomRelated.TypeRoom;
import View.Components.Badge.Badge;
import View.Components.Badge.Badge.BadgeColors;
import View.Components.Badge.BagdeSet;
import View.Components.Inputs.InputDate;
import View.Components.Inputs.InputDays;
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
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class RoomManaging extends VBox {
	Controller controller;
	PrinicipalWindow prinicipalWindow;
	Router router;

	public RoomManaging(Controller controller, PrinicipalWindow prinicipalWindow, Router router) {
		this.controller = controller;
		this.prinicipalWindow = prinicipalWindow;
		this.router = router;

		getStylesheets().add("View/Styles/admin/adminScreens.css");
		setVgrow(prinicipalWindow, Priority.ALWAYS);
		BorderPane mainScreen = new BorderPane();
		prinicipalWindow.setContent(mainScreen);
		getChildren().add(prinicipalWindow);

		VBox centerElem = new VBox(loadRooms(), noFaresContainer());
		BorderPane.setMargin(centerElem, new Insets(20));

		centerElem.setSpacing(20);

		mainScreen.setCenter(centerElem);
		mainScreen.setRight(createRoom());

	}

	public BorderPane loadRooms() {
		Label title = new Label("Carga un archivo de habitaciones!");
		title.setId("title-load-rooms");
		// Imagen personaje a la derecha
		Image imagen = new Image("View/assets/images/Group 87.png");
		ImageView imageView = new ImageView(imagen);

		// agreagar cajas abajo izquierda
		VBox vboxTextos = new VBox();
		Integer[] nums = controller.getRoomsCount();
		vboxTextos.getChildren().addAll(smallInfoRooms("Agregadas", nums[1]), smallInfoRooms("Sin tarifas", nums[0]));
		vboxTextos.setAlignment(Pos.CENTER_LEFT);
		vboxTextos.setSpacing(10);

		// Crear el BorderPane y agregar los elementos
		BorderPane borderPane = new BorderPane();
		borderPane.setId("principal-panel");
		borderPane.setPadding(new Insets(30, 0, 0, 25));
		borderPane.setTop(title);
		borderPane.setLeft(vboxTextos);
		borderPane.setRight(imageView);

		return borderPane;
	}

	public HBox smallInfoRooms(String textGrid, int numGrid) {
		// crear las cajas de abajo
		HBox box = new HBox();
		VBox boxText = new VBox();

		Image prbImg = new Image("View/assets/images/Group 47.png");
		ImageView prbImgView = new ImageView(prbImg);
		Label texto = new Label(textGrid);
		texto.setId("text-small-info");
		Label num = new Label(numGrid + "");
		num.setId("num-small-info");

		boxText.getChildren().addAll(texto, num);
		boxText.setAlignment(Pos.CENTER_LEFT);
		boxText.setSpacing(5);
		box.getChildren().addAll(prbImgView, boxText);
		box.setSpacing(10);

		return box;
	}

	public VBox createRoom() {
		VBox form = new VBox();
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
		BagdeSet bedBagdeSet = new BagdeSet();
		bedBagdeSet.setMaxWidth(300);
		bedBagdeSet.setPadding(new Insets(10));

		Button addBed = new Button();
		Map<Bed, Integer> mapBeds = new HashMap<Bed, Integer>();
		addBed.setOnMouseClicked(e -> {
			Bed bedToAdd = (Bed) bedSelector.getValue();
			Integer numToAdd = Integer.parseInt(numOfBeds.getValue());
			bedBagdeSet.clear();
			for (Map.Entry<Bed, Integer> bedEntry : mapBeds.entrySet()) {
				Bed bed = bedEntry.getKey();
				Integer num = bedEntry.getValue();
				bedBagdeSet.addBadge(new Badge(bed.getBedName() + ": " + num, BadgeColors.GREEN));
			}
			mapBeds.put(bedToAdd, numToAdd);
		});

		HBox bedContainerInputs = new HBox(bedSelector, numOfBeds);
		bedContainerInputs.setSpacing(10);
		VBox bedContainer = new VBox(bedContainerInputs, addBed, bedBagdeSet);

		SelectorInput featuresSelector = new SelectorInput("Características", "Puede seleccionar más de una", "", "",
				new String[] { "Balcón", "Vista al paisaje", "Cocina" }, RoomFeatures.values());

		Button addFeature = new Button();

		BagdeSet featureBadgeSet = new BagdeSet();
		featureBadgeSet.setMaxWidth(300);
		featureBadgeSet.setPadding(new Insets(10));
		Set<RoomFeatures> featuresList = new HashSet<RoomFeatures>();
		addFeature.setOnMouseClicked(e -> {
			RoomFeatures feature = (RoomFeatures) featuresSelector.getValue();
			featuresList.add(feature);
			for (RoomFeatures featureEntry : featuresList) {
				featureBadgeSet.addBadge(new Badge(featureEntry.getFeatureName(), BadgeColors.YELLOW));
			}
		});

		VBox featuresContainer = new VBox(featuresSelector, addFeature, featureBadgeSet);

		SelectorInput typeRoomSelector = new SelectorInput("Tipo de habitación", "Elegir tipo", "", "",
				new String[] { "Estandar", "Suite", "Suite doble" }, TypeRoom.values());

		Button addRoom = new Button("Agregar");

		addRoom.setOnMouseClicked(e -> {

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

		controller.getNoFareRooms().forEach(room -> {
			container_cards.getChildren().add(noFareRoomBox(room));
		});

		container.getChildren().addAll(titleNoFare, container_cards);

		return container;
	}

	public BorderPane noFareRoomBox(Room room) {
		BorderPane noFareCard = new BorderPane();
		noFareCard.setId("no-fare-card");
		noFareCard.setMaxSize(500, 80);
		noFareCard.setMinSize(500, 80);
		noFareCard.setPadding(new Insets(15));

		Label roomName = new Label(
				"Habitaciones #" + room.getRoomId().split("_")[room.getRoomId().split("_").length - 1]);
		roomName.setId("room-name");

		String featuresList = "";

		for (RoomFeatures feature : room.getFeaturesList()) {
			featuresList = feature.getFeatureName() + ", ";
		}

		String bedsList = "";
		for (Map.Entry<Bed, Integer> bedEntry : room.getBeds().entrySet()) {
			Bed bed = bedEntry.getKey();
			Integer num = bedEntry.getValue();
			bedsList = bed.getBedName() + ": " + num + ", ";
		}

		Label roomInfo = new Label(room.getType().getTypeName() + ", " + featuresList + " " + bedsList);

		roomInfo.setId("room-info");
		VBox.setVgrow(roomInfo, Priority.ALWAYS);

		VBox info = new VBox(roomName, roomInfo);
		info.setAlignment(Pos.CENTER_LEFT);

		Button addFairBtn = new Button();
		addFairBtn.setOnMouseClicked(e -> {
			router.popUp(this, popUpFare(room));
		});

		BorderPane.setAlignment(info, Pos.CENTER_LEFT);
		BorderPane.setAlignment(addFairBtn, Pos.CENTER_RIGHT);

		noFareCard.setLeft(info);
		noFareCard.setRight(addFairBtn);

		return noFareCard;
	}

	private VBox popUpFare(Room room) {
		VBox popUpContainer = new VBox();
		popUpContainer.getStylesheets().add("View/Styles/admin/popup.css");
		popUpContainer.getStylesheets().add("View/Styles/components/inputs.css");
		popUpContainer.setId("pop-up-container");

		VBox popUp = new VBox();
		popUp.setId("pop-up");
		popUp.setSpacing(10);
		popUp.setPadding(new Insets(20));
		popUp.setAlignment(Pos.CENTER);

		HBox close = new HBox();
		close.setId("close-pop-up");
		close.setAlignment(Pos.TOP_RIGHT);

		Button closeBtn = new Button();
		closeBtn.setId("close-btn");
		closeBtn.setOnMouseClicked(e -> {
			router.goToRoomManaging();
		});
		close.getChildren().add(closeBtn);

		Text title = new Text("Agregar tarifa");
		title.setId("pop-up-title");

		Text subTitle = new Text("Agrega una nueva tarifa para la habitacion #" + room.getRoomId().split("_")[1]);
		subTitle.setId("pop-up-subtitle");

		InputText fareInput = new InputText("Tarifa", "Ingrese la tarifa", "", "buy");
		fareInput.setId("input-text");

		HBox datesBox = new HBox();
		datesBox.setAlignment(Pos.CENTER);
		datesBox.setSpacing(20);

		InputDate dateFrom = new InputDate("Fecha inicial", "", "", "03/08/2023");
		dateFrom.setId("input-date");

		InputDate dateTo = new InputDate("Fecha final", "", "", "12/09/2023");
		dateTo.setId("input-date");

		InputDays daysInput = new InputDays("Días para ocupar");
		daysInput.setId("input-days");

		Button addFareBtn = new Button("Agregar");
		addFareBtn.setId("button-form");
		setMargin(addFareBtn, new Insets(10));

		addFareBtn.setOnMouseClicked(e -> {
			try {

				controller.createFare(room.createTypeRoomId(), Float.parseFloat(fareInput.getValue()),
						(LocalDate) dateFrom.getValue(), (LocalDate) dateTo.getValue(), new ArrayList<DayOfWeek>(
								Arrays.asList(daysInput.getValue())));
				router.goToRoomManaging();
			} catch (Exception ex) {
				System.out.println(ex.getStackTrace());
				System.out.println(ex.getMessage());
			}
		});

		popUp.setAlignment(Pos.TOP_CENTER);
		popUp.setSpacing(20);

		datesBox.getChildren().addAll(dateFrom, dateTo);
		popUp.getChildren().addAll(close, title, subTitle, fareInput, datesBox, daysInput, addFareBtn);
		popUpContainer.getChildren().add(popUp);

		return popUpContainer;
	}

}
