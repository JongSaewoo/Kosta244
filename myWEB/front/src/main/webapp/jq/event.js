// $(document).ready(function(){});
$(function(){
  let $txtObj = $('input[type=text]');
  let $btObj = $('button').first();	//jq객체를 반환
	$btObj.click(function(){
		alert('클릭되었습니다');
		$txtObj.val('클릭되었습니다');
	});

	//---------------------계산기 START---------------------
  //DOM트리에서 class속성값이 calculator인 객체의 자식중 모든 button객체 찾기

  //querySelectorAll()의 반환형은 NodeList자료형
	//$()의 반환형은 jquery객체형
	//$()의 결과가 여러객체인 경우 각 인덱스의 요소는 js객체이다
	//js객체를 jq객체로 변환하려면 $(js객체)처리가 필요
	//반복문용 jq메소드는 forEach()가 아닌 each()
	var btArr = document.querySelectorAll('div.calculator>button');
	console.log(btArr);
	console.log("--------------")

	let $btArr = $('div.calculator>button');	//jq메커니즘으로 결과값을 얻어왔지만
																						//여러객체이므로 js객체이다
	console.log(typeof($btArr));
	console.log("--------------")
	// $btArr[0].hidden(); // hidden()은 js객체로 오류나지않음
	// $($btArr[0]).hide();	//js객체를 jq객체로 변환하려면 $(js객체)처리가 필요(hide()는 jq객체)

	console.log(btArr[0] === $btArr[0]);	//js메커니즘으로 얻어온 값과 jq메커니즘으로 얻어온값이
	// 둘 다 js객체로 일치함.

	// $btArr.forEach(fucntion(item, index){
	// 	if(index % 2 == 0){
	// 		item.hide();
	// 	}
	// });

	let $resultObj = $('div.calculator>div.result');
	let resultNum = 0;	//계산된 결과값
	let operator;	//연산자
	// $btArr.click(function(){
	// 	switch(inner){
	// 		case '+':
	// 			operator = inner;
	// 			break;
	// 		case '=':
	// 			$resultObj.html(resultNum);
	// 			operator = undefined;
	// 			resultNum = 0;
	// 			break;
	// 		default:  //그 외 숫자 버튼들
	// 			$resultObj.html(inner);
	// 			if(operator == '+'){
	// 				resultNum += parseInt(inner);
	// 			}else {
	// 				resultNum = parseInt(inner);
	// 			}
	// 	}
	// });
	$btArr.each(function(index, item){
		//반복문용 jq메소드는 forEach()가 아닌 each()
		//let $btArr = $('div.calculator>button'); 결과는
		//여러객체(js)를 찾아왔지만 배열형태가아님
		//그래서 forEach()는 쓰지못함
		$(item).click(function(){
			let inner = $(this).html();
			switch(inner){
				case '+':
					operator = inner;
					break;
				case '=':
					$resultObj.html(resultNum);
					// =resultObj.innerHTML = resultNum;
					operator = undefined;
					resultNum = 0;
					break;
				default:  //그 외 숫자 버튼들
					$resultObj.html(inner);
					if(operator == '+'){
						resultNum += parseInt(inner);
					}else {
						resultNum = parseInt(inner);
					}
			}
		});
	});

	//---------------------CEHCKBOX START---------------------
	//HINT:
	// let $cb = $('input[type=checkbox]').first();
	// alert($cb.prop('checked'));	//prop()을 써서 checked프로퍼티값 얻기
	let $cbArr = $('div.checkbox input[type=checkbox]');	//모든 체크박스
	let $cbAll = $('input[type=checkbox]').first();	//ALL 체크박스 1개
	$cbAll.click(function(){
		if($cbAll.prop("checked")) {
			$cbArr.prop("checked",true);
		} else {
			$cbArr.prop("checked",false);
		}
	});

	//쌤이 주신 답
	//let $cbArr = $('div.checkbox input[type=checkbox]');	//모든 체크박스
	//let $cbAll = $('input[type=checkbox]').first();	//ALL 체크박스 1개
	// let $cbOther = $cbArr.not($cbAll);	// ALL체크박스 외 모든 체크박스
	// $cbAll.click(function(){
	// 	let status = $(this).prop('checked');
	// 	$cbOther.prop('checked', status);
	// })

	//---------------------SELECT START---------------------
	var $selectSidoObj = $('div.select>select.sido');
	var $selectSigunguObj = $('div.select>select.sigungu');

	$selectSidoObj.click(function(){
		console.log($(this).val(), "클릭되었습니다");
	});

	$selectSidoObj.change(function(){
		console.log($(this).val(), "변경되었습니다");
		switch($(this).val()){
			case '서울시':
				// selectSigunguObj.innerHTML = '';
				$selectSigunguObj.empty();
				// var seoul = '<option>구를 선택하세요</option>';
				var seoul = '<option>구를 선택하세요</option>';
				seoul += '<option>중구</option>';
				seoul += '<option>강북구</option>';
				seoul += '<option>강동구</option>';
				seoul += '<option>강남구</option>';
				seoul += '<option>강서구</option>';
				$selectSigunguObj.html(seoul);
				// selectSigunguObj.style.display = 'inline-block';
				$selectSigunguObj.show();
				break;
			case '경기도':
				// while(selectSigunguObj.hasChildNodes()){ // 3.
				// 	selectSigunguObj.removeChild(selectSigunguObj.firstChild);
				// }
				$selectSigunguObj.empty();

				var gyeonggi = ['시를 선택하세요', '용인시', '성남시'];
				for(var i=0; i<gyeonggi.length; i++){
					var $opt = $('<option>');
					var txt = gyeonggi[i];
					$opt.append(txt);
					$selectSigunguObj.append($opt);
				}
				$selectSigunguObj.show();
				break;
			default:
				$selectSigunguObj.empty();
				$selectSigunguObj.hide();
				
		}
	});

	//---------------------keyboard START---------------------
	//TODO

	//---------------------submit START---------------------
	let $formObj = $('div.submit>form');

	//DOM nav
	let $btSubmitObj = $formObj.find('button');	//getElementChild -> find()
	let	$textSubmitObj = $formObj.find('input[type=text]');

	$btSubmitObj.click(function(){
		alert('전송버튼 클릭이벤트가 발생했습니다');
	});
	$formObj.submit(function(event){
		alert('폼의 서브밋이벤트가 발생했습니다');
		if($textSubmitObj.val() == ''){
			alert('값을 입력하세요');

			return false;	//기본이벤트처리 금지 + 이벤트전파 중지
		}
	});


	//---------------------a START  ------------------------
	var $divAObj = $("div.a");
    $divAObj.click(function(){
			$(this).css('background-color', 'yellow');
		});
    var $aObj = $("a");
    $aObj.click(function(){
			$(this).css('background-color', 'green');

			return false;
		});
       
});