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
        <form action='${pageContext.servletContext.contextPath}/rud' method='post'>
            <input type='hidden' name='action' value='Create User'><Br>
            <b>user id:</b> <input type='text' name='id' value=${requestScope.user.id}><Br>
            <b>user name:</b> <input type='text' name='name' value="Enter a name in this field"><Br>
            <b>user login:</b> <input type='text' name='login' value="Enter a login in this field"><Br>
            <b>user e-mail:</b> <input type='text' name='email' value="Enter a e-mail in this field"><Br>
            <p><input type='submit' value='Accept'></p>
        </form>
    </body>
</html>

