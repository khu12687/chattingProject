package StaticMethods;

public class FileExt {

	public static String getExt(String path) {
		int lastIndex = path.lastIndexOf("\\");//escape 시키면 특수문자가
		
		String filename = path.substring(lastIndex+1,path.length());
		
		lastIndex = filename.lastIndexOf(".");
		String ext = filename.substring(lastIndex+1,filename.length());
		
		return ext;//호출자에게 결과 전달
	}
	public static String getFilename(String path) {
		int lastIndex = path.lastIndexOf("\\");
		String filename = path.substring(lastIndex+1,path.length());
		
		return filename;//호출자에게 결과 전달
	}

}