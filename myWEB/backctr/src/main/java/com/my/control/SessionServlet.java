package com.my.control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SessionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		out.print("session.isNew()=" + session.isNew());
		out.print("session.getId()=" + session.getId());
		out.print("session.getLastAccessedTime()=" + session.getLastAccessedTime());
		String loginedId = (String)session.getAttribute("loginInfo");
		if(loginedId == null) {
			out.print("<hr>");
			out.print("로그인 안됐습니다");
		}else {
			out.print("<hr>");
			out.print("로그인 됐습니다");
		}
	}

}
