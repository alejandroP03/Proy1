package Controller;

import Model.HotelObjects.UserType;
import View.Components.PrincipalWindow.AdminPrincipalWindow;
import View.Components.PrincipalWindow.EmployeePrincipalWindow;
import View.Components.PrincipalWindow.PrinicipalWindow;
import View.Components.PrincipalWindow.ReceptionistPrincipalWindow;
import View.Screens.AdminScreen.RoomManaging;
import View.Screens.AdminScreen.Inventory.InventoryScreen;
import View.Screens.AuthScreen.Auth;
import View.Screens.EmployeeScreen.PayService;
import View.Screens.RecepcionistScreen.BookingScreen;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class Router {
    private UserType user;
    private Stage mainStage;
    private Controller controller;
    private PrinicipalWindow pw;

    public ArrayList<String> testListFood = new ArrayList<>();




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

        user = UserType.EMPLOYEE;
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
                switchScreen(new RoomManaging(controller, pw));
                break;
            case RECEPTIONIST:
                pw = new ReceptionistPrincipalWindow(this);
                switchScreen(new BookingScreen(controller, pw));
                break;
            case EMPLOYEE:
                testListFood.add("1");
                pw = new EmployeePrincipalWindow(this, controller);
                switchScreen(new PayService(controller, pw, testListFood, false));
                break;

        }

    }

    private void switchScreen(Parent parent_screen) {
        Scene scene = new Scene(parent_screen);
        scene.getStylesheets().add("View/Styles/font.css");

        mainStage.setScene(scene);
        mainStage.show();
    }

    public void goToRoomManaging() {
        switchScreen(new RoomManaging(controller, pw));
    }

    public void goToInventoryScreen() throws Exception {
        switchScreen(new InventoryScreen(controller, pw));
    }

    public void goToBookingScreen() {
        switchScreen(new BookingScreen(controller, pw));
    }



}
