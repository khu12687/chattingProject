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
	JPanel p_content; //페이지가 붙게될 컨텐츠 영역!!
	JButton[] emoti_bt = new JButton[4];
	EmotiPage emotiPage; //페이지수만큼 배열확보
	MainChatWithJFrame mcwf; //이모티콘 선택시 그경로 정보를 가진 path변수를 접근하기 위함
								
	public BigImgCard card;
	public PreviewImg pre;

	public EmotiFrame(MainChatWithJFrame mcwf) {
//		System.out.println("넘겨받은 프레임의 주소는 "+mainChatWithJFrame);
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

		//스타일부여
		p_north.setBackground(Color.BLACK);

		//버튼과 리스너 연결
		for (int i = 0; i < emoti_bt.length; i++) {
			emoti_bt[i].addActionListener(this);
			p_north.add(emoti_bt[i]);
		}

		add(p_north, BorderLayout.NORTH);
		
		//모든 페이지가 프레임에 붙는게 아니라, 컨텐트영역에 붙어야하므로..
		add(p_content);
		p_content.add(emotiPage);

		setVisible(true);
		setBounds(mcwf.getX() + mcwf.getWidth(), mcwf.getY(), 360, 380);
		pack();
		setResizable(false);

		//유저가 보게되는 이모티콘화면!!
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
//		System.out.println("나누름?");
		System.out.println("bigPath = " + emotiPage.bigPath);

		mcwf.setPath(emotiPage.bigPath);

	}

	//EmotiPage 패널의 출력할 이미지를 교체!!
	public void showPage(int page) {
//		System.out.println("선택한 아이템 번째는 " + page);
		this.setTitle(page + "번째 아이템");
		emotiPage.printThumb(page);
	}
}
