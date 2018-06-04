<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Choose action</title>
</head>
    <body>
        <jsp:useBean id="gc" class="java.util.GregorianCalendar"/>
        <jsp:getProperty name="gc" property="time"/>
        <h2></h2>
        <form action="serv" method="POST">
            <input type="submit" value="Вызвать сервлет ContServlet">
        </form>
        <form action="RequestServlet" method="GET">
            <input type="submit" value="Вызвать сервлет RequestServlet">
        </form>
        <form action="bot.html" method="GET">
            <input type="submit" value="Вызвать страницу bot.html">
        </form>
    </body>
</html>
