package functions;

import java.awt.BorderLayout;
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
import javax.swing.JTextField;
import javax.swing.plaf.DimensionUIResource;

import Chatting.ChatApp;
import server.DbManager;

//ȸ������â
public class SignUp extends JFrame {
	JLabel la_signup;//ȸ������ ��ǳ��
	JPanel container;//��ü �����̳�
	JPanel p_west; //�󺧵� ���� �г�
	JLabel la_name, la_id, la_pw, la_email, la_phone;
	JPanel p_center; //�ؽ�Ʈ�ʵ� ���� �г�
	JTextField t_name, t_id, t_email, t_phone;
	JPasswordField t_pw, t_pw_check; //��й�ȣ Ȯ���� ����
	JPanel p_east;//�ߺ�üũ ��ư ���� �г�
	JButton bt_check;//���̵� �ߺ�üũ��ư
	JButton bt_check2;//��й�ȣ �ߺ�üũ��ư
	JPanel p_check;// �Ʒ��� ��ư ���� �г�
	JButton bt_signup, bt_cancel;//�ۼ��Ϸ�,���
	
	ChatApp chatApp;
	
	boolean id_check=false;//�ߺ�üũ �ʱⰪ false
	boolean pw_check=false;//�ߺ�üũ �ʱⰪ false
	

	
	
	
	public SignUp(ChatApp chatApp) {
		this.chatApp = chatApp;
		la_signup = new JLabel("ȸ�����Ծ��");
		container = new JPanel(new BorderLayout());
		p_west = new JPanel();
		la_name = new JLabel("�� ��");
		la_id = new JLabel("ID");
		la_pw = new JLabel("PW");
		la_email = new JLabel("E-MAIL");
		la_phone = new JLabel("PHONE");
		p_center = new JPanel();
		t_name = new JTextField(12);
		t_id = new JTextField(12);
		t_email = new JTextField(12);
		t_phone = new JTextField(12);
		t_pw = new JPasswordField(12);
		t_pw_check = new JPasswordField(12);
		p_east = new JPanel();
		bt_check = new JButton("�ߺ�üũ");
		bt_check2 = new JButton("�ߺ�üũ");
		p_check = new JPanel();
		bt_signup = new JButton("�ۼ��Ϸ�");
		bt_cancel = new JButton("���");
		
		
		//��Ÿ��
		la_signup.setFont(new Font("����",Font.BOLD,20));
		la_signup.setPreferredSize(new Dimension(200, 60));
		la_signup.setHorizontalAlignment(JLabel.CENTER);
		container.setPreferredSize(new Dimension(420, 400));
		container.setBackground(Color.YELLOW);
		p_west.setPreferredSize(new Dimension(60,400));
		p_west.setLayout(null);
		p_center.setPreferredSize(new Dimension(260, 400));
		p_center.setLayout(null);
		p_east.setPreferredSize(new Dimension(90,400));
		p_east.setLayout(null);
		p_check.setPreferredSize(new DimensionUIResource(420, 40));
		//p_check.setBackground(Color.CYAN);
		
		//�󺧵����� =>�ڵ��ߺ����� �޼���
		setlabelSize(la_name, 0);
		setlabelSize(la_id, 70);
		setlabelSize(la_pw, 140);
		setlabelSize(la_email, 250);
		setlabelSize(la_phone, 320);
		
		//�ؽ�Ʈ�ʵ� ��ġ
		t_name.setBounds(0, 0, 270, 30);
		t_id.setBounds(0, 70, 270, 30);
		t_pw.setBounds(0, 140, 270, 30);
		t_pw_check.setBounds(0, 180, 270, 30);
		t_email.setBounds(0, 250, 270, 30);
		t_phone.setBounds(0, 320, 270, 30);
		
		//��ư ��ġ
		bt_check.setBounds(0, 70, 90, 30);
		bt_check2.setBounds(0, 180, 90, 30);
		
		//����
		add(la_signup);
		
		p_west.add(la_name);
		p_west.add(la_id);
		p_west.add(la_pw);
		p_west.add(la_email);
		p_west.add(la_phone);
		container.add(p_west,BorderLayout.WEST);
		
		p_center.add(t_name);
		p_center.add(t_id);
		p_center.add(t_pw);
		p_center.add(t_pw_check);
		p_center.add(t_email);
		p_center.add(t_phone);
		container.add(p_center, BorderLayout.CENTER);
		
		p_east.add(bt_check);
		p_east.add(bt_check2);
		container.add(p_east, BorderLayout.EAST);
		
		add(container);
		
		p_check.add(bt_signup);
		p_check.add(bt_cancel);
		add(p_check);
		
		setLayout(new FlowLayout());
		setSize(450, 550);
		setVisible(true);
		setLocationRelativeTo(null);
		setResizable(false);
		
		
		//��ư�� ������ ����
		//���̵� �ߺ�üũ
		bt_check.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//System.out.println("�ߺ�üũ");
				doubleCheck();
			}
		});
		
		//��й�ȣ �ߺ�üũ
		bt_check2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String pw1 = new String(t_pw.getPassword());
				String pw2 = new String(t_pw_check.getPassword());
				if(pw1.equals(pw2)) {
					
					pw_check=true;
					JOptionPane.showMessageDialog(SignUp.this, "Ȯ�� �Ǿ����ϴ�");
				} else {
					pw_check=false;
					JOptionPane.showMessageDialog(SignUp.this, "��й�ȣ�� �ٽ� Ȯ�����ּ���");
				}
			}
		});
		//ȸ������ ��ư
		bt_signup.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//System.out.println("ȸ������");
				if(id_check&&pw_check) { //�ߺ�üũ ��ư ��� ������ Ȯ���ϸ� ����
					signUpMemeber(SignUp.this);
				} else { //�ߺ�üũ��ư �ϳ��� �ȵǸ� ����
					JOptionPane.showMessageDialog(SignUp.this, "�ߺ�üũ Ȯ�� ���ּ���");
				}
			}
		});
		
		bt_cancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//System.out.println("���");
				SignUp.this.setVisible(false); //â �Ⱥ��̵��� ����
			}
		});
		
	}
	
	//�� ������ ����
	public void setlabelSize(JLabel label, int y) { 
		label.setBounds(0, y, 60, 30);
		label.setHorizontalAlignment(JLabel.CENTER);
		
	}
	
	//���̵� �ߺ��˻�
	public void doubleCheck() {
		String sql = "SELECT * FROM member WHERE member_id=?" ;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = chatApp.con.prepareStatement(sql);
			pstmt.setString(1, t_id.getText());
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				t_id.setText("");
				id_check=false;
				JOptionPane.showMessageDialog(this, "���̵� �ߺ��Ǿ����ϴ�");
			} else {
				id_check=true;
				JOptionPane.showMessageDialog(this, "��밡���� ���̵� �Դϴ�.");
			} 
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbManager.getInstance().closeDB(pstmt, rs);
		}
	}
	
	//ȸ������
	public void signUpMemeber(SignUp signUp) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String member_name=null; //���̵� ������  member���̺� �̸� ����
		
		try {
			chatApp.con.setAutoCommit(false);
			
			//member ���̺� insert
			String sql1 = "insert into member(member_num, member_name, member_id, member_pass, member_email, member_phone)";
			sql1+=" values (seq_member.nextval,?,?,?,?,?)";
			
			pstmt = chatApp.con.prepareStatement(sql1);
			pstmt.setString(1, t_name.getText());
			pstmt.setString(2, t_id.getText());
			pstmt.setString(3, new String(t_pw.getPassword()));
			pstmt.setString(4, t_email.getText());
			pstmt.setString(5, t_phone.getText());
			
			pstmt.executeUpdate();
			
			//���̵� ����
			String sql2 = "select * from member where member_id=?";
			
			pstmt = chatApp.con.prepareStatement(sql2);
			pstmt.setString(1, t_id.getText());
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				member_name = rs.getString("member_name");
			}
			
			//������ ���̺� insert
			String sql3 = "insert into profile(profile_num, member_num,profile_photo,profile_bg,profile_nickname,profile_status)";
			sql3+=" values (seq_profile.nextval,seq_member.currval,'pf.jpg','bg.jpg',?,?)";
			
			pstmt = chatApp.con.prepareStatement(sql3);
			pstmt.setString(1, member_name);
			pstmt.setString(2, "���¸޼����� �����ּ���");
			
			int result = pstmt.executeUpdate();
			
			if(result==0) {
				JOptionPane.showMessageDialog(this, "���Խ���");
			} else {
				chatApp.con.commit();
				JOptionPane.showMessageDialog(this, "ȸ�������� ���ϵ帳�ϴ�");
				id_check=false;
				pw_check=false;
//				signUp.setVisible(false);
				signUp.dispose();
				
	
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this, "���� ������ �����Ѱ� �����Ƿ�, ��� ���� �����մϴ�");
			textBlank();//�Է�â ����
			e.printStackTrace();
			try {
				chatApp.con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			
		} finally {
			DbManager.getInstance().closeDB(pstmt, rs);
		}
		
		
	}
	
	//�Է�â �������� �����
	public void textBlank() {
		t_name.setText("");
		t_id.setText("");
		t_pw.setText("");
		t_pw_check.setText("");
		t_email.setText("");
		t_phone.setText("");
	}
	
	
	
}
