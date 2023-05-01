package View.Components;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;


public class PrinicipalWindow extends GridPane{
    Pane paneGrande;
    public PrinicipalWindow(String user) {
        
        if (user.equals("Admin")){
            setId("admin");
        } else if (user.equals("Recepcionist")) {
            setId("recepcionist");
        } else if (user.equals("Employee")) {
            setId("employee");
        }

<<<<<<< HEAD
        setHgap(50);
        setVgap(50);
        setPadding(new Insets(20, 15, 20, 25));
=======
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
        getColumnConstraints().add(0, column1);
>>>>>>> ece01383c6fc0e5d3ed66efe2784be88e2becc57


        //getChildren().add(lateralMenu());
        RowConstraints row1 = new RowConstraints();
        row1.setMinHeight(30); // altura mínima de 30 píxeles
        row1.setVgrow(Priority.NEVER); // no se expandirá verticalmente
        RowConstraints row2 = new RowConstraints();
        row2.setVgrow(Priority.ALWAYS); // se expandirá verticalmente
        ColumnConstraints col1 = new ColumnConstraints();
        col1.setMinWidth(50); // ancho mínimo de 50 píxeles
        col1.setHgrow(Priority.NEVER); // no se expandirá horizontalmente
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setHgrow(Priority.ALWAYS); // se expandirá horizontalmente

// agregar las RowConstraints y ColumnConstraints al GridPane
        getRowConstraints().addAll(row1, row2);
        getColumnConstraints().addAll(col1, col2);

// agregar los componentes al GridPane
// el componente grande se agrega a la segunda fila y columna
        this.paneGrande = new GridPane();
        this.paneGrande.setStyle("-fx-background-color: white;" + "-fx-background-radius: 20px;" );
        add(this.paneGrande, 1, 1);


// la barra lateral se agrega a la primera columna

        add(lateralMenu(), 0, 1);

// la barra superior se agrega a la primera fila
        Pane barraSuperior = new Pane();
        barraSuperior.setStyle("-fx-background-color: gray;");
        add(barraSuperior, 1, 0);

        // la barra superior se agrega a la primera fila
        add(logo(), 0, 0);

    }

    public VBox lateralMenu(){
        VBox vbox = new VBox();
        vbox.getChildren().addAll(
                verticalIconText("Habitaciones", "View/assets/images/ActiveImages/Subtract.png",true)
                , verticalIconText("Restaurante", "View/assets/images/InactiveImages/building-storefront.png",false),
                verticalIconText("Servicios", "View/assets/images/InactiveImages/shopping-bag.png",false),
                verticalIconText("Inventario", "View/assets/images/InactiveImages/Union.png",false));
        VBox pane = new VBox();
        pane.getChildren().add(vbox);
        vbox.setSpacing(35);
        pane.setAlignment(Pos.CENTER);
        return pane;
    }


    public VBox verticalIconText(String textImg, String ImgPath, boolean isSelected){

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
        return vBox;
    }

    public Pane logo (){

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

    public void userInfo(){

    }

    public Pane getPaneGrande(){
        return this.paneGrande;
    }




}
