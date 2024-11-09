package Servlet;

import Controller.ProductDAO;
import Model.Product;
import Validator.ProductValidator;
import java.io.IOException;
import java.io.InputStream;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@MultipartConfig(maxFileSize = 16177215) // 16MB   
@WebServlet(name = "AdminProductForm", urlPatterns = {"/admin/product_list/form"})
public class AdminProductForm extends HttpServlet {

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String operation = "error";
        if (request.getParameter("operation") != null) {
            operation = request.getParameter("operation");
        }
        if (operation.equals("update")) {
            final int id = new Integer(request.getParameter("id"));
            Product product = ProductDAO.getSingleRecords(id);
            request.setAttribute("product", product);
        } else if (operation.equals("delete")) {
            int result;
            int id = new Integer(request.getParameter("id"));
            result = ProductDAO.delete(id);
            if (result > 0) {
                response.sendRedirect(request.getContextPath() + "/admin/product_list");
                return;
            } else {
                request.setAttribute("errorMsg", "Error Message");
                return;
            }
        } else if (operation.equals("insert")) {
            request.setAttribute("operation", operation);
        }
        request.getRequestDispatcher("../../admin_product-form.jsp").forward(request, response);
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        final String operation = request.getParameter("operation");        
        int result = 0;
        
        Product product = new Product();
        product.setName(request.getParameter("name"));
        product.setDesc(request.getParameter("description"));
        product.setPrice( new Float(request.getParameter("price")));
        product.setCategory(new Integer(request.getParameter("category")));
        product.setBrand(request.getParameter("brand"));
        product.setColor(request.getParameter("color"));
        product.setSex(request.getParameter("sex"));

        InputStream inputStream = null;

        Part filePart = request.getPart("image");
        if (filePart != null) {
            inputStream = filePart.getInputStream();
        }
        product.setISImage(inputStream);
        // Validation
        String errorMessage = ProductValidator.validateProduct(product);
        if (errorMessage != null || filePart == null || filePart.getSize() == 0) {
            if (filePart == null || filePart.getSize() == 0) {
                errorMessage = "Product image is required.<br>";
            }
            response.sendRedirect(request.getContextPath() + "/admin/product_list/form?operation=error");
            return;
        }
        if (operation.equals("insert")) {
            result = ProductDAO.save(product);
            if (result > 0) {
                response.sendRedirect(request.getContextPath() + "/admin/product_list");
            } else {
                request.setAttribute("errorMsg", "Error Message");
            }
        } else if (operation.equals("update")) {
            product.setId(new Integer(request.getParameter("id")));
            result = ProductDAO.edit(product);
            if (result > 0) {
                response.sendRedirect(request.getContextPath() + "/admin/product_list");
            } else {
                request.setAttribute("errorMsg", "Error Message");
            }
        }
    }
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
