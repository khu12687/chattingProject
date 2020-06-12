package Chatting;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.LayoutManager;
import java.awt.Rectangle;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.Scrollable;

public class MyChatContentPanel extends JPanel implements Scrollable {
	
	MyChatContentPanel p_south, p_center;
	JScrollPane scroll;
	JTextField t_input;
	JTextArea area;
	JButton bt_input;
	boolean flag = true;
    private int x;


    public MyChatContentPanel() {
    	final MyChatContentPanel content = new MyChatContentPanel(new GridBagLayout());
    	setPreferredSize(new Dimension(800, 600));
    	t_input = new JTextField(10);
		scroll = new JScrollPane(area);
		p_south = new MyChatContentPanel();
		p_center = new MyChatContentPanel();
//		bt_input = new JButton("����");
		
		//���̱�
		p_south.add(t_input);
		p_south.add(bt_input);
		add(scroll);
		
		p_center.setLayout(new BorderLayout());
		p_center.add(p_south, BorderLayout.SOUTH);
		p_center.add(this, BorderLayout.SOUTH);

    	
    	//������
        p_south.setBounds(10, this.getHeight(), 50, 50);
    	
    	
    	
    	
    
    }
    
    public static void main(String[] args) {
		
	}
    
    
    
    public MyChatContentPanel(LayoutManager manager){
        super(manager);
    }

    @Override
    public Dimension getPreferredScrollableViewportSize(){
        return getPreferredSize();
    }

    @Override
    public int getScrollableBlockIncrement(Rectangle visibleRect, int orientation, int direction){
        return 1;
    }

    @Override
    public boolean getScrollableTracksViewportHeight(){
        return false;
    }

    @Override
    public boolean getScrollableTracksViewportWidth(){
        return true;
    }

    @Override
    public int getScrollableUnitIncrement(Rectangle visibleRect, int orientation, int direction){
        return 1;
    }
    
}
