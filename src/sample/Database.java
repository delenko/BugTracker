package sample;



import javafx.scene.control.Button;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class Database {
    public Connection openDatabase() throws SQLException {
        String url = "jdbc:mysql://localhost:3306";
        String user = "root";
        String password = "Blakes12!@";
        Connection conn = DriverManager.getConnection(url,user,password);
        return conn;
    }
    public void delete(String name){
        try{
            Statement statement = openDatabase().createStatement();
            String nameRetrieve = "Delete from bugs.bugs where bugId in (Select * from (Select bugId from bugs.bugs where name = '"+name+"') AS T);";
            statement.executeUpdate(nameRetrieve);
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
    public void setName (String name, String nameChange){
        try{
            Statement stmt = openDatabase().createStatement();
            String nameRetrieve = "Update bugs.bugs SET NAME = '"+nameChange+"' where name ='"+name+"'";
            stmt.executeUpdate(nameRetrieve);
        }catch(SQLException e){
            e.printStackTrace();
        }

    };

    public void setDate(String name, String dateChange){
        try{
            Statement stmt = openDatabase().createStatement();
            String dates = "Update bugs.bugs SET date = '"+dateChange+"' where name ='"+name+"'";
            stmt.executeUpdate(dates);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    public void setStatus(String name, String statusChange){
        try{
            Statement stmt = openDatabase().createStatement();
            String statuses = "Update bugs.bugs SET status = '"+statusChange+"' where name ='"+name+"'";
            stmt.executeUpdate(statuses);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    public void setExplan(String name, String explanChange){
        try{
            Statement stmt = openDatabase().createStatement();
            String explanations = "Update bugs.bugs SET explanation = '"+explanChange+"' where name ='"+name+"'";
            stmt.executeUpdate(explanations);
        }catch(SQLException e){
            e.printStackTrace();
        }
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
