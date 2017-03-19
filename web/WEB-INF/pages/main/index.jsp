<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="layout/header.jsp" />

<h1>Вы вошли как ${sessionScope.authUser.login}</h1>
<p><a href="login">Login</a></p>
<p><a href="logout">Logout</a></p>

<jsp:include page="layout/footer.jsp" />