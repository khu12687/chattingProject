package Chatting;

import java.awt.Color;
import java.awt.Font;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.text.AbstractDocument;
import javax.swing.text.BadLocationException;
import javax.swing.text.BoxView;
import javax.swing.text.ComponentView;
import javax.swing.text.Element;
import javax.swing.text.IconView;
import javax.swing.text.LabelView;
import javax.swing.text.ParagraphView;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import javax.swing.text.StyledEditorKit;
import javax.swing.text.View;
import javax.swing.text.ViewFactory;

public class MyTextPane extends JTextPane {
	MainChatWithJFrame mcwf;
	ChattingStyleLeft chattingStyleLeft;

	
	ImageIcon icon;
	Icon img;
	private String path;
	
	
    private boolean lineWrap;
    
    private String msg;
    private boolean flag;
    private int fontSize;
    private String fontName;
    private int fontStyle;
//    private 
    public MyTextPane(ChattingStyleLeft chattingStyleLeft, String path, Icon img,
    		final boolean lineWrap, String msg, boolean flag,String fontName,int fontStyle,  int fontSize) {
		this.chattingStyleLeft = chattingStyleLeft;
		this.img = img;
		this.path = path;

    	
        this.lineWrap = lineWrap;
        this.msg = msg;
        this.flag = flag;
        this.fontName = fontName;
        this.fontStyle = fontStyle;
        this.fontSize = fontSize;
        
        if (lineWrap)
            setEditorKit(new WrapEditorKit());
        
        path = chattingStyleLeft.mcwf.getPath();
		System.out.println(path);
		

//		JLabel la_icon = new JLabel();
//		System.out.println(chattingStyleLeft);
//		la_icon  = chattingStyleLeft.mcwf.postImg();
//		Icon myImgIcon = new ImageIcon(path);
//		img = new ImageIcon(path);
//		JLabel la_img = new JLabel(img);
//		JLabel la_icon = new JLabel();
		postImg(path);
//		insertComponent(la_icon);
//		insertComponent(la_img);
//		insertComponent(la_icon);

		
		// 이미지 경로 한번 받아오면 다시 채팅갈 수 있게
		path = "";
        
        setEditable(false);
        setFont(new Font(fontName, fontStyle, fontSize)); 
        //textpane 이용해서 text에 스타일 주기
        StyledDocument doc = getStyledDocument();
        SimpleAttributeSet left = new SimpleAttributeSet();
        StyleConstants.setAlignment(left, StyleConstants.ALIGN_LEFT);
        StyleConstants.setForeground(left, Color.RED);

        SimpleAttributeSet right = new SimpleAttributeSet();
        StyleConstants.setAlignment(right, StyleConstants.ALIGN_RIGHT);
        StyleConstants.setForeground(right, Color.BLUE);
        
        //boolean 으로 식별
        try {
        	if(flag) {
        		doc.insertString(doc.getLength(),("\n")+msg, right );
        		doc.setParagraphAttributes(doc.getLength(), 1, right, false);
        	}else if(!flag) {
        		
        		doc.insertString(doc.getLength(), /*"다른사람  :  " +*/("\n")+ msg, left);
        		doc.setParagraphAttributes(doc.getLength(), 1, left, false);
        	}
		} catch (BadLocationException e1) {
			e1.printStackTrace();
		}
        
        //이미지 사이즈 변경해주는거 따로 뺴놈
//         icon = ResizeImageIcon.resizeImageIcon(path, 70, 70);
//        textPane.insertIcon(icon);
        
        
        
    }

    @Override
    public boolean getScrollableTracksViewportWidth() {
        if (lineWrap)
            return super.getScrollableTracksViewportWidth();
        else
            return getParent() == null
                  || getUI().getPreferredSize(this).width <= getParent().getSize().width;
    }

    private class WrapEditorKit extends StyledEditorKit {
        private final ViewFactory defaultFactory = new WrapColumnFactory();

        @Override
        public ViewFactory getViewFactory() {
            return defaultFactory;
        }
    }

    private class WrapColumnFactory implements ViewFactory {
        @Override
        public View create(final Element element) {
            final String kind = element.getName();
            if (kind != null) {
                switch (kind) {
                    case AbstractDocument.ContentElementName:
                        return new WrapLabelView(element);
                    case AbstractDocument.ParagraphElementName:
                        return new ParagraphView(element);
                    case AbstractDocument.SectionElementName:
                        return new BoxView(element, View.Y_AXIS);
                    case StyleConstants.ComponentElementName:
                        return new ComponentView(element);
                    case StyleConstants.IconElementName:
                        return new IconView(element);
                }
            }

            // Default to text display.
            return new LabelView(element);
        }
    }

    private class WrapLabelView extends LabelView {
        public WrapLabelView(final Element element) {
            super(element);
        }

        @Override
        public float getMinimumSpan(final int axis) {
            switch (axis) {
                case View.X_AXIS:
                    return 0;
                case View.Y_AXIS:
                    return super.getMinimumSpan(axis);
                default:
                    throw new IllegalArgumentException("Invalid axis: " + axis);
            }
        }
    }
	public void postImg(String path) {
		
		Icon myImgIcon =ResizeImageIcon.resizeImageIcon(path, 140	, 140);
		JLabel la_icon = new JLabel(myImgIcon);
		insertComponent(la_icon);
	}
}
