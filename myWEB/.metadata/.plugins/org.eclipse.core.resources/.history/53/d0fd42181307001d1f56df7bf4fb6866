$(function(){
  $.ajax({
    //url:"/back/vieworder",
    url:`${backPath}/vieworder`,
    success:function(jsonObj){
	  if(jsonObj.status==0){//로그인 안한 경우
		location.href="login.html";
	  }else if(jsonObj.status == -1){ //주문조회 실패또는 주문내역이 없는 경우
		alert(jsonObj.msg);
		location.href="css_js_layout.html";
	  }else{
	      let $infosObj = $('div.vieworder>div.infos');
	      let $infoObj = $('div.vieworder>div.infos>div.info');
	
	      $(jsonObj).each(function(index, info){
	        console.log(index, info);
	        let $copyObj = $infoObj.clone();
	        let order_no = info.orderNo;
	        $copyObj.find('div.order_no').html(order_no);
	        
	        let order_dt = info.orderDt;
	        $copyObj.find('div.order_dt').html(order_dt);
	
	        let lines = info.lines;
	        
	     	let $lineObj = $copyObj.find('div.lines>div.line').first();
	        $(lines).each(function(index, line){
	          let $copyLineObj = $lineObj.clone();
	          let p = line.orderP;
	          let order_quantity = line.orderQuantity;
	          $copyLineObj.find("div.order_product>div.prod_no").html(p.prodNo);
	          $copyLineObj.find("div.order_product>div.prod_name").html(p.prodName);
	          $copyLineObj.find("div.order_product>div.prod_price").html(p.prodPrice);
	          $copyLineObj.find("div.order_quantity").html(order_quantity);
	          $copyObj.find("div.lines").append($copyLineObj);
	        });
	        $infosObj.append($copyObj);  
	        $lineObj.remove();
	      });
	      $infoObj.remove();
	    }
    },
    error: function(jqXHR){
      alert('오류:'+ jqXHR.status);
    }
  });
});