package server;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Protocol {
	// �Ѱܹ��� ��û ���ڿ��� �м��Ѵ�!! 
	public static String parse(String requestString) {
		String requestType=null;
		JSONParser parser = new JSONParser();
		try {
			JSONObject jsonObject=(JSONObject)parser.parse(requestString);
			requestType=(String)jsonObject.get("requestType");
			
			//���ϴ°� ���������� ���� ó�� ����� Ʋ���� ������ ���� ��ü�鿡�� ��������
			System.out.println(requestType);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return requestType;
	}
	
//	public static void main(String[] args) {
//		StringBuilder sb = new StringBuilder();
//		sb.append("{\n");
//		sb.append("\"requestType\":\"msg\",\n");
//		sb.append("\"user\":\"1\",\n");
//		sb.append("\"log\":\"ä��\"\n");
//		sb.append("}");
//		
//		System.out.println(sb.toString());
//		
//		new Protocol().parse(sb.toString());
//	}
}
