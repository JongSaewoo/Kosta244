
$(function(){
  //아이디입력객체찾기
  let $inputId = $('input[name=id]');

  //가입버튼객체찾기
  let $btSubmit = $('button[type=submit]');

  //--아이디중복확인버튼 클릭 START--
  let $btIdupchk = $('button.iddupchk');
  $btIdupchk.click(function(){
    $.ajax({
      //url: 'http://localhost:8888/back/iddupchk',
      url: `${backPath}/iddupchk`,
      method : 'get',
      data : {id: $inputId.val()},
      success:function(jsonObj){
        if(jsonObj.status == 1){ //사용가능한 아이디인 경우
          $btSubmit.show();
        }else{
          alert(jsonObj.msg);
        }
      },
      error: function(jqXHR){
        alert('오류:' + jqXHR.status);
      }
    });
  })
  //--아이디중복확인버튼 클릭 END--
  
  //--아이디입력란에 포커스 START--
  // let $inputId = $('input[name=id]');
  $inputId.focus(function(){
    $btSubmit.hide();
  });
  //--아이디입력란에 포커스 END--
  
  //--폼 전송 START --
  //가입버튼클릭이벤트발생->폼서브밋이벤트발생->기본처리(전송)
  //폼객체찾기
  let $form = $('div.signup>form');
  $form.submit(function(){
    alert('submit start');
    //비밀번호 일치확인
    let $pwd = $('div.signup input[name=pwd]');
    let $pwd1 = $('#pwd1');
    if($pwd.val() != $pwd1.val()){
      alert('비밀번호가 일치하지 않습니다');
      $pwd.focus();
      return false; 
    }  
    //let url = '/back/signup';
    let url = `${backPath}/signup`;
    let data = $(this).serialize(); //querystring만들어줌
                //ex)id=a&pwd=1&name=b&addr=c&buildingno=1
    // alert(data);
    $.ajax({
      url: url,
      method: 'post',
      data: data,
      // success:function(responseText){
      //   let jsonObj = JSON.parse(responseText);
      success: function(jsonObj){
        alert(jsonObj.msg);
      },
      error : function(jqXHR){
        alert('에러코드:' + jqXHR.status);
      }
    });
    return false; 
    
  });
  //--폼 전송 END--
});