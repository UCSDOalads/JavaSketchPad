package painttools.tools;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;

import actions.AddDataInputBoxAction;
import buttons.ToolButton;
import ui.PaintPanel;
import ui.cursor.CustomCursors;
import ui.icons.CustomIcons;

public class AddInputBoxTool implements ActionToolsInterface {

	private ToolButton button;
	private PaintPanel panel;

	public AddInputBoxTool(PaintPanel panel) {
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
		button.setOriginalImage(CustomIcons.arrow());
		button.setSelectedImage(CustomIcons.selectedArrow());
		button.addActionListener(this);
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
		
		AddDataInputBoxAction action = new AddDataInputBoxAction(panel);
		if(action.canPerformAction()){
			action.setXY(e.getX(), e.getY());
			action.performAction();
			action.setDefaultXY();
		}
		panel.setDefaultSelectTool();

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

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		panel.setNewCursor (CustomCursors.addComponentcursor());
		
	}



}
