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
        <form id="rudUserForm" action='${pageContext.servletContext.contextPath}/protected/ruduser' method='post'>
            <input type='hidden' name='action' value='${act}'><Br>
            <b>user id:</b> <input type='number' name='id' value='${user.id}' readonly><Br>
            <b>user name:</b> <input type='text' name='name' value='${user.name}'><Br>
            <c:if test="${sessionScope.typeuser eq 'admin'}">
                <b>user login:</b> <input required type='text' name='login' value='${user.login}'><Br>
            </c:if>
            <c:if test="${sessionScope.typeuser ne 'admin'}">
                <b>user login:</b> <input type='text' name='login' value='${user.login}' disabled><Br>
            </c:if>
            <b>user password:</b> <input required type='password' name='password' value='${user.password}'><Br>
            <b>user e-mail:</b> <input type='text' name='email' value='${user.email}'><Br>
            <b>user roles:</b><input type='text' name='nameRoles' value='${user.nameRoles}' disabled><Br>
            <c:if test="${act eq 'Delete User' or act eq 'Find By Id'}" var="result_exp_3">
                <b>user createDate:</b> <input type='text' name='createDate' value='${user.createDate}'><Br>
            </c:if>
            <b>ADDRESS:</b><Br>
            <b>street:</b> <input type='text' name='street' value='${address.street}'><Br>
            <b>house:</b> <input type='text' name='house' value='${address.house}'><Br>
            <b>apartment:</b> <input type='text' name='apartment' value='${address.apartment}'><Br>
            <b>city:</b> <input type='text' name='city' value='${address.city}'><Br>
            <b>zipcode:</b> <input type='text' name='zipcode' value='${address.zipcode}'><Br>
            <b>country:</b> <input type='text' name='country' value='${address.country}'><Br>
            <p><input type='submit' value='Accept'></p>
            <%-- <c:out value="${result_exp}" default="NO DATA OF EXP"/> --%>
        </form>
    </body>
</html>

