package View;

import Controller.Controller;
import Controller.Router;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Hotel PMS");
        primaryStage.setMinWidth(1500);
        primaryStage.setMinHeight(900);
        primaryStage.setX(100);
        primaryStage.setY(100);

        new Router(primaryStage, new Controller());
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
