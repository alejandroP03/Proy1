package View.Components.Inputs;

import javafx.scene.control.PasswordField;

public class InputPassword extends InputText{
    PasswordField pn = new PasswordField();
    public InputPassword(){
        super("Contraseña", "●", "", "person", new PasswordField(){{
            getStyleClass().add("input-text");
        }});
    }
    
    
}
