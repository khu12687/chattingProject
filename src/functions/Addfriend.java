package functions;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import Chatting.ChatApp;
import Chatting.ManageLoggingIn;
import Chatting.ResizeImageIcon;
import server.DbManager;
//친구추가
public class Addfriend extends JFrame {
	JLabel la_title;
	JTabbedPane tab;
	JPanel container1;
	JPanel p_left1, p_right1;
	JLabel la_name, la_phone;
	JTextField t_name, t_phone;
	JButton bt_find1;
	JLabel la_hint1;
	JPanel container2;
	JPanel p_left2, p_right2;
	JLabel la_id;
	JTextField t_id;
	JButton bt_find2;
	JLabel la_hint2;
	
	JPanel p_show1,p_show2;
	JPanel p_profile1, p_profile2;
	JLabel la_show_nickname1, la_show_nickname2;
	JLabel la_show_status1, la_show_status2;
	JPanel p_south;
	JButton bt_add;
	
	ChatApp chatApp;
	String dest="C:/Users/berry/java_workspace/ChatProject/data/profile/";
	JLabel label1;//첫번째 탭 프로필 이미지 사이즈 변경
	JLabel label2;//두번째 탭 프로필 이미지 사이즈 변경

	int member_num1;//첫번째 탭에서 친구 검색 후 나온 member의 프라이머리키
	int member_num2;//두번째 탭에서 친구 검색 후 나온 member의 프라이머리키
	int tab_key;
	String nickname1;//첫번째 탭에서 친구 검색 후 나온 닉네임
	String nickname2;//첫번째 탭에서 친구 검색 후 나온 닉네임
	
