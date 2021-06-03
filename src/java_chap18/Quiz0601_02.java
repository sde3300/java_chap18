package java_chap18;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Quiz0601_02 {

	public static void main(String[] args) {
//		파일 경로는 직접 지정
		String path = "c:\\java602\\Temp\\";
		String fileName = ""; // 파일명을 입력받기 위한 변수
		
//		콘솔을 통하여 사용자 입력을 받기 위하여 Scanner를 사용
		Scanner sc = new Scanner(System.in);
		File file = null;
		FileReader fr = null;
		FileWriter fw = null;
		char[] cBuff = new char[1024]; // 파일의 내용을 읽어와서 저장할 배열
		
		System.out.print("파일 이름을 입력하세요 : ");
		fileName = sc.nextLine();
		
		if (fileName.equals("") || fileName == null) {
			System.out.println("파일 이름이 입력되지 않았습니다.\n 다시 실행하세요");
			System.exit(0); // 프로그램 종료
		}
		else {
			try {
//				File 클래스 타입의 변수에 File 객체 생성
				file = new File(path + fileName);
				
				System.out.println("파일에 입력할 내용을 키보드로 입력하세요");
				fw = new FileWriter(file, true);
				String temp = ""; // 입력되는 문자열을 저장하는 변수
				
				while (true) {
					System.out.print("입력 >> ");
					temp = sc.nextLine();
					
					if (temp.equals("exit")) {
						System.out.println("입력을 종료합니다.");
						break;
					}
					
					temp += "\r\n"; // \r\n은 자바에서 Enter 키를 누른것과 같은 효과
					fw.write(temp); // 파일에 쓰기
				}
				fw.flush(); // 버퍼 비우기
			}
			catch (IOException e) {
				System.out.println("파일 입력 시 오류가 발생했습니다.");
				e.printStackTrace();
			}
			finally {
				if (fw != null) {
					try {
						fw.close();
					}
					catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			
			System.out.println("\n ----- 파일 내용 출력 ----- \n");
			
			try {
				file = new File(path + fileName);
				fr = new FileReader(file);
				
				int readCnt = 0; // 읽어온 글자 수를 저장할 변수
				
				while ((readCnt = fr.read(cBuff)) != -1) {
					String data = new String(cBuff, 0, readCnt);
					System.out.print(data);
				}
			}
			catch (IOException e) {
				e.printStackTrace();
			}
			finally {
				if (fr != null) {
					try {
						fr.close();
					}
					catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
		
	}

}
