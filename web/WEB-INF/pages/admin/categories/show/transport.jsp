<%@ page import="com.belhard.misha.entity.Transport" %>
<%@ page import="java.util.List" %>
<%@ page import="com.belhard.misha.entity.TransportType" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="/WEB-INF/pages/admin/layout/header.jsp" />

<form action="${pageContext.request.contextPath}/admin/transport/add" method="post">
    <select name="transportType">
        <% for (TransportType transportType : (List<TransportType>) request.getAttribute("transportTypes")) {%>
        <option value="<%=transportType.getId()%>"><%=transportType.getType()%></option>
        <% } %>
    </select>
    <input type="text" name="model" placeholder="Model">
    <input type="text" name="capacity" placeholder="Capacity">
    <input type="text" name="speed" placeholder="Speed">
    <button type="submit" name="transportSubmit">Отправить</button>
</form>

<% for(Transport transport : (List<Transport>) request.getAttribute("transports")) { %>
    <p><%=transport.getId()%> <%=transport.getTransportType().getType()%> <%=transport.getModel()%> <%=transport.getCapacity()%> <%=transport.getSpeed()%></p>
<% } %>

<jsp:include page="/WEB-INF/pages/admin/layout/footer.jsp" />
