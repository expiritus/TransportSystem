<%@ page import="com.belhard.misha.controllers.admin.categories.show.ShowStatus" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="/WEB-INF/pages/admin/layout/header.jsp"/>

<c:set var="statusPath" value="<%=ShowStatus.URL%>" />
<form action="${pageContext.request.contextPath}${statusPath}" method="post">
    <input type="text" name="status" placeholder="Статус">
    <button type="submit" name="addStatus">Отправить</button>
</form>

<jsp:useBean id="statuses" scope="request" type="java.util.List"/>
<c:forEach var="status" items="${statuses}">
    <p>${status.id} ${status.status}</p>
    <form action="${pageContext.request.contextPath}${statusPath}" method="post">
        <button type="submit" name="deleteStatus" value="${status.id}">Удалить</button>
    </form>
</c:forEach>

<jsp:include page="/WEB-INF/pages/admin/layout/footer.jsp"/>