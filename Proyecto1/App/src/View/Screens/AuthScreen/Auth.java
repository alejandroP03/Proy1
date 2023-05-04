package View.Screens.AuthScreen;

import Controller.Controller;
import Controller.Router;
import Model.HotelObjects.User;
import Model.HotelObjects.UserType;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;

public class Auth extends HBox {
    boolean isSignUp = true;
    AuthForm form = new AuthForm(isSignUp, this);
    Button switchAuthBtn = new Button(!isSignUp ? "Ir al registro" : "Inciar sesión") {
        {
            setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent arg0) {
                    switchAuth();
                }
            });
            setId("btn-container");
        }
    };
    Controller auth;
    Router router;

    public Auth(Router router, Controller auth) {
        this.auth = auth;
        this.router = router;
        getChildren().add(form);
        getChildren().add(switchAuthContainer);

        setHgrow(form, Priority.ALWAYS);

        setId("auth-container");
        getStylesheets().add("View/Styles/auth.css");
    }

    StackPane switchAuthContainer = new StackPane() {
        {
            getChildren().add(switchAuthBtn);
            setAlignment(Pos.BOTTOM_CENTER);
            setMargin(switchAuthBtn, new Insets(0, 50, 50, 0));
        }
    };

    private void switchAuth() {
        isSignUp = !isSignUp;
        switchAuthBtn.setText(!isSignUp ? "Ir al registro" : "Inciar sesión");
        form.switchForm(isSignUp);
    }

    protected User signUp(String name, String password, UserType userType) throws Exception {
        return auth.signUp(name, password, userType);
    }

    protected boolean userExists(String name, String password) {
        return auth.userExist(name, password);
    }

    protected User signIn(String name, String password) throws Exception {
        return auth.signIn(name, password);
    }

    protected void setUserScene(UserType user) {
        router.setUser(user);
        router.showUserMainScreen();
    }

}
