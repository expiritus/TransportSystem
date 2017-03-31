<%@ page import="com.belhard.misha.controllers.admin.categories.edit.EditTransportType" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="/WEB-INF/pages/admin/layout/header.jsp"/>
<c:set var="transportTypePath" value="<%=EditTransportType.URL%>" />
<jsp:useBean id="transportType" scope="request" type="com.belhard.misha.entity.TransportType" />

<form action="${pageContext.request.contextPath}${transportTypePath}" method="post">
    <input type="text" name="type" value="${transportType.type}">
    <button type="submit" name="updateTransportType" value="${transportType.id}">Обновить</button>
</form>

<jsp:include page="/WEB-INF/pages/admin/layout/footer.jsp"/>