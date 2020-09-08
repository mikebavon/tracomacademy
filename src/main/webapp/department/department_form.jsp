<%@ include file="../directives.jsp" %>
<!DOCTYPE html>
<html>
    <%@ include file="../header.jsp" %>
<body>
<%@ include file="../navbar.jsp" %>
<form action="${pageContext.request.contextPath}/department" method="POST">
  <%@ include file="../error.jsp" %>
  <div class="container">
    <h1>Department Details</h1>
    <hr>
    <label for="name"><b>Name</b></label>
    <input type="text" placeholder="Enter Name" name="name" required value="${requestScope.department.name}">

    <label for="psw"><b>Location</b></label>
    <input type="text" placeholder="Enter Location" name="location" value="${requestScope.department.location}">

    <input type="hidden" name="id" value="${requestScope.department.id}" >

    <hr>
    <button type="submit" class="registerbtn">Save</button>
  </div>
</form>

</body>
</html>

