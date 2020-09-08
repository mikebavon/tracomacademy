<div class="container">
    <c:if test="${requestScope.errorMsg != null}">
        <div class="alert">
          <span class="closebtn" onclick="this.parentElement.style.display='none';">&times;</span>
          ${requestScope.errorMsg}
        </div>
    </c:if>
</div>