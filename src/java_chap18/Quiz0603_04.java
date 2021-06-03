package java_chap18;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Quiz0603_04 {
	
	final static String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	final static String DB_URL = "jdbc:mysql://localhost:3306/testdb1?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC";
	
//	클래스의 멤버 변수로 선언한 Connection 클래스 타입의 변수 conn
	private static Connection conn = null;
	
//	insert 문을 실행하는 메서드
//	테이블에 데이터를 저장하기 위해서 필요한 모든 정보를 매개변수로 받음
	public static void insertQuery(String id, String pw, String name, String email) {
//		SQL 쿼리를 실행하기 위해서 Statement 변수 선언
//		Statement는 한번 실행 후 반드시 닫아주어야 함
//		메서드의 지역변수로 선언하고 사용
		Statement stmt = null;
		try {
			String query = "INSERT INTO address (id, pw, userName, email) ";
			query += "VALUES ('"+ id +"', '"+ pw +"', '"+ name +"', '"+ email +"') ";
			
//			Statement 객체 생성
			stmt = conn.createStatement();
//			insert SQL문 DB서벌 전송
			stmt.executeUpdate(query);
			
			System.out.println("정보가 입력되었습니다.");
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		finally {
			try {
				if (stmt != null) { stmt.close(); }
			}
			catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
//	update SQL을 실행하는 메서드
//	update문은 모든 정보가 필요한 것은 아니지만 나머지 쿼리문을 1개만 사용하기 위해서 모든 정보를 입력 받음
	public static void updateQuery(String id, String pw, String name, String email) {
		Statement stmt = null;
		try {
			String query = "UPDATE address ";
			query += "SET pw = '" + pw + "', userName = '" + name + "', email = '" + email + "' ";
			query += "WHERE id = '" + id + "' ";
			
			stmt = conn.createStatement();
			stmt.executeUpdate(query);
			
			System.out.println("정보가 수정되었습니다.");
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		finally {
			try {
				if (stmt != null) { stmt.close(); }
			}
			catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
//	select SQL 문을 실행하기 위한 메서드
	public static Map<String, String> selectQuery(String id) {
		Statement stmt = null;
//		SQL 쿼리를 실행하기 위해서 Statement 변수 선언
//		SELECT 문 실행 후 결과값을 받기 위한 ResultSet 타입의 변수 선언
//		메서드의 지역변수로 선언하고 사용
		ResultSet rs = null;
//		select 문 실행 결과를 다른 곳에서 사용하기 위해 반환할 데이터 타입
//		Map 타입은 key와 value가 1:1인 쌍으로 이루어져 있는 데이터 타입
//		key는 컬럼명, value는 데이터가 들어가는 형태로 ResultSet 타입 대신 사용하기 좋음
//		ResultSet 타입은 사용 후 만드시 close() 메서드를 사용하여 닫아줘야 함
//		Map 타입은 GC(garbage collecor)가 자동으로 메모리를 해체함
		Map<String, String> result = new HashMap<String, String>();
		
		try {
			String query = "SELECT num, id, pw, userName, email FROM address ";
			query += "WHERE id = '" + id + "' ";
			
//			Statement 객체 생성
			stmt = conn.createStatement();
//			Statement를 사용하여 Select 쿼리문 실행
//			결과값을 ResultSet 타입의 변수 rs에 저장
			rs = stmt.executeQuery(query);
			
//			
			while (rs.next()) {
//				ResultSet 클래스의 메서드는 getXXX를 사용하여 데이터를 가져옴
				String num = rs.getString("num");
				String uId  = rs.getString("id");
				String uPw = rs.getString("pw");
				String uName = rs.getString("userName");
				String uEmail = rs.getString("email");
				
//				Map 타입의 변수 result에 저장
				result.put("num", num);
				result.put("id", uId);
				result.put("pw", uPw);
				result.put("name", uName);
				result.put("email", uEmail);
				
				System.out.println("Number : " + num + "\nID : " + uId + "\nPW: " + uPw + "\nName : " + uName + "\nEmail : " + uEmail + "\n");
			}
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		finally {
			try {
				if (rs != null) { rs.close(); }
				if (stmt != null) { stmt.close(); }
			}
			catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		
//		ResultSet을 통해서 얻은 데이터를 반환
		return result;
	}
	
//	사용자 정보 삭제
	public static void deleteQuery(String id) {
//		statement는 SQL 쿼리문을 한번 사용하면 닫아주는 것이 원칙이기 때문에 메서드의 지역변수로 선언하고 사용
		Statement stmt = null;
		try {
			String query = "DELETE FROM address ";
			query += "WHERE id = '" + id + "' ";
			
//			Statement 객체 생성
			stmt = conn.createStatement();
//			Statement를 통해서 SQL 쿼리문 실행
//			executeUpdate() : insert, update, delete 문 실행 시 사용
			stmt.executeUpdate(query);
			
			System.out.println("정보가 삭제되었습니다.");
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		finally {
			try {
				if (stmt != null) { stmt.close(); }
			}
			catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}

	public static void main(String[] args) {
		String userId = "tester1";
		String userPw = "asdf1234";
		
		Scanner sc = new Scanner(System.in);
		
		try {
//			JDBC 드라이버 로드
			Class.forName(JDBC_DRIVER);
//			지정한 DB 서버로 접속
			conn = DriverManager.getConnection(DB_URL, userId, userPw);
			
			if (conn.isClosed()) {
				System.out.println("데이터 베이스에 연결되지 않았습니다.");
				System.exit(0);
			}
			
			System.out.println("***** 연락처 프로그램 *****");
			
			while (true) {
				System.out.print("\n실행할 명령을 입력하세요(1:입력, 2:수정, 3:조회, 4:삭제, 0: 종료) ");
//				사용자 입력을 통해서 실행할 명령값 받기
				String command = sc.nextLine();
				
//				사용자 입력을 받기 위한 변수
				String id = "";
				String pw = "";
				String name = "";
				String email = "";
				
//				사용자 입력에 따라 실행할 명령 종류 확인
				if (command.equals("1")) {
					System.out.println("사용자 정보를 입력하세요");
					System.out.print("ID : ");
					id = sc.nextLine(); // 사용자입력을 통해서 ID 받기 
					
					System.out.print("PW : ");
					pw = sc.nextLine(); // 사용자입력을 PW 통해서 받기 
					
					System.out.print("NAME : ");
					name = sc.nextLine(); // 사용자입력을 통해서 NAME 받기 
					
					System.out.print("EMAIL : ");
					email = sc.nextLine(); // 사용자입력을 통해서 EMAIL 받기 
					
//					insertQuery() 메서드를 실행하여 DB서버에 Insert SQL 쿼리를 전송
					insertQuery(id, pw, name, email);
				}
				
//				사용자 정보 수정
				else if (command.equals("2")) {
					System.out.println("수정할 ID를 입력하세요");
					System.out.print("ID : ");
					id = sc.nextLine(); // 사용자 입력을 통해서 ID 받기 
					
//					입력 받은 id를 통해서 수정하고자 하는 사용자 정보를 가져옴
					Map<String, String> result = selectQuery(id);
					
//					가져온 사용자 정보를 화면에 출력
					System.out.print("PW : " + result.get("pw") + " : ");
					pw = sc.nextLine(); // 수정할 pw 입력
					
					System.out.print("NAME : " + result.get("name") + " : ");
					name = sc.nextLine(); // 수정할 name 입력
					
					
					System.out.print("EMAIL : " + result.get("email") + " : ");
					email = sc.nextLine(); // 수정할 email 입력
					
//					updateQuery를 실행하여 사용자가 입력한 정보로 수정함
					updateQuery(id, pw, name, email);
				}
				
//				사용자 정보 조회
				else if (command.equals("3")) {
					System.out.println("조회할 ID를 입력하세요");
					System.out.print("ID : ");
					id = sc.nextLine(); // 사용자 입력을 통해서 ID 받기
					
//					selectQuery()를 실행하여 DB서버에 select SQL 쿼리를 전송
					selectQuery(id);
				}
				
//				사용자 삭제
				else if (command.equals("4")) {
					System.out.println("삭제할 ID를 입력하세요");
					System.out.print("ID : ");
					id = sc.nextLine(); // 사용자입력을 통해서 ID 받기 
					
//					deleteQuery()를 실행하여 DB서버에 delete SQL 쿼리를 전송
					deleteQuery(id);
				}
				
//				프로그램 종료
				else if (command.equals("0")) {
					System.out.println("\n***** 프로그램을 종료합니다 *****\n");
					break; // 무한루프인 while문을 탈출
				}
				else {
					System.out.println("\n잘못 입력하셨습니다.\n0 ~ 4까지의 숫자로 입력해주세요.\n");
				}
			}
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		finally {
			try {
				if (conn != null) { conn.close(); }
			}
			catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}

}
