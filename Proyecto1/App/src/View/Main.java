package View;

import Controller.Controller;
import View.Components.PrincipalWindow.AdminPrincipalWindow;
import View.Components.PrincipalWindow.PrinicipalWindow;
import View.Screens.AdminScreen.RoomManaging;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Panel con background color");
        primaryStage.setMinWidth(1125);
        primaryStage.setMinHeight(800);
        Controller controller = new Controller();
        // new Router(primaryStage, controller);
        PrinicipalWindow pw = new AdminPrincipalWindow();
        primaryStage.setScene(new Scene(new RoomManaging(controller, pw)));

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
