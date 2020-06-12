package Icoticon;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.text.Utilities;

import java.awt.Window;
import com.sun.awt.*;

public class PreviewImg extends JFrame {
	BigImgCard card;
	JPanel panel;
	EmotiFrame emotiFrame;
	public PreviewImg(BigImgCard card) {
		this.card=card;
		panel = new JPanel();
		panel.setOpaque(false);
		panel.add(card);
		add(panel);
		setBounds(card.emotiFrame.mcwf.getX(),card.emotiFrame.mcwf.getY()+card.emotiFrame.mcwf.getHeight()-300
				,card.emotiFrame.mcwf.getWidth(),210);
		setUndecorated(true);
		setLayout(new FlowLayout());
		setBackground(new Color(0,200, 0, 0));
		
		setOpacity(0.7f);
		setVisible(true);
		setAlwaysOnTop(true);
		pack();
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int result = e.getKeyCode();
				if(result == KeyEvent.VK_ENTER) {
//					PreviewImg.this.dispatchEvent(new WindowEvent(PreviewImg.this, WindowEvent.WINDOW_CLOSING));
					System.out.println("dd");
					card.emotiFrame.mcwf.t_input.requestFocus();
				}
			}
		});
	}
}
