package java_chap18;

import java.io.FileReader;

public class FileReaderEx {

	public static void main(String[] args) throws Exception {
//		FileReader : 문자 기반 입력 스트림인 Reader의 하위 클래스로 텍스트 기반 파일을 읽기 위해서 사용하는 클래스
//		File 클래스 타입의 객체를 생성자의 매개변수로 사용해도 됨
		FileReader fr = new FileReader("c:\\java602\\filetest.txt");
//		File file = new File("c:\\java602\\filetest.txt");
//		FileReader fr = new FileReader(file);
		
		int readCharNo = 0; // FileReader를 통해서 읽어온 문자를 저장하기 위한 변수
		char[] cBuff = new char[100]; // FileReader를 통해서 읽어온 문자를 전체적으로 저장하기 위한 배열
		
//		read() : FileReader 클래스의 메서드로 파일에서 문자 1개를 읽어와서 반환
//		read() 메서드가 더이상 문자를 읽어올 수 없을 경우 -1을 반환함
//		read(char[] cBuff) : FileReader 클래스의 메서드로 한번에 지정한 배열의 크기만큼 데이터를 읽어옴, 읽어온 데이터는 매개변수인 char 타입의 배열에 저장하고, 읽어온 데이터의 길이를 int 타입으로 반환함
		while ((readCharNo = fr.read(cBuff)) != -1) {
			String data = new String(cBuff, 0, readCharNo);
			System.out.print(data);
		}
		fr.close();
	}

}
