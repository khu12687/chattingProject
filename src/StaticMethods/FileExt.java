package StaticMethods;

public class FileExt {

	public static String getExt(String path) {
		int lastIndex = path.lastIndexOf("\\");//escape ��Ű�� Ư�����ڰ�
		
		String filename = path.substring(lastIndex+1,path.length());
		
		lastIndex = filename.lastIndexOf(".");
		String ext = filename.substring(lastIndex+1,filename.length());
		
		return ext;//ȣ���ڿ��� ��� ����
	}
	public static String getFilename(String path) {
		int lastIndex = path.lastIndexOf("\\");
		String filename = path.substring(lastIndex+1,path.length());
		
		return filename;//ȣ���ڿ��� ��� ����
	}

}