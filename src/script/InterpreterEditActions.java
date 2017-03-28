package script;

import actions.edit.undoredo.SharedUndoRedoActionManager;
import ui.PaintPanel;

public class InterpreterEditActions {

	private static final String UNDO = "undo";
	private static final String REDO = "redo";

	public InterpreterEditActions(Tokenizer tokenizer, PaintPanel panel)
			throws ExecutionErrorException {

		if (tokenizer.hasNext()) {
			switch (tokenizer.next()) {
			case REDO:
				performRedo();
				break;

			case UNDO:
				performUndo();
				break;
				
			default:
				throw new ExecutionErrorException("invalid script");
			}
		} else {
			throw new ExecutionErrorException("incomplete script");
		}
	}

	private void performRedo() {
		SharedUndoRedoActionManager manager = SharedUndoRedoActionManager.getSharedInstance();
		manager.redo();
	}

	private void performUndo() {
		SharedUndoRedoActionManager manager = SharedUndoRedoActionManager.getSharedInstance();
		manager.undo();
	}
}
