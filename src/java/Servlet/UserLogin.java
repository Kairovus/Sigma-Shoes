package Servlet;

import Database.DBConnection;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "UserLogin", urlPatterns = {"/login"})
public class UserLogin extends HttpServlet {
    Connection conn;
    PreparedStatement ps;
    ResultSet rs;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("user_sign-in.jsp").forward(request, response);
    }
    
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        try {
            conn = new DBConnection().setConnection();
            ps = conn.prepareStatement("SELECT * FROM users WHERE email=?");
            ps.setString(1, email);
            rs = ps.executeQuery();
            if (rs.next() && password.equals(rs.getString("password"))) {
                HttpSession session = request.getSession();
                session.setAttribute("username", rs.getString("first_name") + " " + rs.getString("last_name"));
                session.setAttribute("isUser", true);
                session.setAttribute("id", rs.getInt("user_id"));
                response.sendRedirect(request.getContextPath()); 
            } else {
                request.getRequestDispatcher("user_sign-in.jsp").forward(request, response);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
