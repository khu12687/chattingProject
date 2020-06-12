package StaticMethods;

import java.awt.Image;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class ResizeImageIcon {
	static ImageIcon icon;
	public static ImageIcon resizeImageIcon(String path, int w, int h) {

		File f = new File(path);
		URL url;
		try {
			url = f.toURL();
			icon = new ImageIcon(url);
			// You have to convert it to URL because ImageIO just ruins the animation

			icon.setImage(icon.getImage().getScaledInstance(w, h, Image.SCALE_DEFAULT));
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return icon;
	}
//        //æ∆¿Ãƒ‹
//        ImageIcon imageIcon = new ImageIcon(path); // load the image to a imageIcon
//        Image image = imageIcon.getImage(); // transform it 
//        Image newimg = image.getScaledInstance(w	, h,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
//        imageIcon = new ImageIcon(newimg);  // transform it back
//        
//        return imageIcon;
}
