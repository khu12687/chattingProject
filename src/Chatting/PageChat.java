package Chatting;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PageChat extends PagePrimary {
	public ChatApp chatApp;
	private String title;
	private int width;
	private int height;
	private Color color;
	private boolean showFlag;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public boolean isShowFlag() {
		return showFlag;
	}

	public void setShowFlag(boolean showFlag) {
		this.showFlag = showFlag;
	}

	public PageChat(ChatApp chatApp, String title, int width, int height, Color color, boolean showFlag) {

		this.chatApp = chatApp;
		this.title = title;
		this.width = width;
		this.height = height;
		this.color = color;
		this.showFlag = showFlag;

		setLayout(new BorderLayout());
		this.setBackground(color);
		this.setPreferredSize(new Dimension(width, height));
		this.setVisible(this.showFlag);
	}

}
