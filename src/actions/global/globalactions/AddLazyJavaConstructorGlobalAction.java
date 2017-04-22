package actions.global.globalactions;

import java.lang.reflect.Constructor;

import actions.edit.undoredo.SharedUndoRedoActionManager;
import actions.edit.undoredo.UndoRedoableInterface;
import actions.global.GlobalPaintAction;
import paintcomponents.java.interactive.ClassConstructorPaintComponent;
import ui.PaintPanel;

public class AddLazyJavaConstructorGlobalAction extends GlobalPaintAction {

	private Constructor cntrToCreate;
	
	/**
	 * @param cntrToCreate the cntrToCreate to set
	 */
	public void setCntrToCreate(Constructor cntrToCreate){	
		this.cntrToCreate = cntrToCreate;
	}
	
	@Override
	protected void execute(PaintPanel panel) {
		// TODO Auto-generated method stub
		ClassConstructorPaintComponent comp = new ClassConstructorPaintComponent(cntrToCreate,
				panel.getWidth() / 2, panel.getHeight() / 2);
		panel.addPaintComponent(comp);
		// add action to undo redo manager
		SharedUndoRedoActionManager.getSharedInstance().pushUndoableAction(new UndoRedoableInterface() {
			
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
				return "add interactive javaClassConstructor";
			}

			@Override
			protected String commandDescription() {
				return "add a java constructor component";
			}
		});
		panel.repaint();

	}

}
