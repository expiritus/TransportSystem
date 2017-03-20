<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="/WEB-INF/pages/main/layout/header.jsp" />
<p>${requestScope.failSaveUser}</p>
<form action="registration" method="POST">
    <p><input type="text" name="name" placeholder="Имя" value="${sessionScope.name}"></p>
    <p>${sessionScope.errorValidName}</p>

    <p><input type="text" name="login" placeholder="Логин" value="${sessionScope.login}"></p>
    <p>${sessionScope.errorValidLogin}</p>

    <p><input type="text" name="email" placeholder="Email" value="${sessionScope.email}"></p>
    <p>${sessionScope.errorValidEmail}</p>

    <p><input type="password" name="password" placeholder="Пароль" value="${sessionScope.password}"></p>
    <p><input type="password" name="repeatPassword" placeholder="Повторить пароль" value="${sessionScope.repeatPassword}"></p>
    <p>${sessionScope.errorMatchPassword}</p>

    <p><button type="submit">Отправить</button></p>
</form>
<jsp:include page="/WEB-INF/pages/main/layout/footer.jsp" />
