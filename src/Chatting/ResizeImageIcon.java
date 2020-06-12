package Chatting;

import java.awt.Image;

import javax.swing.ImageIcon;

public class ResizeImageIcon {
	
	public  static ImageIcon resizeImageIcon(String path, int w, int h) {
		
        //æ∆¿Ãƒ‹
        ImageIcon imageIcon = new ImageIcon(path); // load the image to a imageIcon
        Image image = imageIcon.getImage(); // transform it 
        Image newimg = image.getScaledInstance(w	, h,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
        imageIcon = new ImageIcon(newimg);  // transform it back
        
        return imageIcon;
	}

}
