<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="/WEB-INF/pages/admin/layout/header.jsp"/>

<form action="${pageContext.request.contextPath}/admin/city/add" method="post">
    <input type="text" name="city">
    <select name="country">
        <jsp:useBean id="countries" scope="request" type="java.util.List"/>
        <c:forEach var="country" items="${countries}">
            <option value="${country.id}">${country.country}</option>
        </c:forEach>
    </select>
    <button type="submit" name="submitCity">Отправить</button>
</form>

<jsp:useBean id="cities" scope="request" type="java.util.List"/>
<c:forEach var="city" items="${cities}">
    <p>${city.id} ${city.city} ${city.country.country}</p>
    <form action="/admin/city/delete" method="post">
        <button type="submit" name="cityId" value="${city.id}">Удалить</button>
    </form>
</c:forEach>

<jsp:include page="/WEB-INF/pages/admin/layout/footer.jsp"/>
