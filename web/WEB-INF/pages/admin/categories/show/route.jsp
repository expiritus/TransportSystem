<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="/WEB-INF/pages/admin/layout/header.jsp"/>

<form action="${pageContext.request.contextPath}/admin/route/add" method="post">
    <label for="transport">Транспорт</label>
    <select name="transport" id="transport">
        <jsp:useBean id="transports" scope="request" type="java.util.List" />
        <c:forEach var="transport" items="${transports}">
            <option value="${transport.id}">${transport.transportType.type} ${transport.model} ${transport.capacity} мест</option>
        </c:forEach>
    </select>
    <label for="cityFrom">Откуда</label>
    <select name="cityFrom" id="cityFrom">
        <jsp:useBean id="cities" scope="request" type="java.util.List" />
        <c:forEach var="city" items="${cities}">
            <option value="${city.id}">${city.city}</option>
        </c:forEach>
    </select>
    <label for="cityTo">Куда</label>
    <select name="cityTo" id="cityTo">
        <c:forEach var="city" items="${cities}">
            <option value="${city.id}">${city.city}</option>
        </c:forEach>
    </select>
    <label for="timeDeparture">Время отправки</label>
    <input type="datetime-local" id="timeDeparture" name="timeDeparture">

    <label for="arrivalTime">Время прибытия</label>
    <input type="datetime-local" id="arrivalTime" name="arrivalTime">
    <button type="submit">Отправить</button>
</form>

<jsp:useBean id="routes" scope="request" type="java.util.List"/>
<c:forEach var="route" items="${routes}">
    <p>
            ${route.id}
            ${route.transport.transportType.type}
            ${route.transport.model}
            ${route.transport.capacity}
            ${route.transport.speed}
            ${route.fromCity.city}
            ${route.toCity.city}
            ${route.status.status}
            ${route.timeDeparture}
            ${route.arrivalTime}
    </p>
</c:forEach>

<jsp:include page="/WEB-INF/pages/admin/layout/footer.jsp"/>