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

	// �̹���
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
	
	//-------------------��������
	Socket client;//��Ĺ����
	public ClientThread clientThread;//Ŭ���̾�Ʈ ������ Ŭ��������
	int port=7777;
	String ip="172.30.1.58";
	int count=0;

	JFrame f_Imo; // �̸�Ƽ�� ������

	

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

		// �ؽ�Ʈ����� �Ʒ��ʿ� ��ũ�ѹ� �����ϴ� �ڵ�
//		scrollBar = new JScrollBar(JScrollBar.HORIZONTAL);
//		p_area.setLayout(new BoxLayout(p_area, BoxLayout.Y_AXIS));
//		BoundedRangeModel brm = t_input.getHorizontalVisibility();
//		scrollBar.setModel(brm);

		p_area = new JPanel(new BorderLayout());
		bt_input = new JButton("����");
		bt_imo = new JButton("�̸�Ƽ��");
		bt_attach = new JButton("��������");
		bt_call = new JButton("������ȭ");
		bt_cal = new JButton("��Ķ����");
		bt_cap = new JButton("ĸ��");

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

		// south�� �ؽ�Ʈ�ʵ�� �޴���ư ���α� ����
		p_south = new JPanel(new BorderLayout());

		// ���̱�
		p_south.add(p_input, BorderLayout.CENTER);
		p_south.add(p_menu, BorderLayout.SOUTH);

		add(p_south, BorderLayout.SOUTH);

		// ������
		p_content.setBackground(Color.white);
		
		
		// �ؽ�Ʈ �ʵ忡 �̺�Ʈ ������
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
		

		// ������ �ؽ�Ʈ�� �����ϴ� ����.
		// getContentPane Ŭ������ ���� Ŭ������ �ִ� ��Ű���� ���� �־�� �ȴ�.
		scroll_main.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		getContentPane().add(scroll_main, BorderLayout.CENTER);

		add(scroll_main);
		setPreferredSize(new Dimension(480, 600)); // ī�� ũ�⸸ŭ ����
		pack();
		setLocationRelativeTo(null);
//		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		setMinimumSize(this.getSize()); // 420, 450 ������� �پ���� �ȴ´�.
		
		connect();
		
		bt_imo.addActionListener(this);

		// �����ӿ� ��������Ŀ�� ������ ����
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
		
		//�����ӿ� �����츮���� ����
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				if(emotiFrame!=null) {
					emotiFrame.dispose();
					
				}
			}
		});

		// �����ӿ� ������Ʈ������ ����
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

	// imo ��ư ����
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();

		if (obj == bt_imo) {
			// �̹� â�� �ִ� ���¿��� �ٽ� ������ ������� �ϰ� �ٽ� ��â����.
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
			JOptionPane.showMessageDialog(this,"�����ͺ��̽� ���ӽ���");
		}else {
//			JOptionPane.showMessageDialog(this,"�����ͺ��̽� ���Ӽ���");
		} 
		dbManager.outputChat(MainChatWithJFrame.this,con);
		
		dbManager.closeDB(con);
	}

	public void connect() {

		
		try {
			client = new Socket(ip, port);
			
			
			// �޽��������� ����������,�������� ��ü�� ��������
			clientThread = new ClientThread(MainChatWithJFrame.this, client);
			clientThread.start();

			dbloging();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Ŀ��?");
			if(count<3) {
//				ip="192.168.10.229";
				ip="192.168.0.246";
				count++;
				connect();
			}else {
				JOptionPane.showMessageDialog(this,"��Ʈ��ũ ���� �߻�");
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

//task1 --> text�� �ȼ�ũ�⸦ ���ؼ� ������ ����â���� ����� ��. --> �ذ�
//task2 --> �̹��� â ���� Ŭ���ϸ� ������ �����ؼ� textpane�� ���̱� --> �ذ�
//task3 --> ����ð� �޼ҵ� ����� �󺧿� ����ð� ���̱�.
//task4 --> gif ���� jlabel�� ���̱�. --> �ذ�
//task5 --> �̹��� �ø��� �ð� �Ʒ��� �������� �ϴ°� --> �ذ�

//area ���� �� �ٿ��� �ȵǰ� p �г� ��ü�� ������ �༭ west east ���̸�
//area �г� ��ü�� �ּ� ũ��� �پ�� �׷��� �ȵ�.
//--> jtextpane �̿��غ���.
//if(flag) {
//	area.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
//	p.add(area);
//}else if(!flag){
//	p.add(area, BorderLayout.WEST);
//}
//p�гο� area���
