<%@ page import="com.solomatoff.crudservlet.User" %>
<%@ page import="java.util.List" %>
<%@ page import="java.sql.Timestamp" %>
<%@ page import="com.solomatoff.crudservlet.CorePresentation" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <% String action = request.getParameter("action"); %>
    <title><%=action%></title>
<html>
<head>
    <meta charset='utf-8'>
    <title><%=action%></title>
</head>
<body>
<p><b><%=action%></b></p>
<form action='<%=request.getContextPath()%>/edit' method='post'>
    <% String userId = request.getParameter("id");
       String userName = request.getParameter("name");
        String userLogin = request.getParameter("login");
        String userEmail = request.getParameter("email");
        String userCreateDate = request.getParameter("createDate");
    %>
    <input type='hidden' name='action' value='<%=action%>'><Br>
    <b>user id:</b> <input type='text' name='id' value='<%=userId%>'><Br>
    <b>user name:</b> <input type='text' name='name' value='<%=userName%>'><Br>
    <b>user login:</b> <input type='text' name='login' value='<%=userLogin%>'><Br>
    <b>user e-mail:</b> <input type='text' name='email' value='<%=userEmail%>'><Br>
    <% if (!action.equals("Create User") && !action.equals("Update User")) { %>
        <b>user createDate:</b> <input type='text' name='createDate' value='<%=userCreateDate%>'><Br>
    <% } %>
    <p><input type='submit' value='Accept'></p>
</form>
</body>
</html>

