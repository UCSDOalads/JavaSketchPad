package actions.edit.undoredo;

import java.util.Stack;

/**
 * 
 * @author cs12wagn
 * add to stack
 * 
 */
public class SharedUndoRedoActionManager {
	private static SharedUndoRedoActionManager sharedInstance = new SharedUndoRedoActionManager();
	private Stack<UndoRedoableInterface> undoStack;
	private Stack<UndoRedoableInterface> redoStack;
	private SharedUndoRedoActionManagerDelegate delegate;
	
	/** Static instance method */
	public static SharedUndoRedoActionManager getSharedInstance(){
		return sharedInstance;
	}
	
	/**
	 * A private Constructor prevents any other
   * class from instantiating.
   */
	private SharedUndoRedoActionManager() {
		undoStack = new Stack<>();
		redoStack = new Stack<>();
	}
	
	public void undo() {
		UndoRedoableInterface undoableAction = undoStack.pop();
		undoableAction.undoAction();
		redoStack.add(undoableAction);
		delegate.didUndoAction(undoableAction);
	}
	
	public void redo() {
		UndoRedoableInterface redoableAction = redoStack.pop();
		redoableAction.redoAction();
		undoStack.add(redoableAction);
		delegate.didUndoAction(redoableAction);
	}
	
	public boolean canUndo() {
		return !undoStack.isEmpty();
	}
	
	public boolean canRedo() {
		return !redoStack.isEmpty();
	}
	
	public void pushUndoableAction( UndoRedoableInterface undoredoableAction ) {
		undoStack.push(undoredoableAction);
		redoStack.clear();
	}

	public SharedUndoRedoActionManagerDelegate getDelegate() {
		return delegate;
	}

	public void setDelegate(SharedUndoRedoActionManagerDelegate delegate) {
		this.delegate = delegate;
	}

}
