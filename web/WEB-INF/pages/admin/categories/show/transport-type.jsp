<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.belhard.misha.entity.TransportType" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="/WEB-INF/pages/admin/layout/header.jsp" />

<form action="${pageContext.request.contextPath}/admin/transport-type/add" method="post">
    <input type="text" name="transportType" placeholder="Transport type">
    <button type="submit" name="submitTransportType">Отправить</button>
</form>

<jsp:useBean id="transportTypes" scope="request" type="java.util.List" />
<c:forEach var="transportType" items="${transportTypes}">
    <p>${transportType.id} ${transportType.type}</p>
    <form action="/admin/transport-type/delete">
        <button type="submit" name="transportTypeId" value="${transportType.id}">Удалить</button>
    </form>
</c:forEach>

<jsp:include page="/WEB-INF/pages/admin/layout/footer.jsp" />