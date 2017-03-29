package actions.edit.undoredo;

import org.hamcrest.StringDescription;

public interface UndoRedoableInterface {
	public void undoAction();
	public void redoAction();
	public String description();
}
