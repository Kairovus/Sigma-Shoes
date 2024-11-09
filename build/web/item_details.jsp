<%@page import="java.sql.ResultSet"%>
<%@page import="Model.Product"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="Database.DBConnection"%>
<%@page import="java.sql.Connection"%>
<%@include file="user/header.jsp"%>

    <main>
      <section class="container">
        <div class="details row">
            <div class="img-wrapper">
                <img src="data:image/jpeg;base64, ${product.getB64Image()}" alt="${product.getName()}">
            </div>
            <div class="details-wrapper">
                <p class="details-item-name">${product.getName()}</p>
                <form action="<%=request.getContextPath()%>/product_details?product=${product.getId()}" method="post">
                    <div class="size-wrapper">
                        <div class="size-row">
                            <%
                                Product product = (Product) request.getAttribute("product");
                                Connection conn = new DBConnection().setConnection();
                                PreparedStatement ps = conn.prepareStatement("SELECT * FROM product_stocks WHERE product_id = ? ORDER BY size");
                                ps.setInt(1, product.getId());
                                ResultSet rs = ps.executeQuery();
                                while (rs.next()) {
                            %>
                            <div class="radio-toolbar">
                                <input class="size" type="radio" id="<%= rs.getInt("size") %>" name="size" value="<%= rs.getInt("size") %>" checked>
                                <label for="<%= rs.getInt("size") %>"><%= rs.getInt("size") %><br>Stock: <%=rs.getInt("quantity")%></label>
                            </div>
                            <% } %>
                        </div>
                        <h3 class="item-price">$ ${product.getPrice()}</h3>
                    </div>
                    <div class="add-button">
                        <button class="add-to-cart">
                            Add to cart
                        </button>
                    </div>
                </form><br>
                    <div class="description">
                        <p>Description: ${product.getDesc()}</p>
                        <p>Brand: ${product.getBrand()}</p>
                        <p>Color: ${product.getColor()}</p>
                        <p>Sex: ${product.getSex()}</p>
                    </div>
            </div>
        </div>
      </section>
    </main>
<%@include file="user/footer.jsp" %>