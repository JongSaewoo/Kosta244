$(function(){
	let $resultObj = $('div.result');

	let $giveStarBtn = $('form>button');

	$giveStarBtn.click(function(){
		$.ajax({
			url: 'http://localhost:8888/back/jsp/score.jsp',
			method: 'get',
			data : 'score='+$('input[name=score]:checked').val(),
			success: function(jsonObj){
				
			},
			error: function(jqXHR, textStatus, erroThrown){
				alert(jqXHR.status + ":" + jqXHR.statusText);
			}
		});
	});
});

