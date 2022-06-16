<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>include.jsp</title>
</head>
<body>
<%int i = 99; %>
<%-- <div style="border:1px solid">
	<%@include file="include_sub.jsp" %>
</div> --%>
<div style="border:1px solid">
	<jsp:include page="include_sub.jsp"></jsp:include>
</div> 
</body>
</html>