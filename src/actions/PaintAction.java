package actions;

import ui.PaintPanel;

public abstract class PaintAction {
	
	protected PaintPanel panel;

	public PaintAction(PaintPanel panel){
		this.panel = panel;
	}
	
	public abstract boolean canPerformAction();
	public abstract void performAction();
	public abstract String locationString();

}
