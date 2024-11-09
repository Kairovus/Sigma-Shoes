package Servlet;

import Controller.StockDAO;
import Model.ProductStock;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "AdminStock", urlPatterns = {"/admin/stock_list"})
public class AdminStock extends HttpServlet {

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getParameter("id") != null && request.getParameter("size") != null) {
            int id = new Integer(request.getParameter("id"));
            int size = new Integer(request.getParameter("size"));
            int result = StockDAO.delete(id, size);
            if (result > 0) {
                response.sendRedirect(request.getContextPath() + "/admin/stock_list");
                return;
            } else {
                request.setAttribute("errorMsg", "Error Message");
                return;
            }
        }
        request.getRequestDispatcher("../admin_stock.jsp").forward(request, response);
    }
    
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ProductStock stock = new ProductStock();
        stock.setProduct_id(new Integer(request.getParameter("product_id")));
        stock.setSize(new Integer(request.getParameter("size")));
        stock.setQuantity(new Integer(request.getParameter("quantity")));
        
        int result = StockDAO.save(stock);
        if (result > 0) {
            response.sendRedirect(request.getContextPath() + "/admin/stock_list");
        } else {
            request.setAttribute("errorMsg", "Error Message");
        }
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
