package View;

import Controller.Controller;
import View.Components.PrinicipalWindow;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Panel con background color");
        primaryStage.setMinWidth(1125);
        primaryStage.setMinHeight(800);
        Controller controller = new Controller();
        // new Router(primaryStage, controller);
        primaryStage.setScene(new Scene(new PrinicipalWindow<VBox>("admin", new VBox())));

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
