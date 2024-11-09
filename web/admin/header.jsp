<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css"> 
    <link rel="stylesheet" href="<%=request.getContextPath() %>/CSS/admin.css">
    <link rel="icon" href="<%=request.getContextPath() %>/img/logo.jpg"/>
    <link rel="stylesheet" href="https://code.jquery.com/ui/1.13.3/themes/base/jquery-ui.css">
  <link rel="stylesheet" href="/resources/demos/style.css">
  <script src="https://code.jquery.com/jquery-3.7.1.js"></script>
  <script src="https://code.jquery.com/ui/1.13.3/jquery-ui.js"></script>
  <script>
  $( function() {
    $( "#accordion" ).accordion();
  } );
  </script>
</head>
<body>
<div class="sidebar">
    <div class="top">
      <div class="logo">
        <img src="<%=request.getContextPath() %>/img/SigmaShoes.png" class="logoimg">
        <span>SIGMA SHOES</span>
      </div>
      <i class="bi bi-layout-sidebar" id="btn"></i>
    </div>
    <ul>
      <li>
        <a href="<%= request.getContextPath() %>/admin/dashboard">
          <i class="bi bi-house"></i>       
          <span class="nav-item">Dashboard</span> 
        </a>
        <span class="tooltip">Dashboard</span>
      </li>
      <li>
        <a href="<%= request.getContextPath() %>/admin/product_list">
          <i class="bi bi-archive"></i>       
          <span class="nav-item">Products</span> 
        </a>
        <span class="tooltip">Products</span>
      </li>
      <li>
        <a href="<%= request.getContextPath() %>/admin/stock_list">
          <i class="bi bi-boxes"></i>
          <span class="nav-item">Stocks</span> 
        </a>
        <span class="tooltip">Stocks</span>
      </li>
      <li>
        <a href="<%= request.getContextPath() %>/admin/category_list">
          <i class="bi bi-grid-fill"></i>
          <span class="nav-item">Categories</span> 
        </a>
        <span class="tooltip">Categories</span>
      </li>
      <li>
        <a href="<%= request.getContextPath() %>/admin/order_list">
          <i class="bi bi-basket"></i>       
          <span class="nav-item">Orders</span> 
        </a>
        <span class="tooltip">Orders</span>
      </li>
      <li>
        <a href="<%= request.getContextPath() %>/admin/admin_list">
          <i class="bi bi-person-circle"></i>       
          <span class="nav-item">Admin</span> 
        </a>
        <span class="tooltip">Admin</span>
      </li>
      <li>
        <a href="<%= request.getContextPath() %>/admin/logout">
          <i class="bi bi-box-arrow-left"></i>
          <span class="nav-item">Logout</span> 
        </a>
        <span class="tooltip">Logout</span>
      </li>
    </ul>
</div>
