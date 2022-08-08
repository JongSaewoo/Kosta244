package com.my.repository;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.my.dto.Customer;
import com.my.exception.AddException;
import com.my.exception.FindException;

@Repository(value="customerRepository")
public class CustomerOracleRepository implements CustomerRepository {
//	@Autowired
//	@Qualifier(value="dataSource")
//	private DataSource ds;	
	// 이미 SqlSessionFactory bean이 property로 dataSource bean을 사용하고있으므로
	// 굳이 @Autowired로 주입할 필요 없음
	
	@Autowired
	private SqlSessionFactory sessionFactory;
	
	@Override
	public void insert(Customer customer) throws AddException {
		SqlSession session = null;
		try {
			session = sessionFactory.openSession();
			session.insert("com.my.mapper.CustomerMapper.insert", customer);
		}catch(Exception e) {
			e.printStackTrace();
			throw new AddException(e.getMessage());
		}finally {
			if(session!= null) {
				session.close();
			}
		}
//		//DB연결
//		Connection con = null;
//		//SQL송신
//		PreparedStatement pstmt = null;	//excuteUpdate()
//
//		try {
//			con = ds.getConnection();
//			String insertSQL = "INSERT INTO customer(id, pwd, name, status, buildingno, address)"
//					+ "	VALUES (?, ?, ?, 1, ?, ?)";
//			pstmt = con.prepareStatement(insertSQL);
//			pstmt.setString(1, customer.getId());
//			pstmt.setString(2, customer.getPwd());
//			pstmt.setString(3, customer.getName());
//			pstmt.setString(4, customer.getBuildingno());
//			pstmt.setString(5, customer.getAddress());
//			pstmt.executeUpdate();
//		} catch (SQLException e) {
//			e.printStackTrace();
//			throw new AddException(e.getMessage());
//		} finally {
//			MyConnection.close(pstmt, con);
//		}
	}

	@Override
	public Customer selectById(String id) throws FindException {
		SqlSession session = null;
		try {
			session = sessionFactory.openSession(); //히카리 라이브러리 메소드 사용
			Customer c = session.selectOne("com.my.mapper.CustomerMapper.selectById", id);
			//히카리에 마이바티스를 얹어 sql문 요청전송
			if(c == null) {
				throw new FindException("고객이 없습니다");
			}
			return c;
		}catch(Exception e) {
			e.printStackTrace();
			throw new FindException(e.getMessage());
		}finally {
			if(session != null) {
				session.close();  //히카리 pool에 사용자원을 돌려준다 : DB와 연결 끊기 x 	
			}
		}
		/*
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
		*/
	}
}	
