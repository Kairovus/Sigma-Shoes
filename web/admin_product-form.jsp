<%@page import="Model.Product"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="Database.DBConnection"%>
<%@page import="java.sql.Connection"%>
<%@include file="admin/header.jsp" %>
<div class="main-content">
    <div class="container">
      <h1>Product Form</h1>
      <div class="user">
        <img src="<%= request.getContextPath() %>/img/guy.png" class="user-img">
        <span class="username"><h2><%= session.getAttribute("adminName") %></h2></span>
      </div>
    </div>
    
    <div class="productsforms">
        <div class="productFormtitle">
            <% if (request.getParameter("operation").equals("update")) { %>
                <h3>Update Product</h3>
            <% } else { %>
                <h3>Add New Product</h3>
            <% } %>
        </div>
            <form action="<%= request.getContextPath() %>/admin/product_list/form?operation=<%= request.getParameter("operation") %>&id=<%= request.getParameter("id") %>" method="POST" enctype="multipart/form-data" class="product-form">
                <% if (request.getParameter("operation").equals("update")) { %>
                <div>
                    <label for="name"><h3>Product Name</h3></label>
                    <input type="text" name="name" id="name" value="${product.name}">
                </div>
                <div>
                    <label for="description"><h3>Product Description</h3></label>
                    <input type="text" name="description" id="description" value="${product.desc}">
                </div>
                <div>
                    <label for="price"><h3>Product Price</h3></label>
                    <input type="text" name="price" id="price" value="${product.price}">
                </div>
                <div>
                    <label for="category"><h3>Product Categories</h3></label>
                    <select name="category" id="category">
                            <option disabled selected value>Select The Categories</option>
                            <%
                            Product product = null;
                            if (request.getAttribute("product") != null) {
                                product = (Product) request.getAttribute("product");
                            }
                            Connection conn = new DBConnection().setConnection();
                            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM categories");
                            %>
                            <% while (rs.next()) { %>
                            <option value="<%= rs.getInt("category_id") %>" <%if (product.getCategory() == rs.getInt("category_id")) {%>selected<%}%>> <%=rs.getString("name")%> </option>
                            <% } %>
                    </select>
                </div>
                <div>
                    <label for="brand"><h3>Product Brand</h3></label>
                    <input type="text" name="brand" id="brand" value="${product.name}">
                </div>
                <!--<div>
                    <label for="Size"><h3>Size</h3></label>
                    <input type="text" name="Size" id="Size">
                </div> -->
                <div>
                    <label for="color"><h3>Color</h3></label>
                    <input type="text" name="color" id="color" value="${product.color}">
                </div>
                <div>
                    <label for="Sex"><h3>Product Sex</h3></label>
                    <select name="sex"> 
                        <option value="male" <%if (product.getSex().equals("male")) {%>selected<%}%>>Male</option>
                        <option value="female" <%if (product.getSex().equals("female")) {%>selected<%}%>>Male</option>
                        <option value="unisex" <%if (product.getSex().equals("unisex")) {%>selected<%}%>>unisex</option>
                    </select>
                </div>
                <div>
                    <label for="image"><h3>Images</h3></label>
                    <input type="file" name="image" id="image" accept="image/*">
                </div>
                        <% } else { %>
                        <div>
                    <label for="name"><h3>Product Name</h3></label>
                    <input type="text" name="name" id="name">
                </div>
                <div>
                    <label for="description"><h3>Product Description</h3></label>
                    <input type="text" name="description" id="description">
                </div>
                <div>
                    <label for="price"><h3>Product Price</h3></label>
                    <input type="text" name="price" id="price">
                </div>
                <div>
                    <label for="category"><h3>Product Categories</h3></label>
                    <select name="category" id="category">
                            <option disabled selected value>Select The Categories</option>
                            <%
                            Connection conn = new DBConnection().setConnection();
                            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM categories");
                            %>
                            <% while (rs.next()) { %>
                            <option value="<%= rs.getInt("category_id") %>"> <%=rs.getString("name")%> </option>
                            <% } %>
                    </select>
                </div>
                <div>
                    <label for="brand"><h3>Product Brand</h3></label>
                    <input type="text" name="brand" id="brand">
                </div>
                <!--<div>
                    <label for="Size"><h3>Size</h3></label>
                    <input type="text" name="Size" id="Size">
                </div> -->
                <div>
                    <label for="color"><h3>Color</h3></label>
                    <input type="text" name="color" id="color">
                </div>
                <div>
                    <label for="Sex"><h3>Product Sex</h3></label>
                    <select name="sex"> 
                        <option value="male">Male</option>
                        <option value="female">Male</option>
                        <option value="unisex">unisex</option>
                    </select>
                </div>
                <div>
                    <label for="image"><h3>Images</h3></label>
                    <input type="file" name="image" id="image" accept="image/*">
                </div>
                <% } %>
                <button type="submit" class="submit" onclick="check()"> Submit</button>
            </form>
    </div>  
</div>



</body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
<script>
    let btn = document.querySelector('#btn')
    let sidebar = document.querySelector('.sidebar')
  
    btn.onclick = function(){
      sidebar.classList.toggle('active');
    };

    function check(){
      var result = confirm("Are you sure?");
      if (result == false){
        event.preventDefault();
      }
    }
  </script>
</html>