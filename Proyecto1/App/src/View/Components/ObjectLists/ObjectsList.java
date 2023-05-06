package View.Components.ObjectLists;

import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class ObjectsList extends ScrollPane {
    protected VBox listObj;
    protected int numCols;
    private ColumnConstraints cols = new ColumnConstraints() {
        {
            setMinWidth(200);
            setMaxWidth(200);
            setMinHeight(100);
            setHalignment(HPos.CENTER);

        }
    };

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
        Node[] objects = getAtributes(objectAtributes);
        RowGrid grid = getRowGrid(objects);
        listObj.getChildren().add(grid);
    }

    public void addElem(Node[] objectAtributes, Pane span) {
        Node[] objects = getAtributes(objectAtributes);
        RowGrid grid = getRowGrid(objects, span);
        listObj.getChildren().add(grid);
    }

    private Node[] getAtributes(Node[] objectAtributes) {
        Node[] objects = new Node[numCols];

        for (int i = 0; i < numCols; i++) {
            if (i < objectAtributes.length)
                objects[i] = objectAtributes[i];
            else
                objects[i] = new Text();
        }

        return objects;
    }

    protected RowGrid getRowGrid(Node[] objects) {
        return new RowGrid(objects) {
            {
                getStyleClass().add("obj-list__item");
            }
        };
    }

    protected RowGrid getRowGrid(Node[] objects, Pane span) {
        return new SpanableRowGrid(objects, span) {
            {
                getStyleClass().add("obj-list__item");
            }
        };

    }

    protected class RowGrid extends GridPane {

        public RowGrid(Node[] elems) {
            {
                setPadding(new Insets(10, 0, 10, 0));
                getStyleClass().add("obj-list__row");
                setAlignment(Pos.CENTER);
                addElems(elems);

            }
        }

        public void addElems(Node[] elems) {
            // Para cada columna le asigna un mismo grow y posiciona los elementos en el
            // centro
            for (int i = 0; i < elems.length; i++) {
                getColumnConstraints().add(i, cols);

                add(elems[i], i, 0);
            }
        }

    }

    protected class SpanableRowGrid extends RowGrid {
        private boolean isPressed = false;
        SpanableRowGrid grid = this;
        Pane span;

        public SpanableRowGrid(Node[] elems, Pane span) {
            super(elems);
            this.span = span;

        }

        public void addElems(Node[] elems) {

            for (int i = 0; i < elems.length; i++) {
                getColumnConstraints().add(i, cols);

                add(elems[i], i, 0);
            }

            setOnMouseClicked(new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent arg0) {
                    getChildren().clear();
                    if (isPressed) {
                        for (int i = 0; i < elems.length; i++) {
                            add(elems[i], i, 0, 1, 1);
                        }
                    } else
                        add(span, 0, 0, elems.length, 1);

                    isPressed = !isPressed;
                }

            });

        }

    }
}
