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
import javax.swing.JPanel;
import javax.swing.JTextField;

import Chatting.ChatApp;

public class FindInfo extends JFrame{
	JLabel la_title;
	
	JPanel container1;
	JLabel la_idTitle;
	JPanel p_info1;
	JLabel la_name;
	JTextField t_name;
	JLabel la_email;
	JTextField t_email;
	JButton bt_send1;
	
	JPanel container2;
	JLabel la_pwTitle;
	JPanel p_info2;
	JLabel la_id;
	JTextField t_id;
	JButton bt_send2;
	
	ChatApp chatApp;
	String member_email; //id찾기 한 결과 이멜일값 임시 저장
	
	
	public FindInfo(ChatApp chatApp) {
		this.chatApp = chatApp;
		la_title = new JLabel("ID & Password 찾기");
		
		container1 = new JPanel();
		la_idTitle = new JLabel("ID찾기");
		p_info1 = new JPanel();
		la_name = new JLabel("이름");
		la_email = new JLabel("email");
		t_name = new JTextField(15);
		t_email = new JTextField(15);
		bt_send1 = new JButton("메일 보내기");
		
		container2 = new JPanel();
		la_pwTitle = new JLabel("Password찾기");
		p_info2 = new JPanel();
		la_id = new JLabel("ID");
		t_id = new JTextField(15);
		bt_send2 = new JButton("메일 보내기");
		
		
		//스타일
		la_title.setFont(new Font("굴림",	 Font.BOLD, 30));
		la_title.setHorizontalAlignment(JLabel.CENTER);
		la_title.setPreferredSize(new Dimension(400,30));
		
		
		
		container1.setPreferredSize(new Dimension(400,240));
		//container1.setBackground(Color.YELLOW);
		container1.setLayout(null);
		la_idTitle.setFont(new Font("돋움",Font.BOLD,15));
		la_idTitle.setHorizontalAlignment(JLabel.CENTER);
		la_idTitle.setBounds(0, 0, 50, 30);
		la_idTitle.setOpaque(true);
		la_idTitle.setBackground(Color.ORANGE);
		
		p_info1.setBounds(0, 30, 400, 170);
		p_info1.setBackground(Color.ORANGE);
		p_info1.setLayout(null);
		la_name.setFont(new Font("돋움",Font.BOLD,15));
		la_name.setHorizontalAlignment(JLabel.LEFT);
		la_name.setBounds(10, 10, 50, 30);
		t_name.setBounds(20, 40, 150, 20);
		la_email.setFont(new Font("돋움",Font.BOLD,15));
		la_email.setHorizontalAlignment(JLabel.LEFT);
		la_email.setBounds(10, 60, 50, 30);
		t_email.setBounds(20, 90, 350, 20);
		bt_send1.setBounds(270, 130, 120, 30);
		
		container2.setPreferredSize(new Dimension(400,200));
		//container2.setBackground(Color.YELLOW);
		container2.setLayout(null);
		la_pwTitle.setFont(new Font("돋움",Font.BOLD,15));
		la_pwTitle.setHorizontalAlignment(JLabel.CENTER);
		la_pwTitle.setBounds(0, 0, 110, 30);
		la_pwTitle.setOpaque(true);
		la_pwTitle.setBackground(Color.PINK);
		
		p_info2.setBounds(0, 30, 400, 130);
		p_info2.setBackground(Color.PINK);
		p_info2.setLayout(null);
		la_id.setFont(new Font("돋움",Font.BOLD,15));
		la_id.setHorizontalAlignment(JLabel.LEFT);
		la_id.setBounds(10, 20, 50, 30);
		t_id.setBounds(20, 50, 150, 20);
		bt_send2.setBounds(270, 90, 120, 30);
		
		
		add(la_title);
		
		p_info1.add(la_name);
		p_info1.add(t_name);
		p_info1.add(la_email);
		p_info1.add(t_email);
		p_info1.add(bt_send1);
		
		container1.add(la_idTitle);
		container1.add(p_info1);
		
		add(container1);
		
		p_info2.add(la_id);
		p_info2.add(t_id);
		p_info2.add(bt_send2);
		
		container2.add(la_pwTitle);
		container2.add(p_info2);
		
		add(container2);
		
		
		
		
		//버튼 연결
		bt_send1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				emailConfirm();
			}
		});
		bt_send2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				idEmailConfirm();
			}
		});
		
		
		
		
		
		setLayout(new FlowLayout());
		setSize(450, 550);
		setVisible(true);
		setLocationRelativeTo(null);
		setResizable(false);
	}
	
	public void emailConfirm() {
		String sql = "select * from member where member_email=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = chatApp.con.prepareStatement(sql);
			pstmt.setString(1, t_email.getText());
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				//JOptionPane.showMessageDialog(this, "입력하신 이멜일로 전송되었습니다");
				//member_email = rs.getString("member_email");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void idEmailConfirm() {
		
	} 


}
