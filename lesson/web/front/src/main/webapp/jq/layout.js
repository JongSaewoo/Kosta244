$(function () {
	//로그인여부확인할 서블릿 요청 응답형태{status:1}--이미 로그인된 경우
	//																	 header>nav>a 를 로그아웃
	// <a href="login.html">로그인</a>
  // <a href="signup.html">가입</a>
  // <a href="productlist.html">상품</a>
	//																	 그 외     --로그인 안된 상태
	//																	 header>nav>a 를 로그인, 가입
	// <a href="login.html">로그인</a>
  // <a href="signup.html">가입</a>
  // <a href="productlist.html">상품</a>
  // <a href="viewcart.html">장바구니</a>

	//--------메인페이지 로딩되자마자 데이터보내기 ---------
	let url = '/back/loginstatus';
	let method = 'get';

	$.ajax({
		url: url,
		method: method,
		success: function(jsonObj){	
			let $navObj = $('header>nav');
			let $navObjHtml = '';
			if(jsonObj.status == 1){	//로그인 된경우
				$navObjHtml += '<a href="vieworder.html">주문내역</a>&nbsp';
				$navObjHtml += '<a href="/back/logout">로그아웃</a>&nbsp';
			}else{	//로그인 안된경우
				$navObjHtml += '<a href="login.html">로그인</a>&nbsp';
				$navObjHtml += '<a href="signup.html">가입</a>&nbsp';
			}
			$navObjHtml += '<a href="productlist.html">상품</a>&nbsp';
			$navObjHtml += '<a href="viewcart.html">장바구니</a>&nbsp';
			$navObj.html($navObjHtml)
		},
		error: function(jqXHR){
			alert("오류 : " + jqXHR.status);
		}
	});

	//메뉴객체들 찾기
	let $menuObj = $('header>nav>a');
	//section의 첫번째 자식요소인 본문(article)객체 찾기
	let $articleObj = $('section>article:first');

	//----------- 메뉴클릭 START ------------
	//메뉴가 클릭되면 article영역의 innerHTML로 로드
	//$menuObj.click(function () {	//a객체가 클릭됬을때
	$('header>nav').on('click', 'a', function(){	//a객체가 생성되고 && 클릭됬을때
		let url = $(this).attr('href');
		let title = $(this).html();

		$articleObj.load(url, function (responseText,
			statusText,
			xhr) {
			if (statusText != 'success') {
				// alert(xhr.status + ":" + xhr.statusText);
				if (xhr.status == 404) {
					let msg = title + ' 자원을 찾을 수 없습니다';
					alert(msg);
				}
			}
			if(url == '/back/logout'){
				location.href="";
			}
		});
		return false;
	});

});