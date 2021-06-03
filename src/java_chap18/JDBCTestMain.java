package java_chap18;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCTestMain {

	public static void main(String[] args) {
//		JDBC?
//		자바 프로그램 내에서 데이터베이스와 관련된 작업을 처리할 수 있도록 도와주는 자바 표준 인터페이스
//		관계형 데이터베이스 시스템에 접근하여 SQL문을 실행하기 위한 자바 API 또는 라이브러리
//		JDBC API를 사용하면 DBMS의 종류에 상관없이 데이터베이스 작업을 처리할 수 있음
		
		
//		JDBC를 사용하여 데이터 베이스 연동
//		1. java.sql.* 패키지 임포트
//		2. JDBC 드라이버 로딩
//			JDBC 드라이버 로딩 단계에서 드라이버 인터페이스를 구현하는 작업
//			Class.forName() 메서드를 사용하여 JDBC 드라이버 로딩
//			JDBC 드라이버가 로딩되면 자동으로 객체가 생성됨
//			생성된 객체는 DriverManager 클래스에 등록
//			JDBC 드라이버 로딩은 프로그램 실행 시 한번만 필요함ㄴ
//		3. 데이터베이스에 접속하기 위한 Connention 객체 생성
//			JDBC 드라이버에서 데이터베이스와 연결된 커넥션를 가져오기 위해서 getConnection() 메서드 실행
//			DriverManager 클래스로 Connection 객체를 생성 시 JDBC 드라이버를 검색하고, 검색된 드라이버를 사용하여 Connection 객체를 생성한 후 반환
//			데이터 베이스 연결이 필요없을 경우 close() 메서드를 사용하여 접속 종료
//		4. 쿼리문 실행을 위한 Statement / PreparedStatement 객체 생성
//			Statement : SQL 쿼리를 데이터베이스 서버에 전송하여 결과를 얻기 위해 사용
//			정적인 쿼리에 사용, 하나의 쿼리를 사용 후 더이상 사용할 수 없음
//			하나의 쿼리 사용 후 close()를 사용하여 메모리에서 제거해야 함
//				executeQuery() : 주로 Select 문을 질의할 때 사용함
//				executeUpdate() : Insert, Update, Delete 문을 질의할 때 사용함
//				close() : Statement를 닫을 때 사용
//			PreparedStatement : 동적인 쿼리에 사용
//			하나의 PreparedStatement 클래스 타입의 객체로 여러 번의 쿼리를 실행할 수 있음
//			동일한 쿼리문을 값만 바꾸어서 여러번 실행해야 할 경우 매개변수가 많아서 쿼리문을 정리해야할 때 유용
//				executeQuery() : 주로 Select 문을 통해서 데이터를 검색하고 반환
//				executeUpdate() : Insert, Update, Delete 문을 징의할 때 사용
//		5. 쿼리문 실행
//			ResultSet : Statement나 PreparedStatent 객체로 Select 문을 실행 시 반환되는 데이터 베이스 결과값을 저장할 수 있는 데이터 타입
//				getXXX(int column) : 설정한 컬럼의 index의 필드값을 설정한 XXX 데이터 타입으로 가져옴
//					ex) getString(0), getInt(1)
//				getXXX(String cloumn) : 설정한 컬럼명의 필드값을 설정한 XXX 데이터 타입으로 가져옴
//					ex) getString(name), getInt(number)
//				close() : ResultSet 객체를 닫음
//				next() : 다음 행으로 커서를 이동
//				first() : 첫번째 행으로 커서를 이동
//				last() : 마자막 행으로 커서를 이동
//				absolute(int row) : 지정한 행으로 커서를 이동
		
//		6. DB 결과값 사용
//		7. 사용된 객체 종료
		
//		JDBC 드라이버로 mysql을 사용하겠다고 선언
		String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
//		mysql 서버의 url 주소를 입력
//		:를 사용하여 각각의 옵션을 설정함
//		mysql - mysql을 사용한다는 의미
//		//XXX:XXX - DB 서버의 주소를 입력(ip, url), port는 지정된 포트 사용
//		url 뒤에 사용할 DB 이름을 입력
//		DB명 뒤 ? 를 붙이고 나머지 옵션을 사용, 옵션끼리의 구분은 & 기호로 함
//			useUnicode=true  - 데이터 베이스 사용 시 문자를 unicode로 사용
//			characterEncoding=UTF-8  - 데이터 베이스 문자셋을 UTF-8 형식 사용
//			serverTimezone=UTC  - 시간대 설정을 UTC방식 사용
		String DB_URL = "jdbc:mysql://localhost:3306/testdb1?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC";
		
		String userId = "tester1";
		String userPw = "asdf1234";
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
//			JDBC 드라이버를 로드
//			DriverManager에 자동으로 등록됨
			Class.forName(JDBC_DRIVER);
//			getConnection() : DriverManager 클래스의 메서드로 실행 시 지정된 DB주소로 접속함
//			사용자 id/pw를 비교하여 로그인까지 처리함
			conn = DriverManager.getConnection(DB_URL, userId, userPw);
			System.out.println("데이터 베이스에 연결되었습니다.");
		}
		catch (SQLException e) {
			System.out.println("데이터 베이스 연결 시 오류가 발생했습니다.");
			e.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if (rs != null) { rs.close(); }
				if (stmt != null) { stmt.close(); }
				
				if (conn != null) {
					System.out.println("데이터 베이스 연결이 종료되었습니다.");
					conn.close();
				}
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
