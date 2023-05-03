package View.Components.ObjectLists;

import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;

public class SpanableObjectsList extends ObjectsList {
    Pane span;

    public SpanableObjectsList(String[] colHeader, Pane span) {
        super(colHeader);
        this.span = span;
    }

    @Override
    protected RowGrid getRowGrid(Node[] objects) {
        return new SpanableRowGrid(objects, span) {
            {
                getStyleClass().add("obj-list__item");
            }
        };
    }

    protected class SpanableRowGrid extends RowGrid {
        private boolean isPressed = false;
        SpanableRowGrid grid = this;
        Pane span;

        public SpanableRowGrid(Node[] elems, Pane spanElem) {
            super(elems);
            this.span = spanElem;
        }

        @Override
        public void addElems(Node[] elems) {

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
