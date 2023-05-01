package View.Components;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.geometry.*;
import javafx.scene.layout.*;
import javafx.scene.text.Text;

import java.awt.*;


public class PrinicipalWindow extends GridPane{

    public PrinicipalWindow() {
        setId("principal");
        setHgap(10);
        setVgap(10);
        setPadding(new Insets(0, 10, 0, 10));


        // Agregar panel de arriba
        Pane logo = new Pane();
        add(logo, 0, 0);
        logo.setStyle("-fx-pref-width: 200px;\n" +
                "    -fx-pref-height: 50px;" +  "-fx-background-color: white;");


//        Pane userInfo = new Pane();
//        add(userInfo, 3, 0);
//        userInfo.setStyle("-fx-pref-width: 100px;\n" +
//                "    -fx-pref-height: 100px;" +  "-fx-background-color: red;");
//
//
//        Pane principalInfo = new Pane();
//        add(principalInfo, 1, 1);
//        principalInfo.setStyle("-fx-pref-width: 600px;\n" +
//                "    -fx-pref-height: 100px;" +  "-fx-background-color: red;");


        // Agregar menu de la izquierda
        add(lateralMenu(), 0, 1);

        ColumnConstraints column1 = new ColumnConstraints();
        column1.setPercentWidth(50);
        column1.setHgrow(Priority.ALWAYS);


        //getChildren().add(lateralMenu());

    }

    public Pane lateralMenu(){
        VBox vbox = new VBox();
        vbox.getChildren().addAll(verticalIconText("Habitaciones", "View/assets/images/ActiveImages/Subtract.png",true)
                , verticalIconText("Restaurante", "View/assets/images/InactiveImages/building-storefront.png",false),
                verticalIconText("Servicios", "View/assets/images/InactiveImages/shopping-bag.png",false),
                verticalIconText("Inventario", "View/assets/images/InactiveImages/Union.png",false));
        Pane pane = new Pane();
        pane.getChildren().add(vbox);
        vbox.setSpacing(35);
        pane.setStyle(" -fx-background-color: blue;");
        return pane;
    }

    public Pane verticalIconText(String textImg, String ImgPath, boolean isSelected){

        ImageView imageView = new ImageView(new Image(ImgPath));
        Label label = new Label(textImg);
        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        if (isSelected){
            // Backgorund Blanco e imagen Azul
            vBox.getStyleClass().add("option-active");
        }else{
            // texto blanco e imagen blanca
            vBox.getStyleClass().add("option-inActive");
            label.setStyle("-fx-text-fill: white;");
        }
        vBox.getChildren().addAll(imageView, label);
        vBox.setSpacing(15);
        Pane pane = new Pane();
        pane.getChildren().add(vBox);
        return pane;
    }
}
