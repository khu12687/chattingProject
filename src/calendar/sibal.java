package calendar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import server.DbManager;

public class sibal extends JFrame {
	JPanel shit;
	JTextArea area;
	JScrollPane scroll;
	JButton bt;
	String D_day;
	String memo;
	Connection con;
	DbManager dbManager;

	public sibal(int year, int month, int day) {
		shit = new JPanel();
		area = new JTextArea(20, 30);
		scroll = new JScrollPane(area);
		bt = new JButton("����");
		D_day = "" + year + "." + month + "." + day + "";

		shit.add(bt);
		shit.add(scroll);
		add(shit);

//		area.setLineWrap(true);
		setSize(400, 540);
		setVisible(true);
		setBounds(400, 0, 400, 540);

		dbManager =DbManager.getInstance();
		con = dbManager.getConnection();
		if (con == null) {
			JOptionPane.showMessageDialog(sibal.this, "�����ͺ��̽� ���ӽ���");
		} else {
//			JOptionPane.showMessageDialog(this,"�����ͺ��̽� ���Ӽ���");
		}
		outputMemo();

		bt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String sql = "insert into calendar(calendar_num,member_num,calendar_day,calendar_memo) values (seq_calendar.nextval,?,?,?)";

				PreparedStatement pstmt = null;
				int result=0;
//				 System.out.println(sql);
				// ���� ���� �޼��� ȣ�� DML�� executeUpdate()ȣ��
				try {
					pstmt = con.prepareStatement(sql);

					memo = area.getText();
					pstmt.setInt(1, 1);
					pstmt.setString(2, D_day);

					pstmt.setString(3, memo);
					
				
						result = pstmt.executeUpdate();// ������ �� �������� ���� ������ ���� ���ڵ� ������ ��ȯ����

					if (result == 0) {
						JOptionPane.showMessageDialog(sibal.this, "��Ͻ���");
					} else {
						JOptionPane.showMessageDialog(sibal.this, "��ϼ���");
						setVisible(false);
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} finally {
					dbManager.closeDB(pstmt);
					// System.out.println("shopApp : "+shopApp);
					// System.out.println("connectionManager : "+shopApp.connectionManager);
					// System.out.println("pstmt : "+pstmt);
				}

				dbManager.closeDB(con);
			}
		});
	}

	public void outputMemo() {
		String sql = "select * from calendar where member_num=1 and calendar_day=? order by calendar_num";
//		String sql = "select * from calendar";
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			pstmt.setString(1, D_day);


			rs = pstmt.executeQuery();
//			JTextAre;
			rs.last();
				Calendar calendar = new Calendar();
				
				calendar.setCalendar_memo(rs.getString("calendar_memo"));
				
				// �Ʒ����� �����ʿ�
				area.append(calendar.getCalendar_memo() + "\n");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			dbManager.closeDB(pstmt, rs);
		}
	}
}
