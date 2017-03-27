package actions.edit.undoredo;

/**
 * An interface for implementing undo and redos.
 * 
 * Each undoable action should push an instance of this interface to the undo redo manger.
 * @author chenzb
 *
 */
public interface UndoRedoableInterface {
	public void undoAction();
	public void redoAction();
}
