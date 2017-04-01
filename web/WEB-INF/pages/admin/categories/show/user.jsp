<%@ page import="com.belhard.misha.controllers.admin.categories.show.ShowUser" %>
<%@ page import="com.belhard.misha.controllers.admin.auth.RegistrationController" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="/WEB-INF/pages/admin/layout/header.jsp"/>

<c:set var="userPath" value="<%=ShowUser.URL%>" />
<c:set var="registrationPath" value="<%=RegistrationController.URL%>" />
<form action="${pageContext.request.contextPath}${registrationPath}" method="POST">
    <input type="text" name="name" placeholder="Имя" value="${requestScope.stateFullMap.name}">
    ${requestScope.errorMap.errorValidName}

    <input type="text" name="login" placeholder="Логин" value="${requestScope.stateFullMap.login}">
    ${requestScope.errorMap.errorValidLogin}

    <input type="text" name="email" placeholder="Email" value="${requestScope.stateFullMap.email}">
    ${requestScope.errorMap.errorValidEmail}

    <input type="password" name="password" placeholder="Пароль" value="${requestScope.stateFullMap.password}">
    <input type="password" name="repeatPassword" placeholder="Повторить пароль" value="${requestScope.stateFullMap.repeatPassword}">
    ${requestScope.errorMap.errorMatchPassword}
    <br/>

    <%--<jsp:useBean id="roles" scope="request" type="java.util.List"/>--%>
    <c:forEach var="role" items="${roles}">
        ${role.role} <input type="checkbox" name="roles" value="${role.id}">
    </c:forEach>

    <button type="submit" name="addUser">Отправить</button>
</form>
<%--<jsp:useBean id="users" scope="request" type="java.util.List" />--%>
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
    <form action="${pageContext.request.contextPath}${userPath}" method="post">
        <button type="submit" name="deleteUser" value="${user.id}">Удалить</button>
    </form>
    <form action="${pageContext.request.contextPath}${userPath}/edit" method="post">
        <button type="submit" name="editUser" value="${user.id}">Редактировать</button>
    </form>
</c:forEach>

<jsp:include page="/WEB-INF/pages/admin/layout/footer.jsp"/>