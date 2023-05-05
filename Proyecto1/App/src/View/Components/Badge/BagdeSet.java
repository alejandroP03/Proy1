package View.Components.Badge;

import javafx.geometry.Pos;
import javafx.scene.layout.TilePane;

public class BagdeSet extends TilePane {
    public BagdeSet() {
        setAlignment(Pos.CENTER);
        setHgap(5);
        setVgap(10);
        getStyleClass().add("badge-set");
    }

    public void addBadge(Badge badge) {
        getChildren().add(badge);

    }
}
