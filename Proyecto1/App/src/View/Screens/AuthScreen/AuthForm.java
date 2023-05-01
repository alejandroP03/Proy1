package View.Screens.AuthScreen;

import View.Components.InputPassword;
import View.Components.InputText;
import View.Components.SelectorInput;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class AuthForm extends VBox {
    private VBox inputsContainer = new VBox() {
        {
            setAlignment(Pos.TOP_LEFT);
            setId("auth-form-card");

        }
    };

    public AuthForm(boolean isSignUp) {
        this.switchForm(isSignUp);
        setAlignment(Pos.CENTER_LEFT);
        setId("auth-form");
    }

    public void switchForm(boolean isSignUp) {
        getChildren().clear();
        inputsContainer.getChildren().clear();

        Text auth_text = new Text(isSignUp ? "Registro" : "Incio de sesión") {
            {
                setId("auth-title");
            }
        };


        inputsContainer.getChildren().add(auth_text);

        inputsContainer.getChildren().add(new InputText("Nombre", "", "", "person"));
        if (isSignUp) {
            inputsContainer.getChildren().add(new SelectorInput("Tipo de usuario", "", "person",
                    new String[] { "Administrador", "Recepcionista", "Empleado" }));
        }
        inputsContainer.getChildren().add(new InputPassword());

        VBox.setMargin(auth_text, new Insets(0, 0, 40, 0));


        getChildren().add(inputsContainer);
    }
}
