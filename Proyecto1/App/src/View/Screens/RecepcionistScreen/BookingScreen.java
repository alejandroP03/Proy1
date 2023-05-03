package View.Screens.RecepcionistScreen;

import View.Components.Inputs.SelectorInput;
import View.Components.ObjectsList;
import View.Components.PrinicipalWindow;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.*;

import java.awt.*;

public class BookingScreen extends VBox{
    public PrinicipalWindow pw;
    public BookingScreen(){
        getStylesheets().add("View/Styles/recepcionist/recepcionistScreens.css");

        pw = new PrinicipalWindow<VBox>("recepcionist", new VBox());
        setVgrow(pw, Priority.ALWAYS);
        Pane mainPane = pw.getMainPane();
        VBox vbox = new VBox();

        vbox.getChildren().add(titleMenu("sapo"));
        vbox.getChildren().add(filtersForm());
        vbox.setSpacing(50);
        vbox.setPadding(new Insets(30,80,40,80));
        vbox.setAlignment(Pos.CENTER);

        mainPane.getChildren().add(vbox);
        getChildren().add(pw);


    }

    public Label titleMenu(String page){
        Label rooms = new Label("Habitaciones  > Fecha > Huesped > Resumen");
        return rooms;
    }

    public GridPane filtersForm(){
        Label title = new Label("Filtros");
        title.setId("title-filter");
        SelectorInput typeRoom = new SelectorInput("Tipo habitacion", "", "", "Suite doble",
                new String[] { "opcion 1", "opcion 2", "opcion 3", "N/A" });
        SelectorInput feature = new SelectorInput("Caracteristica", "", "", "Balcon",
                new String[] { "opcion 1", "opcion 2", "opcion 3", "N/A" });
        SelectorInput bed = new SelectorInput("Cama", "", "", "King",
                new String[] { "opcion 1", "opcion 2", "opcion 3", "N/A" });
        typeRoom.setId("input-recepcionist-filters");
        feature.setId("input-recepcionist-filters");
        bed.setId("input-recepcionist-filters");

        GridPane gridPane = new GridPane();
        gridPane.add(title, 0,0);
        gridPane.add(typeRoom,0,1);
        gridPane.add(feature,1,1);
        gridPane.add(bed,2,1);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setGridLinesVisible(true);
        gridPane.setHgap(20);
        gridPane.setVgap(10);

        return gridPane;

    }

//    public gridInfo(){
//        ObjectsList roomList = new ObjectsList(new String[] { "Nombre", "Tipo", "Disponibilidad", "Room Service", "Precio" });
//
//        roomList.addElem(new Node[] { new Text("Hamburguesa"), new Text("Comida") });
//    }


}
