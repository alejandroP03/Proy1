package View.Components.Inputs;

import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;

public class SelectorInput extends Input<ComboBox<String>> {

    public SelectorInput(String labelText, String[] options) {
        this(labelText, "", options);

    }

    public SelectorInput(String labelText, String help_text, String[] options) {
        this(labelText, help_text, "", options);

    }

    public SelectorInput(String labelText, String help_text, String iconName, String[] options) {
        this(labelText, help_text, iconName, "", options);

    }

    public SelectorInput(String labelText, String help_text, String iconName, String placeholder, String[] options) {
        super(labelText, help_text, iconName, new ComboBox<String>() {
            {
                getStyleClass().add("input-selector");
                getItems().addAll(options);
                setPromptText(placeholder == null || placeholder.isBlank() ? options[0] : placeholder);
                setValue(options[0]);
            }
        });
    }

    @Override
    public String getValue() {
        return this.inputField.getValue();
    }

    public ObservableList<String> getItems(){
        return this.inputField.getItems();
    }

}
