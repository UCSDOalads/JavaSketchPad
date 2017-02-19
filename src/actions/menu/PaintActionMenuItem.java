package actions.menu;

import javax.swing.JMenuItem;

import actions.PaintAction;

public class PaintActionMenuItem extends JMenuItem{

	
	private PaintAction associatedAction;

	public PaintActionMenuItem(PaintAction associatedAction) {
		this.setAssociatedAction(associatedAction);

	}

	public PaintAction getAssociatedAction() {
		return associatedAction;
	}

	public void setAssociatedAction(PaintAction associatedAction) {
		this.associatedAction = associatedAction;
	}
}
