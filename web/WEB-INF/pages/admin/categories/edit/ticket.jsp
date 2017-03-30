<%@ page import="com.belhard.misha.controllers.admin.categories.edit.EditTicket" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="/WEB-INF/pages/admin/layout/header.jsp"/>
<c:set var="statusPath" value="<%=EditTicket.URL%>"/>

<jsp:useBean id="selectedTicket" scope="request" type="com.belhard.misha.entity.Ticket"/>
<jsp:useBean id="users" scope="request" type="java.util.List"/>
<jsp:useBean id="routes" scope="request" type="java.util.List"/>
<form action="${pageContext.request.contextPath}${statusPath}" method="post">
    <select name="userId">
        <c:forEach var="user" items="${users}">
            <c:choose>
                <c:when test="${selectedTicket.userId == user.id}">
                    <option selected value="${user.id}">${user.name}</option>
                </c:when>
                <c:otherwise>
                    <option value="${user.id}">${user.name}</option>
                </c:otherwise>
            </c:choose>
        </c:forEach>
    </select>
    <select name="routeId">
        <c:forEach var="route" items="${routes}">
            <c:choose>
                <c:when test="${selectedTicket.routeId == route.id}">
                    <option selected value="${route.id}">${route.transport.transportType.type} ${route.transport.model} ${route.fromCity.city} ${route.toCity.city} ${route.timeDeparture} ${route.arrivalTime}</option>
                </c:when>
                <c:otherwise>
                    <option value="${route.id}">${route.transport.transportType.type} ${route.transport.model} ${route.fromCity.city} ${route.toCity.city} ${route.timeDeparture} ${route.arrivalTime}</option>
                </c:otherwise>
            </c:choose>
        </c:forEach>
    </select>
    <c:choose>
        <c:when test="${selectedTicket.reservationStatus}">
            Статус резервации <input name="reservationStatus" type="checkbox" checked value="true">
        </c:when>
        <c:otherwise>
            Статус резервации <input name="reservationStatus" type="checkbox" value="false">
        </c:otherwise>
    </c:choose>
    <input name="dateReservation" type="text" id="dateTimeLocal" value="${selectedTicket.dateReservation}">

    <c:choose>
        <c:when test="${selectedTicket.payStatus}">
            Оплачен <input name="payStatus" type="checkbox" checked value="true">
        </c:when>
        <c:otherwise>
            Оплачен <input name="payStatus" type="checkbox" value="true">
        </c:otherwise>
    </c:choose>
    <input name="datePay" type="text" value="${selectedTicket.datePay}">
    <button type="submit" name="updateTicket" value="${selectedTicket.id}">Обновить</button>
</form>

<jsp:include page="/WEB-INF/pages/admin/layout/footer.jsp"/>