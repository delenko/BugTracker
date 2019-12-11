package sample;
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
            //ResultSet rs = mystmt.executeQuery(sql);
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
