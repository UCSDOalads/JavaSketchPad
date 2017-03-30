package actions.edit.undoredo;

public interface SharedUndoRedoActionManagerDelegate {
	
	public void didUndoAction(UndoRedoableInterface obj);
	public void didRedoAction(UndoRedoableInterface obj);
}
