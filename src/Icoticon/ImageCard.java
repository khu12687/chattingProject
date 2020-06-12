package Icoticon;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import Chatting.ManageLoggingIn;

public class ImageCard extends JPanel{
	ImageIcon icon;
	Image img;
	BigImgCard card;
	EmotiPage emotiPage;
	String pathName;
	
	public ImageCard(EmotiPage emotiPage, Image img, String pathName) {
		this.emotiPage=emotiPage;
		this.img=img;
		this.pathName = pathName;
		this.setPreferredSize(new Dimension(80, 80));
		
		emotiPage.p_big.updateUI();
		
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//System.out.println("나의 이미지 객체는 "+img);
				System.out.println("이미지의 경로 : "+pathName);
				if(e.getClickCount()==1) {
					emotiPage.big=img;
					emotiPage.bigPath=pathName;
					emotiPage.emotiFrame.selectEmo();
					emotiPage.p_big.updateUI();
				}
				if(e.getClickCount()==2) {
					emotiPage.emotiFrame.mcwf.setPath(pathName);
					StringBuilder sb = new StringBuilder();
					
					sb.append("{");
					sb.append("\"requestType\":\"Emo\",");
					sb.append("\"user\":"+ManageLoggingIn.myPrimarykey+",");
					sb.append("\"log\":\""+pathName+"\"");
					sb.append("}");			

					emotiPage.emotiFrame.mcwf.clientThread.send(sb.toString());
					
//					emotiPage.emotiFrame.mcwf.createMsg(icon);

					emotiPage.emotiFrame.mcwf.setPath("");
					emotiPage.emotiFrame.mcwf.msg="";
					e.consume();
				}
			}
		});
	}


	@Override
	public void paint(Graphics g) {
		g.drawImage(img, 0, 0, 80, 80, this);
	}
}
