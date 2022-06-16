package com.my.control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.my.dto.Product;

public class MoveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String opt = request.getParameter("opt");
		if("forward".equals(opt)) {	//서버차원에서의 이동 : 클라이언트에게는 주소url창의 경로가 바뀌지않음.
									//				  out용 내부버퍼를 clear후 /iddupchk으로 이동 후
									//  			  돌아오지 않음
			//요청 속성(속성명: 'test', 값: 상품객체) 추가
			Product sample = new Product("F0001", "샌드위치", 2000);
			request.setAttribute("test", sample);
			
			//-------FORWARD전의 응답
			PrintWriter out = response.getWriter();
			out.print("BEFORE FORWARD");
			String path = "/iddupchk";
			RequestDispatcher rd = request.getRequestDispatcher(path);
			rd.forward(request, response);
			
			//-------FORWARD후의 응답
			out.print("AFTER FORWARD");
			
		}else if("redirect".equals(opt)) {	//이동 : 클라이언트에게 주소url창의 경로가 바뀜.
			response.sendRedirect("http://www.google.com");
			
		}else if("include".equals(opt)) {	//포함 : out용 내부버퍼를 clear하지않고 /iddupchk으로 이동 후
											//		돌아옴
			//-------INCLUDE전의 응답
			PrintWriter out = response.getWriter();
			out.print("BEFORE INCLUDE");
			String path = "/iddupchk";
			RequestDispatcher rd = request.getRequestDispatcher(path);
			rd.include(request, response);
			
			//-------INCLUDE후의 응답
			out.print("AFTER INCLUDE");
		}else {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print("<ul>");
			
			out.print("<li>");
			out.print("<a href=\"move?opt=forward\">FORWARD</a>");
			out.print("</li>");
			
			out.print("<li>");
			out.print("<a href=\"move?opt=redirect\">REDIRECT</a>");
			out.print("</li>");
			
			out.print("<li>");
			out.print("<a href=\"move?opt=include\">INCLUDE</a>");
			out.print("</li>");
			
			out.print("</ul>");
			
		}
	}

}
