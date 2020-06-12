package Friend;

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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import Chatting.ChatApp;
import Chatting.MainChatWithJFrame;
import Chatting.PageChat;
import calendar.MyCal;
import functions.Addfriend;
import table.Friend;
import table.Profile;

public class ManageFriend extends PageChat {

	

	JPanel p_center;
	JPanel p_west;
	JPanel p_south;
	JPanel p_north;
	JPanel p_ad; // ÇÏ´Ü±¤°í
	JButton bt_friend; // Ä£±¸
	JButton bt_Chat; // Ã¤ÆÃ
	JButton bt_More; // ´õº¸±â
	JButton bt_Imo; // ÀÌ¸ðÆ¼ÄÜ ¼¥
	JButton bt_calendar; // Ä¶¸°´õ
	JButton bt_Setting; // ¼³Á¤
	// ---------------¼­ÂÊÆÐ³Î----------------------------

	JPanel p_container;// ¼¾ÅÍÆÐ³Î °¨½Ò ÆÐ³Î
	JPanel p_content; // ¶óº§°ú Ä£±¸Ãß°¡¹öÆ° ºÙÀÏ ÆÐ³Î
	JPanel p_north1; // ï¿½óº§°ï¿½ Ä£ï¿½ï¿½ï¿½ß°ï¿½ï¿½ï¿½Æ° ï¿½ï¿½ï¿½ï¿½ ï¿½Ð³ï¿½
	JPanel p_north2; // ï¿½Ì¸ï¿½ï¿½Ë»ï¿½ï¿½ï¿½ ï¿½ï¿½ ï¿½Ö´ï¿½ jtextfield ï¿½ï¿½ï¿½ï¿½ ï¿½Ð³ï¿½\
	JPanel p_south1; // ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½î°? ï¿½ï¿½.
	JLabel la_title;
	JButton bt_add;
	JTextField f_find;
	JPanel p_friend;
	JScrollPane scroll;
	ModelFriend modelFriend;

	// ï¿½Ì°ï¿½ ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½ï¿???
	Image img;
	Label la_name;
	Label la_message;
	ArrayList<Friend> friendList = new ArrayList<Friend>();
	ArrayList<Profile> profileList = new ArrayList<Profile>();
	ArrayList<CardFriend> cardList = new ArrayList<CardFriend>();

	int dw = 0;
	Thread thread;
	String msg;

//---------------ï¿½ï¿½ï¿½ï¿½ï¿½Ð³ï¿½----------------------------

	public ManageFriend(ChatApp chatApp, String title, int width, int height, Color color, boolean showFlag) {
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

		bt_calendar = new JButton("Ä¶¸°´õ");
		bt_Chat = new JButton("Ã¤ÆÃ");
		bt_More = new JButton("´õº¸±â");
		bt_Imo = new JButton("ÀÌ¸ðÆ¼ÄÜ¼¥");
		bt_friend = new JButton("Ä£±¸");
		bt_Setting = new JButton("¼³Á¤");
		// ---------------¼­ÂÊÆÐ³Î ¼±¾ð----------------------------
		modelFriend = new ModelFriend();
		p_friend = new JPanel();
		scroll = new JScrollPane(p_friend);
		p_container = new JPanel(new BorderLayout());
		p_content = new JPanel(new BorderLayout());
		p_north1 = new JPanel(new BorderLayout());
		p_north2 = new JPanel();
		p_south1 = new JPanel();
		la_title = new JLabel("Ä£±¸");
		bt_add = new JButton("Ä£±¸Ãß°¡");
		f_find = new JTextField(10);// Ä£±¸°Ë»ö
//---------------¼¾ÅÍÆÐ³Î ¼±¾ð----------------------------

		p_north.add(bt_friend);
		p_north.add(bt_Chat);
		p_north.add(bt_More);
		p_south.add(bt_Imo);
		p_south.add(bt_calendar);
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
		p_friend.setPreferredSize(new Dimension(330, 0));

		// ---------------¼­ÂÊÆÐ³Î Á¶¸³----------------------------

		p_north1.add(la_title, BorderLayout.WEST);
		p_north1.add(bt_add, BorderLayout.EAST);
		p_north1.add(f_find, BorderLayout.SOUTH);// Ä£±¸°Ë»ö
		p_north1.setPreferredSize(new Dimension(300, 70));
		// ---------------¼­ÂÊÆÐ³Î Á¶¸³----------------------------

		// 350,550

		p_content.add(p_north1, BorderLayout.NORTH);
		p_container.add(p_content, BorderLayout.NORTH);
		p_container.add(scroll);
		add(p_west, BorderLayout.WEST);
		add(p_container, BorderLayout.CENTER);
		add(p_ad, BorderLayout.SOUTH);

		getList();

		// Ä£±¸Ãß°¡¹öÆ°¿¡ ¸®½º³Ê
		bt_add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (chatApp.addfriend == null) {
					chatApp.addfriend = new Addfriend(chatApp);
				} else {
					chatApp.addfriend.dispose();
					chatApp.addfriend = new Addfriend(chatApp);
				}
			}
		});

		// Ä£±¸¹öÆ°¿¡ ¸®½º³Ê
		bt_friend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				scroll.getVerticalScrollBar().setValue(0);
				
				friend();
			}
		});

		// Ä¶¸°´õ¹öÆ°¿¡ ¸®½º³Ê
		bt_calendar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chatApp.mc == null) {
					chatApp.mc = new MyCal(chatApp);
				} else {
					chatApp.mc.dispose();
					chatApp.mc = new MyCal(chatApp);
				}

			}
		});

		// Ã¤ÆÃ¹öÆ° ´­·¶À»¶§ ´­·¶À» ¶§ Ã¤ÆÃÃ¢ ¶ßµµ·Ï ¼³Á¤;
		bt_Chat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chatApp.pages[1].setVisible(false);
				chatApp.pages[2].setVisible(true);
				chatApp.pages[3].setVisible(false);
