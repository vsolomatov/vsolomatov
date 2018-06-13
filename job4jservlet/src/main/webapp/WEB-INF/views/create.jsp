<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.solomatoff.mvc.entities.User" %>
<%@ page import="java.util.List" %>
<%@ page import="java.sql.Timestamp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Create User</title>
        <meta charset='utf-8'>
    </head>
    <body>
        <p><b>Create User</b></p>
        <form id="createForm" action='${pageContext.servletContext.contextPath}/protected/rud' method='post'>
            <input type='hidden' name='action' value='Create User'><Br>
            <b>user id:</b> <input type='number' name='id' value=${requestScope.user.id}><Br>
            <b>user name:</b> <input type='text' name='name' value="Enter a name in this field"><Br>
            <b>user login:</b> <input type='text' name='login' value="Enter a login in this field"><Br>
            <b>user password:</b> <input type='password' name='password' value="Enter a password in this field"><Br>
            <b>user e-mail:</b> <input type='text' name='email' value="Enter a e-mail in this field"><Br>
            <b>user role:</b>
            <c:set var="roles" value="${requestScope.roles}"/>
            <select name="idRole" size="1" form="createForm">
                <c:forEach var="role" items="${roles}">
                    <c:if test="${role.id == user.idRole}" var="result_exp_1">
                        <option selected value="${role.id}">${role.name}</option>
                    </c:if>
                    <c:if test="${role.id != user.idRole}" var="result_exp_2">
                        <option value="${role.id}">${role.name}</option>
                    </c:if>
                </c:forEach>
            </select><Br>
            <p><input type='submit' value='Accept'></p>
        </form>
    </body>
</html>

