package Chatting;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.Connection;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import Chat.ManageChat;
import Icoticon.EmotiFrame;
import Icoticon.EmotiPage;
import server.ClientThread;
import server.DbManager;

public class MainChatWithJFrame extends JFrame implements ActionListener {
	ManageChat manageChat;
	ChatApp chatApp;

	// 이미지
	public ImageIcon icon;
	Icon img;
	private String path;

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public MyChatContentPanel p_content;
	JPanel p_south;
	JScrollPane scroll_main;
	JScrollPane scroll_input;
	public JTextArea t_input;
	JPanel p_area;
	JButton bt_input;
	JButton bt_img;

	JPanel p_input;
	JPanel p_menu;
	JButton bt_imo, bt_attach, bt_call, bt_cal, bt_cap;
	JScrollBar scrollBar;

	public String msg;
	
	boolean dateChanged;
	boolean flag;
	int x;
	
	//-------------------서버연결
	Socket client;//소캣보유
	public ClientThread clientThread;//클라이언트 쓰레드 클레스보유
	int port=7777;
	String ip="172.30.1.58";
	int count=0;

	JFrame f_Imo; // 이모티콘 프레임

	

	EmotiFrame emotiFrame;
	EmotiPage emotiPage;

	public MainChatWithJFrame(ChatApp chatApp) {
		this.chatApp = chatApp;
		path = new String();
		p_content = new MyChatContentPanel(new GridBagLayout());
		scroll_main = new JScrollPane(p_content);
		scroll_main.getVerticalScrollBar().setUnitIncrement(25);

		t_input = new JTextArea(3, 30);
		t_input.setLineWrap(true);
		scroll_input = new JScrollPane(t_input);
		scroll_input.getVerticalScrollBar().setUnitIncrement(10);

		// 텍스트에어리어 아래쪽에 스크롤바 생성하는 코드
//		scrollBar = new JScrollBar(JScrollBar.HORIZONTAL);
//		p_area.setLayout(new BoxLayout(p_area, BoxLayout.Y_AXIS));
//		BoundedRangeModel brm = t_input.getHorizontalVisibility();
//		scrollBar.setModel(brm);

		p_area = new JPanel(new BorderLayout());
		bt_input = new JButton("전송");
		bt_imo = new JButton("이모티콘");
		bt_attach = new JButton("파일전송");
		bt_call = new JButton("무료통화");
		bt_cal = new JButton("톡캘린더");
		bt_cap = new JButton("캡쳐");

		p_input = new JPanel();
		p_menu = new JPanel();

		p_area.add(scroll_input);
//		p_area.add(scrollBar);
		p_input.add(p_area);
		p_input.add(bt_input);
		p_menu.add(bt_imo);
		p_menu.add(bt_attach);
		p_menu.add(bt_call);
		p_menu.add(bt_cal);
		p_menu.add(bt_cap);

		// south쪽 텍스트필드와 메뉴버튼 감싸기 위한
		p_south = new JPanel(new BorderLayout());

		// 붙이기
		p_south.add(p_input, BorderLayout.CENTER);
		p_south.add(p_menu, BorderLayout.SOUTH);

		add(p_south, BorderLayout.SOUTH);

		// 디자인
		p_content.setBackground(Color.white);
		
		
		// 텍스트 필드에 이벤트 리스너
		t_input.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
			int result = e.getKeyCode();
				if (result == KeyEvent.VK_ENTER) {
					msg = t_input.getText();
					
					StringBuilder sb = new StringBuilder();
					sb.append("{");
					sb.append("\"requestType\":\"msg\",");
					sb.append("\"user\":"+ManageLoggingIn.myPrimarykey+",");
					sb.append("\"log\":\""+msg+"\"");
					sb.append("}");			
					clientThread.send(sb.toString());
					
				
					t_input.setText("");
					emotiFrame.pre.dispose();
				}
			}
		});
		

		// 강제로 텍스트를 랩핑하는 과정.
		// getContentPane 클래스가 현재 클래스가 있는 패키지에 같이 있어야 된다.
		scroll_main.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		getContentPane().add(scroll_main, BorderLayout.CENTER);

		add(scroll_main);
		setPreferredSize(new Dimension(480, 600)); // 카톡 크기만큼 조정
		pack();
		setLocationRelativeTo(null);
