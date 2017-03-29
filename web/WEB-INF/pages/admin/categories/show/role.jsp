<%@ page import="com.belhard.misha.controllers.admin.categories.show.ShowRole" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="/WEB-INF/pages/admin/layout/header.jsp"/>

<c:set var="rolePath" value="<%=ShowRole.URL%>"/>
<form action="${pageContext.request.contextPath}${rolePath}" method="post">
    <input type="text" name="role" placeholder="Role">
    <button type="submit" name="addRole">Отправить</button>
</form>

<jsp:useBean id="roles" scope="request" type="java.util.List"/>
<c:forEach var="role" items="${roles}">
    <p>${role.id} ${role.role}</p>
    <form action="${pageContext.request.contextPath}${rolePath}" method="post">
        <button type="submit" name="deleteRole" value="${role.id}">Удалить</button>
    </form>
    <form action="${pageContext.request.contextPath}${rolePath}/edit" method="post">
        <button type="submit" name="editRole" value="${role.id}">Редактировать</button>
    </form>
</c:forEach>

<jsp:include page="/WEB-INF/pages/admin/layout/footer.jsp"/>