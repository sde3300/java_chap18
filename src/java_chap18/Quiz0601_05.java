package java_chap18;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Quiz0601_05 {

	final static String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	final static String DB_URL = "jdbc:mysql://localhost:3306/testdb1?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC";
	
	public static void main(String[] args) {
//		문제 5) 데이터 베이스에 접속하여 사원 번호가 10001 ~ 10010까지의 사원 정보를 출력하는 프로그램을 작성하세요
//		접속 주소 : localhost
//		접속 DB : testdb1
//		검색 테이블 : employees
		
		String userId = "tester1";
		String userPw = "asdf1234";
		
//		DB 커넥션
		Connection conn = null;
//		SQL 쿼리 실행
		Statement stmt = null;
//		SQL 실행 결과
		ResultSet rs = null;
		
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, userId, userPw);
			
			if (conn.isClosed()) {
				System.out.println("데이터 베이스에 연결되지 않았습니다.");
				System.exit(0);
			}
			
			System.out.println("데이터 베이스에 연결되었습니다.\n\n");
			
			String query = "SELECT * FROM employees ";
			query += "WHERE emp_no BETWEEN 10001 AND 10010 ";
			
			stmt = conn.createStatement();
//			SQL 쿼리 실행
			rs = stmt.executeQuery(query); // SQL 쿼리 실행결과를 ResultSet 클래스 타입의 객체에 저장
			
			while (rs.next()) {
				String empNo = rs.getString("emp_no");
				String birthDate = rs.getString("birth_date");
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				String gender = rs.getString("gender");
				String hireDate = rs.getString("hire_date");
				
				System.out.println("사원 번호 : " + empNo + "\n사원 생일 : " + birthDate + "\n사원 이름 : " + firstName + "\n사원 성씨: " + lastName+ "\n사원 성별: " + gender + "\n사원 입사일 : " + hireDate + "\n\n-------------\n");
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
				if (rs != null) { rs.close(); }
				if (stmt != null) { stmt.close(); }
				if (conn != null) { conn.close(); }
				System.out.println("데이터 베이스 연결이 종료되었습니다.");
			}
			catch (Exception e) {
				
			}
		}
	}

}
