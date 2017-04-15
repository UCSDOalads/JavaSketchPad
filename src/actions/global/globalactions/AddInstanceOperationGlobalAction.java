package actions.global.globalactions;

import java.lang.reflect.Constructor;

import actions.global.GlobalPaintAction;
import paintcomponents.java.interactive.InstanceOperationComponent;
import ui.PaintPanel;

public class AddInstanceOperationGlobalAction extends GlobalPaintAction {
	
	private Constructor constructorToSet;
	private InstanceOperationComponent insComp;
	
	@Override
	protected void execute(PaintPanel panel) {
		insComp = new InstanceOperationComponent(
				constructorToSet, panel.getWidth() / 2,
				panel.getHeight() / 2);
		panel.addPaintComponent(insComp);
		// add action to undo redo manager
//		SharedUndoRedoActionManager.getSharedInstance().pushUndoableAction(new UndoRedoableInterface() {
//					
//			@Override
//			public void undoAction() {
//				consComp.remove(panel);
//				panel.repaint();
//			}
//					
//			@Override
//			public void redoAction() {
//				panel.addPaintComponent(consComp);
//				panel.repaint();
//			}
//
//			@Override
//			protected String commandName() {
//				return "NOT YET IMPLEMENTED";
//			}
//
//			@Override
//			protected String commandDescription() {
//				return "NOT YET IMPLEMENTED";
//			}
//		});
		panel.repaint();
	}

	public void setConstructorToSet(Constructor constructorToSet) {
		this.constructorToSet = constructorToSet;
	}

	public InstanceOperationComponent getInsComp() {
		return insComp;
	}
}
