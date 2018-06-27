<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title></title>
</head>
<body>
<form action='${pageContext.servletContext.contextPath}/logout' method='post'>
    <b>Выйти из сессии "${sessionScope.login}" ?</b><br>
    <input type="radio" name="return" value="Yes" checked> Yes
    <input type="radio" name="return" value="No"> No<br>
    <p><input type='submit' value='Accept'></p>
</form>
</body>
</html>
