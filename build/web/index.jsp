<%@page import="Controller.ProductDAO,Model.Product,java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="user/header.jsp" %>
<main>
    <section class="container">
        <div class="catalog row">
           <%
                List<Product> list = new ArrayList<Product>();
                if (request.getParameter("name") != null) {
                    String name = request.getParameter("name");
                    System.out.println(name);
                    list = ProductDAO.searchProduct(name);
                } else {
                    System.out.println("else");
                    list = ProductDAO.getAllRecordsNotEmpty();
                }
                request.setAttribute("list", list);
            %>
            <c:forEach items="${list}" var="b">
                <div class="catalog-item-wrapper">
                    <a href="<%=request.getContextPath()%>/product_details?product=${b.getId()}">
                        <div class="catalog-item-content">
                            <img src="data:image/jpeg;base64, ${b.getB64Image()}" alt="${b.getName()}">
                            <h3 class="image-caption">${b.getName()}</h3>
                            <p class="item-price">$${b.getPrice()}</p>
                        </div>
                    </a>
                </div>
            </c:forEach>
        </div>
    </section>
</main>
<%@include file="user/footer.jsp" %>