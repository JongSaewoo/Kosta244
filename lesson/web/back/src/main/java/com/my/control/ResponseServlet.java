package com.my.control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ResponseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//응답형식설정 : MIME(text/plain, text/html, application/json .. 등)
		response.setContentType("text/html;charset=UTF-8");	
		
		PrintWriter out = response.getWriter();	//바이트단위, 응답출력스트림 얻기
		
		//System.out의 자료형은 PrintStream			//문자단위
		//서버단에서 직접 응답할 내용을 자동 flush()하여 클라이언트(웹브라우저)에게 보내고 웹브라우저는 랜더링하여 보여줌
		out.print("<html>");	// 응답출력스트림 쓰기
		out.print("<body>");
		for(int i=1; i<=5; i++) {
			out.print("<h" + i + ">");
			out.print("제목" + i);
			out.print("</h" + i + ">");
		}
		out.print("</body>");
		out.print("</html>");
	}

}
