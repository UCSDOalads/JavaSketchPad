package painttools.tools;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;

import actions.AddLazyJavaMethodComponentAction;
import buttons.ToolButton;
import ui.PaintPanel;
import ui.cursor.CustomCursors;

public class AddMethodTool implements ActionToolsInterface {

	private ToolButton button;
	private PaintPanel panel;
	public AddMethodTool(PaintPanel panel) {
		this.panel = panel;
        createButton();
		

	}
	
	
	@Override
	public void start(PaintPanel panel) {
	}
	
	@Override
	public void createButton() {
		// TODO Auto-generated method stub
		button = new ToolButton();

		ImageIcon icon = new ImageIcon("./images/dot.png");
		button.setOriginalImage(icon);

		ImageIcon icon2 = new ImageIcon("./images/dotselected.png");
		button.setSelectedImage(icon2);
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
	}


	@Override
	public void actionPerformed(ActionEvent e) {

		panel.setNewCursor (CustomCursors.addComponentcursor());
		
	}




	
}