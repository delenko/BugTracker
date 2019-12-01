package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

import javax.xml.soap.Text;
import java.net.URL;
import java.util.ResourceBundle;


public class Controller{
    @FXML
    private BorderPane bp;
    @FXML
    private GridPane gp;


    @FXML
    private void buttonClick(ActionEvent event) {
        TextArea ta = new TextArea();
        Label bugName = new Label("Name");
        Label bugDate= new Label("Date");
        Label bugStatus = new Label("Status");
        Label explain = new Label("Explanation");
        TextField nameField = new TextField();
        TextField dateField = new TextField();
        Button submit = new Button("Submit");
        gp.add(bugName,0,0);
        gp.add(bugDate,0,1);
        gp.add(bugStatus,0,2);
        gp.add(explain,0,3);
        gp.add(nameField,1,0);
        gp.add(dateField,1,1);
        gp.add(submit,1,4);
        gp.setHalignment(submit, HPos.CENTER);
        gp.add(ta,1,3);
        System.out.println("Hey");


    }
}
