package View.Components.Inputs;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class InputNumber extends InputText {
    public InputNumber(String label, String helpText, int min, int max, int initialValue) {
        super("", "0", helpText);
        getStyleClass().add("input-number");
        this.inputField.getStyleClass().add("input-number");
        
        this.inputField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                    String newValue) {
                if (!newValue.matches("\\d*")) {
                    inputField.setText("");
                } else {
                    if (!newValue.isBlank()) {
                        int value = Integer.parseInt(newValue);
                        if (value < min) {
                            inputField.setText("");
                        } else if (value > max) {
                            inputField.setText(((value / 10)) + "");
                        }
                    }

                }
            }
        });
    }

    @Override
    public String getValue() {
        return this.inputField.getText();
    }
}
