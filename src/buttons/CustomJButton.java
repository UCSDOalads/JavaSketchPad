package buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class CustomJButton extends JButton{

	private String buttonTitle;
	
	public CustomJButton(String title){
		buttonTitle = title;
		setOpaque(false);
		setFocusPainted(false);
		setBorderPainted(false);
	}
	public void setCustomImageIcon(ImageIcon i){
		setIcon(i);
		setText("");
		
	}
	
	public void setButtonTitle(String title){
		buttonTitle = title;
	}
	
	public String getButtonTitle(){
		return buttonTitle;
	}

}
