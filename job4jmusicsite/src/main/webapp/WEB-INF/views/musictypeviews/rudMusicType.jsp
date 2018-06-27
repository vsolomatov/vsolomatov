<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.solomatoff.mvc.entities.MusicType" %>
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
<form id="rudForm"action='${pageContext.servletContext.contextPath}/protected/rudmusictype' method='post'>
    <input type='hidden' name='action' value='${act}'><Br>
    <b>musicType id:</b> <input required type='number' name='id' value='${param.id}'><Br>
    <b>musicType name:</b> <input required type='text' name='musictypename' value='${param.musictypename}'><Br>
    <p><input type='submit' value='Accept'></p>
</form>
</body>
</html>

