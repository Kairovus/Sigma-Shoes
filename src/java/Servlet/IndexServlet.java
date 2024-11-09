package Servlet;

import Database.DBConnection;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "IndexServlet", urlPatterns = {"/home"}) 
public class IndexServlet extends HttpServlet {
    Connection conn;
    PreparedStatement ps;
    String query;
    ResultSet rs;
   
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getParameter("name") != null) {
            request.setAttribute("name", request.getParameter("name"));
        }
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        try {
            conn = new DBConnection().setConnection();
            
            query = "SELECT * FROM admin WHERE email=?";
            ps = conn.prepareStatement(query);
            ps.setString(1, email);
            rs = ps.executeQuery();
            
            if (rs.next() && rs.getString("password").equals(password)) {
                response.sendRedirect(request.getContextPath() + "/admin");
            } else {
                request.setAttribute("errorMsg", "Wrong password");
                request.getRequestDispatcher("index.jsp").forward(request, response);
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
