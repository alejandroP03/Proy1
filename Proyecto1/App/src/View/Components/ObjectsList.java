package View.Components;

import javafx.geometry.HPos;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Text;

public class ObjectsList extends ScrollPane {
    GridPane grid;

    public ObjectsList(String[] colHeader) {
        getStylesheets().add("View/Styles/components/objectList.css");
        getStyleClass().add("object-list-container");

        setContent(getList(colHeader));

        // TODO Harcored code
        setPrefViewportWidth(400);
        setPrefViewportHeight(400);

        setFitToHeight(true);
        setFitToWidth(true);
    }

    private GridPane getList(String[] titles) {
        grid = new GridPane() {
            {
                getStyleClass().add("object-list-grid");
                setGridLinesVisible(true);
                // Para cada columna le asigna un mismo grow y posiciona los elementos en el
                // centro
                for (int i = 0; i < titles.length; i++) {
                    getColumnConstraints().add(i, new ColumnConstraints() {
                        {
                            setHgrow(Priority.ALWAYS);
                            setHalignment(HPos.CENTER);
                        }
                    });

                    Text titleText = new Text(titles[i]);
                    add(titleText, i, 0);
                }
            }
        };

        return grid;
    }

    public void addElem(Node[] objectAtributes) {
        int row = grid.getRowCount() + 1;
        for (int i = 0; i < objectAtributes.length; i++) {
            grid.add(objectAtributes[i], i, row);
        }
        
    }

}
