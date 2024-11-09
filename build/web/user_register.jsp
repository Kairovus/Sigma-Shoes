<%@include file="user/header.jsp"%>

<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="CSS/form.css"
    </head>
    <main>
        <h2>Register</h2>
        <form id="form" action="<%= request.getContextPath() %>/register" method="POST">
            <div>
                <label for="first">First Name:</label>
                <input type="text" name="first" id="first" >
            </div>
            <div>
                <label for="last">Last Name:</label>
                <input type="text" name="last" id="last" >
            </div>
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
            <div>
                <label for="repassword">Confirm Password</label>
                <input type="password" name="repassword" id="repassword">
            </div>
            <div>
                <label for="phone">Phone</label>
                <input type="text" name="phone" id="phone">
            </div>
            <div>
                <label for="address">Address</label>
                <input type="text" name="address" id="address">
            </div>
            <button type="submit" class="sign-button">Sign Up</button>
            <h3 style="font-weight: 400; font-size: medium">Already have an account?  <a href="<%= request.getContextPath() %>/login" style="color: #DC5F00"> Sign In</a></h3>
        </form>
    </main>
</html>
    
<%@include file="user/footer.jsp" %>