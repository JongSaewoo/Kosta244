$(function () {
	//------------데이터 요청-------------
	//alert(window.location.search); //search : 입력한 주소url의 쿼리스트링을 알려줌
	let queryString = location.search.substring(1); //0번 인덱스는 '?',
																									//1번인 'p'문자부터 끝까지 substring하기
	$.ajax({
		url: "/back/viewproduct",
		method: 'get',
		//data: 'prod_no=' + 'C0001',
		data: queryString, 
		success: function (jsonObj) {
			console.log(jsonObj);
			let prod_no = jsonObj.p.prodNo;
			let prod_name = jsonObj.p.prodName;
			let prod_price = jsonObj.p.prodPrice;
			let prod_mfd = jsonObj.p.prodMfd;
			let prod_info = jsonObj.p.prodInfo

			$('div.viewproduct>img').attr('src', '../images/' + prod_no + '.jpg')
				.attr('alt', prod_name);
			$('div.viewproduct ul>li>span.prod_no').html(prod_no);
			$('div.viewproduct ul>li>span.prod_name').html(prod_name);
			$('div.viewproduct ul>li>span.prod_price').html(prod_price);
			$('div.viewproduct ul>li>span.prod_mfd').html(prod_mfd);
			$('div.viewproduct ul>li>span.prod_info').html(prod_info);
		},
		error: function () {
			alert('오류:' + jqXHR.status);
		}
	});

	//------------장바구니 버튼 클릭 START-------------
	$('div.viewproduct ul>li>button').click(function(){
		let prod_no = $('div.viewproduct ul>li>span.prod_no').html();
		let quantity = $('div.viewproduct ul>li>input[name=quantity]').val();

		$.ajax({
			url: '/back/addcart',
			method: 'get',
			data: {prod_no: prod_no, quantity: quantity},
			success: function(jsonObj){
				$('div.viewproduct div.result').show();
			},
			error: function(jqXHR){
				alert('오류:' + jqXHR.status);
			}
		});
		return false;
	});

	//------------상품목록보기버튼 클릭 START-------------
	$('div.result>button.productlist').click(function(){
		$('nav>a[href="productlist.html"]').trigger('click');
	});

	//------------장바구니보기 클릭 START------------
	$('div.result>button.viewcart').click(function(){
		$('nav>a[href="viewcart.html"]').trigger('click');
	});
});