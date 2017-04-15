package actions;

import ui.PaintPanel;
import actions.edit.undoredo.SharedUndoRedoActionManager;
import actions.menu.ActionsMenuBarTitles;

public class UndoAction extends MenuBarPaintAction {

	SharedUndoRedoActionManager manager = SharedUndoRedoActionManager.getSharedInstance();
	
	public UndoAction(PaintPanel panel) {
		super(panel);
	}

	@Override
	public boolean canPerformAction() {
		return manager.canUndo();
	}

	@Override
	public void performAction() {
		manager.undo();
	}

	@Override
	public String locationString() {
		return ActionsMenuBarTitles.Edit().Undo().toString();
	}

}
