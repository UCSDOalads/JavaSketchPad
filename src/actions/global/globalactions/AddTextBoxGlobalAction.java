package actions.global.globalactions;

import javax.swing.JOptionPane;

import paintcomponents.TextPaintComponent;
import actions.edit.undoredo.SharedUndoRedoActionManager;
import actions.edit.undoredo.UndoRedoableInterface;
import actions.global.GlobalPaintAction;
import ui.PaintPanel;

/**
 * add text box to panel in global
 * 
 * @author Bingjie Zhou
 */
public class AddTextBoxGlobalAction extends GlobalPaintAction {

	private String textDisplay;
	private int x;
	private int y;

	/**
	 * set text to display
	 */
	public void setDisplayString(String text) {
		this.textDisplay = text;
	}

	/**
	 * set x
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * set y
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * execute the add function
	 * 
	 * @param panel
	 *            add component to this panel
	 */
	@Override
	protected void execute(PaintPanel panel) {
		// TODO Auto-generated method stub
		TextPaintComponent comp = new TextPaintComponent(textDisplay, x, y);
		panel.addPaintComponent(comp);
		// push action to the manager
		SharedUndoRedoActionManager.getSharedInstance().pushUndoableAction(
				new UndoRedoableInterface() {

					@Override
					public void undoAction() {
						comp.remove(panel);
						panel.repaint();
					}

					@Override
					public void redoAction() {
						panel.addPaintComponent(comp);
						panel.repaint();
					}

					@Override
					protected String commandName() {
						return "add textBox";
					}

					@Override
					protected String commandDescription() {
						return "[Deprecated] add a generic textbox";
					}
				});
		panel.repaint();
	}

}
