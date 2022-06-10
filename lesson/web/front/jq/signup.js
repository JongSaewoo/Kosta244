$(function(){
  //가입 버튼클릭이벤트 발생 -> 폼서브밋이벤트발생 -> 기본처리(전송)
	//form객체 찾기
	let $form = $('div.signupform>form');
	$form.submit(function(){
		//비밀번호 일치확인
		let $pwd = $('#pwdbox');	//비밀번호 인풋
		let $pwd1 = $('#pwdcheckbox');	//비밀번호 확인 인풋
		
		if($pwd.val() != $pwd1.val()){
			alert('비밀번호가 일치하지 않습니다');
			$pwd.focus();	//포커스 강제이동
			return false;
		}

		let url = 'http://localhost:8888/back/jsp/signup.jsp';
		let idValue = $('input#idbox').val();
		let nameValue = $('input#namebox').val();
		let addrValue = $('input#addressbox').val();
		let buildingnoValue = $('input#buildingno').val();
		let data = $(this).serialize();
		alert(data);
		$.ajax({
			url: url,
			method: 'post',
			data: data,
			success: function(responseText){
				let jasonObj = JSON.parse(responseText);
				alert(jasonObj.msg);
			},
			error: function(jqXHR){
				alert('에러코드:' + jqXHR.status);
			}

		});

		return false;
	});
});