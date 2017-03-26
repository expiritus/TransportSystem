<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="/WEB-INF/pages/admin/layout/header.jsp"/>
<form action="${pageContext.request.contextPath}/registration" method="POST">
    <input type="text" name="name" placeholder="Имя" value="${sessionScope.name}">
    ${sessionScope.errorValidName}

    <input type="text" name="login" placeholder="Логин" value="${sessionScope.login}">
    ${sessionScope.errorValidLogin}

    <input type="text" name="email" placeholder="Email" value="${sessionScope.email}">
    ${sessionScope.errorValidEmail}

    <input type="password" name="password" placeholder="Пароль" value="${sessionScope.password}">
    <input type="password" name="repeatPassword" placeholder="Повторить пароль" value="${sessionScope.repeatPassword}">
    ${sessionScope.errorMatchPassword}
    <br/>

    <jsp:useBean id="roles" scope="request" type="java.util.List"/>
    <c:forEach var="role" items="${roles}">
        ${role.role} <input type="checkbox" name="roles" value="${role.id}">
    </c:forEach>

    <button type="submit">Отправить</button>
</form>
<jsp:useBean id="users" scope="request" type="java.util.List" />
<c:forEach var="user" items="${users}">
    <p>
        ${user.id}
        ${user.name}
        ${user.login}
        ${user.email}
        ${user.password}
        <c:forEach var="role" items="${user.roles}">
            <span>${role.role}</span>
        </c:forEach>
    </p>
    <form action="/admin/user/delete" method="post">
        <button type="submit" name="userId" value="${user.id}">Удалить</button>
    </form>
</c:forEach>

<jsp:include page="/WEB-INF/pages/admin/layout/footer.jsp"/>