package java_chap18;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Quiz0601_04 {

	final static String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	final static String DB_URL = "jdbc:mysql://localhost:3306/testdb1?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC";
	
	public static void main(String[] args) {
//		문제 4) LocalDB(MySql)에 접속하는 프로그램을 작성하세요
//		접속 주소 : localhost
//		접속 DB : testdb1
		
		String userId = "tester1";
		String userPw = "asdf1234";
		
//		DB 서버 커넥터
		Connection conn = null;
		
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, userId, userPw);
			
			if (conn.isClosed()) {
				System.out.println("데이터 베이스에 연결되지 않았습니다.");
				System.exit(0);
			}
			
			System.out.println("데이터 베이스에 연결되었습니다.");
		}
		catch (SQLException e) {
			System.out.println("데이터 베이스 사용 시 오류가 발생했습니다.");
		}
		catch (Exception e) {
			System.out.println("데이터 베이스 연결시 오류가 발생했습니다.");
		}
		finally {
			try {
				if (conn != null) {
					System.out.println("데이터베이스 연결을 종료합니다.");
					conn.close();
				}
			}
			catch (Exception e) {
				
			}
		}
	}

}
