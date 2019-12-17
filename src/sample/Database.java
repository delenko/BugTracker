package sample;
import com.mysql.cj.protocol.Resultset;

import javax.xml.transform.Result;
import java.sql.*;

public class Database {

    public void startDatabase(String name, String date, String status, String explain){
        String url = "jdbc:mysql://localhost:3306";
        String user = "root";
        String password = "Blakes12!@";
        try {

            Connection conn = DriverManager.getConnection(url,user,password);
            Statement mystmt = conn.createStatement();
            String sql = "";
            String checkSQL = "Select name from bugs.bugs";
            ResultSet rs = mystmt.executeQuery(checkSQL);
            while(rs.next()){
                if(rs.getString("name").equals(name)){
                    
                    return;
                }else{
                    System.out.println(rs.getString("name"));
                    rs.close();
                    mystmt.executeUpdate("INSERT INTO bugs.bugs "+"(name,date,status,explanation)"+"values ('"+name+"','"+date+"','"+status+"','"+explain+"')");
                }
            }



            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
