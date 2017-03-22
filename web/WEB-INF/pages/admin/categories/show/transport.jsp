<%@ page import="com.belhard.misha.entity.Transport" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="/WEB-INF/pages/admin/layout/header.jsp" />

<% for(Transport transport : (List<Transport>) request.getAttribute("transports")) { %>
    <p><%=transport.getId()%> <%=transport.getTransportType().getType()%> <%=transport.getModel()%> <%=transport.getCapacity()%> <%=transport.getSpeed()%></p>
<% } %>

<jsp:include page="/WEB-INF/pages/admin/layout/footer.jsp" />
