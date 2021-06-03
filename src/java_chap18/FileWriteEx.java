package java_chap18;

import java.io.File;
import java.io.FileWriter;

public class FileWriteEx {

	public static void main(String[] args) throws Exception {
		File file = new File("c:\\java602\\Temp\\file.txt");
		FileWriter fw = new FileWriter(file, true);
		
		fw.write("FileWriter는 한글로된 \r\n");
		fw.write("문자열을 바로 출력할 수 있다. \r\n");
		fw.flush();
		fw.close();
		
		System.out.println("파일에 저장되었습니다.");
		
//		문제1) FileReader과 FileWriter을 사용하여 FileReaderWriterTest.txt 파일에 데이터를 쓰고 읽는 프로그램을 작성하세요
		
//		문제2) FileReader과 FileWriter, Scanner를 사용하여 애국가 1절을 파일에 저장하고, 저장된 데이터를 화면에 출력하는 프로그램을 작성하세요.
	}

}
