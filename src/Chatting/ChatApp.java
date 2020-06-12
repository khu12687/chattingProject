package Chatting;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Chat.ManageChat;
import Friend.ManageFriend;
import calendar.MyCal;
import functions.Addfriend;
import server.DbManager;

public class ChatApp extends JFrame{
	public MyCal mc;
	public Addfriend addfriend;
	JPanel p_content;
	
	public ManageFriend manageFriend;
	public ManageChat manageChat;
	public ManageMore manageMore;
	
	public PagePrimary[] pages = new PagePrimary[4]; // 로그인화면 2개, 채팅 화면 3개 총 5개
	public DbManager DbManager;
	static public Connection con; // 패널들 즉 페이지들이 모두 접근할 수 있도록 보유해놓자!!
	
	boolean hasAuth = false; // 로그인한 경우 true, 안한경우 false
	Object obj;
	


	public ChatApp() {
//		p_content = new JPanel(new BorderLayout());
		p_content = new JPanel();
		//페이지 구성
//		pages[0] = new ManageLogin(this, "", 450, 550, Color.DARK_GRAY, false, true);
		pages[0] = new ManageLoggingIn(this, "", 450, 550, Color.CYAN, true,true);

		
		
		//task1. 모든 패널을 아무 조건 없이 p_content에 붙여 놓으면
		//				왼도우 크기 따라서 panel이 커지지를 않는다.
		//				조건을 줘야한다.
		
		//페이지 구성
		//로그인 되면 로그인 안보이게 하는 설정은 manageLoggingin에 로그인 될때 줬음.
		p_content.add(pages[0]);
//		p_content.add(pages[1]);

		
		add(p_content);
		
		
		
		//접속관리자 
		DbManager = DbManager.getInstance();
		
		pack(); // 보유한 내용물까지 쪼그라든다!!
		setVisible(true);
		setMinimumSize( new Dimension(460,510) ); //윈도우 최소사이즈 정함
		
		
		// 임시적으로 쓸거
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		//오라클 접속
		con = DbManager.getConnection();
		if(con==null) {
			JOptionPane.showMessageDialog(this, "DB접속 실패");
		}else {
			//JOptionPane.showMessageDialog(this, "DB접속 성공");
		}

		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				DbManager.closeDB(con);
				System.exit(0);
			}
			
			
			
		});
	}
	public void loging() {
		pages[1] = new ManageFriend(ChatApp.this, "", 450, 550, Color.YELLOW, true);
		pages[2] = new ManageChat(ChatApp.this, "", 450, 550, Color.BLUE, false);
		pages[3] = new ManageMore(ChatApp.this, "", 450, 550, Color.RED, false);
		
		p_content.add(pages[1]);
		p_content.add(pages[2]);
		p_content.add(pages[3]);
		
	}
	


	
	public static void main(String[] args) {
		new ChatApp();
	}
}


//task1. 모든 패널 컨텐츠에 붙이면 안됨. 패널ㅇ 붙일 조건줘야함.
//task2. 친구 목록에서 패널 크기를 친구수만큰*50 하면 되는데 친구수를 그 전에 어떻게 구할까?