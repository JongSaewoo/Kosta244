package com.my.repository;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.my.dto.Customer;
import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.sql.MyConnection;

public class CustomerOracleRepository implements CustomerRepository {
	
	// 재사용성을 높이기위해 주석처리(selectById()로 대체 가능)
//	@Override
//	public Customer selectByIdAndPwd(String id, String pwd) throws FindException {
//		//DB와 연결
//		Connection con = null;
//		//SQL송신
//		PreparedStatement pstmt = null;
//		//송신결과
//		ResultSet rs = null;
//		try {
//			con=MyConnection.getConnection();
//			String selectIdNPwdSQL = "SELECT * FROM customer WHERE id=? AND pwd=?";
//			pstmt = con.prepareStatement(selectIdNPwdSQL);
//			pstmt.setString(1, id);
//			pstmt.setString(2, pwd);
//			rs = pstmt.executeQuery();
//			if(rs.next()) {	//마지막 쿼리문의 결과값인 rs행의 다음행이 존재한다면(로그인을 성공)..
//				return new Customer(rs.getString("id"),
//						rs.getString("pwd"),
//						rs.getString("name"),
//						rs.getString("address"),
//						rs.getInt("status"),
//						rs.getString("buildingno")
//						);
//			}
//			throw new FindException("고객이 존재하지 않습니다.");
//		}catch(Exception e) {
//			throw new FindException(e.getMessage());
//		}finally {
//			MyConnection.close(rs, pstmt, con);
//		}
//	}


	@Override
	public void insert(Customer customer) throws AddException {
		//DB연결
		Connection con = null;
		//SQL송신
		PreparedStatement pstmt = null;	//excuteUpdate()

		try {
			con = MyConnection.getConnection();
			String insertSQL = "INSERT INTO customer(id, pwd, name, status, buildingno, address)"
					+ "	VALUES (?, ?, ?, 1, ?, ?)";
			pstmt = con.prepareStatement(insertSQL);
			pstmt.setString(1, customer.getId());
			pstmt.setString(2, customer.getPwd());
			pstmt.setString(3, customer.getName());
			pstmt.setString(4, customer.getBuildingno());
			pstmt.setString(5, customer.getAddress());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AddException(e.getMessage());
		} finally {
			MyConnection.close(pstmt, con);
		}
	}

	@Override
	public Customer selectById(String id) throws FindException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String selectIdDupChkSQL = "SELECT * FROM customer WHERE id = ?";
		try {
			con = MyConnection.getConnection();
			pstmt = con.prepareStatement(selectIdDupChkSQL);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return new Customer(rs.getString("id"),
									rs.getString("pwd"),
									rs.getString("name"),
									rs.getString("address"),
									rs.getInt("status"),
									rs.getString("buildingno"));
			}
			throw new FindException("고객이 없습니다");
		}catch(SQLException e) {
			throw new FindException(e.getMessage());
		}finally {
			MyConnection.close(rs, pstmt, con); 
		}
	}
}	
