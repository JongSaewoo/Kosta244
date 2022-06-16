<%@page import="java.io.FileNotFoundException"%>
<%@page import="java.io.FileInputStream"%>
<%@page buffer="1024kb" %>
<%@page errorPage="err.jsp" %>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>pagedirective.jsp</title>
</head>
<body>
<%for(int i=1; i<=10000; i++) {
%><span><%=i %></span>
<%}
%>
<%
FileInputStream fis = null;
fis = new FileInputStream("a.txt");	
%>

</body>
</html>