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

//회원가입창
public class SignUp extends JFrame {
	JLabel la_signup;//회원가입 말풍선
	JPanel container;//전체 컨테이너
	JPanel p_west; //라벨들 붙일 패널
	JLabel la_name, la_id, la_pw, la_email, la_phone;
	JPanel p_center; //텍스트필드 붙일 패널
	JTextField t_name, t_id, t_email, t_phone;
	JPasswordField t_pw, t_pw_check; //비밀번호 확인을 위해
	JPanel p_east;//중복체크 버튼 붙일 패널
	JButton bt_check;//아이디 중복체크버튼
	JButton bt_check2;//비밀번호 중복체크버튼
	JPanel p_check;// 아랫쪽 버튼 붙일 패널
	JButton bt_signup, bt_cancel;//작성완료,취소
	
	ChatApp chatApp;
	
	boolean id_check=false;//중복체크 초기값 false
	boolean pw_check=false;//중복체크 초기값 false
	

	
	
	
	public SignUp(ChatApp chatApp) {
		this.chatApp = chatApp;
		la_signup = new JLabel("회원가입양식");
		container = new JPanel(new BorderLayout());
		p_west = new JPanel();
		la_name = new JLabel("이 름");
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
		bt_check = new JButton("중복체크");
		bt_check2 = new JButton("중복체크");
		p_check = new JPanel();
		bt_signup = new JButton("작성완료");
		bt_cancel = new JButton("취소");
		
		
		//스타일
		la_signup.setFont(new Font("굴림",Font.BOLD,20));
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
		
		//라벨디자인 =>코드중복으로 메서드
		setlabelSize(la_name, 0);
		setlabelSize(la_id, 70);
		setlabelSize(la_pw, 140);
		setlabelSize(la_email, 250);
		setlabelSize(la_phone, 320);
		
		//텍스트필드 위치
		t_name.setBounds(0, 0, 270, 30);
		t_id.setBounds(0, 70, 270, 30);
		t_pw.setBounds(0, 140, 270, 30);
		t_pw_check.setBounds(0, 180, 270, 30);
		t_email.setBounds(0, 250, 270, 30);
		t_phone.setBounds(0, 320, 270, 30);
		
		//버튼 위치
		bt_check.setBounds(0, 70, 90, 30);
		bt_check2.setBounds(0, 180, 90, 30);
		
		//부착
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
		
		
		//버튼과 리스너 연결
		//아이디 중복체크
		bt_check.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//System.out.println("중복체크");
				doubleCheck();
			}
		});
		
		//비밀번호 중복체크
		bt_check2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String pw1 = new String(t_pw.getPassword());
				String pw2 = new String(t_pw_check.getPassword());
				if(pw1.equals(pw2)) {
					
					pw_check=true;
					JOptionPane.showMessageDialog(SignUp.this, "확인 되었습니다");
				} else {
					pw_check=false;
					JOptionPane.showMessageDialog(SignUp.this, "비밀번호를 다시 확인해주세요");
				}
			}
		});
		//회원가입 버튼
		bt_signup.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//System.out.println("회원가입");
				if(id_check&&pw_check) { //중복체크 버튼 모두 눌러서 확인하면 실행
					signUpMemeber(SignUp.this);
				} else { //중복체크버튼 하나라도 안되면 실행
					JOptionPane.showMessageDialog(SignUp.this, "중복체크 확인 해주세요");
				}
			}
		});
		
		bt_cancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//System.out.println("취소");
				SignUp.this.setVisible(false); //창 안보이도록 설정
			}
		});
		
	}
	
	//라벨 사이즈 변경
	public void setlabelSize(JLabel label, int y) { 
		label.setBounds(0, y, 60, 30);
		label.setHorizontalAlignment(JLabel.CENTER);
		
	}
	
	//아이디 중복검사
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
				JOptionPane.showMessageDialog(this, "아이디가 중복되었습니다");
			} else {
				id_check=true;
				JOptionPane.showMessageDialog(this, "사용가능한 아이디 입니다.");
			} 
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbManager.getInstance().closeDB(pstmt, rs);
		}
	}
	
	//회원가입
	public void signUpMemeber(SignUp signUp) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String member_name=null; //아이디 생성시  member테이블 이름 저장
		
		try {
			chatApp.con.setAutoCommit(false);
			
			//member 테이블에 insert
			String sql1 = "insert into member(member_num, member_name, member_id, member_pass, member_email, member_phone)";
			sql1+=" values (seq_member.nextval,?,?,?,?,?)";
			
			pstmt = chatApp.con.prepareStatement(sql1);
			pstmt.setString(1, t_name.getText());
			pstmt.setString(2, t_id.getText());
			pstmt.setString(3, new String(t_pw.getPassword()));
			pstmt.setString(4, t_email.getText());
			pstmt.setString(5, t_phone.getText());
			
			pstmt.executeUpdate();
			
			//아이디 추출
			String sql2 = "select * from member where member_id=?";
			
			pstmt = chatApp.con.prepareStatement(sql2);
			pstmt.setString(1, t_id.getText());
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				member_name = rs.getString("member_name");
			}
			
			//프로필 테이블에 insert
			String sql3 = "insert into profile(profile_num, member_num,profile_photo,profile_bg,profile_nickname,profile_status)";
			sql3+=" values (seq_profile.nextval,seq_member.currval,'pf.jpg','bg.jpg',?,?)";
			
			pstmt = chatApp.con.prepareStatement(sql3);
			pstmt.setString(1, member_name);
			pstmt.setString(2, "상태메세지를 적어주세요");
			
			int result = pstmt.executeUpdate();
			
			if(result==0) {
				JOptionPane.showMessageDialog(this, "가입실패");
			} else {
				chatApp.con.commit();
				JOptionPane.showMessageDialog(this, "회원가입을 축하드립니다");
				id_check=false;
				pw_check=false;
//				signUp.setVisible(false);
				signUp.dispose();
				
	
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this, "세부 업무중 실패한게 있으므로, 모두 원상 복귀합니다");
			textBlank();//입력창 공백
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
	
	//입력창 공백으로 만들기
	public void textBlank() {
		t_name.setText("");
		t_id.setText("");
		t_pw.setText("");
		t_pw_check.setText("");
		t_email.setText("");
		t_phone.setText("");
	}
	
	
	
}
