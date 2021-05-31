package java_chap18;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileOutputStreamMain {

	public static void main(String[] args) {
		FileOutputStream fos = null;
		File file = null;
		
		try {
			file = new File("c:\\java602\\FileOutputStreamTest.txt");
//			FileOutputStream() 생성자의 2번째 매개변수로 true를 사용 시 데이터 덮어쓰기가 아닌 데이터 추가하기로 변경됨
			fos = new FileOutputStream(file);
			
			String message = "FileOutputStream Test!!";
			
//			FileOutputStream 클래스는 바이트 기반 출력 스트림이므로 String 타입의 데이터를 파일에 쓸 수 없음
//			String 타입의 데이터를 byte 타입의 데이터로 변환해야 함
//			getBytes() : String 타입의 데이터를 byte 타입의 배열로 변환
			byte[] buff = message.getBytes();
//			write() : byte 배열 타입의 데이터를 파일에 출력
			fos.write(buff);
//			flush() : FileOutputStream의 버퍼메모리에 남아있는 데이터를 비움 
			fos.flush();
 		}
		catch (IOException e) {
			System.out.println("파일 생성 시 오류가 발생했습니다.");
			e.printStackTrace();
		}
		finally {
			if (fos != null) {
				try {
					fos.close();
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
	}

}
