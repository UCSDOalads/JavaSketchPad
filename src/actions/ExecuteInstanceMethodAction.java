package actions;

import actions.global.ActionName;
import actions.global.GlobalPaintActionExecuter;
import actions.global.globalactions.AddLazyJavaClassGlobalAction;
import actions.global.globalactions.ExecuteInstanceMethodGlobalAction;
import actions.menu.ActionsMenuBarTitles;
import paintcomponents.java.interactive.MethodPaintComponent;
import ui.PaintPanel;
import ui.general.InputManager;
import ui.general.InputManagerDelegate;

public class ExecuteInstanceMethodAction extends MenuBarPaintAction {

	public ExecuteInstanceMethodAction(PaintPanel panel) {
		super(panel);
	}

	@Override
	public boolean canPerformAction() {
		if (panel.getSelectTool().getSelectedComponents().size() != 1) {
			return false;
		}
		if (panel.getSelectTool().getSelectedComponents()
				.get(0) instanceof MethodPaintComponent) {
			return true;
		}
		return false;
	}

	
	
	@Override
	public void performAction() {
			ExecuteInstanceMethodGlobalAction associatedAction
			=  (ExecuteInstanceMethodGlobalAction)ActionName.EXECUTE_INSTANCE_METHOD_ACTION.getAssociatedAction();
			GlobalPaintActionExecuter.getSharedInstance().execute(associatedAction, panel);
	}

	@Override
	public String locationString() {
		// TODO Auto-generated method stub
		return ActionsMenuBarTitles.Lazy().Add().Execute_Instance_Method().toString();
	}
	
	
	
}
