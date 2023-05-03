package View.Components.Calendars;

import java.time.LocalDate;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.skin.DatePickerSkin;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.util.Callback;

public class Calendar extends StackPane {
    LocalDate[] selectedDates = new LocalDate[2];
    DatePicker date_picker = new DatePicker();

    public Calendar() {
        getStylesheets().add("View/Styles/components/date.css");
        getStylesheets().add("View/Styles/components/date-editable.css");

        getChildren().add(date_picker);
        
        
        date_picker.setVisible(false);

        getChildren().add(getDateViewer());

    }

    private void getValues(){
        boolean setedInitialDate = selectedDates[0] != null;
        boolean setedFinalDate = selectedDates[1] != null;

        getChildren().clear();
        getChildren().add(date_picker);
        
        if (setedInitialDate && !setedFinalDate) {
            selectedDates[1] = date_picker.getValue();
            
            date_picker.setDayCellFactory(getDayCellFactory());
            

        } else if (setedInitialDate && setedFinalDate) {
            selectedDates = new LocalDate[2];
            selectedDates[0] = date_picker.getValue();
            date_picker = new DatePicker();
            
        } else {
            selectedDates[0] = date_picker.getValue();
        }
        
        getChildren().add(getDateViewer());
    }

    private Callback<DatePicker, DateCell> getDayCellFactory() {
        return new Callback<DatePicker, DateCell>() {
            public DateCell call(final DatePicker datePicker) {
                return new DateCell() {

                    @Override
                    public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);

                        LocalDate date = selectedDates[0];
                        while(!date.equals(selectedDates[1])){
                            if (item.equals(date)) {
                                setStyle("-fx-background-color: #E29578;");
                            }
                            date = date.plusDays(1);
                        }
                    }
                };
            }
        };

    }

    private Node getDateViewer(){
        DatePickerSkin skin = new DatePickerSkin(date_picker);
        Node datePicker = skin.getPopupContent();
        datePicker.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                getValues();
            }

        });
        return datePicker;
    }

    public LocalDate[] getDateRange() {
        return selectedDates;
    }
}
