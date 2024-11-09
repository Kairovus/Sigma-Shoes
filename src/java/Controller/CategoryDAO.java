package Controller;

import Database.DBConnection;
import Model.Category;
import Model.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class CategoryDAO {
    static Connection conn;
    static Statement stmt;
    static PreparedStatement ps;
    static ResultSet rs;
    
    public static int save(Category category) {
        int status = 0;
        try {
            conn = new DBConnection().setConnection();
            
            // Insert to products Table
            ps = conn.prepareStatement("INSERT INTO categories (name, description) VALUES (?, ?)");
            ps.setString(1, category.getName());
            ps.setString(2, category.getDescription());
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
            ps = conn.prepareStatement("DELETE FROM categories WHERE category_id=?");
            ps.setInt(1, id);
            status = ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return status;
    }
    
    
    public static List<Category> getAllRecords() {
        List<Category> list = new ArrayList<>();

        try {
            conn = new DBConnection().setConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM categories");
            
            while (rs.next()) {
                Category u = new Category();
                u.setId(rs.getInt("category_id"));
                u.setName(rs.getString("name"));
                u.setDescription(rs.getString("description"));
                list.add(u);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }
}
