package View.Components;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;

public class PrinicipalWindow<T extends Pane> extends BorderPane {
    T mainPane;

    public PrinicipalWindow(String user, T layout) {
        this.mainPane = layout;
        mainPane.setId("main-pane");
        getStylesheets().add("View/Styles/components/principalWindow.css");
        GridPane grid = grid();
        getStyleClass().add(user + "-main");
        grid.getStyleClass().add(user);
        setPadding(new Insets(30));
        setCenter(grid);
    }

    public VBox lateralMenu() {
        VBox vbox = new VBox();
        vbox.getChildren().addAll(
                verticalIconText("Habitaciones", "View/assets/images/Subtract.png", true),
                verticalIconText("Restaurante", "View/assets/images/building-storefront.png", false),
                verticalIconText("Servicios", "View/assets/images/shopping-bag.png", false),
                verticalIconText("Inventario", "View/assets/images/Union.png", false));

        VBox pane = new VBox();
        pane.getChildren().add(vbox);
        vbox.setSpacing(35);
        pane.setAlignment(Pos.CENTER);
        
        return pane;
    }

    public VBox verticalIconText(String textImg, String ImgPath, boolean isSelected) {

        ImageView imageView = new ImageView(new Image(ImgPath));
        Label label = new Label(textImg);
        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        if (isSelected) {
            // Backgorund Blanco e imagen Azul
            vBox.getStyleClass().add("option-active");
        } else {
            // texto blanco e imagen blanca
            vBox.getStyleClass().add("option-inActive");
            label.setStyle("-fx-text-fill: white;");
        }
        vBox.getChildren().addAll(imageView, label);
        vBox.setSpacing(15);
        return vBox;
    }

    public Pane logo() {

        ImageView imageView = new ImageView(new Image("View/assets/images/home-modern.png"));
        Label label = new Label("Logo");
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);
        label.setStyle("-fx-text-fill: white;");
        hBox.getChildren().addAll(imageView, label);
        hBox.setSpacing(5);
        Pane pane = new Pane();
        pane.getChildren().add(hBox);
        return pane;

    }

    public GridPane grid() {
        return new GridPane() {
            {

                setId("main");
                setHgap(50);
                setVgap(60);
                setPadding(new Insets(20, 15, 20, 25));

                // Agregar menu de la izquierda
                add(lateralMenu(), 0, 1);

                add(new ScrollPane(){{
                    setContent(getMainPane());
                }}, 1, 1);
                // getChildren().add(lateralMenu());
                RowConstraints row1 = new RowConstraints();
                row1.setMinHeight(30); // altura mínima de 30 píxeles
                row1.setVgrow(Priority.NEVER); // no se expandirá verticalmente
                RowConstraints row2 = new RowConstraints();
                row2.setVgrow(Priority.ALWAYS); // se expandirá verticalmente
                ColumnConstraints col1 = new ColumnConstraints();
                col1.setMinWidth(120); // ancho mínimo de 50 píxeles
                col1.setHgrow(Priority.NEVER); // no se expandirá horizontalmente
                ColumnConstraints col2 = new ColumnConstraints();
                col2.setHgrow(Priority.ALWAYS); // se expandirá horizontalmente

                // agregar las RowConstraints y ColumnConstraints al GridPane
                getRowConstraints().addAll(row1, row2);
                getColumnConstraints().addAll(col1, col2);

                // agregar los componentes al GridPane
                // el componente grande se agrega a la segunda fila y columna

                // la barra lateral se agrega a la primera columna

                add(lateralMenu(), 0, 1);

                // la barra superior se agrega a la primera fila
                Pane header = new Pane();
                header.setStyle("-fx-background-color: gray;");
                add(header, 1, 0);

                // la barra superior se agrega a la primera fila
                add(logo(), 0, 0);

            }
        };
    }

    public T getMainPane() {
        return mainPane;
    }

    public void userInfo() {

    }

}
