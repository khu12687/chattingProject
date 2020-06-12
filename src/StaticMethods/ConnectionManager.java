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

	// 생성시점부터, 데이터베이스에 대한 정보를 프로퍼티에서 가져오자!!
	// 왜 하나 귀찮게???
	// 유지보수와 보안의 목적상
	Properties props;
	FileInputStream fis; // 한글이 없기 때문에 간단하게 간다

	//외부의 클래스가 함부로 new 하지 못하도록 막는다!!
	public ConnectionManager() {
		props = new Properties(); // key-valuew의 쌍으로 되어있는
									// 데이터를 전담하는 객체!! 이아이는
									// 이형태 이외의 데이터는 관심도 없고
									// 읽을수도 없다.
		InputStream is = this.getClass().getResourceAsStream("/database/dbms.db");
		try {
			// 클래스가 아닌 일반파일은 슬러시다 . 클래스들끼리만 .이다.

			props.load(is);
			System.out.println(is);

			url = (String) props.getProperty("oracle_url");
			user = (String) props.getProperty("oracle_userid");
			password = (String) props.getProperty("oracle_password");

		} catch (IOException e) {
			System.out.println("파일을 찾을 수 없네요");
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

	// 데이터 베이스 접속
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

	// 데이터베이스 자원 해제
	// 윈도우창 닫을 때 호출
	public void closeDB(Connection con) {
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	// DML(insert, update, delete)만 수행한 경우
	// Connection과 PreparedStatemnet만 반납
	public void closeDB(PreparedStatement pstmt) {
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	// select문 경우 preparedStatement, ResultSet 닫음
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
	
	//인스턴스를 반환하는 메서드 정의
	//instance 변수에 대한 getter!
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
