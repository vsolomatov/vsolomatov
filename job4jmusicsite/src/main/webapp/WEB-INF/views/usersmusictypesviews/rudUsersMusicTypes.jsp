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
        <form id="rudForm" action='${pageContext.servletContext.contextPath}/protected/rudusersmusictypes' method='post'>
            <input type='hidden' name='action' value='${act}'><Br>
            <b>user id:</b> <input type='number' name='userid' value='${usersmusictypes.userId}' readonly><Br>
            <b>user music Type:</b>
            <c:set var="musictypes" value="${requestScope.musictypes}"/>
            <c:if test="${sessionScope.typeuser eq 'admin'}">
                <select name="musictypeid" size="1" form="rudForm">
            </c:if>
            <c:if test="${sessionScope.typeuser ne 'admin'}">
                <select name="musictypeid" size="1" form="rudForm" disabled>
            </c:if>
                <c:forEach var="musictype" items="${musictypes}">
                    <c:if test="${musictype.id == usersmusictypes.musicTypeId}">
                        <option selected value="${musictype.id}">${musictype.musicTypeName}</option>
                    </c:if>
                    <c:if test="${musictype.id != usersmusictypes.musicTypeId}">
                        <option value="${musictype.id}">${musictype.musicTypeName}</option>
                    </c:if>
                </c:forEach>
            </select><Br>
            <p><input type='submit' value='Accept'></p>
            <%-- <c:out value="${result_exp}" default="NO DATA OF EXP"/> --%>
        </form>
    </body>
</html>

