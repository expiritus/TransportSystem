<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${requestScope.title}</title>
</head>
<body>
<header>
    <a href="${pageContext.request.contextPath}/">Main</a>
    <a href="${pageContext.request.contextPath}/admin">Home</a>
    <a href="${pageContext.request.contextPath}/admin/transport-type">Transport type</a>
    <a href="${pageContext.request.contextPath}/admin/transport">Transport</a>
    <a href="${pageContext.request.contextPath}/admin/route">Route</a>
    <a href="${pageContext.request.contextPath}/admin/city">City</a>
    <a href="${pageContext.request.contextPath}/admin/country">Country</a>
    <a href="${pageContext.request.contextPath}/admin/status">Status</a>
    <a href="${pageContext.request.contextPath}/admin/ticket">Ticket</a>
    <a href="${pageContext.request.contextPath}/admin/user">User</a>
    <a href="${pageContext.request.contextPath}/admin/role">Role</a>
    <a href="${pageContext.request.contextPath}/logout">Logout</a>
    <span>Вы вошли как <a href="${pageContext.request.contextPath}/user">${sessionScope.authUser.login}</a></span>
</header>
