$(function () {
	$.ajax({
		url: "/back/vieworder",
		success: function (jsonObj) {
			let $infosObj = $('div.vieworder>div.infos');
			let $infoObj = $('div.vieworder>div.infos>div.info');

			$(jsonObj).each(function (index, info) {
				let $copyObj = $infoObj.clone();
				let order_no = jsonObj.orderNo;
				$copyObj.find('div.order_no').html(order_no);

				let order_dt = jsonObj.orderDt;
				$copyObj.find('div.order_dt').html(order_no);

				let lines = info.lines;

				let $lineObj = $copyObj.find('div.lines>div.line').first();
				$(lines).each(function (index, line) {
					let $copyLineObj = $lineObj.clone();
					let p = line.orderP;
					let order_quantity = line.orderQuantity;
					$copyLineObj.find("order_product>div.prod_no").html(p.prodNo);
					$copyLineObj.find("order_product>div.prod_name").html(p.prodName);
					$copyLineObj.find("order_product>div.prod_price").html(p.prodPrice);
					$copyLineObj.find("order_quantity").html(orderQuantity);
					$copyObj.find("div.lines").append($copyLineObj);
				});
				$infosObj.append($copyObj);
			});
			$infoObj.remove();
			$lineObj.remove();
		},
		error: function (jqXHR) {

		}
	});
});