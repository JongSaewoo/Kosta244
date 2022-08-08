$(function () {
  //아이디저장 CHECKBOX localstorage처리 완성하세요(js/login.js내용참고)
  //---아이디저장 체크박스객체찾기
  let $cb = $('input[type=checkbox]');

  //--로그인버튼객체 찾기
  let $btLogin = $('div.row>button');

  //--아이디 입력객체찾기
  let $inputId = $('input[name=id]');

  //localStorage에 idValue이름의 item이 있다면
  //아이디 입력객체의 value로 설정하기
  let idValue = localStorage.getItem('idValue');
  if (idValue != null && idValue != '') {
    $inputId.val(idValue);
  }

  //--로그인버튼 클릭 START--
  //아이디저장 체크박스가 체크되었다면 
  //사용자가 입력해준 아이디값을 localstorage에 저장(이름: idValue)
  //아이디저장 체크박스가 체크안되었다면
  //localstorage의 idValue이름의 item을 삭제

  $btLogin.click(function () {
    if ($cb.prop('checked')) {
      localStorage.setItem("idValue", $inputId.val());
    } else {
      localStorage.removeItem('idValue');
    }
  });
  //--로그인버튼 클릭 END--

  //비밀번호입력 객체찾기
  let $inputPwd = $("input[name=pwd]");

  //--form 전송 START--
  let $form = $('div.login>form');
  $form.submit(function () {
    //let url = 'http://localhost:8888/back/login';
    let url = `${backPath}/login`;
    let inputIdValue, inputPwdValue;
    inputIdValue = $inputId.val();//사용자가 입력해준 아이디값
    inputPwdValue = $inputPwd.val();// "             비밀번호값

    let data = 'id=' + inputIdValue +  '&pwd='+ inputPwdValue;
    $.ajax({
      url: url,
      method: 'post',
      data: data,
      // success: function(responseText){
      //   let jsonObj = JSON.parse(responseText);
      success: function(jsonObj){
        if(jsonObj.status == 1){ //로그인 성공
          //location.href = ''; //현재사용중인 주소로 재요청
          location.href = 'css_js_layout.html';
        }else{ //로그인 실패
          alert('로그인 실패');
        }
        //$('section article:first').html(responseText);
      },
      error: function(jqXHR, textStatus, errorThrown){
        alert(jqXHR.status + ":" + jqXHR.statusText);
      }
    });

    return false; //event.preventDefault() + event.stopPropagation()
  });
  //--form 전송 END--
});