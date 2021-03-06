package actions.global.globalactions;

import ui.PaintPanel;
import actions.edit.undoredo.SharedUndoRedoActionManager;
import actions.global.GlobalPaintAction;

public class EditUndoGlobalAction extends GlobalPaintAction {
	SharedUndoRedoActionManager manager = SharedUndoRedoActionManager
			.getSharedInstance();
	@Override
	protected void execute(PaintPanel panel) {
		manager.undo();
	}

}
