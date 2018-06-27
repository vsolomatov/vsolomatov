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
        <table width="90%" border="1" cellpadding="4" cellspacing="0">
            <thead>
                    <tr>
                        <th>id</th>
                        <th>name</th>
                        <th>login</th>
                        <th>e-mail</th>
                        <th>password</th>
                        <th>createDate</th>
                        <th>roles</th>
                        <th>music types</th>
                        <th>address</th>
                        <th></th>
                        <th></th>
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
                            <td><c:out value='${user.nameRoles}'/></td>
                            <td><c:out value='${user.nameMusicTypes}'/></td>
                            <td><c:out value='${user.nameAddress}'/></td>
                            <td align="center">
                                <form  action='${pageContext.servletContext.contextPath}/protected/ruduser' method='GET'>
                                    <input type='hidden' name='action' value='Update User'>
                                    <input type='hidden' name='id' value='${user.id}'>
                                    <input type='submit' value='Edit'>
                                </form>
                            </td>
                            <td align="center">
                                <form  action='${pageContext.servletContext.contextPath}/protected/ruduser' method='GET'>
                                    <input type='hidden' name='action' value='Delete User'>
                                    <input type='hidden' name='id' value='${user.id}'>
                                    <input type='submit' value='Delete'>
                                </form>
                            </td>
                            <td align="center">
                                <form  action='${pageContext.servletContext.contextPath}/protected/ruduser' method='GET'>
                                    <input type='hidden' name='action' value='Find By Id User'>
                                    <input type='hidden' name='id' value='${user.id}'>
                                    <input type='submit' value='View'>
                                </form>
                            </td>
                            <c:if test="${sessionScope.typeuser eq 'admin'}">
                            <td align="center">
                                <form id="rudUserRolesForm" action='${pageContext.servletContext.contextPath}/protected/listuserroles' method='GET'>
                                    <input type='hidden' name='action' value='Update UserRoles'>
                                    <input type='hidden' name='userid' value='${user.id}'>
                                    <input type='submit' value='Edit User Roles'>
                                </form>
                            </td>
                            <td align="center">
                                <form id="rudUsersMusicTypesForm" action='${pageContext.servletContext.contextPath}/protected/listusersmusictypes' method='GET'>
                                    <input type='hidden' name='action' value='Update UsersMusicTypes'>
                                    <input type='hidden' name='userid' value='${user.id}'>
                                    <input type='submit' value='Edit User MusicTypes'>
                                </form>
                            </td>
                            </c:if>
                        </tr>
                    </c:forEach>
            </tbody>
        </table>
        <p>
        <c:if test="${sessionScope.typeuser eq 'admin'}">
            <form action='${pageContext.servletContext.contextPath}/protected/createuser' method='GET'>
                <input type='hidden' name='action' value='Create User'>
                <input type='submit' value='New'>
            </form>
        </c:if>
        <p>
        <ul>
            <c:if test="${sessionScope.typeuser eq 'admin'}">
                <li><a href="${pageContext.servletContext.contextPath}/protected/listroles">List of roles</a></li>
                <li><a href="${pageContext.servletContext.contextPath}/protected/listmusictypes">List of music types</a></li>
            </c:if>
            <li><a href="${pageContext.servletContext.contextPath}/">Back to Main Page</a></li>
            <li><a href="${pageContext.servletContext.contextPath}/logout">Exit</a></li>
        </ul>
    </body>
</html>