//				chatApp.manageChat.getChatList();
//				new MainChatWithJFrame(chatApp);
			}
		});

		// ÅØ½ºÆ®ÇÊµå¿¡ ÈùÆ®ÁÖ±â
		String hint = "Search Name";
		Font gainFont = new Font("µ¸¿ò", Font.PLAIN, 11);
		Font lostFont = new Font("±¼¸²", Font.ITALIC, 11);
		f_find.setText(hint);
		f_find.setFont(lostFont);
		f_find.setForeground(Color.GRAY);

		f_find.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent e) {
				if (f_find.getText().equals(hint)) {
					f_find.setText("");
					f_find.setFont(gainFont);
					f_find.setForeground(Color.BLACK);
				} else {
					f_find.setText(f_find.getText());
					setFont(gainFont);
				}
			}

			public void focusLost(FocusEvent e) {
				if (f_find.getText().equals(hint) || f_find.getText().length() == 0) {
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
		f_find.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				msg = f_find.getText();
				System.out.println(msg);

				for (int i = 0; i < cardList.size(); i++) {
					p_friend.remove(cardList.get(i));
				}
				cardList.removeAll(cardList);
				friendList.removeAll(friendList);
				System.out.println(cardList.size());
				updateUI();
				if (msg.equals("")) {
					getList();
				} else {

					String sql = "SELECT * FROM FRIEND JOIN profile ON friend.YOU =PROFILE.MEMBER_NUM where me=? AND profile.PROFILE_NICKNAME like '%'||?||'%' order by profile.PROFILE_NICKNAME asc";

					PreparedStatement pstmt = null;
					ResultSet rs = null;
					try {
						pstmt = chatApp.con.prepareStatement(sql);

						pstmt.setInt(1, 1);// id
						pstmt.setString(2, msg);// id
						rs = pstmt.executeQuery();

						// Ä¿¼­¸¦ ³»·Á°¡¸é¼­ VO¿¡ ·¹ÄÚµå¸¦ ´ãÀÚ!!
						// List¸¦ »ý¼ºÇÏ¿© ¾Æ·¡ÀÇ VOµéÀ» Â÷°îÂ÷°î ³Ö¾îµÎÀÚ!!
						// ±×·¡¾ß TableModel¿¡¼­ ¹è¿­Ã³·³ »ç¿ëÇÒ ¼ö ÀÖÀ¸´Ï±î!!

						while (rs.next()) {// Ä¿¼­ ÇÑÄ­ ÀÌµ¿!!
							Friend friend = new Friend();
							Profile profile = new Profile();
							// ¹è¿­ÀÇ ÀÎµ¦½º°¡ ¾Æ´Ï¶ó, º¯¼ö¸íÀ» ÀÌ¿ëÇÏ¹Ç·Î ÈÎ¾À Á÷°üÀûÀÌ´Ù!!
							friend.setFriend_num(rs.getInt("friend_num"));
							friend.setMe(rs.getInt("me"));
							friend.setYou(rs.getInt("you"));

							profile.setProfile_num(rs.getInt("profile_num"));
							profile.setMember_num(rs.getInt("member_num"));
							profile.setProfile_photo(rs.getString("profile_photo"));
							profile.setProfile_bg(rs.getString("profile_bg"));
							profile.setProfile_nickname(rs.getString("profile_nickname"));
							profile.setProfile_status(rs.getString("profile_status"));

							friendList.add(friend); // ¿Ï¼ºµÈ VO¸¦ ¸®½ºÆ®¿¡ ´©Àû½ÃÅ°ÀÚ!!
							profileList.add(profile);

							CardFriend card = new CardFriend(friend, profile,friend.getYou());
							cardList.add(card);
							p_friend.add(card);
						}
						modelFriend.list = friendList;
						p_friend.setPreferredSize(new Dimension(320, friendList.size() * 75));
						p_friend.revalidate();
					} catch (SQLException a) {
						a.printStackTrace();
					} finally {
						chatApp.DbManager.closeDB(pstmt, rs);
					}
				}

			}
		});

