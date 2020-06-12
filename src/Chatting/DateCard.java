package Chatting;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import calendar.sibal;


public class DateCard extends JPanel{
	JLabel la;
	LineBorder border;
	int x;
	
	public DateCard(int x, String n, Color color,int year,int month) {
		this.x = x;
		la = new JLabel(n);
		border = new LineBorder(Color.WHITE, 1, false);
		
		add(la);
		
		
		setPreferredSize(new Dimension(50, 50));
		setBackground(color);
		setBorder(border);
		
		this.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				new sibal(year,(month+1),x);
				
				System.out.println(year+"³â,"+(month+1)+"¿ù "+x+"ÀÏ");
			}
		});
	}
}

