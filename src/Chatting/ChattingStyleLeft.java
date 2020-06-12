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
		
		// ������ �̹��� �����ϴ� ��
		i = ResizeImageIcon.resizeImageIcon(profilePath, 60, 60);
		la_profile = new JLabel(i);
		la_profile.setOpaque(true);
		la_profile.setBackground(Color.white);

		// �̸������ϴ� ��
		p_name = new JPanel(new BorderLayout());
		p_name.setBackground(Color.white);
		areaName = new JLabel("����ö");
		areaName.setOpaque(true);
		areaName.setBackground(Color.white);
		areaName.setFont(new Font("���� ���", Font.PLAIN, 15));
		p_name.add(areaName, BorderLayout.SOUTH);

		// content �гο� ���� �гλ���
		p = new JPanel(new BorderLayout());
		p.setBackground(Color.YELLOW);

		// MyTextPane �����ں���
		path = mcwf.getPath();
//		final boolean lineWrap, String msg, boolean flag,String fontName,int fontStyle,  int fontSize
		textPane = new MyTextPane(this, path,img, true, mcwf.msg, flag, "���� ���", Font.PLAIN, 15);
		textPane.setBackground(Color.YELLOW);

		gbc1 = new GridBagConstraints();
		gbc2 = new GridBagConstraints();
		gbc3 = new GridBagConstraints();
		gbc4 = new GridBagConstraints();

		// flag�� ���� ���´��� Ȯ���ϴ� �뵵�� �ϴ� ��.
//		if (flag) {
//			gbc1.insets = new Insets(10, 0, 10, 100);
//			System.out.println("������");
//		} else if (!flag) {

//			System.out.println("����");
//		}

		// �ϴ� �׻� ������ ġ�°ɷ� ����
		flag = false;
		
		//�̹��� gbc
		x++;   //x+=1
		gbc1.insets = new Insets(10, 0, 10, 10);
		gbc1.anchor = GridBagConstraints.NORTHWEST;
		gbc1.gridx = 1;
		gbc1.weightx = 0;
		gbc1.gridy = x; // ���� �����ؾ� �ϱ� ������ ++; �׷��� ä���� �Ʒ��� �޿� ������.
		gbc1.fill = GridBagConstraints.HORIZONTAL;

		//�̸� �� gbc
		gbc2.insets = new Insets(10, 0, 10, 10);
		gbc2.anchor = GridBagConstraints.NORTH;
		gbc2.gridx = 2;
		gbc2.weightx = 0;
		gbc2.gridy = x; // ���� �����ؾ� �ϱ� ������ ++;
		gbc2.fill = GridBagConstraints.CENTER;

		//ä�� textpane gbc
		gbc3.insets = new Insets(10,0, 10, 0);
		gbc3.anchor = GridBagConstraints.CENTER;
		gbc3.gridx = 3;
		gbc3.weightx = 1;
		gbc3.gridy = x; // ���� �����ؾ� �ϱ� ������ ++;
		gbc3.fill = GridBagConstraints.HORIZONTAL;

		// �ð��� ����    �ð� gbc
		la_time = new JLabel(calDate.calDateSimple());
		la_time.setFont(new Font("���� ���", Font.PLAIN, 10));
		gbc4.insets = new Insets(10, 0, 10, 100);
		gbc4.anchor = GridBagConstraints.SOUTH;
		gbc4.gridx = 4;
		gbc4.weightx = 0;
		gbc4.gridy = x; // ���� �����ؾ� �ϱ� ������ ++;
		gbc4.fill = GridBagConstraints.HORIZONTAL;
		gbc4.ipady=20;

		
		//�̹��� �����ϴ°� MyTextPane���� �Ű��.
		//�� �ű�� �ؽ�Ʈ�� �� �������� ���� ����.
		p.add(textPane);
		// �̹��� ��� �ѹ� �޾ƿ��� �ٽ� ä�ð� �� �ְ�
		mcwf.setPath("");
		mcwf.t_input.setText("");
		
		p.updateUI();
		textPane.updateUI();
	}
}
