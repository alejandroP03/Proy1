package View;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application  {

    @Override
    public void start(Stage primaryStage) {

        
        try {

            // Show the scene containing the root layout.
            Label btn = new Label("Hello");
            btn.setId("btn");
            Pane pane = new Pane(btn);
            pane.getStylesheets().add("/Styles/auth.css");
            Scene scene = new Scene(pane);

            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

}
