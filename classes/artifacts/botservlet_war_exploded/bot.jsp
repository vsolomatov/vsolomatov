<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Bot</title>
</head>
<body>
<section>
    <h3>Bot info</h3>
    <jsp:useBean id="botAttr" scope="session" type="com.solomatoff.bot.Bot"/>
    <tr>
        <td>ID: ${botAttr.id} | Name: ${botAttr.name} | Serial number: ${botAttr.serial}</td>
        <td><a href="bot?action=update">Update</a></td>
    </tr>
</section>
</body>
</html>