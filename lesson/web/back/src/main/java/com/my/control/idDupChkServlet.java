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

import com.my.dto.Product;
import com.my.sql.MyConnection;

public class idDupChkServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public idDupChkServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Product sample = (Product)request.getAttribute("test");
		System.out.println("idDupServlet의 sample객체 : " + sample);
		
		String id = request.getParameter("id");
		String result = "{\"status\":0, \"msg\": \"이미 사용중인 아이디입니다\"}";
		
		//DB연결
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String selectIdDupChkSQL = "SELECT * FROM customer WHERE id = ?";
		try {
			con = MyConnection.getConnection();
			pstmt = con.prepareStatement(selectIdDupChkSQL);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(!rs.next()) {
				result = "{\"status\":1, \"msg\": \"사용가능한 아이디입니다\"}";
			}
		} catch (SQLException e) {
			e.printStackTrace();
			String msg = e.getMessage();
			result = "{\"status\":0, \"msg\": \"" + msg + "\"}";
		} finally {
			MyConnection.close(rs, pstmt, con);
		}
		
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print(result);
	}

}
