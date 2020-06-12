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
		
		int lastIndex = poto.lastIndexOf("\\");//escape ��Ű�� Ư�����ڰ� ����� Ż���Ͽ�
		//�׳� �Ϲ� ���ڰ� �ǹ�����
		filename = poto.substring(lastIndex+1,poto.length());

		try {
			fis = new FileInputStream(poto);
			fos = new FileOutputStream(path+filename);
			
			int data=-1;
			while (true) {				
				data=fis.read();//1byte �б�
				if(data==-1) {
//					JOptionPane.showMessageDialog(this,"����");
					System.out.println("����");
					break;//���̻� �����Ͱ� ���ٸ� ����
				}
				fos.write(data);//1byte���	
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
//			JOptionPane.showMessageDialog(this,"������ ã�� �� �����");
			
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			/*finally�� try���̳� catch���� ������ ����ΰ� �ݵ�
			 * �� ���ľ��� ����
			 * ���� ���𰡸� ������ ���ų� �ڿ��� ��ü���Ѿ� �� ��
			 * �� finally�� ���ȴ�*/
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
