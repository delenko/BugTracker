package sample;


import javafx.scene.control.Button;

import javax.swing.plaf.nimbus.State;
import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class Database {
    public Connection openDatabase() throws SQLException {
        String url = "jdbc:mysql://localhost:3306";
        String user = "root";
        String password = "1qaz!QAZ";
        Connection conn = DriverManager.getConnection(url,user,password);
        return conn;
    }

    public String getDate(String buttonName){
        String date = "";
        try{
            Statement stmt = openDatabase().createStatement();
            String dateRetrieve = "Select date from bugs.bugs where name='"+buttonName+"'";
            ResultSet rs = stmt.executeQuery(dateRetrieve);
            while(rs.next()){ date = rs.getString("date");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return date;
    }
    public String getStatus(String buttonName){
        String status = "";
        try{
            Statement stmt = openDatabase().createStatement();
            String statusRetrieve = "Select status from bugs.bugs where name='"+buttonName+"'";
            ResultSet rs = stmt.executeQuery(statusRetrieve);
            while(rs.next()){
                status = rs.getString("status");
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return status;
    }
    public String getExplanation (String buttonName){
        String explanation = "";
        try{
            Statement stmt = openDatabase().createStatement();
            String explnRetrieve = "Select explanation from bugs.bugs where name ='"+buttonName+"'";
            ResultSet rs = stmt.executeQuery(explnRetrieve);
            while(rs.next()){
                explanation = rs.getString("explanation");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return explanation;
    }



    public List<String> storeDBNames(){
        List<String> databaseNames = new ArrayList<>();
        try {
            Statement mystmt = openDatabase().createStatement();
            String check = "Select name from bugs.bugs";
            ResultSet rs = mystmt.executeQuery(check);
            while (rs.next()) {
                String names = rs.getString("name");

               databaseNames.add(names);
            }
            openDatabase().close();
        } catch(SQLException e) {

            e.printStackTrace();
        }
        return databaseNames;
    }

    public int startDatabase(String name, String date, String status, String explain) throws SQLException {
        try {
            Statement mystmt = openDatabase().createStatement();
            String checkSQL = "Select name,status,date,explanation from bugs.bugs";
            ResultSet rs = mystmt.executeQuery(checkSQL);
                while (rs.next()) {
                    String bugNames = rs.getString("name");
                    if(bugNames.equals(name)) {
                        return 1;
                    }
                }
                rs.beforeFirst();
                while(rs.next()){
                    mystmt.executeUpdate("INSERT INTO bugs.bugs " + "(name,date,status,explanation)" + "values ('" + name + "','" + date + "','" + status + "','" + explain + "')");
                    return 0;
                }
            openDatabase().close();
        } catch(SQLException e) {

            e.printStackTrace();
        }

        return 3;
    }

}
