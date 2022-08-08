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

import com.my.sql.MyConnection;

public class SignupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public SignupServlet(){
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		String name = request.getParameter("name");
		String addr = request.getParameter("addr");
		String buildingno = request.getParameter("buildingno");
		
//		System.out.println(id + ":" + pwd + ":" + name + ":" + addr + ":" + buildingno);
		
		//-------------------------------------------------------
		String result = "{\"status\":0, \"msg\": \"가입실패\"}";
		//DB연결
		Connection con = null;
		//SQL송신
		PreparedStatement pstmt = null;	//excuteUpdate()
		
		try {
			con = MyConnection.getConnection();
			String insertSQL = "INSERT INTO customer(id, pwd, name, status, buildingno, address)"
					+ "	VALUES (?, ?, ?, 1, ?, ?)";
			pstmt = con.prepareStatement(insertSQL);
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
			pstmt.setString(3, name);
			pstmt.setString(4, addr);
			pstmt.setString(5, buildingno);
			pstmt.executeUpdate();
			result = "{\"status\":1, \"msg\": \"가입성공\"}";
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			MyConnection.close(pstmt, con);
		}
		
		//DB연결닫기------------------------------------------------
		
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		out.print(result);
	}

}
