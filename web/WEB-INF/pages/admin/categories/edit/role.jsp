<%@ page import="com.belhard.misha.controllers.admin.categories.edit.EditRole" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="/WEB-INF/pages/admin/layout/header.jsp"/>

<form action="${pageContext.request.contextPath}<%=EditRole.URL%>" method="post">
    <jsp:useBean id="role" scope="request" type="com.belhard.misha.entity.Role"/>
    <input type="text" name="role" placeholder="Роль" value="${role.role}">
    <button type="submit" name="updateRole" value="${role.id}">Обновить</button>
</form>

<jsp:include page="/WEB-INF/pages/admin/layout/footer.jsp"/>