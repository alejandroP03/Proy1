package View.Components.PrincipalWindow;

import Controller.Router;
import Model.HotelObjects.UserType;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ReceptionistPrincipalWindow extends PrinicipalWindow {
    Router router;
    VerticalIconText bookingBtn = new VerticalIconText("Crear reserva", "git-button", true);
    VerticalIconText registerBtn = new VerticalIconText("Crear registro", "git-button", false);
    VerticalIconText checkOutBtn = new VerticalIconText("Check-out", "git-button", false);
    VerticalIconText cancelBtn = new VerticalIconText("Cancelar reserva", "git-button", false);
    VerticalIconText selectedScreenBtn = bookingBtn;

    public ReceptionistPrincipalWindow(Router router) {
        super(UserType.RECEPTIONIST, router);
        this.router = router;
    }

    @Override
    public VBox lateralMenu() {
        VBox vbox = new VBox();

        bookingBtn.setOnMouseClicked(e -> {
            if (!bookingBtn.isSelectedStatus(selectedScreenBtn)) {
                router.goToBookingScreen();
                this.selectedScreenBtn = bookingBtn;
            }
        });

        registerBtn.setOnMouseClicked(e -> {
            if (!registerBtn.isSelectedStatus(selectedScreenBtn)) {
                router.goToRegisterForm();
                this.selectedScreenBtn = registerBtn;
            }
        });

        checkOutBtn.setOnMouseClicked(e -> {
            if (!checkOutBtn.isSelectedStatus(selectedScreenBtn)) {
                router.goToCheckOut();
                this.selectedScreenBtn = checkOutBtn;
            }
        });

        cancelBtn.setOnMouseClicked(e -> {
            if (!cancelBtn.isSelectedStatus(selectedScreenBtn)) {
                router.goToCancelBooking();
                this.selectedScreenBtn = cancelBtn;
            }
        });

        vbox.getChildren().addAll(
                bookingBtn,
                registerBtn,
                checkOutBtn,
                cancelBtn);

        vbox.getChildren().addAll();
        VBox pane = new VBox();
        pane.getChildren().add(vbox);
        vbox.setSpacing(35);
        pane.setAlignment(Pos.CENTER);

        return pane;
    }
@Override
    public HBox topMenu(){
        Label room = new Label("Habitacion > ");
        Label date = new Label("Fecha > ");
        Label guest = new Label("Huesped > ");
        Label allData = new Label("Resumen ");

//        date.setStyle("-fx-text-fill: #006D77;");
//        room.setStyle(" -fx-text-fill: grey;");
//        guest.setStyle("-fx-text-fill: grey;");
//        allData.setStyle("-fx-text-fill: grey;");


        date.setOnMouseClicked(e -> {
            router.goToBookingDate();
            date.setStyle("-fx-text-fill: #006D77;");
            room.setStyle(" -fx-text-fill: grey;");
            guest.setStyle("-fx-text-fill: grey;");
            allData.setStyle("-fx-text-fill: grey;");
        });

        room.setOnMouseClicked(e -> {

            //date.setStyle("-fx-text-fill: #006D77;");
            date.setId("active-top-text");
            room.setId("active-top-text");
            guest.setId("inactive-top-text");
            allData.setId("inactive-top-text");

            //room.setStyle(" -fx-text-fill: #006D77;");
//
//            guest.setStyle("-fx-text-fill: grey;");
//            allData.setStyle("-fx-text-fill: grey;");
        });
        guest.setOnMouseClicked(e -> {


            room.setStyle(" -fx-text-fill: #006D77;");
            date.setStyle("-fx-text-fill: #006D77;");
            router.goToFormGuest();
            guest.setStyle("-fx-text-fill: #006D77;");
            allData.setStyle("-fx-text-fill: grey;");
        });
        allData.setOnMouseClicked(e -> {

            router.goToSaveData();
            room.setStyle(" -fx-text-fill: #006D77;");
            date.setStyle("-fx-text-fill: #006D77;");
            guest.setStyle("-fx-text-fill: #006D77;");
            allData.setStyle("-fx-text-fill: #006D77;");
        });

        HBox hBox = new HBox();
        hBox.setSpacing(15);
        hBox.setAlignment(Pos.CENTER);
        hBox.getChildren().addAll(date , room,guest,allData);
        return  hBox;

    }




}
