<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.solomatoff.mvc.entities.User" %>
<%@ page import="java.util.List" %>
<%@ page import="java.sql.Timestamp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Create User Roles</title>
        <meta charset='utf-8'>
    </head>
    <body>
        <p><b>Create User Roles</b></p>
        <form id="createForm" action='${pageContext.servletContext.contextPath}/protected/ruduserroles' method='post'>
            <input type='hidden' name='action' value='Create'><Br>
            <b>user id:</b> <input type='number' name='userid' value=${requestScope.user.id} readonly><Br>
            <b>user role:</b>
            <c:set var="roles" value="${requestScope.roles}"/>
            <select name="roleid" size="1" form="createForm">
                <c:forEach var="role" items="${roles}">
                    <c:if test="${role.id == userroles.roleId}">
                        <option selected value="${role.id}">${role.name}</option>
                    </c:if>
                    <c:if test="${role.id != userroles.roleId}">
                        <option value="${role.id}">${role.name}</option>
                    </c:if>
                </c:forEach>
            </select><Br>
            <p><input type='submit' value='Accept'></p>
        </form>
    </body>
</html>

