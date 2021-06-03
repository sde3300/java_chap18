package java_chap18;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Quiz0601_01 {

	public static void main(String[] args) {
//		문제1) FileReader과 FileWriter을 사용하여 FileReaderWriterTest.txt 파일에 데이터를 쓰고 읽는 프로그램을 작성하세요
		String path = "c:\\java602\\Temp\\";
		String fileName = "FileReaderWriterTest.txt";
		File file = new File(path + fileName);
		FileWriter fw = null;
		FileReader fr = null;
		char[] cBuff = new char[1024]; // FileReader을 통하여 파일 내용을 저장할 변수
		
		try {
			fw = new FileWriter(file, true);
			fw.write("FileWriter를 이용하여 파일에 내용 저장하기\r\n");
			fw.write("간단한 내용을 입력합니다.\r\n");
			fw.flush();
			
			fr = new FileReader(file);
			int readCnt = 0; // read(char[] ) 메서드 실행 후 읽어온 데이터 수 저장
			
			System.out.println("\n ----- 파일 내용 출력 ----- \n");
			
			while ((readCnt = fr.read(cBuff)) != -1) {
//				배열 cBuff에 저장된 내용을 String 타입으로 변환하여 String 타입의 변수 data에 저장
				String data = new String(cBuff, 0, readCnt);
				System.out.print(data);
			}
		}
		catch (IOException e) {
			System.out.println("파일을 사용하는 도중 오류가 발생했습니다.");
			e.printStackTrace();
		}
		finally {
			try {
				if (fw != null) {
					fw.close();
				}
				
				if (fr != null) {
					fr.close();
				}
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
