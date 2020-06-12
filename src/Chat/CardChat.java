package Chat;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

import Chatting.MainChatWithJFrame;
import Friend.ManageFriend;

public class CardChat extends JPanel {

	JPanel p_container; // 이미지랑 라벨 붙일 패널
	JPanel p_con_canvas; // 캔버스 담을 곳
	JPanel pp;
	ManageFriend fm;
	JPanel p_canvas; // 이미지 붙을 곳
	JLabel la_name, la_state;
	Chat chat; // VO 생성!!
	JScrollPane scroll;
	ManageChat manageChat;

	public CardChat(ManageChat manageChat) {
		this.manageChat = manageChat;
//		this.chat = chat;
		
		p_con_canvas = new JPanel();
		// 디자인 시작
		p_container = new JPanel(new BorderLayout());
		p_canvas = new JPanel() {
		};
		pp = new JPanel(); // container와 canvas 담을 패널
		scroll = new JScrollPane(pp);

		la_name = new JLabel("멤버 이름");
		la_state = new JLabel("채팅로그");

		// 스타일 적용
		setPreferredSize(new Dimension(320, 70));
		setBackground(Color.YELLOW);

		p_con_canvas.setPreferredSize(new Dimension(60, 60));
		p_container.setPreferredSize(new Dimension(250, 60));
		p_canvas.setPreferredSize(new Dimension(50, 50));
		p_canvas.setBackground(Color.BLUE);
		la_name.setPreferredSize(new Dimension(250, 20));
		la_state.setPreferredSize(new Dimension(250, 40));

		p_container.add(la_name, BorderLayout.NORTH);
		p_container.add(la_state, BorderLayout.SOUTH);

		p_container.setBackground(Color.white);
		p_con_canvas.setBackground(Color.RED);

		p_con_canvas.add(p_canvas);
		setLayout(new BorderLayout());
		pp.add(p_con_canvas, BorderLayout.WEST);
		pp.add(p_container, BorderLayout.CENTER);

		add(pp);
		
		// 이미지 캔버스에 이벤트 추가
				p_canvas.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
						int resultClick = e.getClickCount();

						if (SwingUtilities.isLeftMouseButton(e)) {
							if (resultClick == 1) {
								System.out.println("프로필화면띄우기");
							}
						}
					}
				});

				// container 에 이벤트 추가
				p_container.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
						int resultClick = e.getClickCount();
						if (SwingUtilities.isRightMouseButton(e)) {
							System.out.println("오른쪽버튼 클릭, 메뉴띄우기");
						}
						if (SwingUtilities.isLeftMouseButton(e)) {
							if (resultClick == 2) {
								System.out.println("친구와 채팅");
//								if() {
//									
//								}
								new MainChatWithJFrame(manageChat.chatApp);
							}

						}
					}

				});

			}
}