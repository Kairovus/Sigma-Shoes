package Servlet;

import Database.DBConnection;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

@MultipartConfig(maxFileSize = 16177215) // 16MB
@WebServlet(name = "AdminServlet", urlPatterns = {"/admin"})
public class AdminServlet extends HttpServlet {
    Connection conn;
    PreparedStatement ps;
    ResultSet rs;

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("admin_login.jsp").forward(request, response);
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        try {
            conn = new DBConnection().setConnection();
            ps = conn.prepareStatement("SELECT * FROM admin WHERE email=?");
            ps.setString(1, email);
            rs = ps.executeQuery();
            if (rs.next() && password.equals(rs.getString("password"))) {
                HttpSession session = request.getSession();
                session.setAttribute("adminName", rs.getString("username"));
                session.setAttribute("isAdmin", true);
                response.sendRedirect(request.getContextPath() + "/admin/dashboard"); 
            } else {
                request.getRequestDispatcher("login.jsp").forward(request, response);
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
