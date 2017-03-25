<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="/WEB-INF/pages/admin/layout/header.jsp"/>
<form action="${pageContext.request.contextPath}/admin/role/add" method="post">
    <input type="text" name="role" placeholder="Role">
    <button type="submit" name="submitRole">Отправить</button>
</form>

<jsp:useBean id="roles" scope="request" type="java.util.List"/>
<c:forEach var="role" items="${roles}">
    <p>${role.id} ${role.role}</p>
</c:forEach>

<jsp:include page="/WEB-INF/pages/admin/layout/footer.jsp"/>