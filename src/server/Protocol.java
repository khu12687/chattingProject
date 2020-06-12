package server;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Protocol {
	// 넘겨받은 요청 문자열을 분석한다!! 
	public static String parse(String requestString) {
		String requestType=null;
		JSONParser parser = new JSONParser();
		try {
			JSONObject jsonObject=(JSONObject)parser.parse(requestString);
			requestType=(String)jsonObject.get("requestType");
			
			//원하는게 무엇인지에 따라 처리 방법이 틀리기 때문에 전용 객체들에게 업무전담
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
//		sb.append("\"log\":\"채팅\"\n");
//		sb.append("}");
//		
//		System.out.println(sb.toString());
//		
//		new Protocol().parse(sb.toString());
//	}
}
