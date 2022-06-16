package com.my.control;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LifeCycleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LifeCycleServlet() {
    	System.out.println("LifeCycleServlet의 생성자 호출됨");
    	//ServletContext sc = this.getServletContext();
    	//String developer = sc.getInitParameter("developer");
    	//System.out.println(developer);
    }

	public void init(ServletConfig config) throws ServletException {
		super.init(config);	//ServletContext객체를 참조
		System.out.println("LifeCycleServlet의 init() 호출됨");
		ServletContext sc = this.getServletContext();
		String developer = sc.getInitParameter("developer");
    	System.out.println(developer);
    	
    	String fileName = this.getInitParameter("fileName");
    	System.out.println(fileName);
	}

	public void destroy() {
		System.out.println("LifeCycleServlet의 destroy() 호출됨");
	}

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("LifeCycleServlet의 service() 호출됨");
		super.service(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("LifeCycleServlet의 doGet() 호출됨");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("LifeCycleServlet의 doPost() 호출됨");
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		System.out.println("요청전달데이터 id=" + id + ", pwd=" + pwd);
	}

}
