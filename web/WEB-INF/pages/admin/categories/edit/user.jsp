<%@ page import="com.belhard.misha.controllers.admin.categories.edit.EditUser" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="/WEB-INF/pages/admin/layout/header.jsp"/>
<c:set var="editUserPath" value="<%=EditUser.URL%>"/>
<jsp:useBean id="user" scope="request" type="com.belhard.misha.entity.User"/>
<jsp:useBean id="allRoles" scope="request" type="java.util.List" />

<form action="${pageContext.request.contextPath}${editUserPath}" method="post">
    <input type="text" name="name" value="${user.name}">
    <input type="text" name="login" value="${user.login}">
    <input type="text" name="password" value="${user.password}">
    <c:forEach var="role" items="${allRoles}">
        <c:set var="flag" value="0" />
        <c:forEach var="userRole" items="${user.roles}">
            <c:choose>
                <c:when test="${userRole.id == role.id}">
                    ${role.role} <input type="checkbox" checked value="${role.id}">
                    <c:set var="flag" value="1" />
                </c:when>
            </c:choose>
        </c:forEach>
        <c:if test="${flag == 0}">
            ${role.role} <input type="checkbox" value="${role.id}">
        </c:if>
    </c:forEach>
    <button type="submit" name="updateUser" value="${user.id}">Обновить</button>
</form>

<jsp:include page="/WEB-INF/pages/admin/layout/footer.jsp"/>