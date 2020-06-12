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
	DbManager dbManager;//dbŬ����
	Connection con;//��� �ڽĵ��� ���� �� �� �ֵ��� ����
	//����� ������ ���� �� �� ���� ������ �������� �޽��� ��ü�� �����Ϸ��� �迭���ٴ� ����Ʈ, ���Ͱ� �� ����
	Vector<MessageObj> clientList = new Vector<MessageObj>();

	public ChatSever() {
		System.out.println(ManageLoggingIn.myPrimarykey);
		p_north = new JPanel();
		t_port = new JTextField("7777", 10);
		bt = new JButton("���� ����");
		area = new JTextArea();
		scroll = new JScrollPane(area);
		
//		----DB
		dbManager = DbManager.getInstance();//dbŬ���� ȣ�� 
		//����Ŭ ����
		con = dbManager.getConnection();
		if(con==null) {
			JOptionPane.showMessageDialog(this,"�����ͺ��̽� ���ӽ���");
		}else {
//			JOptionPane.showMessageDialog(this,"�����ͺ��̽� ���Ӽ���");
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
		//������ ����� ��������
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				dbManager.closeDB(con);//DB��������
				System.exit(0);//���μ�������
			}
		});
	}

	public void startServer() {
		int port = Integer.parseInt(t_port.getText());
		try {
			server = new ServerSocket(port);

			area.append("���� ����� ��ٸ�����...\n");

			while (true) {
				Socket socket = server.accept();

				String ip = socket.getInetAddress().getHostAddress();
				area.append(ip + " ���� ������\n");
				MessageObj messageObj = new MessageObj(this,socket);
				messageObj.start();
				clientList.add(messageObj);
				area.append("���� ������ Ŭ���̾�Ʈ ��" + clientList.size()+"\n");

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
