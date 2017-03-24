<%@ page import="com.belhard.misha.entity.Country" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="/WEB-INF/pages/admin/layout/header.jsp"/>

<form action="${pageContext.request.contextPath}/admin/country/add" method="post">
    <input type="text" name="country">
    <button type="submit">Отправить</button>
</form>
<%for (Country country : (List<Country>) request.getAttribute("countries")) {%>
    <p><%=country.getId()%> <%=country.getCountry()%></p>
<% } %>

<jsp:include page="/WEB-INF/pages/admin/layout/footer.jsp"/>
