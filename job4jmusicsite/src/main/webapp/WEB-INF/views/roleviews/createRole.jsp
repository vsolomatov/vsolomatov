<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.solomatoff.mvc.entities.Role" %>
<%@ page import="java.util.List" %>
<%@ page import="java.sql.Timestamp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create Role</title>
    <meta charset='utf-8'>
</head>
<body>
<p><b>Create Role</b></p>
<form id="createForm" action='${pageContext.servletContext.contextPath}/protected/rudrole' method='post'>
    <input type='hidden' name='action' value='Create Role'><Br>
    <b>role id:</b> <input required type='number' name='id' value=${requestScope.role.id}><Br>
    <b>role name:</b> <input required type='text' name='name' value=${requestScope.role.name}><Br>
    <b>role is admin:</b>
    <select name="isAdmin" size="1" form="createForm">
        <option selected value="false">No</option>
        <option value="true">Yes</option>
    </select><Br>
    <p><input type='submit' value='Accept'></p>
</form>
</body>
</html>