<%@ page import="com.belhard.misha.entity.City" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="/WEB-INF/pages/admin/layout/header.jsp"/>

<%for (City city : (List<City>) request.getAttribute("cities")) {%>
<p><%=city.getId()%> <%=city.getCity()%> <%=city.getCountry().getCountry()%></p>
<% } %>

<jsp:include page="/WEB-INF/pages/admin/layout/footer.jsp"/>
