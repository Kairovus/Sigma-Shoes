package Controller;

import Database.DBConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Model.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Base64;

public class ProductDAO {
    static Connection conn;
    static Statement stmt;
    static PreparedStatement ps;
    static ResultSet rs;
    
    public static int save(Product product) {
        int status = 0;
        try {
            conn = new DBConnection().setConnection();
            
            // Insert to products Table
            ps = conn.prepareStatement("INSERT INTO products (name, description, price, category_id, brand, color, sex, img) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
            ps.setString(1, product.getName());
            ps.setString(2, product.getDesc());
            ps.setFloat(3, product.getPrice());
            ps.setInt(4, product.getCategory());
            ps.setString(5, product.getBrand());
            ps.setString(6, product.getColor());
            ps.setString(7, product.getSex());
            ps.setBlob(8, product.getISImage());
            status = ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return status;
    }
    
    public static int edit(Product product) {
        int status = 0;
        try {
            conn = new DBConnection().setConnection();
            
            // Update to products Table
            ps = conn.prepareStatement("UPDATE products SET name = ?, description = ?, price = ?, category_id = ?, brand = ?, color = ?, sex = ?, img = ? WHERE product_id = ?");
            ps.setString(1, product.getName());
            ps.setString(2, product.getDesc());
            ps.setFloat(3, product.getPrice());
            ps.setInt(4, product.getCategory());
            ps.setString(5, product.getBrand());
            ps.setString(6, product.getColor());
            ps.setString(7, product.getSex());
            ps.setBlob(8, product.getISImage());
            ps.setInt(9, product.getId());
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
            ps = conn.prepareStatement("DELETE FROM order_items WHERE product_id=?");
            ps.setInt(1, id);
            status = ps.executeUpdate();
            ps = conn.prepareStatement("DELETE FROM product_stocks WHERE product_id=?");
            ps.setInt(1, id);
            status = ps.executeUpdate();
            ps = conn.prepareStatement("DELETE FROM products WHERE product_id=?");
            ps.setInt(1, id);
            status = ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return status;
    }
    
    public static Product getSingleRecords(int id) {
        Product u = new Product();
        try {
            conn = new DBConnection().setConnection();
            ps = conn.prepareStatement("SELECT * FROM products WHERE product_id=?");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                u.setId(rs.getInt("product_id"));
                u.setName(rs.getString("name"));
                u.setDesc(rs.getString("description"));
                u.setPrice(rs.getFloat("price"));
                u.setCategory(rs.getInt("category_id"));
                u.setBrand(rs.getString("brand"));
                u.setColor(rs.getString("color"));
                u.setSex(rs.getString("sex"));
                
                byte[] imageData = rs.getBytes("img");
                String base64Image = Base64.getEncoder().encodeToString(imageData);
                u.setB64Image(base64Image);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return u;
    }
    
    public static double getPrice(int id) {
        double price = 0.0;
        try {
            conn = new DBConnection().setConnection();
            ps = conn.prepareStatement("SELECT price FROM products WHERE product_id=?");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                price = rs.getDouble(1);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return price;
    }
    
    
    public static List<Product> getAllRecords() {
        List<Product> list = new ArrayList<>();

        try {
            conn = new DBConnection().setConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM products");
            
            while (rs.next()) {
                Product u = new Product();
                u.setId(rs.getInt("product_id"));
                u.setName(rs.getString("name"));
                u.setDesc(rs.getString("description"));
                u.setPrice(rs.getFloat("price"));
                u.setCategory(rs.getInt("category_id"));
                u.setBrand(rs.getString("brand"));
                u.setColor(rs.getString("color"));
                u.setSex(rs.getString("sex"));
                
                byte[] imageData = rs.getBytes("img");
                String base64Image = Base64.getEncoder().encodeToString(imageData);
                u.setB64Image(base64Image);
                
                list.add(u);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }
    
    
    public static List<Product> getAllRecordsNotEmpty() {
        List<Product> list = new ArrayList<>();

        try {
            conn = new DBConnection().setConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM products p WHERE EXISTS (SELECT size FROM product_stocks ps WHERE p.product_id = ps.product_id)");
            
            while (rs.next()) {
                Product u = new Product();
                u.setId(rs.getInt("product_id"));
                u.setName(rs.getString("name"));
                u.setDesc(rs.getString("description"));
                u.setPrice(rs.getFloat("price"));
                u.setCategory(rs.getInt("category_id"));
                u.setBrand(rs.getString("brand"));
                u.setColor(rs.getString("color"));
                u.setSex(rs.getString("sex"));
                
                byte[] imageData = rs.getBytes("img");
                String base64Image = Base64.getEncoder().encodeToString(imageData);
                u.setB64Image(base64Image);
                
                list.add(u);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }
    
    public static List<Product> searchProduct(String name) {
        List<Product> list = new ArrayList<>();

        try {
            conn = new DBConnection().setConnection();
            ps = conn.prepareStatement("SELECT * FROM products WHERE name LIKE ?");
            ps.setString(1, "%" + name + "%");
            rs = ps.executeQuery();
            
            while (rs.next()) {
                Product u = new Product();
                u.setId(rs.getInt("product_id"));
                u.setName(rs.getString("name"));
                u.setDesc(rs.getString("description"));
                u.setPrice(rs.getFloat("price"));
                u.setCategory(rs.getInt("category_id"));
                u.setBrand(rs.getString("brand"));
                u.setColor(rs.getString("color"));
                u.setSex(rs.getString("sex"));
                
                byte[] imageData = rs.getBytes("img");
                String base64Image = Base64.getEncoder().encodeToString(imageData);
                u.setB64Image(base64Image);
                
                list.add(u);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }
}
