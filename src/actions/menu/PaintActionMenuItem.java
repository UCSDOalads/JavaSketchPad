package actions.menu;

import javax.swing.JMenuItem;

import actions.MenuBarPaintAction;

public class PaintActionMenuItem extends JMenuItem{

	
	private MenuBarPaintAction associatedAction;
	private ActionsMenuBar actionsMenuBar;

	public PaintActionMenuItem(MenuBarPaintAction associatedAction, ActionsMenuBar actionsMenuBar) {
		this.setAssociatedAction(associatedAction);
		this.actionsMenuBar = actionsMenuBar;
	}


	public MenuBarPaintAction getAssociatedAction() {
		return associatedAction;
	}

	public void setAssociatedAction(MenuBarPaintAction associatedAction) {
		this.associatedAction = associatedAction;
	}

	public void performAction() {
		associatedAction.performAction();
		//update menu bar status
		if(actionsMenuBar != null)
			actionsMenuBar.updateEnableStatusForAllMenuItems();
	}
}
