package actions.menu;

import javax.swing.JMenuItem;

import actions.PaintAction;

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
