<%@page import="java.util.List"%>
<%@page import="Model.Cart"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Controller.ProductDAO"%>
<%@page import="Model.Product"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.util.Map"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="Database.DBConnection"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.util.HashMap"%>
<%@include file="user/header.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    if (session.getAttribute("cart") != null) {
        int id, size;
        Product product = new Product();
        List<Cart> carts = (ArrayList) session.getAttribute("cart");
%>

<main>
    <section class="container">
        <form action="<%= request.getContextPath()%>/cart" method="POST">
            <div class="cart-wrapper">
                <%
                    for (Cart c : carts) {
                        id = c.getId();
                        size = c.getSize();
                        product = ProductDAO.getSingleRecords(id);
                        request.setAttribute("product", product);
                %>
                <div class="cart-item">
                    <div class="cart-item-content">
                        <div style="display: flex; align-items: center;">
                            <div class="cart-image">
                                <img src="data:image/jpeg;base64, <%= product.getB64Image()%>" alt="<%= product.getName()%>">
                            </div>
                            <span class="cart-item-name">
                                <%=product.getName()%><br><br>
                                Brand: <%=product.getBrand()%><br><br>
                                Size: <%= size%><br><br>
                                Price: $<%=product.getPrice()%><br><br>
                            </span>
                        </div>
                        <input type="hidden" name="product_id" value="<%= product.getId()%>">
                        <input type="hidden" name="size" value="<%= size%>">
                        <div class="quantity-button">
                            <div class="quantity">
                                <button type="button" class="minus" aria-label="Decrease">&minus;</button>
                                <input type="number" name="quantity" class="input-box" value="1" min="1" max="10">
                                <button type="button" class="plus" aria-label="Increase">&plus;</button>
                            </div>
                        </div>
                    </div>
                </div>
                <%
                        }
                    }
                %>
                <button class="checkout">
                    Checkout
                </button>
            </div>
        </form>
    </section>
</main>
<%@include file="user/footer.jsp" %>
