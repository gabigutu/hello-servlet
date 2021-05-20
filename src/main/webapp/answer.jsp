<%--
  Created by IntelliJ IDEA.
  User: gabigutu
  Date: 19/05/2021
  Time: 11:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Your answer</title>
</head>
<body>
<%@include file="header.jsp"%>
<h1>Genius</h1>

<p>Your question was: <%=request.getParameter("question")%></p>
<p>Answer: <%=java.lang.Math.random()%></p>

</body>
</html>
