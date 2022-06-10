<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%String id = request.getParameter("id");
String pwd = request.getParameter("pwd");
if(id.equals("id1") && pwd.equals("p1")){
%>{"status":1}<%
}else{%>
{"status":2}<%}%>

