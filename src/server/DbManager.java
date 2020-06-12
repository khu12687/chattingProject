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
		
		
		// db�� ä�� �ø���
		String sql = "insert into log(log_num,group_member_num,log_chat) values (seq_log.nextval, ?,?)";
//		String sql = "insert into chating_log(chat_log,user_num,chatlog) values (cahtlog.nextval,1,'�ƽ����')";
		
		//1�� �������� �� ��������
		//msg ���� ģ ä��
	
		PreparedStatement pstmt = null;
//		 System.out.println(sql);
		// ���� ���� �޼��� ȣ�� DML�� executeUpdate()ȣ��
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1,1);//����� ���� �ѹ�
			if(msg.equals("")) {
				return;
			}else {
			pstmt.setString(2,msg);
			}
			int result = pstmt.executeUpdate();// ������ �� �������� ���� ������ ���� ���ڵ� ������ ��ȯ����
			if (result == 0) {
				JOptionPane.showMessageDialog(server, "��Ͻ���");
			} else {
//				JOptionPane.showMessageDialog(server, "��ϼ���");
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
			rs=pstmt.executeQuery();//������
			
			//�ѹ��� �ϳ��� ���ڵ�
			while (rs.next()) {
				// ä�÷α� Ŭ����
				MyLog myLog= new MyLog();

				// rs���� ä�÷α׷� �Űܽ���

				myLog.setLog_chat(rs.getString("log_chat"));
				String msg = myLog.getLog_chat();
				//�Ʒ����� �����ʿ�
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
