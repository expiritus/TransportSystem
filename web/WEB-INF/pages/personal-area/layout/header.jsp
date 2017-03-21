<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${requestScope.title}</title>
</head>
<body>
<header>
    <% if (request.getAttribute("adminPanel") != null) {%>
        <a href="${pageContext.request.contextPath}/admin">Admin panel</a>
    <% } %>
</header>
