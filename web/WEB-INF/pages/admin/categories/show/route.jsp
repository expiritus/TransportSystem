<%@ page import="com.belhard.misha.controllers.admin.categories.show.ShowRoute" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="/WEB-INF/pages/admin/layout/header.jsp"/>

<c:set var="routePath" value="<%=ShowRoute.URL%>" />

<jsp:useBean id="cities" scope="request" type="java.util.List" />
<jsp:useBean id="transports" scope="request" type="java.util.List" />
<jsp:useBean id="routes" scope="request" type="java.util.List"/>
<jsp:useBean id="statuses" scope="request" type="java.util.List" />

<form action="${pageContext.request.contextPath}${routePath}" method="post">
    <label for="transport">Транспорт</label>
    <select name="transport" id="transport">
        <c:forEach var="transport" items="${transports}">
            <option value="${transport.id}">${transport.transportType.type} ${transport.model} ${transport.capacity} мест</option>
        </c:forEach>
    </select>
    <label for="cityFrom">Откуда</label>
    <select name="cityFrom" id="cityFrom">

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
    <label for="status">Статус</label>
    <select name="statusId" id="status">
        <c:forEach var="status" items="${statuses}">
            <option value="${status.id}">${status.status}</option>
        </c:forEach>
    </select>
    <label for="timeDeparture">Время отправки</label>
    <input type="datetime-local" id="timeDeparture" name="timeDeparture">

    <label for="arrivalTime">Время прибытия</label>
    <input type="datetime-local" id="arrivalTime" name="arrivalTime">
    <button type="submit" name="addRoute">Отправить</button>
</form>

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
    <form action="${pageContext.request.contextPath}${routePath}" method="post">
        <button type="submit" name="deleteRoute" value="${route.id}">Удалить</button>
    </form>
    <form action="${pageContext.request.contextPath}${routePath}/edit" method="post">
        <button type="submit" name="editRoute" value="${route.id}">Редактировать</button>
    </form>
</c:forEach>

<jsp:include page="/WEB-INF/pages/admin/layout/footer.jsp"/>