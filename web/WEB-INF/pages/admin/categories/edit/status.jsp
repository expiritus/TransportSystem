<%@ page import="com.belhard.misha.controllers.admin.categories.edit.EditStatus" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="/WEB-INF/pages/admin/layout/header.jsp" />
<c:set var="statusPath" value="<%=EditStatus.URL%>" />

<jsp:useBean id="status" scope="request" type="com.belhard.misha.entity.Status" />
<form action="${pageContext.request.contextPath}${statusPath}" method="post">
    <input type="text" name="status" value="${status.status}" >
    <button type="submit" name="updateStatus" value="${status.id}">Обновить</button>
</form>

<jsp:include page="/WEB-INF/pages/admin/layout/footer.jsp" />