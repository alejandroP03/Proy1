package View.Components;

import javafx.geometry.HPos;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

public class ObjectsList extends ScrollPane{
    StackPane elements = new StackPane();

    /*
     * 
     * @param elements
     */
    public ObjectsList(String[] colHeader){
        getStylesheets().add("View/Styles/components/objectList.css");
        getStyleClass().add("object-list-container");

        setContent(getList(colHeader));
        setPrefViewportWidth(400);
        setPrefViewportHeight(400);
        setFitToHeight(true);
        setFitToWidth(true);
    }

    public GridPane getList(String[] titles){
        return new GridPane(){{
            getStyleClass().add("object-list-grid");
            setGridLinesVisible(true);
            //Para cada columna le asigna un mismo grow y posiciona los elementos en el centro
            for (int i = 0; i < titles.length; i++) {
                getColumnConstraints().add(i, new ColumnConstraints(){{
                    setHgrow(Priority.ALWAYS);
                    setHalignment(HPos.CENTER);
                }});

                Text titleText = new Text(titles[i]);
                add(titleText, i, 0);
            }
        }};
    }
}
