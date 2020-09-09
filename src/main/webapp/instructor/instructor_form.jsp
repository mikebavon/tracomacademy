<%@ include file="../directives.jsp" %>
<!DOCTYPE html>
<html>
    <%@ include file="../header.jsp" %>
<body>
<%@ include file="../navbar.jsp" %>
<form action="${pageContext.request.contextPath}/instructor" method="POST">
  <%@ include file="../error.jsp" %>
  <div class="container">
    <h1>Instructor Details</h1>
    <hr>
    <label for="idNo"><b>Id No</b></label>
    <input type="text" placeholder="Enter Id No" name="idNo" required value="${requestScope.instructor.idNo}">

    <hr>
    <label for="firstName"><b>First Name</b></label>
    <input type="text" placeholder="Enter First Name" name="firstName" required value="${requestScope.instructor.firstName}">

    <label for="lastName"><b>Last Name</b></label>
    <input type="text" placeholder="Enter Last Name" name="lastName" value="${requestScope.instructor.lastName}">

    <label for="phoneNo"><b>Phone Number</b></label>
    <input type="text" placeholder="Enter Phone Number" name="phoneNo" value="${requestScope.instructor.phoneNo}">

    <label for="departmentId"><b>Department</b></label>
    <select placeholder="Enter Department" name="departmentId">
          <c:forEach items="${requestScope.departments}" var="department" >
              <option value="${department.id}" selected="${requestScope.instructor.departmentId == department.id? 'SELECTED' : ''}">${department.name}</option>
          </c:forEach>
    </select>

    <input type="hidden" name="id" value="${requestScope.instructor.id}" >

    <hr>
    <button type="submit" class="registerbtn">Save</button>
  </div>
</form>

</body>
</html>

