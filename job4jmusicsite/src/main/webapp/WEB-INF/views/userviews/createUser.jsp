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
        <form id="createForm" action='${pageContext.servletContext.contextPath}/protected/ruduser' method='post'>
            <input type='hidden' name='action' value='Create User'><Br>
            <b>user id:</b> <input required type='number' name='id' value=${requestScope.user.id}><Br>
            <b>user name:</b> <input type='text' name='name' value="Enter a name in this field"><Br>
            <b>user login:</b> <input required type='text' name='login' value="Enter a login in this field"><Br>
            <b>user password:</b> <input required type='password' name='password' value="Enter a password in this field"><Br>
            <b>user e-mail:</b> <input type='text' name='email' value="Enter a e-mail in this field"><Br>
            <b>ADDRESS:</b><Br>
            <b>street:</b> <input type='text' name='street' value="Enter a street in this field"><Br>
            <b>house:</b> <input type='text' name='house' value="Enter a house in this field"><Br>
            <b>apartment:</b> <input type='text' name='apartment' value="Enter a apartment in this field"><Br>
            <b>city:</b> <input type='text' name='city' value="Enter a city in this field"><Br>
            <b>zip-code:</b> <input type='text' name='zipcode' value="Enter a zip-code in this field"><Br>
            <b>country:</b> <input type='text' name='country' value="Enter a country in this field"><Br>
            <p><input type='submit' value='Accept'></p>
        </form>
    </body>
</html>

