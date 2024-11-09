package Controller;

import Database.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CartDAO {

    public static int getOrderId() {
        Connection conn;
        ResultSet rs;
        int order_id = 0;
        try {
            conn = new DBConnection().setConnection();
            rs = conn.createStatement().executeQuery("SELECT MAX(order_id) FROM orders");
            if (rs.next()) {
                order_id = rs.getInt(1) + 1;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return order_id;
    }

    public static int saveOrder(int order_id, int user_id, double total_price) {
        Connection conn;
        int status = 0;
        try {
            conn = new DBConnection().setConnection();

            // Insert to table order
            PreparedStatement ps = conn.prepareStatement("INSERT INTO orders (order_id, user_id, total_amount) VALUES (?, ?, ?)");
            ps.setInt(1, order_id);
            ps.setInt(2, user_id);
            ps.setDouble(3, total_price);
            status = ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return status;
    }

    public static int saveOrderItems(int order_id, int product_id, int size, int qty) {
        Connection conn;
        PreparedStatement ps;
        int status = 0;
        try {
            conn = new DBConnection().setConnection();
            ps = conn.prepareStatement("INSERT INTO order_items (order_id, product_id, size, quantity) VALUES (?, ?, ?, ?)");
            ps.setInt(1, order_id);
            ps.setInt(2, product_id);
            ps.setInt(3, size);
            ps.setInt(4, qty);
            status = ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return status;
    }

    public static int decreaseStock(int product_id, int size, int qty) {
        Connection conn;
        PreparedStatement ps;
        int status = 0;
        try {
            conn = new DBConnection().setConnection();
            ps = conn.prepareStatement("UPDATE product_stocks SET quantity = quantity - ? WHERE product_id = ? AND size = ?");
            ps.setInt(1, qty);
            ps.setInt(2, product_id);
            ps.setInt(3, size);
            status = ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return status;
    }
}
