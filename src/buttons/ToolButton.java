package buttons;

import java.awt.Dimension;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToggleButton;

public class ToolButton extends JToggleButton {
	Dimension d = new Dimension(40,40);
	
	public ToolButton(String str){
		super(str);

		
	}
	
	public ToolButton(){
		setOpaque(false);
		setFocusPainted(false);
		setBorderPainted(false);
		setButtonSize(d);
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
	
	public void setButtonSize(Dimension d){
		setSize(d);
		
	}

}
