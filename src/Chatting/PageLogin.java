package Chatting;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;


//�α��� ���������� �ƺ�
public class PageLogin extends PagePrimary{
	ChatApp chatApp; // ���� ��ư���? ������ �����?
	String title;
	Color color;
	int width;
	int height;
	boolean showFlag;
	boolean reSize; // �α��� ������ ������ ���� ���ϰ� ����.
	
	public PageLogin(ChatApp chatApp, String title, int width, int height, 
								Color color, boolean showFlag, 	boolean reSize) {
		this.chatApp = chatApp;
		this.title = title;
		this.color = color;
		this.width = width;
		this.height = height;
		this.showFlag = showFlag;
		this.reSize = reSize;
		
		this.setBackground(color);
		this.setPreferredSize(new Dimension(width,height));
		this.setVisible(this.showFlag);
		
	}
}