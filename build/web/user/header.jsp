<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sigma Shoes</title>
        <meta name="viewport" contents="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="icon" href=" img/logo.jpg"/>
        <link rel="stylesheet" type="text/css" href="CSS/styles.css">
    </head>
<body>
    <header>
        <nav>
            <div class="container row v-center" style="justify-content: space-between;">
                <div class="row v-center">
                <div class="nav__logo">
                    <a href="<%=request.getContextPath()%>"><img src="img/logo.jpg" alt="Sigma Shoes logo" class="logo"></a>
                </div>
                <h1 class="m-0 logo__caption accent-color">Sigma Shoes</h1>
                <ul class="nav__list nav__list--primary v-center">
                    <li class="nav__item"><a href="<%=request.getContextPath()%>" class="nav__link">Home</a></li>
                    <li class="nav__item"><a href="<%=request.getContextPath()%>/faq.jsp" class="nav__link">About</a></li>
                    <li class="nav__item">
                        <% boolean isUser = session.getAttribute("username") != null && session.getAttribute("isUser").equals(true);
                           if (isUser) { %>
                        <%= session.getAttribute("username") %>
                        <% } else { %>
                        <a href="<%=request.getContextPath()%>/login" class="nav__link">Register/Login</a>
                        <% } %>
                        </li>
                        </ul>
                    </div>
                    <% if (isUser) { %>
                    <div class="cart-search row v-center">
                    <a href="<%=request.getContextPath()%>/cart"><i class="fa fa-shopping-cart" aria-hidden="true" style="font-size: 40px;color: white;"></i></a>
                    <% } %>
                    <form class="search"  method="GET">
                        <input type="text" name="name" id="search" placeholder="Enter the product name">
                    </form>
                    </div> 
            </div>
        </nav>
    </header>