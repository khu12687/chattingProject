package Chat;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import Chatting.ChatApp;
import Chatting.ManageLoggingIn;
import Chatting.PageChat;
import Friend.ManageFriend;
import Friend.ModelFriend;
import functions.ChangePw;
import server.DbManager;
import table.Group_Member;

public class ManageChat extends PageChat {
//	ManageFriend managefri
	
	JPanel p_center;
	JPanel p_west;
	JPanel p_south;
	JPanel p_north;
	JPanel p_ad; // ÇÏ´Ü±¤°í
	JButton bt_Friend; // Ä£±¸
	JButton bt_Chat; // Ã¤ÆÃ
	JButton bt_More; // ´õº¸±â!
	JButton bt_Imo; // ÀÌ¸ðÆ¼ÄÜ ¼¥
	JButton bt_Inform; // ¾Ë¸²
	JButton bt_Setting; // ¼³Á¤
	//---------------¼­ÂÊÆÐ³Î----------------------------

	JPanel p_container;// ¼¾ÅÍÆÐ³Î °¨½Ò ÆÐ³Î
	JPanel p_content; // ¶óº§°ú Ä£±¸Ãß°¡¹öÆ° ºÙÀÏ ÆÐ³Î
	JPanel p_north1; // ï¿½óº§°ï¿½ Ä£ï¿½ï¿½ï¿½ß°ï¿½ï¿½ï¿½Æ° ï¿½ï¿½ï¿½ï¿½ ï¿½Ð³ï¿½
	JPanel p_north2; // ï¿½Ì¸ï¿½ï¿½Ë»ï¿½ï¿½ï¿½ ï¿½ï¿½ ï¿½Ö´ï¿½ jtextfield ï¿½ï¿½ï¿½ï¿½ ï¿½Ð³ï¿½\
	JPanel p_south1; // ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½î°? ï¿½ï¿½.
	JLabel la_title;
	JButton bt_add;
	JTextField f_find;
	JPanel p_friend;
	public JScrollPane scroll;
	ModelFriend modelFriend;
	ManageFriend manageFriend;
	
	
	//ï¿½Ì°ï¿½ ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½ï¿???
	Image img;
	Label la_name;
	Label la_message;
	ArrayList<Group_Member> group_memberList = new ArrayList<Group_Member>();
	ArrayList<CardChat> cardChat = new ArrayList<CardChat>();
	
//---------------ï¿½ï¿½ï¿½ï¿½ï¿½Ð³ï¿½----------------------------
	

	

	public ManageChat(ChatApp chatApp, String title, int width, int height, Color color, boolean showFlag) {
		super(chatApp, title, width, height, color, showFlag);
		this.chatApp = chatApp;
		setTitle(title);
		setColor(color);
		setWidth(width);
		setHeight(height);

		p_ad = new JPanel();// ÇÏ´Ü ±¤°í
		p_center = new JPanel(new BorderLayout());
		p_west = new JPanel(new BorderLayout());
		p_south = new JPanel(); // p_west¿¡ ºÙÀÏ ÆÐ³Î --Ä£±¸, Ã¤ÆÃ, ´õº¸±â
		p_north = new JPanel();// p_west¿¡ ºÙÀÏ ÆÐ³Î --Ä£±¸, Ã¤ÆÃ, ´õº¸±â
		bt_Friend = new JButton("Ä£±¸");
		bt_Chat = new JButton("Ã¤ÆÃ");
		bt_More = new JButton("´õº¸±â");
		bt_Imo = new JButton("ÀÌ¸ðÆ¼ÄÜ¼¥");
		bt_Inform = new JButton("¾Ë¸²");
		bt_Setting = new JButton("¼³Á¤");
		//---------------¼­ÂÊÆÐ³Î ¼±¾ð----------------------------
		modelFriend = new ModelFriend();
		p_friend = new JPanel();
		scroll = new JScrollPane(p_friend);
		p_container = new JPanel(new BorderLayout());
		p_content = new JPanel(new BorderLayout());
		p_north1 = new JPanel(new BorderLayout());
		p_north2 = new JPanel();
		p_south1 = new JPanel();
		la_title = new JLabel("Ã¤ÆÃ");
		bt_add = new JButton("»õ·Î¿îÃ¤ÆÃ");
		f_find = new JTextField(10);
//---------------¼¾ÅÍÆÐ³Î ¼±¾ð----------------------------

		p_north.add(bt_Friend);
		p_north.add(bt_Chat);
		p_north.add(bt_More);
		p_south.add(bt_Imo);
		p_south.add(bt_Inform);
		p_south.add(bt_Setting);
		p_north.setPreferredSize(new Dimension(50, 220));
		p_south.setPreferredSize(new Dimension(50, 120));

		p_west.add(p_north, BorderLayout.NORTH);
		p_west.add(p_south, BorderLayout.SOUTH);
		p_west.setPreferredSize(new Dimension(100, 450));
		p_center.setPreferredSize(new Dimension(300, 380));
		p_center.setBackground(Color.GREEN);
		p_ad.setPreferredSize(new Dimension(350, 100));
		p_ad.setBackground(Color.PINK);
		p_friend.setPreferredSize(new Dimension(330	,0));

		//---------------¼­ÂÊÆÐ³Î Á¶¸³----------------------------

		p_north1.add(la_title, BorderLayout.WEST);
		p_north1.add(bt_add, BorderLayout.EAST);
		p_north1.add(f_find, BorderLayout.SOUTH);
		p_north1.setPreferredSize(new Dimension(300, 70));
		//---------------¼­ÂÊÆÐ³Î Á¶¸³----------------------------

		// 350,550

		p_content.add(p_north1, BorderLayout.NORTH);
		p_container.add(p_content, BorderLayout.NORTH);
		p_container.add(scroll);
		add(p_west, BorderLayout.WEST);
		add(p_container, BorderLayout.CENTER);
		add(p_ad, BorderLayout.SOUTH);

		
		
		//Ä£±¸¹öÆ°¿¡ ¸®½º³Ê
		bt_Friend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chatApp.pages[1].setVisible(true);
				chatApp.pages[2].setVisible(false);
				chatApp.pages[3].setVisible(false);
//				chatApp.manageFriend.friend();
//				chatApp.manageFriend.p_friend
				
			}
		});
		
		//¼³Á¤ ¹öÆ°¿¡ ¾×¼Ç ¸®½º³Ê
		bt_Setting.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ChangePw changePw = new ChangePw(ManageChat.this);
			}
		});
		
		getChatList();
		
		//Ã¤ÆÃ¹öÆ°¿¡ ¾×¼Ç ¸®½º³Ê
		bt_Chat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				for (int i = 0; i < cardChat.size(); i++) {
					p_friend.remove(cardChat.get(i));
				}
				group_memberList.removeAll(group_memberList);
