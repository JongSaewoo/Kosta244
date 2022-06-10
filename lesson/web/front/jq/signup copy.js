alert("1");
$(function(){
  //가입 버튼클릭이벤트 발생 -> 폼서브밋이벤트발생 -> 기본처리(전송)
	//form객체 찾기
	let $form = $('div.signupform>form');
	$form.submit(function(){
		//비밀번호 일치확인
		let $pwd = $('#pwdbox');	//비밀번호 인풋
		let $pwd1 = $('#pwdcheckbox');	//비밀번호 확인 인풋
		alert(data);

		return false;
	});
});