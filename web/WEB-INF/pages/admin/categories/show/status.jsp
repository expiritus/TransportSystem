<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="/WEB-INF/pages/admin/layout/header.jsp"/>
<form action="${pageContext.request.contextPath}/admin/status/add" method="post">
    <input type="text" name="status">
    <button type="submit" name="statusSubmit">Отправить</button>
</form>

<jsp:useBean id="statuses" scope="request" type="java.util.List"/>
<c:forEach var="status" items="${statuses}">
    <p>${status.id} ${status.status}</p>
</c:forEach>

<jsp:include page="/WEB-INF/pages/admin/layout/footer.jsp"/>