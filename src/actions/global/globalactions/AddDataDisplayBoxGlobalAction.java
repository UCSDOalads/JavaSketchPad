package actions.global.globalactions;

import paintcomponents.data.DataDisplayPaintComponent;
import paintcomponents.data.DataInputTextfieldPaintComponent;
import actions.edit.undoredo.SharedUndoRedoActionManager;
import actions.edit.undoredo.UndoRedoableInterface;
import actions.global.GlobalPaintAction;
import ui.PaintPanel;

public class AddDataDisplayBoxGlobalAction extends GlobalPaintAction {

	private String dataDisplay;
	private int x;
	private int y;

	public void setDisplayString(String dataDisplay) {
		this.dataDisplay = dataDisplay;
	}

	/**
	 * @param x
	 *            the x to set
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * @param y
	 *            the y to set
	 */
	public void setY(int y) {
		this.y = y;
	}

	@Override
	protected void execute(PaintPanel panel) {
		DataDisplayPaintComponent comp = new DataDisplayPaintComponent(
				dataDisplay, x, y);
		panel.addPaintComponent(comp);

		// push action to manager
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
						return "add data displayBox";
					}

					@Override
					protected String commandDescription() {
						return "add a string display";
					}

				});
		panel.repaint();
	}

}