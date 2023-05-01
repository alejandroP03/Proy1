package View.Screens.AuthScreen;

import View.Components.SelectorInput;
import javafx.scene.layout.Pane;

public class Auth extends Pane {

    public Auth() {

        /* super(new InputText("Whereas recognition of the inherent dignity", "A text", "Help text", "person")); */
        super(new SelectorInput("Whereas recognition of the inherent dignity", "Help text", "person"));

        setId("auth-container");
        getStylesheets().add("View/Styles/auth.css");
    }

}
