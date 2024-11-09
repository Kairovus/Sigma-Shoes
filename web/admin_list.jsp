<%@page import="Controller.AdminDAO"%>
<%@page import="java.util.List"%>
<%@page import="Model.Admin"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="admin/header.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="main-content">
    <div class="container">
      <h1>Admin Page</h1>
      <div class="user">
        <img src="<%= request.getContextPath() %>/img/guy.png" class="user-img">
        <span class="username"><h2><%= session.getAttribute("adminName") %></h2></span>
      </div>
    </div>

      <form class="adminform" action="<%= request.getContextPath() %>/admin/admin_list" method="POST">
      <div class="adminformtitle">
        <h2>Add New Admins</h2>
      </div>
      <hr>
      <div class="admin-form" style="color: white;">
        <div>
          <label for="username"><h3>Username</h3></label>
          <input type="text" name="username" id="username">
        </div>
        <div>
          <label for="email"><h3>Email</h3></label>
          <input type="text" name="email" id="email">
        </div>
        <div>
          <label for="password"><h3>Password</h3></label>
          <input type="text" name="password" id="password">
        </div>
        <div class="addadmin">
          <button type="submit" class="submit"> Add</button>
        </div>
      </div>
    </form>


    <div class="admintable">
      <h3 style="color: white;">Existing Admins</h3>
      <div class="admintabletitle">
        <h3>Admin Name</h3>
      </div>
      <hr>
      <%
          List<Admin> list = AdminDAO.getAllRecords();
          request.setAttribute("list", list);
      %>
      <c:forEach items="${list}" var="b">
        <div class="adminrow">
            <div class="adminname">
                ${b.getUsername()}
            </div>
            <div>
                <div class="delete inline-block">
                    <a class="deletetext" href="<%= request.getContextPath() %>/admin/admin_list?id=${b.getId()}">
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