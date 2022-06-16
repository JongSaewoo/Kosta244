<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>score.jsp</title>
</head>
<body>
<%!int totalScore = 0;%>
<%!int count = 0;%>
<%!float AvgScore = 0;%>
<%--요청전달데이터 얻기 --%>
<%
String score = request.getParameter("score");
totalScore += Integer.parseInt(score);
count++;
%>
<div name="scoreDiv">
<%=score %>점을 선택하셨습니다<br>
--------------------------------<br>
총점은 <%=totalScore %>점입니다<br>
참여인원은 <%=count %>명입니다<br>
평점은 <%=(float)totalScore / count %>점입니다<br>
</div>
<a href="http://localhost:8888/front/html/score.html">별점주기</a>
</body>
</html>