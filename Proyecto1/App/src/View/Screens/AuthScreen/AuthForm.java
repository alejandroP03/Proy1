package View.Screens.AuthScreen;

import Controller.Hotel;
import Controller.WorkersAuth.HotelWorkersAuth;
import Model.HotelObjects.User;
import Model.HotelObjects.UserType;
import View.Components.Inputs.Input;
import View.Components.Inputs.InputPassword;
import View.Components.Inputs.InputText;
import View.Components.Inputs.SelectorInput;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Popup;

public class AuthForm extends VBox {

    HotelWorkersAuth authHandler = HotelWorkersAuth.getInstance();
    Hotel hotel = Hotel.getInstance();

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

        InputText nameInput = new InputText("Nombre", "", "", "person");
        inputsContainer.getChildren().add(nameInput);
        SelectorInput userType = new SelectorInput("Tipo de usuario", "", "person","",
                new String[] { "Administrador", "Recepcionista", "Empleado" }, UserType.values());
        if (isSignUp) {
            inputsContainer.getChildren().add(userType);
        }
        InputPassword passwordInput = new InputPassword();
        inputsContainer.getChildren().add(passwordInput);

        Button registerButton = new Button(isSignUp ? "Registrarse" : "Iniciar sesión");


        Label errorLabel = new Label("prueba");


        registerButton.setOnAction(e->{
            Alert errorAlert = new Alert(Alert.AlertType.INFORMATION);
            String name = nameInput.getValue();
            UserType type = (UserType) userType.getValue();
            String password = passwordInput.getValue();
            errorAlert.setHeaderText("Error");
            errorAlert.setTitle("Error");

            try {
                if(isSignUp){
                    if(name.isBlank() || password.isBlank()){

                        errorAlert.setContentText("Nombre de usuario invalido o contraseña invalido");
                        errorAlert.showAndWait();
                    } else if (authHandler.userExists(name,password,hotel.getUserHandler().getData())){

                        errorAlert.setContentText("El usuario ya se encuentra registrado");
                        errorAlert.showAndWait();
                    } else {
                        authHandler.register(name,password,type,hotel.getUserHandler().getData());
                        hotel.getUserHandler().SavePersistentData();

                    }

                }
                else{
                    if(name.isBlank() || password.isBlank()){

                        errorAlert.setContentText("Nombre de usuario invalido o contraseña invalido");
                        errorAlert.showAndWait();
                    } else if (!authHandler.userExists(name,password,hotel.getUserHandler().getData())){

                        errorAlert.setContentText("Nombre de usuario invalido o contraseña incorrecto (Verifique si se encuentra registrado)");
                        errorAlert.showAndWait();
                    } else{
                        authHandler.login(name,password,hotel.getUserHandler().getData());
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
