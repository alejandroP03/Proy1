package View.Components.Badge;

import javafx.geometry.Pos;
import javafx.scene.control.Button;

public class Badge extends Button {
    public enum BadgeColors {
        RED, YELLOW, GREEN
    }

    public Badge(String text, BadgeColors color) {
        setText(text);
        setDisable(true);
        setAlignment(Pos.CENTER);
        getStylesheets().add("View/Styles/components/badges.css");
        setId("badge");
        getStyleClass().add("badge-" + color.toString().toLowerCase());
    }
}
