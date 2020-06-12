package functions;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import Chatting.ChatApp;
import Chatting.ManageLoggingIn;
import Chatting.PageChat;
import server.DbManager;

public class ChangePw extends JFrame {
	JLabel la_title;
	
	JPanel container1;
	JLabel la_pw;
	JPasswordField t_pw;
	JButton bt;
	JPanel container2;
	JLabel la_nextPw;
	JPasswordField t_nextPw1;
	JPasswordField t_nextPw2;
	JButton bt_check;
	JButton bt_change;
	
	ChatApp chatApp;
	PageChat pageChat; 
	
	boolean pw_check = false;//중복체크 초기값 false
	boolean nextPw_check = false;//중복체크 초기값 false
	
	
	public ChangePw(PageChat pageChat) {
		this.pageChat = pageChat;
		la_title = new JLabel("비밀번호 변경");
		
		container1 = new JPanel();
		la_pw = new JLabel("현재 비밀번호");
		t_pw = new JPasswordField(15);
		bt = new JButton("확인");
		container2 = new JPanel();
		la_nextPw = new JLabel("변경할 비밀번호");
		t_nextPw1 = new JPasswordField(15);
		t_nextPw2 = new JPasswordField(15);
		bt_check = new JButton("중복체크");
		bt_change = new JButton("변경!");
		
		la_title.setFont(new Font("굴림",Font.BOLD,30));
		la_title.setHorizontalAlignment(JLabel.CENTER);
		la_title.setPreferredSize(new Dimension(400, 50));
		
		container1.setPreferredSize(new Dimension(400, 55));
		container1.setBackground(Color.ORANGE);
		container1.setLayout(null);
		la_pw.setFont(new Font("돋움",Font.BOLD,13));
		la_pw.setHorizontalAlignment(JLabel.LEFT);
		la_pw.setBounds(10, 10, 120, 30);
		t_pw.setBounds(110, 13, 180, 30);
		bt.setBounds(300, 13, 70, 30);
		
		
		container2.setPreferredSize(new Dimension(400,180));
		container2.setBackground(Color.PINK);
		container2.setLayout(null);
		la_nextPw.setFont(new Font("돋움",Font.BOLD,13));
		la_nextPw.setHorizontalAlignment(JLabel.LEFT);
		la_nextPw.setBounds(10, 10, 120, 30);
		t_nextPw1.setBounds(110, 13, 180, 30);
		t_nextPw2.setBounds(110, 53, 180, 30);
		bt_check.setBounds(300, 53, 90, 30);
		bt_change.setBounds(300, 140, 90, 30);
		
		
		add(la_title);
		
		container1.add(la_pw);
		container1.add(t_pw);
		container1.add(bt);
		
		add(container1);
		
		container2.add(la_nextPw);
		container2.add(t_nextPw1);
		container2.add(t_nextPw2);
		container2.add(bt_check);
		container2.add(bt_change);
		
		add(container2);
		
		//버튼 연결
		bt.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				checkPrePw();
				
			}
		});
		bt_check.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				checkPw();
			}
		});
		
		bt_change.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				changePw();
				
			}
		});
		setLayout(new FlowLayout());
		setSize(450, 400);
		setVisible(true);
		setLocationRelativeTo(null);
		setResizable(false);
	}
	
	public void checkPrePw() {
		String sql = "select * from member where member_pass=? and member_num=? ";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = chatApp.con.prepareStatement(sql);
			pstmt.setString(1, new String(t_pw.getPassword()));
			pstmt.setString(2, Integer.toString(ManageLoggingIn.myPrimarykey));
			
			int result = pstmt.executeUpdate();
			if(result==0) {
				pw_check = false;
				JOptionPane.showMessageDialog(this, "비밀번호를 확인해주세요");
			} else {
				pw_check = true;
				JOptionPane.showMessageDialog(this, "인증되었습니다");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbManager.getInstance().closeDB(pstmt, rs);
		}
				
	}
	
	public void checkPw() {
		String pw1 = new String(t_nextPw1.getPassword());
		String pw2 = new String(t_nextPw2.getPassword());
		if(pw1.equals(pw2)) {
			nextPw_check=true;
			JOptionPane.showMessageDialog(this, "확인 되었습니다");
		} else {
			nextPw_check=false;
			JOptionPane.showMessageDialog(this, "비밀번호를 다시 확인해주세요");
		}
		
	}
	
	public void changePw() {
		if((pw_check==false)&&(nextPw_check==false)) {
			JOptionPane.showMessageDialog(this, "비밀번호를 확인해주세요");
		} else if((pw_check==false)&&(nextPw_check==true)) {
			JOptionPane.showMessageDialog(this, "현재 비밀번호를 확인해주세요");
		} else if((pw_check==true)&&(nextPw_check==false)) {
			JOptionPane.showMessageDialog(this, "변경할 비밀번호를 확인해주세요");
		} else { //비밀번호 확인버튼을 확인 후 진행
			
		String sql ="update member set member_pass=? where member_num=?"; //바꿀비밀번호,프라이머리키
		PreparedStatement pstmt = null;
		
		try {
			pstmt = chatApp.con.prepareStatement(sql);
			
			pstmt.setString(1, new String(t_nextPw1.getPassword()));
			pstmt.setString(2, Integer.toString(ManageLoggingIn.myPrimarykey));
			
			int result = pstmt.executeUpdate();
			if(result!=0) {
				JOptionPane.showMessageDialog(this, "입력하신 비밀번호로 변경되었습니다");
				ChangePw.this.setVisible(false);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbManager.getInstance().closeDB(pstmt);
		}
		
		
	}
	
	}
}
	


