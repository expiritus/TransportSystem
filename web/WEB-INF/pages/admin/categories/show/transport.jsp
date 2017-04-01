<%@ page import="com.belhard.misha.controllers.admin.categories.show.ShowTransport" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="/WEB-INF/pages/admin/layout/header.jsp"/>

<c:set var="transportPath" value="<%=ShowTransport.URL%>" />
<form action="${pageContext.request.contextPath}${transportPath}" method="post">
    <select name="transportType">
        <jsp:useBean id="transportTypes" scope="request" type="java.util.List"/>
        <c:forEach var="transportType" items="${transportTypes}">
            <option value="${transportType.id}">${transportType.type}</option>
        </c:forEach>
    </select>
    <input type="text" name="model" placeholder="Model" value="${sessionScope.stateFullMap.stateFillModel}">
    ${sessionScope.errorMap.errorModel}

    <input type="text" name="capacity" placeholder="Capacity" value="${sessionScope.stateFullMap.stateFullCapacity}">
    ${sessionScope.errorMap.errorCapacity}

    <input type="text" name="speed" placeholder="Speed" value="${sessionScope.stateFullMap.stateFullSpeed}">
    ${sessionScope.errorMap.errorSpeed}
    <button type="submit" name="addTransport">Отправить</button>
</form>

<jsp:useBean id="transports" scope="request" type="java.util.List"/>
<c:forEach var="transport" items="${transports}">
    <p>${transport.id} ${transport.transportType.type} ${transport.model} ${transport.capacity} ${transport.speed}</p>
    <form action="${pageContext.request.contextPath}${transportPath}" method="post">
        <button type="submit" name="deleteTransport" value="${transport.id}">Удалить</button>
    </form>
    <form action="${pageContext.request.contextPath}${transportPath}/edit" method="post">
        <button type="submit" name="editTransport" value="${transport.id}">Редактировать</button>
    </form>
</c:forEach>

<jsp:include page="/WEB-INF/pages/admin/layout/footer.jsp"/>
