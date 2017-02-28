package actions;

import ui.PaintPanel;

/**
 * Paint Action abstracts a particular menu action
 * 
 * Override this class to create a new Action.
 * 
 * Perfrom action will only be called when can perform action returns true.
 * 
 * Most of the cases, you have to call panel.repaint() as the last statement of
 * perform action
 * 
 * @author chenzb
 *
 */
public abstract class PaintAction {

	protected PaintPanel panel;

	public PaintAction(PaintPanel panel) {
		this.panel = panel;
	}

	/**
	 * Whether this action can perform. Subclasses generally base the return
	 * value on the current selection on screen
	 * <code>panel.getSelectTool().getSelectedComponents</code>
	 * 
	 * @return true if the action can be performed
	 */
	public abstract boolean canPerformAction();

	/**
	 * Performs this action Subclassess must invoke panel.repaint if the action
	 * changes the panel
	 */
	public abstract void performAction();

	/**
	 * The location of this item in the menu bar.
	 * For example, "File/Save As..", "File/Open Recent/Clear Menu"
	 * @return
	 */
	public abstract String locationString();

}
