<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.solomatoff.mvc.entities.MusicType" %>
<%@ page import="java.util.List" %>
<%@ page import="java.sql.Timestamp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create Music Type</title>
    <meta charset='utf-8'>
</head>
<body>
<p><b>Create Music Type</b></p>
<form id="createForm" action='${pageContext.servletContext.contextPath}/protected/rudmusictype' method='post'>
    <input type='hidden' name='action' value='Create MusicType'><Br>
    <b>music Type id:</b> <input required type='number' name='id' value=${requestScope.musictype.id}><Br>
    <b>music Type name:</b> <input required type='text' name='musictypename' value=${requestScope.musictype.musicTypeName}><Br>
    <p><input type='submit' value='Accept'></p>
</form>
</body>
</html>