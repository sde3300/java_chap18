package java_chap18;

import java.io.File;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileEx {

	public static void main(String[] args) throws Exception {
//		File 클래스를 사용하여 지정한 위치의 파일 정보를 불러옴
//		해당 클래스의 객체는 파일의 존재 여부와 상관없이 생성됨
		File dir = new File("c:/java602/Temp/Dir");
		File file1 = new File("c:/java602/Temp/file1.txt");
		File file2 = new File("c:/java602/Temp/file2.txt");
//		URI 형태로 파일의 경로를 입력해도 상관없음
		File file3 = new File(new URI("file:///c:/java602/Temp/file3.txt"));
		
//		exists() : File 클래스 생성 시 지정한 파일 및 폴더의 존재 여부를 확인함
		if (dir.exists() == false) {
//			mkdir() : 지정한 폴더를 생성
//			mkdirs() : 지정한 폴더를 생성, 해당 경로에 존재하는 모든 폴더를 같이 생성
			dir.mkdirs(); 
		}
		
		if (file1.exists() == false) {
//			createNewFile() : 지정한 경로에 지정한 파일을 생성
			file1.createNewFile();
		}
		
		if (file2.exists() == false) {
			file2.createNewFile();
		}
		
		if (file3.exists() == false) {
			file3.createNewFile();
		}
		
		File temp = new File("c:/java602/Temp");
//		SimpleDateFormat : 해당 클래스는 날짜 관련 입출력 형식을 지정하는 클래스
//		프로그래밍의 날짜는 Unix Time을 기준으로 하여 현재시간까지의 진행된 시간을 매초로 표시함
//		현재 시간을 확인하면 모든 시간이 초로 출력되기에 일반 사용자가 보기에 어려움
//		일반 사용자가 보기 쉽도록 날짜의 형식을 지정하는 타입이 SimpleDateFormat 클래스임
//		Unix Time(UTC) : 세계 표준시를 뜻하며 1970년 1월 1일 0시 0분 00초를 기준으로 현재까지 흐른 모든 시간을 초 단위로 표현한 것
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		listFiles() : 지정한 File 클래스 객체가 가지고 있는 파일 및 폴더 리스트를 출력
		File[] contents = temp.listFiles();
		
//		long time = System.currentTimeMillis() / 1000;
//		System.out.println("현재 Unix Time : " + time);
		
		System.out.println("날짜           시간              형태         크기       이름");
		System.out.println("------------------------------------------------------------");
		
		for (File file : contents) {
//			Date : 해당 클래스는 자바의 시간 및 날짜를 표현하는 기본 클래스
//			lastModified() : File클래스의 메서드로 마지막 수정 날짜 및 시간을 출력
			System.out.print(sdf.format(new Date(file.lastModified())));
			
//			isDirectory() : File클래스의 메서드로 해당 객체의 경로가 폴더인지 아닌지 확인
			if (file.isDirectory()) {
				System.out.print("\t\t<DIR>\t\t\t" + file.getName());
			}
			else {
				System.out.print("\t\t\t\t" + file.length() + "\t" + file.getName());
			}
			System.out.println();
		}
	}

}
