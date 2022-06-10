//아이디 입력객체찾기
let $inputId = $('#idbox');

//비밀번호입력 객체찾기
let $inputPwd = $("#pwdbox");

let $form = $('div.loginform');

//---------- form 요청 응답 START -----------
$(function () {
	$form.submit(function () {
		let url = 'http://localhost:8888/back/jsp/login.jsp';
		let inputIdValue, inputPwdValue;
		inputIdValue = $inputId.val();	//사용자가 입력한 아이디값
		inputPwdValue = $inputPwd.val();	//사용자가 입력한 비밀번호값

		let data = 'id=' + inputIdValue + '&pwd=' + inputPwdValue;

		//GET방식 jq : 조회 및 보안에 문제가 안되는 영역은 GET이 어울림
		// GET에서 쓰이는 함수 : .load()함수
		// $('section>article:first')
		// 	.load(url, data,
		// 		function(responseText, statusText, xhr) {
		// 			if (statusText != 'success') {	//응답오류인 경우(페이지자원을 찾지못했을때)
		// 				alert(xhr.status + ":" + xhr.statusText);
		// 			}else{	//응답성공인 경우(페이지자원을 찾았을때)
		// 				let jsonObj = JSON.parse(responseText)	//{status:1} 이라는 문자열을 json객체로 변환
		// 				if(jsonObj.status == 1){	//status라는 키를 가져오면 로그인을 성공했을때 status값이 1

		// 				}else if(jsonObj.status == 2){	//status키 : 값이 0이면 로그인을 실패했을때
		// 					alert("로그인 실패");
		// 				}
		// 			}
		// 	});
		
		//POST방식의 ajax : 수정,삭제 등 보안에 민감한 영역은 POST가 어울림 
		$.ajax({
			url: url,
			method: 'post',
			data: data,
			success: function(responseText){
				let jsonObj = JSON.parse(responseText);
				if(jsonObj.status == 1){
					location.href = '';	//주소 url을 현재 사용하고있는 주소 url로 변경
				}else{
					alert("로그인 실패");
				}
				// $('section>article:first').html(responseText);
			},
			error: function(jqXHR, textStatus, erroThrown){
				alert(jqXHR.status + ":" + jqXHR.statusText);
			}
		});

		return false;
	});
});