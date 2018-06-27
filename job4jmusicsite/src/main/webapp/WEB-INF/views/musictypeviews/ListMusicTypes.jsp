<%@ page import="com.solomatoff.mvc.entities.MusicType" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <meta charset='utf-8'>
    <title>Music Types</title>
</head>
<body>
<p><b>List of music types</b>
<p><table width="80%" border="1" cellpadding="4" cellspacing="0">
    <tbody>
    <tr>
        <th>id</th>
        <th>name</th>
        <th></th>
        <th></th>
        <th></th>
    </tr>
    <c:set var="musictypes" value="${requestScope.musictypes}"/>
    <c:forEach var="musictype" items="${musictypes}">
        <tr>
            <td><c:out value='${musictype.id}'/></td>
            <td><c:out value='${musictype.musicTypeName}'/></td>
            <td align="center">
                <form  action='${pageContext.servletContext.contextPath}/protected/rudmusictype' method='GET'>
                    <input type='hidden' name='action' value='Update MusicType'>
                    <input type='hidden' name='id' value='${musictype.id}'>
                    <input type='hidden' name='musictypename' value='${musictype.musicTypeName}'>
                    <input type='submit' value='Edit'>
                </form>
            </td>
            <td align="center">
                <form  action='${pageContext.servletContext.contextPath}/protected/rudmusictype' method='GET'>
                    <input type='hidden' name='action' value='Delete MusicType'>
                    <input type='hidden' name='id' value='${musictype.id}'>
                    <input type='hidden' name='musictypename' value='${musictype.musicTypeName}'>
                    <input type='submit' value='Delete'>
                </form>
            </td>
            <td align="center">
                <form  action='${pageContext.servletContext.contextPath}/protected/rudmusictype' method='GET'>
                    <input type='hidden' name='action' value='Find By Id MusicType'>
                    <input type='hidden' name='id' value='${musictype.id}'>
                    <input type='hidden' name='musictypename' value='${musictype.musicTypeName}'>
                    <input type='submit' value='View'>
                </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<p><form action='${pageContext.servletContext.contextPath}/protected/createmusictype' method='GET'>
    <input type='hidden' name='action' value='Create MusicType'>
    <input type='submit' value='New'>
</form>
<ul>
    <li><a href="${pageContext.servletContext.contextPath}/protected/listusers">Back to list of users</a></li>
    <li><a href="${pageContext.servletContext.contextPath}/logout">Exit</a></li>
</ul>
</body>
</html>