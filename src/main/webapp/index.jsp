<%@ include file="directives.jsp" %>
<c:set var="pageTitle" value="Register User" />
<!DOCTYPE html>
<html>
<%@ include file="header.jsp" %>
<body>
    <form action="register" method="POST">
      <%@ include file="error.jsp" %>
      <div class="container">
        <h1>Sign Up</h1>
        <p>Please fill in this form to create an account.</p>
        <hr>

        <label for="name"><b>Name</b></label>
        <input type="text" placeholder="Enter Name" name="name" required>

        <label for="email"><b>Email</b></label>
        <input type="email" placeholder="Enter Email" name="email" required>

        <label for="psw"><b>Password</b></label>
        <input type="password" placeholder="Enter Password" name="password" required>

        <label for="psw-repeat"><b>Repeat Password</b></label>
        <input type="password" placeholder="Confirm Password" name="confirmPassword" required>
        <hr>
        <p>By creating an account you agree to our <a href="#">Terms & Privacy</a>.</p>

        <button type="submit" class="registerbtn">Register</button>
      </div>

      <div class="container signin">
        <p>Already have an account? <a href="login.jsp">Sign in</a>.</p>
      </div>
    </form>

</body>
</html>

