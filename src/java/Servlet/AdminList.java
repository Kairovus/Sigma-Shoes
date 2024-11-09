package Servlet;

import Controller.AdminDAO;
import Model.Admin;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "AdminList", urlPatterns = {"/admin/admin_list"})
public class AdminList extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getParameter("id") != null) {
            final int id = new Integer(request.getParameter("id"));
            int result = AdminDAO.delete(id);
            if (result > 0) {
                response.sendRedirect(request.getContextPath() + "/admin/admin_list");
                return;
            } else {
                request.setAttribute("errorMsg", "Error Message");
                return;
            }
        }
        request.getRequestDispatcher("../admin_list.jsp").forward(request, response);
    }
    
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("DISINI");
        Admin admin = new Admin();
        admin.setUsername(request.getParameter("username"));
        admin.setPassword(request.getParameter("password"));
        admin.setEmail(request.getParameter("email"));
        int result = AdminDAO.save(admin);
            if (result > 0) {
                response.sendRedirect(request.getContextPath() + "/admin/admin_list");
            } else {
                request.setAttribute("errorMsg", "Error Message");
            }
    }
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
