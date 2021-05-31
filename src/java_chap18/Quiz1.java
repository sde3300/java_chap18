package java_chap18;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class Quiz1 {

	public static void main(String[] args) {

//		문제 1) FileInputStream과 FileOutputStream 클래스를 사용하여 FileStream.txt 파일을 생성하고 사용자가 입력한 내용을 화면에 출력 후 'exit' 명령이 입력되면 사용자의 입력이 중지되고 파일에 저장된 내용을 모두 화면에 출력하는 프로그램을 작성하세요
//		파일명 : FileStream.txt
//		파일 위치 : C:\\java602\\
//		출력 형식 : 파일에 쓸 내용 : ~~~~
//					파일에 쓴 내용 : ~~~~
//					입력을 종료합니다.
//					------------- 파일의 내용 -------------
//						~~~~~~~~~~~~~
//						~~~~~~~~~~~~~
		
//		사용자 입력을 위한 Scanner 클래스
		Scanner sc = new Scanner(System.in);
		File file = null; // 파일의 기본 정보 
		FileInputStream fis = null; // 파일의 내용 불러오기
		FileOutputStream fos = null; // 파일에 저장하기
		String fileName = "FileStream.txt"; // 파일 이름
		String filePath = "c:\\java602\\"; // 파일의 경로
		
		try {
//			파일의 절대경로를 지정하여 파일 객체를 생성
			file = new File(filePath + fileName);
//			2번째 매개변수를 true로 사용 시 기존 파일에 데이터 추가하기
			fos = new FileOutputStream(file, true);
			
			System.out.println("파일에 저장할 내용을 입력하세요");
			System.out.println("-------------------------------");
			
			while (true) {
				System.out.print("파일에 쓸 내용 : ");
				String msg = sc.nextLine(); // 사용자 입력 내용 가져오기
				
				if (msg.equals("exit")) {
					System.out.println("입력을 종료합니다.");
					fos.flush(); // FileOutputStream 버퍼의 메모리 비워주기
					break;
				}
				
				msg += "\n";
				byte[] buff = msg.getBytes(); // 입력된 데이터를 byte[] 타입으로 변환
				fos.write(buff); // 입력된 내용을 파일에 쓰기
				
				System.out.print("파일에 쓴 내용 : " + msg);
			}
		}
		catch (IOException e) {
			System.out.println("파일에 데이터 저장 시 오류가 발생했습니다.");
			e.printStackTrace();
		}
		finally {
			if (fos != null) {
				try {
					fos.close();
				}
				catch(Exception e) {
					e.printStackTrace();
				}
			}

		}
		
		System.out.println("\n----------파일의 내용---------\n");
		try {
//			불러올 파일의 정보
			file = new File(filePath + fileName);
			fis = new FileInputStream(file); // 내용을 확인 할 파일 지정
			
			int buffSize = fis.available(); // 한번에 읽어 올 byte 수 확인
			byte[] buff = new byte[buffSize]; // 필요한 byte[] 생성
			fis.read(buff); // 지정한 byte[]에 데이터 읽어오기
			
			System.out.println(new String(buff)); // 읽어온 byte 데이터를 문자열로 변환하여 출력
		}
		catch (IOException e) {
			System.out.println("파일에 데이터 저장 시 오류가 발생했습니다");
			e.printStackTrace();
		}
		finally {
			if (fis != null) {
				try {
					fis.close();
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

}
