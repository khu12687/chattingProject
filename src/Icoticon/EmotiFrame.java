package Icoticon;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Chatting.MainChatWithJFrame;

public class EmotiFrame extends JFrame implements ActionListener {
	JPanel p_north;
	JPanel p_content; //�������� �ٰԵ� ������ ����!!
	JButton[] emoti_bt = new JButton[4];
	EmotiPage emotiPage; //����������ŭ �迭Ȯ��
	MainChatWithJFrame mcwf; //�̸�Ƽ�� ���ý� �װ�� ������ ���� path������ �����ϱ� ����
								
	public BigImgCard card;
	public PreviewImg pre;

	public EmotiFrame(MainChatWithJFrame mcwf) {
//		System.out.println("�Ѱܹ��� �������� �ּҴ� "+mainChatWithJFrame);
		this.mcwf = mcwf;

		p_north = new JPanel();
		p_content = new JPanel();

		emoti_bt[0] = new JButton("p1");
		emoti_bt[1] = new JButton("p2");
		emoti_bt[2] = new JButton("p3");
		emoti_bt[3] = new JButton("p4");

		emotiPage = new EmotiPage(this, "Imoticon", Color.YELLOW, 380, 340, true);
//		emotiPage.setSize(new Dimension(340,340));
//		emotiPage.setLayout(new GridLayout(4, 5, 5, 5));

		//��Ÿ�Ϻο�
		p_north.setBackground(Color.BLACK);

		//��ư�� ������ ����
		for (int i = 0; i < emoti_bt.length; i++) {
			emoti_bt[i].addActionListener(this);
			p_north.add(emoti_bt[i]);
		}

		add(p_north, BorderLayout.NORTH);
		
		//��� �������� �����ӿ� �ٴ°� �ƴ϶�, ����Ʈ������ �پ���ϹǷ�..
		add(p_content);
		p_content.add(emotiPage);

		setVisible(true);
		setBounds(mcwf.getX() + mcwf.getWidth(), mcwf.getY(), 360, 380);
		pack();
		setResizable(false);

		//������ ���ԵǴ� �̸�Ƽ��ȭ��!!
		showPage(0);
		// Page2 main = (Page2)emotiPages[1];

		emotiPage.updateUI();
		emotiPage.revalidate();


		addWindowFocusListener(new WindowFocusListener() {
			public void windowLostFocus(WindowEvent e) {
			}

			public void windowGainedFocus(WindowEvent e) {
			}
		});

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();

		if (obj == emoti_bt[0]) {
			showPage(0);
		} else if (obj == emoti_bt[1]) {
			showPage(1);
		} else if (obj == emoti_bt[2]) {
			showPage(2);
		} else if (obj == emoti_bt[3]) {
			showPage(3);
		}

	}

	public void selectEmo() {
		card = new BigImgCard(emotiPage.big, emotiPage.bigPath, this);
		
		if(pre!=null) {
			pre.dispose();
			pre = new PreviewImg(card);
		}else {
			pre = new PreviewImg(card);
		}
//		System.out.println("������?");
		System.out.println("bigPath = " + emotiPage.bigPath);

		mcwf.setPath(emotiPage.bigPath);

	}

	//EmotiPage �г��� ����� �̹����� ��ü!!
	public void showPage(int page) {
//		System.out.println("������ ������ ��°�� " + page);
		this.setTitle(page + "��° ������");
		emotiPage.printThumb(page);
	}
}
