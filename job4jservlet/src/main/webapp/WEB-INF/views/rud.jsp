<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.solomatoff.mvc.entities.User" %>
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
        <form action='${pageContext.servletContext.contextPath}/rud' method='post'>
            <input type='hidden' name='action' value='${act}'><Br>
            <b>user id:</b> <input type='text' name='id' value='${param.id}'><Br>
            <b>user name:</b> <input type='text' name='name' value='${param.name}'><Br>
            <b>user login:</b> <input type='text' name='login' value='${param.login}'><Br>
            <b>user e-mail:</b> <input type='text' name='email' value='${param.email}'><Br>
            <c:if test="${act eq 'Delete User' or act eq 'Find By Id'}" var="result_exp">
                <b>user createDate:</b> <input type='text' name='createDate' value='${param.createDate}'><Br>
            </c:if>
            <p><input type='submit' value='Accept'></p>
            <%-- <c:out value="${result_exp}" default="NO DATA OF EXP"/> --%>
        </form>
    </body>
</html>

