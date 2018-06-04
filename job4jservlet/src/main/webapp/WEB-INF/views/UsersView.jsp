<%@ page import="com.solomatoff.mvc.entities.User" %>
<%@ page import="java.util.List" %>
<%@ page import="org.slf4j.Logger" %>
<%@ page import="com.solomatoff.mvc.controller.LoggerUtil" %>
<%@ page import="com.solomatoff.mvc.controller.Controller" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
    <head>
        <meta charset='utf-8'>
        <title>Users</title>
    </head>
    <body>
        <p><b>A list of users</b>
        <p><table width="80%" border="1" cellpadding="4" cellspacing="0">
                <tbody>
                    <tr>
                        <th>id</th>
                        <th>name</th>
                        <th>login</th>
                        <th>e-mail</th>
                        <th>createDate</th>
                        <th></th>
                        <th></th>
                        <th></th>
                    </tr>
                    <c:set var="users" value="${requestScope.users}"/>
                    <c:forEach var="user" items="${users}">
                    <tr>
                        <td><c:out value='${user.id}'/></td>
                        <td><c:out value='${user.name}'/></td>
                        <td><c:out value='${user.login}'/></td>
                        <td><c:out value='${user.email}'/></td>
                        <td><c:out value='${user.createDate}'/></td>
                        <td align="center">
                            <form  action='${pageContext.servletContext.contextPath}/rud' method='GET')>
                                <input type='hidden' name='action' value='Update User'>
                                <input type='hidden' name='id' value='${user.id}'>
                                <input type='hidden' name='name' value='${user.name}'>
                                <input type='hidden' name='login' value='${user.login}'>
                                <input type='hidden' name='email' value='${user.email}'>
                                <input type='hidden' name='createDate' value='${user.createDate}'>
                                <input type='submit' value='Edit'>
                            </form>
                        </td>
                        <td align="center">
                            <form  action='${pageContext.servletContext.contextPath}/rud' method='GET'>
                                <input type='hidden' name='action' value='Delete User'>
                                <input type='hidden' name='id' value='${user.id}'>
                                <input type='hidden' name='name' value='${user.name}'>
                                <input type='hidden' name='login' value='${user.login}'>
                                <input type='hidden' name='email' value='${user.email}'>
                                <input type='hidden' name='createDate' value='${user.createDate}'>
                                <input type='submit' value='Delete'>
                            </form>
                        </td>
                        <td align="center">
                            <form  action='${pageContext.servletContext.contextPath}/rud' method='GET'>
                                <input type='hidden' name='action' value='Find By Id'>
                                <input type='hidden' name='id' value='${user.id}'>
                                <input type='hidden' name='name' value='${user.name}'>
                                <input type='hidden' name='login' value='${user.login}'>
                                <input type='hidden' name='email' value='${user.email}'>
                                <input type='hidden' name='createDate' value='${user.createDate}'>
                                <input type='submit' value='View'>
                            </form>
                        </td>
                    </tr>
                    </c:forEach>
                </tbody>
            </table>
        <p><form action='${pageContext.servletContext.contextPath}/create' method='GET'>
            <input type='hidden' name='action' value='Create User'>
                <input type='submit' value='New'>
            </form>
    </body>
</html>