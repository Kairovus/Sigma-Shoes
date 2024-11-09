<%@page import="java.sql.PreparedStatement"%>
<%@page import="Database.DBConnection"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.ResultSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="admin/header.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<div class="main-content">
    <div class="container">
        <h1>Order Page</h1>
        <div class="user">
            <img src="<%= request.getContextPath()%>/img/guy.png" class="user-img">
            <span class="username"><h2><%= session.getAttribute("adminName")%></h2></span>
        </div>
    </div>

    <div class="ordertable">
        <div class="ordertabletitle">
            <h3>Order ID</h3>
            <h3>Customer</h3>
            <h3>Total Amount</h3>
            <h3>Date</h3>
        </div>
        <hr>
        <%
            Connection conn = new DBConnection().setConnection();
            ResultSet rs = conn.createStatement().executeQuery("SELECT order_id, o.user_id, CONCAT(first_name,' ', last_name) AS username, order_date, total_amount FROM orders o JOIN users u ON o.user_id = u.user_id");
            ResultSet rs2;
            PreparedStatement ps;
            while (rs.next()) {
        %>
        <button class="accordion">
            <div class="orderrow">
                <div class="datename">
                    <%= rs.getInt("order_id")%>
                </div>
                <div class="customername">
                    <%= rs.getString("username")%>
                </div>
                <div class="productsname">
                    $<%= rs.getFloat("total_amount")%>
                </div>
                <div class="pricename">
                    <%= rs.getString("order_date")%>
                </div>
            </div></button>

        <div class="panel">
            <% ps = conn.prepareStatement("SELECT * FROM order_items oi JOIN products p ON oi.product_id = p.product_id WHERE order_id = ?");
                ps.setInt(1, rs.getInt("order_id"));
                rs2 = ps.executeQuery();
                while (rs2.next()) {
            %>
            <div class="orderproduct">
                <%= rs2.getString("name")%>
            </div>
            <div class="orderprice">
                Size: <%= rs2.getInt("size")%>
            </div>
            <div class="orderquantitiy">
                Qty: <%= rs2.getInt("quantity")%>
            </div>
            <div class="ordersize">
                $<%= rs2.getFloat("price")%>
            </div>
            <% } %>
        </div>
        <% }%>
    </div>
</div>
</body>
<script>
    let btn = document.querySelector('#btn')
    let sidebar = document.querySelector('.sidebar')

    btn.onclick = function () {
        sidebar.classList.toggle('active');
    };

    var acc = document.getElementsByClassName("accordion");
    var i;

    for (i = 0; i < acc.length; i++) {
        acc[i].addEventListener("click", function () {
            this.classList.toggle("active");
            var panel = this.nextElementSibling;
            if (panel.style.maxHeight) {
                panel.style.maxHeight = null;
            } else {
                panel.style.maxHeight = panel.scrollHeight + "px";
            }
        });
    }
</script>
</script>
</html>