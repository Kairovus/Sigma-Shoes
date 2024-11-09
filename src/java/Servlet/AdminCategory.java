package Servlet;

import Controller.CategoryDAO;
import Model.Category;
import Validator.ProductValidator;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "AdminCategory", urlPatterns = {"/admin/category_list"})
public class AdminCategory extends HttpServlet {

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getParameter("operation")!= null) {
            final int id = new Integer(request.getParameter("id"));
            int result = CategoryDAO.delete(id);
            if (result > 0) {
                response.sendRedirect(request.getContextPath() + "/admin/category_list");
                return;
            } else {
                request.setAttribute("errorMsg", "Error Message");
                return;
            }
        } else {
            request.getRequestDispatcher("../admin_category.jsp").forward(request, response);
        }
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int result = 0;
        Category category = new Category();
        category.setName(request.getParameter("name"));
        category.setDescription(request.getParameter("description"));
        
        result = CategoryDAO.save(category);
            if (result > 0) {
                response.sendRedirect(request.getContextPath() + "/admin/category_list");
            } else {
                request.setAttribute("errorMsg", "Error Message");
            }
        
        String errorMessage = ProductValidator.validateCategory(category);
        if (errorMessage != null) {
            response.sendRedirect(request.getContextPath() + "/admin/category_list");
            return;
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
