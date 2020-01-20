package sample;

import com.sun.org.apache.xpath.internal.operations.Bool;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;

import javax.xml.soap.Text;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class Controller{
    public Controller(){

    }
    private List<Button> buttons = new ArrayList<>();

    @FXML
    private GridPane gp;
    @FXML
    private VBox leftBP;


    EventHandler<MouseEvent> eventHandler = event -> {
        gp.getChildren().clear();
        Database db = new Database();
        Label dateRetrieved = new Label(db.getDate(((Button)event.getSource()).getText()));
        Label name = new Label(((Button)event.getSource()).getText());
        Label status = new Label(db.getStatus(((Button)event.getSource()).getText()));
        Label explanation = new Label(db.getExplanation(((Button)event.getSource()).getText()));
        explanation.setAlignment(Pos.TOP_LEFT);
        Label nameCat = new Label("Name:");
        Label dateCat = new Label("Date:");
        Label statusCat = new Label("Status:");
        Label expCat = new Label("Explanation:");
        expCat.setAlignment(Pos.TOP_LEFT);
        Button delete = new Button("Delete");
        Button update = new Button("Update");
        HBox hbox = new HBox();
        hbox.setAlignment(Pos.CENTER_LEFT);
        hbox.getChildren().add(delete);
        hbox.setAlignment(Pos.CENTER_RIGHT);
        hbox.getChildren().add(update);
        gp.setAlignment(Pos.TOP_LEFT);
        gp.add(nameCat,0,0);
        gp.add(dateCat,0,1);
        gp.add(statusCat,0,2);
        gp.add(expCat,0,3);
        gp.add(name,1,0);
        gp.add(hbox,1,4);
        gp.add(dateRetrieved,1,1);
        gp.add(status,1,2);
        gp.add(explanation,1,3);
        delete.setOnAction(event1 -> {
            gp.getChildren().removeAll(dateRetrieved,name,status,explanation);
            db.delete(((Button)event.getSource()).getText());
            createButtons(db.storeDBNames());
        });


    };


    @FXML
    public void createButtons(List<String>buttonNames ){
        leftBP.getChildren().clear();
        List<String>buttonsList = new ArrayList<>();
        for(int j = 0;j<buttons.size();j++){
            buttonsList.add(buttons.get(j).getText());
        }
        if((buttonNames.size()-1)==buttons.size()){
                for (int i = 0;i<buttons.size();i++)
                if(!buttonsList.contains(buttonNames.get(i))) {
                    buttons.add(new Button(buttonNames.get(i)));
                }
        }else if(buttonNames.size()!=buttons.size()){
            for (int i = 0;i<buttonNames.size();i++) {
                buttons.add(new Button(buttonNames.get(i)));
                buttons.get(i).addEventHandler(MouseEvent.MOUSE_CLICKED,eventHandler);
            }
        }
        for(Button butts : buttons){
            leftBP.getChildren().add(butts);
        }
    }


    @FXML
    private void buttonClick(ActionEvent event) {
        TextArea ta = new TextArea();
        Label bugName = new Label("Name:");
        Label bugDate= new Label("Date:");
        Label bugStatus = new Label("Status:");
        Label explain = new Label("Explanation:");
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
        GridPane.setHalignment(submit, HPos.CENTER);
        gp.add(ta,1,3);


        submit.setOnAction((e)-> {
            try{
           Database db = new Database();
            String name = nameField.getText();
           LocalDate dates = datePicker.getValue();
           String date = dates.toString();
           String status = statuses.getValue();
           String explanation = ta.getText();
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
        }


    public void showAll(ActionEvent actionEvent) {
        Database dbs = new Database();
        createButtons(dbs.storeDBNames());
    }
}
