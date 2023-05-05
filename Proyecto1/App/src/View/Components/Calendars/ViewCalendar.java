package View.Components.Calendars;

import java.time.LocalDate;

import javafx.scene.Node;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.skin.DatePickerSkin;
import javafx.scene.layout.StackPane;
import javafx.util.Callback;

public class ViewCalendar extends StackPane {
    public ViewCalendar(LocalDate[] dates) {

        getStylesheets().add("View/Styles/components/date.css");
        getStylesheets().add("View/Styles/components/date-view.css");

        DatePicker date_picker = new DatePicker();
        StackPane root = new StackPane(date_picker);
        date_picker.setVisible(false);

        final Callback<DatePicker, DateCell> dayCellFactory = new Callback<DatePicker, DateCell>() {
            public DateCell call(final DatePicker datePicker) {
                return new DateCell() {
                    @Override
                    public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);
                        for (LocalDate localDate : dates) {
                            if (item.equals(localDate)) {
                                setStyle("-fx-background-color: #E29578;");
                            }
                        }
                    }
                };
            }
        };

        date_picker.setDayCellFactory(dayCellFactory);

        DatePickerSkin skin = new DatePickerSkin(date_picker);
        Node datePicker = skin.getPopupContent();
        
        root.getChildren().add(datePicker);

        getChildren().add(root);

    }
}