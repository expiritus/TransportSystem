<%@ page import="com.belhard.misha.entity.Country" %>
<%@ page import="java.util.List" %>
<%@ page import="com.belhard.misha.entity.City" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="/WEB-INF/pages/admin/layout/header.jsp"/>

<%for (City o : (List<City>) request.getAttribute("cities")) {%>
    <p><%=o.getId()%> <%=o.getCity()%> <%=o.getCountry().getCountry()%></p>
<% } %>

<jsp:include page="/WEB-INF/pages/admin/layout/footer.jsp"/>
