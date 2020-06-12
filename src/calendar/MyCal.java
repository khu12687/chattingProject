package calendar;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import Chatting.ChatApp;
import Chatting.DateCard;

public class MyCal extends JFrame {
	JPanel p_content;
	DateCard card;
	JPanel p_whole;//��ü �г� 
	JPanel p_both; //��ư�� �޷�����, ���Խ�ų ���
	JPanel p_contentBT; //��ư�� ���Խ�ų �г�
	JPanel p_contentTXT; //�޷������ �г�
	
	JPanel blank;
	
	JLabel la_title;//�޷� ����
	JButton bt_next;//���� ��ư
	JButton bt_prev;//���� ��ư
	boolean flag = true;
	
	
	String[] week= {"Sun","Mon","Tue","Wed","Thur","Fri","Sat"};
	Calendar cal;
	int year;
	int month;
	int startDay;//�� ���� ���� ����
	int lastDate;//�� ���� ������ ��
	int theDate;
	
	int year1;
	int month1;
	
	LineBorder border;
	ChatApp chatApp;
	
	public MyCal(ChatApp chatApp) {
		this.chatApp=chatApp;
		cal = Calendar.getInstance();
		p_content = new JPanel();
		p_whole = new JPanel();
		p_both = new JPanel();
		p_contentBT = new JPanel();
		p_contentTXT = new JPanel();
		
		
		
		Border emptyBorder = BorderFactory.createEmptyBorder();
		getCurrent();
		
		
		
		la_title = new JLabel(year+"��"+" "+(month+1)+"��", SwingConstants.CENTER);
		bt_next = new JButton("next");
		bt_prev = new JButton("prev");
		
		bt_next.setAlignmentX(RIGHT_ALIGNMENT);
		
		//��Ÿ�� �ο� 
		p_content.setBackground(Color.white);
		p_whole.setBackground(Color.white);
		p_both.setBackground(Color.white);
		p_contentBT.setBackground(Color.white);
		p_contentTXT.setBackground(Color.white);
		la_title.setPreferredSize(new Dimension(420, 50));
		la_title.setFont(new Font("����", Font.BOLD, 30));
		la_title.setBackground(Color.BLACK);
		la_title.setForeground(Color.BLACK);
		p_both.setBackground(Color.GREEN);
		
		
		//���ʰ��� ���� 
		p_contentTXT.add(la_title);
		p_contentBT.add(bt_prev);
		p_contentBT.add(bt_next);
		
		p_contentBT.setLayout(new BoxLayout(p_contentBT, BoxLayout.LINE_AXIS));
		p_contentBT.add(new JLabel("     "));
		p_contentBT.add(bt_prev);
		p_contentBT.add(Box.createHorizontalGlue());
		p_contentBT.add(bt_next);
		p_contentBT.add(new JLabel("     "));
		
		
		bt_next.setBackground(Color.white);
		bt_next.setBorder(emptyBorder);
		bt_prev.setBackground(Color.white);
		bt_prev.setBorder(emptyBorder);
		
		
		p_both.setLayout(new GridLayout(2, 1));
		p_both.add(p_contentTXT);
		p_both.add(p_contentBT);
		
		setIconImage(null);

		add(p_both, BorderLayout.NORTH);
		
		//���Ͱ��� ���� 
		add(p_content);
		
		createCard();
		
		setSize(400,540);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		
		//������ư
		bt_next.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				month=month +1;
				if(month >=12) {
					month =0;
					year++;
				}
				cal.set(year, month, 1);

				getStartDay(month);
				deleteCard();
				createCard();
				
				la_title.setText(year+"��"+(month+1)+"��");
			
			}
		});
	
		//������ư
		bt_prev.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				month=month -1;
				if(month <0) {
					month =11;
					year--;
				}
				cal.set(year, month, 1);
				getStartDay(month);
				deleteCard();
				createCard();
				
				la_title.setText(year+"��"+(month+1)+"��");
				
			}
		});
		
		
		
	}
	
	
	
	
	public void getCurrent() {
		year=cal.get(Calendar.YEAR);
		month=cal.get(Calendar.MONTH);
		theDate = cal.get(Calendar.DAY_OF_MONTH);
		
		year1=cal.get(Calendar.YEAR);
		month1=cal.get(Calendar.MONTH);
		
		
		
		getStartDay(month);
		
		System.out.println(theDate);
	}
	

	public void getStartDay(int mm) {
		Calendar calendar =  Calendar.getInstance();
		calendar.set(year, mm, 1);
		startDay=calendar.get(Calendar.DAY_OF_WEEK);
	
		
	
		calendar.set(year, mm , -1);
		
		
		lastDate=cal.getActualMaximum(Calendar.DATE);
		

	}
	
	
	public void deleteCard() {
		Component[] comp=p_content.getComponents();
		for(int i=0;i<comp.length;i++){
			p_content.remove(comp[i]);
		}
		p_content.updateUI();
	}
	
	
	public void createCard() {
		for(int i=1;i<=week.length;i++) {
			card = new DateCard(i, week[i-1],Color.CYAN,year,month);
			p_content.add(card);
		}
		
		
		int d=0;
		String str=null;
		System.out.println("lastDate"+lastDate);
		
		for(int i=1;i<=42;i++) {
			if(i>=startDay && d<=lastDate-1) {
				d++;
				str=d+"";
			}else {
				str="";
			}
			if(theDate==d && month==month1 && year==year1) {
				card = new DateCard(d, str,Color.YELLOW,year,month);	
			}else {
				card = new DateCard(d, str,Color.GRAY,year,month);	
				
			}
			
			p_content.add(card);
		}
		
	}
	
	
	
	public static void main(String[] args) {
		
	}
	

}
