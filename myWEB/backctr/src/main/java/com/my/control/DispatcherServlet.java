package com.my.control;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String servletPath = request.getServletPath();
		System.out.println(servletPath);
		// tomcat의 web.xml의 default서블릿이 .jpg, .png, .gif, .html, .css, .js 요청만 응답
//		if("/login".equals(servletPath)) {
//			Controller control = new CustomerController();
//			control.excute(request, response);
//		}else if("/signup".equals(servletPath)) {
//			Controller control = new CustomerController();
//			control.excute(request, response);
//		}else if("/productlist".equals(servletPath)) {
//			
//		}
		//지금 실행중인 웹프로젝트의 실제경로
		ServletContext sc = getServletContext();
		String envPath = sc.getRealPath("my.properties"); //my.properties의 경로를 얻어옴
		System.out.println(envPath);
		
		//my.properties파일의 내용을 프로퍼티이름과 값으로 JVM에 로드
		Properties env = new Properties();
		env.load(new FileInputStream(envPath));
		
		//
		String clazzName = env.getProperty(servletPath);
		Controller control = null;
		String result = null;
		try {			
			Class clazz = Class.forName(clazzName);  //JVM에 클래스파일(*Controller.class) 로드
			control = (Controller)clazz.newInstance();  //로드된 클래스파일 객체생성
			result = control.excute(request, response); 
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}  
		
		//
		response.setContentType("application/json;charset=UTF-8");	
		PrintWriter out = response.getWriter();
		out.print(result);
	}

}