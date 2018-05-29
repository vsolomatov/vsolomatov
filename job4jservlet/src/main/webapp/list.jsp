<%@ page import="com.solomatoff.crudservlet.User" %>
<%@ page import="java.util.List" %>
<%@ page import="com.solomatoff.crudservlet.CorePresentation" %>
<%@ page import="org.slf4j.Logger" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <meta charset='utf-8'>
        <title>Users</title>
    </head>
    <body>
        <% CorePresentation presentation = CorePresentation.getInstance();
        List<User> users = presentation.executeAction("Find All", new User());
        String contextPath = request.getContextPath(); %>
        <p><b>A list of users</b>
        <p><table width="80%" border="1" cellpadding="4" cellspacing="0">
                <tbody>
                    <tr>
                        <th>id</th>
                        <th>name</th>
                        <th>login</th>
                        <th>e-mail</th>
                        <th>createDate</th>
                        <th></th>
                        <th></th>
                        <th></th>
                    </tr>
                    <% for (User user : users) {%>
                    <tr>
                        <td><%=user.getId()%></td>
                        <td><%=user.getName()%></td>
                        <td><%=user.getLogin()%></td>
                        <td><%=user.getEmail()%></td>
                        <td><%=user.getCreateDate()%></td>
                        <td align="center">
                            <form  action='<%=contextPath%>/edit.jsp'>
                                <input type='hidden' name='action' value='Update User'>
                                <input type='hidden' name='id' value='<%=user.getId()%>'>
                                <input type='hidden' name='name' value='<%=user.getName()%>'>
                                <input type='hidden' name='login' value='<%=user.getLogin()%>'>
                                <input type='hidden' name='email' value='<%=user.getEmail()%>'>
                                <input type='hidden' name='createDate' value='<%=user.getCreateDate()%>'>
                                <input type='submit' value='Edit'>
                            </form>
                        </td>
                        <td align="center">
                            <form  action='<%=contextPath%>/edit.jsp'>
                                <input type='hidden' name='action' value='Delete User'>
                                <input type='hidden' name='id' value='<%=user.getId()%>'>
                                <input type='hidden' name='name' value='<%=user.getName()%>'>
                                <input type='hidden' name='login' value='<%=user.getLogin()%>'>
                                <input type='hidden' name='email' value='<%=user.getEmail()%>'>
                                <input type='hidden' name='createDate' value='<%=user.getCreateDate()%>'>
                                <input type='submit' value='Delete'>
                            </form>
                        </td>
                        <td align="center">
                            <form  action='<%=contextPath%>/edit.jsp'>
                                <input type='hidden' name='action' value='Find By Id'>
                                <input type='hidden' name='id' value='<%=user.getId()%>'>
                                <input type='hidden' name='name' value='<%=user.getName()%>'>
                                <input type='hidden' name='login' value='<%=user.getLogin()%>'>
                                <input type='hidden' name='email' value='<%=user.getEmail()%>'>
                                <input type='hidden' name='createDate' value='<%=user.getCreateDate()%>'>
                                <input type='submit' value='View'>
                            </form>
                        </td>
                    </tr>
                    <% } %>
                </tbody>
            </table>
        <p><form action='<%=contextPath%>/create.jsp'>
                <input type='submit' value='New'>
            </form>
        <%--! Записываем в логгер список пользователей --%>
        <% Logger logger = CorePresentation.getLOG();
        presentation.saveUsersAsXmlToLog(users, logger); %>
    </body>
</html>