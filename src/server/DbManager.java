package server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import Chatting.MainChatWithJFrame;
import table.MyLog;

public class DbManager {
	static DbManager instance;
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String user = "c##java";
	String password = "1234";
	Connection con = null;
	private DbManager() {
	
	}
	

	public Connection getConnection() {

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(url, user, password);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}

	public void closeDB(Connection con) {
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void closeDB(PreparedStatement pstmt) {
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void closeDB(PreparedStatement pstmt, ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void inputChat(ChatSever server,String msg){
//		String msgA = chatA.t_input.getText();
		
		
		// db에 채팅 올리기
		String sql = "insert into log(log_num,group_member_num,log_chat) values (seq_log.nextval, ?,?)";
//		String sql = "insert into chating_log(chat_log,user_num,chatlog) values (cahtlog.nextval,1,'아쉬울게')";
		
		//1이 유저정보 내 누구인지
		//msg 내가 친 채팅
	
		PreparedStatement pstmt = null;
//		 System.out.println(sql);
		// 쿼리 수행 메서드 호출 DML은 executeUpdate()호출
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1,1);//단톡방 유저 넘버
			if(msg.equals("")) {
				return;
			}else {
			pstmt.setString(2,msg);
			}
			int result = pstmt.executeUpdate();// 실행결과 이 쿼리문에 의해 영향을 받은 레코드 갯수를 반환해줌
			if (result == 0) {
				JOptionPane.showMessageDialog(server, "등록실패");
			} else {
//				JOptionPane.showMessageDialog(server, "등록성공");
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			
		} finally {
			closeDB(pstmt);
			// System.out.println("shopApp : "+shopApp);
			// System.out.println("connectionManager : "+shopApp.connectionManager);
			// System.out.println("pstmt : "+pstmt);
		}
	}

	public void outputChat(MainChatWithJFrame mainChat,Connection con) {
		String sql = "select * from log order by log_num asc";

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt= con.prepareStatement(sql);
			rs=pstmt.executeQuery();//결과담기
			
			//한번당 하나의 레코드
			while (rs.next()) {
				// 채팅로그 클래스
				MyLog myLog= new MyLog();

				// rs에서 채팅로그로 옮겨심자

				myLog.setLog_chat(rs.getString("log_chat"));
				String msg = myLog.getLog_chat();
				//아래문장 수정필요
//				chatApp.area.append(chating_log.getChatlog()+"\n");
				mainChat.createMsg(msg);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeDB(pstmt, rs);
		}
	}
	
	public void inputEmo(ChatSever server,String pathName) {
		String sql = "insert into log_emoticon(log_emoticon_num ,group_member_num,log_chat) values (seq_log.nextval, ?,?)";
	}
	
	
	public static DbManager getInstance() {
		if(instance==null) {
			instance = new DbManager();
		}
		return instance;
	}
	
	public static void main(String[] args) {
		new DbManager();
	}
}
