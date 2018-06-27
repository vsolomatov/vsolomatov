<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.solomatoff.mvc.entities.User" %>
<%@ page import="java.util.List" %>
<%@ page import="java.sql.Timestamp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Create User Music Types</title>
        <meta charset='utf-8'>
    </head>
    <body>
        <p><b>Create User Music Types</b></p>
        <form id="createForm" action='${pageContext.servletContext.contextPath}/protected/rudusersmusictypes' method='post'>
            <input type='hidden' name='action' value='Create'><Br>
            <b>user id:</b> <input type='number' name='userid' value=${requestScope.user.id} readonly><Br>
            <b>user music type:</b>
            <c:set var="musictypes" value="${requestScope.musictypes}"/>
            <select name="musictypeid" size="1" form="createForm">
                <c:forEach var="musictype" items="${musictypes}">
                    <c:if test="${musictype.id == usersmusictypes.musicTypeId}">
                        <option selected value="${musictype.id}">${musictype.name}</option>
                    </c:if>
                    <c:if test="${musictype.id != usersmusictypes.musicTypeId}">
                        <option value="${musictype.id}">${musictype.name}</option>
                    </c:if>
                </c:forEach>
            </select><Br>
            <p><input type='submit' value='Accept'></p>
        </form>
    </body>
</html>

