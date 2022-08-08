package com.my.control;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.my.dto.Product;

@Controller
public class ParameterTest {
	@GetMapping("a")
	public void a() {
		System.out.println("a메서드 호출");
	}
	@GetMapping("b")
	public void b(HttpServletRequest request) {
		System.out.println(request.getParameter("no"));
	}
	@GetMapping("c")
	public void c(HttpServletResponse response) throws IOException {
		response.sendRedirect("http://www.google.com");
	}
	@GetMapping("d")
	public void d(HttpSession session) {
		System.out.println("세션 새로 생성 여부 : " + session.isNew()); 
	}
	@GetMapping("e") //http://localhost:8888/backctr/e?prodNo=C0002&prodName=아메리카노&prodPrice=1500
	public void e(String prodNo, String prodName, int prodPrice) {
		System.out.println("prodNo=" + prodNo); 
		System.out.println("prodName=" + prodName); 
		System.out.println("prodPrice=" + prodPrice); 
	}
	@GetMapping("f") //http://localhost:8888/backctr/f?prod_no=C0002&prodName=아메리카노&prodPrice=1500
	public void f(@RequestParam(name = "prod_no") String prodNo, 
				  @RequestParam(required = false) String prodName, //required를 false로 넣으면 값을 꼭 요청안해도 해당파라미터는 null값 처리됨
				  												   //ex)http://localhost:8888/backctr/f?prod_no=C0002&prodPrice=1500	
				  @RequestParam(required = false, defaultValue = "0") int prodPrice) {	//http://localhost:8888/backctr/f?prod_no=C0002
		System.out.println("prodNo=" + prodNo); 
		System.out.println("prodName=" + prodName); 
		System.out.println("prodPrice=" + prodPrice); 
	}
	//유용한 기능 ★★★
	@GetMapping("g")  //http://localhost:8888/backctr/g?prodNo=C0002&prodName=아메리카노&prodPrice=1500
	public void g(Product p) {
		System.out.println("prodNo=" + p.getProdNo()); 
		System.out.println("prodName=" + p.getProdName()); 
		System.out.println("prodPrice=" + p.getProdPrice()); 
	}
	@GetMapping("h")  //http://localhost:8888/backctr/h?arr=one&arr=two
	public void h(String[] arr) {
		for(String str : arr) {
			System.out.println(str);
		}
	}
	@PostMapping("i")  //http://localhost:8888/backctr/i?prodNo=1&prodName=one&prodNo=2&prodName=two
//	public void i(String[] prodNo, String[] prodName) {
//		for(String no : prodNo) {
//			System.out.println(no);
//		}
//		for(String name : prodName) {
//			System.out.println(name);
//		}
//	}
	public void i(@RequestBody Product[] list) {
		for(Product p: list) {
			System.out.println(p);
		}
	}
}
