<%@ page import="com.solomatoff.mvc.entities.User" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
    <head>
        <meta charset='utf-8'>
        <title>Users</title>
    </head>
    <body>
        <p><b>List of users</b></p>
        <table width="80%" border="1" cellpadding="4" cellspacing="0">
            <thead>
                    <tr>
                        <th>id</th>
                        <th>name</th>
                        <th>login</th>
                        <th>e-mail</th>
                        <th>password</th>
                        <th>createDate</th>
                        <th>nameRole</th>
                        <th></th>
                        <th></th>
                        <th></th>
                    </tr>
            </thead>
            <c:set var="users" value="${requestScope.users}"/>
            <tbody>
                    <c:forEach var="user" items="${users}">
                        <tr>
                            <td><c:out value='${user.id}'/></td>
                            <td><c:out value='${user.name}'/></td>
                            <td><c:out value='${user.login}'/></td>
                            <td><c:out value='${user.email}'/></td>
                            <td><c:out value='${user.password}'/></td>
                            <td><c:out value='${user.createDate}'/></td>
                            <td><c:out value='${user.nameRole}'/></td>
                            <td align="center">
                                <form  action='${pageContext.servletContext.contextPath}/protected/rud' method='GET')>
                                    <input type='hidden' name='action' value='Update User'>
                                    <input type='hidden' name='id' value='${user.id}'>
                                    <input type='submit' value='Edit'>
                                </form>
                            </td>
                            <td align="center">
                                <form  action='${pageContext.servletContext.contextPath}/protected/rud' method='GET'>
                                    <input type='hidden' name='action' value='Delete User'>
                                    <input type='hidden' name='id' value='${user.id}'>
                                    <input type='submit' value='Delete'>
                                </form>
                            </td>
                            <td align="center">
                                <form  action='${pageContext.servletContext.contextPath}/protected/rud' method='GET'>
                                    <input type='hidden' name='action' value='Find By Id User'>
                                    <input type='hidden' name='id' value='${user.id}'>
                                    <input type='submit' value='View'>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
            </tbody>
        </table>
        <p>
        <c:if test="${sessionScope.typeuser eq 'admin'}">
            <form action='${pageContext.servletContext.contextPath}/protected/create' method='GET'>
                <input type='hidden' name='action' value='Create User'>
                <input type='submit' value='New'>
            </form>
        </c:if>
        <p>
        <ul>
            <c:if test="${sessionScope.typeuser eq 'admin'}">
                <li><a href="${pageContext.servletContext.contextPath}/protected/listroles">List of roles</a></li>
            </c:if>
            <li><a href="${pageContext.servletContext.contextPath}/">Back to Main Page</a></li>
            <li><a href="${pageContext.servletContext.contextPath}/logout">Exit</a></li>
        </ul>
    </body>
</html>