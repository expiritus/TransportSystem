<%@ page import="com.belhard.misha.entity.Country" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="/WEB-INF/pages/admin/layout/header.jsp"/>

<%for (Country o : (List<Country>) request.getAttribute("countries")) {%>
    <p><%=o.getId()%> <%=o.getCountry()%></p>
<% } %>

<jsp:include page="/WEB-INF/pages/admin/layout/footer.jsp"/>