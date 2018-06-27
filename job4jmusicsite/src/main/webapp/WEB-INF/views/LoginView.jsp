<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title></title>
</head>
<body>
    <c:if test="${not empty error}">
        <div style="background-color: red">
           <c:out value="${error}"/>
        </div>
    </c:if>
    <form action='${pageContext.servletContext.contextPath}/login' method='post'>
        <b>User login:</b> <input type='text' name='login' value="root"><Br>
        <b>User password:</b> <input type='password' name='password' value="root"><Br>
        <input type='hidden' name='typeview' value="${param.typeview}"><Br>
        <input type='hidden' name='typestorage' value="${param.typestorage}"><Br>
        <p><input type='submit' value='Accept'></p>
    </form>
</body>
</html>
