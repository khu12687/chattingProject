package server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

import javax.swing.ImageIcon;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import Chatting.MainChatWithJFrame;
import Chatting.ManageLoggingIn;

public class ClientThread extends Thread {
	Socket socket;
	BufferedReader buffr;
	BufferedWriter buffw;
	MainChatWithJFrame mainChat;
	ImageIcon icon;
	public ClientThread(MainChatWithJFrame mainChat, Socket socket) {
		this.mainChat = mainChat;
		this.socket = socket;

		try {
			buffr = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			buffw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// 서버에 메시지 보내기
	public void send(String requestType) {
		try {
			buffw.write(requestType + "\n");
			buffw.flush();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 챗a에 메세지 보내기 챗a입장에서 인풋
	public void listen() {
		String type = null;
		String msg = null;
		String pathName = null;
		int user = 0;
		JSONParser parser = new JSONParser();
		try {
			type = buffr.readLine();

			String requestType = Protocol.parse(type);
			if (requestType.equals("msg")) {// 대화를 원하면..
				try {
					JSONObject jsonObject = (JSONObject) parser.parse(type);
					long q = (Long) jsonObject.get("user");
					user = (int) q;
					msg = (String) jsonObject.get("log");

					mainChat.createMsg(msg);

				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else if(requestType.equals("Emo")) {
				try {
					JSONObject jsonObject = (JSONObject)parser.parse(type);
					long q = (Long) jsonObject.get("user");
					user = (int) q;
					pathName = (String) jsonObject.get("log");
					
					mainChat.setPath(pathName);
					mainChat.createMsg(icon);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void run() {
		while (true) {
			listen();
		}
	}
}
