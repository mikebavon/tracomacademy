<%@ include file="../directives.jsp" %>
<!DOCTYPE html>
<html>
    <%@ include file="../header.jsp" %>
<body>
    <%@ include file="../navbar.jsp" %>

    <c:url var="addInstructor" value="instructor" >
        <c:param name="action" value="add" />
    </c:url>
    <button class="button"><a href="${addInstructor}">Add</a></button>
    <div class="dataGrid">
        <table>
          <tr>
            <th>Id</th>
            <th>Id No</th>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Phone No</th>
            <th>Department</th>
            <th></th>
            <th></th>
          </tr>
          <c:forEach items="${requestScope.instructors}" var="instructor" >

            <c:url var="editInstructor" value="instructor" >
                <c:param name="id" value="${instructor.id}" />
                <c:param name="action" value="edit" />
            </c:url>

            <c:url var="deleteInstructor" value="instructor" >
                <c:param name="id" value="${instructor.id}" />
                <c:param name="action" value="delete" />
            </c:url>

              <tr>
                <td>${instructor.id}</td>
                <td>${instructor.idNo}</td>
                <td>${instructor.firstName}</td>
                <td>${instructor.lastName}</td>
                <td>${instructor.phoneNo}</td>
                <td>${instructor.departmentName}</td>
                <td><a href="${editInstructor}">Edit</a></td>
                <td><a href="${deleteInstructor}">Delete</a></td>
              </tr>
          </c:forEach>
        </table>
    </div>
</body>
</html>

