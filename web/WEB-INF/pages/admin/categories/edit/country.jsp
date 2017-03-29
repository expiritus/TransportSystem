<%@ page import="com.belhard.misha.controllers.admin.categories.edit.EditCountry" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="/WEB-INF/pages/admin/layout/header.jsp" />

<form action="${pageContext.request.contextPath}<%=EditCountry.URL%>" method="post">
    <jsp:useBean id="country" scope="request" type="com.belhard.misha.entity.Country" />
    <input type="text" name="country" placeholder="Страна" value="${country.country}">
    <button type="submit" name="updateCountry" value="${country.id}">Обновить</button>
</form>

<jsp:include page="/WEB-INF/pages/admin/layout/footer.jsp" />