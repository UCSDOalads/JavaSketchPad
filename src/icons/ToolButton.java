package icons;

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
	}
	
	
	public void setSelectedImage(ImageIcon icon){
		setSelectedIcon(icon);
	}
	
	public void setOriginalImage(ImageIcon icon){
		setIcon(icon);
	}

}
