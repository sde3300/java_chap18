package java_chap18;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCTest3Main {

	final static String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	final static String DB_URL = "jdbc:mysql://localhost:3306/testdb1?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC";
	
	public static void main(String[] args) {
		String userId = "tester1";
		String userPw = "asdf1234";
		
		Connection conn = null;
		Statement stmt = null;
		
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, userId, userPw);
			
			if (conn.isClosed()) {
				System.out.println("데이터 베이스에 연결되지 않았습니다.");
				System.exit(0);
			}
			System.out.println("데이터 베이스에 연결되었습니다.\n");
			
//			insert 쿼리로 변경됨
//			String insertQuery = "INSERT INTO address (id, pw, userName, email) ";
//			insertQuery += "VALUES ('test27', '1234', '테스트27', 'test27@gmail.com') ";
			String query = "UPDATE address SET pw = '4321' ";
			query += "WHERE num = 42 ";
			
			stmt = conn.createStatement();
//			executeUpdate() : INSERT, UPDATE, DELETE SQL문을 실행할때 사용
//			insert, update, delete는 결과값을 실행된 row수로 반환함
//			SQL문을 실행하고 난 결과값을 ResultSet으로 받을 필요가 없음
			int result = stmt.executeUpdate(query);
			
			System.out.println("실행 결과 : " + result + "개 실행되었습니다.");
			
//			String updateQuery = "";
//			String deleteQuery = "";
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		finally {
			try {
				if (stmt != null) { stmt.close(); }
				if (conn != null) { conn.close(); }
				System.out.println("데이터 베이스 연결이 종료되었습니다.");
			}
			catch (Exception e) {
				
			}
		}
	}
	
}
