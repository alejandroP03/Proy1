package View.Screens.AuthScreen;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;

public class Auth extends HBox {

    boolean isSignUp = true;
    AuthForm form = new AuthForm(isSignUp);
    StackPane switchAuthContainer = new StackPane();
    Button switchAuthBtn = new Button(!isSignUp ? "Ir al registro" : "Inciar sesión") {
        {
            setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent arg0) {
                    switchAuth();
                }

            });
        }
    };

    public Auth() {
        switchAuthContainer.getChildren().add(switchAuthBtn);
        setId("switch-id-btn");

        getChildren().add(form);
        getChildren().add(switchAuthBtn);
        switchAuthBtn.setAlignment(Pos.CENTER_RIGHT);
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