//		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		setMinimumSize(this.getSize()); // 420, 450 사이즈보다 줄어들지 안는다.
		
		connect();
		
		bt_imo.addActionListener(this);

		// 프레임에 윈도우포커스 리스너 연결
		addWindowFocusListener(new WindowFocusListener() {
			public void windowLostFocus(WindowEvent e) {
				if(emotiFrame!=null) {
					if (emotiFrame.pre != null) {
						emotiFrame.pre.dispose();
					}
				}
			}
			public void windowGainedFocus(WindowEvent e) {
			}
		});
		
		//프레임에 윈도우리스너 연결
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				if(emotiFrame!=null) {
					emotiFrame.dispose();
					
				}
			}
		});

		// 프레임에 컴포넌트리스너 연결
		addComponentListener(new ComponentAdapter() {

			public void componentMoved(ComponentEvent e) {
				if (emotiFrame != null) {
					if (emotiFrame.pre != null) {
						emotiFrame.pre.setBounds(MainChatWithJFrame.this.getX(),
								MainChatWithJFrame.this.getY() + MainChatWithJFrame.this.getHeight() - 300,
								MainChatWithJFrame.this.getWidth(), 187);
					}
				}
			}
			public void componentResized(ComponentEvent e) {
				if (emotiFrame != null) {
					if (emotiFrame.pre != null) {
						emotiFrame.pre.dispose();
					}
				}
			}
		});
	}

	// imo 버튼 연결
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();

		if (obj == bt_imo) {
			// 이미 창이 있는 상태에서 다시 누르면 사라지게 하고 다시 새창생성.
			if (emotiFrame != null) {
				emotiFrame.dispose();
			}
			emotiFrame = new EmotiFrame(this);
			emotiFrame.repaint();
//			emotiFrame.update(g);
			emotiFrame.revalidate();
		}
	}
	
	public void dbloging() {
		DbManager dbManager = DbManager.getInstance();
		Connection con = dbManager.getConnection();
		if(con==null) {
			JOptionPane.showMessageDialog(this,"데이터베이스 접속실패");
		}else {
//			JOptionPane.showMessageDialog(this,"데이터베이스 접속성공");
		} 
		dbManager.outputChat(MainChatWithJFrame.this,con);
		
		dbManager.closeDB(con);
	}

	public void connect() {

		
		try {
			client = new Socket(ip, port);
			
			
			// 메시지접속을 성공했으니,쓰레드인 객체를 생성하자
			clientThread = new ClientThread(MainChatWithJFrame.this, client);
			clientThread.start();

			dbloging();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("커밋?");
			if(count<3) {
//				ip="192.168.10.229";
				ip="192.168.0.246";
				count++;
				connect();
			}else {
				JOptionPane.showMessageDialog(this,"네트워크 문제 발생");
				setVisible(false);
				e.printStackTrace();				
			}		
		}
	}
	
	public void createMsg(ImageIcon icon) {
		this.icon = icon;
		x++;
		ChattingStyleLeft chattingStyleLeft = new ChattingStyleLeft(MainChatWithJFrame.this, flag, x, msg);
		p_content.add(chattingStyleLeft.la_profile, chattingStyleLeft.gbc1);
		p_content.add(chattingStyleLeft.areaName, chattingStyleLeft.gbc2);
		p_content.add(chattingStyleLeft.p, chattingStyleLeft.gbc3);
		p_content.add(chattingStyleLeft.la_time, chattingStyleLeft.gbc4);

		p_content.revalidate();
		chattingStyleLeft.textPane.revalidate();
		MainChatWithJFrame.this.revalidate();

		t_input.setCaretPosition(0);
		chattingStyleLeft.textPane.setCaretPosition(chattingStyleLeft.textPane.getDocument().getLength());
		if (emotiFrame != null) {
			if (emotiFrame.pre != null) {
				emotiFrame.pre.dispatchEvent(new WindowEvent(emotiFrame.pre, WindowEvent.WINDOW_CLOSING));
			}
		}
	}

	public void createMsg(String msg) {
		this.msg=msg;
		x++;
		ChattingStyleLeft chattingStyleLeft = new ChattingStyleLeft(MainChatWithJFrame.this, flag, x,msg);
		p_content.add(chattingStyleLeft.la_profile, chattingStyleLeft.gbc1);
		p_content.add(chattingStyleLeft.areaName, chattingStyleLeft.gbc2);
		p_content.add(chattingStyleLeft.p, chattingStyleLeft.gbc3);
		p_content.add(chattingStyleLeft.la_time, chattingStyleLeft.gbc4);

		p_content.revalidate();
		chattingStyleLeft.textPane.setCaretPosition(chattingStyleLeft.textPane.getDocument().getLength());
	}

}

//task1 --> text의 픽셀크기를 구해서 윈도우 현재창에서 빼줘야 함. --> 해결
//task2 --> 이미지 창 띄우고 클릭하면 사이즈 변경해서 textpane에 붙이기 --> 해결
//task3 --> 현재시간 메소드 만들고 라벨에 현재시간 붙이기.
//task4 --> gif 파일 jlabel에 붙이기. --> 해결
//task5 --> 이미지 올릴때 시간 아래로 떨어지게 하는거 --> 해결

//area 정렬 후 붙여도 안되고 p 패널 자체에 보더를 줘서 west east 불이면
//area 패널 자체가 최소 크기로 줄어듬 그래서 안됨.
//--> jtextpane 이용해보자.
//if(flag) {
//	area.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
//	p.add(area);
//}else if(!flag){
//	p.add(area, BorderLayout.WEST);
//}
//p패널에 area담기
