<%@ page import="com.belhard.misha.entity.Route" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="/WEB-INF/pages/admin/layout/header.jsp"/>

<% for (Route route : (List<Route>) request.getAttribute("routes")) {%>
<p><%=route.getId()%>
    <%=route.getTransport().getTransportType().getType()%>
    <%=route.getTransport().getModel()%>
    <%=route.getTransport().getCapacity()%>
    <%=route.getTransport().getSpeed()%>
    <%=route.getFromCity().getCity()%>
    <%=route.getToCity().getCity()%>
    <%=route.getStatus().getStatus()%>
    <%=route.getTimeDeparture()%>
    <%=route.getArrivalTime()%>
</p>
<% } %>

<jsp:include page="/WEB-INF/pages/admin/layout/footer.jsp"/>