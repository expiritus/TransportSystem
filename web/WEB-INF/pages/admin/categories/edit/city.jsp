<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="/WEB-INF/pages/admin/layout/header.jsp" />

<form action="${pageContext.request.contextPath}/admin/city/edit" method="post">
    <jsp:useBean id="city" scope="request" type="com.belhard.misha.entity.City" />
    <input type="text" name="city" value="${city.city}">
    <select name="country">
        <jsp:useBean id="countries" scope="request" type="java.util.List" />
        <c:forEach var="country" items="${countries}">
            <c:choose>
                <c:when test="${country.country == requestScope.selected}">
                    <option selected value="${country.id}">${country.country}</option>
                </c:when>
                <c:otherwise>
                    <option value="${country.id}">${country.country}</option>
                </c:otherwise>
            </c:choose>
        </c:forEach>
    </select>
    <button type="submit" name="updateCity">Обновить</button>
</form>

<jsp:include page="/WEB-INF/pages/admin/layout/footer.jsp" />
