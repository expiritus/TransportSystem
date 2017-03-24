<%@ page import="com.belhard.misha.entity.Ticket" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="/WEB-INF/pages/admin/layout/header.jsp" />

<% for (Ticket ticket : (List<Ticket>) request.getAttribute("tickets")) {%>
    <p>
        <%=ticket.getId()%>
        <%=ticket.getUser().getName()%>
        <%=ticket.getRoute().getTransport().getTransportType().getType()%>
        <%=ticket.getRoute().getTransport().getModel()%>
        <%=ticket.getRoute().getTransport().getCapacity()%>
        <%=ticket.getRoute().getFromCity().getCity()%>
        <%=ticket.getRoute().getToCity().getCity()%>
        <%=ticket.getRoute().getTimeDeparture()%>
        <%=ticket.getRoute().getArrivalTime()%>
    </p>
<% } %>


<jsp:include page="/WEB-INF/pages/admin/layout/footer.jsp" />
