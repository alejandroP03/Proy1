package Controller;

import Model.HotelObjects.UserType;
import View.Screens.AuthScreen.Auth;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Router {
    UserType user;
    Scene scene;

    public Router(Stage mainStage, Controller controller) {
        if (user == null) {
            
            Scene auth = new Scene(new Auth(this, controller));
            mainStage.setScene(auth);
            mainStage.show();
        }
    }

}
