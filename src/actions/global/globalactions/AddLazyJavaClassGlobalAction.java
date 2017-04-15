package actions.global.globalactions;

import paintcomponents.java.lazy.ClassPaintComponent;
import ui.PaintPanel;
import actions.edit.undoredo.SharedUndoRedoActionManager;
import actions.edit.undoredo.UndoRedoableInterface;
import actions.global.GlobalPaintAction;

public class AddLazyJavaClassGlobalAction extends GlobalPaintAction {

	private Class classToCreate;
	private UndoRedoableInterface undoRedoBlock;

	/**
	 * @param classToCreate
	 *            the classToCreate to set
	 */
	public void setClassToCreate(Class classToCreate) {
		this.classToCreate = classToCreate;
	}

	@Override
	protected void execute(PaintPanel panel) {
		ClassPaintComponent comp = new ClassPaintComponent(classToCreate,
				panel.getWidth() / 2, panel.getHeight() / 2);
		panel.addPaintComponent(comp);

		// add action to undo redo manager
		undoRedoBlock = new UndoRedoableInterface() {

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

	protected UndoRedoableInterface getUndoRedoBlock() {
		return undoRedoBlock;
	}
}
