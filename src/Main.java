import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class Main {
    Properties p = new Properties();


    public Main() {


        loadProperties();
        connectToAndQueryDatabase(p.getProperty("username"), p.getProperty("password"));


    }

    private void loadProperties() {
        try {
            p.load(new FileInputStream("resources/settings.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void connectToAndQueryDatabase(String username, String password) {
        try (Connection con = DriverManager.getConnection(p.getProperty("connectionString"), username, password)) {
            // Statement stmt = con.createStatement();
            // ResultSet rs = stmt.executeQuery("SELECT a,b,c FROM TABLE1");

            //  while (rs.next()){
            //      int x = rs.getInt("a");
            //     String s = rs.getString("b");
            //       float f = rs.getFloat("c");
            //  }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Main();
    }

}