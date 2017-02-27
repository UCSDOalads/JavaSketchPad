package actions;

import ui.PaintPanel;

public abstract class PaintAction {
	
	protected PaintPanel panel;

	public PaintAction(PaintPanel panel){
		this.panel = panel;
	}
	
	public abstract boolean canPerformAction();
	/**
	 * Performs this action
	 * Subclassess must invoke panel.repaint if the action changes the panel
	 */
	public abstract void performAction();

	public abstract String locationString();

}
