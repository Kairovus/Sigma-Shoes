package Servlet;

import Controller.UserDAO;
import Model.User;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "UserRegister", urlPatterns = {"/register"})
public class UserRegister extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("user_register.jsp").forward(request, response);
    }
    
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User user = new User();
        user.setFirstName(request.getParameter("first"));
        user.setLastName(request.getParameter("last"));
        user.setEmail(request.getParameter("email"));
        user.setPassword(request.getParameter("password"));
        user.setPhone(request.getParameter("phone"));
        user.setAddress(request.getParameter("address"));
        
        int result = UserDAO.save(user);
            if (result > 0) {
                response.sendRedirect(request.getContextPath() + "/login");
            } else {
                request.setAttribute("errorMsg", "Error Message");
            }
    }
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }
}