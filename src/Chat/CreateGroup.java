package Chat;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import Chatting.ChatApp;
import Chatting.ManageLoggingIn;

public class CreateGroup {
	public CreateGroup(String nickname,int me,int you) {

		String sql = "insert into group_room(group_room_num,group_room_name) values (seq_group_room.nextval,?)";

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			ChatApp.con.setAutoCommit(false);

			pstmt = ChatApp.con.prepareStatement(sql);
			pstmt.setString(1, nickname);

			pstmt.executeUpdate();

			String sql2 = "insert into group_member(group_member_num,group_room_num,member_num) values (seq_group_member.nextval,seq_group_room.currval,?)";

			pstmt = ChatApp.con.prepareStatement(sql);
			pstmt.setInt(1, ManageLoggingIn.myPrimarykey);

			pstmt.executeUpdate();

			String sql3 = "insert into group_member(group_member_num,group_room_num,member_num) values (seq_group_member.nextval,seq_group_room.currval,?)";

			pstmt = ChatApp.con.prepareStatement(sql);
			pstmt.setInt(1, you);

			int result = pstmt.executeUpdate();

			if (result == 0) {
//				JOptionPane.showMessageDialog(this, "등록실패");
				System.out.println("등록실패");
			} else {
				ChatApp.con.commit();
				System.out.println("등록성공");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				ChatApp.con.rollback();
				System.out.println("롤백");
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}

}
