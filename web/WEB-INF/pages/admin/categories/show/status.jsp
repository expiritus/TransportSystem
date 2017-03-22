<%@ page import="com.belhard.misha.entity.Status" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="/WEB-INF/pages/admin/layout/header.jsp" />

<% for (Status status : (List<Status>)request.getAttribute("statuses")) {%>
    <p><%=status.getId()%> <%=status.getStatus()%></p>
<% } %>

<jsp:include page="/WEB-INF/pages/admin/layout/footer.jsp" />