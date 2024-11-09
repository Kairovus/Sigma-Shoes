package Controller;

import Database.DBConnection;
import Model.Admin;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class AdminDAO {
    static Connection conn;
    static Statement stmt;
    static PreparedStatement ps;
    static ResultSet rs;
    
    public static int save(Admin admin) {
        int status = 0;
        try {
            conn = new DBConnection().setConnection();
            
            // Insert to products Table
            ps = conn.prepareStatement("INSERT INTO admin (username, password, email) VALUES (?, ?, ?)");
            ps.setString(1, admin.getUsername());
            ps.setString(2, admin.getPassword());
            ps.setString(3, admin.getEmail());
            status = ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return status;
    }
    
    
    public static int delete(int id) {
        int status = 0;
        try {
            conn = new DBConnection().setConnection();
            ps = conn.prepareStatement("DELETE FROM admin WHERE admin_id=?");
            ps.setInt(1, id);
            status = ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return status;
    }
    
    
    public static List<Admin> getAllRecords() {
        List<Admin> list = new ArrayList<>();

        try {
            conn = new DBConnection().setConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM admin");
            
            while (rs.next()) {
                Admin u = new Admin();
                u.setId(rs.getInt("admin_id"));
                u.setUsername(rs.getString("username"));
                u.setPassword(rs.getString("password"));
                u.setEmail(rs.getString("email"));
                list.add(u);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }
}
