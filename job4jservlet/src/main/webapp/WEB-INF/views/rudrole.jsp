<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.solomatoff.mvc.entities.Role" %>
<%@ page import="java.util.List" %>
<%@ page import="java.sql.Timestamp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset='utf-8'>
    <c:set var="act" value="${param.action}"/>
    <title><c:out value="${act}"/></title>
</head>
<body>
<p><b><c:out value="${act}"/></b></p>
<form id="rudForm"action='${pageContext.servletContext.contextPath}/protected/rudrole' method='post'>
    <input type='hidden' name='action' value='${act}'><Br>
    <b>role id:</b> <input type='number' name='id' value='${param.id}'><Br>
    <b>role name:</b> <input type='text' name='name' value='${param.name}'><Br>
    <b>role is admin:</b>
        <select name="isAdmin" size="1" form="rudForm">
            <c:if test="${param.isAdmin eq 'false'}">
                <option selected value="false">No</option>
                <option value="true">Yes</option>
            </c:if>
            <c:if test="${param.isAdmin eq 'true'}">
                <option value="false">No</option>
                <option selected value="true">Yes</option>
            </c:if>
        </select>
    <p><input type='submit' value='Accept'></p>
</form>
</body>
</html>

