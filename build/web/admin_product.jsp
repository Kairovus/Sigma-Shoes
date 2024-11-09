<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="admin/header.jsp" %>
<%@page import="Controller.ProductDAO,Model.Product,java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="main-content">
    <div class="container">
      <h1>Product Page</h1>
      <div class="user">
        <img src="<%= request.getContextPath() %>/img/guy.png" class="user-img">
        <span class="username"><h2><%= session.getAttribute("adminName") %></h2></span>
      </div>
    </div>
    
    <div class="products">
        <a href="<%= request.getContextPath() %>/admin/product_list/form?operation=insert">
        <div class="addbtn">
            Add New Product
        </div>
        </a>
        <div class="producttable">
            <h3 style="color: white;">Product Names</h3>
            <hr>
            <%
                List<Product> list = ProductDAO.getAllRecords();
                request.setAttribute("list", list);
            %>
            <c:forEach items="${list}" var="b">
                <div class="productsrow">
                    <div class="name" style="color: white;">
                    ${b.getName()}
                    </div>
                <div>
                    <div class="edit inline-block">
                        <a href="<%= request.getContextPath() %>/admin/product_list/form?operation=update&id=${b.getId()}" class="edittext">
                            <i class="bi bi-pencil-square"></i> Edit
                        </a>
                    </div>
                    <div class="delete inline-block">
                        <a href="<%= request.getContextPath() %>/admin/product_list/form?operation=delete&id=${b.getId()}" class="deletetext">
                            <i class="bi bi-trash"></i> Delete
                        </a>
                    </div>
                </div>
            </div>
            </c:forEach>
            
        </div>
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