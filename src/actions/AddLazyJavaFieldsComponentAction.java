package actions;

import paintcomponents.java.lazy.ClassPaintComponent;
import ui.PaintPanel;
import actions.global.ActionName;
import actions.global.GlobalPaintActionExecuter;
import actions.global.globalactions.AddLazyJavaFieldsComponentGlobalAction;
import actions.menu.ActionsMenuBarTitles;

public class AddLazyJavaFieldsComponentAction extends MenuBarPaintAction {

	public AddLazyJavaFieldsComponentAction(PaintPanel panel) {
		super(panel);
	}

	// TODO
	// NOTE: I am copying from Constructor and Methods, consider refinement
	@Override
	public boolean canPerformAction() {
		if (panel.getSelectTool().getSelectedComponents().size() != 1) {
			return false;
		}
		if (panel.getSelectTool().getSelectedComponents().get(0) instanceof ClassPaintComponent) {
			return true;
		}
		return false;
	}

	@Override
	public void performAction() {
		ClassPaintComponent comp = (ClassPaintComponent) panel.getSelectTool()
				.getSelectedComponents().get(0);

		AddLazyJavaFieldsComponentGlobalAction assiciatedAction = (AddLazyJavaFieldsComponentGlobalAction) ActionName.ADD_LAZY_JAVA_FIELDS_ACTION
				.getAssiciatedAction();
		assiciatedAction.setComponent(comp);
		assiciatedAction.setCoord(panel.getWidth() / 2, panel.getHeight() / 2);
		GlobalPaintActionExecuter.getSharedInstance().execute(assiciatedAction,
				panel);
	}

	@Override
	public String locationString() {
		return ActionsMenuBarTitles.Lazy().Add().Java_Fields().toString();
	}

}
