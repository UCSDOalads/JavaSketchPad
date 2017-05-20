package painttools.tools;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

import ui.PaintPanel;
import actions.AddLazyJavaMethodComponentAction;
import buttons.ToolButton;

public class AddMethodTool extends PaintTool {

	private ToolButton button;
	
	public AddMethodTool(PaintPanel panel) {
        button = new ToolButton("Add Method");
		
        AddLazyJavaMethodComponentAction action = new AddLazyJavaMethodComponentAction(panel);
		
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if (action.canPerformAction()) {
					action.performAction();
				}
				
			}
		});
	}
	
	
	@Override
	public void start(PaintPanel panel) {
	}
	
	@Override
	public ToolButton getButton() {
		return button;
	}

	@Override
	public void reset() {
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	
}