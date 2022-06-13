<%@page import="java.util.Calendar"%><%@ page contentType="text/event-stream; charset=UTF-8"%><%!
long startTime=0;
%><%
response.setHeader("Cache-Control","no-store"); //HTTP1.0ver
response.setHeader("Pragma","no-cache"); 
response.setDateHeader("Expires",0); 
if (request.getProtocol().equals("HTTP/1.1")) //HTTP1.1ver
        response.setHeader("Cache-Control", "no-cache");
long term = (System.currentTimeMillis()-startTime)/1000;
String msg = "배송시작했습니다";
if(startTime == 0){
	startTime = System.currentTimeMillis();
}else if(term > 10){
	msg = "배송완료되었습니다";
	startTime=0;
}else{
	msg = "배송중입니다";
}%>retry: 5000
data: <%=msg%>

