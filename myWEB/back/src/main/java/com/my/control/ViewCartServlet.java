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
import com.my.dto.Product;

public class ViewCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Map<Product, Integer>cart = (Map)session.getAttribute("cart");
		
		ObjectMapper mapper = new ObjectMapper();
		List list = new ArrayList<>();
		for(Product p: cart.keySet()) {
			Integer quantity = cart.get(p);
			Map map = new HashMap<>();
			map.put("p", p);
			map.put("quantity", quantity);
			//mapper.writeValueAsString(map);	//{"p":{"prodNo" : "C0001", ~ }, "quantity": 1}
			list.add(map);
		}
		String result = mapper.writeValueAsString(list);	//[{},{}]
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print(result);
	}
}
