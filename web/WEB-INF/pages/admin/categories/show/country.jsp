<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.belhard.misha.entity.Country" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="/WEB-INF/pages/admin/layout/header.jsp"/>

<form action="${pageContext.request.contextPath}/admin/country/add" method="post">
    <input type="text" name="country">
    <button type="submit">Отправить</button>
</form>

<jsp:useBean id="countries" scope="request" type="java.util.List" />
<c:forEach var="country" items="${countries}">
    <p>${country.id} ${country.country}</p>
    <form action="/admin/country/delete" method="post">
        <button type="submit" name="countryId" value="${country.id}">Удалить</button>
    </form>
</c:forEach>

<jsp:include page="/WEB-INF/pages/admin/layout/footer.jsp"/>
