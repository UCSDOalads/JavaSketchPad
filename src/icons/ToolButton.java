package icons;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToggleButton;

public class ToolButton extends JToggleButton {
	
	public ToolButton(String str){
		super(str);

		
	}
	
	public ToolButton(){
		setOpaque(false);
		setFocusPainted(false);
		setBorderPainted(false);
		setSize(60,60);
	}
	
	
	public void setSelectedImage(ImageIcon icon){
		Image img = icon.getImage() ;  
		Image newimg = img.getScaledInstance( this.getWidth(), this.getHeight(),  java.awt.Image.SCALE_SMOOTH ) ;  
		icon = new ImageIcon( newimg );
		setSelectedIcon(icon);
	}
	
	public void setOriginalImage(ImageIcon icon){
		Image img = icon.getImage() ;  
		Image newimg = img.getScaledInstance( this.getWidth(), this.getHeight(),  java.awt.Image.SCALE_SMOOTH ) ;  
		icon = new ImageIcon( newimg );
		setIcon(icon);
	}

}
