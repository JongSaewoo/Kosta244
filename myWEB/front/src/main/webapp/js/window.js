function displayTime(element){
	var today = new Date();
	var month = today.getMonth() + 1;
	var date = today.getDate();
	var now = '<h1>';
	now += today.getFullYear();
	now += '-';
	now += month<10? '0'+month : month;
	now += '-';
	now += date<10? '0'+date : date;
	now += ' ';
	now += today.getHours() + ':' + today.getMinutes() + ':' + today.getSeconds();
	now += '<h1/>';
	element.innerHTML = now;
}

window.addEventListener('load', function(){
	var windowID;
	var btClose = this.document.querySelector('button.close');

	//------------- 새창띄우기 버튼 클릭 START -------------
  var btOpen = 
  	this.document.querySelector('button.open');
	btOpen.addEventListener('click', function(){
		var url = 'js_popup.html';
		var target = 'first';	// 창을 계속 재오픈할때 매번 새로운 창을 띄우는것을 금지시킴
		var features = 'width=300px, height=500px';
		windowID = window.open(url, target, features);
		this.style.display = 'none';  //새창띄우기버튼 사라짐
		btClose.style.display = 'inline';  //새창닫기버튼 보여짐
	});

	//------------- 새창닫기 버튼 클릭 START -------------

	btClose.addEventListener('click', function(){
		windowID.close();	//windowID는 js_popup.html에 있는 window
		this.style.display = 'none';	//새창닫기버튼 사라짐.
		btOpen.style.display = 'inline';  //새창닫기버튼 보여짐
	})

	//------------- 5초 후 버튼 클릭 START ---------------
	var btTimeOut = document.querySelector("button.timeout");
	btTimeOut.addEventListener('click', function(){
		window.setTimeout(function(){	//콜백함수 이용
			alert("5초가 지났습니다");
		}, 5*1000);
	})

	//------------- 현재시간값 버튼 클릭 START ------------
	var divObj = document.querySelector('div');
	
	var today = new Date();
	var month = today.getMonth() + 1;
	var date = today.getDate();
	var now = '<h1>';
	now += today.getFullYear();
	now += '-';
	now += month<10? '0'+month : month;
	now += '-';
	now += date<10? '0'+date : date;
	now += ' ';
	now += today.getHours() + ':' + today.getMinutes() + ':' + today.getSeconds();
	now += '<h1/>';
	divObj.innerHTML = now;

	displayTime(divObj);

	//------------- 1초 간격 버튼 클릭 START --------------
	var intervalID;
	var btInterval = document.querySelector('button.interval');
	btInterval.addEventListener('click', function(){
		// window.setInterval(function(){
		// 	displayTime(divObj);
		// }, 1000);
		intervalID = window.setInterval(displayTime, 1000, divObj);
	});
	
	//------------- 1초 간격 해제 버튼 클릭 START --------------
	var btClear = document.querySelector('button.clear');
	btClear.addEventListener('click', function(){
		window.clearInterval(intervalID);
	});
});