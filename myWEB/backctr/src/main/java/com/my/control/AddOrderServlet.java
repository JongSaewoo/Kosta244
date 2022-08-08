package com.my.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.my.dto.OrderInfo;
import com.my.dto.OrderLine;
import com.my.dto.Product;
import com.my.exception.AddException;
import com.my.repository.OrderOracleRepository;
import com.my.repository.OrderRepository;

public class AddOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		ObjectMapper mapper = new ObjectMapper();
		String result = "";
		
		HttpSession session = request.getSession();
		Map<Product, Integer> cart = (Map)session.getAttribute("cart");
		//--------sample data----------
//		if(cart == null) {
//			cart = new HashMap<>();
//		}
//		cart.put(new Product("C0001", "아메리카노", 1000), 1);
//		cart.put(new Product("C0002", "아이스아메리카노", 1000), 2);
//		cart.put(new Product("G0001", "텀블러", 1000), 3);
//		session.setAttribute("loginInfo", "id9");
		
		if(cart == null || cart.size() == 0) {	
			//장바구니가 비어있는 경우(오래 사용하지않아 30분이후 세션이 초기화되어 장바구니가 빈 경우)
			Map<String, Object> map = new HashMap<>();
			map.put("status", -1);
			map.put("msg", "주문실패: 장바구니가 비었습니다");
			result = mapper.writeValueAsString(map);
		}else {
			//로그인된 사용자인가 확인
			String loginedId = (String)session.getAttribute("loginInfo");
			if(loginedId == null) {
				Map<String, Object> map = new HashMap<>();
				map.put("status", 0);
				map.put("msg", "로그인하세요");
				result = mapper.writeValueAsString(map);
			}else {
				OrderRepository repository = new OrderOracleRepository();
				OrderInfo info = new OrderInfo();
				info.setOrderId(loginedId);
				List<OrderLine> lines = new ArrayList<>();
				for(Product p : cart.keySet()) {
					Integer quantity = cart.get(p);
					OrderLine line = new OrderLine();
					line.setOrderP(p);
					line.setOrderQuantity(quantity);
					lines.add(line);
				}
				info.setLines(lines);
				
				try {
					repository.insert(info);	//주문추가
					session.removeAttribute("cart");	//장바구니 비우기
					Map<String, Object> map = new HashMap<>();
					map.put("status", 1);
					map.put("msg", "주문성공");
					result = mapper.writeValueAsString(map);
				} catch (AddException e) {
					e.printStackTrace();
				}
			}
		}
		out.print(result);
	}

}
