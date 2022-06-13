//1.문서의 끝까지 해석후 DOM트리가 완성되고 화면에 렌더링할 준비가 되면
//window객체의 load이벤트가 발생한다

//이클립스에서 js_event.html의 DOM트리작성이 완료될때까지 기다림
//트리작성이 완료되면 window객체의 load()이벤트발생을 감시시작 
//감시하다가 이벤트가 발생하면 function()이 자동호출
window.addEventListener("load", function(){
    //DOM트리의 type속성이 text인 input객체찾기
    var txtObj = document.querySelector("input[type=text]");

    //DOM트리의 button객체찾기
    var btObj = document.querySelector("button");
    //button객체의 click이벤트가 발생했을때 function()이 자동호출
    btObj.addEventListener("click", function(){
        alert('클릭되었습니다');
        txtObj.value = '클릭되었습니다';
    })

    //---------------------계산기 START---------------------
    //DOM트리에서 class속성값이 calculator인 객체찾기
    var divObj = document.querySelector("div.calculator");
    //DOM트리에서 class속성값이 calculator인 객체의 자식중 모든 button 순서대로 찾기
    var btArr = document.querySelectorAll("div.calculator>button");
    //콜백함수를 사용하지않아 마지막 i값만 출력됨
    // for(var i=0; i<btArr.length; i++){
    //   btArr[i].addEventListener('click', function(){
    //     console.log(i+1, '버튼클릭되었습니다');
    //   });
    // }

    //콜백함수를 이용하여 문제해결하기
    // for(var i=0; i<btArr.length; i++){
    //   (function(j){ //이름없는 함수 : (function(전달받을 인자값){})(전달할 인자값);
    //     btArr[j].addEventListener('click', function(){
    //       console.log(j, '버튼클릭되었습니다');
    //     });
    //   })(i);
    // }

    //forEach문을 사용하여 콜백함수를 조금 더 간결하게 쓰기
    var resultObj = document.querySelector("div.calculator>div.result");

    var resultNum = 0;  //결과값
    var operator; //연산자
    btArr.forEach(function(item, index){
      item.addEventListener('click', function(){
        // console.log(index, '버튼클릭되었습니다');
        // resultObj.innerHTML = index + '번 버튼이 클릭되었습니다';
        // resultObj.innerHTM = item.innerHTML + '번 버튼이 클릭되었습니다';
        var inner = this.innerHTML;
        switch(inner){
          case '+':
            operator = inner;
            break;
          case '=':
            resultObj.innerHTML = resultNum;
            operator = undefined;
            resultNum = 0;
            break;
          default:  //그 외 숫자 버튼들
          resultObj.innerHTML = inner;
            if(operator == '+'){
              resultNum += parseInt(inner);
            }else {
              resultNum = parseInt(inner);
            }
        }
      });
    });
    //---------------------계산기 END  ---------------------

    //---------------------CHECKBOX START-------------------
    var cbArr = 
    document.querySelectorAll("div.checkbox input[type=checkbox]");
  
    cbArr.forEach(function(item, index){
      // if(index == 0){ //첫 번째 checkbox객체
      
      // }
      console.log(item, item.checked);
    });
    
    cbArr[0].addEventListener('click', function(){
      for(var i=1; i<cbArr.length; i++){
        cbArr[i].checked = this.checked;
      }
    });
    //---------------------CHECKBOX END  -------------------

    //---------------------SELECT START  -------------------
    var selectSidoObj = document.querySelector("div.select>select.sido");
    var selectSigunguObj = document.querySelector("div.select>select.sigungu");

    selectSidoObj.addEventListener('click', function(){
      console.log(this.value, "클릭되었습니다");
    });
    selectSidoObj.addEventListener('change', function(){
      console.log(this.value, "변경되었습니다");
      switch(this.value){
        case '서울시':
          selectSigunguObj.innerHTML = '';
          var seoul = '<option>구를 선택하세요</option>';
          seoul += '<option>중구</option>';
          seoul += '<option>강북구</option>';
          seoul += '<option>강동구</option>';
          seoul += '<option>강남구</option>';
          seoul += '<option>강서구</option>';
          selectSigunguObj.innerHTML = seoul;
          selectSigunguObj.style.display = 'inline-block';
          break;
        case '경기도':
          // selectSigunguObj.innerHTML = ''; // 1. 1,2,3중 innerHTML을 이용하여 초기화한
                                              // 1번 방법이 가장 적합함
          // for(var i=0; i<selectSigunguObj.childNodes.length; i++){ // 2.
          //   console.log('before remove length', selectSigunguObj.childNodes.length);
          //   selectSigunguObj.removeChild(selectSigunguObj.childNodes[i]);
          //   console.log('after remove length', selectSigunguObj.childNodes.length);

          // }
          while(selectSigunguObj.hasChildNodes()){ // 3.
            selectSigunguObj.removeChild(selectSigunguObj.firstChild);
          }

          // var gyeonggi = '<option>시를 선택하세요</option>';
          // gyeonggi += '<option>용인시</option>';
          // gyeonggi += '<option>성남시</option>';
          // selectSigunguObj.innerHTML = gyeonggi;
          var gyeonggi = ['시를 선택하세요', '용인시', '성남시'];
          for(var i=0; i<gyeonggi.length; i++){
            var opt = document.createElement('option');
            var txt = document.createTextNode(gyeonggi[i]);
            opt.appendChild(txt);
            selectSigunguObj.appendChild(opt);
          }
          selectSigunguObj.style.display = 'inline-block';
          break;
        default:
          selectSigunguObj.innerHTML = '';
          selectSigunguObj.style.display = 'none';
          
      }
    });
    //---------------------SELECT END  ---------------------

    //---------------------KEYBOARD START  -----------------
    //DOM트리에서 div.keyboard의 input객체찾기
    var inputObj = document.querySelector("div.keyboard>input[type=text]");
    inputObj.addEventListener('click', function(){
      console.log('input객체가 클릭되었습니다');
    });
    inputObj.addEventListener('focus', function(){
      console.log('input객체 포커스받았습니다');
      this.style.color = 'blue';
    });

    //keydown->keypress->keyup
    inputObj.addEventListener('keyup', function(event){
      alert('입력된키값 : ' + event.key);
      if(event.key == 'Enter'){
        
      }
    });
    //---------------------KEYBOARD END  -------------------

    //---------------------SUBMIT START  -------------------
    //전송관련이벤트 : 버튼의 click이벤트 -> 폼의 submit이벤트 -> 폼의 submit이벤트 기본처리(전송)됨
    // var btSubmitObj = this.document.querySelector('div.submit>form>button');
    var formObj = this.document.querySelector('div.submit>form');
    // var textSubmitObj = this.document.querySelector('div.submit>form>input[name=t]');

    //DOM nav
    var btSubmitObj = formObj.lastElementChild;
    console.log("-------------");
    console.log(btSubmitObj);
    console.log("-------------");
    var textSubmitObj = formObj.firstElementChild;

    btSubmitObj.addEventListener('click', function(){
      alert("전송버튼 클릭이벤트가 발생했습니다");
    });
    formObj.addEventListener('submit', function(event){
      alert("폼의 서브밋이벤트가 발생했습니다");
      if(textSubmitObj.value == ''){
        alert('값을 입력해주세요');
        event.preventDefault(); //기본이벤트처리(핸들러) 금지 : 전송 금지
      }
    });
    //---------------------SUBMIT END  ---------------------

    //---------------------a START  ------------------------
    //이동관련 이벤트 : a객체의 클릭이벤트 -> 클릭이벤트 기본처리(이동)
    //이벤트전파 : 이벤트버블링 - 하위객체에서 발생한 이벤트가 상위객체로 전파
    var divAObj = this.document.querySelector("div.a");
    divAObj.addEventListener('click', function(){
      this.style.backgroundColor = 'yellow';
    });
    var aObj = document.querySelector("a");
    aObj.addEventListener('click', function(event){
      this.style.backgroundColor = 'green';
      event.preventDefault();
      event.stopPropagation();  //(자식입장에서)부모에게 이벤트전파 중지 : 이벤트버블링 중지
    });
    //---------------------a END  --------------------------

    //querySelectorAll이 getElementsByTagName보다 
    //다수의 태그를 찾는데에 있어 더 적합함.(배열형태)
    var inputNodeList = this.document.querySelectorAll("input");
    console.log("----------------");
    console.log(inputNodeList);
    console.log("----------------");

    var inputCollection = this.document.getElementsByTagName("input");
    console.log("----------------");
    console.log(inputCollection);
    console.log("----------------");

    //getElementsByTagName의 객체를 forEach하면 오류남.
    //반대로 querySelectorAll를 forEach하면 오류나지않음.
    inputNodeList.forEach(function(item, index){
      console.log('inputCollection', item); 
    });
});