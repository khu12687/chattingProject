package Icoticon;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

public class BigImgCard extends JPanel{
	Image img;
	String path;
	EmotiFrame emotiFrame;
	public BigImgCard(Image img, String path,EmotiFrame emotiFrame) {
		this.emotiFrame = emotiFrame;
		this.img=img;
		this.path=path;
		this.setPreferredSize(new Dimension(emotiFrame.mcwf.getWidth()-40, 150));
	}
	@Override
	public void paint(Graphics g) {
		g.drawImage(img, emotiFrame.mcwf.getWidth()-200, 0, 150, 150, BigImgCard.this);
	}
}
