$(function () {
	$.ajax({
		url: 'http://localhost:8888/back/productlist',
		success: function (jsonObj) {
			//jsonObj은 배열형태, 반복처리하면서
				//div.td객체를 DOM트리에서 찾아 복사 붙여넣기
				//붙여넣기한 div.td객체의 하위객체중 img객체의 href속성값을 상품번호.jpg,
				//											 alt속성값을 상품명
      let $tdObj = $('div.td');

			$(jsonObj).each(function(index, item){
				console.log(item.prod_no + ":" + item.prod_name + ":" + item.prod_price);
        let $copyObj = $tdObj.clone();  //div.td내용 복사
        let $imgObj = $copyObj.find("img"); //div.td내의 img태그 찾기
        $imgObj.attr("src", "../images/" + item.prod_no + ".jpg");
        $imgObj.attr("alt", item.prod_name);
        $copyObj.find("li.prod_name").html(item.prod_name); //복사한 내용 붙여넣기
        
        $('div.table').append($copyObj);
			});

      //원본 td를 복사하는 용도로 잘 썼지만 실제 화면에서 보이므로 안보이도록 할 수 있음
      //1. .js파일에서 고치기(hide(), remove(), empty())
      //2. css파일에서 hidden으로 할 수 있음
      $tdObj.hide();
		},
		error: function (jqXHR) {
			alert("오류: " + jqXHR.status);	//오류코드 404, 500, 200-JSON문자열문제
		}
	});
	//div.table객체 찾기
	let $tableObj = $('div.table');
	//div.td객체찾기
	let $tdObj = $('div.td');
	//--div.td객체 클릭 START--
	$tableObj.on('click', 'div.td', function(){
		let src = $(this).find('img').attr('src');	// ../images/C0001.jpg
		console.log('src', src);
		let arr = src.split('/');
		console.log('arr', arr);
		let prod_no = arr[arr.length-1].split('.')[0];	//C0001.jpg
		console.log('prod_no', prod_no);
		location.href = "http://localhost:8888/back/viewproduct?prod_no=" + prod_no;
	});
	//--div.td객체 클릭 END--
});