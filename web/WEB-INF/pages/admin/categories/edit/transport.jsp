<%@ page import="com.belhard.misha.controllers.admin.categories.edit.EditTransport" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="/WEB-INF/pages/admin/layout/header.jsp"/>
<c:set var="transportPath" value="<%=EditTransport.URL%>"/>
<jsp:useBean id="transportTypes" scope="request" type="java.util.List" />
<jsp:useBean id="selectedTransport" scope="request" type="com.belhard.misha.entity.Transport" />


<form action="${pageContext.request.contextPath}${transportPath}" method="post">
    <select name="transportTypeId">
        <c:forEach var="transportType" items="${transportTypes}">
            <c:choose>
                <c:when test="${selectedTransport.transportTypeId == transportType.id}">
                    <option selected value="${transportType.id}">${transportType.type}</option>
                </c:when>
                <c:otherwise>
                    <option value="${transportType.id}">${transportType.type}</option>
                </c:otherwise>
            </c:choose>
        </c:forEach>
    </select>
    <input type="text" name="model" value="${selectedTransport.model}" placeholder="Модель">
    <input type="text" name="capacity" value="${selectedTransport.capacity}" placeholder="Вместимость">
    <input type="text" name="speed" value="${selectedTransport.speed}" placeholder="Скорость">
    <button type="submit" name="updateTransport" value="${selectedTransport.id}">Обновить</button>
</form>

<jsp:include page="/WEB-INF/pages/admin/layout/footer.jsp"/>