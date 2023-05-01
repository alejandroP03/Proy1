package View.Screens.AuthScreen;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;

public class Auth extends HBox {

    boolean isSignUp = true;
    AuthForm form = new AuthForm(isSignUp);
    Button switchAuthBtn = new Button(!isSignUp ? "Ir al registro" : "Inciar sesión") {
        {
            setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent arg0) {
                    switchAuth();
                }

            });
            setId("btn-container");
        }
    };

    StackPane switchAuthContainer = new StackPane() {
        {
            getChildren().add(switchAuthBtn);
            setAlignment(Pos.BOTTOM_CENTER);
            setMargin(switchAuthBtn, new Insets(0, 50, 50, 0));
        }
    };

    public Auth() {

        getChildren().add(form);
        getChildren().add(switchAuthContainer);

        setHgrow(form, Priority.ALWAYS);

        setId("auth-container");
        getStylesheets().add("View/Styles/auth.css");
    }

    private void switchAuth() {
        isSignUp = !isSignUp;
        switchAuthBtn.setText(!isSignUp ? "Ir al registro" : "Inciar sesión");
        form.switchForm(isSignUp);

    }

}