//				friendList.removeAll(friendList);
				System.out.println(group_memberList.size());
				updateUI();
				getChatList();
			}
		});
		
		
		//ÅØ½ºÆ®ÇÊµå¿¡ ÈùÆ®ÁÖ±â
		  String hint = "Search Name";
		  Font gainFont = new Font("verdana", Font.PLAIN, 11);  
		  Font lostFont = new Font("Tahoma", Font.ITALIC, 11);
		  f_find.setText(hint);
		  f_find.setFont(lostFont);
		  f_find.setForeground(Color.GRAY);

		f_find.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(f_find.getText().equals(hint)) {
					f_find.setText("");
					f_find.setFont(gainFont);
					f_find.setForeground(Color.BLACK);
				}else {
					f_find.setText(f_find.getText());  
					setFont(gainFont);  
				}
			}
			
			@Override
			public void focusLost(FocusEvent e) {
		        if (f_find.getText().equals(hint)|| f_find.getText().length()==0) {  
		        	f_find.setText(hint);  
		            setFont(lostFont);  
		            setForeground(Color.GRAY);  
		          } else {  
		        	  f_find.setText(f_find.getText());  
		        	  f_find.setFont(gainFont);  
		        	  setForeground(Color.BLACK);  
		          }  
			}
		});
		
		this.setBackground(color);
		this.setPreferredSize(new Dimension(width, height));
		setVisible(showFlag);
	}
	
	
	
	
	public void getChatList() {
		String sql = "select * from group_member WHERE member_num = ? ";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			
			pstmt = chatApp.con.prepareStatement(sql);
			pstmt.setInt(1, ManageLoggingIn.myPrimarykey);
			
			rs = pstmt.executeQuery();

			// Ä¿¼­¸¦ ³»·Á°¡¸é¼­ VO¿¡ ·¹ÄÚµå¸¦ ´ãÀÚ!!
			// List¸¦ »ý¼ºÇÏ¿© ¾Æ·¡ÀÇ VOµéÀ» Â÷°îÂ÷°î ³Ö¾îµÎÀÚ!!
			// ±×·¡¾ß TableModel¿¡¼­ ¹è¿­Ã³·³ »ç¿ëÇÒ ¼ö ÀÖÀ¸´Ï±î!!
			
			
			while (rs.next()) {// Ä¿¼­ ÇÑÄ­ ÀÌµ¿!!
				Group_Member group_member = new Group_Member();
				// ¹è¿­ÀÇ ÀÎµ¦½º°¡ ¾Æ´Ï¶ó, º¯¼ö¸íÀ» ÀÌ¿ëÇÏ¹Ç·Î ÈÎ¾À Á÷°üÀûÀÌ´Ù!!
				group_member.setGroup_member_num(rs.getInt("group_member_num"));
				group_member.setGroup_room_num(rs.getInt("group_room_num"));
				group_member.setMember_num(rs.getInt("member_num"));
				
				group_memberList.add(group_member); // ¿Ï¼ºµÈ VO¸¦ ¸®½ºÆ®¿¡ ´©Àû½ÃÅ°ÀÚ!!

				CardChat card = new CardChat(this);
				cardChat.add(card);
				p_friend.add(card);
			}
//			System.out.println("´ã°ÜÁø ÃÑ ÀÎ¿øÀº : " + friendList.size());
			
			// »ý¼ºµÈ ¸®½ºÆ®¸¦ TableModel¿¡°Ô Àü´ÞÇÏÀÚ!!
//			modelFriend.list = group_memberList;
			p_friend.setPreferredSize(new Dimension(320,group_memberList.size()*75));
			p_friend.revalidate();

			

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbManager.getInstance().closeDB(pstmt, rs);
		}
	}
}


