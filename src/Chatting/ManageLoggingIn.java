package Chatting;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import functions.FindInfo;
import functions.SignUp;
import server.DbManager;

//ÀÚµ¿·Î±×ÀÎ »óÅÂ¿¡¼­ ºñ¹Ð¹øÈ£ ÀÔ·ÂÇÏ¸é ¶ß´Â ·Î±×ÀÎ È­¸é
public class ManageLoggingIn extends PageLogin{
	JPanel loginBox;
	JPanel form; // ·Î±×ÀÎ Æû
	JLabel la_id;
	JLabel la_pw;
	JTextField t_id;
	JPasswordField t_pw;
	JButton bt_regist;
	//-----------------------------------
	JPanel info;
	JButton bt_findInfo;
	JButton bt_newAccount;
	ChatApp chatApp;
	
	public static int myPrimarykey; // ³ªÀÇ ÇÁ¶óÀÌ¸Ó¸® Å° ÀÓ½ÃÀúÀå
	
	public ManageLoggingIn(ChatApp chatApp, String title, int width, int height, 
												Color color, boolean showFlag,boolean reSize) { // ï¿½Î¸ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½ï¿½Ñ´ï¿½!!
		super(chatApp,title, width,height,color,showFlag, reSize);
		
		this.chatApp=chatApp;
		loginBox = new JPanel();
		form = new JPanel();
		la_id = new JLabel("ID");
		la_pw = new JLabel("Password");
		t_id = new JTextField("suyou881", 10);
		t_pw = new JPasswordField(10);
		bt_regist = new JButton("Login");		
		info = new JPanel();
		bt_findInfo = new JButton("Find Info");
		bt_newAccount = new JButton("NewAccount");
		
		//½ºÅ¸ÀÏ Àû¿ë
		loginBox.setBackground(Color.WHITE);
		loginBox.setPreferredSize(new Dimension(250,170));
		
		//·¹ÀÌ¾Æ¿ôº¯°æ
		form.setLayout(new GridLayout(2,2));
		
		//ï¿½ï¿½ï¿½ï¿½
		form.add(la_id);
		form.add(t_id);
		form.add(la_pw);
		form.add(t_pw);

		loginBox.add(form);
		loginBox.add(bt_regist);
//		loginBox.add(new JPanel().add(loginBox));
		
		
		info.add(bt_findInfo);
		info.add(bt_newAccount);

		add(loginBox);

		add(info);
		
		chatApp.setResizable(reSize);
		//·Î±×ÀÎ ¹öÆ°¿¡ ¸®½º³Ê ¿¬°á!!
		bt_regist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loginCheck();
			}
		});
		bt_findInfo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FindInfo findInfo = new FindInfo(chatApp);
			}
		});

		bt_newAccount.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SignUp signUp = new SignUp(chatApp);

			}
		});
	}
	
	public void loginCheck() {
		// ? ¹°À½Ç¥ Ç¥±â¹ýÀÇ ¿ªÈ° : ¹ÙÀÎµå º¯¼ö¶ó ÇÑ´Ù...
		// µ¥ÀÌÅÍº£ÀÌ½º ¼º´ÉÇâ»óÀ» À§ÇØ »ç¿ë..
		String sql = "select * from member where member_id=? and member_pass=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = chatApp.con.prepareStatement(sql);
			pstmt.setString(1, t_id.getText());//id
			pstmt.setString(2, new String(t_pw.getPassword()));//ps
			//System.out.println(t_pw.getText());
			rs = pstmt.executeQuery(); //ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½
			
			if(rs.next()) {
				myPrimarykey = rs.getInt("member_num");//³ªÀÇ ÇÁ¶óÀÌ¸Ó¸® ÀÓ½ÃÀúÀå
				System.out.println(myPrimarykey);
				chatApp.hasAuth=true;
//				JOptionPane.showMessageDialog(this, "·Î±×ÀÎ¿Ï·á");
				//·Î±×ÀÎ µÇ¸é ·Î±×ÀÎ È­¸é ¾Èº¸ÀÌ°Ô ¼³Á¤
				chatApp.loging();
				chatApp.pages[0].setVisible(false);
				chatApp.pages[1].setVisible(true);
				chatApp.pages[2].setVisible(false);
				chatApp.pages[3].setVisible(false);
			}else { // Â÷ÁöÇÏ´Â µ¥ÀÌÅÍ ¾øÀ½
				chatApp.hasAuth=false;
				JOptionPane.showMessageDialog(this, "·Î±×ÀÎ ½ÇÆÐ");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DbManager.getInstance().closeDB(pstmt, rs);
		}
		
	}
	
}