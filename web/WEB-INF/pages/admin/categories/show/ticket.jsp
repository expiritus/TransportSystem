<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="/WEB-INF/pages/admin/layout/header.jsp"/>

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
</c:forEach>


<jsp:include page="/WEB-INF/pages/admin/layout/footer.jsp"/>
