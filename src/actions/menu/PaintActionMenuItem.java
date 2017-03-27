package actions.menu;

import javax.swing.JMenuItem;

import actions.PaintAction;

/**
 * An action menu item is a JMenuItme associated with an action. 
 * 
 * The associated paint action is invoked on a mouse click.
 * 
 * @author chenzb
 *
 */
public class PaintActionMenuItem extends JMenuItem{

	
	private PaintAction associatedAction;
	private ActionsMenuBar actionsMenuBar;

	public PaintActionMenuItem(PaintAction associatedAction, ActionsMenuBar actionsMenuBar) {
		this.setAssociatedAction(associatedAction);
		this.actionsMenuBar = actionsMenuBar;
	}
	public PaintActionMenuItem(PaintAction associatedAction) {
		this.setAssociatedAction(associatedAction);
	}
	public PaintAction getAssociatedAction() {
		return associatedAction;
	}

	public void setAssociatedAction(PaintAction associatedAction) {
		this.associatedAction = associatedAction;
	}

	public void performAction() {
		associatedAction.performAction();
		//update menu bar status
		if(actionsMenuBar != null)
			actionsMenuBar.updateEnableStatusForAllMenuItems();
	}
}
