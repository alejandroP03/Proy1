package Controller;

import Model.HotelObjects.UserType;
import View.Components.PrincipalWindow.AdminPrincipalWindow;
import View.Components.PrincipalWindow.PrinicipalWindow;
import View.Components.PrincipalWindow.ReceptionistPrincipalWindow;
import View.Screens.AdminScreen.CreateServiceScreen;
import View.Screens.AdminScreen.RoomManaging;
import View.Screens.AdminScreen.Inventory.InventoryScreen;
import View.Screens.AuthScreen.Auth;
import View.Screens.EmployeeScreen.ShowMenu;
import View.Screens.RecepcionistScreen.BookingDate;
import View.Screens.RecepcionistScreen.BookingScreen;
import View.Screens.RecepcionistScreen.CancelBooking;
import View.Screens.RecepcionistScreen.CheckOutScreen;
import View.Screens.RecepcionistScreen.RegisterForm;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Router {
    private UserType user;
    private Stage mainStage;
    private Controller controller;
    private PrinicipalWindow pw;

    public Router(Stage mainStage, Controller controller) {
        this.controller = controller;
        this.mainStage = mainStage;
        switchScreen(new Auth(this, controller));

        // TODO Para probar una pantalla solo agreguen estas lineas con los valores
        // correspondientes
        /*
         * user = UserType.ADMIN;
         * showUserMainScreen();
         */

        user = UserType.ADMIN;
        showUserMainScreen();

    }

    public void setUser(UserType user) {
        this.user = user;
    }

    public UserType getUser() {
        return user;
    }

    public void showUserMainScreen() {
        switch (user) {
            case ADMIN:
                pw = new AdminPrincipalWindow(this, controller);
                    goToRoomManaging();
                break;
            case RECEPTIONIST:
                pw = new ReceptionistPrincipalWindow(this);
                switchScreen(new BookingDate(controller, pw)); // TODO CAMBiar
                break;
            case EMPLOYEE:
                pw = new ReceptionistPrincipalWindow(this);
                switchScreen(new ShowMenu(controller, pw));
                break;

        }

    }

    private void switchScreen(Parent parent_screen) {
        Scene scene = new Scene(parent_screen);

        scene.getStylesheets().add("View/Styles/font.css");

        double mainStageWidth = mainStage.getWidth();
        double mainStageHeight = mainStage.getHeight();
        mainStage.setScene(scene);
        mainStage.setWidth(mainStageWidth);
        mainStage.setHeight(mainStageHeight);
        mainStage.show();
    }

    public void popUp(Parent parent_screen, Parent popUp) {
        Scene scene = new Scene(new StackPane(parent_screen, popUp));

        scene.getStylesheets().add("View/Styles/font.css");

        double mainStageWidth = mainStage.getWidth();
        double mainStageHeight = mainStage.getHeight();
        mainStage.setScene(scene);
        mainStage.setWidth(mainStageWidth);
        mainStage.setHeight(mainStageHeight);
        mainStage.show();
    }

    // ADMIN SCREENS
    public void goToRoomManaging() {
        switchScreen(new RoomManaging(controller, pw, this));
    }

    public void goToServicesManaging() {
        switchScreen(new CreateServiceScreen(controller, pw));
    }

    public void goToInventoryScreen() throws Exception {
        switchScreen(new InventoryScreen(controller, pw, mainStage));
    }

    // prinicipal
    public void goToBookingScreen() {
        switchScreen(new BookingScreen(controller, pw));
    }

    // Tiene reserva??
    public void goToRegisterForm() {
        switchScreen(new RegisterForm(controller, pw));
    }

    // checkout
    public void goToCheckOut() {
        switchScreen(new CheckOutScreen(controller, pw));
    }

    public void goToCancelBooking() {
        switchScreen(new CancelBooking(controller, pw));
    }
}
