package View.Screens.AuthScreen;

import View.Components.Input;
import View.Components.PrinicipalWindow;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class ScreenAdmin extends VBox {

        public ScreenAdmin(){
                getStylesheets().add("View/Styles/AdminScreen.css");
                setId("background-window");
                setPrefSize(800,800);
                setAlignment(Pos.CENTER);
                PrinicipalWindow pw = new PrinicipalWindow("Admin");
                setVgrow(pw, Priority.ALWAYS);
                setPadding(new Insets(50));
                Pane paneGrande = pw.getPaneGrande();


                VBox leftGrid = new VBox();
                leftGrid.setPadding(new Insets(20,30,40,50));
                BorderPane cargaGrid = loadRooms();
//                cargaGrid.getChildren().add(new Label("PRUEBA CARGA GRID"));
//                cargaGrid.setBackground(new javafx.scene.layout.Background(
//                        new javafx.scene.layout.BackgroundFill(
//                                Color.web("#2033CC"), null, null)));

                VBox vboxSinTarifa = new VBox();
//                vboxSinTarifa.getChildren().add(new Label("PRUEBA SIN TARIFA"));
//                vboxSinTarifa.setBackground(new javafx.scene.layout.Background(
//                        new javafx.scene.layout.BackgroundFill(
//                                Color.web("#f7a9a8"), null, null)));
                leftGrid.setPadding(new Insets(20,30,40,50));
                for(int i =0; i<3; i++){
                        GridPane roomNoFare = noRoomsFare();
                        vboxSinTarifa.getChildren().add(roomNoFare);
                }


                VBox rightGrid = new VBox();
                //rightGrid.setPadding(new Insets(20,30,40,50));

                leftGrid.getChildren().add(cargaGrid);
                leftGrid.getChildren().add(vboxSinTarifa);
                paneGrande.getChildren().add(leftGrid);

                VBox vboxCrear = new VBox();
                paneGrande.getChildren().add(vboxCrear);

                getChildren().add(pw);

        }

        public BorderPane loadRooms(){
                Label titulo = new Label("Carga un archivo de habitaciones!");
                //Imagen personaje a la derecha
                Image imagen = new Image("View/assets/images/Group 87.png");
                ImageView imageView = new ImageView(imagen);

                // agreagar cajas abajo izquierda
                VBox vboxTextos = new VBox();
                vboxTextos.getChildren().addAll(smallInfoRooms("Agregadas"),smallInfoRooms("Sin tarifas"));
                vboxTextos.setAlignment(Pos.CENTER_LEFT);
                vboxTextos.setSpacing(10);

                // Crear el BorderPane y agregar los elementos
                BorderPane borderPane = new BorderPane();
                borderPane.setId("principal-panel");
                borderPane.setPadding(new Insets(30,0,0,25));
                borderPane.setTop(titulo);
                borderPane.setLeft(vboxTextos);
                borderPane.setRight(imageView);

                return borderPane ;
        }

        public GridPane smallInfoRooms (String textGrid){
                // crear las cajas de abajo
                Image prbImg = new Image("View/assets/images/Group 47.png");
                ImageView prbImgView = new ImageView(prbImg);
                Label texto = new Label(textGrid);
                Label num = new Label("0");

                // Crear el GridPane y ajustar las columnas
                GridPane gridPane = new GridPane();
                gridPane.setPadding(new Insets(2));
                gridPane.setHgap(10);
                gridPane.setVgap(-10);
                gridPane.getColumnConstraints().add(new ColumnConstraints());
                gridPane.getColumnConstraints().add(new ColumnConstraints(140));
                // Agregar la imagen y el texto al GridPane
                gridPane.add(prbImgView, 0, 0);
                gridPane.add(texto, 1, 0);
                gridPane.add(num, 1, 1);
                return gridPane;
        }



        public GridPane createRoom(){
                Label title = new Label("Crear una habitacion");
                VBox form = new VBox();
                form.setSpacing(10); // agregar espacio entre los nodos

//                Label nameLabel = new Label("Nombre:");
//                TextField nameField = new TextField();
//
//                Label emailLabel = new Label("Email:");
//                TextField emailField = new TextField();
//
//                Label phoneLabel = new Label("Teléfono:");
//                TextField phoneField = new TextField();
//
//                form.getChildren().addAll(title ,nameLabel, nameField, emailLabel, emailField, phoneLabel, phoneField);


                return null;


        }

        public GridPane noRoomsFare(){
                Label roomName = new Label("Jabitacion #00");
                Label roomInfo = new Label("sdklajdkasjdk jkdhsjkadhjkashdjk ajkdh asjk");
                Button addFairBtn = new Button("+");

                addFairBtn.setOnAction(e -> System.out.println("MAMABICHO"));

                GridPane gridPane = new GridPane();
                gridPane.setId("grid-rooms");
                gridPane.setPadding(new Insets(2));
                gridPane.setHgap(60);
                gridPane.setVgap(-5);
                gridPane.getColumnConstraints().add(new ColumnConstraints());
                gridPane.getColumnConstraints().add(new ColumnConstraints());
                // Agregar la imagen y el texto al GridPane
                gridPane.add(addFairBtn, 1, 0);
                gridPane.add(roomName, 0, 0);
                gridPane.add(roomInfo, 0, 1);
                return gridPane;
        }





}
