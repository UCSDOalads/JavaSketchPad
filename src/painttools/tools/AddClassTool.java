package painttools.tools;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

import actions.AddLazyJavaClassAction;
import buttons.ToolButton;
import ui.PaintPanel;
import ui.cursor.CustomCursors;
import ui.icons.CustomIcons;

public class AddClassTool implements ActionToolsInterface {

	private ToolButton button;
	private PaintPanel panel;

	public AddClassTool(PaintPanel panel) {
		this.panel = panel;
		createButton();


	}

	@Override
	public void start(PaintPanel panel) {
	}

	/**
	 * create a toolButton for this tool, and set icons
	 */
	@Override
	public void createButton() {
		// TODO Auto-generated method stub
		button = new ToolButton();
		button.setOriginalImage(CustomIcons.classIcon());
		button.setSelectedImage(CustomIcons.selectedClassIcon());

		button.addActionListener(this);

		
	}
	@Override
	public ToolButton getButton() {
		return button;
	}

	@Override
	public void reset() {

	}

	/**
	 * when mouse click paintPanel, add class dialog will pop up, and a class
	 * component will be added to where mouse was clicked
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		AddLazyJavaClassAction action = new AddLazyJavaClassAction(panel);
		if (action.canPerformAction()) {
			// set the starting point for class component
			action.setXY(e.getX(), e.getY());
			action.performAction();
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
	public void mouseWheelMoved(MouseWheelEvent e) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * change the cursor when add button is clicked
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		panel.setNewCursor (CustomCursors.addComponentcursor());
	}
}
