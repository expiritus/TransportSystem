<%@ page import="com.belhard.misha.entity.Role" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="/WEB-INF/pages/admin/layout/header.jsp"/>

<% for (Role role : (List<Role>) request.getAttribute("roles")) {%>
<p><%=role.getId()%> <%=role.getRole()%></p>
<% } %>
<jsp:include page="/WEB-INF/pages/admin/layout/footer.jsp"/>