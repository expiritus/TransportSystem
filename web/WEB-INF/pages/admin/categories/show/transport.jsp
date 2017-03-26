<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="/WEB-INF/pages/admin/layout/header.jsp"/>

<form action="${pageContext.request.contextPath}/admin/transport/add" method="post">
    <select name="transportType">
        <jsp:useBean id="transportTypes" scope="request" type="java.util.List"/>
        <c:forEach var="transportType" items="${transportTypes}">
            <option value="${transportType.id}">${transportType.type}</option>
        </c:forEach>
    </select>
    <input type="text" name="model" placeholder="Model">
    <input type="text" name="capacity" placeholder="Capacity">
    <input type="text" name="speed" placeholder="Speed">
    <button type="submit" name="transportSubmit">Отправить</button>
</form>

<jsp:useBean id="transports" scope="request" type="java.util.List"/>
<c:forEach var="transport" items="${transports}">
    <p>${transport.id} ${transport.transportType.type} ${transport.model} ${transport.capacity} ${transport.speed}</p>
    <form action="/admin/transport/delete" method="post">
        <button type="submit" name="transportId" value="${transport.id}">Удалить</button>
    </form>
</c:forEach>

<jsp:include page="/WEB-INF/pages/admin/layout/footer.jsp"/>
