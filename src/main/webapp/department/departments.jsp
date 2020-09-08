<%@ include file="../directives.jsp" %>
<!DOCTYPE html>
<html>
    <%@ include file="../header.jsp" %>
<body>
    <%@ include file="../navbar.jsp" %>
    <button class="button"><a href="department/department_form.jsp">Add</a></button>
    <div class="dataGrid">
        <table>
          <tr>
            <th>Id</th>
            <th>Name</th>
            <th>Location</th>
            <th></th>
            <th></th>
          </tr>
          <c:forEach items="${requestScope.departments}" var="department" >

            <c:url var="editDepartment" value="department" >
                <c:param name="id" value="${department.id}" />
                <c:param name="action" value="edit" />
            </c:url>

            <c:url var="deleteDepartment" value="department" >
                <c:param name="id" value="${department.id}" />
                <c:param name="action" value="delete" />
            </c:url>

              <tr>
                <td>${department.id}</td>
                <td>${department.name}</td>
                <td>${department.location}</td>
                <td><a href="${editDepartment}">Edit</a></td>
                <td><a href="${deleteDepartment}">Delete</a></td>
              </tr>
          </c:forEach>
        </table>
    </div>
</body>
</html>

