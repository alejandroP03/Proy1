package View.Components.Inputs;

import javafx.scene.control.DatePicker;

public class InputDate extends Input<DatePicker> {
    public InputDate(String labelText, String help_text, String iconName, String placeholder) {
        super(labelText, help_text, iconName, new DatePicker() {
            {
                // getStyleClass().add("input-date");
                setId("input-date");
                setPromptText(placeholder);

            }
        });

    }

    @Override
    public Object getValue() {
        return this.inputField.getValue();
    }
}
