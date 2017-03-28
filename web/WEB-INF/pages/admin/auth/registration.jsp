<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="/WEB-INF/pages/main/layout/header.jsp" />
<p>${requestScope.failSaveUser}</p>
<form action="registration" method="POST">
    <p><input type="text" name="name" placeholder="Имя" value="${requestScope.stateFullMap.name}"></p>
    <p>${requestScope.errorMap.errorValidName}</p>

    <p><input type="text" name="login" placeholder="Логин" value="${requestScope.stateFullMap.login}"></p>
    <p>${requestScope.errorMap.errorValidLogin}</p>

    <p><input type="text" name="email" placeholder="Email" value="${requestScope.stateFullMap.email}"></p>
    <p>${requestScope.errorMap.errorValidEmail}</p>

    <p><input type="password" name="password" placeholder="Пароль" value="${requestScope.stateFullMap.password}"></p>
    <p><input type="password" name="repeatPassword" placeholder="Повторить пароль" value="${requestScope.stateFullMap.repeatPassword}"></p>
    <p>${requestScope.errorMap.errorMatchPassword}</p>

    <p><button type="submit">Отправить</button></p>
</form>
<jsp:include page="/WEB-INF/pages/main/layout/footer.jsp" />
