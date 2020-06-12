package Chatting;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileSave {
	String filename;
	public FileSave() {
		fileCopy("E:\\java\\data\\3.png");
	}
	
	public void fileCopy(String poto) {
		String path = "E:/java/data/profile/";
		FileInputStream fis=null;
		FileOutputStream fos=null;
		
		int lastIndex = poto.lastIndexOf("\\");//escape 시키면 특수문자가 기능을 탈출하여
		//그냥 일반 문자가 되버린다
		filename = poto.substring(lastIndex+1,poto.length());

		try {
			fis = new FileInputStream(poto);
			fos = new FileOutputStream(path+filename);
			
			int data=-1;
			while (true) {				
				data=fis.read();//1byte 읽기
				if(data==-1) {
//					JOptionPane.showMessageDialog(this,"성공");
					System.out.println("성공");
					break;//더이상 데이터가 없다면 멈춤
				}
				fos.write(data);//1byte출력	
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
//			JOptionPane.showMessageDialog(this,"파일을 찾을 수 없어요");
			
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			/*finally는 try문이나 catch문을 수행한 실행부가 반드
			 * 시 거쳐야할 영역
			 * 따라서 무언가를 마무리 짓거나 자원을 해체시켜야 할 경
			 * 우 finally가 사용된다*/
			if(fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(fos !=null) {
				try {				
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}		
			}
		}
		
		
	}
	public void fileSend() {
		
	}
	public static void main(String[] args) {
		new FileSave();
	}
}
