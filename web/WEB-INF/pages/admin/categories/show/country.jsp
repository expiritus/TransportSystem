<%@ page import="com.belhard.misha.controllers.admin.categories.show.ShowCountry" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="/WEB-INF/pages/admin/layout/header.jsp"/>

<c:set var="countryPath" value="<%=ShowCountry.URL%>" />
<form action="${pageContext.request.contextPath}${countryPath}" method="post">
    <input type="text" name="country" placeholder="Страна">
    <button type="submit" name="addCountry">Отправить</button>
</form>

<jsp:useBean id="countries" scope="request" type="java.util.List"/>
<c:forEach var="country" items="${countries}">
    <p>${country.id} ${country.country}</p>
    <form action="${pageContext.request.contextPath}${countryPath}" method="post">
        <button type="submit" name="deleteCountry" value="${country.id}">Удалить</button>
    </form>
    <form action="${pageContext.request.contextPath}${countryPath}/edit" method="post">
        <button type="submit" name="editCountry" value="${country.id}">Редактировать</button>
    </form>
</c:forEach>

<jsp:include page="/WEB-INF/pages/admin/layout/footer.jsp"/>
