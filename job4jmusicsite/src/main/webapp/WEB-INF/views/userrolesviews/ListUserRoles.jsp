<%@ page import="com.solomatoff.mvc.entities.User" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
    <head>
        <meta charset='utf-8'>
        <title>UserRoles</title>
    </head>
    <body>
        <p><b>List of user roles (user id = ${user.id})</b></p>
        <table width="80%" border="1" cellpadding="4" cellspacing="0">
            <thead>
                    <tr>
                        <th>user id</th>
                        <th>role id</th>
                        <th>role name</th>
                        <th>role is admin</th>
                        <th></th>
                    </tr>
            </thead>
            <c:set var="userroles" value="${requestScope.userroles}"/>
            <tbody>
                    <c:forEach var="userrole" items="${userroles}">
                        <tr>
                            <td align="center"><c:out value='${userrole.userId}'/></td>
                            <td align="center"><c:out value='${userrole.roleId}'/></td>
                            <td align="center">
                                <c:forEach var="role" items="${roles}">
                                    <c:if test="${role.id == userrole.roleId}">
                                        <c:out value='${role.name}'/>
                                    </c:if>
                                </c:forEach>
                            </td>
                            <td align="center">
                                <c:forEach var="role" items="${roles}">
                                    <c:if test="${role.id == userrole.roleId}">
                                        <c:out value='${role.isAdmin}'/>
                                    </c:if>
                                </c:forEach>
                            </td>
                            <td align="center">
                                <form  action='${pageContext.servletContext.contextPath}/protected/ruduserroles' method='GET'>
                                    <input type='hidden' name='action' value='Delete'>
                                    <input type='hidden' name='userid' value='${userrole.userId}'>
                                    <input type='hidden' name='roleid' value='${userrole.roleId}'>
                                    <input type='submit' value='Delete'>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
            </tbody>
        </table>
        <p>
        <c:if test="${sessionScope.typeuser eq 'admin'}">
            <form action='${pageContext.servletContext.contextPath}/protected/createuserroles' method='GET'>
                <input type='hidden' name='action' value='Create'>
                <input type='hidden' name='userid' value='${user.id}'>
                <input type='submit' value='New'>
            </form>
        </c:if>
        <p>
        <ul>
            <c:if test="${sessionScope.typeuser eq 'admin'}">
                <li><a href="${pageContext.servletContext.contextPath}/protected/listusers">Back to List users</a></li>
            </c:if>
            <li><a href="${pageContext.servletContext.contextPath}/">Back to Main Page</a></li>
            <li><a href="${pageContext.servletContext.contextPath}/logout">Exit</a></li>
        </ul>
    </body>
</html>