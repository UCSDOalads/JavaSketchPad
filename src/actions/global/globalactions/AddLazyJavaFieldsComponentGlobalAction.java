package actions.global.globalactions;

import paintcomponents.java.lazy.ClassPaintComponent;
import paintcomponents.java.lazy.FieldsPaintComponent;
import ui.PaintPanel;
import actions.edit.undoredo.SharedUndoRedoActionManager;
import actions.edit.undoredo.UndoRedoableInterface;
import actions.global.GlobalPaintAction;

public class AddLazyJavaFieldsComponentGlobalAction extends GlobalPaintAction {

    private ClassPaintComponent comp;

    @Override
    protected void execute(PaintPanel panel) {
	FieldsPaintComponent fieldsComp = new FieldsPaintComponent(
		comp.getDisplayingClass(), panel.getWidth() / 2,
		panel.getHeight() / 2);
	panel.addPaintComponent(fieldsComp);
	// push action to the manager
	SharedUndoRedoActionManager.getSharedInstance().pushUndoableAction(
		new UndoRedoableInterface() {

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
		});
	panel.repaint();
    }

    public void setComponent(ClassPaintComponent comp) {
	this.comp = comp;
    }
}
