package View.Components.Inputs;

import java.util.HashMap;
import java.util.Map;

import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;

public class SelectorInput extends Input<ComboBox<String>> {
    Map<Object, Object> realValuesMap = new HashMap<Object, Object>();

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
        this(labelText, help_text, iconName, placeholder, options, options);
    }

    public SelectorInput(String labelText, String help_text, String iconName, String placeholder, String[] options, Object[] realValues) {
        super(labelText, help_text, iconName, new ComboBox<String>() {
            {
                getStyleClass().add("input-selector");
                getItems().addAll(options);
                setPromptText(placeholder == null || placeholder.isBlank() ? options[0] : placeholder);
                setValue(options[0]);
            }
        });

        for (int i = 0; i < options.length; i++) {
            this.realValuesMap.put(options[i], realValues[i]);
        }
        
    }


    @Override
    public Object getValue() {
        return this.realValuesMap.get(this.inputField.getValue());
    }

    public ObservableList<String> getItems(){
        return this.inputField.getItems();
    }

}
