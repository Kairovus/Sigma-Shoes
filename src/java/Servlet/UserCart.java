package Servlet;

import Controller.CartDAO;
import Controller.ProductDAO;
import Model.Cart;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "UserCart", urlPatterns = {"/cart"})
public class UserCart extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        boolean isUser = session != null && session.getAttribute("username") != null && session.getAttribute("isUser").equals(true);
        if (isUser) {
            request.getRequestDispatcher("cart_page.jsp").forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/login");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Variables declaration
        List<Cart> carts = new ArrayList<>();
        int product_id, size, qty, user_id, order_id = 0;
        double total_price = 0.0;

        // Get the cart values
        for (int i = 0; i < request.getParameterValues("product_id").length; i++) {
            Cart cart = new Cart();
            product_id = new Integer(request.getParameterValues("product_id")[i]);
            size = new Integer(request.getParameterValues("size")[i]);
            qty = new Integer(request.getParameterValues("quantity")[i]);
            cart.setId(product_id);
            cart.setSize(size);
            cart.setQty(qty);
            carts.add(cart);
        }

        // Update the total_price variable
        user_id = (int) request.getSession().getAttribute("id");
        for (Cart c : carts) {
            total_price += ProductDAO.getPrice(c.getId());
        }

        // Get the order_id
        order_id = CartDAO.getOrderId();

        // Insert to table orders
        int result = CartDAO.saveOrder(order_id, user_id, total_price);
        if (result <= 0) {
            request.setAttribute("errorMsg", "Error Message");
        }

        // Insert to order_items table and update the product's stocks
        int result2 = 0, result3 = 0;
        for (Cart c : carts) {
            result2 = CartDAO.saveOrderItems(order_id, c.getId(), c.getSize(), c.getQty());
            result3 = CartDAO.decreaseStock(c.getId(), c.getSize(), c.getQty());
            if (result2 <= 0 || result3 <= 0) {
                request.setAttribute("errorMsg", "Error Message");
            }
        }

        // Clear the cart session and update the product_stock table
        List<Cart> cartSession = new ArrayList<>();
        HttpSession session = request.getSession(false);
        session.setAttribute("cart", cartSession);
        response.sendRedirect(request.getContextPath());

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
