package com.my.control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.my.dto.Product;

public class AddCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String prodNo = request.getParameter("prod_no");
		String quantity = request.getParameter("quantity");

		HttpSession session = request.getSession();
		Map<Product, Integer> cart = (Map)session.getAttribute("cart");                                                                                     
		if(cart == null) {
			cart = new HashMap<>();
			session.setAttribute("cart", cart);
		}
		
		Product p = new Product(); p.setProdNo(prodNo);
		int newQuantity = Integer.parseInt(quantity);
		Integer oldQuantity = cart.get(p);
		if(oldQuantity != null) { //상품이 있는 경우
			newQuantity+= oldQuantity;
		}
		cart.put(p, newQuantity);
		
		System.out.println("장바구니 목록수 :" + cart.size());
		System.out.println(cart);
	}
}
