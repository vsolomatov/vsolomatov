<%@ page import="com.solomatoff.mvc.entities.User" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
    <head>
        <meta charset='utf-8'>
        <title>UsersMusicTypes</title>
    </head>
    <body>
        <p><b>List of user music types (user id = ${user.id})</b></p>
        <table width="80%" border="1" cellpadding="4" cellspacing="0">
            <thead>
                    <tr>
                        <th>user id</th>
                        <th>music type id</th>
                        <th>music type name</th>
                        <th></th>
                    </tr>
            </thead>
            <c:set var="usersmusictypes" value="${requestScope.usersmusictypes}"/>
            <tbody>
                    <c:forEach var="usersmusictype" items="${usersmusictypes}">
                        <tr>
                            <td align="center"><c:out value='${usersmusictype.userId}'/></td>
                            <td align="center"><c:out value='${usersmusictype.musicTypeId}'/></td>
                            <td align="center">
                                <c:forEach var="musictype" items="${musictypes}">
                                    <c:if test="${musictype.id == usersmusictype.musicTypeId}">
                                        <c:out value='${musictype.musicTypeName}'/>
                                    </c:if>
                                </c:forEach>
                            </td>
                            <td align="center">
                                <form  action='${pageContext.servletContext.contextPath}/protected/rudusersmusictypes' method='GET'>
                                    <input type='hidden' name='action' value='Delete'>
                                    <input type='hidden' name='userid' value='${usersmusictype.userId}'>
                                    <input type='hidden' name='musictypeid' value='${usersmusictype.musicTypeId}'>
                                    <input type='submit' value='Delete'>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
            </tbody>
        </table>
        <p>
        <c:if test="${sessionScope.typeuser eq 'admin'}">
            <form action='${pageContext.servletContext.contextPath}/protected/createusersmusictypes' method='GET'>
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