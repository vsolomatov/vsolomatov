<%@ page import="com.solomatoff.mvc.entities.Role" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <meta charset='utf-8'>
    <title>Roles</title>
</head>
<body>
<p><b>List of roles</b>
<p><table width="80%" border="1" cellpadding="4" cellspacing="0">
    <tbody>
    <tr>
        <th>id</th>
        <th>name</th>
        <th>is Admin</th>
        <th></th>
        <th></th>
        <th></th>
    </tr>
    <c:set var="roles" value="${requestScope.roles}"/>
    <c:forEach var="role" items="${roles}">
        <tr>
            <td><c:out value='${role.id}'/></td>
            <td><c:out value='${role.name}'/></td>
            <td align="center"><c:out value='${role.isAdmin}'/></td>
            <td align="center">
                <form  action='${pageContext.servletContext.contextPath}/protected/rudrole' method='GET'>
                    <input type='hidden' name='action' value='Update Role'>
                    <input type='hidden' name='id' value='${role.id}'>
                    <input type='hidden' name='name' value='${role.name}'>
                    <input type='hidden' name='isAdmin' value='${role.isAdmin}'>
                    <input type='submit' value='Edit'>
                </form>
            </td>
            <td align="center">
                <form  action='${pageContext.servletContext.contextPath}/protected/rudrole' method='GET'>
                    <input type='hidden' name='action' value='Delete Role'>
                    <input type='hidden' name='id' value='${role.id}'>
                    <input type='hidden' name='name' value='${role.name}'>
                    <input type='hidden' name='isAdmin' value='${role.isAdmin}'>
                    <input type='submit' value='Delete'>
                </form>
            </td>
            <td align="center">
                <form  action='${pageContext.servletContext.contextPath}/protected/rudrole' method='GET'>
                    <input type='hidden' name='action' value='Find By Id Role'>
                    <input type='hidden' name='id' value='${role.id}'>
                    <input type='hidden' name='name' value='${role.name}'>
                    <input type='hidden' name='isAdmin' value='${role.isAdmin}'>
                    <input type='submit' value='View'>
                </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<p><form action='${pageContext.servletContext.contextPath}/protected/createrole' method='GET'>
    <input type='hidden' name='action' value='Create Role'>
    <input type='submit' value='New'>
</form>
<ul>
    <li><a href="${pageContext.servletContext.contextPath}/protected/listusers">Back to list of users</a></li>
    <li><a href="${pageContext.servletContext.contextPath}/logout">Exit</a></li>
</ul>
</body>
</html>