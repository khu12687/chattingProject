package server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

import javax.swing.JTextArea;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class MessageObj extends Thread {
	Socket socket;
	BufferedReader buffr;
	BufferedWriter buffw;
	JTextArea arae;
	ChatSever server;


	public MessageObj(ChatSever server, Socket socket) {
		this.server = server;
		this.socket = socket;

		try {
			//접속과 동시에 대화용 소켓으로 부터 스트림 뽑기
			buffr = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			buffw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void listen() {
		String msg = null;
		String type=null;
		String pathName=null;
		int user=0;
		JSONParser parser = new JSONParser();
		
		try {
			while (true) {
				type = buffr.readLine();
				
				//사이가 중요하다
	
				
				String requestType=Protocol.parse(type);
				if(requestType.equals("msg")) {// 대화를 원하면..
					try {
						JSONObject jsonObject=(JSONObject)parser.parse(type);
						long q=(Long)jsonObject.get("user");
						user=(int)q;
						msg=(String)jsonObject.get("log");
						
						server.dbManager.inputChat(server,msg);
						server.area.append(msg + "\n");
												
						broadCast(type);
						
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else if(requestType.equals("Emo")) {
					try {
						
						
						JSONObject jsonObject=(JSONObject)parser.parse(type);
						long q=(Long)jsonObject.get("user");
						user=(int)q;
						pathName=(String)jsonObject.get("log");
						
						server.dbManager.inputEmo(server,pathName);
						server.area.append(pathName + "\n");
						
						broadCast(type);
						
						
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				
				//db에 채팅로그 넘겨주기
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void broadCast(String msg) {
		for (int i = 0; i < server.clientList.size(); i++) {
			MessageObj obj = server.clientList.get(i);
			obj.send(msg);
		}	
	}
	
	public void send(String log) {
		try {
			buffw.write(log + "\n");
			buffw.flush();
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void run() {
		listen();
	}
}
