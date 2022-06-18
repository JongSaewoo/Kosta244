package com.my.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.my.sql.MyConnection;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("LifeCycleServlet의 doPost() 호출됨");

		//요청전달데이터 얻기
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");

		//DB와 연결
		Connection con = null;
		//SQL송신
		PreparedStatement pstmt = null;
		//송신결과
		ResultSet rs = null;
		//응답결과
		String result = "{\"status\": 0}";
		
		//세션(클라이언트별 객체)얻기
		HttpSession session = request.getSession();
		session.removeAttribute("loginInfo");
		
		try {
			con=MyConnection.getConnection();
			String selectIdNPwdSQL = "SELECT * FROM customer WHERE id=? AND pwd=?";
			pstmt = con.prepareStatement(selectIdNPwdSQL);
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
			rs = pstmt.executeQuery();
			if(rs.next()) {	//마지막 쿼리문의 결과값인 rs행의 다음행이 존재한다면(로그인을 성공)..
				result = "{\"status\": 1}";
				session.setAttribute("loginInfo", id);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//DB와의 연결 닫기
			MyConnection.close(rs,  pstmt, con);
		}

		System.out.println("요청전달데이터 id=" + id + ", pwd=" + pwd);
		response.setContentType("application/json;charset=UTF-8");	
		PrintWriter out = response.getWriter();

		//		if(id.equals("id1") && pwd.equals("p1")) {
		//			out.print("{\"status\": 1}");	// 웹에 출력
		//			System.out.println("{\"status\": 1}");	// 이클립스 콘솔에 출력
		//		}else {
		//			out.print("{\"status\": 2}");
		//			System.out.println("{\"status\": 2}");
		//		}

		out.print(result);
	}

}
