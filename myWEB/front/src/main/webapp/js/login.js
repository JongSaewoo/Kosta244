window.addEventListener('load', function(){
    //---------- 아이디 저장 체크박스객체찾기 -----------
    let cb = document.querySelector('input[type=checkbox]');

    //---------- 로그인버튼객체 찾기 --------------------
    let btLogin = document.querySelector('div.tr3>div.td1>button');

    //---------- 아이디입력객체 찾기 --------------------
    let inputId = document.querySelector('input[name=id]');

    //localStorage에 idValue이름의 item이 있다면
    //아이디 입력객체에 value로 설정하기
    let idValue = this.localStorage.getItem('idValue');
    if(idValue != null && idValue != ''){
        inputId.value = idValue;
    }


    //---------- 로그인버튼 클릭 START ------------------
    //아이디저장 체크박스가 체크되었다면
    //사용자가 입력해준 아이디값을 localStorage에 저장(이름 : idValue)
    //아이디저장 체크박스가 체크안되었다면
    //localStorage의 idValuedlfmadml item을 삭제
    btLogin.addEventListener('click', function(){
        if(cb.checked){
            localStorage.setItem("idValue", inputId.value);
        }else {
            localStorage.removeItem('idValue');
        }
    });
})