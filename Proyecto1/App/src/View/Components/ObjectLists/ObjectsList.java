package View.Components.ObjectLists;

import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class ObjectsList extends ScrollPane {
    protected VBox listObj;
    protected int numCols;

    public ObjectsList(String[] colHeader) {
        getStylesheets().add("View/Styles/components/objectList.css");
        getStyleClass().add("object-list-container");
        createList(colHeader);
        setContent(listObj);
        setFitToWidth(true);
        setFitToHeight(true);
        setMaxWidth(1100);
    }

    private VBox createList(String[] titles) {

        Text[] titles_nodes = new Text[titles.length];

        for (int i = 0; i < titles_nodes.length; i++) {
            titles_nodes[i] = new Text(titles[i]);
        }

        listObj = new VBox(new RowGrid(titles_nodes) {
            {
                getStyleClass().add("obj-list__title");
            }
        });
        this.numCols = titles.length;

        return listObj;
    }

    public void addElem(Node[] objectAtributes) {
        Node[] objects = new Node[numCols];

        for (int i = 0; i < numCols; i++) {
            if (i < objectAtributes.length)
                objects[i] = objectAtributes[i];
            else
                objects[i] = new Text();
        }

        RowGrid grid = getRowGrid(objects);

        listObj.getChildren().add(grid);

    }

    protected RowGrid getRowGrid(Node[] objects) {
        return new RowGrid(objects) {
            {
                getStyleClass().add("obj-list__item");
            }
        };
    }

    protected class RowGrid extends GridPane {

        public RowGrid(Node[] elems) {
            {
                getStyleClass().add("obj-list__row");
                setAlignment(Pos.CENTER);
                addElems(elems);

            }
        }

        public void addElems(Node[] elems) {
            // Para cada columna le asigna un mismo grow y posiciona los elementos en el
            // centro
            for (int i = 0; i < elems.length; i++) {
                getColumnConstraints().add(i, new ColumnConstraints() {
                    {
                        setMinWidth(150);
                        setMaxWidth(150);
                        setMinHeight(50);
                        setHalignment(HPos.CENTER);
                        setHgrow(Priority.ALWAYS);
                    }
                });
                
                add(elems[i], i, 0);
            }
        }

    }

}
