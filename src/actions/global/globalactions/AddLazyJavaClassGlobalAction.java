package actions.global.globalactions;

import paintcomponents.java.lazy.ClassPaintComponent;
import ui.PaintPanel;
import actions.edit.undoredo.SharedUndoRedoActionManager;
import actions.edit.undoredo.UndoRedoableInterface;
import actions.global.GlobalPaintAction;

public class AddLazyJavaClassGlobalAction extends GlobalPaintAction {

	private Class classToCreate;
	private int x;
	private int y;

	/**
	 * @param classToCreate
	 *            the classToCreate to set
	 */
	public void setClassToCreate(Class classToCreate) {
		this.classToCreate = classToCreate;
	}

	@Override
	protected void execute(PaintPanel panel) {
		ClassPaintComponent comp = new ClassPaintComponent(classToCreate, x, y);
		//The class input box shouldn't be updatable.
		comp.setCannotUpdate();
		panel.addPaintComponent(comp);

		if (panel.getSelectTool() != null) {
			panel.getSelectTool().clearSelection();
			panel.getSelectTool().selectComponent(comp);
		}

		// add action to undo redo manager
		UndoRedoableInterface undoRedoBlock = new UndoRedoableInterface() {

			@Override
			public void undoAction() {
				comp.remove(panel);
				panel.repaint();
			}

			@Override
			public void redoAction() {
				panel.addPaintComponent(comp);
				panel.repaint();
			}

			@Override
			protected String commandName() {
				return "add lazy javaClass";
			}

			@Override
			protected String commandDescription() {
				return "add a java class component";
			}
		};

		SharedUndoRedoActionManager.getSharedInstance().pushUndoableAction(
				undoRedoBlock);
		panel.repaint();

	}

	public void setCoord(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
