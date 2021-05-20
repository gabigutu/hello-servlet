<%! int a; String s, t; %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<%@include file="header.jsp"%>
<h1><%= "Bună, bine ați venit!" %>
</h1>
<br/>
<%= 7  %><!-- expression tag (acesta e un comment de html) -->
<%= true %>
<% int a; a = 2;
System.out.println("a = " + a);
%><!-- scriptlet; contine cod de java -->
<%= a %>
<%= request.getParameter("age") %>
<%
//    response.getWriter().println("info");
%>
Time: <%=java.util.Calendar.getInstance().getTime()%>
ServletName: <%=config.getServletName()%>
AppContextPath: <%=application.getContextPath()%>

<%-- comment jsp --%>
<a href="hello-servlet">Hello Servlet</a>
<form method="post" action="hello-servlet">
    <input type="text" name="victima" value="Gigel"/>
    <input type="submit" value="Show me">
</form>

<h3>Form for answer</h3>
<form method="post" action="answer.jsp">
    <label>Question:</label>
    <input type="text" name="question" value=""/>
    <input type="submit" value="Answer me">
</form>

<h3>Goodbye servlet</h3>
<form method="post" action="GoodbyeServlet">
    <input type="text" name="name" value="Vasile"/>
    <input type="submit" value="Goodbye me">
</form>

</body>
</html>