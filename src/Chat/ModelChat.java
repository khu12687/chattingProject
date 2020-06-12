package Chat;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class ModelChat extends AbstractTableModel {
	public ArrayList<Chat> list = new ArrayList<Chat>();
	String[] columnNames = {
			"group_room_name","group_member","group_member_number"
			,"log_chat","log_time","profile_photo"
	};

	@Override
	public int getRowCount() {
		return list.size();
	}

	@Override
	public int getColumnCount() {
		return 6;
	}
	
	//테이블 이름 출력하기
	@Override
	public String getColumnName(int col) {
		return columnNames[col];
	}

	@Override
	public Object getValueAt(int row, int col) {
		Chat chat = list.get(row);
		String data = null;
		if(col==0) {
			data = chat.getGroup_room_name();
		}else if(col==1) {
			data = chat.getGroup_member();
		}else if(col==2) {
			data =  Integer.toString(chat.getGroup_member_number());
		}else if(col==3) {
			data = chat.getLog_chat();
		}else if(col==4) {
			data = chat.getLog_time();
		}else if(col==5) {
			data = chat.getProfile_photo();
		}
		
		return data;
	}

}