//		thread = new Thread() {
//			public void run() {

//			}
//
//		};

		this.setBackground(color);
		this.setPreferredSize(new Dimension(width, height));
		this.setVisible(true);

	}
	
	public void friend() {
		for (int i = 0; i < cardList.size(); i++) {
			p_friend.remove(cardList.get(i));
		}
		cardList.removeAll(cardList);
		friendList.removeAll(friendList);
		System.out.println(cardList.size());
		updateUI();
		getList();
	}

	// Ä£±¸Ãß°¡¹öÆ°
	public void getList() {
		String sql = "SELECT * FROM FRIEND JOIN profile ON friend.YOU =PROFILE.MEMBER_NUM where me=? order by profile.PROFILE_NICKNAME asc";

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = chatApp.con.prepareStatement(sql);

			pstmt.setInt(1, 1);// id
			rs = pstmt.executeQuery();

			// Ä¿¼­¸¦ ³»·Á°¡¸é¼­ VO¿¡ ·¹ÄÚµå¸¦ ´ãÀÚ!!
			// List¸¦ »ý¼ºÇÏ¿© ¾Æ·¡ÀÇ VOµéÀ» Â÷°îÂ÷°î ³Ö¾îµÎÀÚ!!
			// ±×·¡¾ß TableModel¿¡¼­ ¹è¿­Ã³·³ »ç¿ëÇÒ ¼ö ÀÖÀ¸´Ï±î!!
			System.out.println("getList" + cardList.size());
			int count = 0;
			while (rs.next()) {// Ä¿¼­ ÇÑÄ­ ÀÌµ¿!!
				count++;
				System.out.println(count);
				Friend friend = new Friend();
				Profile profile = new Profile();
				// ¹è¿­ÀÇ ÀÎµ¦½º°¡ ¾Æ´Ï¶ó, º¯¼ö¸íÀ» ÀÌ¿ëÇÏ¹Ç·Î ÈÎ¾À Á÷°üÀûÀÌ´Ù!!
				friend.setFriend_num(rs.getInt("friend_num"));
				friend.setMe(rs.getInt("me"));
				friend.setYou(rs.getInt("you"));

				profile.setProfile_num(rs.getInt("profile_num"));
				profile.setMember_num(rs.getInt("member_num"));
				profile.setProfile_photo(rs.getString("profile_photo"));
				profile.setProfile_bg(rs.getString("profile_bg"));
				profile.setProfile_nickname(rs.getString("profile_nickname"));
				profile.setProfile_status(rs.getString("profile_status"));

				friendList.add(friend); // ¿Ï¼ºµÈ VO¸¦ ¸®½ºÆ®¿¡ ´©Àû½ÃÅ°ÀÚ!!
				profileList.add(profile);

				CardFriend card = new CardFriend(friend, profile,friend.getYou());
				cardList.add(card);
				p_friend.add(card);
			}
			System.out.println("´ã°ÜÁø ÃÑ ÀÎ¿øÀº : " + friendList.size());

			// »ý¼ºµÈ ¸®½ºÆ®¸¦ TableModel¿¡°Ô Àü´ÞÇÏÀÚ!!
			modelFriend.list = friendList;
			p_friend.setPreferredSize(new Dimension(320, friendList.size() * 75));
			p_friend.revalidate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			chatApp.DbManager.closeDB(pstmt, rs);
		}
	}

}
