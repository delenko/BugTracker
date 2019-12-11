package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

import javax.xml.soap.Text;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;


public class Controller{
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
        ComboBox<String> statuses = new ComboBox<>();
        statuses.getItems().addAll("Development","Test","QA","Release");
        DatePicker datePicker = new DatePicker();
        gp.add(datePicker,1,1);
        gp.add(bugName,0,0);
        gp.add(bugDate,0,1);
        gp.add(bugStatus,0,2);
        gp.add(explain,0,3);
        gp.add(nameField,1,0);
        gp.add(submit,1,4);
        gp.add(statuses,1,2);
        gp.setHalignment(submit, HPos.CENTER);
        gp.add(ta,1,3);
        System.out.println("Hey");

        submit.setOnAction((e)-> {
            try{
           Database db = new Database();
            String name = nameField.getText();
           LocalDate dates = datePicker.getValue();
           String date = dates.toString();
           String status = statuses.getValue().toString();
           String explanation = ta.getText();
           System.out.println(name+" "+date+" "+status+" "+explanation);
           db.startDatabase(name, date, status, explanation);
            }catch(NullPointerException ex){
                ex.printStackTrace();
            }
        });
        };



    private void moveData(ActionEvent event) {
    }

    public void moveData(String name, String date, String status, String explain){

    }
}
