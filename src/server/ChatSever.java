package server;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Chatting.ManageLoggingIn;

public class ChatSever extends JFrame implements Runnable {
	JPanel p_north;
	JTextField t_port;
	JButton bt;
	JTextArea area;
	JScrollPane scroll;

	ServerSocket server;
	Thread thread;

	
	
//	-----------------DB
	DbManager dbManager;//db클래스
	Connection con;//모든 자식들이 접근 할 수 있도록 선언
	//몇명이 들어올지 예측 할 수 없기 때문에 여러개의 메시지 객체를 보유하려면 배열보다는 리스트, 백터가 더 낫다
	Vector<MessageObj> clientList = new Vector<MessageObj>();

	public ChatSever() {
		System.out.println(ManageLoggingIn.myPrimarykey);
		p_north = new JPanel();
		t_port = new JTextField("7777", 10);
		bt = new JButton("서버 가동");
		area = new JTextArea();
		scroll = new JScrollPane(area);
		
//		----DB
		dbManager = DbManager.getInstance();//db클래스 호출 
		//오라클 접속
		con = dbManager.getConnection();
		if(con==null) {
			JOptionPane.showMessageDialog(this,"데이터베이스 접속실패");
		}else {
//			JOptionPane.showMessageDialog(this,"데이터베이스 접속성공");
		} 

		p_north.setPreferredSize(new Dimension(500, 50));
		p_north.setBackground(Color.YELLOW);

		p_north.add(t_port);
		p_north.add(bt);

		add(p_north, BorderLayout.NORTH);
		add(scroll);

		setVisible(true);
		setSize(300, 500);
		

//		dbManager.inputChat(server,msg);
//		dbManager.outputChat(ChatA.this);
		bt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				thread = new Thread(ChatSever.this);
				thread.start();

			}
		});
		//윈도우 종료시 접속해제
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				dbManager.closeDB(con);//DB접속해제
				System.exit(0);//프로세스종료
			}
		});
	}

	public void startServer() {
		int port = Integer.parseInt(t_port.getText());
		try {
			server = new ServerSocket(port);

			area.append("서버 사용자 기다리는중...\n");

			while (true) {
				Socket socket = server.accept();

				String ip = socket.getInetAddress().getHostAddress();
				area.append(ip + " 에서 접속함\n");
				MessageObj messageObj = new MessageObj(this,socket);
				messageObj.start();
				clientList.add(messageObj);
				area.append("현재 접속한 클라이언트 수" + clientList.size()+"\n");

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	


	public void run() {
		startServer();
	}

	public static void main(String[] args) {
		new ChatSever();
	}
}
