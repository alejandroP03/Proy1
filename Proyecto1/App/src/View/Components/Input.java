package View.Components;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;

public class Input extends FlowPane {
    TextField inputField = new TextField();

    public Input(String placeholder) {
        inputField.setPromptText(placeholder);
        inputField.setId("input");
        getChildren().add(inputField);
        setId("input-container");
        getStylesheets().add("View/Styles/components.css");
    }

    public Input(String label, String placeholder) {
        this(placeholder);

        getChildren().add(0, new Label(label) {
            {
                setId("input-label");
            }
        });
    }

    public Input(String label, String placeholder, String help_text) {
        this(label, placeholder);

        getChildren().add(2, new Label(help_text) {
            {
                setId("input-help-text");
            }
        });
    }

    public Input(String label, String placeholder, String help_text, String iconName) {
        this(label, placeholder, help_text);

        inputField.setStyle(String.format("-fx-background-image: url('View/assets/icons/%s.png');", iconName));
    }

    public String getText(){
        return inputField.getText();
    }

    
}
