package Database;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    private Connection conn = null;
    public Connection setConnection() {
        try {
            String url = "jdbc:mysql://localhost:3306/sigma_shoes";
            String user = "root";
            String password = "";
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
}
