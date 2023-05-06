package View.Components.Inputs;

import java.time.DayOfWeek;
import java.util.Arrays;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class InputDays extends VBox {
    DayOfWeek[] availableDays = new DayOfWeek[7];

    public InputDays(String labelText) {

        HBox btnsContainer = new HBox();
        Label label = new Label(labelText);
        label.setAlignment(Pos.CENTER_LEFT);
        setAlignment(Pos.CENTER);
        setSpacing(15);
        btnsContainer.setSpacing(10);
        btnsContainer.setAlignment(Pos.CENTER);
        for (DayOfWeek day : DayOfWeek.values()) {
            btnsContainer.getChildren().add(createButton(day));

        }

        getChildren().addAll(label, btnsContainer);

    }

    private Button createButton(DayOfWeek day) {
        return new Button(day.toString().charAt(0) + "") {
            {
                getStyleClass().add("input-days-button");
                setOnAction(e -> {
                    // Add or remove the day from the list DayOfWeek[] availableDays
                    if (availableDays[day.getValue() - 1] == null) {
                        availableDays[day.getValue() - 1] = day;
                        getStyleClass().add("input-days-button-selected");
                    } else {
                        availableDays[day.getValue() - 1] = null;
                        getStyleClass().remove("input-days-button-selected");
                    }

                });
            }
        };
    }

    public DayOfWeek[] getValue() {
        DayOfWeek[] filteredDates = Arrays.stream(availableDays)
                .filter(date -> date != null)
                .toArray(DayOfWeek[]::new);
        return filteredDates;
    }

}
