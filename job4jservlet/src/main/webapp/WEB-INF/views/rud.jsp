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
        <form id="rudForm"action='${pageContext.servletContext.contextPath}/protected/rud' method='post'>
            <input type='hidden' name='action' value='${act}'><Br>
            <b>user id:</b> <input type='number' name='id' value='${user.id}' readonly><Br>
            <b>user name:</b> <input type='text' name='name' value='${user.name}'><Br>
            <c:if test="${sessionScope.typeuser eq 'admin'}">
                <b>user login:</b> <input type='text' name='login' value='${user.login}'><Br>
            </c:if>
            <c:if test="${sessionScope.typeuser ne 'admin'}">
                <b>user login:</b> <input type='text' name='login' value='${user.login}' disabled><Br>
            </c:if>
            <b>user password:</b> <input type='password' name='password' value='${user.password}'><Br>
            <b>user e-mail:</b> <input type='text' name='email' value='${user.email}'><Br>
            <b>user role:</b>
            <c:set var="roles" value="${requestScope.roles}"/>
            <c:if test="${sessionScope.typeuser eq 'admin'}">
                <select name="idRole" size="1" form="rudForm">
            </c:if>
            <c:if test="${sessionScope.typeuser ne 'admin'}">
                <select name="idRole" size="1" form="rudForm" disabled>
            </c:if>
                <c:forEach var="role" items="${roles}">
                    <c:if test="${role.id == user.idRole}" var="result_exp_1">
                        <option selected value="${role.id}">${role.name}</option>
                    </c:if>
                    <c:if test="${role.id != user.idRole}" var="result_exp_2">
                        <option value="${role.id}">${role.name}</option>
                    </c:if>
                </c:forEach>
            </select><Br>
            <c:if test="${act eq 'Delete User' or act eq 'Find By Id'}" var="result_exp_3">
                <b>user createDate:</b> <input type='text' name='createDate' value='${user.createDate}'><Br>
            </c:if>
            <p><input type='submit' value='Accept'></p>
            <%-- <c:out value="${result_exp}" default="NO DATA OF EXP"/> --%>
        </form>
    </body>
</html>

