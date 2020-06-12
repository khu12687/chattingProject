package Friend;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import table.Friend;

public class ModelFriend extends AbstractTableModel{

	public ArrayList<Friend> list = new ArrayList<Friend>();
	String[] columnNames = {
			"chat_friend_id","name","state","img"
	};
	
	@Override
	public int getRowCount() {
		return list.size();
	}

	@Override
	public int getColumnCount() {
		return 4;
	}
	
	//테이블 이름 출력하기
	@Override
	public String getColumnName(int col) {
		return columnNames[col];
	}

	@Override
	public Object getValueAt(int row, int col) {
		Friend friend = list.get(row);
		String data = null;
		if(col==0) {
			data = Integer.toString(friend.getFriend_num());
		}else if(col==1) {
			data = Integer.toString(friend.getMe());
		}else if(col==2) {
			data = Integer.toString(friend.getYou());
		}
//		else if(col==3) {
//			data = friend.getImg();
//		}
		
		return data;
	}

}
