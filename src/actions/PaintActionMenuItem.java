package actions;

import javax.swing.JMenuItem;

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
