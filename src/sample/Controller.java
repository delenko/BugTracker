package sample;

import com.sun.org.apache.xpath.internal.operations.Bool;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import javax.xml.soap.Text;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class Controller{
    public Controller(){

    }
    @FXML
    private GridPane gp;
    @FXML
    private VBox leftBP;
    @FXML
    private Button add;

    @FXML
    public void createButtons(List<String>buttonNames ){
        leftBP.setSpacing(2.0);
        leftBP.getChildren().removeAll();
        List<Button> buttons = new ArrayList<>();
        for(String buttonName : buttonNames) {
            for(int j =0;j<buttons.size();j++){
                if(!buttons.get(j).getText().equals(buttonName)){
                    buttons.add(new Button(buttonName));
                    leftBP.getChildren().add(buttons.get(j));
                }
            }
            //buttons.add(new Button(buttonName));
        }
       /* for(int i = 0;i<buttons.size();i++){
            if(buttons.get(i)==null){
                leftBP.getChildren().add(buttons.get(i));
            }

        }*/
    }


    @FXML
    private void buttonClick(ActionEvent event) {
        TextArea ta = new TextArea();
        Label bugName = new Label("Name");
        Label bugDate= new Label("Date");
        Label bugStatus = new Label("Status");
        Label explain = new Label("Explanation");
        TextField nameField = new TextField();
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
           int start = db.startDatabase(name, date, status, explanation);
           Alert errors1 = new Alert(Alert.AlertType.ERROR,"You have entered this name before. Please change name!");

            if(start ==0){
                createButtons(db.storeDBNames());
            }
            else if(start ==1 ) {
                errors1.showAndWait();
            }
            }catch(NullPointerException | SQLException ex){
                ex.printStackTrace();
            }
        });
        };


}
