package actions.edit.undoredo;

import ui.helper.historyui.HistoryDataObject;

public abstract class UndoRedoableInterface extends HistoryDataObject {

	public abstract void undoAction();
	public abstract void redoAction();
	public String description(){
		return commandName() + " -- " + commandDescription();
	}
	protected abstract String commandName();
	protected abstract String commandDescription();
}
