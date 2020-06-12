package StaticMethods;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionManager {
//	static ConnectionManager instance;
	private String url;
	private String user;
	private String password;

	// ������������, �����ͺ��̽��� ���� ������ ������Ƽ���� ��������!!
	// �� �ϳ� ������???
	// ���������� ������ ������
	Properties props;
	FileInputStream fis; // �ѱ��� ���� ������ �����ϰ� ����

	//�ܺ��� Ŭ������ �Ժη� new ���� ���ϵ��� ���´�!!
	public ConnectionManager() {
		props = new Properties(); // key-valuew�� ������ �Ǿ��ִ�
									// �����͸� �����ϴ� ��ü!! �̾��̴�
									// ������ �̿��� �����ʹ� ���ɵ� ����
									// �������� ����.
		InputStream is = this.getClass().getResourceAsStream("/database/dbms.db");
		try {
			// Ŭ������ �ƴ� �Ϲ������� �����ô� . Ŭ�����鳢���� .�̴�.

			props.load(is);
			System.out.println(is);

			url = (String) props.getProperty("oracle_url");
			user = (String) props.getProperty("oracle_userid");
			password = (String) props.getProperty("oracle_password");

		} catch (IOException e) {
			System.out.println("������ ã�� �� ���׿�");
			e.printStackTrace();
		}finally {
			if(is!=null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	// ������ ���̽� ����
	public Connection getConnection() {
		Connection con = null;
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

	// �����ͺ��̽� �ڿ� ����
	// ������â ���� �� ȣ��
	public void closeDB(Connection con) {
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	// DML(insert, update, delete)�� ������ ���
	// Connection�� PreparedStatemnet�� �ݳ�
	public void closeDB(PreparedStatement pstmt) {
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	// select�� ��� preparedStatement, ResultSet ����
	public void closeDB(PreparedStatement pstmt, ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	//�ν��Ͻ��� ��ȯ�ϴ� �޼��� ����
	//instance ������ ���� getter!
//	public static ConnectionManager getInstance() {
//		if(instance==null) {
//			instance = new ConnectionManager();
//			
//		}
//		return instance;
//	}
//
//	public static void main(String[] args) {
//		new ConnectionManager().getConnection();
//	}
}
