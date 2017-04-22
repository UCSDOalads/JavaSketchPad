package actions;

import javax.swing.JOptionPane;

import actions.edit.undoredo.SharedUndoRedoActionManager;
import actions.edit.undoredo.UndoRedoableInterface;
import actions.global.ActionName;
import actions.global.GlobalPaintActionExecuter;
import actions.global.globalactions.AddDataDisplayBoxGlobalAction;
import actions.global.globalactions.AddTextBoxGlobalAction;
import actions.menu.ActionsMenuBarTitles;
import paintcomponents.TextPaintComponent;
import ui.PaintPanel;

/**
 * add textbox to the panel
 * 
 * @author Bingjie Zhou
 */
public class AddTextBoxAction extends MenuBarPaintAction {

	/**
	 * constructor to create object
	 * 
	 * @param panel
	 *            panel to add component on
	 */
	public AddTextBoxAction(PaintPanel panel) {
		super(panel);
	}

	/**
	 * check whether can be performed or not
	 */
	@Override
	public boolean canPerformAction() {
		return true;
	}

	/**
	 * add the text box component to panel
	 */
	@Override
	public void performAction() {
		String s = JOptionPane
				.showInputDialog("Please enter the text to display");
		AddTextBoxGlobalAction assiciatedAction = (AddTextBoxGlobalAction) ActionName.ADD_TEXT_BOX_ACTION
				.getAssiciatedAction();
		assiciatedAction.setDisplayString(s);
		assiciatedAction.setX(panel.getWidth() / 2);
		assiciatedAction.setY(panel.getHeight() / 2);
		GlobalPaintActionExecuter.getSharedInstance().execute(assiciatedAction,
				panel);
	}

	/**
	 * return local strings in the text box
	 */
	@Override
	public String locationString() {
		return ActionsMenuBarTitles.Developer("Add/Text Box...").toString();
	}

}
