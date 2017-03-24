<%@ page import="com.belhard.misha.entity.User" %>
<%@ page import="java.util.List" %>
<%@ page import="com.belhard.misha.entity.Role" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="/WEB-INF/pages/admin/layout/header.jsp" />
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
    <br />
    <% for(Role role : (List<Role>) request.getAttribute("roles")){ %>
        <%=role.getRole()%><input type="checkbox" name="roles" value="<%=role.getId()%>">
    <% } %>

    <button type="submit">Отправить</button>
</form>
<% for(User user : (List<User>)request.getAttribute("users")) {%>
    <p>
        <%=user.getId()%>
        <%=user.getName()%>
        <%=user.getLogin()%>
        <%=user.getEmail()%>
        <%=user.getPassword()%>
        <% for (Role role : user.getRoles()) {%>
            <span><%=role.getRole()%> </span>
        <% }%>
    </p>
<% } %>

<jsp:include page="/WEB-INF/pages/admin/layout/footer.jsp" />