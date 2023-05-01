package View.Components;

import javafx.scene.control.TextField;

public class InputText extends Input<TextField> {

    
    public InputText(String label, String placeholder) {
        super(label, placeholder, new TextField() {
            {
                getStyleClass().add("input-text");
                setPromptText(placeholder);
            }
        });
    }

    public InputText(String label, String placeholder, String helpText) {
        super(label, placeholder, helpText, new TextField() {
            {
                getStyleClass().add("input-text");
                setPromptText(placeholder);
            }
        });
    }

    public InputText(String label, String placeholder, String help_text, String iconName) {
        super(label, help_text, iconName, new TextField() {
            {
                getStyleClass().add("input-text");
                setPromptText(placeholder);
            }
        });
    }

    @Override
    public String getValue() {
        return inputField.getText();
    }

}
