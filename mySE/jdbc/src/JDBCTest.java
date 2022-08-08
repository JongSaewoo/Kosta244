import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

public class JDBCTest {
	public static void search() {
		//1.JDBC드라이버 설치
		//2.JDBC드라이버 클래스로드
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return;
		}
		//3.DB연결
		Connection con = null;
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "hr";
		String password = "hr";
		try {
			con = DriverManager.getConnection(url, user, password);			
		} catch (SQLException e) {			
			e.printStackTrace();
			return;
		}
		//4.SQL문 송신(SELECT,        INSERT/UPDATE/DELETE, CREATE/ALTER/DROP)
		Statement stmt = null;
		//			(executeQuery(),        executeUpdate()
		//5.결과 수신 (행들,                   처리건수,                 0       ) 
		//          (ResultSet                  int                    )
		//ex ResultSet rs = stmt.executeQuery("SELECT~");  //반환값이 행들
		//   int rowcnt =   stmt.executeUpdate("INSERT~"); //반환값이 처리건수
		//   int rowcnt =   stmt.executeUpdate("CREATE~"); //반환값이 무조건 0

		ResultSet rs = null;
		try {
			stmt = con.createStatement();
			String selectSQL = "SELECT employee_id, first_name, salary, hire_date FROM employees";
			rs = stmt.executeQuery(selectSQL); //executeUpdate();
			SimpleDateFormat sdf = new SimpleDateFormat("yy/MM/dd hh:mm:ss");
			//6.결과 활용
			while(rs.next()) {
				int id = rs.getInt("employee_id");  //rs.getInt(1)
				String name = rs.getString("first_name"); //rs.getString(2)
				int sal = rs.getInt("salary");
				java.sql.Date hdt = rs.getDate("hire_date");

				System.out.println(id + ":" + name + ":" + sal  + ":" + hdt + ":" + sdf.format(hdt));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//7.DB연결해제
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	public static void add() {
		//1.JDBC드라이버 설치
		//2.JDBC드라이버 클래스로드
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return;
		}
		//3.DB연결
		Connection con = null;
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "hr";
		String password = "hr";
		try {
			con = DriverManager.getConnection(url, user, password);			
		} catch (SQLException e) {			
			e.printStackTrace();
			return;
		}
		//4.SQL문 송신(SELECT,        INSERT/UPDATE/DELETE, CREATE/ALTER/DROP)
		Statement stmt = null;
		//			(executeQuery(),        executeUpdate()
		//5.결과 수신 (행들,                   처리건수,                 0       ) 
		//          (ResultSet                  int                    )
		try {
			stmt = con.createStatement();
			String insertSQL = "INSERT INTO customer(id, pwd, name, status) \r\n"
					+ "VALUES ('id9', 'p9', 'n9', 1)";
			int rowcnt = stmt.executeUpdate(insertSQL);
			System.out.println(rowcnt+"건이 추가되었습니다");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	public static void main(String[] args) {
		//search();
		add();
	}
}