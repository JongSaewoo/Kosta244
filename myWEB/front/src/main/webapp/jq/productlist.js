$(function(){
    $.ajax({
        url:`${backPath}/productlist`,
        success: function(jsonObj){
			//console.log(jsonObj);
            //jsonObj은 배열형태, 반복처리하면서
               //div.td객체를 DOM트리에서 찾아 복사 붙여넣기
               //붙여넣기한 div.td객체의 하위객체중 img객체의 src 상품번호.jpg alt속성값을 상품명으로 설정
            let $tdObj =$('div.td');
            
            $(jsonObj).each(function(index, item){
              console.log(item.prodNo +":" + item.prodName + ":"+ item.prodPrice);
              let $copyObj = $tdObj.clone(); //객체복제
              let $imgObj = $copyObj.find("img");
              $imgObj.attr("src", "../images/" + item.prodNo + ".jpg");
              $imgObj.attr("alt", item.prodName);
              $copyObj.find("li.prod_name").html(item.prodName);
              $('div.table').append($copyObj);//붙여넣기
            });
            $tdObj.hide();
        },
        error: function(jqXHR){
            alert("오류:" + jqXHR.status);//404, 500, 200-JSON문자열문제
        }
    });
    //div.table객체 찾기
    let $tableObj = $('div.table');
    //div.td객체들 찾기
    let $tdObj = $('div.td');
    //--div.td객체 클릭 START--
    //$tdObj.click(function(){
    $tableObj.on('click', 'div.td', function(){
        let src = $(this).find('img').attr('src'); // ../images/C0001.jpg
        console.log('src', src);
        let arr = src.split('/');
        console.log('arr', arr);
        let prod_no = arr[arr.length-1].split('.')[0]; //C0001.jpg
        console.log('prod_no', prod_no);
        //location.href = "http://localhost:8888/back/viewproduct?prod_no=" + prod_no;
        location.href = "/front/html/viewproduct.html?prod_no=" + prod_no;
    });
    //--div.td객체 클릭 END--
    
});