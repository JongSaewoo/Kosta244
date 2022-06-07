import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.my.sql.MyConnection;

public class TransactionTest {

	public static void main(String[] args) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = MyConnection.getConnection();
			con.setAutoCommit(false); //
			String insertInfoSQL = 
					"INSERT INTO order_info(order_no, order_id, order_dt)"
					+" VALUES     (order_seq.NEXTVAL,     ?,       SYSDATE)";
			String insertLineSQL =
					"INSERT INTO order_line(order_no, order_prod_no, order_quantity)"
					+" VALUES     (order_seq.CURRVAL,     ?,           ?)";
			
			pstmt = con.prepareStatement(insertInfoSQL); //주문기본추가SQL
			pstmt.setString(1, "id1");
			pstmt.executeUpdate();
			
			pstmt = con.prepareStatement(insertLineSQL); //주문상세추가SQL
			pstmt.setString(1, "C0001"); pstmt.setInt(2, 10);//C0001상품
			pstmt.executeUpdate();
			pstmt.setString(1, "X"); pstmt.setInt(2, 10); //X상품
			pstmt.executeUpdate(); //예외 예상
			con.commit(); //트랜잭션 완료
		} catch (SQLException e) {
			if(con != null) {
				try {
					con.rollback();//트랜잭션 취소
				} catch (SQLException e1) {
				}
			}
			e.printStackTrace();
		}

	}

}
