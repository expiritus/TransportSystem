<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="layout/header.jsp" />

<h1>Вы вошли как ${sessionScope.auth.login}</h1>

<a href="logout">Logout</a>
<jsp:include page="layout/footer.jsp" />
