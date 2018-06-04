<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update</title>
</head>
<body>
<section>
    <jsp:useBean id="botAttr" scope="session" type="com.solomatoff.bot.Bot"/>
    <form method="post" action="bot?action=submit">
        <dl>
            <dt>ID: </dt>
            <dd><input type="number" name="id" value="${botAttr.id}" placeholder="${botAttr.id}" /></dd>
        </dl>
        <dl>
            <dt>Name: </dt>
            <dd><input type="text" name="name" value="${botAttr.name}" placeholder="${botAttr.name}" /></dd>
        </dl>
        <dl>
            <dt>Serial number: </dt>
            <dd><input type="number" name="serial" value="${botAttr.serial}" placeholder="${botAttr.serial}" /></dd>
        </dl>
        <button type="submit">Save</button>
    </form>
</section>
</body>
</html>