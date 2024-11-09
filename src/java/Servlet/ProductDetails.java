package Servlet;

import Controller.ProductDAO;
import Model.Cart;
import Model.Product;
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

@WebServlet(name = "ProductDetails", urlPatterns = {"/product_details"})
public class ProductDetails extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int productId = new Integer(request.getParameter("product"));
        Product product = ProductDAO.getSingleRecords(productId);
        request.setAttribute("product", product);
        request.getRequestDispatcher("item_details.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Cart> carts = new ArrayList<>();
        HttpSession session = request.getSession(false); 
        
        if (session != null && session.getAttribute("isUser") != null) {
            if (session.getAttribute("cart") != null) {
                carts = (ArrayList) session.getAttribute("cart");
            }
            Cart cart = new Cart();
            int size = new Integer(request.getParameter("size")); 
            int id = new Integer(request.getParameter("product"));
            cart.setSize(size);
            cart.setId(id);
            carts.add(cart);
            session.setAttribute("cart", carts);
            response.sendRedirect(request.getContextPath() + "/cart");
        } else {
            response.sendRedirect(request.getContextPath() + "/login");
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
