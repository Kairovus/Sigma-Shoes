<%@page import="Model.Product"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="Database.DBConnection"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="admin/header.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="main-content">
    <div class="container">
      <h1>Stock Page</h1>
      <div class="user">
        <img src="<%= request.getContextPath() %>/img/guy.png" class="user-img">
        <span class="username"><h2><%= session.getAttribute("adminName") %></h2></span>
      </div>
    </div>
      <form class="stockform" action="<%= request.getContextPath() %>/admin/stock_list" method="POST">
      <div class="stockformtitle">
        <h2>Add Stocks</h2>
      </div>
      <hr>
      <div class="stock-form">
        <div>
            <label for="name"><h3 style="color: white;">Product Name</h3></label>
            <select name="product_id" id="name">
                <option disabled selected value>Select The Product</option>
                <%
                Product product = null;
                Connection conn = new DBConnection().setConnection();
                ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM products");
                %>
                <% while (rs.next()) { %>
                <option value="<%= rs.getInt("product_id") %>"><%= rs.getInt("product_id") %> (<%=rs.getString("name")%>) </option>
                <% } %>
            </select>
        </div>
        <div>
          <label for="size"><h3 style="color: white;">Product Size</h3></label>
          <input type="text" name="size" id="size">
        </div>
        <div>
            <label for="stock"><h3 style="color: white;">Product Stock</h3></label>
            <input type="text" name="quantity" id="stock">
          </div>
        <div class="addadmin">
          <button type="submit" class="submit"> Add</button>
        </div>
      </div>
    </form>
    <div class="stocktable">
      <h3 style="color: white;">Existing Admins</h3>
      <div class="stocktabletitle">
        <h3>Product Name (id)</h3>
        <h3>Size Name</h3>
        <h3>Stock Name</h3>
      </div>
      <hr>
      <%
          ResultSet rs2 = conn.createStatement().executeQuery("SELECT ps.product_id, p.name, size, quantity FROM product_stocks ps JOIN products p ON ps.product_id = p.product_id");
          while (rs2.next()) {
      %>
      <div class="stockrow">
        <div class="productname">
            <%= rs2.getString("name") %> (<%= rs2.getInt("product_id") %>)
        </div>
        <div class="sizename">
            <%= rs2.getInt("size") %>
        </div>
        <div class="stockname">
            <%= rs2.getInt("quantity") %>
        </div>
        <div>
            <div class="delete inline-block">
                <a class="deletetext" href="<%= request.getContextPath() %>/admin/stock_list?id=<%= rs2.getInt("product_id") %>&size=<%= rs2.getInt("size") %>">
                <i class="bi bi-trash"></i> Delete
              </a>
            </div>
        </div>
      </div>
      <% } %>
    </div>
    
</div>

</body>
<script>
    let btn = document.querySelector('#btn')
    let sidebar = document.querySelector('.sidebar')
  
    btn.onclick = function(){
      sidebar.classList.toggle('active');
    };
</script>
</html>