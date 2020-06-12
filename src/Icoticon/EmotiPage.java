package Icoticon;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class EmotiPage extends JPanel{
	EmotiFrame emotiFrame; //내가 살아가는 윈도우 어버이
	String title;
	Color color;
	int width;
	int height;
	boolean showFlag;
	JLabel la_img;
	Icon i;
	JLabel imgLabel; // imagePath 에 있는 경로를 이용하여 이미지가 붙을 라벨    이미지 하나하나
	public JPanel p_big; //  imgLabel들을 붙일 큰패널
	JButton bt_choice;
	JScrollPane scroll;
	Toolkit kit;
	Image img;
	String path = "C:/images/";
	
	String[][] imagePath= { 
			{ path+"e1.png", path+"e2.png", path+"e3.png", path+"e4.png", path+"e5.png",path+"e6.png",
				path+"e5.png",path+"e1.png",path+"e10.png",path+"e9.png",path+"e8.png",path+"e7.png",
				path+"e1.png",path+"e1.png",path+"e1.png",path+"e1.png"
			},
			{ path+"love.gif", path+"love.gif", path+"love.gif", path+"love.gif", path+"love.gif", path+"love.gif", 
				path+"love.gif", path+"love.gif", path+"love.gif", path+"love.gif", path+"love.gif", path+"love.gif", 
				path+"love.gif", path+"love.gif", path+"love.gif", path+"love.gif"
			},
			{ path+"e10.png",path+"e9.png",path+"e8.png",path+"e2.png", path+"e3.png", path+"e4.png",
				path+"e9.png",path+"e8.png",path+"e7.png",path+"e9.png",path+"e8.png",path+"e7.png",
				path+"e9.png",path+"e8.png",path+"e7.png",path+"e9.png"
			},
			{ path+"e5.png",path+"e1.png",path+"e10.png",path+"e9.png",path+"e5.png",path+"e1.png",path+"e10.png",
				path+"e9.png",path+"e5.png",path+"e1.png",path+"e10.png",path+"e9.png",path+"e5.png",
				path+"e1.png",path+"e10.png",path+"e9.png"
			}			
				
		};
	
	Image[][] emotiArray;
	public String bigPath; //유저가 선택한 이미지 경로
	public Image big; //유저가 선택한 이미지  == 미리보기에 붙일 이미지
	ImageCard[] imgCard;
	BigImgCard card;
	
	public EmotiPage(EmotiFrame emotiFrame, String title,Color color,int width, int height,boolean showFlag) {
		this.emotiFrame =emotiFrame;
		this.title =title;
		this.color = color;
		this.width = width;
		this.height = height;
		this.showFlag = showFlag;
		
		this.setVisible(showFlag);
		this.setBackground(color);
		this.setPreferredSize(new Dimension(width,height));
		kit = Toolkit.getDefaultToolkit();
		p_big = new JPanel(new GridLayout(5,4,5,5));
		scroll = new JScrollPane(p_big);

		// 스타일부여
		scroll.setPreferredSize(new Dimension(380, 340));
		scroll.getVerticalScrollBar().setUnitIncrement(15);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		p_big.setBackground(Color.WHITE);
		
//		setLayout(new BorderLayout());
		
		add(scroll);
		

		
		//이미지 미리 생성해놓기!!
		createThumb();
	}
	
	public void createThumb() {
		emotiArray = new Image[imagePath.length][imagePath[0].length];
		
		for(int i=0;i<imagePath.length;i++) {
			for(int a=0;a<imagePath[i].length;a++) {
				emotiArray[i][a] = kit.getImage(imagePath[i][a]);
			}
		}
	}
	
	 //이모티콘의 유형을 사용자가 버튼으로 결정지을 수 있다
	public void printThumb(int index) {
		p_big.removeAll();//기존꺼 싹 지우고 
		
		imgCard = new ImageCard[imagePath[0].length];
		for (int i = 0; i < emotiArray[index].length; i++) {
			imgCard[i] = new ImageCard(this, emotiArray[index][i], imagePath[index][i]);
			p_big.add(imgCard[i]);
			
		}
		
		p_big.updateUI();//새로고침
		p_big.revalidate();
	}
}










