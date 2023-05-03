package View;

import Controller.Controller;
import Controller.Router;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Panel con background color");
        primaryStage.setMinWidth(1125);
        primaryStage.setMinHeight(800);
        Controller controller = new Controller();
        new Router(primaryStage, controller);
    }

    public static void main(String[] args) {
        launch(args);
    }

}
