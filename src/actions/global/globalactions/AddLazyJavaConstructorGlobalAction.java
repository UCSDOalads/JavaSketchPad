package actions.global.globalactions;

import java.lang.reflect.Constructor;

import paintcomponents.java.lazy.ClassConstructorPaintComponent;
import paintcomponents.java.lazy.ClassPaintComponent;
import ui.PaintPanel;
import actions.edit.undoredo.SharedUndoRedoActionManager;
import actions.edit.undoredo.UndoRedoableInterface;
import actions.global.GlobalPaintAction;

public class AddLazyJavaConstructorGlobalAction extends GlobalPaintAction {

	private ClassPaintComponent comp;
	private int desiaredConstructorIndex;
	private UndoRedoableInterface undoRedoBlock;
	private int x;
	private int y;

	@Override
	protected void execute(PaintPanel panel) {
		Constructor[] cons = comp.getDisplayingClass().getConstructors();
		ClassConstructorPaintComponent consComp = new ClassConstructorPaintComponent(
				cons[desiaredConstructorIndex], x, y);
		panel.addPaintComponent(consComp);
		// add action to undo redo manager
		undoRedoBlock = new UndoRedoableInterface() {

			@Override
			public void undoAction() {
				consComp.remove(panel);
				panel.repaint();
			}

			@Override
			public void redoAction() {
				panel.addPaintComponent(consComp);
				panel.repaint();
			}

			@Override
			protected String commandName() {
				return "add lazy javaConstructor";
			}

			@Override
			protected String commandDescription() {
				return "add a lazily evaludated java constructor";
			}
		};

		SharedUndoRedoActionManager.getSharedInstance().pushUndoableAction(
				undoRedoBlock);
		panel.repaint();
	}

	public void setConstructorIndex(int index) {
		this.desiaredConstructorIndex = index;
	}

	public void setComponent(ClassPaintComponent comp) {
		this.comp = comp;
	}

	public Constructor[] getConstructor() {
		return comp.getDisplayingClass().getConstructors();
	}

	protected UndoRedoableInterface getUndoRedoBlock() {
		return undoRedoBlock;
	}

	public void setCoord(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
