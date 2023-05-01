package View.Components;

import javafx.scene.control.ComboBox;

public class SelectorInput extends Input<ComboBox<String>> {

    public SelectorInput(String labelText, String[] options) {
        this(labelText, "", options);

    }

    public SelectorInput(String labelText, String help_text, String[] options) {
        this(labelText, help_text, "", options);

    }

    public SelectorInput(String labelText, String help_text, String iconName, String[] options) {
        super(labelText, help_text, iconName, new ComboBox<String>() {
            {
                getStyleClass().add("input-selector");
                getItems().addAll(options);
                setPromptText("Administrador");
            }
        });

    }

    @Override
    public String getValue() {
        return this.inputField.getValue();
    }

}
