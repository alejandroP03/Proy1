package View.Components;

import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;

public abstract class Input<T extends Control> extends FlowPane {
    /*
     * Componente base para TextFields y ComboBox principalmente
     * ya tiene implementados todos los estilos del design system
     * 
     */

    protected T inputField;

    public Input(String labelText, T inputField) {
        this.inputField = inputField;
        inputField.setId("input");
        getChildren().add(inputField);
        getChildren().add(0, new Label(labelText) {
            {
                setId("input-label");
            }
        });

        setId("input-container");
        getStylesheets().add("View/Styles/components.css");
    }

    public Input(String labelText, String help_text, T inputField) {
        this(labelText, inputField);

        getChildren().add(2, new Label(help_text) {
            {
                setId("input-help-text");
            }
        });
    }

    public Input(String labelText, String help_text, String iconName, T inputField) {
        this(labelText, help_text, inputField);
        inputField.setStyle(String.format("-fx-background-image: url('View/assets/icons/%s.png');", iconName));
    }

    abstract public String getValue();

}
