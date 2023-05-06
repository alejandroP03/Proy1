package View.Screens.EmployeeScreen;

import Controller.Controller;
import View.Components.PrincipalWindow.PrinicipalWindow;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.*;
import javafx.scene.text.Text;

import java.util.List;

public class PayService extends VBox {

    boolean isPrincipal = true;
    PayServiceForm payForm;
    ToggleButton switchYesNoBtn = new ToggleButton() {
        {
            setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent arg0) {
                    switchYesNo();
                }

            });
            setId("btn-container");
        }
    };
    StackPane switchYesNoContainer = new StackPane() {
        {
            getChildren().add(switchYesNoBtn);
            setAlignment(Pos.BOTTOM_CENTER);
            setMargin(switchYesNoBtn, new Insets(0, 50, 50, 0));
        }
    };


    public PayService(Controller controller, PrinicipalWindow prinicipalWindow, List<String> addedServices, boolean isService){
        this.payForm = new PayServiceForm(isPrincipal, addedServices, isService);
        getStylesheets().add("View/Styles/employee/payService.css");
        //PrinicipalWindow pw = new PrinicipalWindow<Pane>("employee", new Pane());
        setVgrow(prinicipalWindow, Priority.ALWAYS);
        prinicipalWindow.setPadding(new Insets(15));


        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(30, 0, 40, 30));

        Text header = new Text("Pagar servicio");
        header.setId("header-pay-service");

        Label isPrLabel = new Label("¿Es huésped principal?");
        isPrLabel.setId("is-principal");


        HBox toggleHbox = new HBox();




        toggleHbox.getChildren().addAll(new Label("No"), switchYesNoBtn, new Label("Sí"));
        toggleHbox.setSpacing(10);

        BorderPane liText = new BorderPane();
        VBox vbText = new VBox();
        vbText.getChildren().addAll(header,isPrLabel);
        VBox leftInfo = new VBox();

        leftInfo.getChildren().addAll(vbText,toggleHbox,payForm);
        leftInfo.setAlignment(Pos.CENTER_LEFT);

        leftInfo.setSpacing(15);
        borderPane.setLeft(leftInfo);

        VBox rightInfo = new VBox();

        prinicipalWindow.setContent(leftInfo);

        getChildren().add(prinicipalWindow);



    }

    private void switchYesNo() {
        isPrincipal = !isPrincipal;

        payForm.switchForm(isPrincipal);

    }


    public VBox leftBorder(){
        Text title = new Text("Pagar servicio");
        VBox form =  new VBox();

        form.setId("pay-service");
        form.setSpacing(10);
        form.setPadding(new Insets(30));


        return form;

    }
}
