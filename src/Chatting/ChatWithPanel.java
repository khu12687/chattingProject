package Chatting;

import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.Rectangle;

import javax.swing.JPanel;
import javax.swing.Scrollable;

public class ChatWithPanel extends JPanel implements Scrollable {

    public ChatWithPanel(LayoutManager manager){
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
