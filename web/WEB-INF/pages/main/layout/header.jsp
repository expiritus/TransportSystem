<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${requestScope.title}</title>
</head>
<body>
<header>
    <span><a href="${pageContext.request.contextPath}/user">${sessionScope.authUser.login}</a></span>
    <% if (session.getAttribute("authUser") == null ) {%>
        <a href="${pageContext.request.contextPath}/login">Login</a>
        <a href="${pageContext.request.contextPath}/registration">Registration</a>
    <% } %>
    <a href="${pageContext.request.contextPath}/logout">Logout</a>
</header>
