package java_chap18;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class FileInputStreamMain {

	public static void main(String[] args) throws IOException {
//		getResource(), getPath()를 사용하여 지정한 파일의 절대 경로를 알아내는 방법
		String path = FileInputStreamMain.class.getResource("fileInputStreamTest.txt").getPath();
		
//		InputStream를 상속받아서 파일의 내용을 바이트 방식으로 읽어오는 클래스
		FileInputStream fis = null;
//		지정한 파일의 정보를 가져오는 클래스
		File file = new File(path);
		
		try {
//			File 클래스 타입의 변수 file을 매개변수로 하여 FileInputStream 클래스의 객체를 생성
			fis = new FileInputStream(file);
			
			int read = 0; // read() 메서드를 통해서 읽어온 데이터를 저장하는 변수
//			read() : 파일의 내용을 1바이트씩 읽어오는 메서드, 더 이상 내용이 없을 경우 -1을 반환
			
			while ((read = fis.read()) != -1) {
				System.out.print((char)read);
			}
		}
		catch (IOException e) {
			System.out.println("파일을 읽어오는데 오류가 발생했습니다.");
			e.printStackTrace();
		}
		finally {
			if (fis != null) {
				try {
					
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}
			
		}
		
		System.out.println("------- 한글 출력 -------");
		
		try {
			fis = new FileInputStream(file);
//			available() : 파일 스트림에서 읽어올 수 있는 byte의 수를 반환
			int buffSize = fis.available();
			byte[] buff = new byte[buffSize]; // 원하는 크기의 byte 배열 생성
			
//			read(byte[] b) : 배열의 크기만큼 파일에서 byte 타입으로 읽어옴
			fis.read(buff);
			System.out.println(new String(buff)); // byte 타입의 배열을 문자열로 변환하여 출력 
		}
		catch (IOException e) {
			System.out.println("파일을 읽어오는데 오류가 발생했습니다.");
		}
		finally {
			if (fis != null) {
				try {
					
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
//		FileInputStream fis = new FileInputStream(new File(path));
//		
//		int read = 0;
//		
//		while ((read = fis.read()) != -1) {
//			System.out.print((char)read);
//		}
//
//		fis.close();
		
	}

}
