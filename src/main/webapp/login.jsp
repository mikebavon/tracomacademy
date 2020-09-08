<%@ include file="directives.jsp" %>
<!DOCTYPE html>
<html>
    <%@ include file="header.jsp" %>
<body>

<form action="login" method="POST">
  <%@ include file="error.jsp" %>
  <div class="container">
    <h1>Login</h1>
    <hr>
    <label for="email"><b>Email</b></label>
    <input type="email" placeholder="Enter Email" name="email" required value="${param.email}">

    <label for="psw"><b>Password</b></label>
    <input type="password" placeholder="Enter Password" name="password" required>
    <hr>
    <p>By creating an account you agree to our <a href="#">Terms & Privacy</a>.</p>

    <button type="submit" class="registerbtn">Sign In</button>
  </div>

  <div class="container signin">
    <p>Create Account? <a href="index.jsp">Sign Up</a>.</p>
  </div>
</form>

</body>
</html>

