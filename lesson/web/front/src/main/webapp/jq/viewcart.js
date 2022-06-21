$(function(){
	$.ajax({
		url: '/back/viewcart',
		success: function (jsonObj) {
			let $itemObj = $('div.viewcart>div.item');
			
			$(jsonObj).each(function (index, element) {
				$copyObj = $itemObj.clone();
	
				let p = element.p;
				let product = '<ul>';
				product += '<li class="prod_no">상품번호:<span>' + p.prodNo + '</li></span>';
				product += '<li class="prod_name">상품명:<span>' + p.prodName + '</li></span>'; 
				product += '<li class="prod_price">가격:<span>' + p.prodPrice + '</li></span>'; 
				product += '</ul>'  
				$copyObj.find('div.product').html(product);
	
				let quantity = element.quantity;
				$copyObj.find('div.quantity').html('수량:<span>' + quantity + '</sapn>');
	
				$('div.viewcart').append($copyObj);
			});
		},
		error: function (jqXHR) {
			alert('오류: ' + jqXHR.status);
		}
	});
	//--주문하기 버튼 클릭 START--------------
	$('div.viewcart>div.addorder>button').click(function(){
		$.ajax({
			url: '/back/addorder',
			success: function(jsonObj){
				if(jsonObj.status == 1){	//주문성공경우
					alert(jsonObj.msg);
					// location.href="";	//
					$('nav>a[href="productlist.html"]').trigger('click');	//상품목록
				}else if(jsonObj.status == 0){	//로그인 안된 경우
					alert(jsonObj.msg);
					$('nav>a[href="login.html"]').trigger('click');	//로그인
				}else if(jsonObj.status == -1){	//주문실패
					alert(jsonObj.msg);
				}
			},
			error: function(jqXHR){
				alert('오류: ' + jqXHR.status);
			}
		});
	});
});
