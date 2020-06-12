package StaticMethods;

/*���ϰ� ���õ� �ߺ��� �۾��� ������ִ� Ŭ���� ����!*/
public class FileManager {
	// ������ Ȯ���� ���ϱ�
	// Ȯ���ڸ� ���ϱ� ���� ��θ� ȣ��� �ѱ�� ��
	public static String getExt(String path) {
		// String path ="C:\\Users\\berry\\java_workspace\\20200511\\res\\pika.jpg";
		// 1�ܰ� : ���ϸ� �����ϱ�
		// ������ ������ ��������, �������� ���ϸ�
		int lastIndex = path.lastIndexOf("\\");// escape ��Ű�� Ư�����ڰ�
		// ����� Ż���Ͽ� �׳� �Ϲݹ��ڰ� �Ǿ� ������.
		String filename = path.substring(lastIndex + 1, path.length());

		// 2�ܰ� : ���ϸ��� ������� Ȯ���ڸ� ��������
		// ���� ������ ������ ������ ����
		lastIndex = filename.lastIndexOf(".");
		String ext = filename.substring(lastIndex + 1, filename.length());

		return ext;// ȣ���ڿ��� ��� ����
	}

}
