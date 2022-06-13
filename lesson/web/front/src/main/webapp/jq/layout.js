$(function () {
	//메뉴객체들 찾기
	let $menuObj = $('header>nav>a');
	//section의 첫번째 자식요소인 본문(article)객체 찾기
	let $articleObj = $('section>article:first');

	//----------- 메뉴클릭 START ------------
	//메뉴가 클릭되면 article영역의 innerHTML로 로드
	$menuObj.click(function () {
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
		});
		return false;
	});

});