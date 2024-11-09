<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Login</title>
    <link rel="stylesheet" href="CSS/admin.css">
</head>
<body>
    <div class="login">
        <div class="logintitle">
            <h1 style="color: white;">Welcome</h1>
        </div>
        <div >
            <img src="img/SigmaShoes.png" class="loginpic">
        </div>
        <form action="<%= request.getContextPath() %>/admin" method="POST">
            <div class="loginform">
                <div>
                    <h3 for="email" style="color: white;">Email</h3>
                    <input type="text" name="email" id="email" placeholder="Email">
                </div>
                <div>
                    <h3 for="password" style="color: white;">Password</h3>
                    <input type="password" name="password" id="password" placeholder="Password">
                </div>
            </div>
            <button type="submit" class="loginbtn">Login</button>
        </form>
    </div>
</body>
</html>