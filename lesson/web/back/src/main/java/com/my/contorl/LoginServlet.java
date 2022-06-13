package com.my.contorl;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("LifeCycleServlet의 doPost() 호출됨");
		
		response.setContentType("application/json;charset=UTF-8");	
		PrintWriter out = response.getWriter();
		
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		
		System.out.println("요청전달데이터 id=" + id + ", pwd=" + pwd);
		
		if(id.equals("id1") && pwd.equals("p1")) {
			out.print("{\"status\": 1}");	// 웹에 출력
			System.out.println("{\"status\": 1}");	// 이클립스 콘솔에 출력
		}else {
			out.print("{\"status\": 2}");
			System.out.println("{\"status\": 2}");
		}
	}

}
