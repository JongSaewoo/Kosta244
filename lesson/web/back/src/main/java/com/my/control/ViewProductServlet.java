package com.my.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.my.dto.Product;
import com.my.exception.FindException;
import com.my.repository.ProductOracleRepository;
import com.my.repository.ProductRepository;

public class ViewProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		String result = "";
		//1.요청전달데이터 얻기(url의 쿼리 스트링값 얻기)
		String prod_no = request.getParameter("prod_no");
		
		//2.DB에서 상품검색
		ProductRepository repository = new ProductOracleRepository();
		try {
			Product p = repository.selectByProdNo(prod_no);
			Map<String, Object> map = new HashMap<>();
			map.put("status", 1);
			map.put("p", p);

			//JSON라이브러리(JACKSON) 활용
			//result = "{\"status\":1, \"p\":{\"prod_no\": \"" + p.getProdNo() + "\"} }";
			ObjectMapper mapper = new ObjectMapper();
			//String pJsonValue = mapper.writeValueAsString(p);  //요청전달데이터가 모두 JSON형태로 변함	
//			System.out.println("pJsonValue" + pJsonValue);
			//result = "{\"status\":1, \"p\":" + pJsonValue + "}";
			
			result = mapper.writeValueAsString(map);
			System.out.println("jsonValue : " + result);
			out.print(result);
			
			//-------------JSP 방식---------------
//			//3.request의 속성(이름:"p", 값:상품객체) 설정 (JSP방식)
//			request.setAttribute("p", p);
			
//			//4."/jsp/viewproduct.jsp" 이동 (JSP방식)
//			String path = "/jsp/viewproduct.jsp";
//			RequestDispatcher rd = request.getRequestDispatcher(path);
//			rd.forward(request, response);
			
			
		} catch (FindException e) {
			e.printStackTrace();
			Map<String, Object> map = new HashMap<>();
			map.put("status", 0);
			map.put("msg", e.getMessage());
			
			ObjectMapper mapper = new ObjectMapper();
			result = mapper.writeValueAsString(map);
			System.out.println("jsonValue : " + result);
			out.print(result);
		}
	}

}
