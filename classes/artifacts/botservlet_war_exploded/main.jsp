<%@ page import="com.solomatoff.chapt17.ContServlet" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Data from servlet</title>
</head>
<body>
<h3>Региональные установки и Время</h3>
<c:out value="Locale from request: ${loc}"/><br>
<c:out value="Time from request: ${calend.time}"/><br>
<br>
<h3>Еще некоторые данные из сервлета</h3>
<c:out value="servletName: ${servletName}"/><br>
<c:out value="mimeTypeHtml: ${mimeTypeHtml}"/><br>
<c:out value="mimeTypeJsp: ${mimeTypeJsp}"/><br>
<c:out value="realPath main.jsp: ${realPath}"/><br>
<c:out value="serverInfo: ${serverInfo}"/><br>

<jsp:useBean id="reqBean" scope="request" class="com.solomatoff.chapt17.ReqAsBean"/>
<h3>Данные из запроса сервлета</h3>
<c:out value="CharacterEncoding: ${reqBean.characterEncoding}"/><br>
<c:out value="ContentType: ${reqBean.contentType}"/><br>
<c:out value="Protocol: ${reqBean.protocol}"/><br>
<c:out value="ServerName: ${reqBean.serverName}"/><br>
<c:out value="ServerPort: ${reqBean.serverPort}"/><br>
<c:out value="RemoteAddr: ${reqBean.remoteAddr}"/><br>
<c:out value="RemoteHost: ${reqBean.remoteHost}"/><br>
<c:out value="RemoteUser: ${reqBean.remoteUser}"/><br>
<c:out value="Method: ${reqBean.method}"/><br>
<c:out value="QueryString: ${reqBean.queryString}"/><br>

<h3>Данные из контекстов</h3>
<c:out value="${requestScope.reqBean}" default="NO DATA"/><br>
<c:out value="${requestScope.reqBean.getServerName()}" default="NO DATA"/><br>
<c:out value="${sessionScope.test}" default="NO DATA"/><br>
</body>
</html>
