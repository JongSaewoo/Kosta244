<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>first.jsp</title>
</head>
<body>
<h1>FIRST JSP</h1>.jsp용 java파일이 자동만들어짐.
<%int i=99; %>
<%out.print(i); %>
<hr>
<%=i %>
<hr>
<%! int i; %>
<%=i %> <%--i의 값은 jspService()안의 지역변수 i=99와 공유되기 때문에 값은 99가됨. --%>
<hr>
<%=this.i %>
</body>
</html>