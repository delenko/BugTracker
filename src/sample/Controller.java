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
        Button nameButton = new Button("Modify");
        Button statusButton = new Button("Modify");
        Button dateButton = new Button("Modify");
        Button explanationButton = new Button("Modify");
        TextField tf = new TextField("Modify name text");
        ComboBox<String> statuses = new ComboBox<>();
        statuses.getItems().addAll("Development","Test","QA","Release");
        expCat.setAlignment(Pos.TOP_LEFT);
        DatePicker datePicker = new DatePicker();
        TextArea ta = new TextArea("Modify explanation text");
        ta.setWrapText(true);
        Button delete = new Button("Delete");
        Button update = new Button("Update");
        HBox hbox = new HBox();
        HBox hboxName = new HBox(10,name,nameButton);
        HBox hBoxStatus = new HBox(10,status,statusButton);
        HBox hBoxDate = new HBox(10,dateRetrieved,dateButton);
        HBox hBoxExplanation = new HBox(5,explanation,explanationButton);
        hbox.setAlignment(Pos.CENTER_LEFT);
        hbox.getChildren().add(delete);
        hbox.setAlignment(Pos.CENTER_RIGHT);
        hbox.getChildren().add(update);
        gp.setAlignment(Pos.TOP_LEFT);
        gp.add(nameCat,0,0);
        gp.add(dateCat,0,1);
        gp.add(statusCat,0,2);
        gp.add(expCat,0,3);
        gp.add(hboxName,1,0);
        gp.add(hBoxDate,1,1);
        gp.add(hBoxStatus,1,2);
        gp.add(hBoxExplanation,1,3);
        gp.add(hbox,1,4);
        nameButton.setOnAction(event2->{
            hboxName.getChildren().clear();
            hboxName.getChildren().add(tf);
        });
        statusButton.setOnAction(event3->{
            hBoxStatus.getChildren().clear();
            hBoxStatus.getChildren().add(statuses);
        });
        dateButton.setOnAction(event4->{
            hBoxDate.getChildren().clear();
            hBoxDate.getChildren().add(datePicker);
        });
        explanationButton.setOnAction(event5->{
            hBoxExplanation.getChildren().clear();
            hBoxExplanation.getChildren().add(ta);
        });
        delete.setOnAction(event1 -> {
            gp.getChildren().removeAll(dateRetrieved,name,status,explanation);
            db.delete(((Button)event.getSource()).getText());
            leftBP.getChildren().remove((Button)event.getSource());
            createNewButtons(db.storeDBNames());
            gp.getChildren().removeAll(nameCat,dateCat,statusCat,expCat,hboxName,hBoxDate,hBoxExplanation,hBoxStatus);
        });
        update.setOnAction(event6 -> {
            if(hboxName.getChildren().contains(tf)) {
                db.setName(name.getText(), tf.getText());
                leftBP.getChildren().remove((Button) event.getSource());
                buttons.add(new Button(tf.getText()));
                leftBP.getChildren().clear();
                for(Button buttNames : buttons){
                    leftBP.getChildren().add(buttNames);
                }
            }
            if(hBoxDate.getChildren().contains(datePicker)){
                LocalDate dateModified = datePicker.getValue();
                db.setDate(name.getText(),dateModified.toString());
            }
            if(hBoxStatus.getChildren().contains(statuses))
                db.setStatus(name.getText(),statuses.getValue());
            if(hBoxExplanation.getChildren().contains(ta.getText()))
                db.setExplan(name.getText(),tf.getText());


            createNewButtons(db.storeDBNames());
            gp.getChildren().removeAll(nameCat,dateCat,statusCat,expCat,hboxName,hBoxDate,hBoxExplanation,hBoxStatus);
        });
    };

    public void createNewButtons(List<String>buttonName){
        leftBP.getChildren().clear();

        if(buttons.size()==buttonName.size()){
           ;return;
        }

        else if(buttons.size()-1==buttonName.size()){
            buttons.clear();
            for(int i =0;i<buttonName.size();i++){
                buttons.add(new Button(buttonName.get(i)));
                buttons.get(i).addEventHandler(MouseEvent.MOUSE_CLICKED,eventHandler);
                leftBP.getChildren().add(buttons.get(i));
            }
        }
    }


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
                    buttons.get(i).addEventHandler(MouseEvent.MOUSE_CLICKED,eventHandler);
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
        gp.getChildren().clear();
        TextArea ta = new TextArea();
        ta.setWrapText(true);
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
            gp.getChildren().remove(nameField);
            gp.getChildren().remove(bugName);
            gp.getChildren().remove(statuses);
            gp.getChildren().remove(bugDate);
            gp.getChildren().remove(bugStatus);
            gp.getChildren().remove(explain);
            gp.getChildren().remove(submit);
            gp.getChildren().remove(datePicker);
            gp.getChildren().remove(ta);
        });

        }


    public void showAll(ActionEvent actionEvent) {
        Database dbs = new Database();
        createButtons(dbs.storeDBNames());
    }
}
