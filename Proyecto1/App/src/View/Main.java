package View;

import Controller.Controller;
import Controller.Router;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Panel con background color");
        primaryStage.setMinWidth(1125);
        primaryStage.setMinHeight(800);

        new Router(primaryStage, new Controller());
    }

    public static void main(String[] args) {
        launch(args);
    }

}