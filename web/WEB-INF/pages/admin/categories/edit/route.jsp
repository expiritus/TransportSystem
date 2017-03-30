<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.belhard.misha.controllers.admin.categories.edit.EditRoute" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="/WEB-INF/pages/admin/layout/header.jsp"/>

<jsp:useBean id="transports" scope="request" type="java.util.List"/>
<jsp:useBean id="selectedRoute" scope="request" type="com.belhard.misha.entity.Route" />
<jsp:useBean id="cities" scope="request" type="java.util.List"/>
<jsp:useBean id="statuses" scope="request" type="java.util.List" />

<c:set var="routePath" value="<%=EditRoute.URL%>"/>

<form action="${pageContext.request.contextPath}${routePath}" method="post">
    <label for="transport">Транспорт</label>
    <select name="transportId" id="transport">
        <c:forEach var="transport" items="${transports}">
            <c:choose>
                <c:when test="${selectedRoute.transportId == transport.id}">
                    <option selected value="${transport.id}">${transport.transportType.type} ${transport.model} ${transport.capacity}
                        мест
                    </option>
                </c:when>
                <c:otherwise>
                    <option value="${transport.id}">${transport.transportType.type} ${transport.model} ${transport.capacity}
                        мест
                    </option>
                </c:otherwise>
            </c:choose>

        </c:forEach>
    </select>
    <label for="cityFrom">Откуда</label>
    <select name="cityFrom" id="cityFrom">
        <c:forEach var="city" items="${cities}">
            <c:choose>
                <c:when test="${selectedRoute.from == city.id}">
                    <option selected value="${city.id}">${city.city}</option>
                </c:when>
                <c:otherwise>
                    <option value="${city.id}">${city.city}</option>
                </c:otherwise>
            </c:choose>
        </c:forEach>
    </select>
    <label for="cityTo">Куда</label>
    <select name="cityTo" id="cityTo">
        <c:forEach var="city" items="${cities}">
            <c:choose>
                <c:when test="${selectedRoute.to == city.id}">
                    <option selected value="${city.id}">${city.city}</option>
                </c:when>
                <c:otherwise>
                    <option value="${city.id}">${city.city}</option>
                </c:otherwise>
            </c:choose>
        </c:forEach>
    </select>
    <label for="status">Статус</label>
    <select name="statusId" id="status">
        <c:forEach var="status" items="${statuses}">
            <c:choose>
                <c:when test="${selectedRoute.statusId == status.id}">
                    <option selected value="${status.id}">${status.status}</option>
                </c:when>
                <c:otherwise>
                    <option value="${status.id}">${status.status}</option>
                </c:otherwise>
            </c:choose>
        </c:forEach>
    </select>
    <label for="timeDeparture">Время отправки</label>
    <input type="text" id="timeDeparture" name="timeDeparture" value="${selectedRoute.timeDeparture}">
    <c:set var="dateDeparture" value="${selectedRoute.timeDeparture}" />
    <label for="arrivalTime">Время прибытия</label>
    <input type="text" id="arrivalTime" name="arrivalTime" value="${selectedRoute.arrivalTime}">
    <button type="submit" name="updateRoute" value="${selectedRoute.id}">Отправить</button>
</form>

<jsp:include page="/WEB-INF/pages/admin/layout/footer.jsp"/>