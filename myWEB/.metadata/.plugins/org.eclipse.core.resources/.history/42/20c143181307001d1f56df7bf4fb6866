$(function(){
  alert(window.location.search);
  let queryString = location.search.substring(1);
  $.ajax({
    //url: "/back/viewproduct",
    url: `${backPath}/viewproduct`,
    method: 'get',
    // data : 'prod_no=' +'C0001',
    data : queryString,
    success: function(jsonObj){
      if(jsonObj.status == 1){
        let prod_no = jsonObj.p.prodNo;
        let prod_name = jsonObj.p.prodName;
        let prod_price = jsonObj.p.prodPrice;
        let prod_mfd = jsonObj.p.prodMfd;
        let prod_info = jsonObj.p.prodInfo;
        $('div.viewproduct>img').attr('src', '../images/' + prod_no + '.jpg').attr('alt', prod_name);

        $('div.viewproduct ul>li>span.prod_no').html(prod_no);
        $('div.viewproduct ul>li>span.prod_name').html(prod_name);
        $('div.viewproduct ul>li>span.prod_price').html(prod_price);
        $('div.viewproduct ul>li>span.prod_mfd').html(prod_mfd);
        $('div.viewproduct ul>li>span.prod_info').html(prod_info);
      }else{
        alert(jsonObj.msg);
      }
    },
    error: function(jqXHR){
      alert('오류:'+ jqXHR.status);
    }
  });
  //--장바구니버튼 클릭 START--
  $('div.viewproduct ul>li>button').click(function(){
    let prod_no = $('div.viewproduct ul>li>span.prod_no').html();
    let quantity= $('div.viewproduct ul>li>input[name=quantity]').val();
    alert("장바구니버튼 클릭 prod_no:" + prod_no +"quantity:" + quantity);
    $.ajax({
      //url: '/back/addcart',
      url:`${backPath}/addcart`,
      method: 'get',
      data: {prod_no:prod_no, quantity: quantity},
      success:function(){
	
        $('div.viewproduct div.result').show();  
      },
      error: function(jqXHR){
        alert('오류:'+ jqXHR.status);
      }
    });
    return false;
  });
  //--장바구니버튼 클릭 END--

  //--상품목록보기버튼 클릭 START--
  $('div.result>button.productlist').click(function(){
    $('nav>a[href="productlist.html"]').trigger('click');
  });
  //--상품목록보기버튼 클릭 END--

  //--장바구니보기버튼 클릭 START--
  $('div.result>button.viewcart').click(function(){
    $('nav>a[href="viewcart.html"]').trigger('click');
  });
  //--장바구니보기버튼 클릭 END--



});