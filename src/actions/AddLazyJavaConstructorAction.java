package actions;

import paintcomponents.java.lazy.ClassPaintComponent;
import ui.PaintPanel;
import actions.global.ActionName;
import actions.global.GlobalPaintActionExecuter;
import actions.global.globalactions.AddLazyJavaConstructorGlobalAction;
import actions.menu.ActionsMenuBarTitles;

public class AddLazyJavaConstructorAction extends MenuBarPaintAction {

    public AddLazyJavaConstructorAction(PaintPanel panel) {
	super(panel);
    }

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

	AddLazyJavaConstructorGlobalAction assiciatedAction = (AddLazyJavaConstructorGlobalAction) ActionName.ADD_LAZY_JAVA_CONSTRUCTOR_ACTION
		.getAssiciatedAction();
	assiciatedAction.setComponent(comp);
	GlobalPaintActionExecuter.getSharedInstance().execute(assiciatedAction,
		panel);
    }

    @Override
    public String locationString() {
	return ActionsMenuBarTitles.Lazy().Add().Java_Constructor().toString();
    }

}
