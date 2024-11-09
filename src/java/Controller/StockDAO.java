package Controller;

import Database.DBConnection;
import Model.ProductStock;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class StockDAO {
    static Connection conn;
    static Statement stmt;
    static PreparedStatement ps;
    static ResultSet rs;
    
    public static int save(ProductStock stock) {
        int status = 0;
        try {
            conn = new DBConnection().setConnection();
            
            // Insert to products Table
            ps = conn.prepareStatement("INSERT INTO product_stocks (product_id, size, quantity)  VALUES (?, ?, ?)  ON DUPLICATE KEY UPDATE quantity = quantity + VALUES(quantity);");
            ps.setInt(1, stock.getProduct_id());
            ps.setInt(2, stock.getSize());
            ps.setInt(3, stock.getQuantity());
            status = ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return status;
    }
    
    
    public static int delete(int id, int size) {
        int status = 0;
        try {
            conn = new DBConnection().setConnection();
            
            ps = conn.prepareStatement("DELETE FROM product_stocks WHERE product_id=? AND size=?");
            ps.setInt(1, id);
            ps.setInt(2, size);
            status = ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return status;
    }
}
