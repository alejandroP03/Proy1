package View;

import View.Screens.AuthScreen.Auth;
import View.Screens.AuthScreen.ScreenAdmin;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {

        // EntryPoint
        Pane authScreen = new Auth();

        Pane prb = new ScreenAdmin();
        Scene scene = new Scene(prb);

        //Scene scene = new Scene(authScreen);




        scene.getStylesheets().add("View/Styles/components.css");
        primaryStage.setTitle("Panel con background color");


        scene.getStylesheets().add("View/Styles/font.css");




        primaryStage.setMinWidth(1125);
        primaryStage.setMinHeight(800);
        primaryStage.setX(300);
        primaryStage.setY(100);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

}
