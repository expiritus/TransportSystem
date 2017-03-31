<%@ page import="com.belhard.misha.controllers.admin.categories.show.ShowTicket" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="/WEB-INF/pages/admin/layout/header.jsp"/>

<c:set var="ticketPath" value="<%=ShowTicket.URL%>"/>
<jsp:useBean id="tickets" scope="request" type="java.util.List"/>
<jsp:useBean id="users" scope="request" type="java.util.List" />
<jsp:useBean id="routes" scope="request" type="java.util.List" />
<form action="${pageContext.request.contextPath}${ticketPath}" method="post">
    <select name="userId">
        <c:forEach var="user" items="${users}">
            <option value="${user.id}">${user.name}</option>
        </c:forEach>
    </select>
    <select name="routeId">
        <c:forEach var="route" items="${routes}">
            <option selected value="${route.id}">${route.transport.transportType.type} ${route.transport.model} ${route.fromCity.city} ${route.toCity.city} ${route.timeDeparture} ${route.arrivalTime}</option>
        </c:forEach>
    </select>
    Зарезервирован <input type="checkbox" name="reservationStatus">
    Дата резервации <input type="datetime-local" name="dateReservation">
    Оплачен <input type="checkbox" name="payStatus">
    Дата оплаты <input type="datetime-local" name="datePay">
    <button type="submit" name="addTicket">Отправить</button>
</form>



<c:forEach var="ticket" items="${tickets}">
    <p>
            ${ticket.id}
            ${ticket.user.name}
            ${ticket.route.transport.transportType.type}
            ${ticket.route.transport.model}
            ${ticket.route.transport.capacity}
            ${ticket.route.fromCity.city}
            ${ticket.route.toCity.city}
            ${ticket.route.timeDeparture}
            ${ticket.route.arrivalTime}
            <c:choose>
                <c:when test="${ticket.reservationStatus}">
                    Зарезервирован
                </c:when>
                <c:otherwise>
                    Не заразервирован
                </c:otherwise>
            </c:choose>
            <c:choose>
                <c:when test="${ticket.payStatus}">
                    Оплачен
                </c:when>
                <c:otherwise>
                    Не оплачен
                </c:otherwise>
            </c:choose>
    </p>
    <form action="${pageContext.request.contextPath}${ticketPath}" method="post">
        <button type="submit" name="deleteTicket" value="${ticket.id}">Удалить</button>
    </form>
    <form action="${pageContext.request.contextPath}${ticketPath}/edit" method="post">
        <button type="submit" name="editTicket" value="${ticket.id}">Редактировать</button>
    </form>
</c:forEach>


<jsp:include page="/WEB-INF/pages/admin/layout/footer.jsp"/>
