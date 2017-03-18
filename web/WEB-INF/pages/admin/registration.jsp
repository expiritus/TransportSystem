<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="layout/header.jsp" />
<form action="registration" method="POST">
    <p><input type="text" name="name" placeholder="Имя"></p>
    <p><input type="text" name="login" placeholder="Логин"></p>
    <p><input type="email" name="email" placeholder="Email"></p>
    <p><input type="password" name="password" placeholder="Пароль"></p>
    <p><input type="password" name="repeatPassword" placeholder="Повторить пароль"></p>
    <p><a href="login">Логин</a></p>
    <p><button type="submit">Отправить</button></p>
</form>
<jsp:include page="layout/footer.jsp" />
