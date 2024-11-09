<%@page import="java.sql.ResultSet"%>
<%@page import="Database.DBConnection"%>
<%@page import="java.sql.Connection"%>
<%@include file="admin/header.jsp" %>

<% 
    Connection conn = new DBConnection().setConnection();
    ResultSet rs1 = conn.createStatement().executeQuery("SELECT COUNT(*) FROM orders");
    if (rs1.next()) {
        request.setAttribute("orderCount", rs1.getInt(1));
    }
    ResultSet rs2 = conn.createStatement().executeQuery("SELECT SUM(total_amount) FROM orders");
    if (rs2.next()) {
        request.setAttribute("totalRevenue", rs2.getFloat(1));
    }
%>

<div class="main-content">
  <div class="container">
    <h1>Hello <%= session.getAttribute("adminName") %>!</h1>
    <div class="user">
      <img src="<%= request.getContextPath() %>/img/guy.png" class="user-img">
      <span class="username"><h2><%= session.getAttribute("adminName") %></h2></span>
    </div>
  </div>

  <div class="order-container">
    <div class="order-title">
      <h1>Order</h1>
    </div>
    <hr>
    <div class="order-stats">
      <h3>Total Order</h3>
      <div class="ordernum">
          ${orderCount} orders
      </div>
    </div> 
  </div>
  
  <div class="revenue-container">
    <div class="revenue-title">
      <h1>Revenue</h1>
    </div>
    <hr>
    <div class="revenue-stats">
      <h3>Total Revenue</h3>
      <div class="revenuenum">
        $${totalRevenue}
      </div>
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