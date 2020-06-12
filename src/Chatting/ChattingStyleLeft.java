package Chatting;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import StaticMethods.CalDate;

public class ChattingStyleLeft {
//	ImageIcon icon;
	Icon img;
	Icon i;
	String profilePath = "C:/Users/suyou/Desktop/images/me.jpg";
	String path;
	public MyTextPane textPane;

	JLabel la_profile;

	JPanel p_name;
	JLabel areaName;
	JLabel la_time;
	MainChatWithJFrame mcwf;
	boolean flag;
	int x;

	JPanel p;
	GridBagConstraints gbc1;
	GridBagConstraints gbc2;
	GridBagConstraints gbc3;
	GridBagConstraints gbc4;
	
	CalDate calDate;

	public ChattingStyleLeft(MainChatWithJFrame mcwf, boolean flag, int x, String msg) {
		this.mcwf = mcwf;
		flag = false;
		this.x = x;
		calDate =CalDate.getInstance();
		
		// 프로필 이미지 생성하는 라벨
		i = ResizeImageIcon.resizeImageIcon(profilePath, 60, 60);
		la_profile = new JLabel(i);
		la_profile.setOpaque(true);
		la_profile.setBackground(Color.white);

		// 이름생성하는 라벨
		p_name = new JPanel(new BorderLayout());
		p_name.setBackground(Color.white);
		areaName = new JLabel("유형철");
		areaName.setOpaque(true);
		areaName.setBackground(Color.white);
		areaName.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		p_name.add(areaName, BorderLayout.SOUTH);

		// content 패널에 붙일 패널생성
		p = new JPanel(new BorderLayout());
		p.setBackground(Color.YELLOW);

		// MyTextPane 생성자변경
		path = mcwf.getPath();
//		final boolean lineWrap, String msg, boolean flag,String fontName,int fontStyle,  int fontSize
		textPane = new MyTextPane(this, path,img, true, mcwf.msg, flag, "맑은 고딕", Font.PLAIN, 15);
		textPane.setBackground(Color.YELLOW);

		gbc1 = new GridBagConstraints();
		gbc2 = new GridBagConstraints();
		gbc3 = new GridBagConstraints();
		gbc4 = new GridBagConstraints();

		// flag는 누가 보냈는지 확인하는 용도로 일단 씀.
//		if (flag) {
//			gbc1.insets = new Insets(10, 0, 10, 100);
//			System.out.println("오른쪽");
//		} else if (!flag) {

//			System.out.println("인쪽");
//		}

		// 일단 항상 상대방이 치는걸로 세팅
		flag = false;
		
		//이미지 gbc
		x++;   //x+=1
		gbc1.insets = new Insets(10, 0, 10, 10);
		gbc1.anchor = GridBagConstraints.NORTHWEST;
		gbc1.gridx = 1;
		gbc1.weightx = 0;
		gbc1.gridy = x; // 값이 증가해야 하기 때문에 ++; 그래야 채팅이 아래로 쭈욱 써진다.
		gbc1.fill = GridBagConstraints.HORIZONTAL;

		//이름 라벨 gbc
		gbc2.insets = new Insets(10, 0, 10, 10);
		gbc2.anchor = GridBagConstraints.NORTH;
		gbc2.gridx = 2;
		gbc2.weightx = 0;
		gbc2.gridy = x; // 값이 증가해야 하기 때문에 ++;
		gbc2.fill = GridBagConstraints.CENTER;

		//채팅 textpane gbc
		gbc3.insets = new Insets(10,0, 10, 0);
		gbc3.anchor = GridBagConstraints.CENTER;
		gbc3.gridx = 3;
		gbc3.weightx = 1;
		gbc3.gridy = x; // 값이 증가해야 하기 때문에 ++;
		gbc3.fill = GridBagConstraints.HORIZONTAL;

		// 시간라벨 생성    시간 gbc
		la_time = new JLabel(calDate.calDateSimple());
		la_time.setFont(new Font("맑은 고딕", Font.PLAIN, 10));
		gbc4.insets = new Insets(10, 0, 10, 100);
		gbc4.anchor = GridBagConstraints.SOUTH;
		gbc4.gridx = 4;
		gbc4.weightx = 0;
		gbc4.gridy = x; // 값이 증가해야 하기 때문에 ++;
		gbc4.fill = GridBagConstraints.HORIZONTAL;
		gbc4.ipady=20;

		
		//이미지 삽입하는거 MyTextPane으로 옮겼따.
		//안 옮기면 텍스트가 늘 사진보다 위에 나옴.
		p.add(textPane);
		// 이미지 경로 한번 받아오면 다시 채팅갈 수 있게
		mcwf.setPath("");
		mcwf.t_input.setText("");
		
		p.updateUI();
		textPane.updateUI();
	}
}
