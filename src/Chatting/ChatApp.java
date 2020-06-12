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
	
	public PagePrimary[] pages = new PagePrimary[4]; // �α���ȭ�� 2��, ä�� ȭ�� 3�� �� 5��
	public DbManager DbManager;
	static public Connection con; // �гε� �� ���������� ��� ������ �� �ֵ��� �����س���!!
	
	boolean hasAuth = false; // �α����� ��� true, ���Ѱ�� false
	Object obj;
	


	public ChatApp() {
//		p_content = new JPanel(new BorderLayout());
		p_content = new JPanel();
		//������ ����
//		pages[0] = new ManageLogin(this, "", 450, 550, Color.DARK_GRAY, false, true);
		pages[0] = new ManageLoggingIn(this, "", 450, 550, Color.CYAN, true,true);

		
		
		//task1. ��� �г��� �ƹ� ���� ���� p_content�� �ٿ� ������
		//				�޵��� ũ�� ���� panel�� Ŀ������ �ʴ´�.
		//				������ ����Ѵ�.
		
		//������ ����
		//�α��� �Ǹ� �α��� �Ⱥ��̰� �ϴ� ������ manageLoggingin�� �α��� �ɶ� ����.
		p_content.add(pages[0]);
//		p_content.add(pages[1]);

		
		add(p_content);
		
		
		
		//���Ӱ����� 
		DbManager = DbManager.getInstance();
		
		pack(); // ������ ���빰���� �ɱ׶���!!
		setVisible(true);
		setMinimumSize( new Dimension(460,510) ); //������ �ּһ����� ����
		
		
		// �ӽ������� ����
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		//����Ŭ ����
		con = DbManager.getConnection();
		if(con==null) {
			JOptionPane.showMessageDialog(this, "DB���� ����");
		}else {
			//JOptionPane.showMessageDialog(this, "DB���� ����");
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


//task1. ��� �г� �������� ���̸� �ȵ�. �гΤ� ���� ���������.
//task2. ģ�� ��Ͽ��� �г� ũ�⸦ ģ������ū*50 �ϸ� �Ǵµ� ģ������ �� ���� ��� ���ұ�?