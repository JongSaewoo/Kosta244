<%@page import="com.my.dto.Product"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>viewproduct.jsp</title>
<link rel="stylesheet" href="./css/viewproduct.css">
</head>
<body>
	<%
	Product p = (Product) request.getAttribute("p");
	%>
	<jsp:include page="../html/header.html"></jsp:include>

	<section>
		<article>
			<div class="viewproduct">
				<img src="../front/images/<%=p.getProdNo()%>.jpg"
					alt="<%=p.getProdName()%>">
				<div class="detail">
					<ul>
						<li>상품번호: <%=p.getProdNo()%></li>
						<li>상품명:<%=p.getProdName()%></li>
						<li>상품가격:<%=p.getProdPrice()%></li>
						<li>상품제조일자:<%=p.getProdMfd()%></li>
						<li>상품상세:<%=p.getProdInfo()%></li>
						<li>수량 : <input type="number" max="9" min="1" value="1">
						</li>
						<li><button>장바구니 넣기</button>
					</ul>
				</div>
			</div>
		</article>

	</section>
	<jsp:include page="../html/footer.html"></jsp:include>

</body>
</html>