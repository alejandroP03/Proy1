package View.Components;

import javafx.scene.control.ComboBox;

public class SelectorInput extends Input<ComboBox<String>> {

    public SelectorInput(String labelText) {
        super(labelText, new ComboBox<String>() {
            {
                getStyleClass().add("input-selector");
            }
        });
    }

    public SelectorInput(String labelText, String help_text) {
        super(labelText, help_text, new ComboBox<String>() {
            {
                getStyleClass().add("input-selector");
            }
        });

    }

    public SelectorInput(String labelText, String help_text, String iconName) {
        super(labelText, help_text, iconName, new ComboBox<String>() {
            {
                getStyleClass().add("input-selector");
            }
        });
    }

    @Override
    public String getValue() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getValue'");
    }

}
