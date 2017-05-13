package actions.global.globalactions;

import java.lang.reflect.Method;

import paintcomponents.java.interactive.InstanceOperationComponent;
import ui.PaintPanel;
import actions.edit.undoredo.SharedUndoRedoActionManager;
import actions.edit.undoredo.UndoRedoableInterface;
import actions.global.GlobalPaintAction;

public class AddInstanceMethodGlobalAction extends GlobalPaintAction {

	private InstanceOperationComponent insComp;
	private Method methodToSet;

	@Override
	protected void execute(PaintPanel panel) {
		insComp.addMethodPaintComponent(methodToSet, panel);
		SharedUndoRedoActionManager.getSharedInstance().pushUndoableAction(
				new UndoRedoableInterface() {

					@Override
					public void undoAction() {
						insComp.remove(panel);
						panel.repaint();
					}

					@Override
					public void redoAction() {
						panel.addPaintComponent(insComp);
						panel.repaint();
					}

					@Override
					protected String commandName() {
						return "add lazy javaInstanceMethodComponent";
					}

					@Override
					protected String commandDescription() {
						return "add a lazily evaluated java instance method component";
					}
				});
		panel.repaint();
	}

	public void setInsComp(InstanceOperationComponent insComp) {
		this.insComp = insComp;
	}

	public void setMethodToSet(Method methodToSet) {
		this.methodToSet = methodToSet;
	}

}
