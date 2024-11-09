package Controller;

import Database.DBConnection;
import Model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class UserDAO {
    static Connection conn;
    static PreparedStatement ps;
    
    public static int save(User user) {
        int status = 0;
        try {
            conn = new DBConnection().setConnection();
            
            // Insert to products Table
            ps = conn.prepareStatement("INSERT INTO users (first_name, last_name, email, password, phone_number, address) VALUES (?, ?, ?, ?, ?, ?)");
            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getPassword());
            ps.setString(5, user.getPhone());
            ps.setString(6, user.getAddress());
            status = ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return status;
    }
}
