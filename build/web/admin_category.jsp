<%@page import="Controller.CategoryDAO"%>
<%@page import="Model.Category"%>
<%@page import="Model.Category"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="admin/header.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<div class="main-content">
    <div class="container">
      <h1>Categories Page</h1>
      <div class="user">
        <img src="<%= request.getContextPath() %>/img/guy.png" class="user-img">
        <span class="username"><h2><%= session.getAttribute("adminName") %></h2></span>
      </div>
    </div>

    <div class="categoriesform">
        <div class="categoriesformtitle">
            <h2>Add New Categories</h2>
        </div>
        <hr>
        <form action="<%= request.getContextPath() %>/admin/category_list" method="POST" class="categories-form">
        <div>
            <label for="category"><h3 style="color: white;">Category</h3></label>
            <input type="text" name="category" id="category">
        </div>
        <div>
            <label for="description"><h3 style="color: white">Description</h3></label>
            <input type="text" name="description" id="description">
        </div>
        <div class="addcategory">
            <button type="submit" class="submit">Submit</button>
        </div>
        </form>
    </div>

    <div class="categoriestable">
        <h3 style="color: white;">Existing Categories</h3>
        <div class="categoriestabletitle">
            <h3>Categories</h3>
            <h3>Description</h3>
        </div>
        <hr>
            <%
                List<Category> list = CategoryDAO.getAllRecords();
                request.setAttribute("list", list);
            %>
            <c:forEach items="${list}" var="b">
            <div class="categoryrow" style="color: white;">
                    <div class="name">
                        ${b.getName()}
                    </div>
                    <div class="name">
                        ${b.getDescription()}
                    </div>
                    <div>
                    <div class="delete inline-block">
                        <a class="deletetext" href="<%= request.getContextPath() %>/admin/category_list?operation=delete&id=${b.getId()}">
                            <i class="bi bi-trash"></i> Delete
                        </a>
                    </div>
                </div>
            </div>
            </c:forEach>
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