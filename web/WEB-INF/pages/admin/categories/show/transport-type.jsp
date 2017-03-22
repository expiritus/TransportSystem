<%@ page import="com.belhard.misha.entity.TransportType" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="/WEB-INF/pages/admin/layout/header.jsp" />

<% for(TransportType transportType : (List<TransportType>)request.getAttribute("transportTypes")) { %>
    <p><%=transportType.getId()%> <%=transportType.getType()%></p>
<% } %>

<jsp:include page="/WEB-INF/pages/admin/layout/footer.jsp" />