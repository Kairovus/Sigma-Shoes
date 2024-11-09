<%@include file="user/header.jsp"%>

<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="CSS/form.css"
    </head>
    <main>
        <h2>Sign In</h2>
        <form id="form" action="<%= request.getContextPath() %>/login" method="POST">
            <div>
                <label for="email">Email</label>
                <input type="email" name="email" id="email" >
            </div>
            <div>
                <label for="password">Password</label>
                <input
                    type="password"
                    name="password"
                    id="password">
            </div>
            <button type="submit" class="sign-button">Sign In</button>
            <h3 style="font-weight: 400; font-size: medium">Don't have an account?  <a href="<%= request.getContextPath() %>/register" style="color: #DC5F00"> Sign Up</a></h3>
        </form>
    </main>
</html>
    
<%@include file="user/footer.jsp" %>