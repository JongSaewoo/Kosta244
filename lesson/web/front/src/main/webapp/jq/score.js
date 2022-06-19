$(function () {
	let $resultObj = $('div.result');

	let $giveStarBtn = $('form>button');

	$giveStarBtn.click(function () {
		$.ajax({
			url: 'http://localhost:8888/back/jsp/score.jsp',
			method: 'get',
			data: 'score=' + $('input[name=score]:checked').val(),
			success: function (jsonObj) {
				let url = $(this).attr('href');
				let title = $(this).html();

				$resultObj.load(url, function (responseText,
					statusText,
					xhr) {
					if (statusText != 'success') {
						// alert(xhr.status + ":" + xhr.statusText);
						if (xhr.status == 404) {
							let msg = title + ' 자원을 찾을 수 없습니다';
							alert(msg);
						}
					}
					if (url == '/back/logout') {
						location.href = "";
					}
				});
			},
			error: function (jqXHR, textStatus, erroThrown) {
				alert(jqXHR.status + ":" + jqXHR.statusText);
			}
		});
	});
});