	public Addfriend(ChatApp chatApp) {
		this.chatApp = chatApp;
		la_title = new JLabel("친구 추가?");
		tab = new JTabbedPane();
		
		container1 = new JPanel();
		p_left1 = new JPanel();
		p_right1 = new JPanel();
		la_name = new JLabel("친구이름");
		la_phone = new JLabel("전화번호");
		t_name = new JTextField(12);
		t_phone = new JTextField(12);
		bt_find1 = new JButton("검색");
		la_hint1 = new JLabel("친구의 이름과 전화번호를 입력해 주세요");
				
		container2 = new JPanel();
		p_left2 = new JPanel();
		p_right2 = new JPanel();
		la_id = new JLabel("ID");
		t_id = new JTextField(15);
		bt_find2 = new JButton("검색");
		la_hint2 = new JLabel("메신져 ID를 입력해 주세요");
		
		
		p_show1 = new JPanel();
		p_profile1 = new JPanel();
		la_show_nickname1 = new JLabel();
		la_show_status1 = new JLabel();
		p_show2 = new JPanel();
		p_profile2 = new JPanel();
		la_show_nickname2 = new JLabel();
		la_show_status2 = new JLabel();
		
		p_south = new JPanel(new BorderLayout());
		bt_add = new JButton("친구 추가");
		
		//?뒪???씪
		la_title.setFont(new Font("굴림",Font.BOLD,20));
		la_title.setHorizontalAlignment(JLabel.LEFT);
		la_title.setPreferredSize(new Dimension(350, 30));
		
		container1.setPreferredSize(new Dimension(350, 250));
		p_left1.setPreferredSize(new Dimension(80,80));
		//p_left1.setBackground(Color.YELLOW);
		p_left1.setLayout(null);
		p_right1.setPreferredSize(new Dimension(250,80));
		//p_right1.setBackground(Color.BLUE);
		p_right1.setLayout(null);
		
		la_name.setHorizontalAlignment(JLabel.CENTER);
		la_name.setBounds(0, 10, 80, 20);
		la_phone.setHorizontalAlignment(JLabel.CENTER);
		la_phone.setBounds(0, 50, 80, 20);
		
		t_name.setBounds(0, 10, 250, 20);
		t_phone.setBounds(0, 50, 250, 20);
		
		container2.setPreferredSize(new Dimension(350, 250));
		p_left2.setPreferredSize(new Dimension(80,80));
		//p_left2.setBackground(Color.YELLOW);
		p_left2.setLayout(null);
		p_right2.setPreferredSize(new Dimension(250,80));
		//p_right2.setBackground(Color.BLUE);
		p_right2.setLayout(null);
		
		la_id.setHorizontalAlignment(JLabel.CENTER);
		la_id.setBounds(0, 30, 80, 20);
		
		t_id.setBounds(0, 30, 200, 20);
		
		la_hint1.setPreferredSize(new Dimension(250, 30));
		la_hint1.setHorizontalAlignment(JLabel.CENTER);
		la_hint2.setPreferredSize(new Dimension(250, 30));
		la_hint2.setHorizontalAlignment(JLabel.CENTER);
		
		p_show1.setLayout(null);
		p_show1.setPreferredSize(new Dimension(350, 200));
		//p_show1.setBackground(Color.RED);
		
		p_profile1.setBounds(20, 20, 80, 80);
		la_show_nickname1.setHorizontalAlignment(JLabel.LEFT);
		la_show_nickname1.setBounds(120, 35, 220, 20);
		la_show_status1.setHorizontalAlignment(JLabel.LEFT);
		la_show_status1.setBounds(120, 70, 220, 20);
		
		p_show2.setLayout(null);
		p_show2.setPreferredSize(new Dimension(350, 200));
		//p_show2.setBackground(Color.RED);
		
		p_profile2.setBounds(20, 20, 80, 80);
		la_show_nickname2.setHorizontalAlignment(JLabel.LEFT);
		la_show_nickname2.setBounds(120, 35, 220, 20);
		la_show_status2.setHorizontalAlignment(JLabel.LEFT);
		la_show_status2.setBounds(120, 70, 220, 20);
		
		p_south.setPreferredSize(new Dimension(350, 30));
		
		
		add(la_title);
		
		p_left1.add(la_name);
		p_left1.add(la_phone);
		p_right1.add(t_name);
		p_right1.add(t_phone);
		container1.add(p_left1);
		container1.add(p_right1);
		container1.add(la_hint1);
		container1.add(bt_find1);
		
		p_show1.add(p_profile1);
		p_show1.add(la_show_nickname1);
		p_show1.add(la_show_status1);
		
		container1.add(p_show1);
		
		tab.add("연락처로 추가",container1);
		
		
		p_left2.add(la_id);
		p_right2.add(t_id);
		container2.add(p_left2);
		container2.add(p_right2);
		container2.add(la_hint2);
		container2.add(bt_find2);
		
		p_show2.add(p_profile2);
		p_show2.add(la_show_nickname2);
		p_show2.add(la_show_status2);
		
		container2.add(p_show2);
		
		tab.add("ID로 추가",container2);
		
		
		add(tab);
		
		p_south.add(bt_add,BorderLayout.EAST);
		add(p_south);
		
	
		//버튼연결
		//연락처로 추가 탭 검색 버튼
		bt_find1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("연락처로 검색");
				findPhone();
			}
		});
		//ID로 추가 탭 검색 버튼
		bt_find2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("ID로 검색");
				findId();
			}
		});
		
		//최종 친구 추가 버튼
		bt_add.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//addFriend();
				System.out.println("친구 추가");
				friendCheck();
			}
		});
		//어느 탭을 누르는지 확인하는 용도
		tab.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				System.out.println("현재 선택한 탭 번호는 : "+tab.getSelectedIndex());
				tab_key = tab.getSelectedIndex(); //0번은 '연락처로 추가' 값, 1번은 'id로 추가' 값
			}
		});
		
		
	
		setLayout(new FlowLayout());
		setSize(400,400);
		setVisible(true);
		setLocationRelativeTo(null);
		setResizable(false);
		
	}
	
	
	public void findPhone() {
		String sql = "select m.member_num,p.profile_photo,p.profile_nickname, p.profile_status from member m, profile p";
		sql+=" where m.member_num = p.member_num and m.member_name=? and m.member_phone=?";
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		
		try {
			pstmt =chatApp.con.prepareStatement(sql);
			pstmt.setString(1, t_name.getText());
			pstmt.setString(2, t_phone.getText());
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				System.out.println("친구찾기 성공");
				
				member_num1 = rs.getInt("member_num");// 첫번째 탭 프라이머리키 임시저장
				
				//프로필 사진 화면에 보여주기
				showProfilePhoto1(rs.getString("profile_photo"));
				p_profile1.add(label1);
				p_profile1.updateUI();
				
				//닉네임 상태메세지 보여주기
				nickname1 = rs.getString("profile_nickname");
				String status = rs.getString("profile_status");
				
				la_show_nickname1.setText("닉네임 : "+nickname1);
				if(status!=null) { //null값이 아니면 상태메시지 출력
					la_show_status1.setText("상태 : "+status);
				} else {//null값이면 빈칸으로 출력
					la_show_status1.setText("상태 : ");
				}
				
				
			} else {
				System.out.println("실패");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DbManager.getInstance().closeDB(pstmt, rs);
		}
		
	}
	
	//member&profile 테이블 조인해서 가져오기
	public void findId() {
		String sql = "select m.member_num, p.profile_photo,p.profile_nickname, p.profile_status from member m, profile p";
		sql+=" where m.member_num = p.member_num and m.member_id=?";
		PreparedStatement pstmt=null;
		ResultSet rs = null;

		
		try {
			pstmt = chatApp.con.prepareStatement(sql);
			pstmt.setString(1, t_id.getText());
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				System.out.println("친구찾기 성공");
				System.out.println(rs.getString("profile_photo"));
				System.out.println(rs.getString("profile_nickname"));
				System.out.println(rs.getString("profile_status"));
				
				member_num2 = rs.getInt("member_num");// 두번째 탭 프라이머리키 임시저장
				
				//프로필 사진 화면에 보여주기
				showProfilePhoto2(rs.getString("profile_photo"));
				p_profile2.add(label2);
				p_profile2.updateUI();
				
				//닉네임 상태메세지 보여주기
				nickname2 = rs.getString("profile_nickname");
				String status = rs.getString("profile_status");
				la_show_nickname2.setText("닉네임 : "+nickname2);
				if(status!=null) { //null값이 아니면 상태메시지 출력
					la_show_status2.setText("상태 : "+status);
				} else {//null값이면 빈칸으로 출력
					la_show_status2.setText("상태 : ");
				}
			} else {
				System.out.println("실패");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DbManager.getInstance().closeDB(pstmt, rs);
		}
		
	}
	//첫번째 탭 프로필
	public void showProfilePhoto1(String fileName) {
		Icon i = new ImageIcon();
		i = ResizeImageIcon.resizeImageIcon(dest+fileName, 80, 80);
		label1 = new JLabel(i);
}
	//두번째 탭 프로필
	public void showProfilePhoto2(String fileName) {
			Icon i = new ImageIcon();
			i = ResizeImageIcon.resizeImageIcon(dest+fileName, 80, 80);
			label2 = new JLabel(i);
	}
	
	
	
	//친구추가 할때 친구로 이미 되어 있으면x, 친구상태가 아니면 친구추가 진행
	public void friendCheck() {
		//나의 정보와 검색한 정보와 같다면=나 자신을 검색한 것=> 친구추가x
		if(Integer.toString(ManageLoggingIn.myPrimarykey).equals(Integer.toString(member_num1))) {
			JOptionPane.showMessageDialog(this, "자신을 친구추가 할 수 없습니다");
		} else if (Integer.toString(ManageLoggingIn.myPrimarykey).equals(Integer.toString(member_num2))) {
			JOptionPane.showMessageDialog(this, "자신을 친구추가 할 수 없습니다");
		} else {
			String sql = "select * from friend where me=? and you=?";
			PreparedStatement pstmt=null;
			ResultSet rs = null;
			
			try {
				pstmt = chatApp.con.prepareStatement(sql);
				
				//나의 프라이머리 키
				pstmt.setString(1, Integer.toString(ManageLoggingIn.myPrimarykey));
				
				//검색한 친구의 프라이머리 키
				if(tab_key==0) {//1번째 탭 클릭했을때
					pstmt.setString(2, Integer.toString(member_num1));
				} else if(tab_key==1) {//2번째 탭 클릭했을때
					pstmt.setString(2, Integer.toString(member_num2));
				}
		
				rs=pstmt.executeQuery();
				
				if(rs.next()) { //데이터 있음=> 이미 친구로 추가 되어 있음!
					JOptionPane.showMessageDialog(this, "이미 친구추가 되어 있습니다");
				} else { //데이터 없음 => 친구추가 해야함
					addFriend();
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DbManager.getInstance().closeDB(pstmt, rs);
			}
		}
	
	}
	
	//친구추가
		public void addFriend() {
			String sql ="insert into friend(friend_num,me,you)";
			sql+=" values(seq_friend.nextval,?,?)";
			PreparedStatement pstmt = null;
			
			try {
				pstmt = chatApp.con.prepareStatement(sql);
				
				
				pstmt.setString(1, Integer.toString(ManageLoggingIn.myPrimarykey));
				
				//검색한 친구의 프라이머리 키
				if(tab_key==0) {//1번째 탭 클릭했을때
					pstmt.setString(2, Integer.toString(member_num1));
				} else if(tab_key==1){//2번째 탭 클릭했을때
					pstmt.setString(2, Integer.toString(member_num2));
				}
				int result = pstmt.executeUpdate();
				if(result!=0) {
					
					if(tab_key==0) {//1번째 탭 클릭했을때
						JOptionPane.showMessageDialog(this, nickname1+ " 님이 친구추가 되었습니다");
					} else if(tab_key==1){//2번째 탭 클릭했을때
						JOptionPane.showMessageDialog(this, nickname2+ " 님이 친구추가 되었습니다");
					}
					
				}
			
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DbManager.getInstance().closeDB(pstmt);
			}
			
		}
	
}
