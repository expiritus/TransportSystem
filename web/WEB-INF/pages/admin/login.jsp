<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="layout/header.jsp" />
<form action="login" method="POST">
    <p><input type="text" name="login" placeholder="Логин" value="${sessionScope.login}"></p>
    <p>${sessionScope.errorValidateLogin}</p>

    <p><input type="password" name="password" placeholder="Пароль"></p>
    <p>${sessionScope.errorValidatePassword}</p>
    <p>${sessionScope.userNotFound}</p>
    <p><a href="registration">Регистрация</a></p>
    <p><button type="submit">Отправить</button></p>
</form>
<jsp:include page="layout/footer.jsp" />
