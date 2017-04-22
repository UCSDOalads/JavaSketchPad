package actions.global.globalactions;

import paintcomponents.java.lazy.ClassPaintComponent;
import paintcomponents.java.lazy.FieldsPaintComponent;
import ui.PaintPanel;
import actions.edit.undoredo.SharedUndoRedoActionManager;
import actions.edit.undoredo.UndoRedoableInterface;
import actions.global.GlobalPaintAction;

public class AddLazyJavaFieldsComponentGlobalAction extends GlobalPaintAction {

	private ClassPaintComponent comp;
	private int x;
	private int y;

	@Override
	protected void execute(PaintPanel panel) {
		FieldsPaintComponent fieldsComp = new FieldsPaintComponent(
				comp.getDisplayingClass(), x, y);
		panel.addPaintComponent(fieldsComp);
		// push action to the manager
		UndoRedoableInterface undoRedoBlock = new UndoRedoableInterface() {

			@Override
			public void undoAction() {
				fieldsComp.remove(panel);
			}

			@Override
			public void redoAction() {
				panel.addPaintComponent(fieldsComp);

			}

			@Override
			protected String commandName() {
				return "add lazy javaFieldsComponent";
			}

			@Override
			protected String commandDescription() {
				return "add a java fields component";
			}
		};

		SharedUndoRedoActionManager.getSharedInstance().pushUndoableAction(
				undoRedoBlock);
		panel.repaint();
	}

	public void setComponent(ClassPaintComponent comp) {
		this.comp = comp;
	}

	public void setCoord(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
