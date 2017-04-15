package actions.global;

import actions.edit.undoredo.UndoRedoableInterface;
import ui.PaintPanel;

/**
 * Global paint Action corresponds to any event happening on the paint panel.
 * Every menu itmes, paint tools send to action manager an instance of this
 * action.
 * 
 * @author chenzb
 *
 */
public abstract class GlobalPaintAction<T extends GlobalPaintAction<?>> {

	protected abstract void execute(PaintPanel panel);

	/**
	 * Subclass if the action can be undone or redone, return an interface, else
	 * return null if this action cannot be undone.
	 * 
	 * @return
	 */
	protected abstract UndoRedoableInterface getUndoRedoBlock();

}
