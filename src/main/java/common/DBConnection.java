package common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConnection {
//	자바에서 오라클 DB에 접속
	
	public static Connection getConnection() {
		Connection con = null;
//		자바에서 DB를 연결하는 과정
		try {
			 // 오라클 jdbc 호출(드라이버 읽어오기)
//			이클립스에 드라이버를 주입하지 않으면 제대로 실행되지 않음
//			뭔가 스프링의 의존성 주입(dependency)과 닮음
//			260415: 드라이버(ojdbc8.jar) 주입 완료
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		String db_url = "jdbc:oracle:thin:@jsl-704:1523/xe";
//		String db_url = "jdbc:oracle:thin:@1.245.91.227:1523/xe";
		String db_user = "track27_11g";
		String db_password = "1234";
		
//		DB 연결
		try {
			con = DriverManager.getConnection(db_url, db_user, db_password);
		} catch (SQLException e) {
			System.out.println("DB Connection Error");
			e.printStackTrace();
		}
		
		return con;
	}
//	반드시 DB 접속을 끊어주기 위해 사용(->DB 접속 해제 안하면 문제가 발생한다고 함!)
	public static void closeDB(Connection con, PreparedStatement ps, ResultSet rs) {
//		선언된 역순으로 종료(각 변수가 null이 아닐 경우)
//		접속 성공 여부와 관계없이 접속 끊기
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if (ps != null) {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
}
