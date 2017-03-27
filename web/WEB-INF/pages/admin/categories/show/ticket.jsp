<%@ page import="com.belhard.misha.controllers.admin.categories.show.ShowTicket" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="/WEB-INF/pages/admin/layout/header.jsp"/>

<c:set var="ticketPath" value="<%=ShowTicket.URL%>" />
<jsp:useBean id="tickets" scope="request" type="java.util.List"/>
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
    </p>
    <form action="${pageContext.request.contextPath}${ticketPath}" method="post">
        <button type="submit" name="deleteTicket" value="${ticket.id}">Удалить</button>
    </form>
</c:forEach>


<jsp:include page="/WEB-INF/pages/admin/layout/footer.jsp"/>
