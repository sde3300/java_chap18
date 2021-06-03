package java_chap18;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class PerparedStatementMain {
	final static String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	final static String DB_URL = "jdbc:mysql://localhost:3306/testdb1?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC";
	
	public static void main(String[] args) {
//	PreparedStatement
//	SQL문이 미리 컴파일되어 저장되어 있는 형태의 Statement
//	SQL을 여러번 사용할 수 있음
//	SQL 쿼리문을 객체 생성 시 매개변수로 사용하고, SQL문 ? 인 와일드카드 문자를 사용하여 나중에 해당 와일드카드 문자에 데이터를 입력하는 형태로 사용
//	와일드카드 문자에 데이터를 입력하기 위한 메서드로 setXXX()를 사용함   
//		XXX에는 데이터 타입을 입력
//		첫번째 매개변수는 ? 의 위치,두번째 매개변수는 데이터 값
//	이미 입력된 데이터를 삭제하기 위한 메서드도 존재 (clearParameters())
//	executeQuery() : Select 문을 실행할 경우 사용, ResultSet 타입으로 데이터를 반환
//	executeUpdate() : Insert, Update, Delete 문을 실행 할 경우 사용, int 타입으로 데이터를 반환
//	setInt() : ? 인 와일드카드 문자에 int 타입의 데이터 입력
//	setString() : ? 인 와일드카드 문자에 String 타입의 데이터 입력
//		ex) pstmt.setInt(1, 100);
//		ex) pstmt.setString(2, "문자열");
//	clearParameters() : 이미 입력된 데이터를 모두 삭제

		String userId = "tester1";
		String userPw = "asdf1234";
		
//		DB 서버 접속을 위한 객체
		Connection conn = null;
		Statement stmt = null; // Statement 변수
		PreparedStatement pstmt = null; // PreparedStatement 변수
		ResultSet stmtRs = null; // Statement를 사용 시 변환받을 데이터
		ResultSet pstmtRs = null; // PreparedStatement를 사용 시 반환받을 데이터 
		
//		쿼리에 입력할 데이터 
		String name = "mario";
		int number1 = 10000;
		int number2 = 20000;
		
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, userId, userPw);
			
			if (conn.isClosed()) {
				System.out.println("데이터베이스가 연결되지 않았습니다");
				System.exit(0);
			}
			
//			Statement를 사용한 SQL 쿼리
//			변수를 사용하여 일반적인 문자열을 만들듯이 SQL 쿼리문을 만들어야 함
			String stmtQuery = "SELECT * FROM employees ";
			stmtQuery += "WHERE first_name = '" + name + "' ";
			stmtQuery += "AND emp_no BETWEEN " + number1 + " AND " + number2 + " ";
		
//			PreparedStatement를 사용한 SQL 쿼리문
//			변수가 들어갈 부분에 ? 와일드카드 문자를 사용
//			데이터가 문자열이든 숫자든 ''를 신경쓰지 않아도 됨
			String pstmtQuery = "SELECT * FROM employees ";
			pstmtQuery += "WHERE first_name = ? ";
			pstmtQuery += "AND emp_no BETWEEN ? AND ? ";
			
//			Statement 객체 생성
			stmt = conn.createStatement();
//			Statement 를 사용하여 select SQL 쿼리 실행
			stmtRs = stmt.executeQuery(stmtQuery);
			
//			PreparedStatement 객체 생성
//			객체 생성 시 쿼리문을 매개변수로 포함함
			pstmt = conn.prepareStatement(pstmtQuery);
//			setXXX 메서드로 해당 위치의 ? 와일드카드 문자에 데이터를 적용
			pstmt.setString(1, name);
			pstmt.setInt(2, number1);
			pstmt.setInt(3, number2);
//			PreparedStatement 를 사용하여 SQL 쿼리 실행			
			pstmtRs = pstmt.executeQuery();
			
			while (stmtRs.next()) {
				int empNo = stmtRs.getInt("emp_no");
				String birthDate = stmtRs.getString("birth_date");
				String firstName = stmtRs.getString("first_name");
				String lastName = stmtRs.getString("last_name");
				String gender = stmtRs.getString("gender");
				String hireDate = stmtRs.getString("hire_date");
			
				System.out.println("----- Statement를 사용한 출력 -----");
				System.out.println("사번 : " + empNo + "\n생일 : " + birthDate + "\n이름 : " + firstName + " " + lastName);
				if (gender.equals("M")) {
					System.out.println("성별 : 남성");
				}
				else {
					System.out.println("성별 : 여성");
				}
				System.out.println("입사일 : " + hireDate);
			}
			System.out.println("\n\n");
				
			
			while (pstmtRs.next()) {
				int empNo = pstmtRs.getInt("emp_no");
				String birthDate = pstmtRs.getString("birth_date");
				String firstName = pstmtRs.getString("first_name");
				String lastName = pstmtRs.getString("last_name");
				String gender = pstmtRs.getString("gender");
				String hireDate = pstmtRs.getString("hire_date");
				
				System.out.println("----- PrepatedStatement 를 사용한 출력 -----");
				System.out.println("사번 : " + empNo + "\n생일 : " + birthDate + "\n이름 : " + firstName + " " + lastName);
				if (gender.equals("M")) {
					System.out.println("성별 : 남성");
				}
				else {
					System.out.println("성별 : 여성");
				}
				System.out.println("입사일 : " + hireDate);
			}
			
		}
	
		catch (SQLException e) {
			
		}
		catch (Exception e) {
			
		}
		finally {
			try {
				if (pstmtRs != null) { pstmtRs.close(); }
				if (stmtRs != null) { stmt.close(); }
				if (pstmt != null) { pstmt.close(); }
				if (stmt != null) { stmt.close(); }
				if (conn != null ) { conn.close(); }
			}
			catch (Exception e) {
				
			}
			
		}
	}
}