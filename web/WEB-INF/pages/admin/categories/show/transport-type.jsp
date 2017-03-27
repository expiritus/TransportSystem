<%@ page import="com.belhard.misha.controllers.admin.categories.show.ShowTransportType" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="/WEB-INF/pages/admin/layout/header.jsp"/>

<c:set var="transportTypePath" value="<%=ShowTransportType.URL%>" />
<form action="${pageContext.request.contextPath}${transportTypePath}" method="post">
    <input type="text" name="transportType" placeholder="Transport type">
    <button type="submit" name="addTransportType">Отправить</button>
</form>

<jsp:useBean id="transportTypes" scope="request" type="java.util.List"/>
<c:forEach var="transportType" items="${transportTypes}">
    <p>${transportType.id} ${transportType.type}</p>
    <form action="${pageContext.request.contextPath}${transportTypePath}" method="post">
        <button type="submit" name="deleteTransportType" value="${transportType.id}">Удалить</button>
    </form>
</c:forEach>

<jsp:include page="/WEB-INF/pages/admin/layout/footer.jsp"/>