package StaticMethods;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CalDate {
	private static CalDate instance;
	private CalDate() {
	}
	
	public String calDateSimple() {
		Date today = new Date();
		SimpleDateFormat format = new SimpleDateFormat("HH:mm a");
		return format.format(today);
	}
	
	public String calDateComplicated() {
		Date today = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy�� MM�� dd�� E����");
		return format.format(today);
	}
	public static CalDate getInstance() {
		if(instance==null) {
			instance = new CalDate();
		}
		return instance;
	}
}
