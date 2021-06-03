package java_chap18;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCTest2Main {
//	접속 정보를 상수로 선언
	final static String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	final static String DB_URL = "jdbc:mysql://localhost:3306/testdb1?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC";

	public static void main(String[] args) {
		String userId = "tester1";
		String userPw = "asdf1234";
		
//		DB 서버 커넥터
		Connection conn = null;
//		SQL 쿼리를 DB서버에 질의하는 객체
		Statement stmt = null;
//		SQL 쿼리를 질의한 후 그 결과값을 받아오는 객체
		ResultSet rs = null;
		
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, userId, userPw);
			
			if (conn.isClosed()) {
				System.out.println("데이터 베이스에 연결되지 않았습니다.");
				System.exit(0);
			}
			
			System.out.println("데이터 베이스에 연결되었습니다.");
			
//			SQL문을 생성
			String query = "SELECT * FROM address ";
//			Connection 클래스의 객체를 사용하여 SQL 쿼리문을 실행할 Statement를 생성
			stmt = conn.createStatement();
//			SQL 쿼리를 Statement 를 통하여 DB 서버에 전송
//			SELECT 문을 실행했을 경우 그 결과값을 ResultSet 클래스 타입의 객체가 받아옴
			rs = stmt.executeQuery(query);
			
//			next() : ResultSet클래스의 메서드로 커서를 다음 Row로 이동, 다음 Row로 이동이 가능하면 true를 반환하고 다음ROW로 이동, 이동이 불가능 시 false를 반환
			while (rs.next()) {
				int num = rs.getInt("num");
				String id = rs.getString("id");
				String pw = rs.getString("pw");
				String name = rs.getString("userName");
				String email = rs.getString("email");
				
				System.out.println("순번 : " + num + "\n사용자 ID : " + id + "\n사용자 PW : " + pw + "\n사용자명 : " + name + "\n사용자 이메일 : " + email + "\n\n-------------\n");
			}
		}
		catch (SQLException e) {
			System.out.println("데이터 베이스 사용 시 오류가 발생했습니다.");
		}
		catch (Exception e) {
			System.out.println("데이터 베이스 연결시 오류가 발생했습니다.");
		}
		finally {
			try {
//				close() : 외부 리소스를 사용 종료 시 GC가 자동으로 메모리를 회수할 수 없기 때문에 사용자가 직접 메모리 회수를 해야 함
//				사용 순서의 역순으로 resource를 해제해야 함 ResultSet > Statement > Connection 순서로 해제
				if (rs != null) { rs.close(); }
				if (stmt != null) { stmt.close(); }
				if (conn != null) { conn.close(); }
			}
			catch (Exception e) {
				
			}
		}
	}

}
