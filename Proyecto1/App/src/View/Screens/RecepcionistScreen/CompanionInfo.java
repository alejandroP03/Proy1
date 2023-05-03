package View.Screens.RecepcionistScreen;

import View.Components.Inputs.InputText;
import View.Components.PrinicipalWindow;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Accordion;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class CompanionInfo extends  VBox{
    public PrinicipalWindow pw;
    public CompanionInfo(){
        getStylesheets().add("View/Styles/recepcionist/recepcionistScreens.css");
        pw = new PrinicipalWindow<VBox>("recepcionist", new VBox());
        setVgrow(pw, Priority.ALWAYS);
        Pane mainPane = pw.getMainPane();
        VBox vbox = new VBox();
        Label title = new Label("Datos de los acompanantes");
        title.setStyle("-fx-font-weight: 700;"+ "-fx-font-size: 24px;" + "-fx-line-height: 32px;" );

        vbox.getChildren().addAll(title,addAccordion(4));
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(30,80,40,80));
        vbox.setSpacing(20);



        mainPane.getChildren().add(vbox);
        getChildren().add(pw);
    }

    public Accordion addAccordion(int nCompanion){
        Accordion accordion = new Accordion();
        for (int i = 1; i <= nCompanion; i++) {
            addInfo("Huesped "+i, accordion);
            System.out.println(i);
        }
        return accordion;
    }

    public void addInfo(String nCompanion,Accordion accordion ){
        InputText name = new InputText("Nombre", "Jhon Doe", "", "person");
        InputText id = new InputText("Cedula", "1039382348", "", "person");
        VBox vBox = new VBox();
        vBox.setSpacing(0);
        vBox.getChildren().addAll(name,id);
        TitledPane pane = new TitledPane(nCompanion , vBox);
        accordion.getPanes().add(pane);

    }
}