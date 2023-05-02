package View.Components.Inputs;

import javafx.scene.control.TextField;

public class InputText extends Input<TextField> {
    
    
    public InputText(String label, String placeholder) {
        this(label, placeholder, "" );
    }

    public InputText(String label, String placeholder, String helpText) {
        this(label, placeholder, helpText, "");
    }

    public InputText(String label, String placeholder, String help_text, String iconName) {
        super(label, help_text, iconName, new TextField() {
            {
                getStyleClass().add("input-text");
                setPromptText(placeholder);
            }
        });
    }

    protected InputText(String label, String placeholder, String help_text, String iconName, TextField field) {
        super(label, help_text, iconName, field);
    }

    @Override
    public String getValue() {
        return inputField.getText();
    }

}
