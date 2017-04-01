<%@ page import="com.belhard.misha.controllers.admin.categories.show.ShowCity" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="/WEB-INF/pages/admin/layout/header.jsp"/>

<c:set var="cityPath" value="<%=ShowCity.URL%>" />
<form action="${pageContext.request.contextPath}${cityPath}" method="post">
    <input type="text" name="city" placeholder="Город">
    <select name="country">
        <jsp:useBean id="countries" scope="request" type="java.util.List"/>
        <c:forEach var="country" items="${countries}">
            <option value="${country.id}">${country.country}</option>
        </c:forEach>
    </select>
    <button type="submit" name="addCity">Отправить</button>
</form>

<jsp:useBean id="cities" scope="request" type="java.util.List"/>
<c:forEach var="city" items="${cities}">
    <p>${city.id} ${city.city} ${city.country.country}</p>

    <c:if test="${sessionScope.errorCityId == city.id}">
        ${sessionScope.errorDeleteCity}
    </c:if>
    <form action="${pageContext.request.contextPath}${cityPath}" method="post">
        <button type="submit" name="deleteCity" value="${city.id}">Удалить</button>
    </form>
    <form action="${pageContext.request.contextPath}${cityPath}/edit" method="post">
        <button type="submit" name="editCity" value="${city.id}">Редактировать</button>
        <input type="hidden" name="country" value="${city.country.country}">
    </form>
</c:forEach>

<jsp:include page="/WEB-INF/pages/admin/layout/footer.jsp"/>
