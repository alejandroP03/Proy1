package View.Screens.AuthScreen;

import View.Components.PrinicipalWindow;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class General extends VBox {

        public General(){
                setId("background-window");
                setPrefSize(800,800);
                setAlignment(Pos.CENTER);
                PrinicipalWindow pw = new PrinicipalWindow();
                setVgrow(pw, Priority.ALWAYS);
                setPadding(new Insets(50));
                getChildren().add(pw);
        }


}
