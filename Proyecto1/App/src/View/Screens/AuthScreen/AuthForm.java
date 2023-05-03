package View.Screens.AuthScreen;

import Model.HotelObjects.UserType;
import View.Components.Inputs.InputPassword;
import View.Components.Inputs.InputText;
import View.Components.Inputs.SelectorInput;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class AuthForm extends VBox {
    Auth authControl;

    private VBox inputsContainer = new VBox() {
        {
            setAlignment(Pos.TOP_LEFT);
            setId("auth-form-card");

        }
    };

    public AuthForm(boolean isSignUp, Auth authControl) {
        this.switchForm(isSignUp);
        this.authControl = authControl;
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

        InputText nameInput = new InputText("Nombre", "", "", "person");
        inputsContainer.getChildren().add(nameInput);
        SelectorInput userType = new SelectorInput("Tipo de usuario", "", "person", "",
                new String[] { "Administrador", "Recepcionista", "Empleado" }, UserType.values());
        if (isSignUp) {
            inputsContainer.getChildren().add(userType);
        }
        InputPassword passwordInput = new InputPassword();
        inputsContainer.getChildren().add(passwordInput);

        Button registerButton = new Button(isSignUp ? "Registrarse" : "Iniciar sesión");

        registerButton.setOnAction(e -> {
            Alert errorAlert = new Alert(Alert.AlertType.INFORMATION);
            String name = nameInput.getValue();
            UserType type = (UserType) userType.getValue();
            String password = passwordInput.getValue();
            errorAlert.setHeaderText("Error");
            errorAlert.setTitle("Error");

            try {
                boolean userExist = authControl.userExists(name, password);
                boolean isDataValid = name.isBlank() || password.isBlank();
                if (isSignUp) {
                    if (isDataValid) {
                        errorAlert.setContentText("Nombre de usuario invalido o contraseña invalido");
                        errorAlert.showAndWait();

                    } else if (userExist) {
                        errorAlert.setContentText("El usuario ya se encuentra registrado");
                        errorAlert.showAndWait();
                    } else {
                        authControl.signUp(name, password, type);
                        
                    }

                } else {
                    if (isDataValid) {
                        errorAlert.setContentText("Nombre de usuario invalido o contraseña invalido");
                        errorAlert.showAndWait();
                    } else if (!userExist) {
                        errorAlert.setContentText(
                                "Nombre de usuario invalido o contraseña incorrecto (Verifique si se encuentra registrado)");
                        errorAlert.showAndWait();
                    } else {
                        authControl.signIn(name, password);
                    }

                }

            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }

        });

        inputsContainer.getChildren().add(registerButton);

        VBox.setMargin(auth_text, new Insets(0, 0, 40, 0));

        getChildren().add(inputsContainer);
    }
}
