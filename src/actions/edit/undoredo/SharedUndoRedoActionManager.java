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
		if(canUndo()){
			UndoRedoableInterface undoableAction = undoStack.pop();
			undoableAction.undoAction();
			redoStack.add(undoableAction);
			delegate.didUndoAction(undoableAction);
		}
		else
			System.out.println("empty undo stack");
	}
	
	public void redo() {
		if(canRedo()){
			UndoRedoableInterface redoableAction = redoStack.pop();
			redoableAction.redoAction();
			undoStack.add(redoableAction);
			delegate.didRedoAction(redoableAction);
		}
		else
			System.out.println("empty redo stack");
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
		delegate.didAddNewAction(undoredoableAction);
	}

	

	public void setDelegate(SharedUndoRedoActionManagerDelegate delegate) {
		this.delegate = delegate;
	}

	/**
	 * @return the undoStack
	 */
	public Stack<UndoRedoableInterface> getUndoStack() {
		return undoStack;
	}

	/**
	 * @return the redoStack
	 */
	public Stack<UndoRedoableInterface> getRedoStack() {
		return redoStack;
	}

}
