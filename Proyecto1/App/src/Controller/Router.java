package Controller;

import Model.HotelObjects.UserType;
import View.Screens.AdminScreen.RoomManaging;
import View.Screens.AuthScreen.Auth;
import View.Screens.RecepcionistScreen.BookingScreen;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Router {
    private UserType user;
    private Stage mainStage;
    private Controller controller;

    public Router(Stage mainStage, Controller controller) {
        this.controller = controller;
        this.mainStage = mainStage;
        switchScreen(new Auth(this, controller));

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
                switchScreen(new RoomManaging(controller));
                break;
            case RECEPTIONIST:
                switchScreen(new BookingScreen());
                break;
            case EMPLOYEE:
                switchScreen(new VBox(new Text("El que hizo pagar servicios lo hizo mal")));
                break;

        }

    }

    public void switchScreen(Parent parent_screen) {
        Scene scene = new Scene(parent_screen);
        mainStage.setScene(scene);
        mainStage.show();
    }

}
