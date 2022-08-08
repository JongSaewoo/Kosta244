import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import com.my.sql.MyConnection;

public class PreparedStatementTest {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Connection con = null;
		//Statement stmt = null; 
		PreparedStatement pstmt = null; // Statement의 자식클래스
		try {
			con = MyConnection.getConnection();
			//stmt = con.createStatement();
			String insertSQL = "INSERT INTO customer(id, pwd, name, status)"
					+ " VALUES (?, ?, ?, ?)"; // '?'를 바인드 변수라 한다.
			pstmt = con.prepareStatement(insertSQL); //stmt와 다르게 인자를 미리 넣어 준비함
			
			System.out.print("추가할 아이디를 입력하세요 : ");
			String id = sc.nextLine();
			
			System.out.print("추가할 비밀번호를 입력하세요 : ");
			String pwd = sc.nextLine();
			
			System.out.print("추가할 이름을 입력하세요 : ");
			String name = sc.nextLine();
			
			System.out.println("일반고객이면 1, 기업고객이면 2를 입력하세요");
			int status = sc.nextInt();
			
			//String insertSQL = "INSERT INTO customer (id, pwd, name)"
			//		+ " VALUES ('" + id + "', '" + pwd + "', '" + name + "')";
			//stmt.executeUpdate(insertSQL);
			pstmt.setString(1, id);   // 1번째 바인드변수에 id값을 넣는다.
			pstmt.setString(2, pwd);  // 2번째 바인드변수에 pwd를 넣는다.
			pstmt.setString(3, name);
			pstmt.setInt(4, status);
			pstmt.executeUpdate(); //PrepareStatement는 인자를 미리 준비했기에 인자를 넣을필요없음
			System.out.println("고객 등록 완료");
			
		} catch (SQLException e) {
			e.printStackTrace();
			return;
		} finally {
			MyConnection.close(pstmt, con);
		}
	}

}
