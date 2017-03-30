package actions;

import actions.edit.undoredo.SharedUndoRedoActionManager;
import actions.edit.undoredo.UndoRedoableInterface;
import actions.menu.ActionsMenuBarTitles;
import paintcomponents.java.lazy.ClassPaintComponent;
import paintcomponents.java.lazy.FieldsPaintComponent;
import ui.PaintPanel;

public class AddLazyJavaFieldsComponentAction extends PaintAction {

public AddLazyJavaFieldsComponentAction(PaintPanel panel) {
		super(panel);
	}
	//TODO
	//NOTE: I am copying from Constructor and Methods, consider refinement
	@Override
	public boolean canPerformAction() {
		if (panel.getSelectTool().getSelectedComponents().size() != 1) {
			return false;
		}
		if (panel.getSelectTool().getSelectedComponents()
				.get(0) instanceof ClassPaintComponent) {
			return true;
		}
		return false;
	}
	@Override
	public void performAction() {
	ClassPaintComponent comp = (ClassPaintComponent) panel.getSelectTool()
				.getSelectedComponents().get(0);

		FieldsPaintComponent fieldsComp =
				new FieldsPaintComponent(comp.getDisplayingClass(),
				panel.getWidth() / 2,
				panel.getHeight() / 2);
		panel.addPaintComponent(fieldsComp);
		//push action to the manager
		SharedUndoRedoActionManager.getSharedInstance().pushUndoableAction(new UndoRedoableInterface() {
				
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

	@Override
	public String locationString() {
		return ActionsMenuBarTitles.Lazy().Add().Java_Fields().toString();
	}

}
