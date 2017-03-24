<%@ page import="com.belhard.misha.entity.City" %>
<%@ page import="java.util.List" %>
<%@ page import="com.belhard.misha.entity.Country" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="/WEB-INF/pages/admin/layout/header.jsp"/>
<form action="${pageContext.request.contextPath}/admin/city/add" method="post">
    <input type="text" name="city">
    <select name="country">
        <% for(Country country : (List<Country>) request.getAttribute("countries")) { %>
            <option value="<%=country.getId()%>"><%=country.getCountry()%></option>
        <% } %>
    </select>
    <button type="submit" name="submitCity">Отправить</button>
</form>


<%for (City city : (List<City>) request.getAttribute("cities")) {%>
<p><%=city.getId()%> <%=city.getCity()%> <%=city.getCountry().getCountry()%></p>
<% } %>

<jsp:include page="/WEB-INF/pages/admin/layout/footer.jsp"/>
