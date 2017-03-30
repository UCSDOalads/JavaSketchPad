package actions.edit.undoredo;


public abstract class UndoRedoableInterface {
	public abstract void undoAction();
	public abstract void redoAction();
	public String description(){
		return commandName() + " -- " + commandDescription();
	}
	protected abstract String commandName();
	protected abstract String commandDescription();
}
